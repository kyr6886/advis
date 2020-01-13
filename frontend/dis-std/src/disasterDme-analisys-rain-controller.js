import Vue from 'vue'
import Axios from 'axios'
import * as d3 from 'd3'

var vue = new Vue({
    el: '#scope-anl-rain',
    data: function() {
        return {
          link: null,
          node: null,
          t:null,  
          raster:new ol.layer.Tile({source : new ol.source.OSM()}),
          chartObj:[]
          ,modal:{title:'',contents:[],evtDay:'',damageName:''}
          ,viewModel:{
            listDamages:[]
            ,listSido:[]
            ,listActionManual:[]
            ,selected:{
                damageType:'NHR',
                damageMoney:'',
                damagePerson:'',
                rain:'',
                sido:''
            },
            selectedOrgNode:{
                name:'',
                value:0
              },
              selectedTargetNode:{
                name:'',
                value:0
              }
            ,rain:{
                 orgInfo:null
                ,listOrgDamages:[]
                ,listOrgTimely:[]
                ,listOrgKmasAlert:[]
                ,listOrgKmasWarning:[]
                ,listOrgYearDme:[]
                ,listPrintOrgTimely:[]
                ,targetInfo:null
                ,listTargetDamages:[]
                ,listTargetTimely:[]
                ,listTargetKmasAlert:[]
                ,listTargetKmasWarning:[]
                ,listTargetYearDme:[]
                ,listPrintTargetTimely:[]
                

            }
            ,asos:{
                org:null,
                target:null
            }
            ,kma:{

            }
            ,message:{
                blank:'조회 조건(피해액,인명피해)을 선택 하세요.'
            }

          }

        }
    } 
    ,mounted: function() {
        console.log('loading');
        try {
           pageLoad(this);
           this.fnGetListSigungu();
           this.fnGetListDisasterManual();
           
        } catch (e) {
            console.log(e);
        }
    }
    , methods: {
        fnGetListSigungu:function(){
            let vm=this;
            Axios.post("/api/common/code/area/list.do", {})
            .then(function(response){
                vm.viewModel.listSido=response.data.listAreaSidoCodes;
            
            })
            .catch(function(ex){
                console.log(ex);
            });
        }
        ,fnOnClickBtnSearch:function(){
            this.fnGetListRainDamage();
            
        },

        fnGetListDisasterManual:function(){
            let vm=this;
            Axios.post("/disaster/manual/list/group", {
                paramCtgId:vm.viewModel.selected.damageType
            })
            .then(function(response){
                vm.viewModel.listActionManual=response.data.resultManualList;
            
            })
            .catch(function(ex){

            });
        },
        //특보 지역조회
        fnGetKmaReport:function(isDropOrg,paramBegDate,paramEndDate){
            let vm=this;
            Axios.post("/api/statistics/kma/list",{paramStartDate:paramBegDate+"000000",paramEndDate:paramEndDate+"235900"})
            .then(function(response){
                let kmaInformsAlert=[];
                let kmaInformsWarning=[];
                if(response.data.listKmaInformAlert!=null||response.data.listKmaInformWarning!=null){
                   if(response.data.listKmaInformAlert==null) response.data.listKmaInformAlert=[];
                   if(response.data.listKmaInformWarning==null) response.data.listKmaInformWarning=[];
                    
                   for(let i=0;i<response.data.listKmaInformAlert.length;i++){
                        let item=response.data.listKmaInformAlert[i];
                        if((item.reportGungu!=null && item.reportGungu.length>0) || (item.reportSido!=null && item.reportSido.length>0)){
                            kmaInformsAlert.push(item);
                        }
                    }
                   
                    for(let i=0;i<response.data.listKmaInformWarning.length;i++){
                        let item=response.data.listKmaInformWarning[i];
                        if((item.reportGungu!=null && item.reportGungu.length>0) || (item.reportSido!=null && item.reportSido.length>0)){
                            kmaInformsWarning.push(item);
                        }
                    }

                    if(isDropOrg){
                        
                        vm.viewModel.rain.listOrgKmasAlert=kmaInformsAlert;
                        vm.viewModel.rain.listOrgKmasWarning=kmaInformsWarning;
                    //   vm.fnCreatOrgMap();
                    }else{
                        vm.viewModel.rain.listTargetKmasAlert=kmaInformsAlert;
                        vm.viewModel.rain.listTargetKmasWarning=kmaInformsWarning;
                    //  vm.fnCreatTargetMap();
                    }

                    vm.fnGetEventGroupId(isDropOrg);
             }
            })
            .catch(function(ex){
                console.log(ex);
            });
        }
        //호우 피해 조회
        ,fnGetListRainDamage:function(){
            let vm=this;
            let _money=vm.viewModel.selected.damageMoney.split("-");
            let _person=vm.viewModel.selected.damagePerson.split("-");
            let _rain=vm.viewModel.selected.rain.split("-");
            let _paramStDamge=_money[0]==''?null:parseInt(_money[0]);
            let _paramEndDamge=_money[0]==''?null:parseInt(_money[1]);;
            let _paramStDamgePerson=_person[0]==''?null:parseInt(_person[0]);
            let _paramEndDamgePerson=_person[0]==''?null:parseInt(_person[1]);
            let _paramStRain=_rain[0]==''?null:parseInt(_rain[0]);
            let _paramEndRain=_rain[0]==''?null:parseInt(_rain[1]);

            Axios.post("/api/statistics/rain/damage/list",{
                 paramDamageCode:vm.viewModel.selected.damageType
                ,paramStartDate:null
                ,paramEndDate:null
                ,paramStDamageMoney:_paramStDamge
                ,paramEndDamageMoney:_paramEndDamge
                ,paramStComDmeCount:_paramStDamgePerson
                ,paramEndComDmeCount:_paramEndDamgePerson
                ,paramStRain:_paramStRain
                ,paramEndRain:_paramEndRain
                ,paramSidoCode:vm.viewModel.selected.sido
            })
            .then(function(response){
                vm.viewModel.listDamages=response.data.listDamages;


            })
            .catch(function(ex){
                console.log(ex);
            });

        },
        //Drop 
        fnOnDropDamageInfo:function(paramTargetId,paramBegDate,paramEndDate){
            for(let i=0;i<this.viewModel.listDamages.length;i++){
                let item=this.viewModel.listDamages[i];
                if(item.beg_date==paramBegDate&&item.end_date==paramEndDate){
                    if(paramTargetId=="org-data"){
                        this.viewModel.rain.listOrgTimely=[];
                        this.viewModel.rain.orgInfo=item;
                        this.viewModel.rain.orgInfo.listYearDme=[];
                        this.fnListSigunguDamages(true);
                    }else{
                        this.viewModel.rain.listTargetTimely=[];
                        this.viewModel.rain.targetInfo=item;
                        this.viewModel.rain.targetInfo.listYearDme=[];
                        this.fnListSigunguDamages(false);
                    }
                    break;
                }
            }
            
            this.fnGetKmaReport(paramTargetId=="org-data",paramBegDate,paramEndDate);
        }
        ,fnListSigunguDamages:function(paramIsOrg){
            let vm=this;
            let paramObj=paramIsOrg?this.viewModel.rain.orgInfo:this.viewModel.rain.targetInfo;    
            
            Axios.post("/api/yeardme/list",{paramStartDate:paramObj.beg_date,paramEndDate:paramObj.end_date})
            .then(function(response){
                
                if(response.data.listYearDme!=null && response.data.listYearDme.length>0){
                    
                    for(let i=0;i< response.data.listYearDme.length;i++){
                        let item=response.data.listYearDme[i];
                        item.total_damage=parseFloat(item.com_total)+parseFloat(item.pri_total)+parseFloat(item.pub_total);
                    }
                    response.data.listYearDme.sort(function(a,b){return b.total_damage-a.total_damage;});
                }

                if(paramIsOrg){
                    vm.viewModel.rain.listOrgYearDme=response.data.listYearDme;
                    setTimeout(function(){ vm.fnListStatistics("orgChart",vm.viewModel.rain.orgInfo);},0);
                
                }else{
                    vm.viewModel.rain.listTargetYearDme=response.data.listYearDme;
                    setTimeout(function(){  vm.fnListStatistics("targetChart",vm.viewModel.rain.targetInfo);},0);
                
                }
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        fnListStatistics:function (paramTargetId,paramDropItem){
            let vm=this;
            let begDate=paramDropItem.beg_date;
            let endDate=paramDropItem.end_date;
            Axios.post("/api/analysis/rain/damage/build/list",{paramStartDate:begDate,paramEndDate:endDate})
            .then(function(response){
                
              
                let damagesGroup=[
                    { group: 0, id: '재산피해', value: response.data.listStatistics[0].sum, rate: 0,range:0 }
                    ,{ group: 1, id: '공공시설', value: response.data.listStatistics[0].damagePublic, rate: 0,range:0 }
                    ,{ group: 1, id: '사유시설', value: response.data.listStatistics[0].damagePrivate, rate: 0,range:0 }
                 
                 
                ];

                
                let defaultLink=[
                    {  target: '재산피해', value: 0, source: "공공시설", rate:0,range:0}
                    ,{  target: '재산피해', value: 0, source: "사유시설", rate:0,range:0}
                    

                ];

                for(let i=0;i<response.data.listStatistics.length;i++){
                    let item=response.data.listStatistics[i];
                    if(item.parentYn=="Y"){
                        let _value=item.damageTotal;
                        if(item.damageName!="공공시설"&&item.damageName!="사유시설"){
                        damagesGroup.push({ group: 1, id:item.damageName, value:_value, rate: 0,range:0 });
                        defaultLink.push({  target: '재산피해', value: _value, source: item.damageName, rate:0,range:0});
                        }
                     
                    }
                }

                let listTemp=[];
                for(let i=0;i<response.data.listStatistics.length;i++){
                    let item=response.data.listStatistics[i];
                  
                        let damage=parseInt(item.damageTotal/100000);
                        if(damage>=0&& damage<=10) item.range=1;
                        else if(damage>10&& damage<=20) item.range=2;
                        else if(damage>20&& damage<=100) item.range=3;
                        else if(damage>100) item.range=4;

                    if(item.parentYn=="N"){
                        listTemp.push(item);
                    }
                    
                }


                for(let i=0;i<damagesGroup.length;i++){
                    let item=damagesGroup[i];
                    let damage=parseInt(item.value/100000);
                    if(damage>=0&& damage<=10) item.range=1;
                    else if(damage>10&& damage<=20) item.range=2;
                    else if(damage>20&& damage<=100) item.range=3;
                    else if(damage>100) item.range=4;

                    
                }
                vm.fnCreateBubbleChart(damagesGroup,defaultLink,listTemp,paramTargetId);
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        //#지도
        fnCreatOrgMap:function(){
            let vm=this;

            
            for(let i=0;i<vm.viewModel.rain.listOrgTimely.length;i++){
                    let item=vm.viewModel.rain.listOrgTimely[i];
                    let _map = new ol.Map({
                        layers : [ vm.raster],
                        target : 'org-map'+i,
                        view : new ol.View({
                            center : ol.proj.fromLonLat([ 128, 35.5 ]),
                            zoom : 6
                        })
                    });
                    let tempArr=[];
                    if(item.kmaAlert!=undefined &&item.kmaAlert!=null){
                        if(item.kmaAlert.reportGungu!=null&&item.kmaAlert.reportGungu.length>0){
                           tempArr=item.kmaAlert.reportGungu;
                        }
                        if(item.kmaAlert.reportSido!=null&&item.kmaAlert.reportSido.length>0){
                           for(let z=0;z<item.kmaAlert.reportSido.length;z++){
                            tempArr.push(item.kmaAlert.reportSido[z]);
                           }
                        }
                        
                        vm.fnSetMapAlert(_map,tempArr.join("\\,"));
                    }

                    if(item.kmaWarning!=undefined &&item.kmaWarning!=null){
                        if(item.kmaWarning.reportGungu!=null&&item.kmaWarning.reportGungu.length>0){
                           tempArr=item.kmaWarning.reportGungu;
                        }
                        if(item.kmaWarning.reportSido!=null&&item.kmaWarning.reportSido.length>0){
                           for(let z=0;z<item.kmaWarning.reportSido.length;z++){
                            tempArr.push(item.kmaWarning.reportSido[z]);
                           }
                        }
                        
                        vm.fnSetMapWarning(_map,tempArr.join("\\,"));
                    }

            }
                
        }
        ,fnCreatTargetMap:function(){
            let vm=this;

            
            for(let i=0;i<vm.viewModel.rain.listTargetTimely.length;i++){
                    let item=vm.viewModel.rain.listTargetTimely[i];
                    let _map = new ol.Map({
                        layers : [ vm.raster],
                        target : 'target-map'+i,
                        view : new ol.View({
                            center : ol.proj.fromLonLat([ 128, 35.5 ]),
                            zoom : 6
                        })
                    });
                    let tempArr=[];
                    if(item.kmaAlert!=undefined &&item.kmaAlert!=null){
                        if(item.kmaAlert.reportGungu!=null&&item.kmaAlert.reportGungu.length>0){
                           tempArr=item.kmaAlert.reportGungu;
                        }
                        if(item.kmaAlert.reportSido!=null&&item.kmaAlert.reportSido.length>0){
                           for(let z=0;z<item.kmaAlert.reportSido.length;z++){
                            tempArr.push(item.kmaAlert.reportSido[z]);
                           }
                        }
                        
                        vm.fnSetMapAlert(_map,tempArr.join("\\,"));
                    }

                    if(item.kmaWarning!=undefined &&item.kmaWarning!=null){
                        if(item.kmaWarning.reportGungu!=null&&item.kmaWarning.reportGungu.length>0){
                           tempArr=item.kmaWarning.reportGungu;
                        }
                        if(item.kmaWarning.reportSido!=null&&item.kmaWarning.reportSido.length>0){
                           for(let z=0;z<item.kmaWarning.reportSido.length;z++){
                            tempArr.push(item.kmaWarning.reportSido[z]);
                           }
                        }
                        
                        vm.fnSetMapWarning(_map,tempArr.join("\\,"));
                    }

            }
        }
        ,fnSetMapAlert:function(paramMap,paramPosition){
            
            
               
                let wmsSource = new ol.source.TileWMS({
                    url: 'http://61.105.196.70:7000/geoserver/common/wms',
                    params: {
                        'LAYERS': 'view_kma_gungu_lv1',
                        'viewparams':'sigungu_code:'+paramPosition,
                        ratio:1
                    },
                    
                    serverType: 'geoserver'
                
                });
                
                let wmsLayer = new ol.layer.Tile({
                    source: wmsSource
                });
                
                paramMap.addLayer(wmsLayer);
            
            
        },
        fnSetMapWarning:function(paramMap,paramPosition){
            
            
               
                let wmsSource = new ol.source.TileWMS({
                    url: 'http://61.105.196.70:7000/geoserver/common/wms',
                    params: {
                        'LAYERS': 'view_kma_gungu_lv2',
                        'viewparams':'sigungu_code:'+paramPosition,
                        ratio:1
                    },
                    
                    serverType: 'geoserver'
                
                });
                
                let wmsLayer = new ol.layer.Tile({
                    source: wmsSource
                });
                
                paramMap.addLayer(wmsLayer);
            
            
        },
        fnGetEventGroupId:function(paramIsOrg){
            let param="";
            
            if(this.viewModel.selected.damageType=="NHR"){
                let paramObj=paramIsOrg?this.viewModel.rain.orgInfo:this.viewModel.rain.targetInfo;
                let paramObjKmasAlert=paramIsOrg?this.viewModel.rain.listOrgKmasAlert:this.viewModel.rain.listTargetKmasAlert;
                let paramObjKmasWarning=paramIsOrg?this.viewModel.rain.listOrgKmasWarning:this.viewModel.rain.listTargetKmasWarning;
                let paramObjTimely=paramIsOrg?this.viewModel.rain.listOrgTimely:this.viewModel.rain.listTargetTimely;
                let vm=this;
                Axios.post("/api/advis/dis/event/groupid/info",{paramCtgId:vm.viewModel.selected.damageType
                    ,paramStartDate:paramObj.beg_date
                    ,paramEndDate:paramObj.end_date
                    ,paramTitle:null}
                    )
                    .then(function(response){
                        if(response.data.eventList!=null &&response.data.eventList.length>0){
                            for(let i=0;i<response.data.eventList.length;i++){
                                let item=response.data.eventList[i];
                                let kmaAlert=[];
                                let kmaWarning=[];
                                
                                for(let k=0;k<paramObjKmasAlert.length;k++){
                                    let sub=paramObjKmasAlert[k];
                                    if(parseFloat(item.evt_date)>=parseFloat(sub.dt_tm_fc)){
                                        kmaAlert.push(sub);
                                    }
                                }
                                
                                for(let k=0;k<paramObjKmasWarning.length;k++){
                                    let sub=paramObjKmasWarning[k];
                                    if(parseFloat(item.evt_date)>=parseFloat(sub.dt_tm_fc)){
                                        kmaWarning.push(sub);
                                    }
                                }


                                let sortKmaAlert=null;
                                let sortKmaWarning=null;

                                if(kmaAlert.length>0){
                                    kmaAlert.sort(function(a,b){return b.dt_tm_fc-a.dt_tm_fc;});
                                    sortKmaAlert=kmaAlert[0];
                                }
                                
                                if(kmaWarning.length>0){
                                    kmaWarning.sort(function(a,b){return b.dt_tm_fc-a.dt_tm_fc;});
                                    sortKmaWarning=kmaWarning[0];
                                }
                                
                                paramObjTimely.push({
                                    eventTime:item.evt_date,event:item,kmaAlert:sortKmaAlert,kmaWarning:sortKmaWarning
                                    ,kmaRs:{rain_at:false,rain_alert:false,typhoon_at:false,typhoon_alert:false,wind_at:false,wind_alert:false}
                                    ,control:{ship:false,park:false,road:false,flight:false,sea:false,etc:false}
                                    ,actions:{}
                                });

                            }
                         
                            for(let i=0;i<paramObjTimely.length;i++){
                                let item=paramObjTimely[i];
                                item.eventItemList=[];

                                for(let k=0;k<response.data.eventList.length;k++){
                                    let evt=response.data.eventList[k];
                                    if(item.eventTime==evt.evt_date){
                                        item.kmaRs.rain_at=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NHR10101001010040","주의보",item.kmaRs.rain_at,item.eventItemList);
                                        item.kmaRs.rain_alert=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NHR10101001010040","경보",item.kmaRs.rain_alert,item.eventItemList);
                                        item.kmaRs.typhoon_at=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NHR10101001010020","주의보",item.kmaRs.typhoon_at,item.eventItemList);
                                        item.kmaRs.typhoon_alert=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NHR10101001010020","경보",item.kmaRs.typhoon_alert,item.eventItemList);
                                        item.kmaRs.wind_at=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NHR10101001010030","주의보",item.kmaRs.wind_at,item.eventItemList);
                                        item.kmaRs.wind_alert=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NHR10101001010030","경보",item.kmaRs.wind_alert,item.eventItemList);

                                        item.control.ship=vm.fnIsContainControlTxt(evt.eventItemList,"NHR30302003020010","여객선",item.control.ship,item.eventItemList);
                                        item.control.park=vm.fnIsContainControlTxt(evt.eventItemList,"NHR30302003020020","국립공원",item.control.park,item.eventItemList);
                                        item.control.road=vm.fnIsContainControlTxt(evt.eventItemList,"NHR30302003020030","도로",item.control.road,item.eventItemList);
                                        item.control.flight=vm.fnIsContainControlTxt(evt.eventItemList,"NHR30302003020040","항공",item.control.flight,item.eventItemList);
                                        item.control.etc=vm.fnIsContainControlTxt(evt.eventItemList,"NHR30302003020050","기타",item.control.etc,item.eventItemList);

                                        item.damageTxt=vm.fnGetDamageTxt(evt.eventItemList,item.eventTime);
                                        item.actions=vm.fnGetActionKeyword(response.data.resultList,item.eventTime);// 대응현황
                                    }
                                }
                            }

                            paramObjTimely.sort(function(a,b){ return a.eventTime-b.eventTime;});
                            if(paramIsOrg){
                                setTimeout(function(){vm.fnCreatOrgMap(); },0);
                              
                            }else{
                                setTimeout(function(){vm.fnCreatTargetMap(); },0);
                              
                            }
                        }

                        
                    })
                    .catch(function(ex){
                        console.log(ex);
                    });

            }
        }
        ,fnGetDamageTxt:function(paramListAction,paramActionTime){
            let rs="";
            for(let i=0;i<paramListAction.length;i++){
                let item=paramListAction[i];
                    if(item.ctg_id.indexOf("3030100")>-1){
                        rs+=item.contents+"\r";
                    }
                
            }
            if(rs=="")rs="등록된 피해현황 내용이 없습니다.";
            return rs.replace(/(\r\n|\n|\r)/g,"<br />");
        }
        ,fnGetActionKeyword:function(paramListAction,paramActionTime){
            let vm=this;
            let rs={
                 isAction:false
                 ,content:''
                 ,size:0
                 ,lvl1:[]
                ,lvl2:[]
                ,lvl3:[]
                ,lvl4:[]
                ,lvl5:[]
                ,lvl6:[]
            }
            for(let i=0;i<paramListAction.length;i++){
                let item=paramListAction[i];
                item.month=item.month.length==1?"0"+item.month:item.month;
                item.day=item.day.length==1?"0"+item.day:item.day;
                item.hour=item.hour.length==1?"0"+item.hour:item.hour;
                if((item.year+item.month+item.day+item.hour)==paramActionTime.substring(0,10)){
                    for(let k=0;k<vm.viewModel.listActionManual.length;k++){
                       let action= vm.viewModel.listActionManual[k];
                        
                       if(item.dis_act_lv1!=null&&item.dis_act_lv1.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl1.indexOf(action.manual_title)==-1) {
                                rs.lvl1.push({title:action.manual_title,content:item.dis_act_lv1,manual_contents:action.manual_contents});
                                rs.isAction=true;
                                
                            }
                        }

                        if(item.dis_act_lv2!=null&&item.dis_act_lv2.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/\s*/gi, ""))>-1 ){
                            if(rs.lvl2.indexOf(action.manual_title)==-1) {
                                rs.lvl2.push({title:action.manual_title,content:item.dis_act_lv2,manual_contents:action.manual_contents});
                                
                                rs.isAction=true;
                            }
                        }
                        if(item.dis_act_lv3!=null&&item.dis_act_lv3.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl3.indexOf(action.manual_title)==-1) {
                                rs.lvl3.push({title:action.manual_title,content:item.dis_act_lv3,manual_contents:action.manual_contents});
                                rs.isAction=true;
                            }
                        }
                        if(item.dis_act_lv4!=null&&item.dis_act_lv4.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl4.indexOf(action.manual_title)==-1) {
                                rs.lvl4.push({title:action.manual_title,content:item.dis_act_lv4,manual_contents:action.manual_contents});
                                rs.isAction=true;}
                        }
                        if(item.dis_act_lv5!=null&&item.dis_act_lv5.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl5.indexOf(action.manual_title)==-1) {
                                rs.lvl5.push({title:action.manual_title,content:item.dis_act_lv5,manual_contents:action.manual_contents});
                                rs.isAction=true;}
                        }
                        if(item.dis_act_lv6!=null&&item.dis_act_lv6.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl6.indexOf(action.manual_title)==-1) {
                                rs.lvl6.push({title:action.manual_title,content:item.dis_act_lv6,manual_contents:action.manual_contents});
                                
                                rs.isAction=true;
                            }
                        }
                    }
                }
            }
            if(rs.isAction){
               
                rs.size= rs.lvl1.length;
                if(rs.size<=rs.lvl2.length)  rs.size=rs.lvl2.length;
                if(rs.size<=rs.lvl3.length)  rs.size=rs.lvl3.length;
                if(rs.size<=rs.lvl4.length)  rs.size=rs.lvl4.length;
                if(rs.size<=rs.lvl5.length)  rs.size=rs.lvl5.length;
                if(rs.size<=rs.lvl6.length)  rs.size=rs.lvl6.length;
               
            }
            return rs;
        },
        fnIsContainEventCodeTxt:function(paramListCode,paramCodeTxt,paramSearchTxt,paramIsBool,paramListEvent){
            if(paramIsBool=='undefined') paramIsBool = false;

            for(let i=0;i<paramListCode.length;i++){
                let item=paramListCode[i];
           
                if(item.ctg_id==paramCodeTxt && item.contents!=null){
                    item.contents=item.contents.replace(" ","");
                    if(item.contents.indexOf(paramSearchTxt)>-1 && item.contents.indexOf(paramSearchTxt+"해제")==-1){
                        paramIsBool = true;
                        
                        let index = -1;
                        for(let j=0; j<paramListEvent.length; j++){
                            if(paramListEvent[j].contents.replace(" ","") == item.contents.replace(" ","")){
                                index = j;
                            }
                        }
                        if(index==-1) paramListEvent.push(item);

                        break;
                    }
                }
            }
            return paramIsBool;
        },
        fnIsContainControlTxt:function(paramListCode,paramCodeTxt,paramSearchTxt,paramIsBool,paramListEvent){
            //let rs=false;
            if(paramIsBool=='undefined') paramIsBool = false;

            for(let i=0;i<paramListCode.length;i++){
                let item=paramListCode[i];
                if(item.ctg_id==paramCodeTxt && item.contents!=null){
                    paramIsBool = true;
                    
                    let index = -1;
                    for(let j=0; j<paramListEvent.length; j++){
                        if(paramListEvent[j].contents.replace(" ","") == item.contents.replace(" ","")){
                            index = j;
                        }
                    }
                    if(index==-1) paramListEvent.push(item);
                }
               
            }
            return paramIsBool;
        },
        fnCreateBubbleChart: function(defaultGrp,defaultLink,paramList,paramTargetId) {
            this.node=null;
            this.link=null;
            let vm=this;
            $("#"+paramTargetId).html("<g></g>");

            // {
            //     "nodes": [
            //       {"id": "Myriel", "group": 1},
            //       {"id": "Napoleon", "group": 1},
            //       {"id": "Mlle.Baptistine", "group": 1},
            //       ...
            //     ],
            //     "links": [
            //       {"source": "Napoleon", "target": "Myriel", "value": 1},
            //       {"source": "Mlle.Baptistine", "target": "Myriel", "value": 8},
            //       ...
            //     ]
            //   }
            var colores_g = ["rgba(248,187,208,1)", "rgba(178,223,219,1)", "rgba(187,222,251,1)", "rgba(178,235,242,1)", "rgba(248,187,208,1)", "rgba(197,202,233,1)"];
            let colors=["#3498DB","#F1C40F","#F39C12","#D35400","#C0392B"];
            let width = 663;
            let height = 300;
            let sum = 0;
            let bubbleNode = {
                nodes: defaultGrp,
                links:defaultLink

            };
            let index = 0;
            for (let i = 0; i < paramList.length; i++) {
                let item = paramList[i];
                if (item.damageName != '기타') {
                  
                        bubbleNode.nodes.push({  group: 2, value: item.damageTotal, id: item.damageName ,range:item.range});
                        sum += item.damageTotal;
                      //  index++;
                  
                }
            }


            index = 0;
            this.fnFindDamageBuildGroup(paramList);
            for (let i = 0; i < paramList.length; i++) {
                let item = paramList[i];
                
                if (item.damageName != '기타') {
                      
                       if(item.target=='') item.target='재산피해';

                        bubbleNode.links.push({  target: item.target, value: item.damageTotal, source: item.damageName, rate: (item.damageTotal / sum) * 100 + 125 });
                    //    index++;
                    
                }
            }
            
            for (let i = 1; i < bubbleNode.nodes.length; i++) {
                
                let item = bubbleNode.nodes[i];
                let rate = (item.value / sum) * 100;
                
                item.rate = rate + 25;

            }


            //let c10 = d3.scaleOrdinal(d3.schemeCategory20);
            let c10 = d3.scaleOrdinal(d3.schemePaired);

            let margin = { top: 20, right: 0, bottom: 0, left: 0 };


            let forceLayout = d3.forceSimulation()
            .force("link", d3.forceLink().id(function (d) {
                return d.id;
              })
             )
             .force("charge", d3.forceManyBody().strength(-100))
              .force("center", d3.forceCenter(width / 2, height / 2));
              
    
            
            this.link = d3.select("#"+paramTargetId+" g")
                .attr("class", "links")
                .selectAll("line")
                .data(bubbleNode.links)
                .enter().append("line")
                .attr("stroke-width", function(d) { return 1; })

            this.node = d3.select("#"+paramTargetId)
                .append("g")
                .attr("class", "nodes")
                .selectAll("g")
                .data(bubbleNode.nodes)
                .enter()
                .append("g");
              

            this.node.append("circle")
                .attr("r", function(d) {  return d.group==0?15:10; })
                .attr("fill", function(d) { 
                    return colors[d.range]; 
                });

            this.node.append("text")
                .text(function(d) {

                    return d.group<2?d.id:'';
                })
                .attr("class", "title")
                .attr('x',10)
                .attr('y', 3);
            this.node.on("click",function(d){
                if(paramTargetId.indexOf("org")>-1){
                    vm.viewModel.selectedOrgNode.name=d.id;
                    vm.viewModel.selectedOrgNode.value=d.value;
                }else{
                    vm.viewModel.selectedTargetNode.name=d.id;
                    vm.viewModel.selectedTargetNode.value=d.value;
                }
                vm.fnShowToolTip(paramTargetId+"-tooltip",d3.event);
            });
            
            forceLayout.nodes(bubbleNode.nodes)
                .on("tick", this.ticked);
            forceLayout.force("link").links(bubbleNode.links);

        },
        ticked: function() {
            this.link.attr("x1", function(d) { 
                return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });

            this.node.attr("transform", function(d) {
                return "translate(" + d.x + "," + d.y + ")";
            });
        }
        

        ,fnOnClickManualTitle:function(paramObj,paramDay,paramName){
            this.modal.manual_contents=[];
            this.modal.contents=[];
            this.modal.evtDay=paramDay;
            this.modal.title="대응현황";
            this.modal.damageName=paramName;
            if(paramObj.content!=''){
                paramObj.content = paramObj.content.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
            }
            this.modal.contents.push({contentTitle:'',content:paramObj.content});
            $('#contentViewModal').modal('show');
        }
        ,fnFindDamageBuildGroup:function(paramListDamages){
       
            let damageGroup=[
                 {id:'재산피해',item:['농경지','농작물','건물','선박']}
                ,{id:'공공시설',item:['도로','하천','소하천','상하수도','항만','어항','학교','철도','수리','사방임도','통신','군사','소규모','기타']}
                ,{id:'사유시설',item:['축대','가축','축사','양식장','어망,어구','비닐하우스']}
            ];
            for(let i=0;i<paramListDamages.length;i++){
                let item=paramListDamages[i];
                
                
                item.target='';
                for(let k=0;k<damageGroup.length;k++){
                    for(let z=0;z<damageGroup[k].item.length;z++){
                        if(damageGroup[k].item[z]==item.damageName){
                            item.target=damageGroup[k].id;
                            break; 
                        }
                    }
                }

            }
        },
        fnShowToolTip:function(tooltipId,event){

            var ttid = "#"+tooltipId;
            var xOffset = 20;
            var yOffset = 10;

            var toolTipW = $(ttid).width();
            var toolTipeH = $(ttid).height();
            var windowY = $(window).scrollTop();
            var windowX = $(window).scrollLeft();
            var curX = event.pageX;
            var curY = event.pageY;
            var ttleft = ((curX) < $(window).width() / 2) ? curX - toolTipW - xOffset*2 : curX + xOffset;
            if (ttleft < windowX + xOffset){
            ttleft = windowX + xOffset;
            } 
            var tttop = ((curY - windowY + yOffset*2 + toolTipeH) > $(window).height()) ? curY - toolTipeH - yOffset*2 : curY + yOffset;
            if (tttop < windowY + yOffset){
            tttop = curY + yOffset;
            } 
            $(ttid).css('top', tttop + 'px').css('left', ttleft + 'px');
            $(ttid).show();
        }
        ,fnHideTooltip:function(tooltipId){
            var ttid = "#"+tooltipId;
            $(ttid).hide();

        }
        ,fnOnClickViewContent:function(paramIsOrg,paramTitle,paramBegDate){
            this.fnFindCategoryId(paramIsOrg,paramTitle,paramBegDate);
        }
        ,fnFindCategoryId:function(paramIsOrg,paramTitle,paramBegDate){
            let vm=this;
            let paramObj=paramIsOrg?this.viewModel.rain.orgInfo:this.viewModel.rain.targetInfo;
            let paramObjTimely=paramIsOrg?this.viewModel.rain.listOrgTimely:this.viewModel.rain.listTargetTimely;

            Axios.post("/advis/code/search",{paramCtgId:vm.viewModel.selected.damageType,paramTitle:paramTitle})
            .then(function(response){
                vm.modal.manual_contents=[];
                if(response.data.list!=null && response.data.list.length>0){
                    for(let i=0;i<paramObjTimely.length;i++){
                        let item=paramObjTimely[i];
                        if(item.event.evt_date==paramBegDate){
                            let listContents=vm.fnGetContent(item.event.evt_date,item.event.eventItemList,response.data.list[0].ctg_id);
                            
                            vm.modal.title=paramTitle;
                            vm.modal.evtDay=item.event.evt_date;
                            vm.modal.contents=listContents;
                          //  vm.modal.damageName=paramName;
                            //vm.modal.contents=
                            $('#contentViewModal').modal('show');
                            break;
                        }
                    }
                }

            })
            .catch(function(ex){
                
                console.log(ex);
            })

        }
        ,fnGetContent:function(paramDay,paramEventItemList,paramCtgId){
            let rs=[];
            for(let i=0;i<paramEventItemList.length;i++){
                let item=paramEventItemList[i];
                if(item.ctg_id.startsWith(paramCtgId)){
                    rs.push({content:item.contents,contentTitle:item.contentsTitle,evtDay:paramDay})
                }

            }
            return rs;
        }
        ,fnOnClickListManual:function(paramActions,paramDamageDay){
            
            if(paramActions.isAction){
            this.modal.damageName="";
            this.modal.evtDay=paramDamageDay;
            this.modal.title="대응현황";
            this.modal.contents=[];
            this.modal.manual_contents=[];
            if(paramActions.lvl1!=null && paramActions.lvl1.length>0){
                this.modal.manual_contents.push({lvl:"lvl1",list:paramActions.lvl1});
            }
            if(paramActions.lvl2!=null && paramActions.lvl2.length>0){
                this.modal.manual_contents.push({lvl:"lvl2",list:paramActions.lvl2});
            }
            if(paramActions.lvl3!=null && paramActions.lvl3.length>0){
                this.modal.manual_contents.push({lvl:"lvl3",list:paramActions.lvl3});
            }
            if(paramActions.lvl4!=null && paramActions.lvl4.length>0){
                this.modal.manual_contents.push({lvl:"lvl4",list:paramActions.lvl4});
            }
            if(paramActions.lvl5!=null && paramActions.lvl5.length>0){
                this.modal.manual_contents.push({lvl:"lvl5",list:paramActions.lvl5});
            }
            if(paramActions.lvl6!=null && paramActions.lvl6.length>0){
                this.modal.manual_contents.push({lvl:"lvl6",list:paramActions.lvl6});
            }
            $('#contentViewModal').modal('show');
            }
        }
    }
});