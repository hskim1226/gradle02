<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-info {
            padding: 200px 0 60px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }

        section.application-info h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.application-info .spacer-big {
            margin-bottom: 7em;
        }

        section.application-info .spacer-mid {
            margin-bottom: 5em;
        }

        section.application-info .spacer-small {
            margin-bottom: 3em;
        }

        section.application-info .spacer-tiny {
            margin-bottom: 1em;
        }

        section.application-info .tab-content {
            background-color: #fff;
            color: #000;
        }
    </style>
</head>
<body>
<section class="application-info">
    <div class="container">
        <ul id="myTab" class="nav nav-tabs nav-justified">
            <li><a href="#info" data-toggle="tab">기본정보</a></li>
            <li><a href="#biography" data-toggle="tab">자기소개서</a></li>
            <li><a href="#etc" data-toggle="tab">기타</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade" id="info">
                <form class="form-horizontal" role="form">
                    <h2 class="slogan">지원자 정보</h2>
                    <div class="form-group">
                        <label for="campName" class="col-sm-3 control-label">지원캠퍼스</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="campName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="admsNo" class="col-sm-3 control-label">지원과정</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="admsNo"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ariInstName" class="col-sm-3 control-label">학연산 연구기관명</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="ariInstName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="deptName" class="col-sm-3 control-label">지원학과</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="deptName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detailMajor" class="col-sm-3 control-label">세부전공</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="detailMajor"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="korName" class="col-sm-3 control-label">이름</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="korName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-3 control-label">영문</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="engName" name="engName" />
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="engSurName" name="engSurName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="residentNumber" class="col-sm-3 control-label">주민등록번호</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="residentNumber" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">E-mail</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="telephone" class="col-sm-3 control-label">전화번호</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="telephone" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="col-sm-3 control-label">휴대폰</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="mobile" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-3 control-label">주소</label>
                        <div class="col-sm-1">
                            <input type="text" class="form-control" id="zipcode" name="zipcode" />
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input type="text" class="form-control" id="address" />
                                <span class="input-group-addon glyphicon glyphicon-search" id="search-zipcode"></span>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="detail-addr" name="detailAddr" />
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
