<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>    </title>
</head>
<body>


<div class="content">
    <div class="con_tit">
        <h2><span>지원자검색</span></h2>
        <div class="location"> HOME &gt; 지원자관리 &gt; <span>지원자검색</span> </div>

    </div>

    <div class="con_section">
        <form:form commandName="idSearchForm"  method="post" role="form" action="${contextPath}/admin/search/applicants/idSearch" id="search-form">
        <div class="srch_box">
            <input type="hidden" id="page-number-hidden" name="page.no" value="${searchPageForm.page.no}" />

            <p class="srch_tit"><i class="fa fa-search"></i>수험번호검색</p>
            <label for="applId"><strong>수험번호</strong></label>
            <input type="text" id="applId" name="applId" class="ipt_txt1" style />&nbsp;&nbsp;
            <span class="btnBlueS">
          <input type="submit" value="검색" class="btnBox" id='idSearchBtn' />
            </span>

        </div>
        </form:form>


            <table class="tbl_typeA text-center" summary="전형별 지원현황">
                <caption>전형별 지원현황</caption>
                <thead>
                <tr>
                    <th>수험번호</th>
                    <th>캠퍼스</th>
                    <th>지원학과<br>세부전공</th>
                    <th>지원전형<br>지원과정</th>
                    <th>성명<br>생년월일</th>
                    <th>전화번호<br>이메일</th>
                    <th>결제방법<br>결제금액</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${applList.size() == 0}" >
                    <tr >
                        <td colspan="7">해당 정보 없음</td>
                    </tr>
                </c:if>
                <c:forEach var="applList" items="${applList}" varStatus="status">
                    <tr class="applList"  applNo="${applList.applNo}">
                        <td>${applList.applId}</td>
                        <td>${applList.campName}</td>
                        <td>${applList.deptName}
                            <c:if test ="${applList.detlMajCode != 'DM000'}">
                                <br>${applList.detlMajName}
                            </c:if>
                        </td>
                        <td>
                            <c:if test ="${applList.admsNo == '15B'}">
                                ${applList.applAttrName}<br>
                            </c:if>
                                ${applList.corsTypeName}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${applList.admsNo == '15D'}">
                                    ${applList.engName},${applList.engSur} <br> ${applList.bornDay}
                                </c:when>
                                <c:otherwise>
                                    ${applList.korName} <br> ${applList.rgstBornDate}
                                </c:otherwise>

                            </c:choose>
                        </td>
                        <td>${applList.mobiNum} <br>${applList.mailAddr} </td>
                        <td>${applList.payTypeName}<br>${applList.admsFee} </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="page_number">
                <fmt:parseNumber var="indexCount" integerOnly= "true" value="${idSearchForm.page.totalCount/idSearchForm.page.rows + 1}" />
                <c:if test="${indexCount != 0}">
                <a class="Lbegin"><span><a href="#" class="end" onclick="movePage(1); return false;">1page</a>
        <c:if test="${idSearchForm.page.no-1 > 0}">
           <a href="#" onclick="movePage(${idSearchForm.page.no-1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_previous.gif" alt="이전페이지" /></a>
        </c:if>
        <c:forEach begin="1" end="${indexCount}" var="pageNumIndex">
            <c:if test="${idSearchForm.page.no==pageNumIndex}">
               <span class="active">${pageNumIndex}</span>
            </c:if>
            <c:if test="${idSearchForm.page.no!=pageNumIndex}">
                <a href="#" onclick="movePage(${pageNumIndex}); return false;">${pageNumIndex}</a>
            </c:if>
        </c:forEach>
        <c:if test="${idSearchForm.page.no < indexCount}">
            <a href="#" onclick="movePage(${idSearchForm.page.no+1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_next.gif" alt="다음페이지" /></a>
        </c:if>
        <a href="#" class="end" onclick="movePage(${indexCount}); return false;">${indexCount}page</a>
    </c:if>
            </div>

<%--

            <div id="LblockcChgInfoBtn" class="con_btn text-right">
                <a class="btn_set btnBlueS" id="downBtn"  href="#"><span>엑셀파일 다운로드</span></a>
            </div>
            --%>
        </div>
            <!-- /con_section -->
    </div>
    <!-- /content -->




<content tag="local-script">

    <script>

    jQuery(document).ready( function(){
        jQuery(".applList").on('click', function(){
            location.href = "${contextPath}/admin/search/applicant/applInfoDetail?applNo="+jQuery(this).attr('applNo');
        }).css("cursor","pointer");

        jQuery("#idSearchBtn").on('click', function(e) {
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
