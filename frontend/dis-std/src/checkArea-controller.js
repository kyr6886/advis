import Vue from 'vue'
import Axios from 'axios'

var vue = new Vue({
    el: '#container',
    data: function() {
        return {
            viewModel: {
                allList: [],
                checkedList: []
            }
        }
    },
    mounted: function() {
        this.fnlistCheckedArea();
    },
    methods: {
        fnSaveCheckedArea: function() {
            if (confirm("저장하시겠습니까?")) {
                let vm = this;

                Axios.post("/history/accident/createCheckedArea", {
                    listSelectedSidoCodes: vm.viewModel.allList.filter(function(element) {
                        return element.checked
                    })
                }).
                then(function(response) {
                    alert("저장되었습니다.");
                }).
                catch(function(ex) {
                    console.log(ex);
                });
            }
        },
        fnlistCheckedArea: function() {
            let vm = this;

            Axios.post("/history/accident/listCheckedAreaView", {}).
            then(function(response) {
                vm.viewModel.checkedList = response.data.listSelectedSidoCodes;
                vm.viewModel.allList = response.data.listAllSidoCodes;

                for (let i = 0; i < vm.viewModel.allList.length; i++) {
                    for (let j = 0; j < vm.viewModel.checkedList.length; j++) {
                        if (vm.viewModel.allList[i].law_code == vm.viewModel.checkedList[j].law_code) {
                            vm.viewModel.allList[i].checked = true;
                            break;
                        }
                    }
                }
            }).
            catch(function(ex) {
                console.log(ex);
            });
        },
        fnCheckAll: function(event) {
            this.viewModel.allList.forEach(function(element) {
                element.checked = event;
            })
        }
    }
});