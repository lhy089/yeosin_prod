$(function(){
	//$("#Layer1").insertBefore("#familySite");
	$("#mainMenu").GNB();
	$("#leftMenu").SNB();
	$("#skipContent a").focus(function(){
		$(this).addClass("focus");
	})
	.blur(function(){
		$(this).removeClass("focus");
	});
});

//image src replace
jQuery.fn.imgSwap = function(src_in,src_out){
	if(typeof src_out != "string") src_out="_on";
	$(this).each(function(){
		var that=$(this);
		var imgSrc=that.attr("src");
		if(imgSrc==undefined) return;
		var imgType=imgSrc.match(/.gif$|.jpg$|.png$/);
		if(imgSrc.indexOf(src_in+imgType) == -1) return;
		that.attr("src",imgSrc.replace(src_in+imgType,src_out+imgType));
	});
	return this;
}

function imgRollover(selector,options){
	var opt = { 
		srcOn  : "_on" //img src replace on �⺻�� "on"
		, srcOff : "" //img src replace off �⺻�� ""
		, hold   : "ignoreRollover" //�ѿ��� ���� �Ƚ�ų Ŭ������ - �ش� �̹��� �±׿� �־�� ��
	}
	opt = $.extend(opt, options || {});
	function setBind(el){
		var img = el;
		if(el.hasClass(opt.hold)) return;
		if(el.parent()[0].tagName == "A"){
			el = el.parent();
			img = el.find("img");
		}
		el.bind("mouseenter focus",function(){
			img.imgSwap(opt.srcOff,opt.srcOn);
		})
		el.bind("mouseout blur",function(){
			img.imgSwap(opt.srcOn,opt.srcOff);
		});
	}
	for(var s in selector){
		o = $(selector[s]);
		if(o.is("[src]")){
			o.each(function(){
				setBind($(this));
			});
		}else{
			o.find("[src]").each(function(){
				setBind($(this));
			});
		}
	}
}


function imgRollover2(selector,options){
	var opt = {
		srcOn  : "_on"
		, srcOff : ""
		, hold   : null
	}
	opt = $.extend(opt, options || {});
	function setBind(el){
		if(el.hasClass(opt.hold)) return;
		el.hover(function(){
			$(this).imgSwap(opt.srcOff,opt.srcOn);
		},function(){
			$(this).imgSwap(opt.srcOn,opt.srcOff);
		});
	}
	for(var s in selector){
		o = $(selector[s]);
		if(o.is("[src]")){
			o.each(function(){
				setBind($(this));
			});
		}else{
			o.find("[src]").each(function(){
				setBind($(this));
			});
		}
	}
}


