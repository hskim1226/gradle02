<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><decorator:title default="연세대학교 대학원 입학 신청 시스템"/></title>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="${contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${contextPath}/css/admin/base.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/global_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/global_layout.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/page_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/page_layout.css"/>
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
            <div id="LblockTopLogo">
                <a href="#"><img src="${contextPath}/img/admin/topLogo.gif" alt="사이트 상단 로고" /></a>
            </div>
            <div id="LblockTopMenu">
                <ul class="Lclear">
                    <li class="Lidx1"><span><a href="#"></a></span>
                        어쩌구
                    </li>
                    <li class="Llast"><span><a href="#"></a></span>
                        저쩌구
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div id="LblockLeft">

    <h1>지원현황</h1>

    <div>
        <ul>
            <li class="Lfirst"><span><a href="#">마감 : D-3 일</a></span></li>
            <li class="Llast"><span><a href="#">현재지원자 : 100명</a></span></li>
            <li class="Llast"><span><a href="#">미결재지원자 : 10명</a></span></li>
        </ul>
    </div>

    <div id="LblockLeftTitle">
        <div id="LblockLeftMenuStyle">
            <img src="${contextPath}/img/admin/slideMenu.gif" alt="슬라이드메뉴" />
            <img src="${contextPath}/img/admin/treeMenu.gif" alt="트리메뉴" />
        </div>
        <h1>담당자 메뉴</h1>

    </div>
    <div id="LblockLeftMenu" class="LslideMenu">
        <ul>
            <li class="Lfirst">
                <span><a href="#">통계</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="${contextPath}/admin/stats/category">전형별 지원현황</a></span></li>
                    <li class="Llast"><span><a href="${contextPath}/admin/stats/daily">일자별 지원현황</a></span></li>
                </ul>
            </li>
            <li class="Lidx1">
                <span><a href="#">지원자관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="${contextPath}/admin/search/applicants">지원자검색</a></span></li>
                    <li class="Llast"><span><a href="${contextPath}/admin/stats/unpaid">미결제자현황</a></span></li>
                </ul>
            </li>
            <li class="Lidx2">
                <span><a href="#">지원변경/취소관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="${contextPath}/admin/modification/list">변경처리조회</a></span></li>
                    <li class="Lidx1"><span><a href="${contextPath}/admin/modification/request">원서수정</a></span></li>
                    <li class="Lidx2"><span><a href="${contextPath}/admin/modification/unit">지원단위변경</a></span></li>
                    <li class="Llast"><span><a href="${contextPath}/admin/cancel/application">지원취소</a></span></li>
                </ul>
            </li>
            <li class="Lidx3"><span><a href="#">데이터다운로드</a></span></li>
            <li class="Lidx4">
                <span><a href="#">합격자관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="#">합격자검색</a></span></li>
                    <li class="Llast"><span><a href="#">합격자일괄등록</a></span></li>
                </ul>
            <li class="Lidx5"><span><a href="#">전형료정산</a></span></li>
            <li class="Lidx6">
                <span><a href="#">기준정보관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="#">모집학과 관리</a></span></li>
                    <li class="Llast"><span><a href="#">전형료 관리</a></span></li>
                </ul>
            </li>
            <li class="Lidx7">
                <span><a href="#">사용자계정관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="#">사용자관리</a></span></li>
                    <li class="Llast"><span><a href="#">권한관리</a></span></li>
                </ul>
            </li>
            <li class="Llast"><span><a href="#">공지사항</a></span></li>
        </ul>
    </div>

    <div id="LblockBanner01">
    </div>

</div>
<decorator:body />
<%--JS--%>
<script src="${contextPath}/js/admin/prototype.js"></script>
<script src="${contextPath}/js/admin/dui_base.js"></script>
<script src="${contextPath}/js/admin/dui_effect.js"></script>
<script src="${contextPath}/js/admin/dui_dragndrop.js"></script>
<script src="${contextPath}/js/admin/dui_hhmenu.js"></script>
<script src="${contextPath}/js/admin/dui_tree.js"></script>
<script src="${contextPath}/js/admin/dui_slidemenu.js"></script>
<script src="${contextPath}/js/admin/common.js"></script>
<decorator:getProperty property="page.local-script"/>
</body>
</html>