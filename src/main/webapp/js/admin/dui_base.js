
dui = new Object();
/**
 * Cross Browsing을 위한 라이브러리
 */
dui.CB = new Object();

/* 브라우저 정보 알아내기(Browser Sniffing) */
// convert all characters to lowercase to simplify testing
dui.CB.Browser = new Object();
dui.CB.Browser.agent          = navigator.userAgent.toLowerCase();
// *** BROWSER VERSION ***
// Note: On IE5, these return 4, so use isIE5up to detect IE5.
dui.CB.Browser.majorVersion   = parseInt(navigator.appVersion);
dui.CB.Browser.minorVersion   = parseFloat(navigator.appVersion);
// Note: Opera and WebTV spoof Navigator
dui.CB.Browser.isNav          = ((dui.CB.Browser.agent.indexOf('mozilla')!=-1) &&
	(dui.CB.Browser.agent.indexOf('spoofer')==-1) && 
	(dui.CB.Browser.agent.indexOf('compatible') == -1) && (dui.CB.Browser.agent.indexOf('opera')==-1) && 
	(dui.CB.Browser.agent.indexOf('webtv')==-1) && (dui.CB.Browser.agent.indexOf('hotjava')==-1));
dui.CB.Browser.isNav2         = (dui.CB.Browser.isNav && (dui.CB.Browser.majorVersion == 2));
dui.CB.Browser.isNav3         = (dui.CB.Browser.isNav && (dui.CB.Browser.majorVersion == 3));
dui.CB.Browser.isNav4         = (dui.CB.Browser.isNav && (dui.CB.Browser.majorVersion == 4));
dui.CB.Browser.isNav4up       = (dui.CB.Browser.isNav && (dui.CB.Browser.majorVersion >= 4));
dui.CB.Browser.isNavOnly      = (dui.CB.Browser.isNav && ((dui.CB.Browser.agent.indexOf(";nav") != -1) || (dui.CB.Browser.agent.indexOf("; nav") != -1)) );
dui.CB.Browser.isNav6         = (dui.CB.Browser.isNav && (dui.CB.Browser.majorVersion == 5));
dui.CB.Browser.isNav6up       = (dui.CB.Browser.isNav && (dui.CB.Browser.majorVersion >= 5));
dui.CB.Browser.isGecko        = (dui.CB.Browser.agent.indexOf('gecko') != -1);
dui.CB.Browser.isIE           = ((dui.CB.Browser.agent.indexOf("msie") != -1) && (dui.CB.Browser.agent.indexOf("opera") == -1));
dui.CB.Browser.isIE3          = (dui.CB.Browser.isIE && (dui.CB.Browser.majorVersion < 4));
dui.CB.Browser.isIE4          = (dui.CB.Browser.isIE && (dui.CB.Browser.majorVersion == 4) && (dui.CB.Browser.agent.indexOf("msie 4")!=-1) );
dui.CB.Browser.isIE4up        = (dui.CB.Browser.isIE && (dui.CB.Browser.majorVersion >= 4));
dui.CB.Browser.isIE5          = (dui.CB.Browser.isIE && (dui.CB.Browser.majorVersion == 4) && (dui.CB.Browser.agent.indexOf("msie5.0")!=-1) );
dui.CB.Browser.isIE5_5        = (dui.CB.Browser.isIE && (dui.CB.Browser.majorVersion == 4) && (dui.CB.Browser.agent.indexOf("msie5.5") !=-1));
dui.CB.Browser.isIE5up        = (dui.CB.Browser.isIE && !dui.CB.Browser.isIE3 && !dui.CB.Browser.isIE4);
dui.CB.Browser.isIE5_5up      = (dui.CB.Browser.isIE && !dui.CB.Browser.isIE3 && !dui.CB.Browser.isIE4 && !dui.CB.Browser.isIE5);
dui.CB.Browser.isIE6          = (dui.CB.Browser.isIE && (dui.CB.Browser.majorVersion == 4) && (dui.CB.Browser.agent.indexOf("msie 6.")!=-1) && (dui.CB.Browser.agent.indexOf("msie 7")==-1));
dui.CB.Browser.isIE6up        = (dui.CB.Browser.isIE && !dui.CB.Browser.isIE3 && !dui.CB.Browser.isIE4 && !dui.CB.Browser.isIE5 && !dui.CB.Browser.isIE5_5);
dui.CB.Browser.isIE7          = (dui.CB.Browser.isIE && (dui.CB.Browser.majorVersion == 4) && (dui.CB.Browser.agent.indexOf("msie 7.")!=-1));
dui.CB.Browser.isIE7up        = (dui.CB.Browser.isIE && !dui.CB.Browser.isIE3 && !dui.CB.Browser.isIE4 && !dui.CB.Browser.isIE5 && !dui.CB.Browser.isIE5_5 && !dui.CB.Browser.isIE6);
// KNOWN BUG: On AOL4, returns false if IE3 is embedded browser
dui.CB.Browser.isAOL          = (dui.CB.Browser.agent.indexOf("aol") != -1);
dui.CB.Browser.isAOL3         = (dui.CB.Browser.isAOL && dui.CB.Browser.isIE3);
dui.CB.Browser.isAOL4         = (dui.CB.Browser.isAOL && dui.CB.Browser.isIE4);
dui.CB.Browser.isAOL5         = (dui.CB.Browser.agent.indexOf("aol 5") != -1);
dui.CB.Browser.isAOL6         = (dui.CB.Browser.agent.indexOf("aol 6") != -1);
dui.CB.Browser.isOpera        = (dui.CB.Browser.agent.indexOf("opera") != -1);
dui.CB.Browser.isOpera2       = (dui.CB.Browser.agent.indexOf("opera 2") != -1 || dui.CB.Browser.agent.indexOf("opera/2") != -1);
dui.CB.Browser.isOpera3       = (dui.CB.Browser.agent.indexOf("opera 3") != -1 || dui.CB.Browser.agent.indexOf("opera/3") != -1);
dui.CB.Browser.isOpera4       = (dui.CB.Browser.agent.indexOf("opera 4") != -1 || dui.CB.Browser.agent.indexOf("opera/4") != -1);
dui.CB.Browser.isOpera5       = (dui.CB.Browser.agent.indexOf("opera 5") != -1 || dui.CB.Browser.agent.indexOf("opera/5") != -1);
dui.CB.Browser.isOpera5up     = (dui.CB.Browser.isOpera && !dui.CB.Browser.isOpera2 && !dui.CB.Browser.isOpera3 && !dui.CB.Browser.isOpera4);
dui.CB.Browser.isWebtv       = (dui.CB.Browser.agent.indexOf("webtv") != -1);
dui.CB.Browser.isTVNavigator = ((dui.CB.Browser.agent.indexOf("navio") != -1) || (dui.CB.Browser.agent.indexOf("navio_aoltv") != -1));
dui.CB.Browser.isAOLTV        = dui.CB.Browser.isTVNavigator;
dui.CB.Browser.isHotJava      = (dui.CB.Browser.agent.indexOf("hotjava") != -1);
dui.CB.Browser.isHotJava3     = (dui.CB.Browser.isHotJava && (dui.CB.Browser.majorVersion == 3));
dui.CB.Browser.isHotJava3up   = (dui.CB.Browser.isHotJava && (dui.CB.Browser.majorVersion >= 3));

