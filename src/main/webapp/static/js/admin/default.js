var Browser = { a : navigator.userAgent.toLowerCase() }
Browser = {
  ie : /*@cc_on true || @*/ false,
  ie6 : Browser.a.indexOf('msie 6') != -1,
  ie7 : Browser.a.indexOf('msie 7') != -1,
  ie8 : Browser.a.indexOf('msie 8') != -1,
  opera : !!window.opera,
  safari : Browser.a.indexOf('safari') != -1,
  safari3 : Browser.a.indexOf('applewebkit/5') != -1,
  mac : Browser.a.indexOf('mac') != -1,
  chrome : Browser.a.indexOf('chrome') != -1,
  firefox : Browser.a.indexOf('firefox') != -1
}
   
// 기본 Zoom
var nowZoom = 100;
// 최대 Zoom
var maxZoom = 200;
// 최소 Zoom
var minZoom = 80;
   
// 화면크기 확대
var jsBrowseSizeUp = function() {
  if( Browser.chrome ) {
    if( nowZoom < maxZoom ) {
      nowZoom += 10; // 10 = 25%씩 증가
      document.getElementById('content').style.zoom = nowZoom + "%";
    } else{
      alert('최대 확대입니다.');
    }
  } else if( Browser.opera ) {
    alert('오페라는 화면크기 기능을 지원하지 않습니다.\n브라우저 내의 확대/축소 기능을 이용하시기 바랍니다.');
  } else if( Browser.safari || Browser.safari3 || Browser.mac ) {
    alert('사파리, 맥은 화면크기 기능을 지원하지 않습니다.\n브라우저 내의 확대/축소 기능을 이용하시기 바랍니다.');
  } else if( Browser.firefox ) {
    alert('파이어폭스는 화면크기 기능을 지원하지 않습니다.\n브라우저 내의 확대/축소 기능을 이용하시기 바랍니다.');
  } else {
    if( nowZoom < maxZoom ) {
      nowZoom += 10; //10 = 25%씩 증가
      document.getElementById('content').style.position = "relative";
      document.getElementById('content').style.zoom = nowZoom + "%";
    } else {
      alert('최대 확대입니다.');
    }
  }
};
   
// 화면크기 축소
var jsBrowseSizeDown = function() {
  if( Browser.chrome ) {
    if( nowZoom < maxZoom ) {
      nowZoom -= 10; // 10 = 25%씩 증가
      document.getElementById('content').style.zoom = nowZoom + "%";
    } else {
      alert('최대 확대입니다.');
    }
  } else if( Browser.opera ) {
    alert('오페라는 화면크기 기능을 지원하지 않습니다.\n브라우저 내의 확대/축소 기능을 이용하시기 바랍니다.');
  } else if( Browser.safari || Browser.safari3 || Browser.mac  ) {
    alert('사파리, 맥은 화면크기 기능을 지원하지 않습니다.\n브라우저 내의 확대/축소 기능을 이용하시기 바랍니다.');
  } else if( Browser.firefox ) {
    alert('파이어폭스는 화면크기 기능을 지원하지 않습니다.\n브라우저 내의 확대/축소 기능을 이용하시기 바랍니다.');
  } else {
    if( nowZoom < maxZoom ) {
      nowZoom -= 10; //10 = 25%씩 증가
      document.getElementById('content').style.position = "relative";
      document.getElementById('content').style.zoom = nowZoom + "%";
    } else {
      alert('최대 축소입니다.');
    }
  }
};

// 화면크기 원래대로(100%)
var jsBrowseSizeDefault = function() {
  nowZoom = 100;
  document.getElementById('content').style.zoom = nowZoom + "%";
};