//�Ѹ� ���
jQuery.fn.slideImages = function(options){
	$(this).each(function(){
		var opt = {
			wrap           : $(this)
			,type          : 1
			,ul            : "ul"
			,btn_prev      : ".prev" //������ư
			,btn_pause     : ".pause" //�Ͻ�����
			,btn_next      : ".next" //������ư
			,navi          : ".navi" //�׺�
			,naviAuto      : true //��� �� ��ŭ �׺� �ڵ� ���� true | false
			,resizingCheck : false //â������ ����� �̹��� ũ�� �缳�� true | false
			,hoverClass    : "over"
			,navSwapOn     : "on" //�׺� �̹��� src replace on (ico_on.gif)
			,navSwapOff    : "off" //�׺� �̹��� src replace off (ico_off.gif)
			,eventHandler  : "mouseenter focus" //�׺� �̺�Ʈ �ڵ鷯
			,speed         : 300 //�����̵� �ӵ�
			,rollTimer     : 4000 //�Ѹ� ��� �ð� 1/1000��
			,navAlt        : "���" //�׺� �̹����϶� alt���� �ڵ����� this.navAlt�� + ���ڰ����� ��
			,viewNum       : 1 //�������� �̹��� ��
			,fixResizing   : null //resizingCheck : true �϶� ������¡ ������ �Ǵ� ������Ʈ
			,fnBefore      : null //�ʱ�, ������¡ �ɶ� �Լ� ����
			,fnMoveBefore      : null //�����̵� �� �Լ�
			,fnMoveAfter      : null //�����̵� �� �Լ�
			,skipNavi      : '<a href="">��� �׺���̼� �ǳʶ��</a>'
			,rollover      : false

			// * type ����
			// 1 - ���� ���������� �����̵�
			// 2 -
			// 3 - �̹����� fade in��out�ϸ鼭 ��ȯ
			// 4 - ���Ϸ� �����̵�
		}
		opt = $.extend(opt, options || {});

		(new slideImages()).init(function(){
			this.el.wrap       = opt.wrap;
			this.el.ul         = opt.ul;
			this.el.btn_prev   = opt.btn_prev;
			this.el.btn_pause  = opt.btn_pause;
			this.el.btn_next   = opt.btn_next;
			this.el.navi       = opt.navi;
			this.el.skipNavi   = null;
			this.type          = opt.type;
			this.naviAuto      = opt.naviAuto;
			this.resizingCheck = opt.resizingCheck;
			this.hoverClass    = opt.hoverClass;
			this.navSwapOn     = opt.navSwapOn;
			this.navSwapOff    = opt.navSwapOff;
			this.eventHandler  = opt.eventHandler;
			this.speed         = opt.speed;
			this.rollTimer     = opt.rollTimer;
			this.navAlt        = opt.navAlt;
			this.viewNum       = opt.viewNum;
			this.fnBefore      = opt.fnBefore;
			this.fnMoveBefore      = opt.fnMoveBefore;
			this.fnMoveAfter      = opt.fnMoveAfter;
			this.fixResizing   = opt.fixResizing;
			this.skipNavi      = opt.skipNavi;
			this.rollover      = opt.rollover;
		});
	});
	return this;
}

