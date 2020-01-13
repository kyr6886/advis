<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>D3 v4 - force layout</title>
<style>
.svglabel {
  font-family: sans-serif;
  pointer-events: none;
}

body {
  -webkit-touch-callout: none; /* iOS Safari */
    -webkit-user-select: none; /* Safari */
     -khtml-user-select: none; /* Konqueror HTML */
       -moz-user-select: none; /* Firefox */
        -ms-user-select: none; /* Internet Explorer/Edge */
            user-select: none; /* Non-prefixed version, currently
                                  supported by Chrome and Opera */
}
</style>
</head>

<body>
<div id="graph"></div>    
    
    
    
<script src="//cdnjs.cloudflare.com/ajax/libs/d3/4.1.1/d3.js"></script>    
<script>
var width = 960,
height = 700,
radius = (Math.min(width, height) / 2) - 10;
var formatNumber = d3.format(",d");
var x = d3.scaleLinear()
.range([0, 2 * Math.PI]);
var y = d3.scaleLinear()
.range([0, radius]);
var color = d3.scaleOrdinal(d3.schemeCategory20);
var partition = d3.partition();

function startAngle(d) { return Math.max(0, Math.min(2 * Math.PI, x(d.x0))); }
function endAngle(d) { return Math.max(0, Math.min(2 * Math.PI, x(d.x1))); }
function innerRadius(d) { return Math.max(0, y(d.y0)); }
function outerRadius(d) { return Math.max(0, y(d.y1)); }