// 상단 메인메뉴
function displaySub(id) {
	for(i=1 ; i<=7 ; i++) {
		var img = document.getElementById("menu"+i).getElementsByTagName("img").item(0);
		if( img.src.indexOf("_on.") > -1 ) img.src = img.src.replace("_on.png", ".png");
	
		var sub = document.getElementById("lnb_depth"+i)
	
		if( sub ) {
			sub.style.display="none"

			var imgTag = sub.getElementsByTagName("img");

			for( var j = 0; j < imgTag.length ; j++ ) {
				var imgName = imgTag[j].src;
				if( imgName.indexOf("_on.") > -1 ) imgTag[j].src = imgName.replace("_on.png",".png");
			}
		}
	}
    //메인메뉴 롤오버 (만약 롤오버 사용안할려면비활성화)
	imgName = document.getElementById("menu"+id).getElementsByTagName("img").item(0).src;
	if( imgName.indexOf("_on.") < 0 ) {
		document.getElementById("menu"+id).getElementsByTagName("img").item(0).src = imgName.replace(".png","_on.png");
	}
	
    //서브메뉴 롤오버 (만약 롤오버 사용안할려면비활성화)
	if(document.getElementById("lnb_depth"+id)) document.getElementById("lnb_depth"+id).style.display="block";
}



// 탭메뉴1 (class)
function tabMenu(id) {
	for(i=1 ; i<=2 ; i++) {
		var sub = document.getElementById("tabLIst"+i)
		var sub2 = document.getElementById("tab"+i)

		if( sub ) {
			sub.style.display = "none";
			sub2.className = "board_tit";
		}
	}
    
	//메인메뉴 롤오버 (사용안할시 비활성화)
	var cName = document.getElementById("tab"+id).className;
	if( cName.indexOf(" on") < 0 ) {
		document.getElementById("tab"+id).className = "board_tit on";
	}
	
    //서브메뉴 롤오버 (사용안할시 비활성화)
	if(document.getElementById("tabLIst"+id)) document.getElementById("tabLIst"+id).style.display="block";
}

// 캘린더메뉴 (class)
function calenderMenu(id) {
	for(i=1 ; i<=5 ; i++) {
		var sub = document.getElementById("calender_area"+i)
		var sub2 = document.getElementById("calender_tab"+i)

		if( sub ) {
			sub.style.display = "none";
			sub2.className = "";
		}
	}
    
	//메인메뉴 롤오버 (사용안할시 비활성화)
	var cName = document.getElementById("calender_tab"+id).className;
	if( cName.indexOf(" on") < 0 ) {
		document.getElementById("calender_tab"+id).className = "on";
	}
	
    //서브메뉴 롤오버 (사용안할시 비활성화)
	if(document.getElementById("calender_area"+id)) document.getElementById("calender_area"+id).style.display="block";
}



// 탭메뉴2 (image)
function tabMenu2(id) {
	for(i=1 ; i<=2 ; i++) {
		var img = document.getElementById("tab"+i).getElementsByTagName("img").item(0);
		if( img.src.indexOf("_on.") > -1 ) img.src = img.src.replace("_on.png", ".png");
	
		var sub = document.getElementById("tabLIst"+i)
	
		if( sub ) {
			sub.style.display="none"

			var imgTag = sub.getElementsByTagName("img");

			for( var j = 0; j < imgTag.length ; j++ ) {
				var imgName = imgTag[j].src;
				if( imgName.indexOf("_on.") > -1 ) imgTag[j].src = imgName.replace("_on.png",".png");
			}
		}
	}
    
	//메인메뉴 롤오버 (사용안할시 비활성화)
	imgName = document.getElementById("tab"+id).getElementsByTagName("img").item(0).src;
	if( imgName.indexOf("_on.") < 0 ) {
		document.getElementById("tab"+id).getElementsByTagName("img").item(0).src = imgName.replace(".png","_on.png");
	}
	
    //서브메뉴 롤오버 (사용안할시 비활성화)
	if(document.getElementById("tabLIst"+id)) document.getElementById("tabLIst"+id).style.display="block";
}



// 탭메뉴3 (text)
function main_mouseover(data) {
  var obj1 = document.getElementById('tab01');
  var obj2 = document.getElementById('tab02');
  var obj3 = document.getElementById('tab03');
  var obj4 = document.getElementById('tab04');
  var obj1_list = document.getElementById('tab01_list');
  var obj2_list = document.getElementById('tab02_list');
  var obj3_list = document.getElementById('tab03_list');
  var obj4_list = document.getElementById('tab04_list');
  var obj = document.getElementById(data);
  var obj_list = document.getElementById(data+'_list');
  obj1.className = "";
  obj2.className = "";
  obj3.className = "";
  obj4.className = "";
  obj1_list.style.display = "none";
  obj2_list.style.display = "none";
  obj3_list.style.display = "none";
  obj4_list.style.display = "none";
  obj_list.style.display = "block";
  obj.className = "on";
}



