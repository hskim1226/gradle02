<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>내 원서</title>
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
        <form class="form-horizontal" id="LGD_PAYINFO" name="applicationIdentifier" role="form" method="post">
            <div class="row mar-bot40">
                <div class="col-sm-offset-1 col-sm-10">
                    <div class="form-group inner-container-white">
                        <div class="col-sm-offset-1 col-sm-10 text-gray">
                            <i class="fa fa-list fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b>지원 내역</b></span>
                        </div>
                        <div class="spacer-small">&nbsp;</div>
                        <div class="col-sm-offset-1 col-sm-10 align-center">
                            <table class="table table-stripped text-gray">
                                <thead>
                                <tr>
                                    <th>캠퍼스</th>
                                    <th>전형</th>
                                    <th>학과</th>
                                    <th>과정</th>
                                    <th>세부전공</th>
                                    <th>지원상태</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${myList}" var="item">
                                    <tr>
                                        <td valign="middle" style="vertical-align: middle;">${item.campName}</td>
                                        <td valign="middle" style="vertical-align: middle;">${item.admsTypeName}</td>
                                        <td valign="middle" style="vertical-align: middle;">${item.deptName}</td>
                                        <td valign="middle" style="vertical-align: middle;">${item.corsTypeName}</td>
                                        <td valign="middle" style="vertical-align: middle;">${item.detlMajName}</td>
                                        <td valign="middle" style="vertical-align: middle;">${item.applStsName}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="6">

                                            <button id="modify" class="btn btn-warning modify ${item.applStsCode.lastIndexOf('0')==3?"":"disabled"}"
                                                    data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                                    data-admsTypeCode="${item.admsTypeCode}" <c:if test="${item.applStsCode.lastIndexOf('0')!=3}">disabled</c:if> >원서 수정하기<span class="my-tooltip">작성 중인 상태에서만 수정 가능합니다.</span></button>
                                            <button id="preview" class="btn btn-info preview ${item.applStsCode=="00010"?"":"disabled"}"
                                            <%--<button id="preview" class="btn btn-info preview"--%>
                                                    data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                                data-admsTypeCode="${item.admsTypeCode}" <c:if test="${item.applStsCode!='00010'}">disabled</c:if> >원서 미리보기</button>
                                                    <%--data-admsTypeCode="${item.admsTypeCode}" >원서 미리보기</button>--%>

                                            <button id="pay" class="btn btn-primary pay ${item.applStsCode=="00010"?"":(item.applStsCode=="00021"?"":"disabled")}"
                                                    name="2015학년도 ${item.campName} ${item.admsTypeName} ${item.deptName} ${item.corsTypeName}"
                                                    data-applNo="${item.applNo}"
                                                    value="${item.admsFee}"
                                                    <c:if test="${item.applStsCode!='00010' && item.applStsCode!='00021'}">disabled</c:if> >전형료 결제하기</button>

                                            <div class="btn-group">
                                                <a type="button" class="btn btn-success dropdown-toggle ${item.applStsCode=="00020"?"":"disabled"}" <c:if test="${item.applStsCode!='00020'}">disabled</c:if> data-toggle="dropdown" data-target="#">지원서 보기<span class="caret"></span></a>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a class="print" data-applNo="${item.applNo}" data-format="pdf" data-filename="application_${item.admsTypeCode=="C" ? "en" : "kr"}">지원서(PDF)</a></li>
                                                    <li><a class="print" data-applNo="${item.applNo}" data-format="pdf" data-filename="admission_${item.admsTypeCode=="C" ? "en" : "kr"}">수험표(PDF)</a></li>
                                                    <li><a class="print" data-admsNo="${item.admsNo}" data-applNo="${item.applNo}" data-format="pdf">전체 파일(PDF)</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <input type="hidden" name="LGD_PRODUCTINFO" id="LGD_PRODUCTINFO"/>
                            <input type="hidden" name="LGD_AMOUNT" id="LGD_AMOUNT"/>
                            <input type="hidden" name="application.applNo" id="applNo"/>
                            <input type="hidden" name="application.admsNo" id="admsNo"/>
                            <input type="hidden" name="application.entrYear" id="entrYear"/>
                            <input type="hidden" name="application.admsTypeCode" id="admsTypeCode"/>
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
                var target = e.target;

                var admsNo = target.getAttribute('data-admsNo');
                var applNo = target.getAttribute('data-applNo');
                var reportFormat = target.getAttribute('data-format');

                if (admsNo != null && admsNo.length > 0) {
                    window.open('${contextPath}/pdf/download/' + admsNo + '/' + applNo);
                } else {
                    var reportName = target.getAttribute('data-filename');
                    window.open('${contextPath}/application/print?applNo=' + applNo +
                    '&reportFormat=pdf&reportName=' + reportName);
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
