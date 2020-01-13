import Vue from 'vue'
import Axios from 'axios'

var vue = new Vue({
    el: '#scope-category',
    data: function() {
        return {
            viewModel: {
                listDepth1: [],
                listDepth2: [],
                listDepth3: [],
                listDepth4: [],
                
                selectedDepth1: '',
                selectedDepth2: [],
                selectedDepth3: [],
                selectedDepth4: [],

                selectedDepth1_nm: '',
                selectedDepth2_nm: [],
                selectedDepth3_nm: [],
                selectedDepth4_nm: [],

                categoryList: [],
                eventList: [],
                eventItemList: [],

                categoryDepth1: [],
                categoryDepth2: [],
                categoryDepth3: [],
                categoryDepth4: [],

                ListAllcategoryDepth2: [],
                ListAllcategoryDepth3: [],
                ListAllcategoryDepth4: [],
            },

        };
    },
    mounted: function() {
        pageLoad(this);
    },
    methods: {
        fnGetInit: function(paramCtgId, paramDepth){
            $("#loading-layer").show();
            let _depth = parseInt(paramDepth);

            for(let i=1; i<_depth+1; i++){
                this.fnDisCodeList(paramCtgId, i);

                if(i==_depth){
                    this.fnGetCategoryDepthList(paramCtgId.substring(0,3));
                    this.fnGetEventList(paramCtgId.substring(0,3));
                }
            }
            $('#loading-layer').hide();
        }

        ,fnOnClickSearch: function(){
            
            let vm = this.viewModel;
            let cnt = 0;
            
            if(vm.selectedDepth2.length==0||vm.selectedDepth3.length==0){
                alert('소분류까지 필수선택 입니다.');
                return;
            }else{
                for(let i=0; i<vm.selectedDepth2.length; i++){
                    cnt = 0;
                    for(let j=0; j<vm.selectedDepth3.length; j++){
                        
                        if(vm.selectedDepth3[j].substring(0,5) == vm.selectedDepth2[i]){
                            cnt++;    
                        }
                    }
                    if(cnt == 0) break;
                }
            }
            if(cnt == 0){
                alert('소분류까지 필수선택 입니다.');
                return;
            }

            $("#loading-layer").show();
            
            this.fnGetCategoryDepthList(vm.selectedDepth1);
            this.fnGetEventList(vm.selectedDepth1);

            $('#loading-layer').hide();
        }

        ,fnDisCodeList:function(paramCtgId, paramDepth){
            let vm = this.viewModel;
            
            let _ctg_id = null;
            if(paramDepth==1){
                _ctg_id = null;
                vm.selectedDepth1 = paramCtgId.substring(0,3);
            }else if(paramDepth==2){
                _ctg_id = paramCtgId.substring(0,3);
                if(paramCtgId.length>3) vm.selectedDepth2.push(paramCtgId.substring(0,5));
            }else if(paramDepth==3){
                _ctg_id = paramCtgId.substring(0,5);
                if(paramCtgId.length>5) vm.selectedDepth3.push(paramCtgId.substring(0,10));
            }else if(paramDepth==4){
                _ctg_id = paramCtgId.substring(0,10);
                if(paramCtgId.length>10) vm.selectedDepth4.push(paramCtgId.substring(0,17));
            }

            Axios.post("/api/std/manageDisCodelist", {
                paramDepth:paramDepth
                ,paramCtgId:_ctg_id
            }).
            then(function(response) {
                let _list = response.data.list;
                if(paramDepth == 1){
                    vm.listDepth1 = _list;
                    if(vm.selectedDepth1 != ''){
                        for(let i=0; i<vm.listDepth1.length; i++){
                            if(vm.listDepth1[i].ctg_id == vm.selectedDepth1) vm.selectedDepth1_nm = vm.listDepth1[i].title;
                        }
                    }
                }else if(paramDepth == 2){
                    vm.listDepth2 = _list;
                    if(vm.selectedDepth2.length>0){
                        for(let i=0; i<vm.listDepth2.length; i++){
                            let temp = {ctg_id:'', title:''};
                            if(vm.listDepth2[i].ctg_id == vm.selectedDepth2) {
                                temp.ctg_id = vm.listDepth2[i].ctg_id;
                                temp.title = vm.listDepth2[i].title;
                                vm.selectedDepth2_nm.push(temp);
                            }
                        }
                    }
                }else if(paramDepth == 3){
                    vm.listDepth3 = _list;
                    if(vm.selectedDepth3.length>0){
                        for(let i=0; i<vm.listDepth3.length; i++){
                            let temp = {ctg_id:'', title:''};
                            if(vm.listDepth3[i].ctg_id == vm.selectedDepth3) {
                                temp.ctg_id = vm.listDepth3[i].ctg_id;
                                temp.title = vm.listDepth3[i].title;
                                vm.selectedDepth3_nm.push(temp);
                            }
                        }
                    }
                }else if(paramDepth == 4){
                    vm.listDepth4 = _list;
                    if(vm.selectedDepth4.length>0){
                        for(let i=0; i<vm.listDepth4.length; i++){
                            let temp = {ctg_id:'', title:''};
                            if(vm.listDepth4[i].ctg_id == vm.selectedDepth4){
                                temp.ctg_id = vm.listDepth4[i].ctg_id;
                                temp.title = vm.listDepth4[i].title;
                                vm.selectedDepth4_nm.push(temp);
                            } 
                        }
                    }
                }
                
            }).
            catch(function(ex) {
                console.log(ex);
                $('#loading-layer').hide();
            });
        }

        ,fnOnClickDisCodeList: function(paramDepth, paramCtgId){
            let vm = this.viewModel;

            Axios.post("/api/std/manageDisCodelist", {
                paramDepth:paramDepth
                ,paramCtgId:paramCtgId
            }).
            then(function(response) {
                let _list = response.data.list;

                if(paramDepth == 2){ //대분류 change
                    vm.listDepth2 = _list;
                    vm.listDepth3 = [];
                    vm.listDepth4 = [];

                }else if(paramDepth == 3){ //중분류 change
                    vm.listDepth3 = _list;
                    vm.listDepth4 = [];

                }else if(paramDepth == 4){ //소분류 change
                    vm.listDepth4 = _list;

                }
            }).
            catch(function(ex) {
                console.log(ex);
                $('#loading-layer').hide();
            });
        }

        ,fnOnChangeCtgId: function(paramDepth, paramCtgId, paramTitle){
            let vm = this.viewModel;

            Axios.post("/api/std/manageDisCodelist", {
                paramDepth:paramDepth
                ,paramCtgId:paramCtgId
            }).
            then(function(response) {
                let _list = response.data.list;
                if(paramDepth == 2){ //대분류 change
                    vm.listDepth2 = _list;
                    vm.listDepth3 = [];
                    vm.listDepth4 = [];
                    
                    vm.selectedDepth2 = [];
                    vm.selectedDepth3 = [];
                    vm.selectedDepth4 = [];

                    vm.selectedDepth1_nm = paramTitle;
                    vm.selectedDepth2_nm = [];
                    vm.selectedDepth3_nm = [];
                    vm.selectedDepth4_nm = [];

                }else if(paramDepth == 3){ //중분류 change
                    vm.listDepth3 = _list;
                    vm.listDepth4 = [];

                    if(document.getElementById(paramCtgId).checked == true){
                        let temp = {ctg_id:'', title:''};
                        temp.ctg_id = paramCtgId;
                        temp.title = paramTitle;
                        vm.selectedDepth2_nm.push(temp);
                        
                    }else{

                        let tempList = [];
                        
                        for(let i=0; i<vm.selectedDepth3.length; i++){
                            if(vm.selectedDepth3[i].indexOf(paramCtgId) < 0){
                                tempList.push(vm.selectedDepth3[i]);
                            }
                        }
                        vm.selectedDepth3 = tempList;

                        tempList = [];
                        for(let i=0; i<vm.selectedDepth4.length; i++){
                            if(vm.selectedDepth4[i].indexOf(paramCtgId) < 0){
                                tempList.push(vm.selectedDepth4[i]);
                            }
                        }
                        vm.selectedDepth4 = tempList;

                        
                        let tempNameList = [];
                        for(let i=0; i<vm.selectedDepth2_nm.length; i++){
                            if(vm.selectedDepth2_nm[i].ctg_id.indexOf(paramCtgId) < 0){
                                let temp = {ctg_id:'', title:''};
                                temp.ctg_id = vm.selectedDepth2_nm[i].ctg_id;
                                temp.title = vm.selectedDepth2_nm[i].title;
                                tempNameList.push(temp);
                            }
                        }
                        vm.selectedDepth2_nm = tempNameList;

                        tempNameList = [];
                        for(let i=0; i<vm.selectedDepth3_nm.length; i++){
                            if(vm.selectedDepth3_nm[i].ctg_id.indexOf(paramCtgId) < 0){
                                let temp = {ctg_id:'', title:''};
                                temp.ctg_id = vm.selectedDepth3_nm[i].ctg_id;
                                temp.title = vm.selectedDepth3_nm[i].title;
                                tempNameList.push(temp);
                            }
                        }
                        vm.selectedDepth3_nm = tempNameList;

                        tempNameList = [];
                        for(let i=0; i<vm.selectedDepth4_nm.length; i++){
                            if(vm.selectedDepth4_nm[i].ctg_id.indexOf(paramCtgId) < 0){
                                let temp = {ctg_id:'', title:''};
                                temp.ctg_id = vm.selectedDepth4_nm[i].ctg_id;
                                temp.title = vm.selectedDepth4_nm[i].title;
                                tempNameList.push(temp);
                            }
                        }
                        vm.selectedDepth4_nm = tempNameList;
                    }

                }else if(paramDepth == 4){ //소분류 change
                    vm.listDepth4 = _list;

                    if(document.getElementById(paramCtgId).checked == true){
                        let temp = {ctg_id:'', title:''};
                        temp.ctg_id = paramCtgId;
                        temp.title = paramTitle;
                        vm.selectedDepth3_nm.push(temp);
                    }else{

                        let tempList = [];
                        for(let i=0; i<vm.selectedDepth4.length; i++){
                            if(vm.selectedDepth4[i].indexOf(paramCtgId) < 0){
                                tempList.push(vm.selectedDepth4[i]);
                            }
                        }
                        vm.selectedDepth4 = tempList;

                        let tempNameList = [];
                        for(let i=0; i<vm.selectedDepth3_nm.length; i++){
                            if(vm.selectedDepth3_nm[i].ctg_id.indexOf(paramCtgId) < 0){
                                let temp = {ctg_id:'', title:''};
                                temp.ctg_id = vm.selectedDepth3_nm[i].ctg_id;
                                temp.title = vm.selectedDepth3_nm[i].title;
                                tempNameList.push(temp);
                            }
                        }
                        vm.selectedDepth3_nm = tempNameList;

                        tempNameList = [];
                        for(let i=0; i<vm.selectedDepth4_nm.length; i++){
                            if(vm.selectedDepth4_nm[i].ctg_id.indexOf(paramCtgId) < 0){
                                let temp = {ctg_id:'', title:''};
                                temp.ctg_id = vm.selectedDepth4_nm[i].ctg_id;
                                temp.title = vm.selectedDepth4_nm[i].title;
                                tempNameList.push(temp);
                            }
                        }
                        vm.selectedDepth4_nm = tempNameList;
                    }
                        
                }else{
                    if(paramDepth == 5){
                        if(document.getElementById(paramCtgId).checked == true){
                            let temp = {ctg_id:'', title:''};
                            temp.ctg_id = paramCtgId;
                            temp.title = paramTitle;
                            vm.selectedDepth4_nm.push(temp);
                        }else{

                            let tempNameList = [];
                            for(let i=0; i<vm.selectedDepth4_nm.length; i++){
                                if(vm.selectedDepth4_nm[i].ctg_id.indexOf(paramCtgId) < 0){
                                    let temp = {ctg_id:'', title:''};
                                    temp.ctg_id = vm.selectedDepth4_nm[i].ctg_id;
                                    temp.title = vm.selectedDepth4_nm[i].title;
                                    tempNameList.push(temp);
                                }
                            }
                            vm.selectedDepth4_nm = tempNameList;

                        }
                    }
                }
                
            }).
            catch(function(ex) {
                console.log(ex);
                $('#loading-layer').hide();
            });
        }

        ,fnGetCategoryDepthList: function(paramCtgId){
            
            let vm = this.viewModel;
            vm.categoryDepth2 = [];
            vm.categoryDepth3 = [];
            vm.categoryDepth4 = [];
            vm.categoryList = [];

            vm.ListAllcategoryDepth2 = [];
            vm.ListAllcategoryDepth3 = [];
            vm.ListAllcategoryDepth4 = [];

            Axios.post("/advis/code/tree/category/list", {
                paramCtgId: paramCtgId
            })
            .then(function (response) {
                vm.categoryList = response.data.list;
            
                for(var i=0; i<vm.categoryList.length; i++){
                    if(vm.categoryList[i].depth == 2){
                        vm.ListAllcategoryDepth2.push(vm.categoryList[i]);
                    }else if(vm.categoryList[i].depth == 3){
                        vm.ListAllcategoryDepth3.push(vm.categoryList[i]);
                    }else{
                        if(vm.categoryList[i].depth == 4){
                            vm.ListAllcategoryDepth4.push(vm.categoryList[i]);
                        }
                    }
                }

                if(vm.selectedDepth2.length==0){
                    vm.categoryDepth2 = vm.ListAllcategoryDepth2;
                }else{
                    for(let i=0; i<vm.ListAllcategoryDepth2.length; i++){
                        if(vm.selectedDepth2.indexOf(vm.ListAllcategoryDepth2[i].ctg_id) > -1) vm.categoryDepth2.push(vm.ListAllcategoryDepth2[i]);
                    }
                }

                if(vm.selectedDepth3.length==0){
                    vm.categoryDepth3 = vm.ListAllcategoryDepth3;
                }else{
                    for(let i=0; i<vm.ListAllcategoryDepth3.length; i++){
                        if(vm.selectedDepth3.indexOf(vm.ListAllcategoryDepth3[i].ctg_id) > -1) vm.categoryDepth3.push(vm.ListAllcategoryDepth3[i]);
                    }
                }
                
                if(vm.selectedDepth4.length==0){
                    vm.categoryDepth4 = vm.ListAllcategoryDepth4;

                    for(let i=0; i<vm.selectedDepth3.length; i++){
                        for(let k=0; k<vm.ListAllcategoryDepth4.length; k++){
                            if(vm.ListAllcategoryDepth4[k].ctg_id.indexOf(vm.selectedDepth3[i]) > -1){
                                vm.selectedDepth4.push(vm.ListAllcategoryDepth4[k].ctg_id);
                                let temp = {ctg_id:'', title:''};
                                temp.ctg_id = vm.ListAllcategoryDepth4[k].ctg_id;
                                temp.title = vm.ListAllcategoryDepth4[k].title;
                                vm.selectedDepth4_nm.push(temp);
                            }
                        }
                    }
                }else{
                    let cnt = 0;
                    for(let i=0; i<vm.selectedDepth3.length; i++){
                        cnt = 0;
                        for(let j=0; j<vm.selectedDepth4.length; j++){
                            if(vm.selectedDepth3[i].substring(0,10) == vm.selectedDepth4[j].substring(0,10)){
                                cnt ++;
                            }
                        }
                        if(cnt == 0){
                            for(let k=0; k<vm.ListAllcategoryDepth4.length; k++){
                                if(vm.ListAllcategoryDepth4[k].ctg_id.indexOf(vm.selectedDepth3[i]) > -1){
                                    vm.selectedDepth4.push(vm.ListAllcategoryDepth4[k].ctg_id);
                                    let temp = {ctg_id:'', title:''};
                                    temp.ctg_id = vm.ListAllcategoryDepth4[k].ctg_id;
                                    temp.title = vm.ListAllcategoryDepth4[k].title;
                                    vm.selectedDepth4_nm.push(temp);
                                }
                            }
                        }
                    }

                    for(let i=0; i<vm.ListAllcategoryDepth4.length; i++){
                        if(vm.selectedDepth4.indexOf(vm.ListAllcategoryDepth4[i].ctg_id) > -1) vm.categoryDepth4.push(vm.ListAllcategoryDepth4[i]);
                    }
                }

            }).catch(function (e){ $("#loading-layer").hide();} );
        }

        ,fnGetEventList: function(paramCtgId){
            let vm = this.viewModel;
            vm.eventList = [];

            Axios.post("/advis/code/tree/event/list", {
                paramCtgId: paramCtgId
            })
            .then(function (response) {
                let _list = response.data.resultList;
                
                for(let i=0; i<_list.length; i++){
                    let _data = {ctg_id:'', evt_id:'', title:'', detail:[] };
                    _data.ctg_id = _list[i].ctg_id;
                    _data.evt_id = _list[i].evt_id;
                    _data.title = _list[i].title;

                    Axios.post("/advis/code/tree/eventItem/list", {
                        paramEvtId: _list[i].evt_id
                    })
                    .then(function (response) {
                        let _detail = response.data.eventItemList;

                        for(let j=0; j<_detail.length; j++){
                            if(vm.selectedDepth4.indexOf(_detail[j].ctg_id)>-1){
                                _data.detail.push(_detail[j]);
                            }
                        }
                        vm.eventList.push(_data);
                    }).
                    catch(function(ex) {
                        console.log(ex);
                        $('#loading-layer').hide();
                    });
                    
                }

            }).
            catch(function(ex) {
                console.log(ex);
                $('#loading-layer').hide();
            });
        }


    }
});