// 레이어 보이기/감추기 (visibility)
// <a onmouseover="MM_showHideLayers('layer1','','show',';layer2','','hide');" onfocus="this.onmouseover();">이미지</a>
function MM_showHideLayers1() { //v9.0
  var i,p,v,obj,args=MM_showHideLayers2.arguments;
  for (i=0; i<(args.length-2); i+=3) 
  with (document) if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
	if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
	obj.visibility=v; }
}



// 레이어 보이기/감추기 (display)
function MM_showHideLayers2() { //v9.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) 
  with (document) if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
	if (obj.style) { obj=obj.style; v=(v=='show')?'block':(v=='hide')?'none':v; }
	obj.display=v; }
}



// 레이어 보이기/감추기 (다른버전)
// 사용예시(보이기)
// <a onclick="displayOn('selectbox_depart');">타이틀 이미지</a>
// <div id="selectbox_depart">컨텐츠내용</div>

// 사용예시(감추기)
// <a onclick="displayOff('selectbox_depart');">이미지</a>
// <div id="selectbox_depart">컨텐츠내용</div>

// displayOff('idName','idName'); displayOn('idName','idName'); //인수 개수에 상관없다.
/***************************************************************************************************/

function displayOn() {
var i,j,a=displayOn.arguments;
for(i=0;i<a.length;i++) {
obj = document.getElementById(a[i]);
if (obj) { obj.style.display = "block"; }
}
}

function displayOff() {
var i,j,a=displayOff.arguments;
for(i=0;i<a.length;i++) {
obj = document.getElementById(a[i]);
if (obj) { obj.style.display = "none"; }
}
}


// 이미지롤오버 (마우스오버, 마우스아웃)
// <a href="#" onmouseout="imageOut(this)" onmouseover="imageOver(this)">이미지</a>

// 탭메뉴 마우스 오버/아웃 스크립트
// <a href="#" onmouseover="imageOver(this);return false;" id="tab_over1">이미지</a>
/***************************************************************************************************/ 

function imageOver(anchor) {
	for(i=1 ; i<=3 ; i++) {	
		var sub = document.getElementById("tab_over"+i);   
		if( sub ) {
			var imgTag = sub.getElementsByTagName("img");
			for( var j = 0; j < imgTag.length ; j++ ) {
				var imgName = imgTag[j].src;
				if( imgName.indexOf("_on.") > -1 ) imgTag[j].src = imgName.replace("_on.png",".png");
			}
		}
	}

	var imgEl = anchor.getElementsByTagName("img").item(0);
	if( imgEl.src.indexOf("_on.png") < 0 ) imgEl.src = imgEl.src.replace(".png", "_on.png");
}

function imageOut(anchor) {
	var imgEl = anchor.getElementsByTagName("img").item(0);
	if( imgEl.src.indexOf("_on.png") > -1 ) imgEl.src = imgEl.src.replace("_on.png", ".png");
}



// 로그인
function login_ok(){
  if (document.all.m_id.value == "") {
	alert("아이디를 입력해 주세요.");
	document.all.m_id.focus();
	return false;
  }
  if (document.all.m_password.value == "") {
	alert("비밀번호를 입력해 주세요.");
	document.all.m_password.focus();
	return false;
  }
  return true;
}



// 팝업 띄우기 
// <a onclick="popup_open('popup.asp','293','300')" title="새창">팝업창</a>
function popup_open(url, width, height) {
  window.open(url, 'popup', 'left=0, top=0, width='+width+', height='+height+', toolbar=no, menubar=no, status=no, scrollbars=auto, resizable=no')
}



// 셀렉트박스 바로가기 링크 (새창이동)
/*
<form name="selectbox" action="index.html" target="proc" method="post" onsubmit="return select_gogo();">
<select name="site_url"><option value="http://www.snlib.net">통합도서관</option></select>
</form>
*/

function select_gogo() {
  var val = document.selectbox.site_url.value;
  window.open(val,'','');
  return false;
}

// 통합검색
function search_header(){
  if (document.tmform_header.searchName.value == "") {
	alert("검색어를 입력해 주세요.");
	document.tmform_header.searchName.focus();
	return false;
  }
  return true;
}