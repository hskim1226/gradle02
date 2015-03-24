
var _leftMenu, _leftMenuHtml;
var _slideMenu;
function changeLeftMenuStyle(aEvent) { 
	_leftMenu.innerHTML = _leftMenuHtml;
	var menuObj = ""; 
	
	if(aEvent) menuObj = aEvent.target;
	else menuObj = event.srcElement;

	var changeEls = document.getElementById("LblockLeftMenuStyle").getElementsByTagName("img");
	for(var i=0;i<changeEls.length; i++) { 
	    if (changeEls[i] == menuObj) {		
			switch (i) {
				case 0:
					_leftMenu.className = "LslideMenu";
					break;
				case 1:
					_leftMenu.className = "LtreeMenu";
					break;
			}
		}
	}
	initLeftMenu();
}

function initPage() {
	_leftMenu = $("LblockLeftMenu");
	_leftMenuHtml = _leftMenu.innerHTML;
	initTopMenu();
	initLeftMenu();
	
	var LblockLeftMenuStyle = document.getElementById("LblockLeftMenuStyle");
	if (LblockLeftMenuStyle) {
		var changeEls = document.getElementById("LblockLeftMenuStyle").getElementsByTagName("img");
		for (var i = 0; i < changeEls.length; i++) {
			changeEls[i].onclick = changeLeftMenuStyle;
		}
	}
	
	initTreeBlock();
}

function initTopMenu() {
	_topMenu = new dui.hhmenu.HHMenu();
	var root = document.getElementById("LblockTopMenu").getElementsByTagName("ul")[0];
	_topMenu.init(root);
}

function initLeftMenu() {
	if (_leftMenu.hasClassName("LslideMenu")) initSlideMenu();
	else if (_leftMenu.hasClassName("LtreeMenu")) initTreeMenu();
}

function initSlideMenu() { 
	var root = _leftMenu.getElementsByTagName("ul")[0];
	_slideMenu = dui.SlideMenu.makeSlideMenu(root);
	_slideMenu.beforeOpen = function(menu) { 
		menu.firstChild.className = "Lopen";
	}
	_slideMenu.afterClose = function(menu) { 
		menu.firstChild.className = "Lclosed";
	}
} 

function initTreeMenu() {
  treeMenu = new dui.tree.Tree(); 	
	treeMenu.imagePath = "../images/treemenu_images/";
	treeMenu.enableMoveNode(false);
	var root = document.getElementById("LblockLeftMenu").getElementsByTagName("ul")[0];
	treeMenu.init(root);
	//var selectedNode = treeMenu.selectNode("0>1>1");
} 

function initTreeBlock() {
	var treeBlocks = document.getElementsByClassName("LblockTree");
	for (var i=0; i<treeBlocks.length; i++) {
	  var tree = new dui.tree.Tree(); 	
		tree.imagePath = "../images/tree_images/";
		tree.enableMoveNode(false);
		var root = treeBlocks[i].getElementsByTagName("ul")[0];
		tree.init(root);
	}
}
function doSomething() {}

window.onload = initPage;