function slideImages(){
	this.el = {}
	this.el.li        = ">*";
	this.el.a         = "a";
	this.target       = "0";
	this.curr         = 0;
	this.pause       = false;
	this.mouseon     = false;
}
$.extend(slideImages.prototype,{
	bind : function(obj,evt,fn){
		var o = this;
		for(var s in obj){
			obj[s].bind(evt,fn);
		}
	}
	,firstSetting : function(){
		var o = this;
		function init(){
			if(typeof o.fnBefore === "function"){
				o.fnBefore();
			}
			o.size = o.el.li.outerWidth();
			o.vsize = o.el.li.outerHeight();
			o.totalSize = o.size*o.total+100;
			o.totalvSize = o.vsize*o.total+100;

			if(o.type === 1){
				o.el.ul.css({overflow:"hidden",position:"relative",width:o.totalSize,height:o.vsize});
				o.el.a.css({width:o.size,height:o.vsize});
				o.el.li.css({position:"absolute"});
				o.el.li.each(function(i){
					$(this).css("left",o.size*i);
				});
			}else if(o.type === 2){

			}else if(o.type === 3){
				o.el.ul.css({position:"relative",zIndex:0});
				o.el.li.css({display:"none",position:"absolute",zIndex:1,opacity:0});
				o.el.li.eq(0).css({display:"block",opacity:1});
			}else if(o.type === 4){
				o.el.ul.css({overflow:"hidden",position:"relative",width:o.size,height:o.totalvSize});
				o.el.a.css({width:o.size,height:o.vsize});
				o.el.li.css({position:"absolute"});
				o.el.li.each(function(i){
					$(this).css("top",o.vsize*i);
				});
			}
		}
		if(o.resizingCheck){
			if(o.fixResizing !== null){
				o.fixResizing = $(o.fixResizing);
			}else{
				o.fixResizing = o.el.wrap;
			}
			o.el.li.width($(o.fixResizing).outerWidth());
			$(window).resize(function(){
				o.el.li.width($(o.fixResizing).outerWidth());
				init();
				o.move();
			});
		}
		init();
	}
	,attachNavi : function(){
		var o = this;
		if(!o.el.navi.length) return;
		if(o.naviAuto){
			var html=o.el.navi.html();
			for(var i=1; i<o.total; i++){
				o.el.navi.append(html);
			}
		}
		if(o.el.navi.find("a,button").length){
			o.el.btn_navi=o.el.navi.find("a,button");
		}else{
			o.el.btn_navi=o.el.navi.find(">*");
		}
		o.el.navi.find("a").each(function(i){
			var aname = "____slideImages___"+$("*[id*='____slideImages___']").length+"0"+i;
			$(this).attr("href","#"+aname);
			o.el.a.eq(i).attr("id",aname);
		});
		o.el.navi.find("img").each(function(i){
			$(this).attr("alt",o.navAlt+" "+(i+1));
		});
		
		o.el.skipNavi.css({overflow:"hidden", position:"absolute", zIndex:"-1", left:"-9999px", width:"1px", height:"1px", fontSize:"0", lineHeight:"0", textIndent:"-9999px"}).attr("href","#"+o.el.a.eq(0).attr("id"));
	}
	,move : function(){
		var o = this;
		if(o.curr < 0) return;
		if(typeof o.fnMoveBefore === "function"){
			o.fnMoveBefore.apply(o);
		}
		var fnAfter = function(){
			if(typeof o.fnMoveAfter === "function"){
				o.fnMoveAfter.apply(o);
			}
		}
		if(o.type === 1){
			o.target=-o.curr*o.size;
			o.el.ul.stop().animate({marginLeft:o.target+"px"},o.speed,fnAfter);
			o.naviEffect();
		}else if(o.type === 2){
			o.target=-o.curr*o.size;
			o.el.ul.stop().animate({marginLeft:o.target+"px"},o.speed,fnAfter);
			o.naviEffect();
		}else if(o.type === 3){
			var obj,obj_prev;
			obj = o.el.li.eq(o.curr);
			obj_prev = o.el.li.filter(":visible");
			obj.css("zIndex",1);
			obj_prev.css("zIndex",0);
			$(obj_prev, obj_prev.find("img")).stop().animate({opacity:0},o.speed,function(){
				$(this).css("display","none");
			});
			$(obj, obj.find("img")).stop().css("display","block").animate({opacity:1},o.speed,fnAfter);
			o.naviEffect();
		}else if(o.type === 4){
			o.target=-o.curr*o.vsize;
			o.el.ul.stop().animate({marginTop:o.target+"px"},o.speed,fnAfter);
			o.naviEffect();
		}
	}
	,naviEffect : function(){
		if(!this.el.navi.length) return;
		var o = this;
		if(o.el.btn_navi.find("img").length){
			o.el.btn_navi.filter("."+o.hoverClass).find("img").imgSwap(o.navSwapOn,o.navSwapOff);
			o.el.btn_navi.eq(o.curr).find("img").imgSwap(o.navSwapOff,o.navSwapOn);
		}
		o.el.btn_navi.removeClass(o.hoverClass);
		o.el.btn_navi.eq(o.curr).addClass(o.hoverClass);
	}
	,rolling : function(time){
		var o=this;
		var timer;
		function action(){
			clearInterval(timer);
			if(o.pause) return;
			timer=setInterval(function(){
				if(o.curr+o.viewNum>=o.total){
					o.curr=0;
				}else{
					o.curr=(o.curr+1)%(o.el.li.length);
				}
				o.move();
			},time);
		}
		function clearTimer(){
			clearInterval(timer);
			clearInterval(timer);
		}
		o.el.wrap.mouseover(function(){
			clearTimer();
		});
		o.bind([o.el.wrap, o.el.a, o.el.btn_prev, o.el.btn_pause, o.el.btn_next],"focus",function(){
			clearTimer();
		})
		o.el.wrap.mouseleave(function(){
			action();
		});
		o.bind([o.el.wrap, o.el.a, o.el.btn_prev, o.el.btn_pause, o.el.btn_next],"blur",function(){
			action();
		})
		if(o.el.btn_navi){
			o.el.btn_navi.focus(function(){
				clearTimer();
			});
			o.el.btn_navi.blur(function(){
				action();
			});
		}
		action();
	}
	,imgReady : function(fn){
		var o,img,k;
		o = this;
		img = o.el.li.find("img:first")[0];
		k = 0;
		function chk(){
			if(++k>5){
				img = o.el.li.eq(1).find("img")[0];
			}
			if(img.complete){
				fn();
				return;
			}
			setTimeout(chk,100);
		}
		chk();
	}
	,init : function(fn){
		var o = this;
		if(typeof fn==="function"){
			fn.apply(o);
		}
		
		o.el.ul = o.el.wrap.find(o.el.ul);
		o.el.li = o.el.ul.find(o.el.li);
		o.el.a = o.el.li.find(o.el.a);
		o.el.btn_prev = o.el.wrap.find(o.el.btn_prev);
		o.el.btn_pause = o.el.wrap.find(o.el.btn_pause);
		o.el.btn_next = o.el.wrap.find(o.el.btn_next);
		o.el.navi = o.el.wrap.find(o.el.navi);
		o.total = o.el.li.length;

		o.el.navi.before($(o.skipNavi).addClass("skipRollingBnrNavi"));
		o.el.skipNavi = o.el.wrap.find(".skipRollingBnrNavi");
		
		o.attachNavi();
		o.imgReady(function(){
			o.firstSetting();
			o.move();

			o.el.btn_prev.bind("click",function(){
				if(o.curr<=0) return false;
				o.curr--;
				o.move();
				return false;
			});
			o.el.btn_next.bind("click",function(){
				if(o.curr+o.viewNum>=o.total) return false;
				o.curr++;
				o.move();
				return false;
			});
			o.el.btn_pause.bind("click",function(){
				o.pause = !o.pause;
				o.el.ul.stop();
				return false;
			});
			if(o.el.btn_navi){
				o.el.btn_navi.each(function(i){
					$(this).bind(o.eventHandler,function(){
						o.curr=i;
						o.move();
						if(o.eventHandler.indexOf("click") != -1){
							return false;
						}
					});
				});
			}
			o.el.a.bind("mousedown",function(){
				o.mouseon = true;
			});
			o.el.a.bind("mouseup",function(){
				o.mouseon = false;
			});
			o.el.a.each(function(i){
				$(this).bind("focus",function(){
					if(!o.mouseon){
						o.el.ul.stop();
						o.curr=i-o.viewNum+1;
						o.move();
						o.el.wrap.scrollLeft(0);
					}
				});
			});

			if(o.rollTimer){
				o.rolling(o.rollTimer);
			}
			
			if(o.rollover){
				o.el.a.each(function(i){
					$(this).bind("mouseenter focus",function(){
						$(this).find("img").imgSwap(o.navSwapOff,o.navSwapOn);
					});
					$(this).bind("mouseout blur",function(){
						$(this).find("img").imgSwap(o.navSwapOn,o.navSwapOff);
					});
				});
			}
		});
		return this;
	}
});

