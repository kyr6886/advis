
noaaAdmin.directive('editorForm',['$http',function($http){
	return{
		restrict:'E,C',
		scope:{binding:'='},
		templateUrl:_context+'/contents/base/directives/editor.html?1=1',
		replace: true,
		link:function(scope,element,attrs){
			nhn.husky.EZCreator.createInIFrame({
		        oAppRef: editor_object,
		        elPlaceHolder: "smartEditor",
		        sSkinURI: _context+"/contents/base/editor/SmartEditor2Skin.html", 
		        htParams : {
		            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseToolbar : true,             
		            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseVerticalResizer : true,     
		            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseModeChanger : true, 
		        }
		    });
			
			
			
		}
	};
	
}]);