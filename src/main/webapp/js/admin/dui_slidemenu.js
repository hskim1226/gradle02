/**
 * @(#) dui_slidemenu.js version 1.0
 *
 *  Copyright(저작권) Do Not Erase This Comment!!! (이 주석문을 지우지 말것)
 *
 *  Do Not re-distribute with-out permission. especially out-side of LG-CNS.
 *  허가 없이 재배포 해서는 안된다. 특히 LG-CNS의 외부로 유출을 하여서는 안된다.
 *
 * AUTHORS LIST       E-MAIL  
 * jaehyun lim    jhylim@lgcns.com
 */
dui.SlideMenu = {};

dui.SlideMenu = function () {};
dui.SlideMenu.makeSlideMenu = function (elId) {
	var el = $(elId);
	Object.extend(el, dui.SlideMenu.prototype);
	el.init();
	return el;
};
dui.SlideMenu.prototype = {
	menus : null,
	currentObj : null,
	clickedObj : null,
	currentMenu : null,
	acceleration : -0.8,
	isEffectRunning : false,
	setOpenMenu : null,
	
	beforeOpen : function(menuObj) {},
	afterOpen : function(menuObj) {},
	beforeClose : function(menuObj) {},
	afterClose : function(menuObj) {},

	init : function() {
		this.menus = this.childElements();
		this.setMenu(this.menus);
		this.initMenu();
	},
	
	setMenu : function(menus) { 
		for (var i=0; i<menus.length; i++) {
			var UL = menus[i].getElementsByTagName("ul")[0];
			menus[i].UL = $(UL);
			if (UL) {
				this.setMenu(UL.childElements());
				for(var j=0;j<UL.childElements().length;j++) {
					if(this.getChildUL(UL.childElements()[j])) {
						UL.childElements()[j].depth = "3";
					}
				}				
				menus[i].state = "closed";
			}
			else 
				menus[i].state = "none";
			
			var eventSrc = menus[i].getElementsByTagName("span")[0];
			if (!eventSrc) eventSrc = menus[i];
			eventSrc.onclick = this.toggle.bind(this, menus[i]);
		}
	},	
	
	initMenu : function() { 
		var linkObjs = this.getElementsByTagName("li");  
	    var currentMenu = this.currentMenu;	
		for(var i=0;i<linkObjs.length;i++) { 	
		    if(linkObjs[i].className.indexOf("Lcurrent") > -1) {
			     var selectMenu = linkObjs[i];
				 if(selectMenu.parentNode.parentNode.parentNode.parentNode.tagName=="LI") { 
				     selectMenu.parentNode.parentNode.parentNode.parentNode.UL.height = selectMenu.parentNode.parentNode.parentNode.parentNode.UL.getHeight();
					 selectMenu.parentNode.parentNode.parentNode.parentNode.UL.style.display="block";
					 selectMenu.parentNode.parentNode.parentNode.parentNode.state="open";
					 selectMenu.parentNode.parentNode.parentNode.parentNode.childElements()[0].className = "Lopen";	
					 selectMenu.parentNode.parentNode.UL.height = selectMenu.parentNode.parentNode.parentNode.parentNode.UL.getHeight();
					 selectMenu.parentNode.parentNode.UL.style.display="block";
					 selectMenu.parentNode.parentNode.state="open"; 
					 selectMenu.parentNode.parentNode.childElements()[0].className = "Lopen";
				 } else if(selectMenu.parentNode.parentNode.tagName=="LI") {
				 	 selectMenu.parentNode.parentNode.UL.height = selectMenu.parentNode.parentNode.parentNode.parentNode.UL.getHeight();
					 selectMenu.parentNode.parentNode.UL.style.display="block";
					 selectMenu.parentNode.parentNode.state="open";
					 selectMenu.parentNode.parentNode.childElements()[0].className = "Lopen";
				 } 
				 this.currentObj = selectMenu;
				 currentMenu=selectMenu;
			}	
		
			linkObjs[i].onclick = function(aEvent){					
				if (aEvent) aEvent.stopPropagation();
				else window.event.cancelBubble = true;
								
				if (this.className && this.className.indexOf("Lcurrent") > -1) {}  
				else if (currentMenu) {
					this.className += " Lcurrent";
					currentMenu.className = currentMenu.className.substr(0, currentMenu.className.length - 9);
				} else this.className += " Lcurrent";
			   	
				currentMenu = this;
			}	 			
		} 
	},
	
	selectNode : function(selectMenus) {
		var openMenusIndexes = selectMenus.split(">");
		if (openMenusIndexes.length == 1) {
			this.childElements()[openMenusIndexes[0]].className += " Lcurrent";	
		} else if(openMenusIndexes.length == 2) {
			this.childElements()[openMenusIndexes[0]].UL.childElements()[openMenusIndexes[1]].className +=" Lcurrent";	
		} else if(openMenusIndexes.length == 3) { 		
			this.childElements()[openMenusIndexes[0]].UL.childElements()[openMenusIndexes[1]].UL.childElements()[openMenusIndexes[2]].className +=" Lcurrent";
		}
		this.initMenu();
	},
	 
	toggle : function(obj) { 			
	    this.clickedObj = obj;

		var menu = obj;  
		if (!menu.UL) { 
			this.closeOpenMenu();
			this.currentObj = obj;
			return;
		}
				
		if (menu.state == "closed") {  
			var callback = function() { 
				this.open(menu);
				menu.state = "open"; 
				this.currentObj = menu; 
			} 
			this.closeOpenMenu(callback.bind(this));			
		}
		else { 
			this.close(menu);
			menu.state = "closed";
		}
	},
	
	closeOpenMenu : function (callback) { 	 	
        if(this.currentObj) { 
			if(this.currentObj.parentNode == this.clickedObj.parentNode) { 
				if (this.currentObj.state == "open") { 
					this.close(this.currentObj, callback);
					this.currentObj.state = "closed";
				} else if(callback) callback();
			} else if(this.currentObj.parentNode.parentNode.parentNode.parentNode.parentNode == this.clickedObj.parentNode) {
				this.close(this.currentObj.parentNode.parentNode.parentNode.parentNode, callback);
				this.currentObj.parentNode.parentNode.parentNode.parentNode.state = "closed";
			} else if(this.currentObj.parentNode.parentNode.parentNode == this.clickedObj.parentNode) { 
				this.close(this.currentObj.parentNode.parentNode, callback);
				this.currentObj.parentNode.parentNode.state = "closed";
			} else { 
			    var closeObj = this.closeAnother(this.clickedObj);
                if(closeObj) {
					this.close(closeObj);
					closeObj.state = "closed";
				}
							
				if (callback) callback();
				else {			 
					
				}
			}
		} else {
			if(callback) callback();
		}		
	},
	
	open : function (menu, height) {
		var UL = menu.UL;		
		var twoDepthObjs = "";
		var elHeight = 0;
		if(this.clickedObj.depth != "3") { 
			twoDepthObjs = this.getChildUL(this.clickedObj).childElements(); 
			for(var i=0;i<twoDepthObjs.length;i++) {
				if(twoDepthObjs[i].depth=="3" && twoDepthObjs[i].state == "open") {
				    if (isNaN(parseInt(this.getChildUL(twoDepthObjs[i]).style.height))) { 
						elHeight = elHeight + this.getChildUL(twoDepthObjs[i]).height; 
					}
					else {
						elHeight = elHeight + parseInt(this.getChildUL(twoDepthObjs[i]).style.height);
					}
				}
			}
		}		
	
		if(!height) height=0;
		
		if (!UL.height) { 
			var dimensions = UL.getDimensions();
			UL.height = dimensions.height;
		}  
		var effect = new dui.effect.Scale(0, UL.height + height + elHeight);
		effect.acceleration = this.acceleration;
		effect.nodisplay = true;
		if (!height) 
			effect.initHeight = 0; 
		else 
			effect.initHeight = 0;
		var slideMenu = this;
		effect.callback = function(){
			slideMenu.isEffectRunning = false;
			slideMenu.afterOpen(menu); 
		}
		this.isEffectRunning = true; 
		this.beforeOpen(menu);
		effect.run(UL);
				
       if(menu.depth=="3") { 
           this.open(menu.parentNode.parentNode, UL.height); 
		   menu.parentNode.parentNode.state = "open";	
	   }
	},
	
	close : function (menu, callback, height) { 
		var UL = menu.UL;
		if(!UL) {
			if (callback) callback();
			return;
		} 		
		var objHeight = height ? height : UL.getHeight();			
		
		if(menu.depth=="3" && menu.state=="open") {	
           this.close(menu.parentNode.parentNode,callback,objHeight); 
	    }

		var effect = new dui.effect.Scale(0, 0-objHeight);
		effect.acceleration = this.acceleration;
		effect.nodisplay = true;
		var slideMenu = this;
		effect.callback = function() { 
			slideMenu.isEffectRunning = false;
			if(menu.state=="closed") slideMenu.afterClose(menu);
			if (callback) callback();
		}
		this.isEffectRunning = true; 
		this.beforeClose(menu);
		effect.run(UL);		
	},
	
	setCallback : function(effect, callback) {
	}, 	
		
	getChildUL : function(obj) { 
		var isChildUL = false;
		var childElements = obj.childElements();
		if (childElements[childElements.length-1].tagName == "UL") return childElements[childElements.length-1];
		else return null;
	},
	
	closeAnother : function(obj) {
        var closeObj = '';
		var els = obj.parentNode.childElements();
		
		for(var i=0;i<els.length;i++) { 
			if(els[i].state=="open") closeObj=els[i];
		}
				
		return closeObj;
	}
}