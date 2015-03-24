<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>    </title>
    <script type="text/javascript">



  
    </script>
</head>
<body>

<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
            <ul>
                <li class="Lfirst"><span><a href="#">HOME</a></span></li>
                <li><span><a href="#">지원자관리</a></span></li>
                <li class="Llast"><span>지원자검색</span></li>
            </ul>
        </div>

        <h1>지원자검색</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">

            <div>
                <form:form commandName="searchForm"  method="post" role="form" action="${contextPath}/admin/search/applicants/idSearch" id="search-form">

                    <input type="hidden" id="page-number-hidden" name="page.no" value="${searchForm.page.no}" />
                    <table summary="지원현황 검색조건">
                        <caption>지원현황 검색조건</caption>
                        <tbody>
                        <tr><td colsapn =2></td></tr>
                        <tr><th class="Cat">수험번호검색 : </th>
                            <th><label for="applId">수험번호</label></th>
                            <td><input type="text" class="Ltext" id="applId" name="applId" size="15" /></td>
                        </tr>
                        <tr> <td colsapn =2></td> </tr>
                        </tbody>
                    </table>
                    <input id="idSearchBtn" type='image' class="Limage" src="${contextPath}/img/admin/repository/btn_search.gif" />
                </form:form>
            </div>
        </div>

        <div id="LblockListTable01" class="LblockListTable">
            <table summary="전형별 지원현황" >
                <caption>전형별 지원현황</caption>
                <thead>
                <tr>
                    <th class="Lfirst">수험번호</th>
                    <th>캠퍼스</th>
                    <th>지원학과<br>세부전공</th>
                    <th>지원전형<br>지원과정</th>
                    <th>성명<br>생년월일</th>
                    <th>전화번호<br>이메일</th>
                    <th>결제방법<br>결제금액</th>
                </tr>
                </thead>
                    <c:if test="${applList.size() == 0}" >
                        <tr >
                            <td colspan="7">해당 정보 없음</td>
                        </tr>
                    </c:if>
                   <c:forEach var="applList" items="${applList}" varStatus="status">
                    <tr class="<c:if test="${status.index == 0}">Lfirst </c:if>applList" applNo="${applList.applNo}">
                        <td>${applList.applId}</td>
                        <td>${applList.campName}</td>
                        <td>${applList.deptName}</td>
                        <td >${applList.applAttrName}<br>${applList.corsTypeName}</td>
                        <td >${applList.korName} <br> ${applList.rgstNo}</td>
                        <td >${applList.mobiNum} <br>${applList.mailAddr} </td>
                        <td >${applList.payTypeName}<br>${applList.admsFee} </td>
                    </tr>
                    </c:forEach>
            </table>
            <ul>
                <fmt:parseNumber var="indexCount" integerOnly= "true" value="${totalCount/searchForm.page.rows + 1}" />
                <c:if test="${indexCount != 0}">
                    <li class="Lbegin"><span><a href="#" onclick="movePage(1); return false;">1page</a></span></li>
                    <c:if test="${idSearchForm.page.no-1 > 0}">
                        <li class="Lprevious"><span><a href="#" onclick="movePage(${idSearchForm.page.no-1}); return false;"><img src="${contextPath}/img/admin/repository/list_page_previous.gif" alt="이전페이지" /></a></span></li>
                    </c:if>
                    <c:forEach begin="1" end="${indexCount}" var="pageNumIndex">
                        <c:if test="${idSearchForm.page.no==pageNumIndex}">
                            <li class="Lfirst"><span>${pageNumIndex}</span></li>
                        </c:if>
                        <c:if test="${idSearchForm.page.no!=pageNumIndex}">
                            <li><span><a href="#" onclick="movePage(${pageNumIndex}); return false;">${pageNumIndex}</a></span></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${idSearchForm.page.no < indexCount}">
                        <li class="Lnext"><span><a href="#" onclick="movePage(${idSearchForm.page.no+1}); return false;"><img src="${contextPath}/img/admin/repository/list_page_next.gif" alt="다음페이지" /></a></span></li>
                    </c:if>
                    <li class="Lend"><span><a href="#" onclick="movePage(${indexCount}); return false;">${indexCount}page</a></span></li>
                </c:if>
            </ul>
            
        </div>
    </div>
</div>

<content tag="local-script">

    <script>

    jQuery(document).ready( function(){
        jQuery(".applList").on('click', function(){
            location.href = "${contextPath}/admin/search/applicant/applInfoDetail?applNo="+jQuery(this).attr('applNo');
        }).css("cursor","pointer");

        jQuery(".Limage").on('click', function(e) {
            e.preventDefault();
            submitForm();
        });


        function submitForm(){
            jQuery("#page-number-hidden").val(1);
            jQuery("#search-form").submit();

        };
    });

    function movePage(pageNumIndex){
        jQuery("#page-number-hidden").val(pageNumIndex);
        jQuery("#search-form").submit();
    };   

    </script>
</content>
</body>
</html>
