swiper = new Swiper('.swiper1', {
		pagination : '.pagination1',
		loop : true,
		autoplay : 5000,
		paginationClickable :true
	});

	if ($(window).height() <= 416) {
		$('.module').find('img').width("60%");
	} else if ($(window).height() > 416 && $(window).height() < 520) {
		$('.module').find('img').width("80%");
	} else {
		$('.module').find('img').width('100%');
	}
	function toTwo() {
		var iHeight = $('.nav').find('li').height();
		$('.nav li').find('dl').css('paddingTop', (iHeight - 40) / 2 + 'px')
				.css('color', '#fff').css('height', '50px');
		var iHeight2 = $('.menu').height();
		var iHeight3 = $('.lead').find("h1").height();
		$('.lead').find('h1').css('paddingTop', 3 + 'px');
		$('.lead').find('.menu').css('paddingTop', (iHeight2 - 15) / 2 + 'px');
		$('.lead').find('.user').css('paddingTop', (iHeight2 - 22) / 2 + 'px');
	}
	toTwo();

	var ulla = $("#div1 ul");
	var leftbtn = $("#leftbtn");
	var rightbtn = $("#rightbtn");
	var li = $("#div1 ul li");
	var lione = $("#div1 ul li").eq(0).width();
	var spanlis = $("#lisbtn span");
	var n = 0;
	var zwidth = lione * li.length;
	var time1;
	var time2;
	spanlis.eq(0).addClass("select").siblings().removeClass("select");
	function test1() {

		if (time1) {
			clearInterval(time1);
		}
		if (time2) {
			clearTimeout(time2);
		}
		time1 = setInterval("test2()", 2500);
	}
	test1();
	function test2() {
		nrem.nadd();
	}
	$("#div1").swiperight(function() {
		if (time1) {
			clearInterval(time1)
		}
		if (time2) {
			clearTimeout(time2);
		}
		nrem.nsubtract();
	});
	$("#div1").swipeleft(function() {
		if (time1) {
			clearInterval(time1)
		}
		if (time2) {
			clearTimeout(time2);
		}
		nrem.nadd();

	});
	var nrem = {
		nadd : function() {
			if (n >= (li.length - 1)) {
				n = 0;
				$("#div1").scrollLeft(0);
			}
			if (n == li.length - 2) {
				spanlis.eq(0).addClass("select").siblings().removeClass(
						"select");
			}
			n++;
			$("#div1").stop(true).animate({
				scrollLeft : n * lione
			}, 500);
			spanlis.eq(n).addClass("select").siblings().removeClass("select");
			time2 = setTimeout("test1()", 1500);
		},
		nsubtract : function() {
			if (n <= 0) {
				n = li.length - 1;
				$("#div1").scrollLeft(zwidth);
			}
			n--;
			$("#div1").stop(true).animate({
				scrollLeft : n * lione
			}, 500);
			spanlis.eq(n).addClass("select").siblings().removeClass("select");
			time2 = setTimeout("test1()", 1500);
		}
	}
	var ind = 0;
	$(".menu").css('cursor', 'pointer');
	$(".menu")
			.click(
					function() {
						$(".hidden_menu").height($(window).height());
						$('#nav').stop().animate({
							"left" : "164px"
						}, 200, 'linear');
						$('.foot3').stop().animate({
							"left" : "164px"
						}, 200, 'linear');
						$(".hidden_menu").find("ul").height(
								$(".hidden_menu").height());
						$(".hidden_menu").find("ul").find("li").height(
								$(".hidden_menu").find("ul").height() / 13.5);
						if ($('.out').height() < $(".hidden_menu").height()) {
							$('.out').height($(".hidden_menu").height());
						}
						if ($('#footer')) {
							$('#footer').stop().animate({
								"left" : "164px"
							}, 200, 'linear');
						}
						if ($('.cure_nav')) {
							$('.cure_nav').stop().animate({
								"left" : "164px"
							}, 200, 'linear');
						}
						if (ind == 0) {
							$(".out").stop().animate({
								"left" : "164px"
							}, 200, 'linear');
							$(".hidden_menu").show();
							ind = 1;
						} else {
							$(".out,#nav,#footer,.cure_nav,.foot3").stop()
									.animate({
										"left" : "0"
									}, 200, 'linear');
							ind = 0;
						}

					})
	$('html')
			.swipeleft(
					function() {
						$(".out,.foot3").stop().animate({
							left : "0"
						}, 200, 'swing');
						if ($('.out').css('left') < 0) {
							$('.out').css('left', 0);
						}
						$('.head').css('position', 'fixed').css('top', 0);
						$(".hidden_menu").height($(window).height());
						$(".hidden_menu").find("ul").height(
								$(".hidden_menu").height());
						$(".hidden_menu").find("ul").find("li").height(
								$(".hidden_menu").find("ul").height() / 13.5);
						if ($('.out').height() < $(".hidden_menu").height()) {
							$('.out').height($(".hidden_menu").height());
						}
					});
	$('html')
			.swiperight(
					function() {
						$(".out,.foot3").stop().animate({
							left : "164px"
						}, 200, 'swing');
						if ($('.out').css('left') > 0) {
							$('.out').css('left', 0);
						}
						$('.head').css('position', 'fixed').css('top', 0);
						$(".hidden_menu").height($(window).height());
						$(".hidden_menu").find("ul").height(
								$(".hidden_menu").height());
						$(".hidden_menu").find("ul").find("li").height(
								$(".hidden_menu").find("ul").height() / 13.5);
						if ($('.out').height() < $(".hidden_menu").height()) {
							$('.out').height($(".hidden_menu").height());
						}
					});

	$(document).dblclick(function() {
		return false;
	});
	function logout() {
//		var is = confirm(msg.index_loginout);
//		if (is) {
//			location.href = "${pageContext.request.contextPath}/logout";
//		}
	}