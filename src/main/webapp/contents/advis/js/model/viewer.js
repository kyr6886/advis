
var vueObj = null;
function pageLoad(obj) {
    vueObj = obj;
    obj.fnGetDetail($("#paramCtgId").val());
    obj.fnGetList($("#paramCtgId").val());
}