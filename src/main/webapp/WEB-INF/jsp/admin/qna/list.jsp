<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <script type="text/javascript">
        $.noConflict();
        jQuery(document).ready( function(){
            jQuery(".qna").on('click', function(){
                location.href = "${contextPath}/admin/qna/detail?id="+jQuery(this).attr('qna-id');
            }).css("cursor","pointer");
        });

        function movePage(pageNumIndex){
            jQuery("#page-number-hidden").val(pageNumIndex);
            jQuery("#search-form").submit();
        }
    </script>
</head>
<body>
<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
            <ul>
                <li class="Lfirst"><span><a href="#">HOME</a></span></li>
                <li><span><a href="#">Q&A</a></span></li>
                <li class="Llast"><span>Q&A 목록</span></li>
            </ul>
        </div>

        <h1>Q&A 목록</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="${contextPath}/admin/qna/list" id="search-form" method="get">
                        <input type="hidden" id="page-number-hidden" name="page.no" value="${searchForm.page.no}" />
                        <table summary="Q&A 검색조건">
                            <caption>Q&A 검색조건</caption>
                            <tbody>
                            <tr>
                                <th><label for="sCampus">검색대상</label></th>
                                <td>
                                    <select id="sCampus" name="searchCondition">
                                        <option value="1" <c:if test="${param.searchCondition==1}">selected="selected"</c:if>>질문제목</option>
                                        <option value="2" <c:if test="${param.searchCondition==2}">selected="selected"</c:if>>질문내용</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" name="searchKeyword" class="Ltext" size="30" value="${param.searchKeyword}"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="image" class="Limage" src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/btn_search.gif" /></a>
                    </form>
                </div>
            </div>
        </div>

        <div id="LblockListTable01" class="LblockListTable">
            <table summary="일자별 지원현황">
                <caption>일자별 지원현황</caption>
                <thead>
                <tr>
                    <th class="LHdata">순번</th>
                    <th class="LHdata">제목</th>
                    <th class="LHdata">조회수</th>
                    <th class="LHdata">등록일</th>
                    <th class="LHdata">답변여부</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="qna" items="${qna}" varStatus="status">
                    <tr class="<c:if test="${status.index == 0}">Lfirst </c:if>qna" qna-id="${qna.id}">
                        <td class="LRdata">${qna.id}</td>
                        <td class="LRdata">${qna.title}</td>
                        <td class="LRdata">${qna.readCnt}</td>
                        <td class="LRdata">${qna.creDate}</td>
                        <td class="LRdata"><c:if test="${empty qna.answer}" >Not Yet</c:if><c:if test="${not empty qna.answer}" >Yes</c:if></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <ul>
                <fmt:parseNumber var="indexCount" integerOnly= "true" value="${totalCount/searchForm.page.rows + 1}" />
                <c:if test="${indexCount != 0}">
                    <li class="Lbegin"><span><a href="#" onclick="movePage(1); return false;">1page</a></span></li>
                    <c:if test="${searchForm.page.no-1 > 0}">
                        <li class="Lprevious"><span><a href="#" onclick="movePage(${searchForm.page.no-1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_previous.gif" alt="이전페이지" /></a></span></li>
                    </c:if>
                    <c:forEach begin="1" end="${indexCount}" var="pageNumIndex">
                        <c:if test="${searchForm.page.no==pageNumIndex}">
                            <li class="Lfirst"><span>${pageNumIndex}</span></li>
                        </c:if>
                        <c:if test="${searchForm.page.no!=pageNumIndex}">
                            <li><span><a href="#" onclick="movePage(${pageNumIndex}); return false;">${pageNumIndex}</a></span></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${searchForm.page.no < indexCount}">
                        <li class="Lnext"><span><a href="#" onclick="movePage(${searchForm.page.no+1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_next.gif" alt="다음페이지" /></a></span></li>
                    </c:if>
                    <li class="Lend"><span><a href="#" onclick="movePage(${indexCount}); return false;">${indexCount}page</a></span></li>
                </c:if>
            </ul>



        </div>

    </div>

    <!--div id="LblockButton">
        <a href="#"><input type="button" value="엑셀파일 다운로드" onclick="doSomething(); return false;" /></a>
    </div -->


</div>
<content tag="local-script">
    <script>

    </script>
</content>

</body>
</html>
