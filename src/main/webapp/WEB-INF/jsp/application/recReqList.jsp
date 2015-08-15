<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L06551"/><%--추천서 요청 내역--%></title>
    <style>
        .editable {
            cursor: hand;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form:form cssClass="form-horizontal" id="rec-req" commandName="recommendationList" role="form">
            <div class="row mar-bot40">
                <div class="col-sm-offset-1 col-sm-10">
                    <div class="form-group inner-container-white">
                        <div class="col-sm-offset-1 col-sm-10 text-gray">
                            <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00301"/><%--지원 내역--%></b></span>
                        </div>
                        <div class="spacer-small">&nbsp;</div>
                        <div class="col-sm-offset-1 col-sm-10 align-center">
                            <table class="table table-stripped text-gray">
                                <thead>
                                <tr>
                                    <th><spring:message code="L06552"/><%--번호--%></th>
                                    <th><spring:message code="L06553"/><%--교수 이름--%></th>
                                    <th><spring:message code="L06554"/><%--교수 e-mail--%></th>
                                    <th><spring:message code="L06555"/><%--요청 상태--%></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${recommendationList.size() == 0}">
                                        <tr>
                                            <td colspan="6"><spring:message code="U06301"/></td>  <%--요청 내역이 없습니다.--%>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${recommendationList}" var="item" varStatus="itemStatus">
                                            <tr>
                                            <c:choose>
                                                <c:when test='${item.getRecStsCode().equals("00001")}'>
                                                    <td valign="middle" style="vertical-align: middle;"><span class="editable" data-recNo="${item.recNo}">${item.recSeq}</span></td>
                                                    <td valign="middle" style="vertical-align: middle;"><span class="editable" data-recNo="${item.recNo}">${item.profName}</span></td>
                                                    <td valign="middle" style="vertical-align: middle;"><span class="editable" data-recNo="${item.recNo}">${item.profMailAddr}</span></td>
                                                    <td valign="middle" style="vertical-align: middle;"><span class="editable" data-recNo="${item.recNo}">${pageContext.response.locale == 'en' ? item.recStsNameXxen : item.recStsName}</span></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td valign="middle" style="vertical-align: middle;">${item.recSeq}</td>
                                                    <td valign="middle" style="vertical-align: middle;">${item.profName}</td>
                                                    <td valign="middle" style="vertical-align: middle;">${item.profMailAddr}</td>
                                                    <td valign="middle" style="vertical-align: middle;">${pageContext.response.locale == 'en' ? item.recStsNameXxen : item.recStsName}</td>
                                                </c:otherwise>
                                            </c:choose>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                            <div class="spacer-small">&nbsp;</div>
                            <div class="col-sm-12 nopadding">
                                <button class="create btn btn-info btn-lg btn-block btn-save input-text"><spring:message code="L06556"/><%--신규 요청--%></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="applNo" name="applNo" value="${applNo}"/>
            <input type="hidden" id="recNo" name="recNo"/>
        </form:form>
    </div>

</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {

            var setHidden = function (obj) {
                document.getElementById('recNo').value = obj.getAttribute('data-recNo');
            };

            $('.editable').click(function(e){
                e.preventDefault();
                var form = document.getElementById('rec-req');
                setHidden(e.target);
                form.action = "${contextPath}/application/recReq/edit";
                form.submit();

            });

            $('.create').click(function(e){
                e.preventDefault();
                var form = document.getElementById('rec-req');
                document.getElementById('recNo').value = -1;
                form.action = "${contextPath}/application/recReq/edit";
                form.submit();

            });

            <%-- action 성공 여부 알림 처리 --%>
            var showActionResult = function() {
                var msg = '${resultMsg}';
                if (msg.length > 0) {
                    confirm(msg);
                }
            };
            showActionResult();
            <%-- action 성공 여부 알림 처리 --%>
        });
    </script>
</content>
</body>
</html>