var arc = d3.arc()
.startAngle( function(d) { return startAngle(d);  })
.endAngle(   function(d) { return endAngle(d);    })
.innerRadius(function(d) { return innerRadius(d); })
.outerRadius(function(d) { return outerRadius(d); })
var texttransform = function(d) {
var translation = y(d.y0);
var rotation = computeTextRotation(d);
if (rotation > 90 && rotation < 270) {
  rotation = rotation + 180;
  translation = -translation;
}
return (
  "rotate(" + rotation + ")" +
  "translate(" + translation + ",0)"
);
}
var transition = {};
function calcTransitionPercentage(){
var now = Date.now()-transition.clockNow;
if(!transition.delay || now > transition.delay){
  return Math.min(1,(now-(transition.delay||0))/transition.duration);
}
return 0;
}
function computeTextRotation(d) {
if (d.depth === 0) {
  return 0;
}
var current = x((d.x0 + d.x1)/2);
var angle = (current - Math.PI / 2) / Math.PI * 180;
if(transition.node === d){
  angle -= 90 * calcTransitionPercentage();
}
return (angle >  90 || angle < 270) ?  angle : 180 + angle ;
}
var textanchor = function(d) {
if (d.depth === 0) {
  return "middle";
}
var rotation = computeTextRotation(d);
return (rotation > 90 && rotation < 270) ? "end" : "start";
}
var textdx = function(d) {
if (d.depth === 0) {
  return 0;
}
var rotation = computeTextRotation(d);
return (rotation > 90 && rotation < 270) ? -6 : 6;
}
var svg = d3.select("body").append("svg")
.attr("width", width)
.attr("height", height)
.append("g")
.attr("transform", "translate(" + width / 2 + "," + (height / 2) + ")");
function calcFontSize(d) {
const xFactor = 12, yFactor = 7.5 ; // stub
if (d.depth === 0) {
  return "30px";
}
// use inner arc len as text height delimiter
var innerArc = (endAngle(d) - startAngle(d)) * 2 * Math.PI * innerRadius(d);
var len = (d.y1-d.y0) * radius;
return Math.min(innerArc / yFactor, len / d.data.textlen * xFactor) + "px";
}
function click(d = { x0: 0, x1: 1, y0: 0, y1: 1 }) {
transition = {clockNow: Date.now(), duration: 750, node: d }
var trans = svg.transition().duration(750);
trans.selectAll("path")
  .attrTween("d", function(n) { return function() { return arc(n); }; })
  .tween("scale", function() {
    var xd = d3.interpolate(x.domain(), [d.x0, d.x1]),
    yd = d3.interpolate(y.domain(), [d.y0, 1]),
    yr = d3.interpolate(y.range(), [d.y0 ? 20 : 0, radius]);
    return function(t) {
      x.domain(xd(t));
      y.domain(yd(t)).range(yr(t));
    };
  });
  trans.selectAll("text")
    .attrTween("transform",   function(n) { return function() { return texttransform(n); }; })
    .attrTween("text-anchor", function(n) { return function() { return textanchor(n); }; })
    .attrTween("dx",          function(n) { return function() { return textdx(n); }; })
    .styleTween("font-size",  function(n) { return function() { return calcFontSize(n); }; });
  trans.selectAll("text")
    .delay(400)
    .attrTween("opacity",     function(n) { return function() {
      if (d === n || n.ancestors().includes(d)) {
        return 1;
      } else {
        return 0;
      }
    }; });
}
d3.text('https://raw.githubusercontent.com/manooh/NVSee/master/data/feelings_EN.txt', function(error, raw){
if (error) throw error;
// replace two-space indentation with pipes
raw = raw.replace(new RegExp('  ', 'g'), '|');
console.log(raw);
//read pipe-delimited data
var dsv = d3.dsvFormat('|');
console.log(dsv);
var flatData = dsv.parse(raw);
console.log(flatData);
var rData = currentNode = tree(flatData);

rData = d3.hierarchy(rData);
var nodes = partition(rData
    .sum(function(d) { return 1; }) // each leaf gets a size of 1
    .sort(function(a, b) { d3.ascending(a.name, b.name) }) // not working?
  )
  .descendants();
g = svg.selectAll("path")
  .data(nodes)
  .enter().append("g");
path = g.append("path")
  .attr("d", arc)
  .style("fill", function(d, i) {
    var c;
    if (d.depth === 0) {
      return "white";
    } else if (d.depth === 1) {
      c = color((d.children ? d : d.parent).data.name);
    } else if (d.depth > 1) {
      c = d3.color(d.parent.data.color).darker();
    }
    d.data.color = c;
    return c;
  })
  .on("click", click)
  .append("title")
  .text(function(d) { return d.data.name });
text = g.append("text")
  .style("fill", function(d) {
    if (d.depth === 0) {
      return "#CCC";
    } else {
      return "#FFF";
    }})
  .attr("class", "svglabel")
  .attr("transform",   texttransform)
  .attr("text-anchor", textanchor)
  .attr("dx",  textdx)
  .attr("dy", ".35em") // vertical-align
  .text(function(d) { return d.data.name; })
  .style("font-size", function(d) {
      // hack. save text len as property to make accessible in transiton
      d.data.textlen = this.getComputedTextLength();
      return calcFontSize(d);
    });
});
function tree(nodes) {
var curr, parent, root;
var lev = 1;
nodes.forEach(function(d) {
  if (!root) {
    // handle root (first node)
    curr = {
      name:     d.d1,
      children: []
    };
    root   = curr;
    parent = curr;
  } else {
    if (d['d' + (lev+1)]) {
      // handle children
      lev = lev+1;
      parent = curr;
    } else if (d['d' + (lev-1)]) {
      // handle moving up the hierarchy
      lev = lev-1;
      parent = parent.parent;
    } else if (!d['d' + lev]) {
      // if it's neither child, nor moving up, nor a sibling, handle exception
      throw "unhandled tree level";
    }
    curr = {
      name:     d['d' + lev],
      children: []
    };
    curr.parent = parent;
    parent.children.push(curr);
  }
});
return root;
};
/* !(function(){
    "use strict"

    var width,height
    var chartWidth, chartHeight
    var margin
    var svg = d3.select("#graph").append("svg")
    var chartLayer = svg.append("g").classed("chartLayer", true)
    
    main()
    
    function main() {
        var range = 100
        var data = {
            nodes:d3.range(0, range).map(function(d){ return {label: "l"+d ,r:~~d3.randomUniform(8, 28)()}}),
            links:d3.range(0, range).map(function(){ return {source:~~d3.randomUniform(range)(), target:~~d3.randomUniform(range)()} })        
        }
        
        setSize(data)
        drawChart(data)    
    }
    
    function setSize(data) {
	      let bound = document.querySelector("svg")
        	.getBoundingClientRect();
      
        width = bound.width;
        height = bound.height;
    
        margin = {top:0, left:0, bottom:0, right:0 }
        
        
        chartWidth = width - (margin.left+margin.right)
        chartHeight = height - (margin.top+margin.bottom)
        
        svg.attr("width", width).attr("height", height)
        
        
        chartLayer
            .attr("width", chartWidth)
            .attr("height", chartHeight)
            .attr("transform", "translate("+[margin.left, margin.top]+")")
            
            
    }
    
    function drawChart(data) {
        function edgeForce(axis, origin, strength) {
          var nodes;
          
          function force(alpha) {
            nodes.forEach(function(node) {
              Math.max(Math.abs(origin - node[axis]), node.r)
              
              
              var delta = strength / (origin - node[axis]) * alpha;
              var repulsion = node.r * strength / 10000
              
              node[axis] -= delta;
            })
          }
          
          force.initialize = function(_) {
            nodes = _;
          }
          
          return force;
        }
        
        var simulation = d3.forceSimulation()
            //.force("link", d3.forceLink().id(function(d) { return d.index }))
            .force("collide",d3.forceCollide( function(d){return d.r + 4 }).iterations(16) )
           .force("charge", d3.forceManyBody(function(d){return -240;}))
            .force("center", d3.forceCenter(chartWidth / 2, chartHeight / 2))
            //.force("y", d3.forceY(0))
            //.force("x", d3.forceX(0))
        .force('edge-left', edgeForce('x', 0, 1000))
        .force('edge-right', edgeForce('x', chartWidth, 1000))
        .force('edge-top', edgeForce('y', 0, 1000))
        .force('edge-bottom', edgeForce('y', chartHeight, 1000))
    
        var link = svg.append("g")
            .attr("class", "links")
            .selectAll("line")
            .data(data.links)
            .enter()
            .append("line")
            .attr("stroke", "black")
        
        var node = svg.append("g")
            .attr("class", "nodes")
            .selectAll("circle")
            .data(data.nodes)
            .enter().append("circle")
            .attr("r", function(d){  return d.r })
            .call(d3.drag()
                .on("start", dragstarted)
                .on("drag", dragged)
                .on("end", dragended));    
        
        
        var ticked = function() {
            link
                .attr("x1", function(d) { return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });
    
            node
                .attr("cx", function(d) { return d.x; })
                .attr("cy", function(d) { return d.y; });
        }  
        
        simulation
            .nodes(data.nodes)
            .on("tick", ticked);
    
        simulation.force("link")
            .links(data.links);    
        
        
        
        function dragstarted(d) {
            if (!d3.event.active) simulation.alphaTarget(0.3).restart();
            d.fx = d.x;
            d.fy = d.y;
        }
        
        function dragged(d) {
            d.fx = d3.event.x;
            d.fy = d3.event.y;
        }
        
        function dragended(d) {
            if (!d3.event.active) simulation.alphaTarget(0);
            d.fx = null;
            d.fy = null;
        } 
                
    }
}()); */
</script>    
</body>
</html>