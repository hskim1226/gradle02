<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class="content">
    <div class="con_tit">
        <h2><span>기타 지원내용 변경</span></h2>
        <div class="location"> HOME &gt; 지원변경/원서수정 &gt; <span>기타 지원내용 변경</span> </div>
    </div>

    <div class="con_section">
        <form id ="applicantSearchForm" action="${contextPath}/admin//modification/changeEtc" method="post">
            <div class="srch_box"> <strong><label for="applId">수험번호</label></strong>
                <input type="text" name="applId" id="applId" class="ipt_txt1"  value="${applicantSearchForm.applId}" >
          <span class="btnBlueS">
          <input type="submit" value="검색" class="btnBox" id="searchBtn"  />
          </span> </div>
        </form>

        <%@include file="applicationInfo.jsp"%>
        <%@include file="applicantInfo.jsp"%>


        <c:if test="${applInfo.applNo != null}" >
        <h3 class="tit1">변경요청 지원정보용</h3>
        <form id ="changeForm" action="${contextPath}/admin/modification/requestChangeEtc" method="post">
            <input type="hidden" name="applNo" value=${applInfo.applNo}> </input>
            <input type="hidden" name="admsNo" value=${applInfo.admsNo}> </input>
        <table class="tbl_typeA mb15" summary="변경요청 지원정보">
            <caption>
                변경요청 지원정보
            </caption>
            <colgroup>
                <col width="20%" />
                <col width="80%" />
            </colgroup>
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
        </c:if>
            <c:if test="${applInfo.applNo != null}" >
            <div class="con_btn text-right">
                <a class="btn_set btnRedS" id="changeBtn" ><span>기타정보 변경요청</span></a>
            </div>
            </c:if>
    </div>
    <!-- /con_section -->
</div>
<!-- /content -->



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