/* 이벤트 관련 */
dui.CB.addEventHandler = function (oTarget, sEventType, fnHandler) {
    if (oTarget.addEventListener) {
        oTarget.addEventListener(sEventType, fnHandler, false);
    } else if (oTarget.attachEvent) {
        oTarget.attachEvent("on" + sEventType, fnHandler);
    } else {
        oTarget["on" + sEventType] = fnHandler;
    }
};
        
dui.CB.removeEventHandler = function (oTarget, sEventType, fnHandler) {
    if (oTarget.removeEventListener) {
        oTarget.removeEventListener(sEventType, fnHandler, false);
    } else if (oTarget.detachEvent) {
        oTarget.detachEvent("on" + sEventType, fnHandler);
    } else { 
        oTarget["on" + sEventType] = null;
    }
};

dui.CB.getEvent = function(aEvent) {
	if (window.event) return window.event;
	else return aEvent;
};
dui.CB.stopPropagation = function(aEvent) {
	aEvent = dui.CB.getEvent(aEvent);
	if (aEvent.eventPhase)
		aEvent.stopPropagation();
	else 
		aEvent.cancelBubble = true;
};

/* get window inner size */
dui.CB.getWindowRect = function () {
	var rect = new Object();
	var width, height;
	if (self.innerHeight) { // IE 외 모든 브라우저
		width = self.innerWidth;
		height = self.innerHeight;
	}
	else if (document.documentElement &&	document.documentElement.clientHeight) { // Explorer 6 Strict 모드
		width = document.documentElement.clientWidth;
		height = document.documentElement.clientHeight;
	}
	else if (document.body) { // 다른 IE 브라우저
		width = document.body.clientWidth;
		height = document.body.clientHeight;
	}
	
	rect.width = width;
	rect.height = height;
	return rect;
}