//��
jQuery.fn.tabUI = function(options){
	var opt = {
		tab : ".tabUI_tab" //��
		,con : ".tabUI_con" //������
		,tabSrcOn : "_on" //�̹��� replace src on (a_on.gif)
		,tabSrcOff : "" //�̹��� replace src off (a.gif)
		,tabHover : "on" //�� ������ Ŭ����
		,eventHandler : "mouseenter focus" //�̺�Ʈ�ڵ鷯
		,imgHover : true //�̹��� �¿��� true | false
		,href : false //click �̺�Ʈ�϶� href ���� true | false
		,fn : "" //�� Ȱ��ȭ�� �� ������ �Լ�
		,titAttr : "selected" //�� Ȱ��ȭ�� �� title �� ���� �߰��� ����
	};
	var opt = $.extend(opt, options || {});
	function init(wrap){
		var
		el = {
			tab : wrap.find(opt.tab)
			,con : wrap.find(opt.con)
			,currTab : null
		}
		,att = {
			title : []
		}
		,prevIdx = 1
		,currTit = "";
		el.tab.each(function(i){
			att.title.push($(this).attr("title"));
			$(this).bind(opt.eventHandler,function(){
				var o = $(this);
				if(opt.fn != ""){
					opt.fn(i);
				}
				el.currTab = el.tab.filter("."+opt.tabHover);
				if(opt.imgHover){
					el.currTab.find("img").imgSwap(opt.tabSrcOn,opt.tabSrcOff);
					if(o.find("img").length){
						$(this).find("img").imgSwap(opt.tabSrcOff,opt.tabSrcOn);
					}
				}
				if(att.title[prevIdx] === ""){
					el.currTab.removeAttr("title");
				}else{
					el.currTab.attr("title",att.title[prevIdx]);
				}
				if(!att.title[i]){
					currTit = opt.titAttr;
				}else{
					currTit = att.title[i] + " - " + opt.titAttr;
				}
				o.attr("title",currTit);
				el.tab.removeClass(opt.tabHover);
				o.addClass(opt.tabHover);
				el.con.hide();
				el.con.eq(i).show();
				prevIdx = i;
				if(!opt.href){
					return false;
				}
			});
		});

		el.tab.eq(0).trigger(opt.eventHandler.split(" ")[0]);

	}
	$(this).each(function(){
		init($(this));
	});
	return this;
}

