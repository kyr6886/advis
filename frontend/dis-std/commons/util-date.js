export default {
  toDay_YYYYMMDD: function (n) {
    let now = new Date();
    if (n != undefined && n != null) {
      now.setDate(now.getDate()+parseInt(n))
    }
    let y = now.getFullYear();
    let m = now.getMonth() + 1;
    let d = now.getDate();
    return '' + y +'-'+ (m < 10 ? '0' : '') + m +'-'+ (d < 10 ? '0' : '') + d;
  }

   ,toChangeMonth_YYYYMMDD: function (n) {
    let now = new Date();
     if (n != undefined && n != null) {
       now.setMonth(now.getMonth() + parseInt(n))
    }
    let y = now.getFullYear();
    let m = now.getMonth() + 1;
    let d = now.getDate();
    return '' + y + '-' + (m < 10 ? '0' : '') + m + '-' + (d < 10 ? '0' : '') + d;
  }
  ,toChangeYear_YYYYMMDD: function (n) {
    let now = new Date();
     if (n != undefined && n != null) {
       now.setFullYear(now.getFullYear() + parseInt(n))
    }
    let y = now.getFullYear();
    let m = now.getMonth() + 1;
    let d = now.getDate();
    console.log('' + y + '-' + (m < 10 ? '0' : '') + m + '-' + (d < 10 ? '0' : '') + d);
    return '' + y + '-' + (m < 10 ? '0' : '') + m + '-' + (d < 10 ? '0' : '') + d;
  }

}
