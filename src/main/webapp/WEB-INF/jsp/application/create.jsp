<%--
  Created by IntelliJ IDEA.
  User: hanmomhanda
  Date: 14. 8. 6
  Time: 오전 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<section class="application-compose" id="app-list">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
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
                            <td><a href="apply.html">2015학년도 연세대학교 일반대학원 석사과정 수시 모집</a></td>
                            <td>2014-09-28 / 2014-10-03</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="spacer-small"></div>
                <hr/>
                <form class="form-horizontal" role="form" action="mylist.html">
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
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script src="/js/bootstrap-datepicker.js"></script>
    <script src="/js/bootstrap-datepicker.kr.js"></script>
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
        $('#saveApplication').click(function(){location.href='/application/mylist';});
    </script>
</content>
</body>
</html>
