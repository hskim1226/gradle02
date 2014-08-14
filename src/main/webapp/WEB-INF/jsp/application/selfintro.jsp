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
<section class="application-selfintro" id="application-selfintro">
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
        <form class="form-horizontal" role="form" id="formSeflIntro" action="${contextPath}/application/selfintro/save">
            <h2 class="slogan">자기소개서</h2>
            <div class="spacer-small"></div>
            <div class="col-md-offset-1 col-md-11">
                <table class="table table-stripped">
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta1" class="text-center"><h3 class="slogan text-center">경력</h3></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta1" name="ta1" placeholder="주요 경력사항을 작성해주세요" rows="12"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta2" class="text-center"><h3 class="slogan">지원 동기<br/><br/>및<br/><br/>장래 계획</h3></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta2" placeholder="지원 동기와 장래 계획을 작성해주세요" rows="12"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta3" class="text-center"><h3 class="slogan">성격<br/><br/>및<br/><br/>특기</h3></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta3" placeholder="성격의 장단점과 특기를 작성해주세요" rows="12"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta4" class="text-center"><h3 class="slogan">수상 내역</h3></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta4" placeholder="주요 수상 내역을 작성해주세요" rows="12"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-sm-2 text-center" style="vertical-align: middle;"><label for="ta5" class="text-center"><h3 class="slogan">기타</h3></label></td>
                        <td class="col-sm-10">
                            <textarea class="form-control" id="ta5" placeholder="본인을 잘 설명할 수 있는 내용을 작성해주세요" rows="12"></textarea>
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
