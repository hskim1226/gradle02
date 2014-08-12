<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-create {
            padding: 200px 0 60px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }

        section h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section h3.slogan {
            color: #fdfdfd;
            font-size: 24px;
            font-weight: 500;
            text-align: left;
        }

        section.notice-detail h4.slogan {
            color: #ff8d8d;
            font-size: 18px;
            font-weight: 500;
            text-align: left;
        }

        section .slogan {
            font-weight: 900;
        }

        section .big-font {
            color: #fdfdfd;
            font-size: 20px;
            font-weight: 500;
            text-align: left;
        }

        section .mid-font {
            color: #fdfdfd;
            font-size: 18px;
            text-align: left;
        }

        section .small-font {
            color: #fdfdfd;
            font-size: 12px;
            text-align: left;
        }


    </style>
</head>
<body>
<section class="application-create" id="application-create">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">원서 작성 사전 동의</h2>
                <div>개인정보보호법』 제 15조 및 제 22조에 따라 연세대학교 대학원 신·편입생 선발과 관련하여 개인정보의 수집과 이용을 위해서 개인정보 수집 및 이용에 대한 귀하의 동의가 필요합니다.</div>
                <%--<div class="align-center">--%>
                    <div class="spacer-small"></div>
                    <form class="form-horizontal" id="agreement" role="form">
                        <hr/>
                        <h3 class="slogan">개인 정보 수집 및 이용에 관한 동의</h3>
                        <div>원서 접수 및 입학 전형을 위해 개인정보를 수집 및 이용하며, 이외의 다른 목적에는 절대 사용되지 않습니다.</div>
                        <div class="spacer-tiny"></div>
                        <ul>
                            <li class="big-font">개인 정보의 종류</li>
                            <div>이름, 주민등록번호(외국인 전형 등의 경우 : 외국인등록번호 또는 여권번호), 주소, 전화번호, 휴대전화번호, 이메일, 추가 연락처, 학교정보(최종학력구분, 재학/출신 고교명, 졸업(예정)연도, 고교 전화번호)</div>
                            <div class="spacer-tiny"></div>
                            <li class="big-font">개인 정보의 수집 및 이용 목적</li>
                            <div>원서 접수 및 입학 전형</div>
                            <div class="spacer-tiny"></div>
                            <li class="big-font">개인 정보의 보유 및 이용 기간</li>
                            <div>개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체 없이 해당 개인정보를 파기</div>
                        </ul>
                        <div class="form-group">
                            <label class="col-md-offset-1 col-md-6 control-label big-font slogan">개인 정보 수집 및 이용에 동의 하십니까?</label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="1">동의함
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="0">동의하지 않음
                            </label>
                        </div>
                        <div class="spacer-small"></div>
                        <hr/>
                        <h3 class="slogan">고유식별정보 수집 및 이용에 대한 동의</h3>
                        <div>원서 접수 및 입학 전형을 위해 고유식별정보(주민등록번호, 외국인등록번호, 여권번호)를 수집하고 있습니다(근거 법령 : 고등교육법 시행령 제73조(고유식별정보의 처리)).</div>
                        <div class="spacer-tiny"></div>
                        <div class="form-group" align="center">
                            <label class="col-md-offset-1 col-md-6 control-label big-font slogan">고유식별정보 수집 및 이용에 동의하십니까?</label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="1">동의함
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="0">동의하지 않음
                            </label>
                            <label class="col-md-offset-1 col-md-9 control-label mid-font slogan">고유식별정보 수집 및 이용에 동의하지 않을 경우 원서를 접수할 수 없습니다.</label>
                        </div>

                        <div class="spacer-small"></div>



                        <hr/>
                        <div class="form-group">
                            <label for="inputEName" class="col-sm-3 control-label">영문 이름</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputEName" placeholder="Passport English Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">성 별</label>
                            <div class="col-sm-offset-0 col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="m"> 남자(Male)
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="f"> 여자(Female)
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputDoB" class="col-sm-3 control-label">생년월일</label>
                            <div class="col-sm-offset-0 col-sm-6" id="datapickerBox">
                                <input class="form-control" type="text" id="inputDoB" placeholder="Date of Birth">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputSSN" class="col-sm-3 control-label">주민등록번호</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputSSN" placeholder="Social Security Number">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputNation" class="col-sm-3 control-label">국적</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputNation" placeholder="Nationality">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputCPhone" class="col-sm-3 control-label">핸드폰</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputCPhone" placeholder="010-####-####">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPhone" class="col-sm-3 control-label">전화번호</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputPhone" placeholder="###-####-####">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputAddress" class="col-sm-3 control-label">주소</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputAddress" placeholder="Address">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEMail" class="col-sm-3 control-label">이메일</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="email" class="form-control" id="inputEMail" placeholder="E-mail Address">
                            </div>
                        </div>

                        <div class="spacer-small"></div>
                        <hr/>
                        <h2 class="slogan">학력 사항</h2>
                        <div class="form-group">
                            <label for="inputHSchool" class="col-sm-3 control-label">출신 고교</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputHSchool" placeholder="High School">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputUniv" class="col-sm-3 control-label">출신 대학교</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputUniv" placeholder="College or University">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputGrad" class="col-sm-3 control-label">출신 대학원</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputGrad" placeholder="Graduate College or University">
                            </div>
                        </div>

                        <div class="spacer-small"></div>
                        <hr/>
                        <h2 class="slogan">전형료 환불 계좌</h2>
                        <div class="form-group">
                            <label for="inputBank" class="col-sm-3 control-label">은행명</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputBank" placeholder="Bank Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputBAccount" class="col-sm-3 control-label">계좌 번호</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputBAccount" placeholder="Bank Account Number">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputOAccount" class="col-sm-3 control-label">예금주</label>
                            <div class="col-sm-offset-0 col-sm-6">
                                <input type="text" class="form-control" id="inputOAccount" placeholder="Bank Account Owner">
                            </div>
                        </div>
                    </form>
                    <div class="spacer-small"></div>
                    <!--<div class="form-group">-->
                    <div class="col-sm-offset-3 col-sm-6">
                        <button class="btn btn-default btn-lg btn-block" id="saveApplication">저장하기</button>
                    </div>
                    <!--</div>-->
                <%--</div>--%>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script src="${contextPath}/js/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>
    <script>
        $('#datapickerBox input').datepicker({
            format: "yyyy-mm-dd",
            startView: 3,
            todayBtn:"linked",
            language: "kr",
            orientation: "top auto",
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true,
            todayHighlight: true
        });
        $('#saveApplication').click(function(){location.href='${contextPath}/application/mylist';});
    </script>
</content>
</body>
</html>
