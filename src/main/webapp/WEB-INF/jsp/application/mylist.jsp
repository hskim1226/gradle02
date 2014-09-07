<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-mylist {
            padding: 200px 0 60px;
            background: #443355;
            color: #fdfdfd;
            position:relative;
        }

        section.application-mylist h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.application-mylist .spacer-big {
            margin-bottom: 7em;
        }

        section.application-mylist .spacer-mid {
            margin-bottom: 5em;
        }

        section.application-mylist .spacer-small {
            margin-bottom: 3em;
        }

        section.application-mylist .spacer-tiny {
            margin-bottom: 1em;
        }
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
<section class="application-mylist" id="application-mylist">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">지원 내역</h2>
                <div class="align-center">
                    <form class="form-horizontal" id="LGD_PAYINFO" role="form" method="post">
                        <table class="table table-stripped">
                            <thead>
                            <tr>
                                <th>캠퍼스</th>
                                <th>전형</th>
                                <th>학과</th>
                                <th>과정</th>
                                <th>세부전공</th>
                                <th>지원상태</th>
                                <%--<th>작업</th>--%>
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
                                <%--<td valign="middle" style="vertical-align: middle;">--%>
                                    <%--<button id="modify" class="btn btn-default btn-block modify" data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}" data-admsTypeCode="${item.admsTypeCode}" ${item.applStsCode=="00001"?"":"disabled"}>수정하기</button>--%>
                                    <%--&lt;%&ndash;<button id="verify" class="btn btn-info btn-block" ${item.applStsCode=="00010"?"":"disabled"}>확인하기</button>&ndash;%&gt;--%>
                                    <%--<button id="verify" class="btn btn-info btn-block verify" data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}" data-admsTypeCode="${item.admsTypeCode}">확인하기</button>--%>
                                    <%--<button id="pay" class="btn btn-primary btn-block pay"--%>
                                            <%--name="2015학년도 ${item.campName} ${item.admsTypeName} ${item.deptName} ${item.corsTypeName}"--%>
                                            <%--&lt;%&ndash;value="80000" ${item.applStsCode=="00010"?"":(item.applStsCode=="00021"?"":"disabled")}>결제하기</button>&ndash;%&gt;--%>
                                            <%--value="80000">결제하기</button>--%>
                                    <%--<button id="showApplicationBirt" class="btn btn-success btn-block showApplicationBirt" data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}" data-admsTypeCode="${item.admsTypeCode}" ${item.applStsCode=='00020'?"":"disabled"}>지원서보기</button>--%>
                                    <%--<button id="showAppLableBirt" class="btn btn-success btn-block showAppLableBirt" data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}" data-admsTypeCode="${item.admsTypeCode}" ${item.applStsCode=='00020'?"":"disabled"}>수험표출력</button>--%>
                                <%--</td>--%>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    <button id="modify" class="btn btn-default modify ${item.applStsCode=="00001"?"":"disabled"}"
                                            data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                            data-admsTypeCode="${item.admsTypeCode}">원서 수정하기<span class="my-tooltip">작성 중인 상태에서만 수정 가능합니다.</span></button>
                                    <button id="verify" class="btn btn-info verify ${item.applStsCode=="00010"?"":"disabled"}"
                                            data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                            data-admsTypeCode="${item.admsTypeCode}">원서 미리보기<span class="my-tooltip">작성 완료 상태에서 미리볼 수 있습니다.</span></button>
                                    <button id="pay" class="btn btn-primary pay ${item.applStsCode=="00010"?"":(item.applStsCode=="00021"?"":"disabled")}"
                                        name="2015학년도 ${item.campName} ${item.admsTypeName} ${item.deptName} ${item.corsTypeName}"
                                        value="80000">전형료 결제하기</button>
                                            <%--value="80000">결제하기</button>--%>
                                    <button id="showApplicationBirt" class="btn btn-success showApplicationBirt ${item.applStsCode=='00020'?"":"disabled"}"
                                            data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                            data-admsTypeCode="${item.admsTypeCode}">지원서 보기</button>
                                    <button id="showAppLableBirt" class="btn btn-success showAppLableBirt ${item.applStsCode=='00020'?"":"disabled"}"
                                            data-applNo="${item.applNo}" data-admsNo="${item.admsNo}" data-entrYear="${item.entrYear}"
                                            data-admsTypeCode="${item.admsTypeCode}">수험표 출력</button>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <input type="hidden" name="LGD_PRODUCTINFO" id="LGD_PRODUCTINFO"/>
                        <input type="hidden" name="LGD_AMOUNT" id="LGD_AMOUNT"/>
                        <input type="hidden" name="LGD_TIMESTAMP" id="LGD_TIMESTAMP"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {
            var getQueryString = function (obj, includeApplNo) {
                var withoutApplNo = "?admsNo=" + obj.getAttribute("data-admsNo") + "&" +
                                "entrYear=" + obj.getAttribute("data-entrYear") + "&" +
                                "admsTypeCode=" + obj.getAttribute("data-admsTypeCode"),
                        withApplNo = withoutApplNo + "&applNo=" + obj.getAttribute("data-applNo");
                return includeApplNo ? withApplNo : withoutApplNo;

            $('.modify').click(function(e){
                location.href="${contextPath}/application/apply" + getQueryString(e.target, true);
                e.preventDefault();
            });
            $('.verify').click(function(e){
                location.href="${contextPath}/application/apply" + getQueryString(e.target, true);
                e.preventDefault();
            });
            $('.pay').click(function(e){
                document.getElementById('LGD_PRODUCTINFO').value = e.target.name;
                document.getElementById('LGD_AMOUNT').value = e.target.value;
                document.getElementById('LGD_PAYINFO').setAttribute("action", "${contextPath}/pay/confirm");
                $('#LGD_PAYINFO').submit();
            });
            $('.showApplicationBirt').click(function(e){
                <%--location.href="${contextPath}/application/show";--%>
                e.preventDefault();
            });
            $('.showAppLabel').click(function(e){
                <%--location.href="${contextPath}/application/show";--%>
                e.preventDefault();
            });

            }
        })
    </script>
</content>
</body>
</html>
