import Vue from 'vue'
import Axios from 'axios'

new Vue({
    el: '#scope-dmeStation',
    data: function() {
        return {
            viewModel: {
                paramArea: "",
                paramType: "",
                listAreaCode: [],
                listTypeCode: [],
                listDmeStation: []
            }
        }
    },
    mounted: function() {
        this.fnPageInit();
    },
    methods: {
        fnPageInit: function() {
            let vm = this;

            Axios.post("/advis/damage/station/init", {}).
            then(function(response) {
                vm.viewModel.listTypeCode = response.data.listTypeDmeStation;
                vm.viewModel.listAreaCode = response.data.listSido;

                if (vm.viewModel.listTypeCode.length > 0) {
                    vm.viewModel.paramArea = vm.viewModel.listAreaCode[0].code.substring(0, 2);

                    vm.fnListDmeStation();
                }
            }).catch(function(ex) {
                console.log(ex);
            });
        },
        fnListDmeStation: function() {
            let vm = this;

            Axios.post("/advis/damage/station/list", {
                paramArea: vm.viewModel.paramArea,
                paramType: vm.viewModel.paramType
            }).then(function(response) {
                vm.viewModel.listDmeStation = response.data.listDmeStation;
            }).catch(function(ex) {
                console.log(ex);
            });
        }
    }
});