dui.CB.getInnerWidth = function () {
	return dui.CB.getWindowRect().width;
}

dui.CB.getInnerHeight = function () {
	return dui.CB.getWindowRect().height;
}

dui.CB.getMousePosition = function (aEvent) {
	var posx = 0;
	var posy = 0;
	aEvent = dui.CB.getEvent(aEvent);
	
	if (aEvent.pageX || aEvent.pageY) { // pageX/Y 표준 검사
		posx = aEvent.pageX;
		posy = aEvent.pageY;
	}
	else if (aEvent.clientX || aEvent.clientY) { //clientX/Y 표준 검사 Opera
		posx = aEvent.clientX;
		posy = aEvent.clientY;
		if (dui.CB.Browser.isIE) { // IE 여부 검사
			posx += document.body.scrollLeft;
			posy += document.body.scrollTop;
		}
	}
}

/**
 * 유용한 Element Prototype을 위한 라이브러리
 */
// Element insert
if (typeof HTMLElement!="undefined" && !HTMLElement.prototype.insertAdjacentElement) { // IE 이외 브라우저
	HTMLElement.prototype.insertAfter = function(newElement, targetElement) {
		var parent = targetElement.parentNode;
		if (parent.lastChild == targetElement) {
			parent.appendChild(newElement);
		} else {
			parent.insertBefore(newElement, this.nextSibling);
		}
	}
	HTMLElement.prototype.insertAdjacentElement = function(where,parsedNode){ 
			switch (where){ 
					case 'beforeBegin': 
							this.parentNode.insertBefore(parsedNode,this) 
							break; 
					case 'afterBegin': 
							this.insertBefore(parsedNode,this.firstChild); 
							break; 
					case 'beforeEnd': 
							this.appendChild(parsedNode); 
							break; 
					case 'afterEnd': 
							if (this.nextSibling) this.parentNode.insertBefore(parsedNode,this.nextSibling); 
							else this.parentNode.appendChild(parsedNode); 
							break; 
			} 
	} 
	HTMLElement.prototype.insertAdjacentHTML = function(where,htmlStr) { 
			var r = this.ownerDocument.createRange(); 
			r.setStartBefore(this); 
			var parsedHTML = r.createContextualFragment(htmlStr); 
			this.insertAdjacentElement(where,parsedHTML) 
	}     
	HTMLElement.prototype.insertAdjacentText = function(where,txtStr){ 
			var parsedText = document.createTextNode(txtStr) 
			this.insertAdjacentElement(where,parsedText) 
	} 
}
window.addOnload = function (func) {
	var oldOnload = window.onload;
	if (typeof window.onload != "function") {
		window.onload = func;
	} else {
		window.onload = function() {
			oldOnload();
			func();
		}
	}
}


/**
 * Element(Prototype.js) 확장
 */
