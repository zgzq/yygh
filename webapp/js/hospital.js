
/*********** ����̶����� **********/
var myScroll;
	function loaded() {
		myScroll = new iScroll('wrapper', {
			useTransform: false,
			onBeforeScrollStart: function (e) {
				var target = e.target;
				while (target.nodeType != 1) target = target.parentNode;
	
				if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
					e.preventDefault();
			}
		});
	}
	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
	document.addEventListener('DOMContentLoaded', loaded, false);

	
$(window).scroll(function(){
	$('.head').css('top',0);
});
$(function(){
	
	
	var ind=0;
	
	$('.menu').click(function(){
		var $h=$('#wrapper').width();
		$('#scroller').width($h);
		$(".hidden_menu").height($(window).height());
		$('#nav').stop().animate({"left":"164px"},200,'linear');
		$('.foot3').stop().animate({"left":"164px"},200,'linear');
		$('#footer').stop().animate({"left":"164px"},200,'linear');
		$('.cure_nav').stop().animate({"left":"164px"},200,'linear');
		$('.head').stop().animate({"left":"164px"},200,'linear');
		$('#wrapper').stop().animate({"left":"164px"},200,'linear');
		$(".hidden_menu").find("ul").height($(".hidden_menu").height());
		$(".hidden_menu").find("ul").find("li").height($(".hidden_menu").find("ul").height()/13.5);
		
		if($('#footer')){
			$('#footer').stop().animate({"left":"164px"},200,'linear');
		}
		if($('#tiwen_btn')){
			$('#tiwen_btn').stop().animate({"left":"164px"},200,'linear');
		}
		if($('.cure_nav')){
			$('.cure_nav').stop().animate({"left":"164px"},200,'linear');
		}
		if(ind==0){
			$("#wrapper").stop().animate({"left":"164px"},200,'linear');
			$(".hidden_menu").show();
			$('.head').stop().animate({"left":"164px"},200,'linear');
			ind=1;
		}
		else{
			$("#wrapper,#nav,#tiwen_btn,#footer,#footer1.cure_nav,.foot3").stop().animate({"left":"0"},200,'linear');
			$('.head').stop().animate({"left":"0px"},200,'linear');
			ind=0;
		}		
	})

	
	$('.hidden_menu').height($(document).height());
	
	$('.user').click(function(){
		$('.head').css('position','fixed').css('top',0);						  
	});

	
	$('#footer').find('li').click(function(){
		$('.head').css('position','fixed').css('top',0);									   
	});

		$('.cure_li2').css('cursor','pointer');
		$(".cure_li2").click(function(){
			var num=$(this).index();
			$('.jiaoshou_miaoshu').eq(num).show().siblings().hide();
			$(".cure_li2").removeClass("active_an");
			$(this).addClass("active_an");
			$(".cure_li2").find(".white_pic").hide();
			$(".cure_li2").find(".gray_pic").show();
			$(this).find(".gray_pic").hide();
			$(this).find(".white_pic").show();
			$(".jiaoshou").show();
		})
})



$("#az_daohang").find('li').height($(document).height()/32);
$("#az_daohang2").find('li').height($(document).height()/32);



function startMove(obj, json, fn) {
	clearInterval(obj.iTimer);
	var iCur = 0;
	var iSpeed = 0;
	obj.iTimer = setInterval(function() {
		var iBtn = true;
		for (var attr in json) {
			
			if (attr == 'opacity') {
				iCur = Math.round(css(obj, 'opacity') * 100);
			} else {
				iCur = parseInt(css(obj, attr));
			}
			iSpeed = (json[attr] - iCur) / 8;
			iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);
			if (iCur != json[attr]) {
				iBtn = false;
				if (attr == 'opacity') {
					obj.style.opacity = (iCur + iSpeed) / 100;
					obj.style.filter = 'alpha(opacity='+(iCur + iSpeed)+')';
				} else {
					obj.style[attr] = iCur + iSpeed + 'px';
				}
			}
			
		}
		
		if (iBtn) {
			clearInterval(obj.iTimer);
			fn && fn.call(obj);
		}
		
	}, 10);
}

function css(obj, attr) {
	if (obj.currentStyle) {
		return obj.currentStyle[attr];
	} else {
		return getComputedStyle(obj, false)[attr];
	}
}