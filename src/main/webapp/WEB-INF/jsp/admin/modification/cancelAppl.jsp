<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<div class="content">
    <div class="con_tit">
        <h2><span>지원취소</span></h2>
        <div class="location"> HOME &gt; 지원변경/원서수정 &gt; <span>지원취소</span> </div>
    </div>

    <div class="con_section">
        <form id ="applicantSearchForm" action="${contextPath}/admin//modification/cancelAppl" method="post">
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

                <form id ="changeForm" action="${contextPath}/admin/modification/requestCancel" method="post">
                    <input type="hidden" name="applNo" value=${applInfo.applNo}> </input>
                    <input type="hidden" name="admsNo" value=${applInfo.admsNo}> </input>
                    <input type="hidden" id="befValInput" name ="befVal"> </input>
                    <input type="hidden" id="aftValInput" name ="aftVal"> </input>

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
                        <th>취소사유</th>
                        <td colspan="5"><textarea  r rows ="5" cols="60" name="cnclResn"  id="cnclResn" ></textarea></td>
                    </tr>
                    </tbody>
                </table>
                </form>
        </c:if>
        <c:if test="${applInfo.applNo != null}" >
            <div class="con_btn text-right">
                <a class="btn_set btnRedS" id="changeBtn" ><span>지원취소요청</span></a>
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
                if (confirm('해당 지원서를 취소요청 하시겠습니까?')) {

                    jQuery('#befValInput').val(
                                    "${applInfo.admsTypeName}" +"-"+
                                    "${applInfo.ariInstName}" +"-"+
                                    "${applInfo.campName}" +"<br>"+
                                    "${applInfo.collName}" +"-"+
                                    "${applInfo.deptName}" +"<br>"+
                                    "${applInfo.corsTypeName}" +"<br>"+
                                    "${applInfo.detlMajName}" );
                    jQuery('#aftValInput').val('--지원 취소--');
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
