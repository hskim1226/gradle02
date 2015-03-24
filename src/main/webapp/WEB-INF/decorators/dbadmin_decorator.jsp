<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>

<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><decorator:title default="연세대학교 대학원 입학원서 접수 시스템"/></title>

    <script src="${contextPath}/js/html5shiv.min.js"></script>
    <script src="${contextPath}/js/respond.min.js"></script>
    <script src="${contextPath}/jqgrid/js/jquery-1.11.0.min.js"></script>

    <link rel="stylesheet" href="${contextPath}/css/admin/old/base.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/global_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/global_layout.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/page_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/page_layout.css"/>
    <link rel="stylesheet" href="${contextPath}/jquery-ui/jquery-ui.css"/>    
    <link rel="stylesheet" href="${contextPath}/jquery-ui/jquery-ui.structure.css"/>    
    <link rel="stylesheet" href="${contextPath}/jquery-ui/jquery-ui.theme.css"/>      
	<link rel="stylesheet" href="${contextPath}/jqgrid/css/ui.jqgrid.css"/>    

    <decorator:head />
</head>
<body>

<div id="LblockSkipLink">
    <p><a href="#LblockTopMenu">주메뉴 바로가기</a></p>
    <p><a href="#LblockLeftMenu">서브메뉴 바로가기</a></p>
    <p><a href="#LblockMain">콘텐츠 바로가기</a></p>
</div>

<div id="LblockWrapper">
    <div id="LblockHeader">
        <div id="LblockHeaderIn">
            <div id="LblockTopMenu">
                <ul class="Lclear">
                    <li class="Lidx1"><span><a href="#"></a></span></li>
                    <li class="Llast"><span><a href="${contextPath}/j_spring_security_logout.do">sign out</a></span></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div id="LblockLeft">
    <div id="LblockLeftTitle">
        <h1>DB 관리 메뉴</h1>
    </div>
    <div id="LblockLeftMenu" class="LslideMenu">
        <ul>
            <li class="Lfirst">
                <span><a href="#">입시요강 관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="${contextPath}/dbadmin/adms">입학전형 관리</a></span></li>
                    <li class="Lidx1"><span><a href="${contextPath}/dbadmin/adms_cors">전형별 모집과정 관리</a></span></li>
                    <li class="Llast"><span><a href="${contextPath}/dbadmin/adms_dept">전형별 모집학과 관리</a></span></li>
                </ul>
            </li>
         </ul>
     </div>
 </div>

<decorator:body />

<script src="${contextPath}/jquery-ui/jquery-ui.min.js"></script>    
<script src="${contextPath}/jqgrid/js/jquery.jqGrid.min.js"></script>    
<script src="${contextPath}/jquery-ui/i18n/grid.locale-kr.js"></script> 
<script src="${contextPath}/js/jquery.bpopup.min.js"></script>
<script src="${contextPath}/js/admin/old/prototype.js"></script>
<script src="${contextPath}/js/admin/old/dui_base.js"></script>
<script src="${contextPath}/js/admin/old/dui_effect.js"></script>
<script src="${contextPath}/js/admin/old/dui_dragndrop.js"></script>
<script src="${contextPath}/js/admin/old/dui_hhmenu.js"></script>
<script src="${contextPath}/js/admin/old/dui_tree.js"></script>
<script src="${contextPath}/js/admin/old/dui_slidemenu.js"></script>
<script src="${contextPath}/js/admin/old/common.js"></script>
<script src="${contextPath}/js/json2.js"></script>

<decorator:getProperty property="page.local-script"/>
</body>
</html>