
var fnGetAdmSld = function(paramLayerNm,paramPolyColor,paramStrokeColor){
	var adm_red='<?xml version="1.0" encoding="UTF-8"?>';
	adm_red+='<StyledLayerDescriptor xmlns="http://www.opengis.net/sld" xmlns:sld="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml" version="1.0.0">';
	adm_red+='  <NamedLayer>';
	adm_red+='    <Name>'+paramLayerNm+'</Name>';
	adm_red+='      <UserStyle>';
	adm_red+='        <Name>'+paramLayerNm+'</Name>';
	adm_red+='        <FeatureTypeStyle>';
	adm_red+='          <Name>name</Name>';
	adm_red+='          <Rule>';
	adm_red+='            <PolygonSymbolizer>';
	adm_red+='              <Fill>';
	adm_red+='                <CssParameter name="fill">'+paramPolyColor+'</CssParameter>';
	adm_red+='              </Fill>';
	adm_red+='              <Stroke>';
	adm_red+='                <CssParameter name="stroke">'+paramStrokeColor+'</CssParameter>';
	adm_red+='                <CssParameter name="stroke-width">1</CssParameter>';
	adm_red+='              </Stroke>';
	adm_red+='            </PolygonSymbolizer>';
	adm_red+='          </Rule>';
	adm_red+='        </FeatureTypeStyle>';
	adm_red+='      </UserStyle>';
	adm_red+='    </NamedLayer>';
	adm_red+='</StyledLayerDescriptor>';
	return adm_red;
}