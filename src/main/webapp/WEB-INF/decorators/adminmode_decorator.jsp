<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
    <title>Gradnet</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <%--

    <script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/html5shiv.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/css/admin/base.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/css/admin/global_style.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/css/admin/global_layout.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/css/admin/page_style.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/css/admin/page_layout.css"/>



    --%>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jquery-ui/jquery-ui.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jquery-ui/jquery-ui.structure.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jquery-ui/jquery-ui.theme.css"/>
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jqgrid/css/ui.jqgrid.css"/>


    <link type="text/css" rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/css/admin/default.css" />
    <link rel="stylesheet" href="<spring:eval expression="@app.getProperty(\"path.static\")"/>/css/admin/font-awesome.min.css">

    <script type="text/javascript" src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/jquery-1.9.0.js"></script>
    <script type="text/javascript" src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/jquery-ui-1.10.3.custom.js"></script>

    <script type="text/javascript" src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/default.js"></script>

    <%--
    <script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jqgrid/js/jquery-1.11.0.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jquery-ui/jquery-ui.min.js"></script>

    --%>

    <script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jqgrid/js/jquery.jqGrid.min.js"></script>
    <script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/jquery-ui/i18n/grid.locale-kr.js"></script>
    <decorator:head />
</head>
<body>
<div id="wrap">
<div id="header">
    <div class="logo">
        <h1><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/h1_logo.png" alt="gradnet" /></h1>
    </div>
    <div class="date_info"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/img_icon1.png" alt="" /> <strong>원서접수기간 :</strong> 2014-04-07 09:30 ~ 2014-04-10 16:30</div>
    <div class="gnb">
        <ul>
            <li><a href="${contextPath}/admin/main"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_home.png" alt="Home" /></a></li>
            <li><a href="${contextPath}/logout"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_logout.png" alt="Logout" /></a></li>
        </ul>
    </div>
</div>
<!-- /header -->
<div id="container">
<div class="snb">
    <div class="user_info">
        <p class="name"><strong>${adminInfo.adminName}</strong>님 반갑습니다.</p>
    </div>
    <!-- /user_info -->

    <div id="recru" class="snb_box1">
        <h2 class="lang-kor-malgun"><span>모집구분</span></h2>
        <div class="box_con">
        <c:forEach var="applCntList" items="${applCntList}" varStatus="status">

            <li><span style="width: 142px; float: left" >${applCntList.codeVal}</span><span class="number"  style ="float:left; width:40px; color:#ffd800; text-align:right"><strong>${applCntList.cnt}</strong> 명</span></li>
        </c:forEach>

        <!-- /목록 리스트 -->
        </div>
        <a href="#open" title="목록 펼치기" id="recru_select" class="btn_more"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_menu1.png" alt="목록"></a> </div>
    <!-- /recru -->

    <div id="state" class="snb_box1">
        <h2 class="lang-kor-malgun"><span>접수현황</span></h2>
        <div class="box_con">
            <ul>
                <li><span class="tit" id="currCnt">현재접수인원</span><span class="number"><strong>${adminInfo.currCnt}</strong> 명</span></li>
                <li><span class="tit" id="unpaidCnt">미결제인원</span><span class="number"><strong>${adminInfo.unpaidCnt}</strong> 명</span></li>
                <li><span class="tit" id="unpaidCnt">원서작성중인원</span><span class="number"><strong>${adminInfo.uncmplCnt}</strong> 명</span></li>
                <li><span class="tit" id="changeCnt">지원취소/변경인원</span><span class="number"><strong>${adminInfo.changeCnt}</strong> 명</span></li>
            </ul>
        </div>
        <a href="#" title="새로고침" class="btn_more"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/btn_menu1.png" alt="새로고침"></a> </div>
    <!-- /state -->

    <div class="menu_wrap">
        <ul class="dep1">
            <li><a href="#" id="menu_wrap1_click">통계</a>
                <ul class="dep2" id="menu_wrap1">
                    <li><a href="${contextPath}/admin/stats/basicCntByDept">학과별 지원현황</a></li>
                    <li><a href="${contextPath}/admin/stats/detailCntByDept">학과별 상세 지원현황</a></li>
                    <li><a href="${contextPath}/admin/stats/basicCntByAdms">전형별 지원현황</a></li>
                    <li><a href="${contextPath}/admin/stats/unpaidCnt">미결제자현황</a></li>
                    <li><a href="${contextPath}/admin/stats/basicCntByWeekly">최근일자별 지원현황</a></li>
                </ul>
            </li>
            <li><a href="#" id="menu_wrap2_click">지원자검색</a>
                <ul class="dep2" id="menu_wrap2">
                    <li><a href="${contextPath}/admin/search/applicants/deptSearch">지원자검색(학과)</a></li>
                    <li><a href="${contextPath}/admin/search/applicants/idSearch">지원자검색(수험번호)</a></li>
                    <li><a href="${contextPath}/admin/search/applicants/nameSearch">지원자검색(성명)</a></li>
                </ul>
            </li>
            <li><a href="#" id="menu_wrap3_click">지원변경/취소관리</a>
                <ul class="dep2" id="menu_wrap3">
                    <li><a href="${contextPath}/admin/modification/changeList">변경처리조회</a></li>
                    <li><a href="${contextPath}/admin/modification/changeInfo">지원자정보수정</a></li>
                    <li><a href="${contextPath}/admin/modification/changeUnit">지원단위변경</a></li>
                    <li><a href="${contextPath}/admin/modification/cancelAppl">지원취소</a></li>
                    <li><a href="${contextPath}/admin/modification/changeEtc">기타변경요청</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /menu_wrap -->

</div>
<!-- /snb -->
    <decorator:body />
</div>
<!-- /container -->

<div id="footer"> COPYRIGHT (C) 2015. <strong>APEXSOFT</strong>. All Rights Reserved. </div>
</div>
<!-- /wrap -->


<%--
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/jquery.bpopup.min.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/prototype.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/dui_base.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/dui_effect.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/dui_dragndrop.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/dui_hhmenu.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/dui_tree.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/dui_slidemenu.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/admin/common.js"></script>
<script src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/js/json2.js"></script>
--%>
<script type="text/javascript">

    $(function(){
        $("#recru_select").click(function(){
            $(".box_con_list").toggle();
        });
    });

    $(function(){
        $("#box_con_list1").click(function(){
            var box_con_list = $("#box_con_list1").html();
            $("#box_con_title").html(box_con_list);
            $(".box_con_list").toggle();
        });
        $("#box_con_list2").click(function(){
            var box_con_list = $("#box_con_list2").html();
            $("#box_con_title").html(box_con_list);
            $(".box_con_list").toggle();
        });
        $("#box_con_list3").click(function(){
            var box_con_list = $("#box_con_list3").html();
            $("#box_con_title").html(box_con_list);
            $(".box_con_list").toggle();
        });
        $("#box_con_list4").click(function(){
            var box_con_list = $("#box_con_list4").html();
            $("#box_con_title").html(box_con_list);
            $(".box_con_list").toggle();
        });
        $("#box_con_list5").click(function(){
            var box_con_list = $("#box_con_list5").html();
            $("#box_con_title").html(box_con_list);
            $(".box_con_list").toggle();
        });
    });

    $(function(){
        $("#menu_wrap1").hide();
        $("#menu_wrap2").hide();
        $("#menu_wrap3").hide();

        $("#menu_wrap1_click").click(function(){
            $("#menu_wrap1").toggle();
        });
        $("#menu_wrap2_click").click(function(){
            $("#menu_wrap2").toggle();
        });
        $("#menu_wrap3_click").click(function(){
            $("#menu_wrap3").toggle();
        });
    });
</script>

<decorator:getProperty property="page.local-script"/>
</body>
</html>