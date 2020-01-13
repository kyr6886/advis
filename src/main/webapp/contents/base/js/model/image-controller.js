noaaAdmin.controller('imageCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	
	$scope.fnCreateImage=function(name,w,h){
	
		 var tags=document.getElementsByName('targetImage');
		 for(var i=0;i<tags.length;i++){
			
			 html2canvas(tags[i], {
	              allowTaint: true,
	              taintTest: false,
	              onrendered: function(canvas) {
	            	  var data=canvas.toDataURL('image/png');
	            	  fnAddImages(data);
	              },
	              
	              width:w,
	              height:h
	          });
		 }
	}
	
	fnAddImages=function(targetImg){
	
		  $http.post(_context+"/test/html5/saveImage",{base64Str:unescape(encodeURIComponent(targetImg))}
		    ).success(function(data){
		    	console.log(data);
		    });
		
	}
	
	fnDataURLtoBlob=function(dataURL) {
		// Decode the dataURL    
		var binary = atob(dataURL.split(',')[1]);
		// Create 8-bit unsigned array
		var array = [];
		for(var i = 0; i < binary.length; i++) {
		    array.push(binary.charCodeAt(i));
		 }
		// Return our Blob object
		return new Blob([new Uint8Array(array)], {type: 'image/png'});
	 }
	
}]);