<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title><spring:message code="L00504"/><%--원서 작성 사전 동의--%></title>
    <style>
        section.normal-white h4.slogan {
            color: #333333;
            font-size: 18px;
            font-weight: 500;
            text-align: left;
        }
        section .slogan {
            font-weight: 900;
        }
        section .big-font {
            color: #333333;
            font-size: 20px;
            font-weight: 500;
            text-align: left;
        }
        section .mid-font {
            color: #333333;
            font-size: 18px;
            text-align: left;
        }
        section .small-font {
            color: #333333;
            font-size: 12px;
            text-align: left;
        }
        section .text-center {
            text-align: center;
        }
        .text-left {
            text-align: left;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form class="form-horizontal" id="formAgreement" role="form" action="${contextPath}/application/basis/edit" method="post">
            <div class="row mar-bot40">
                <div class="form-group inner-container-white">
                    <div class="col-md-10 col-md-offset-1 word-keep-all text-gray">
                        <div class="col-sm-12 text-gray">
                            <i class="fa fa-check fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00504"/><%--원서 작성 사전 동의--%></b></span>
                        </div>
                        <div class="spacer-small">&nbsp;</div>
                        <div class="col-sm-12 text-left">
                            <div><spring:message code="YONSEI_AGR001"/><%--『개인정보보호법』 제 15조 및 제 22조에 따라 연세대학교 대학원 신·편입생 선발과 관련하여 개인정보의 수집과 이용을 위해서 개인정보 수집 및 이용에 대한 귀하의 동의가 필요합니다.--%></div>
                            <div class="spacer-small">&nbsp;</div>
                            <hr/>
                            <div><h3 class="slogan"><spring:message code="YONSEI_AGR101"/><%--개인 정보 수집 및 이용에 관한 동의--%></h3></div>
                            <div><spring:message code="YONSEI_AGR102"/><%--학생선발에 관한 사무 및 합격자를 대상으로 하는 학적부 작성·관리 등 교육의 과정 기록에 관한 사무를 위해 개인정보를 수집 및 이용하며, 이외의 다른 목적에는 절대 사용되지 않습니다.--%></div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <ul>
                                <li class="big-font"><spring:message code="YONSEI_AGR103"/><%--개인 정보의 종류--%></li>
                                <div><spring:message code="YONSEI_AGR104"/><%--이름, 주민등록번호(외국인 전형 등의 경우 : 외국인등록번호 또는 여권번호), 주소, 전화번호, 휴대전화번호, 이메일, 추가 연락처, 학교정보(최종학력구분, 재학/출신 학교명, 졸업(예정)연도)--%></div>
                                <div class="spacer-tiny">&nbsp;</div>
                                <li class="big-font"><spring:message code="YONSEI_AGR105"/><%--개인 정보의 수집 및 이용 목적--%></li>
                                <div><spring:message code="YONSEI_AGR106"/><%--학생선발에 관한 사무 및 합격자를 대상으로 하는 학적부 작성·관리 등 교육의 과정 기록에 관한 사무와 일반대학원 총학생회에서 제공하는 복지 및 학술 사업 안내(합격자에 한 함)--%></div>
                                <div class="spacer-tiny">&nbsp;</div>
                                <li class="big-font"><spring:message code="YONSEI_AGR107"/><%--개인 정보의 보유 및 이용 기간--%></li>
                                <div><spring:message code="YONSEI_AGR108"/><%--개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체 없이 해당 개인정보를 파기--%></div>
                                <div class="spacer-tiny">&nbsp;</div>
                                <li class="big-font"><spring:message code="YONSEI_AGR113"/><%--동의하지 않을 권리 및 그에 따른 불이익--%></li>
                                <div><spring:message code="YONSEI_AGR114"/><%--귀하는 개인정보의 수집 및 이용에 동의하지 않으실 수 있으나, 필수 정보 수집 동의 거부 시 입학지원서 접수가 불가합니다.--%></div>
                            </ul>
                            <div class="spacer-small">&nbsp;</div>
                            <div class="form-group" align="center">
                                <label class="col-md-offset-1 col-md-6 big-font slogan text-center"><spring:message code="YONSEI_AGR109"/><%--개인 정보 수집 및 이용에 동의 하십니까?--%></label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio1" value="0"><spring:message code="YONSEI_AGR110"/><%--동의함--%>
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio1" value="1" checked><spring:message code="YONSEI_AGR111"/><%--동의하지 않음--%>
                                </label>
                                <div class="spacer-tiny">&nbsp;</div>
                                <label class="col-md-offset-1 col-md-10 mid-font slogan text-center"><spring:message code="YONSEI_AGR112"/><%--개인정보 수집 및 이용에 동의하지 않을 경우 원서를 접수할 수 없습니다.--%></label>
                            </div>
                            <div class="spacer-small">&nbsp;</div>

                            <hr/>
                            <h3 class="slogan"><spring:message code="YONSEI_AGR201"/><%--고유식별정보 수집 및 이용에 대한 동의--%></h3>
                            <div><spring:message code="YONSEI_AGR202"/><%--학생선발에 관한 사무 및 합격자를 대상으로 하는 학적부 작성·관리 등 교육의 과정 기록에 관한 사무를 위해 고유식별정보(주민등록번호, 외국인등록번호, 여권번호)를 수집하고 있습니다(근거 법령 : 고등교육법 시행령 제73조(고유식별정보의 처리)).--%></div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <ul>
                                <li class="big-font"><spring:message code="YONSEI_AGR207"/><%--개인정보 수집·이용 목적--%></li>
                                <div><spring:message code="YONSEI_AGR208"/><%--재외국민 및 외국인학생의 원서 접수 및 대입 전형--%></div>
                                <div class="spacer-tiny">&nbsp;</div>
                                <li class="big-font"><spring:message code="YONSEI_AGR209"/><%--개인정보 수집 항목--%></li>
                                <div><spring:message code="YONSEI_AGR210"/><%--여권번호, 외국인등록번호--%></div>
                                <div><spring:message code="YONSEI_AGR211"/><%--* 주민등록번호는 고등교육법 시행령 제73조 제1항에 따라 수집합니다.--%></div>
                                <div class="spacer-tiny">&nbsp;</div>
                                <li class="big-font"><spring:message code="YONSEI_AGR212"/><%--개인정보 보유기간--%></li>
                                <div><spring:message code="YONSEI_AGR213"/><%--대학입학 전형관리 및 공정성 검증을 위한 보유기간 : 4년--%></div>
                                <div class="spacer-tiny">&nbsp;</div>
                                <li class="big-font"><spring:message code="YONSEI_AGR214"/><%--동의하지 않을 권리 및 그에 따른 불이익--%></li>
                                <div><spring:message code="YONSEI_AGR215"/><%--귀하는 개인정보의 수집 및 이용에 동의하지 않으실 수 있으나, 동의 거부 시 입학지원서 접수가 불가합니다.--%></div>
                            </ul>
                            <div class="spacer-small">&nbsp;</div>
                            <div class="form-group" align="center">
                                <label class="col-md-offset-1 col-md-6 big-font text-center slogan"><spring:message code="YONSEI_AGR203"/><%--고유식별정보 수집 및 이용에 동의하십니까?--%></label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio2" value="0"><spring:message code="YONSEI_AGR204"/><%--동의함--%>
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio2" value="1" checked><spring:message code="YONSEI_AGR205"/><%--동의하지 않음--%>
                                </label>
                                <div class="spacer-tiny">&nbsp;</div>
                                <label class="col-md-offset-1 col-md-10 mid-font text-center slogan word-keep-all"><spring:message code="YONSEI_AGR206"/><%--고유식별정보 수집 및 이용에 동의하지 않을 경우 원서를 접수할 수 없습니다.--%></label>
                            </div>
                            <div class="spacer-small">&nbsp;</div>

                            <hr/>
                            <h3 class="slogan"><spring:message code="YONSEI_AGR301"/><%--개인정보 취급 위탁 대한 동의--%></h3>
                            <div><spring:message code="YONSEI_AGR302"/><%--학생선발에 관한 사무를 위해 다음과 같이 개인정보를 위탁하고 있으며, 관계 법령에 따라 위탁 계약 시 개인정보가 안전하게 관리될 수 있도록 필요한 사항을 규정하고 있습니다.--%></div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="col-md-offset-1 col-md-11">
                                <table class="table table-stripped">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="YONSEI_AGR303"/><%--수탁업체--%></th>
                                        <th><spring:message code="YONSEI_AGR304"/><%--위탁업무 내용--%></th>
                                        <th><spring:message code="YONSEI_AGR305"/><%--개인정보 보유, 이용 기간--%></th>
                                        <th><spring:message code="YONSEI_AGR306"/><%--기타--%></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td class="text-center"><spring:message code="YONSEI_AGR307"/><%--(주)에이펙스소프트--%></td>
                                        <td class="text-center"><spring:message code="YONSEI_AGR308"/><%--입학 원서 접수 대행--%></td>
                                        <td class="text-center"><spring:message code="YONSEI_AGR309"/><%--처리 목적 달성 시 또는 위탁계약 종료 시까지--%></td>
                                        <td class="text-center"><spring:message code="YONSEI_AGR310"/><%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="form-group row" align="center">
                                <label class="col-md-offset-1 col-md-6 text-center big-font slogan"><spring:message code="YONSEI_AGR311"/><%--개인정보 취급 위탁에 동의하십니까?--%></label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio3" value="0"><spring:message code="YONSEI_AGR312"/><%--동의함--%>
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio3" value="1" checked><spring:message code="YONSEI_AGR313"/><%--동의하지 않음--%>
                                </label>
                                <div class="spacer-tiny">&nbsp;</div>
                                <label class="col-md-offset-1 col-md-10 text-center mid-font slogan"><spring:message code="YONSEI_AGR314"/><%--개인정보 취급 위탁에 동의하지 않을 경우 원서를 접수할 수 없습니다.--%></label>
                            </div>
                            <div class="spacer-small">&nbsp;</div>

                            <hr/>
                            <h3 class="slogan"><spring:message code="YONSEI_AGR401"/><%--본인 확인 및 지원 자격, 전형일자 확인--%></h3>
                            <div><spring:message code="YONSEI_AGR402"/><%--입학원서는 지원자 본인만 작성하여 지원할 수 있습니다. 차후 이를 위반한 경우에 대학 입학 무효 등의 문제가 발생할 수 있습니다.--%></div>
                            <div><spring:message code="YONSEI_AGR403"/><%--지원 대학/기관의 모집요강을 확인하시기 바랍니다.--%></div>
                            <div><spring:message code="YONSEI_AGR404"/><%--지원 대학/기관의 모집요강을 확인하시기 바랍니다.--%></div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="form-group" align="center">
                                <label class="col-md-offset-1 col-md-6 text-center big-font slogan"><spring:message code="YONSEI_AGR405"/><%--위 내용을 확인하셨습니까?--%></label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio4" value="0"><spring:message code="YONSEI_AGR406"/><%--확인함--%>
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio4" value="1" checked><spring:message code="YONSEI_AGR407"/><%--확인하지 않음--%>
                                </label>
                                <div class="spacer-tiny">&nbsp;</div>
                                <label class="col-md-offset-1 col-md-10 text-center mid-font slogan"><spring:message code="YONSEI_AGR408"/><%--확인하지 않을 경우 원서를 접수할 수 없습니다.--%></label>
                            </div>
                            <div class="spacer-small">&nbsp;</div>

                            <hr/>
                            <h3 class="slogan"><spring:message code="YONSEI_AGR501"/><%--허위지원 방지와 지원 자격 조작 방지에 관한 확인--%></h3>
                            <div><spring:message code="YONSEI_AGR502"/><%--허위지원을 할 경우 형법상 업무방해죄에 해당되며 이에 따라 처벌받을 수 있습니다.--%></div>
                            <div><spring:message code="YONSEI_AGR503"/><%--지원 자격이 허위인 것으로 밝혀진 경우 합격이 취소됩니다.--%></div>
                            <div class="spacer-tiny">&nbsp;</div>
                            <div class="form-group" align="center">
                                <label class="col-md-offset-1 col-md-6 text-center big-font slogan"><spring:message code="YONSEI_AGR504"/><%--위 내용을 확인하셨습니까?--%></label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio5" value="0"><spring:message code="YONSEI_AGR505"/><%--확인함--%>
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="radio5" value="1" checked><spring:message code="YONSEI_AGR506"/><%--확인하지 않음--%>
                                </label>
                                <div class="spacer-tiny">&nbsp;</div>
                                <label class="col-md-offset-1 col-md-10 text-center mid-font slogan"><spring:message code="YONSEI_AGR507"/><%--확인하지 않을 경우 원서를 접수할 수 없습니다.--%></label>
                            </div>
                            <div class="spacer-small">&nbsp;</div>
                            <hr/>
                            <div class="form-group" align="center">
                                <div class="col-md-12 text-center mid-font slogan"><label><spring:message code="YONSEI_AGR601"/><%--전체 동의--%> <input type="checkbox" id="checkAll" name="check1"></label></div>
                                <p>&nbsp;</p>
                                <div><h3 class="slogan" style="text-align: center"><spring:message code="YONSEI_AGR602"/><%--원서 접수 완료(전형료 결제 완료) 후에는 접수 취소 및 변경이 불가능합니다!!--%></h3></div>
                                <div class="spacer-tiny">&nbsp;</div>
                                <div class="col-md-offset-2 col-md-8">
                                    <button class="btn btn-primary btn-lg btn-block" id="composePaper"><spring:message code="L00506"/><%--원서 작성--%></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="application.admsNo" id="admsNo" value="${admsNo}" />
            <input type="hidden" name="application.entrYear" id="entrYear" value="${entrYear}" />
            <input type="hidden" name="application.admsTypeCode" id="admsTypeCode" value="${admsTypeCode}" />
        </form>
    </div>
</section>
<content tag="local-script">
    <script>
        $(document).ready( function() {
            <%-- 단어 잘림 방지 --%>
            $('.word-keep-all').wordBreakKeepAll();

            $('#checkAll').on('click', function () {
                $('#formAgreement input[type="radio"]').each( function () {
                    if (document.getElementById('checkAll').checked) {
                        if (this.value == "0") this.checked = true;
                        else this.checked = false;

                    } else {
                        if (this.value == "1") this.checked = true;
                        else this.checked = false;
                    }
                });
            });

            var o = $('#formAgreement input[type="radio"]');
            o.on('change', function() {
                if ( this.value == "1" ) {
                    nonagreeAlert();
                }
            });

            function nonagreeAlert() {
                alert("<spring:message code="U01100"/>");//동의하지 않을 경우 원서를 접수할 수 없습니다.
            }

            $('#composePaper').click(function(e){
                e.preventDefault();
                var l = $('#formAgreement').find('input').filter('[type="radio"]').length/2, i, t0;
                for (i = 1 ; i <= l ; i++) {
                    t0 = $('input[name=radio'+i+']:checked', '#formAgreement');
                    if ( !t0.val() || t0.val() == "1" ) {
                        nonagreeAlert();
                        $('input[name=radio'+i+']:not(:checked)').focus();
                        return;
                    }
                }

                $('#formAgreement').submit();
            });
        });
    </script>
</content>
</body>
</html>
