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
                            <%-- SYSADMIN 일 경우 안내 배너 표시 --%>
                            <c:if test="${isSYSADMIN}">
                                <div class="col-sm-12">
                                    <span class="btn-group-justified btn-lg btn-danger">THIS IS SYSADMIN, LIST of USER_ID : ${userId}</span>
                                </div>
                            </c:if>
                            <div class="spacer-tiny">&nbsp;</div>
                            <%-- SYSADMIN 일 경우 안내 배너 표시 --%>
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
                                            <td colspan="6"><spring:message code="U00243"/></td>  <%--지원 내역이 없습니다.--%>
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
                                                    <div class="col-sm-12">
                                                        <div class="col-sm-${(item.admsTypeCode=='C' || item.admsTypeCode=='D') ? '3':'4'}">
                                                            <button id="modify${itemStatus.index}" class="btn btn-block btn-warning modify ${item.applStsCode.lastIndexOf('0')==3?'':'disabled'}"
                                                                    data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                                                    data-admsTypeCode="${item.admsTypeCode}"
                                                                    <sec:authorize access="hasRole('ROLE_SYSADMIN')"><c:if test="${item.applStsCode.lastIndexOf('0')!=3}">disabled</c:if></sec:authorize>
                                                                    <sec:authorize access="hasRole('ROLE_USER')">disabled</sec:authorize> >
                                                            	<spring:message code="L00308"/><%-- 원서 수정하기 --%><span class="my-tooltip">작성 중인 상태에서만 수정 가능합니다.</span>
                                                            </button>
                                                                    <%-- 마감 이후 버튼 비활성화 --%>
                                                                    <%-- disabled data-admsTypeCode="${item.admsTypeCode}"><spring:message code="L00308"/>원서 수정하기<span class="my-tooltip">작성 중인 상태에서만 수정 가능합니다.</span></button> --%>
                                                        </div>
                                                        <c:if test="${(item.admsTypeCode=='C' || item.admsTypeCode=='D')}">
                                                            <div class="col-sm-3">
                                                                <button id="recommendation${itemStatus.index}" class="btn btn-block btn-info recommendation ${(item.admsTypeCode=='C' || item.admsTypeCode=='D') ? '':'disabled'}"
                                                                        data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                                                        data-admsTypeCode="${item.admsTypeCode}"><spring:message code="L00315"/><%--추천서 요청--%></button>
                                                            </div>
                                                        </c:if>
                                                        <div class="col-sm-${(item.admsTypeCode=='C' || item.admsTypeCode=='D') ? '3':'4'}">
                                                            <button id="pay${itemStatus.index}" class="btn btn-block btn-primary pay ${item.applStsCode=='00010' ? '':(item.applStsCode=='00021' ? '':'disabled')}"
                                                                    name="${item.entrYear} ${pageContext.response.locale == 'en' ? item.campNameXxen : item.campName} ${pageContext.response.locale == 'en' ? item.admsTypeNameXxen : item.admsTypeName} ${pageContext.response.locale == 'en' ? item.deptNameXxen : item.deptName} ${pageContext.response.locale == 'en' ? item.corsTypeNameXxen : item.corsTypeName}"
                                                                    data-applNo="${item.applNo}"
                                                                    data-userId="${item.userId}"
                                                                    value="${item.admsFee}"
                                                                    <sec:authorize access="hasRole('ROLE_SYSADMIN')"><c:if test="${item.applStsCode!='00010' && item.applStsCode!='00021'}">disabled</c:if></sec:authorize>
                                                                    <sec:authorize access="hasRole('ROLE_USER')">disabled</sec:authorize> >
                                                            	<spring:message code="L00310"/><%--전형료 결제하기--%>
                                                            </button>
                                                        </div>
                                                        <div class="col-sm-${(item.admsTypeCode=='C' || item.admsTypeCode=='D') ? '3':'4'}">
                                                            <div class="btn-group btn-block">
                                                                <a type="button${itemStatus.index}" class="btn btn-block btn-success dropdown-toggle ${item.applStsCode=='00020' ? '':'disabled'}" <c:if test="${item.applStsCode!='00020'}">disabled</c:if> data-toggle="dropdown" data-target="#"><spring:message code="L00311"/><%--지원서 보기--%><span class="caret"></span></a>
                                                                <ul class="dropdown-menu" role="menu">
                                                                    <li><a class="print" data-userid="${item.userId}" data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-admsTypeCode="${item.admsTypeCode}" data-reqType="form"><spring:message code="L00312"/><%--지원서(PDF)--%></a></li>
                                                                    <li><a class="print" data-userid="${item.userId}" data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-admsTypeCode="${item.admsTypeCode}" data-reqType="slip"><spring:message code="L00313"/><%--수험표(PDF)--%></a></li>
                                                                    <li><a class="print" data-userid="${item.userId}" data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-admsTypeCode="${item.admsTypeCode}" data-reqType="merged" data-format="pdf"><spring:message code="L00314"/><%--묶음 파일(ZIP)--%></a></li>
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
                            <input type="hidden" name="applNo"             id="applNo2"/>
                            <input type="hidden" name="application.admsNo" id="admsNo"/>
                            <input type="hidden" name="application.entrYear" id="entrYear"/>
                            <input type="hidden" name="application.admsTypeCode" id="admsTypeCode"/>
                            <input type="hidden" name="application.userId" id="userId"/>
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
                document.getElementById('applNo2').value = obj.getAttribute('data-applNo');
                document.getElementById('admsNo').value = obj.getAttribute('data-admsNo');
                document.getElementById('entrYear').value = obj.getAttribute('data-entrYear');
                document.getElementById('admsTypeCode').value = obj.getAttribute('data-admsTypeCode');
                document.getElementById('reqType').value = obj.getAttribute('data-reqType');
                document.getElementById('userId').value = obj.getAttribute('data-userid');
            };

            $('.modify').click(function(e){
                e.preventDefault();
                var form = document.getElementById('LGD_PAYINFO');
                setHidden(e.target);
                form.action = "${contextPath}/application/basis/edit";
                form.target = '_self';
                form.submit();

            });
            $('.recommendation').click(function(e){
                e.preventDefault();
                var form = document.getElementById('LGD_PAYINFO');
                setHidden(e.target);
                form.action = "${contextPath}/application/recReq/list";
//                form.target = "_blank";
                form.submit();
            });
            $('.pay').click(function(e){
                e.preventDefault();
                var payMsg = '<spring:message code="U00241"/>';   /*전형료 결제이후에는 정보 수정이 불가합니다.\n\n입력하신 모든 정보를 재차 확인하시기 바라며 기입 오류에 대한 책임은 모두 지원자 본인에게 있습니다.*/
                if (confirm(payMsg)) {
                    document.getElementById('LGD_PRODUCTINFO').value = e.target.name;
                    document.getElementById('LGD_AMOUNT').value = e.target.value;
                    document.getElementById('LGD_PAYINFO').setAttribute("action", "${contextPath}/payment/confirm");
                    document.getElementById('applNo').value = e.target.getAttribute('data-applNo');
                    document.getElementById('applNo2').value = e.target.getAttribute('data-applNo');
                    document.getElementById('userId').value = e.target.getAttribute('data-userId');
                    $('#LGD_PAYINFO').submit();
                }
            });
            $('.print').click(function(e){
                e.preventDefault();
                setHidden(e.target);
                var form = document.getElementById('LGD_PAYINFO');
//                form.target = "_blank";
//                var admsNo = e.target.getAttribute('data-admsNo');
                var reqType = e.target.getAttribute('data-reqType');
                form.action = '${contextPath}/postApplication/download/' + reqType;
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
