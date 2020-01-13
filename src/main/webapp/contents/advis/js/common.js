
function headerEvent() {
	var serviceWrap = $('.head-service'), serviceBtn = serviceWrap
			.find('.my-info > a'), myInfoLayer = $('.head-my-info'), myInfoImg = myInfoLayer
			.find('.my-photo img'), imgH;
	serviceBtn.on('click', function(e) {
		var _thisparent = $(this).parent();
		if (_thisparent.hasClass('open')) {
			_thisparent.removeClass('open');
			myInfoLayer.css('right', '9999px');
		} else {
			_thisparent.addClass('open');
			myInfoLayer.css('right', '2rem');
		}
	});
}

function imagesCropMethodLength(_cropClass) {
	var _cropImg = _cropClass.find('img');

	if (_cropImg.length == 1) {
		imagesCropMethod(_cropImg, _cropClass);
	} else {
		_cropImg.each(function(index) {
			imagesCropMethod(_cropImg.eq(index), _cropClass.eq(index));
		});
	}
}
function imagesCropMethod(img, _cbox) {
	var imgFileW = img.width(), imgFileH = img.height(), imgH, imgW;
	if (imgFileW >= imgFileH) {
		img.css({
			'left' : '50%',
			'height' : '100%'
		})
		imgW = img.width() / -2;
		img.css('margin-left', imgW + 'px');
	} else {
		img.css({
			'top' : '50%',
			'width' : '100%'
		})
		imgH = img.height() / -2;
		img.css('margin-top', imgH + 'px');
	}
}
function resizeContent() {
	var windowHeight = $(window).height();
	var topHeight = $("header").height();
	var bottomHeight = $("footer").height() + 1;
	$('#container').css({
		'min-height' : (windowHeight - topHeight - bottomHeight) + 'px'
	});
}

function dropdown_keyboard_access(nav) {
	var menuParent = $(nav).parent();

	// keyboard_access
	$(nav).focus(function() {
		var menuParent = $(this).parent();
		$(menuParent).addClass('on');
		$(menuParent).siblings().removeAttr('class');
	});

	$('.depth2 li:last-child a').blur(function() {
		$(this).parents('nav > ul > li').removeAttr('class');
	});


	// mouse_access
	$(nav).parent().mouseenter(function() {
		if (S_STAT.PC || S_STAT.TABLET) {
			$(this).addClass('on');
			$(this).siblings().removeClass('on');

		}
	});
	$(nav).parent().mouseleave(function() {
		if ((S_STAT.PC || S_STAT.TABLET)) {
			if(!$(this).hasClass('active')) {
				$(this).removeAttr('class');
			}

		}
	});

	$(nav).bind({
		click : function(d) {
			if (S_STAT.TABLET) {
				d.preventDefault();
				$(this).parent().addClass("on")
			}
			if (S_STAT.MOBILE) {
				d.preventDefault();
				if ($(this).parent().hasClass("on")) {
					$(this).parent().siblings().removeAttr("class")
				} else {
					$(this).parent().addClass("on")
				}
			}
		}
	});
}

function calendarInput() {
	$(".datepicker").datepicker(
			{
				changeMonth : true,
				changeYear : true,
				showMonthAfterYear : true, // 타이틀영역 월이전 년출력 (년/월 순)
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ], // 요일
																		// 출력텍스트(축소형)
				monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ], // 달 출력텍스트
				dateFormat : "yy-mm-dd", // 표기방법
				showOtherMonths : true, // 현재월에 이전,다음달 날짜표시
				showOn : "both",
				buttonImage : "/images/ic-calendar.png",
				buttonImageOnly : true,
				buttonText : "날짜선택",
				beforeShow : function() {
					$(this).datepicker("widget").removeClass(
							"datepicker-hide-days");
					$(this).datepicker("widget").removeClass(
							"datepicker-year-wrap");
				},
				onClose : function() {
					$(this).datepicker("widget").removeClass(
							"datepicker-hide-days");
					$(this).datepicker("widget").removeClass(
							"datepicker-year-wrap");
				}
			});
	$(".datepicker.disabled").datepicker("option", "disabled", true);
}


