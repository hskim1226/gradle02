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

        section .text-center {
            text-align: center;
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

                <div class="spacer-small"></div>
                <form class="form-horizontal" id="formAgreement" role="form" action="${contextPath}/application/create">
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
                            <input type="radio" name="radio1" value="0">동의함
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="radio1" value="1">동의하지 않음
                        </label>
                        <label class="col-md-offset-1 col-md-9 control-label mid-font slogan">개인정보 수집 및 이용에 동의하지 않을 경우 원서를 접수할 수 없습니다.</label>
                    </div>
                    <div class="spacer-small"></div>

                    <hr/>
                    <h3 class="slogan">고유식별정보 수집 및 이용에 대한 동의</h3>
                    <div>원서 접수 및 입학 전형을 위해 고유식별정보(주민등록번호, 외국인등록번호, 여권번호)를 수집하고 있습니다(근거 법령 : 고등교육법 시행령 제73조(고유식별정보의 처리)).</div>
                    <div class="spacer-tiny"></div>
                    <div class="form-group" align="center">
                        <label class="col-md-offset-1 col-md-6 control-label big-font slogan">고유식별정보 수집 및 이용에 동의하십니까?</label>
                        <label class="radio-inline">
                            <input type="radio" name="radio2" value="0">동의함
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="radio2" value="1">동의하지 않음
                        </label>
                        <label class="col-md-offset-1 col-md-9 control-label mid-font slogan">고유식별정보 수집 및 이용에 동의하지 않을 경우 원서를 접수할 수 없습니다.</label>
                    </div>
                    <div class="spacer-small"></div>

                    <hr/>
                    <h3 class="slogan">개인정보 취급 위탁 대한 동의</h3>
                    <div>원서 접수 및 대입 전형을 위해 다음과 같이 개인정보를 위탁하고 있으며, 관계 법령에 따라 위탁 계약 시 개인정보가 안전하게 관리될 수 있도록 필요한 사항을 규정하고 있습니다.</div>
                    <div class="spacer-tiny"></div>
                    <div class="col-md-offset-1 col-md-11">
                        <table class="table table-stripped">
                            <thead>
                                <tr>
                                    <th>수탁업체</th>
                                    <th>위탁업무 내용</th>
                                    <th>개인정보 보유, 이용 기간</th>
                                    <th>기타</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="text-center">(주)에이펙스소프트</td>
                                    <td class="text-center">입학 원서 접수 대행</td>
                                    <td class="text-center">처리 목적 달성 시 또는 위탁계약 종료 시까지</td>
                                    <td class="text-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="form-group row" align="center">
                        <label class="col-md-offset-1 col-md-6 control-label big-font slogan">개인정보 취급 위탁에 동의하십니까?</label>
                        <label class="radio-inline">
                            <input type="radio" name="radio3" value="0">동의함
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="radio3" value="1">동의하지 않음
                        </label>
                        <label class="col-md-offset-1 col-md-9 control-label mid-font slogan">개인정보 취급 위탁에 동의하지 않을 경우 원서를 접수할 수 없습니다.</label>
                    </div>
                    <div class="spacer-small"></div>

                    <hr/>
                    <h3 class="slogan">개인정보 제3자 제공에 대한 동의</h3>
                    <div>다음과 같이 개인정보를 제3자에게 제공하고 있습니다.</div>
                    <div class="spacer-tiny"></div>
                    <div class="col-md-offset-1 col-md-11">
                        <table class="table table-stripped">
                            <thead>
                            <tr>
                                <th>개인정보를 제공받는 자</th>
                                <th>제공받는 자의 개인정보 이용 목적</th>
                                <th>제공하는 개인정보 항목</th>
                                <th>제공받는 자의 보유,이용 기간</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="text-center">한국대학교육협의회</td>
                                <td class="text-center">학교알리미에 진학 통계 자료 제공</td>
                                <td class="text-center">등록대학, 모집단위명</td>
                                <td class="text-center">처리목적 달성 시까지</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="form-group" align="center">
                        <label class="col-md-offset-1 col-md-6 control-label big-font slogan">개인정보의 제3자 제공에 동의하십니까?</label>
                        <label class="radio-inline">
                            <input type="radio" name="radio4" value="0">동의함
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="radio4" value="1">동의하지 않음
                        </label>
                        <label class="col-md-offset-1 col-md-9 control-label mid-font slogan">개인정보의 제3자 제공에 동의하지 않아도 원서를 접수할 수 있습니다.</label>
                    </div>
                    <div class="spacer-small"></div>

                    <hr/>
                    <h3 class="slogan">본인 확인 및 지원 자격, 전형일자 확인</h3>
                    <div>입학원서는 지원자 본인만 작성하여 지원할 수 있습니다. 차후 이를 위반한 경우에 대학 입학 무효 등의 문제가 발생할 수 있습니다.</div>
                    <div>지원 대학/기관의 모집요강을 확인하시기 바랍니다.</div>
                    <div class="spacer-tiny"></div>
                    <div class="form-group" align="center">
                        <label class="col-md-offset-1 col-md-6 control-label big-font slogan">위 내용을 확인하셨습니까?</label>
                        <label class="radio-inline">
                            <input type="radio" name="radio5" value="0">확인함
                        </label>
                        <label class="col-md-offset-1 col-md-9 control-label mid-font slogan">본인 확인 및 지원 자격, 전형일자를 확인하지 않을 경우 원서를 접수할 수 없습니다.</label>
                    </div>
                    <div class="spacer-small"></div>

                    <hr/>
                    <h3 class="slogan">허위지원 방지와 지원 자격 조작 방지에 관한 확인</h3>
                    <div>입학원서는 지원자 본인만 작성하여 지원할 수 있습니다. 차후 이를 위반한 경우에 대학 입학 무효 등의 문제가 발생할 수 있습니다.</div>
                    <div>허위지원을 할 경우 형법상 업무방해죄에 해당되며 이에 따라 처벌받을 수 있습니다.</div>
                    <div>지원 자격이 허위인 것으로 밝혀진 경우 합격이 취소됩니다.</div>
                    <div class="spacer-tiny"></div>
                    <div class="form-group" align="center">
                        <label class="col-md-offset-1 col-md-6 control-label big-font slogan">위 내용을 확인하셨습니까?</label>
                        <label class="radio-inline">
                            <input type="radio" name="radio6" value="0">확인함
                        </label>
                        <label class="col-md-11 control-label mid-font slogan">허위지원 방지와 지원 자격 조작 방지에 관한 내용을 확인하지 않을 경우 원서를 접수할 수 없습니다.</label>
                    </div>
                    <div class="spacer-small"></div>
                </form>

                <hr/>
                <div class="text-center "><h3 class="slogan" style="text-align: center">원서 접수 완료(전형료 걸제 완료) 후에는 접수 취소 및 변경이 불가능합니다.!!</h3></div>
                <div class="spacer-small"></div>
                <div class="col-md-offset-2 col-md-8">
                    <button class="btn btn-primary btn-lg btn-block" id="composePaper">원서 작성</button>
                </div>
            </div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script>
        $('#formAgreement input').on('change', function() {
            if($('input[type="radio"]:checked', '#formAgreement').val()==1) {
                alert("동의하지 않을 경우 원서를 접수할 수 없습니다.");
            };
        });

        $('#composePaper').click(function(){
            var radioList = document.getElementsByName('inlineRadioOptions')
              , length = radioList.length
              , i, t0
            ;
            for (i = 0 ; i < length ; i++) {
                t0 = radioList[i];
                console.log(t0.value);
            }

//            $('#formAgreement').submit();
        });
    </script>
</content>
</body>
</html>
