<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
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
                        <table summary="지원현황 검색조건">
                            <caption>지원현황 검색조건</caption>
                            <tbody>
                            <tr>
                                <th><label for="sCampus">모집캠퍼스</label></th>
                                <td>
                                    <select id="sCampus">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">서울캠퍼스</option>
                                        <option value="02">국제캠퍼스</option>
                                        <option value="02">원주캠퍼스</option>
                                    </select>
                                </td>
                                <th><label for="sDetailType">모집전형</label></th>
                                <td>
                                    <select id="sDetailType">
                                        <option value="01">-- 전체 --</option>
                                        <option value="02">석사학위과정</option>
                                        <option value="02">박사학위과정</option>
                                        <option value="02">통합학위과정</option>

                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><label>지원일자</label></th>
                                <td>
                                    <input type="radio" class="Lradio" id="dApplyDate_1" name="dApplyDate" /><label for="dApplyDate_1">금주</label>
                                    <input type="radio" class="Lradio" id="dApplyDate_2" name="dApplyDate" checked="checked" /><label for="dApplyDate_2">전주</label>
                                    <input type="radio" class="Lradio" id="dApplyDate_3" name="dApplyDate" /><label for="dApplyDate_3">오늘까지</label>
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

    <div id="LblockButton">
        <a href="#"><input type="button" value="엑셀파일 다운로드" onclick="doSomething(); return false;" /></a>
    </div>


</div>
<content tag="local-script">
    <script>

    </script>
</content>

<script type="text/javascript">
    $(document).ready( function(){
        $(".qna").on('click', function(){
            location.href = "${contextPath}/qna/detail?id="+$(this).attr('qna-id');
        }).css("cursor","pointer");
    });
</script>
</body>
</html>
