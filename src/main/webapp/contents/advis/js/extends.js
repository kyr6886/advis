// 숫자 타입에서 쓸 수 있도록 format() 함수 추가
Number.prototype.format = function(){
    if(this==0) return 0;
 
    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (this + '');
 
    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
 
    return n;
};
 
// 문자열 타입에서 쓸 수 있도록 format() 함수 추가
String.prototype.format = function(){
    var num = parseFloat(this);
    if( isNaN(num) ) return "0";
 
    return num.format();
};

String.prototype.dateFormat=function(){
	
	if(this.length==8){
	var _year=this.substring(0,4);
	
	var _month=this.substring(4,6);
	
	var _day=this.substring(6,8);
	
		return _year+"-"+_month+"-"+_day;
	}
	if(this.length>=10){
		var _year=this.substring(0,4);
		
		var _month=this.substring(4,6);
		
		var _day=this.substring(6,8);
		var _hour=this.substring(8,10);
			return _year+"-"+_month+"-"+_day+":"+_hour;
		}
	return this;
	
}
String.prototype.dateFormatKR=function(){
	
	if(this.length==8){
	var _year=this.substring(0,4);
	
	var _month=this.substring(4,6);
	
	var _day=this.substring(6,8);
	
		return _year+"년 "+_month+"월 "+_day+"일";
	}
	return this;
	
}

$.fn.rowspan = function(colIdx, isStats) {       
	return this.each(function(){      
		var that;     
		$('tr', this).each(function(row) {      
			$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
				
				if ($(this).html() == $(that).html()
					&& (!isStats 
							|| isStats && $(this).prev().html() == $(that).prev().html()
							)
					) {            
					rowspan = $(that).attr("rowspan") || 1;
					rowspan = Number(rowspan)+1;

					$(that).attr("rowspan",rowspan);
					
					// do your action for the colspan cell here            
					$(this).hide();
					
					//$(this).remove(); 
					// do your action for the old cell here
					
				} else {            
					that = this;         
				}          
				
				// set the that if not already set
				that = (that == null) ? this : that;      
			});     
		});    
	});  
};