Element.addMethods({
	getContentDimensions : function (element) {
		element = $(element); 

	  // prototype.js의 getDimensions()와 같지만 offset -> client로 바꿨음 
		var dimensions;
		var display = $(element).getStyle('display');
    if (display != 'none' && display != null) // Safari bug
      dimensions = {width: element.clientWidth, height: element.clientHeight};
		else {
	    var els = element.style;
	    var originalVisibility = els.visibility;
	    var originalPosition = els.position;
	    var originalDisplay = els.display;
	    els.visibility = 'hidden';
	    els.position = 'absolute';
	    els.display = 'block';
	    var originalWidth = element.clientWidth;
	    var originalHeight = element.clientHeight;
	    els.display = originalDisplay;
	    els.position = originalPosition;
	    els.visibility = originalVisibility;
			dimensions = {width: originalWidth, height: originalHeight};
		}

		// padding을 제한다. 단, ie의 경우를 대비해 padding은 px로만 줄 것
		var left = parseInt(element.getStyle("padding-left"));
		var right = parseInt(element.getStyle("padding-right"));
		var top = parseInt(element.getStyle("padding-top"));
		var bottom = parseInt(element.getStyle("padding-bottom"));
		var padding = {
			left : isNaN(left) ? 0 : left,
			right : isNaN(right) ? 0 : right,
			top : isNaN(top) ? 0 : top,
			bottom : isNaN(bottom) ? 0 : bottom
		}

		dimensions.width -= padding.left + padding.right;
		dimensions.height -= padding.top + padding.bottom;
		return dimensions;
	},

  getContentHeight: function(element) {
    return $(element).getContentDimensions().height;
  },

  getContentWidth: function(element) {
    return $(element).getContentDimensions().width;
  }
});

/**
 * dui.Common
 */

dui.getLeft = function (obj){
	var left = 0;
	if (obj.offsetParent){
		while(obj.offsetParent){
			left += obj.offsetLeft;
			obj = obj.offsetParent;
		}
	}	else if(obj.x) left = obj.x;
	 
	return left;
};
dui.getTop = function (obj){
	var top = 0;
	if(obj.offsetParent){
		while(obj.offsetParent){
			top += obj.offsetTop;
			obj = obj.offsetParent;
		}
	} else if(obj.y) top = obj.y;
	
	return top;
};
dui.alertInfo = function (message) {
	alert("[DevOn UI Info] "+message);
}
dui.alertWarning = function (message) {
	alert("[DevOn UI Warning] "+message);
}
dui.alertError = function (message) {
	alert("[DevOn UI Error] "+message);
}

