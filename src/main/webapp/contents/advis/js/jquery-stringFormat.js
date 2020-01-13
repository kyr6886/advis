(function($){
	$.stringFormat=function(){
		if(arguments.length<2){
			return false;
		}
		else{
			
						for(var i=0;i<arguments.length;i++){
					var searchStr="{"+i+"}";
					while(arguments[0].indexOf(searchStr)!=-1){
						arguments[0]=arguments[0].replace("{"+i+"}",arguments[i+1]);
					}
				}
				return arguments[0];
		
		}
	}
})(jQuery); 