jQuery.fn.GNB = function(){
	var o,d1,d1A,d1img,d2,d2A,d1A_on,d2A_on,d2_on,subMenuBg,timer;
	o = $(this);
	if(!o.length) return;
	d1 = o.find(".dep1");
	d1A = o.find(".dep1>a");
	d1img = o.find(".dep1>a img");
	d2 = o.find(".dep2");
	d2A = o.find(".dep2 a");
	subMenuBg=o.find(".subMenuBg");
	d1A_on=$(),d2A_on=$(),d2_on=$();

	//�ʱ�ȭ
	function init(){
		//activePage();
		reset();
	}
	//Ȱ��ȭ
	function activePage(){
		if(typeof getPageCode != "function") return;
		if(typeof getPageCode() != "string") return;
		var pageCode=getPageCode().split(" ");
		var dep1Code=parseInt(pageCode[0])-1;
		var dep2Code=parseInt(pageCode[1])-1;
		if(dep1Code<0) return;
		d1A_on = d1A.eq(dep1Code);
		if(dep2Code<0) return;
		d2A_on = d1A_on.next().find("a").eq(dep2Code);
		d2_on = d2A_on.parents("ul").eq(0);
	}

	function overEffect(){
		subMenuBg.show();
	}

	function outEffect(){
		subMenuBg.hide();
	}

	//ó�� ����
	function reset(){
		timer = setTimeout(function(){
			menuOn(d1A_on,d2_on);
			subOn(d2A_on);
		},50);
	}

	//1depth,2depth off
	function menuOff(){
		var curr_dep1 = d1A.filter(".on");
		curr_dep1.removeClass("on");
		d1img.each(function(){
			$(this).imgSwap("_on","");
		});
		d2.hide();
	}

	//1depth on
	function menuOn(el_dep1,el_dep2){
		menuOff();
		el_dep1.addClass("on");
		el_dep1.find("img").imgSwap("","_on");
		if(el_dep1.attr("class") == "on" && el_dep2.length){overEffect();}
		else{outEffect();}
		el_dep2.show();
	}

	//2depth on
	function subOn(el){
		d2A.filter(".on").find("img").imgSwap("_on","");
		el.find("img").imgSwap("","_on");
		d2A.removeClass("on");
		el.addClass("on");
	}

	//1depth mouseover
	d1A.each(function(i){
		$(this).bind("mouseover focus", function(){
			clearReset();
			var el_dep1 = $(this);
			var el_dep2 = $(this).parent().find(".dep2");
			menuOn(el_dep1,el_dep2);
		});
	});

	//2depth mouseover
	d2A.bind("mouseover focus", function(){
		clearReset();
		subOn($(this));
	});

	d1A.filter(":first").bind("blur", function(){
		reset();
	});
	d2A.filter(":last").bind("blur", function(){
		reset();
	});

	//ó�� ���·� ���� ���
	function clearReset(){
		clearTimeout(timer);
	}

	//������ ����� ó�� ���·� ����
	o.bind("mouseleave",function(){
		clearReset();
		reset();
	});

	init();
}

