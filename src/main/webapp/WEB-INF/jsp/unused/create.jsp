<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-selfintro {
            padding: 200px 0 60px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }

        section.application-selfintro h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.application-selfintro .spacer-big {
            margin-bottom: 7em;
        }

        section.application-selfintro .spacer-mid {
            margin-bottom: 5em;
        }

        section.application-selfintro .spacer-small {
            margin-bottom: 3em;
        }

        section.application-selfintro .spacer-tiny {
            margin-bottom: 1em;
        }
    </style>
</head>
<body>
<section class="application-selfintro" id="application-create">
    <div class="container">
        <ul id="myTab" class="nav nav-tabs nav-justified">
            <li><a href="#info" data-toggle="tab">기본정보</a></li>
            <li><a href="#biography" data-toggle="tab">자기소개서</a></li>
            <li><a href="#etc" data-toggle="tab">기타</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade" id="info">
                <p>info</p>
            </div>
            <div class="tab-pane fade" id="biography">
                <p>biography</p>
            </div>
            <div class="tab-pane fade" id="etc">
                <p>etc</p>
            </div>
        </div>


<%--
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
--%>
                <h2 class="slogan">선택 과정</h2>
                <div class="align-center">
                    <table class="table table-stripped">
                        <thead>
                        <tr>
                            <th>대학원</th>
                            <th>공고명</th>
                            <th>접수 기간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>일반</td>
                            <td>2015학년도 연세대학교 일반대학원 석사과정 수시 모집</td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="spacer-small"></div>
                <hr/>
                <form class="form-horizontal" role="form">
                    <h2 class="slogan">지원자 정보</h2>
                    <div class="form-group">
                        <label for="inputName" class="col-sm-3 control-label">이름</label>
                        <div class="col-sm-offset-0 col-sm-6">
                            <input type="text" class="form-control" id="inputName" placeholder="Name">
                        </div>
                    </div>
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
<%--
            </div>
        </div>
--%>
    </div>
</section>
<content tag="local-script">
    <script src="${contextPath}/js/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>
    <script>
        $(function() {
            $('#myTab a:first').tab('show');
        });

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
