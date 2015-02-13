<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-studyplan {
            padding: 200px 0 60px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }

        section.application-studyplan h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.application-studyplan .spacer-big {
            margin-bottom: 7em;
        }

        section.application-studyplan .spacer-mid {
            margin-bottom: 5em;
        }

        section.application-studyplan .spacer-small {
            margin-bottom: 3em;
        }

        section.application-studyplan .spacer-tiny {
            margin-bottom: 1em;
        }

        h3.slogan {
            color: #fdfdfd;
            font-size: 28px;
            font-weight: 300;
        }

        h4.slogan {
            color: #fdfdfd;
            font-size: 20px;
            font-weight: 300;
        }
    </style>
</head>
<body>
<section class="application-studyplan" id="application-selfintro">
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
        <form class="form-horizontal" role="form" id="formSeflIntro" action="${contextPath}/application/studyplan/save">
            <h2 class="slogan">학업계획서</h2>
            <div class="spacer-small"></div>
            <div class="col-md-offset-1 col-md-11">
                <table class="table table-stripped">
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta1" class="text-center"><h4 class="slogan text-center">희망 연구 분야<br/></br>및<br/></br>연구 계획</h4></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta1" name="ta1" placeholder="희망 연구 분야와 연구 계획을 작성해주세요" rows="12"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta2" class="text-center"><h4 class="slogan">학부, 대학원<br/><br/>이수 과목 중<br/><br/>관심 과목</h4></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta2" placeholder="학부와 대학원에서 관심있게 수강한 과목에 대해 작성해주세" rows="12"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta3" class="text-center"><h4 class="slogan">석/박사 이후의<br/><br/>계획</h4></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta3" placeholder="석/박사 이후의 진로 계획에 대해 작성해주세요" rows="12"></textarea>
                        </td>
                    </tr>
                </table>
            </div>

        </form>
        <div><h3 class="slogan" style="text-align: center">개발용 : 탭 방식이므로 아래의 자기소개서 저장 버튼은 없앨 예정</h3></div>
        <hr/>
        <div><h4 class="slogan" style="text-align: center">자기소개서는 원서 작성 완료(결제 완료)와 함께 확정됩니다.</h4></div>
        <div class="spacer-tiny"></div>
        <div class="col-md-offset-2 col-md-9">
            <button class="btn btn-primary btn-lg btn-block" id="saveSelfIntro">자기소개서 저장</button>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $('#saveSelfIntro').click(function(){
            $('#formSelfIntro').serialize();
            $('#formSelfIntro').submit();
        });
    </script>
</content>
</body>
</html>