dui.Log = {
	isOpenning : false,
	msgBuf : new Array(),
	
	initHTML : '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">' +
		'<html xmlns="http://www.w3.org/1999/xhtml">\n' +
		'<head>\n' +
		'<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />\n' +
		'<title>Logger (DevOn UI Base Logger)</title>\n' +
		'<script type="text/javascript">\n' +
		'// <![CDATA[\n' +
		'var LOG_STOP = false;\n' +
		'function showMenu() {\n' +
		'	var menuBar = document.getElementById("menuBar");\n' +
		'	if (self.pageYOffset) y = self.pageYOffset; \n' +
		'	else if (document.documentElement &&	document.documentElement.scrollTop) y = document.documentElement.scrollTop; \n' +
		'	else if (document.body) y = document.body.scrollTop; \n' +
		'	menuBar.style.top = y + "px";\n' +
		'	menuBar.style.display = "block";\n' +
		'}\n' +
		'function hideMenu() {\n' +
		'	var menuBar = document.getElementById("menuBar");\n' +
		'	menuBar.style.display = "none";\n' +
		'}\n' +
		'function stopLog() { \n' +
		'	LOG_STOP = true; \n' +
		'	var content = document.getElementById("content"); \n' +
		'	writeLog("<span style=\'color:#f55\'>log stopped.</span>"); \n' +
		'	var stopBtn = document.getElementById("stopBtn"); \n' +
		'	stopBtn.value = "시  작"; \n' +
		'	stopBtn.onclick = startLog; \n' +
		'}\n' +
		'function startLog() { \n' +
		'	LOG_STOP = false; \n' +
		'	var content = document.getElementById("content"); \n' +
		'	writeLog("<span style=\'color:#55f\'>log started.</span>"); \n' +
		'	var stopBtn = document.getElementById("stopBtn"); \n' +
		'	stopBtn.value = "중  지"; \n' +
		'	stopBtn.onclick = stopLog; \n' +
		'}\n' +
		'function clearLog() {\n' +
		'	var content = document.getElementById("content"); \n' +
		'	content.innerHTML = ""; \n' +
		'}\n' +
		'function checkMouse(e) {\n' +
		'	var aEvent = window.event ? window.event : e;\n' +
		'	if (aEvent.clientY < 25) {\n' +
		'		showMenu();\n' +
		'	//alert(Position);\n' +
		'	} else {\n' +
		'		hideMenu();\n' +
		'	}\n' +
		'}\n' +
		'function init() {\n' +
		'	document.onmousemove = checkMouse;\n' +
		'} \n' +
		'function writeLog(message) { \n' +
		'	var content = document.getElementById("content"); \n' +
		'	var pTag = document.createElement("p"); \n' +
		'	pTag.innerHTML = getTime()+message; \n' +
		'	var newLineTag = document.createElement("p"); \n' +
		'	newLineTag.innerHTML = "&nbsp;"; \n' +
		'	if (content.lastChild) content.removeChild(content.lastChild); \n' +
		'	content.appendChild(pTag); \n' +
		'	content.appendChild(newLineTag); \n' +
		'	window.scrollTo(0, document.body.clientHeight); \n' +
		'}; \n' +
		'function getTime() { \n' +
		'	var date = new Date(); \n' +
		'	var timeStr = "["+date.toLocaleTimeString()+"] ";	 \n' +
		'	return timeStr; \n' +
		'}; \n' +
		'// ]]>\n' +
		'</script>\n' +
		'<style type="text/css">\n' +
		'	* {margin:0; padding:0;}\n' +
		'	body {font:75%/1.5em Dotum,돋움,sans-serif; color:#646656;background-color:#FFFFF6;}\n' +
		'	#menuBar {display:none;position:absolute;width:100%;height:24px;text-align:right;padding-top:3px;border-bottom:1px solid #ccc;background:#fff;}\n' +
		'	#menuBar input {border: 1px outset #ccc;margin-right:5px; height:20px;}\n' +
		'</style>\n' +
		'</head>\n' +
		'<body onload="init()">\n' +
		'<div id="menuBar">\n' +
		'<input type="button" id="stopBtn" value="중&nbsp;&nbsp;지" onclick="stopLog()" /><input type="button" value="지우기" onclick="clearLog()" /> \n' +
		'</div>\n' +
		'<br />' +
		'<div id="content"></div>' +
		'</body>\n' +
		'</html>\n',
	
	println : function (message, escape) {
		if (escape) message = message.escapeHTML();
		if (dui.Log.window && !dui.Log.window.closed && dui.Log.window.writeLog) {
			if (!dui.Log.window.LOG_STOP) dui.Log.window.writeLog(message);
		}	else if (dui.Log.isOpenning) { // 첫 창이 뜨기전에 로그가 순간 여러번 호출될 경우에 대비
			dui.Log.msgBuf[dui.Log.msgBuf.length] = message;
		} else {
			var callback = function(pe) {
				if (dui.Log.window.document) {
					var logWindow = dui.Log.window;
					logWindow.document.write(dui.Log.initHTML);
					logWindow.document.close();
					logWindow.writeLog(message);
					if (dui.Log.msgBuf) {
						for (var i=0, len=dui.Log.msgBuf.length;i<len;++i) {
							logWindow.writeLog(dui.Log.msgBuf[i]);
						}
						dui.Log.msgBuf = null;
					}
					pe.stop();

					dui.Log.isOpenning = false;
				}
			};
			dui.Log.isOpenning = true;
			dui.Log.window = window.open("", "DEVONUI_LOG_WINDOW", "height=400,width=400,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes");
			try {
				if (dui.Log.window.writeLog) // 이미 창이 떠있을 경우는 새창이 뜨면서 발생하는 시간지연이 없으므로 PE를 시작하지 않는다.
					dui.Log.window.writeLog(message);
				else {
					dui.Log.openPE = new PeriodicalExecuter(callback, 0.3);
				}
			} catch (e) {
				dui.alertWarning("다른 브라우저에서 이미 로그창을 띄웠습니다. 현재 로그창을 먼저 닫아주십시요.");
			}
		}
	}
};
dui.log = dui.Log.println;
















