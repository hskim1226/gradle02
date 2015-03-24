<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>"연세대학교 대학원 입학원서 접수 시스템"</title>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/base.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/global_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/global_layout.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/page_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/old/page_layout.css"/>
    <link rel="stylesheet" href="${contextPath}/jquery-ui/jquery-ui.css"/>    
    <link rel="stylesheet" href="${contextPath}/jquery-ui/jquery-ui.structure.css"/>    
    <link rel="stylesheet" href="${contextPath}/jquery-ui/jquery-ui.theme.css"/>      
	<link rel="stylesheet" href="${contextPath}/jqgrid/css/ui.jqgrid.css"/>    

    <title></title>
</head>
<body>

<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
        </div>

        <h1>원서수정 대상자검색</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="">
                        <table summary="원서수정 대상자검색">
                            <caption>원서수정 대상자검색</caption>
                            <tbody>
                            <tr>
                                <th><label for="korName">성명</label></th>
                                <td><input type="text" class="Ltext" id="korName" name="korName" size="15" /></td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="image" class="Limage" src="${contextPath}/img/admin/repository/btn_search.gif" /></a>
                    </form>
                </div>
            </div>
        </div>
        <div id="LblockPageSubtitle01" class="LblockPageSubtitle">
            <h2>지원자 선택</h2>
        </div>

        <div id="LblockDetail01" class="LblockDetail">
            <table summary="지원 기본정보">
                <caption>지원 기본정보</caption>
                <tbody>
                <tr>
                    <th>수험번호</th>
                    <td>15-A-1101111</td>
                    <th>캠퍼스</th>
                    <td>서울캠퍼스</td>
                    <th>지원단위</th>
                    <td>토목환경공학과</td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
	    
<content tag="local-script">


    <script src="${contextPath}/jqgrid/js/jquery-1.11.0.min.js"></script>
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


</content>
</body>
</html>
