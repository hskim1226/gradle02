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
                    <form action="">
                        <table summary="Q&A 검색조건">
                            <caption>Q&A 검색조건</caption>
                            <tbody>
                            <tr>
                                <th><label for="sCampus">검색대상</label></th>
                                <td>
                                    <select id="sCampus" name="searchCondition">
                                        <option value="1">제목</option>
                                        <option value="2">질문</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" name="searchKeyword" class="Ltext" size="30" />
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="image" class="Limage" src="${contextPath}/img/admin/btn_search.gif" /></a>
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
