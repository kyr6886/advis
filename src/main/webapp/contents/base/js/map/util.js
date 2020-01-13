var getCircleAlertSymbol=function(svg,width,height,type){
	console.log(svg);
   var _colors=["#fa5882"];
   var _strokeColors=["#ff0040"];
   var _g=svg.append("g")
		.attr("width",width)
		.attr("height",height);
   		
   var circle=_g.append("circle")
		  .style("stroke",_strokeColors[type])
		  .style("fill",_colors[type])
		  .attr("r",width)
		  .attr("cx",width/2)
		  .attr("cy",height/2);
   
   	(function repeat() {
		     circle.transition()
			.duration(2000)
			.attr("r", width/5)
			.attr('stroke-width', 25.5)
			.transition()
			.duration(2000)
			.attr('stroke-width', 0.5)
			.attr("r", width)
			.ease('sine')
			.each("end", repeat);
   	})();
		  
   	return _g;
}