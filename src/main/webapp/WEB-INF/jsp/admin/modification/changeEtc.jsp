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
                <li><span><a href="#">기타 지원내용 변경</a></span></li>
                <li class="Llast"><span>기타 지원내용 변경</span></li>
            </ul>
        </div>

        <h1>지원단위변경</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form id ="applicantSearchForm" action="${contextPath}/admin//modification/changeEtc" method="post">
                        <table summary="변경 대상자검색">
                            <caption>변경 대상자검색</caption>
                            <tbody>
                                <th><label for="applId">수험번호</label></th>
                                <td><input type="text" class="Ltext" id="applId" name="applId" size="15"  value="${applicantSearchForm.applId}">
                                    <img class="Limage" id="searchBtn"  src="${contextPath}/img/admin/btn_search.gif" alt="검색버튼" />
                                </td>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>

        <%@include file="applicationInfo.jsp"%>
        <%@include file="applicantInfo.jsp"%>

        <c:if test="${applInfo.applNo != null}" >
            <div id="LblockPageSubtitle03" class="LblockPageSubtitle">
                <h2>변경내용</h2>
            </div>
            <div id="LblockDetail03" class="LblockDetail">

                <form id ="changeForm" action="${contextPath}/admin/modification/requestChangeEtc" method="post">
                    <input type="hidden" name="applNo" value=${applInfo.applNo}> </input>
                    <input type="hidden" name="admsNo" value=${applInfo.admsNo}> </input>
                <table summary="변경요청 지원정보">
                    <caption>변경요청 지원정보</caption>
                    <tbody>
                    <tr>
                        <th>변경전 내용</th>
                        <td colspan="5"><textarea  r rows ="5" cols="60" name="befVal"  id="befVal" ></textarea></td>
                    </tr>
                    <tr>
                        <th>변경할 내용</th>
                        <td colspan="5"><textarea  r rows ="5" cols="60" name="aftVal"  id="aftVal" ></textarea></td>
                    </tr>
                    <tr>
                        <th>변경 사유</th>
                        <td colspan="5"><textarea  r rows ="5" cols="60" name="cnclResn"  id="cnclResn" ></textarea></td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>

            <div id="LblockButton">
                <a href="#"><input type="button"  id="changeBtn" value="기타정보 변경요청" /></a>
            </div>
        </c:if>
    </div>
  </div>
</body>


<content tag="local-script">
    <script>
        jQuery(document).ready(function() {
            jQuery('#changeBtn').on('click', function(e) {
                event.preventDefault();
                if (confirm('지원정보를 변경요청 하시겠습니까?')) {
                    jQuery('#changeForm').submit();
                }

            });

            jQuery('#searchBtn').on('click', function(event) {
                jQuery('#applicantSearchForm').submit();
            });

        });
    </script>
</content>
</body>
</html>
