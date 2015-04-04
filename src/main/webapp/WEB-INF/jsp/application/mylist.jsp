<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L00301"/><%--내 원서--%></title>
    <style>
        .my-tooltip {
            display: none;
            background: #505050;
            color: #e0e0e0;
            margin-left: 5px;
            padding: 10px;
            position: absolute;
            z-index: 2;
            width:400px;
            height:30px;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form class="form-horizontal" id="LGD_PAYINFO" name="mylist" role="form" method="post">
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
                                    <th><spring:message code="L00302"/><%--캠퍼스--%></th>
                                    <th><spring:message code="L00303"/><%--전형--%></th>
                                    <th><spring:message code="L00304"/><%--학과--%></th>
                                    <th><spring:message code="L00305"/><%--과정--%></th>
                                    <th><spring:message code="L00306"/><%--세부전공--%></th>
                                    <th><spring:message code="L00307"/><%--지원상태--%></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${myList.size() == 0}">
                                        <tr>
                                            <td colspan="6">지원 내역이 없습니다.</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${myList}" var="item" varStatus="itemStatus">
                                            <tr>
                                                <td valign="middle" style="vertical-align: middle;">${pageContext.response.locale == 'en' ? item.campNameXxen : item.campName}</td>
                                                <td valign="middle" style="vertical-align: middle;">${pageContext.response.locale == 'en' ? item.admsTypeNameXxen : item.admsTypeName}</td>
                                                <td valign="middle" style="vertical-align: middle;">${pageContext.response.locale == 'en' ? item.deptNameXxen : item.deptName}</td>
                                                <td valign="middle" style="vertical-align: middle;">${pageContext.response.locale == 'en' ? item.corsTypeNameXxen : item.corsTypeName}</td>
                                                <td valign="middle" style="vertical-align: middle;">${pageContext.response.locale == 'en' ? item.detlMajNameXxen : item.detlMajName}</td>
                                                <td valign="middle" style="vertical-align: middle;">${pageContext.response.locale == 'en' ? item.applStsNameXxen : item.applStsName}</td>
                                            </tr>
                                            <tr>
                                                <td colspan="6">
                                                    <div class="col-sm-offset-1 col-sm-10">
                                                        <div class="col-sm-3">
                                                            <button id="modify${itemStatus.index}" class="btn btn-block btn-warning modify ${item.applStsCode.lastIndexOf('0')==3?"":"disabled"}"
                                                                    data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                                                    data-admsTypeCode="${item.admsTypeCode}" <c:if test="${item.applStsCode.lastIndexOf('0')!=3}">disabled</c:if> ><spring:message code="L00308"/><%--원서 수정하기--%><span class="my-tooltip">작성 중인 상태에서만 수정 가능합니다.</span></button>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <button id="preview${itemStatus.index}" class="btn btn-block btn-info preview ${item.applStsCode=="00010"?"":"disabled"}"
                                                                <%--<button id="preview" class="btn btn-info preview"--%>
                                                                    data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}" data-reqType="appl"
                                                                    data-admsTypeCode="${item.admsTypeCode}" <c:if test="${item.applStsCode!='00010'}">disabled</c:if> ><spring:message code="L00309"/><%--원서 미리보기--%></button>
                                                                <%--data-admsTypeCode="${item.admsTypeCode}" >원서 미리보기</button>--%>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <button id="pay${itemStatus.index}" class="btn btn-block btn-primary pay ${item.applStsCode=="00010"?"":(item.applStsCode=="00021"?"":"disabled")}"
                                                                    name="2015학년도 ${item.campName} ${item.admsTypeName} ${item.deptName} ${item.corsTypeName}"
                                                                    data-applNo="${item.applNo}"
                                                                    value="${item.admsFee}"
                                                                    <c:if test="${item.applStsCode!='00010' && item.applStsCode!='00021'}">disabled</c:if> ><spring:message code="L00310"/><%--전형료 결제하기--%></button>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="btn-group btn-block">
                                                                <a type="button${itemStatus.index}" class="btn btn-block btn-success dropdown-toggle ${item.applStsCode=="00020"?"":"disabled"}" <c:if test="${item.applStsCode!='00020'}">disabled</c:if> data-toggle="dropdown" data-target="#"><spring:message code="L00311"/><%--지원서 보기--%><span class="caret"></span></a>
                                                                <ul class="dropdown-menu" role="menu">
                                                                    <li><a class="print" data-applNo="${item.applNo}" data-admsTypeCode="${item.admsTypeCode}" data-reqType="appl"><spring:message code="L00312"/><%--지원서(PDF)--%></a></li>
                                                                    <li><a class="print" data-applNo="${item.applNo}" data-admsTypeCode="${item.admsTypeCode}" data-reqType="adms"><spring:message code="L00313"/><%--수험표(PDF)--%></a></li>
                                                                    <li><a class="print" data-admsNo="${item.admsNo}" data-applNo="${item.applNo}" data-format="pdf"><spring:message code="L00314"/><%--전체 파일(PDF)--%></a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                            <input type="hidden" name="LGD_PRODUCTINFO" id="LGD_PRODUCTINFO"/>
                            <input type="hidden" name="LGD_AMOUNT" id="LGD_AMOUNT"/>
                            <input type="hidden" name="application.applNo" id="applNo"/>
                            <input type="hidden" name="application.admsNo" id="admsNo"/>
                            <input type="hidden" name="application.entrYear" id="entrYear"/>
                            <input type="hidden" name="application.admsTypeCode" id="admsTypeCode"/>
                            <input type="hidden" name="reqType" id="reqType"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {

            var setHidden = function (obj) {
                document.getElementById('applNo').value = obj.getAttribute('data-applNo');
                document.getElementById('admsNo').value = obj.getAttribute('data-admsNo');
                document.getElementById('entrYear').value = obj.getAttribute('data-entrYear');
                document.getElementById('admsTypeCode').value = obj.getAttribute('data-admsTypeCode');
                document.getElementById('reqType').value = obj.getAttribute('data-reqType');
            };

            $('.modify').click(function(e){
                e.preventDefault();
                var form = document.getElementById('LGD_PAYINFO');
                setHidden(e.target);
                form.action = "${contextPath}/application/basis/edit";
                form.submit();

            });
            $('.preview').click(function(e){
                e.preventDefault();
                var form = document.getElementById('LGD_PAYINFO');
                setHidden(e.target);
                form.action = "${contextPath}/application/preview";
                form.target = "_blank";
                form.submit();
                <%--var target = e.target;--%>
                <%--var applNo = target.getAttribute('data-applNo');--%>
                <%--window.open('${contextPath}/application/preview/' + applNo);--%>
            });
            $('.pay').click(function(e){
                e.preventDefault();
                var payMsg = '전형료 결제이후에는 정보 수정이 불가합니다.\n\n입력하신 모든 정보를 재차 확인하시기 바라며 기입 오류에 대한 책임은 모두 지원자 본인에게 있습니다.';
                if (confirm(payMsg)) {
                    document.getElementById('LGD_PRODUCTINFO').value = e.target.name;
                    document.getElementById('LGD_AMOUNT').value = e.target.value;
                    document.getElementById('LGD_PAYINFO').setAttribute("action", "${contextPath}/payment/confirm");
                    document.getElementById('applNo').value = e.target.getAttribute('data-applNo');
                    $('#LGD_PAYINFO').submit();
                }
            });
            $('.print').click(function(e){
                e.preventDefault();
                setHidden(e.target);
                var form = document.getElementById('LGD_PAYINFO');
                form.target = "_blank";
                var admsNo = e.target.getAttribute('data-admsNo');
                if (admsNo != null && admsNo.length > 0) { // 전체 파일
                    form.action = '${contextPath}/pdf/download';
                    form.submit();
                } else { // 지원서 또는 수험표
                    form.action = '${contextPath}/application/print';
                    form.submit();
                }
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