jQuery.fn.SNB = function(){
	var o, el
	o = $(this);
	el = {
		d1  : o.find("li.dep1")
		,d2 : o.find("ul.dep2")
	}
	var d1_active, d2_active, d3_active;
	d3_active = o.find(".active:last");
	d2_active = d3_active.parents("li.dep2").eq(0).find("a:first");
	d1_active = d3_active.parents("li.dep1").eq(0).find("a:first");
	/*
	pageCode = location.href.split("divT=")[1].substr(0,4);
	d1_code = parseInt(pageCode.substr(0,4))-1;
	d2_code = parseInt(pageCode.substr(2))-1;
	*/
	if(o.find(".unfold").length){
		el.d1.css("background","none");
		reset();
		return;
	}
	
	function reset(){
		menu(d1_active.parents("li.dep1").eq(0));
		d1_active.addClass("active1");
		d2_active.addClass("active");
		d3_active.addClass("active");
		el.d2.find("ul").each(function(){
			$(this).find("li:even").addClass("odd");
		});
	}
	function menu(obj){
		el.d1.filter(":not(.active)").removeClass("hover").find("ul.dep2").hide();
		obj.addClass("hover").find("ul.dep2").show();
	}
	el.d1.each(function(){
		var that = $(this);
		if(!that.find("ul").length || o.find(".unfold").length){
			that.css("background","none");
		}
		el.d1a = that.find("a:first");
		el.d1a.bind("mouseenter focus",function(){
			menu(that);
		});
	});
	/*
	o.bind("mouseleave",function(){
		reset();
	});
	*/
	reset();
}

//���޴� �ſ�ī�� ������ �Һ�
function layerShowHideUI(lyID){
	var btn,lyr,close,timer,clName;
	btn=$("a[href$="+lyID+"]");
	lyr=$("#"+lyID);
	clName="blind";
	close=lyr.find(".close");
	var lyrShow = function(){
		clearTimeout(timer);
		lyr.removeClass(clName);
	}
	var lyrHide = function(){
		lyr.addClass(clName);
	}
	btn.bind("mouseenter focus",lyrShow);
	btn.bind("mouseleave blur",function(){
		timer = setTimeout(lyrHide,300);
	});
	lyr.mouseover(lyrShow);
	lyr.find("*").focus(lyrShow);
	lyr.mouseleave(lyrHide);
	lyr.find("a:last").blur(lyrHide);
	close.click(function(){
		lyrHide();
		return false;
	});
};

jQuery.easing.easeInOutCubic = function (x, t, b, c, d) {
	if ((t/=d/2) < 1) return c/2*t*t*t + b;
	return c/2*((t-=2)*t*t + 2) + b;
}
jQuery.fn.scrollMenu = function(top){
	var el = $(this);
	var speed = 500;
	var top = parseInt(el.css("top"));
	function init(){
		var targetY=$(window).scrollTop()+top;
		el.stop().animate({top:targetY},speed,"easeInOutCubic");
	}
	init();
	$(window).scroll(function(){
		init();
	});
}


jQuery.fn.textPlaceholder = function() {
	$(this).each(function(){
		var that = this;

		var placeholder = that.getAttribute('defval');
		if(placeholder === null) return;
		var input = jQuery(that);

		if (that.value === '' || that.value == placeholder) {
			input.addClass('text-placeholder');
			that.value = placeholder;
		}

		input.focus(function(){
			if (input.hasClass('text-placeholder') || $.trim(that.value) === placeholder) {
				this.value = '';
				input.removeClass('text-placeholder')
			}
		});

		input.blur(function(){
			if (this.value === '') {
				input.addClass('text-placeholder');
				this.value = placeholder;
			} else {
				input.removeClass('text-placeholder');
			}
		});

		that.form && jQuery(that.form).submit(function(){
			if (input.hasClass('text-placeholder')) {
				that.value = '';
			}
		});
	});
}

