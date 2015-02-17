/**
 * @(#) dui_hhmenu.js version 1.0
 *
 *  Copyright(저작권) Do Not Erase This Comment!!! (이 주석문을 지우지 말것)
 *
 *  Do Not re-distribute with-out permission. especially out-side of LG-CNS.
 *  허가 없이 재배포 해서는 안된다. 특히 LG-CNS의 외부로 유출을 하여서는 안된다.
 *
 * AUTHORS LIST       E-MAIL  
 * jaehyun lim    jhylim@lgcns.com
 * yongseok choi  korsuk@lgcns.com
 */
dui.hhmenu = {};
dui.hhmenu.HHMenu = function() {};
dui.hhmenu.HHMenu.prototype = {
	rootUL : null,
	currentMenu : null,
	
	init : function (ulObj) {
		if (typeof(ulObj) == "string") ulObj = document.getElementById(ulObj);
		this.rootUL = $(ulObj);
		var menus = this.rootUL.childElements();
		for (var i=0; i<menus.length; i++) {
			var childUL = menus[i].getElementsByTagName("ul")[0];
			menus[i].onmouseover = this.onMouseOver.bind(this, childUL);
			menus[i].onmouseout = this.onMouseOut.bind(this, childUL);
			if (childUL) {
				this.adjustPosition(childUL);
				childUL.onmouseover = this.onMouseOver.bind(this, childUL);
				childUL.onmouseout = this.onMouseOut.bind(this, childUL);
			}
		}
		this.currentMenu = menus[0].getElementsByTagName("ul")[0];
	},
	
	adjustPosition : function (childUL) {
		childUL.style.visibility = "hidden";
		childUL.style.display = "block";
		this.setULWidth(childUL);
		var offset = this.rootUL.offsetWidth - childUL.offsetLeft - childUL.offsetWidth;
		if (offset < 0) childUL.style.left = childUL.offsetLeft + offset + "px";
		childUL.style.visibility = "visible";
		childUL.style.display = "none";
	},
	
	setULWidth : function (childUL) {
		var liList = childUL.getElementsByTagName("li");
		var width = 0;
		for (var i=0; i<liList.length; i++) {
			var li = $(liList[i]);
			width += li.offsetWidth+parseInt(li.getStyle("margin-left"))+parseInt(li.getStyle("margin-right"));
		}
		childUL.style.width = width+20+"px"; // firefox에서는 포지셔닝된 부모보다 width가 커지지 못해 아래로 떨어지므로 width를 지정해주어야 함. 20은 여유분.
	},
	
	onMouseOver : function (menu) {
		this.hideMenu(this.currentMenu);
		this.showMenu(menu);
		this.currentMenu = menu;
	},
	
	onMouseOut : function (menu) {
		this.hideMenu(this.currentMenu);
	},
	
	showMenu : function (menu) {
		if (menu) menu.style.display = "block";
	},
	
	hideMenu : function (menu) {
		if (menu) menu.style.display = "none";
	}
}
