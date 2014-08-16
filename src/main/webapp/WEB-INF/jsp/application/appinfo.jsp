<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application {
            padding: 200px 0 60px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }

        section.application h2.slogan {
            color: #000;
            font-size: 36px;
            font-weight: 900;
        }

        section.application .spacer-big {
            margin-bottom: 7em;
        }

        section.application .spacer-mid {
            margin-bottom: 5em;
        }

        section.application .spacer-small {
            height: 3em;
        }

        section.application .spacer-tiny {
            height: 1em;
        }

        section.application .tab-content {
            background-color: #fff;
            color: #000;
        }

        section.application .nav>li>a {
            display: block;
        }

        section.application .btn {
            border-bottom-width: 1px;
        }

        section.application .input-group-addon .btn {
            border-radius: 4px;
        }

        section.application textarea
    </style>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" />
</head>
<body>
<section class="application">
<div class="container">
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
<div class="spacer-tiny"></div>
<hr/>

<ul id="myTab" class="nav nav-tabs nav-justified">
    <li><a href="#appinfo" data-toggle="tab">기본정보</a></li>
    <li><a href="#selfintro" data-toggle="tab">자기소개서</a></li>
    <li><a href="#studyplan" data-toggle="tab">기타</a></li>
</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade" id="appinfo">
        <form:form commandName="applicationVO" cssClass="form-horizontal" role="form">
            <div class="spacer-tiny"></div>
            <div class="form-group">
                <form:label path="campus" cssClass="col-sm-3 control-label">지원캠퍼스</form:label>
                <div class="col-sm-6">
                    <form:select path="campus" cssClass="form-control">
                        <form:option value="-" label="--Please Select" />
                        <form:options items="${campuses}" itemValue="code" itemlabel="name" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="course" cssClass="col-sm-3 control-label">지원과정</form:label>
                <div class="col-sm-6">
                    <form:select path="course" cssClass="form-control">
                        <form:option value="-" label="--Please Select" />
                        <form:options items="${courses}" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="institution" cssClass="col-sm-3 control-label">학연산 연구기관명</form:label>
                <div class="col-sm-6">
                    <form:input path="institution" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="department" cssClass="col-sm-3 control-label">지원학과</form:label>
                <div class="col-sm-6">
                    <form:select path="department" cssClass="form-control">
                        <form:option value="-" label="--Please Select" />
                        <form:options items="${departments}" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="detailMajor" cssClass="col-sm-3 control-label">세부전공</form:label>
                <div class="col-sm-6">
                    <form:select path="detailMajor" cssClass="form-control">
                        <form:option value="-" label="--Please Select" />
                        <form:options items="${detailMajors}" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="korName" cssClass="col-sm-3 control-label">이름</form:label>
                <div class="col-sm-6">
                    <form:input path="korName" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="engName" cssClass="col-sm-3 control-label">영문</form:label>
                <div class="col-sm-3">
                    <form:input path="engName" cssClass="form-control" />
                </div>
                <div class="col-sm-3">
                    <form:input path="engSurName" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="residentNumber" cssClass="col-sm-3 control-label">주민등록번호</form:label>
                <div class="col-sm-6">
                    <form:input path="residentNumber" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="email" cssClass="col-sm-3 control-label">E-mail</form:label>
                <div class="col-sm-6">
                    <form:input path="email" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="telephone" cssClass="col-sm-3 control-label">전화번호</form:label>
                <div class="col-sm-6">
                    <form:input path="telephone" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="mobile" cssClass="col-sm-3 control-label">휴대폰</form:label>
                <div class="col-sm-6">
                    <form:input path="mobile" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="address" cssClass="col-sm-3 control-label">주소</form:label>
                <div class="col-sm-1">
                    <form:input path="zipCode" cssClass="form-control" />
                </div>
                <div class="col-sm-5">
                    <div class="input-group">
                        <form:input path="address" cssClass="form-control" />
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default" id="search-zipcode">
                                <span class="glyphicon glyphicon-search"></span> Search
                            </button>
                        </span>
                    </div>
                </div>
                <div class="col-sm-offset-3 col-sm-6">
                    <form:input path="detailAddr" cssClass="form-control" />
                </div>
            </div>

            <div class="spacer-small"></div>
            <hr/>
            <h2 class="slogan">학력 사항</h2>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-8">
                    <table class="table table-hover table-responsive table-condensed table-bordered">
                        <thead>
                        <tr>
                            <td><input type="checkbox" id="select-all-school" /></td>
                            <td>학력</td>
                            <td>학교명</td>
                            <td>입학일자</td>
                            <td>졸업일자</td>
                            <td>졸업구분</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${schools}" var="school" varStatus="status">
                            <tr>
                                <td>
                                    <input type="checkbox" id="selectRow" />
                                </td>
                                <td>
                                    <form:select path="school.type" items="${schoolTypes}" />
                                </td>
                                <td>
                                    <form:input path="school.name" />
                                </td>
                                <td>
                                    <form:input path="school.entrance" />
                                </td>
                                <td>
                                    <form:input path="school.graduation" />
                                </td>
                                <td>
                                    <form:select path="school.graduationType" items="${graduationTypes}" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-offset-2">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-chevron-up"></span>
                        </button>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </button>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-minus"></span>
                        </button>
                    </div>
                </div>
            </div>

            <div class="spacer-small"></div>
            <hr/>
            <h2 class="slogan">전형료 환불 계좌</h2>
            <div class="form-group">
                <form:label path="bank" cssClass="col-sm-3 control-label">은행명</form:label>
                <div class="col-sm-6">
                    <form:select path="bank" cssClass="form-control">
                        <form:option value="-" label="--Please Select" />
                        <form:options items="${bankList}" itemvalue="code" itemlabel="name" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="accountNumber" cssClass="col-sm-3 control-label">계좌 번호</form:label>
                <div class="col-sm-6">
                    <form:input path="accountNumber" cssClass="form-control" placeholder="Bank Account Number" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="accountOwner" cssClass="col-sm-3 control-label">예금주</form:label>
                <div class="col-sm-6">
                    <form:input path="accountOwner" cssClass="form-control" placeholder="Bank Account Owner" />
                </div>
            </div>
            <div class="spacer-tiny"></div>
        </form:form>
    </div>
    <%--selfintro--%>
    <div class="tab-pane fade" id="selfintro">
        <form:form commandName="selfIntro" cssClass="form-horizontal" role="form" id="formSeflIntro" action="${contextPath}/application/selfintro/save">
            <div class="spacer-tiny"></div>
            <div class="form-group">
                <form:label path="ta1" cssClass="col-sm-3 control-label">주요 경력 사항</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta1" cssClass="form-control" rows="12" placeholder="주요 경력사항을 작성해주세요" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="ta2" cssClass="col-sm-3 control-label">지원 동기 및 장례 계획</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta2" cssClass="form-control" rows="12" placeholder="지원 동기와 장래 계획을 작성해주세요" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="ta3" cssClass="col-sm-3 control-label">성격 및 특기</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta3" cssClass="form-control" rows="12" placeholder="성격의 장단점과 특기를 작성해주세요" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="ta4" cssClass="col-sm-3 control-label">수상 내역</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta4" cssClass="form-control" rows="12" placeholder="주요 수상 내역을 작성해주세요" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="ta5" cssClass="col-sm-3 control-label">기타</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta5" cssClass="form-control" rows="12" placeholder="본인을 잘 설명할 수 있는 내용을 작성해주세요"/>
                </div>
            </div>
            <div class="spacer-tiny"></div>
        </form:form>
    </div>
    <%--studyplan--%>
    <div class="tab-pane fade" id="studyplan">
        <form:form commandName="studyPlan" cssClass="form-horizontal" role="form" action="${contextPath}/application/studyplan/save">
            <div class="spacer-tiny"></div>
            <div class="form-group">
                <form:label path="ta1" cssClass="col-sm-3 control-label">희망 연구 분야 및 연구 계획</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta1" cssClass="form-control" rows="12" placeholder="희망 연구 분야와 연구 계획을 작성해주세요" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="ta2" cssClass="col-sm-3 control-label">학부/대학원 이수과목 중 관심과목</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta2" cssClass="form-control" rows="12" placeholder="학부와 대학원에서 관심있게 수강한 과목에 대해 작성해주세요" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="ta3" cssClass="col-sm-3 control-label">석/박사 이후의 계획</form:label>
                <div class="col-sm-8">
                    <form:textarea path="ta3" cssClass="form-control" rows="12" placeholder="석/박사 이후의 진로 계획에 대해 작성해주세요" />
                </div>
            </div>
            <div class="spacer-tiny"></div>
        </form:form>
    </div>
</div>

<div>
    <button type="button" class="btn btn-default btn-lg" id="temp-save">임시저장</button>
    <button type="button" class="btn btn-primary btn-lg" id="save">저장</button>
    <button type="button" class="btn btn-waring btn-lg" id="reset">되돌리기</button>
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
    <script type="text/javascript">
        $(document).ready(function() {
            $('#myTab a:first').tab('show');

            $('#temp-save').on('click', function() {
                var $curPane = $('.tab-pane.active');
                var $curForm = $curPane.find('form');
                console.log($curForm.serialize());
            });
        });
    </script>
</content>
</body>
</html>