$(document).ready(function() {
			headerEvent();
			imagesCropMethodLength($('.crop-img'));
			resizeContent();
			calendarInput();
			initMenu();

			$('.tab a').on('click', function(e) {
			
				var parent = $(this).parent().parent();
				parent.addClass('on');
				parent.siblings().removeClass('on');
			});
			
			/*	$('.tab2 a').on('click', function(e) {
				e.preventDefault();
				var parent = $(this).parent().parent();
				parent.addClass('on');
				parent.siblings().removeClass('on');
			});

			$('.tab + .map').css('margin-top', $('.search').outerHeight());
			dropdown_keyboard_access('nav > ul > li > a');

			// mobile gnb menu
			$('.ic-menu').on('click focus', function(e) {
				e.preventDefault();
				$('#gnb').addClass('on');
			});

			$('.ic-close').on('click blur', function(e) {
				e.preventDefault();
				$('#gnb').removeAttr('class');
			});

			// main
			$('.main-tab-area > ul > li > a, .panel-category > ul > li > a')
					.on('click focus', function(e) {
						e.preventDefault();
						$(this).parent().addClass('active');
						$(this).parent().siblings().removeAttr('class');
					});*/

			$('.banner .btn').on('click', function(e) {
				if ($('.banner ul').hasClass('show-all')) {
					$('.banner ul').removeAttr('class');
				} else {
					$('.banner ul').addClass('show-all');
				}
			});

			// (구)건설기준 prevent a tag
			$('.panel-category > ul > li > ul > li > a').on('click',
					function(e) {
						if ((S_STAT.PC || S_STAT.TABLET)) {
							e.preventDefault();
						}
					});

			var a = true;
			var c = true;
			var b;
			$("ul.nav").bind({
				focusin : function(d) {
					try {
						clearTimeout(b)
					} catch (d) {
					}
					c = false;
					if (S_STAT.PC || S_STAT.TABLET) {
						$("ul.nav").addClass("on")
					}
				},
				focusout : function(d) {
					if (a) {
						b = setTimeout(function() {
							$("ul.nav").removeClass("on");
							c = true
						}, 20)
					}
				}
			});
			// $("a.menuicon").on({
			// click: function(e) {
			// e.preventDefault();
			// if (S_STAT.MOBILE) {
			// $("#nav").addClass("on");
			// $("body").css({
			// overflow: "hidden",
			// position: "fixed",
			// height: "100%",
			// width: "100%"
			// })
			// }
			// }
			// });
			$("#nav > div > a.menuclose").on({
				click : function(e) {
					e.preventDefault();
					if (S_STAT.TABLET || S_STAT.MOBILE) {
						$("#nav").removeClass("on");
						$("body").css({
							overflow : "visible",
							position : "static",
							height : "auto",
							width : "auto"
						})
					}
				}
			});
		});

$(window).resize(function() {
	resizeContent();
	initMenu()
});

$(window)
		.scroll(
				function(d) {
					$(".btn-top")
							.css(
									"display",
									(($(window).scrollTop() + $(window)
											.height()) > 1200) ? "block"
											: "none")
				}).triggerHandler("scroll");
$(".btn-top a").bind({
	click : function(d) {
		d.preventDefault();
		$(window).scrollTop(0)
	}
})

var P_STAT = {};
var S_STAT = {};

function initMenu() {
	var b = $(window).width();
	if (b >= 1200) {
		S_STAT = {
			PC : 1,
			TABLET : 0,
			MOBILE : 0,
			MOBILE320 : 0,
			CN : "1000"
		}
	} else {
		if (b >= 768 && b < 1200) {
			S_STAT = {
				PC : 0,
				TABLET : 1,
				MOBILE : 0,
				MOBILE320 : 0,
				CN : "0100"
			}
		} else {
			if (b > 320) {
				S_STAT = {
					PC : 0,
					TABLET : 0,
					MOBILE : 1,
					MOBILE320 : 0,
					CN : "0010"
				}
			} else {
				S_STAT = {
					PC : 0,
					TABLET : 0,
					MOBILE : 1,
					MOBILE320 : 1,
					CN : "0011"
				}
			}
		}
	}
	chkResize()
}

function chkResize() {
	if (P_STAT.CN != S_STAT.CN) {
	}
	P_STAT = S_STAT
};


function Tooltip(tooltipId, width){
	  var tooltipId = tooltipId;
	  $("body").append("<div class='tooltip' id='"+tooltipId+"'></div>");

	  if(width){
	    $("#"+tooltipId).css("width", width);
	  }

	  hideTooltip();

	  function showTooltip(content, event) {
	    $("#"+tooltipId).html(content);
	    $("#"+tooltipId).show();

	    updatePosition(event);
	  }

	  function hideTooltip(){
	    $("#"+tooltipId).hide();
	  }

	  function updatePosition(event){
	    var ttid = "#"+tooltipId;
	    var xOffset = 20;
	    var yOffset = 10;

	    var toolTipW = $(ttid).width();
	    var toolTipeH = $(ttid).height();
	    var windowY = $(window).scrollTop();
	    var windowX = $(window).scrollLeft();
	    var curX = event.pageX;
	    var curY = event.pageY;
	    var ttleft = ((curX) < $(window).width() / 2) ? curX - toolTipW - xOffset*2 : curX + xOffset;
	    if (ttleft < windowX + xOffset){
	      ttleft = windowX + xOffset;
	    } 
	    var tttop = ((curY - windowY + yOffset*2 + toolTipeH) > $(window).height()) ? curY - toolTipeH - yOffset*2 : curY + yOffset;
	    if (tttop < windowY + yOffset){
	      tttop = curY + yOffset;
	    } 
	    $(ttid).css('top', tttop + 'px').css('left', ttleft + 'px');
	  }

	  return {
	    showTooltip: showTooltip,
	    hideTooltip: hideTooltip,
	    updatePosition: updatePosition
	  }
	}