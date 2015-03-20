<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><decorator:title default="연세대학교 대학원 입학원서 접수 시스템"/></title>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${contextPath}/js/html5shiv.min.js"></script>
    <script src="${contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <script src="${contextPath}/jqgrid/js/jquery-1.11.0.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/admin/base.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/global_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/global_layout.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/page_style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/admin/page_layout.css"/>
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
            <div id="LblockTopLogo">
                <a href="#"><img src="${contextPath}/img/admin/topLogo.gif" alt="사이트 상단 로고" /></a>
            </div>
            <div id="LblockTopMenu">
                <ul class="Lclear">
                    <li class="Lidx1"><span><a href="#"></a></span>
                   
                    </li>
                    <li class="Llast"><span><a href="${contextPath}/j_spring_security_logout.do">sign out</a></span>

                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div id="LblockLeft">



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
                    <li class="Llast"><span><a href="${contextPath}/admin/search/unpaid">미결제자현황</a></span></li>
                    <li class="Llast"><span><a href="${contextPath}/admin/stats/daily">최근일자별 지원현황</a></span></li>

                  </ul>
              </li>
              <li class="Lidx1">
                  <span><a href="#">지원자검색</a></span>
                  <ul>
                      <li class="Lfirst"><span><a href="${contextPath}/admin/search/applicants/deptSearch">지원자검색(학과)</a></span></li>
                      <li class="Lidx1"><span><a href="${contextPath}/admin/search/applicants/idSearch">지원자검색(수험번호)</a></span></li>
                      <li class="Llast"><span><a href="${contextPath}/admin/search/applicants/nameSearch">지원자검색(성명)</a></span></li>

                  </ul>
              </li>
              <li class="Lidx2">
                  <span><a href="#">지원변경/취소관리</a></span>
                  <ul>
                      <li class="Lfirst"><span><a href="${contextPath}/admin/modification/changeList">변경처리조회</a></span></li>
                      <li class="Lidx1"><span><a href="${contextPath}/admin/modification/changeInfo">지원자정보수정</a></span></li>
                      <li class="Lidx1"><span><a href="${contextPath}/admin/modification/changeUnit">지원단위변경</a></span></li>
                      <li class="Llast"><span><a href="${contextPath}/admin/modification/cancelAppl">지원취소</a></span></li>
                      <li class="Llast"><span><a href="${contextPath}/admin/modification/changeEtc">기타변경요청</a></span></li>
                 </ul>
             </li>
            <%--
            <li class="Lidx3"><span><a href="${contextPath}/admin/data/download">데이터다운로드</a></span></li>

            <li class="Lidx4">
                <span><a href="#">합격자관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="#">합격자검색</a></span></li>
                    <li class="Llast"><span><a href="#">합격자일괄등록</a></span></li>
                </ul>

            <li class="Lidx4"><span><a href="${contextPath}/admin/data/payment">전형료정산</a></span></li>

            <li class="Llast">
                <span><a href="#">기준정보관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="${contextPath}/admin/guideline/deptManage">모집학과 관리</a></span></li>
                    <li class="Lidx2"><span><a href="${contextPath}/admin/guideline/langMandManage">어학성적 조건관리</a></span></li>
                    <li class="Lidx2"><span><a href="${contextPath}/admin/guideline/docMandManage">제출서류 조건관리</a></span></li>
                    <li class="Llast"><span><a href="${contextPath}/admin/guideline/feeManage">전형료 관리</a></span></li>
                </ul>
            </li>
            --%>
            <%--
            <li class="Lidx7">
                <span><a href="#">사용자계정관리</a></span>
                <ul>
                    <li class="Lfirst"><span><a href="#">사용자관리</a></span></li>
                    <li class="Llast"><span><a href="#">권한관리</a></span></li>
                </ul>
            </li>
            <li class="Lidx8"><span><a href="#">공지사항</a></span></li>
            --%>
            <%--   <li class="Llast"><span><a href="${contextPath}/admin/qna/list">Q&A</a></span></li> --%>
         </ul>
     </div>

     <div id="LblockBanner01">
     </div>

 </div>
 <decorator:body />
 <%--JS--%>

<%-- <script src="${contextPath}/js/jquery.min.js"></script> --%>

<script src="${contextPath}/jquery-ui/jquery-ui.min.js"></script>    
<script src="${contextPath}/jqgrid/js/jquery.jqGrid.min.js"></script>    
<script src="${contextPath}/jquery-ui/i18n/grid.locale-kr.js"></script> 
<script src="${contextPath}/js/jquery.bpopup.min.js"></script>
<script src="${contextPath}/js/admin/prototype.js"></script>
<script src="${contextPath}/js/admin/dui_base.js"></script>
<script src="${contextPath}/js/admin/dui_effect.js"></script>
<script src="${contextPath}/js/admin/dui_dragndrop.js"></script>
<script src="${contextPath}/js/admin/dui_hhmenu.js"></script>
<script src="${contextPath}/js/admin/dui_tree.js"></script>
<script src="${contextPath}/js/admin/dui_slidemenu.js"></script>
<script src="${contextPath}/js/admin/common.js"></script>
<script src="${contextPath}/js/json2.js"></script>


	
<decorator:getProperty property="page.local-script"/>
</body>
</html>