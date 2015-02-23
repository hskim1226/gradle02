<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
            <ul>
                <li class="Lfirst"><span><a href="#">HOME</a></span></li>
                <li><span><a href="#">지원변경/원서수정</a></span></li>
                <li class="Llast"><span>지원취소</span></li>
            </ul>
        </div>

        <h1>지원취소</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="">
                        <table summary="지원취소 대상자검색">
                            <caption>지원취소 대상자검색</caption>
                            <tbody>
                            <tr>
                                <th><label for="sApplyNo">수험번호</label></th>
                                <td><input type="text" class="Ltext" id="sApplyNo" size="15" /></td>
                            </tr>
                            <tr>
                            <tr>
                                <th><label for="sApplyNm">성명</label></th>
                                <td><input type="text" class="Ltext" id="sApplyNm" size="15" /></td>
                                <th colspan=2><label for="sRsdnNo" >생년월일(YYMMDD)</label></th>
                                <td><input type="text" class="Ltext" id="sRsdnNo" size="6" /></td>
                            </tr>

                            </tbody>
                        </table>
                        <input type="image" class="Limage" src="${contextPath}/img/admin/btn_search.gif" /></a>
                    </form>
                </div>
            </div>
        </div>
        <div id="LblockPageSubtitle01" class="LblockPageSubtitle">
            <h2>지원정보</h2>
        </div>

        <div id="LblockDetail01" class="LblockDetail">
            <table summary="지원 기본정보">
                <caption>지원 기본정보</caption>
                <tbody>
                <tr>
                    <th>수험번호</th>
                    <td>15-A-1101111</td>
                    <th>캠퍼스</th>
                    <td>서울캠퍼스</td>
                    <th>지원단위</th>
                    <td>토목환경공학과</td>
                </tr>
                <tr>
                    <th>전형구분</th>
                    <td>일반</td>
                    <th>지원과정</th>
                    <td>석사학위과정</td>
                    <th>지원일자</th>
                    <td>2014-09-29</td>
                </tr>
                <tr>
                    <th>지원상태</th>
                    <td>결제완료</td>
                    <th>전형료</th>
                    <td>85,000</td>
                    <th>결제방법</th>
                    <td>카드결제</td>
                </tr>
                </tbody>
            </table>

        </div>

        <div id="LblockPageSubtitle02" class="LblockPageSubtitle">
            <h2>지원서 상세정보</h2>
        </div>
        <div id="LblockDetail02" class="LblockDetail">
            <table summary="지원서 상세정보">
                <caption>지원서 상세정보</caption>
                <tbody>
                <tr>
                    <th>성명</th>
                    <td>홍길동</td>
                    <th>영문명</th>
                    <td>Gildong Hong</td>
                </tr>
                <tr>
                    <th>생년월일</th>
                    <td>1991-01-01</td>
                    <th>주민번호</th>
                    <td>910101-*******</td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td>02-6363-0923</td>
                    <th>핸드폰 번호</th>
                    <td>019-111-1111</td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>서울시 서대문구 신촌동 111-111</td>
                    <th>E-mail</th>
                    <td>yonseiApply@yonsei.co.kr</td>
                </tr>
                <tr>
                    <th>지원세부전공</th>
                    <td>심리학과_김교수님</td>
                    <th>연락처</th>
                    <td>019-111-1111</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="LblockPageSubtitle">
            <h2>지원 취소정보</h2>
        </div>
        <div id="LblockDetail03" class="LblockDetail">
            <table summary="지원 취소정보">
                <caption>지원 취소정보</caption>
                <tbody>
                <tr>
                    <th>취소사유</th>
                    <td ><textarea cols="70" rows="5"></textarea></td>
                </tr>
                </tbody>
            </table>

        </div>

        <div id="LblockButton">
            <a href="#"><input type="button"  id="changeBtn" value="지원취소요청" /></a>
        </div>
    </div>
</div>

<content tag="local-script">
    <script>
        jQuery(document).ready(function() {
            jQuery('#changeBtn').on('click', function(e) {
                event.preventDefault();
                alert("아직 개발중입니다")
                <%--
                if (confirm('지원자 정보를 수정하시겠습니까?')) {
                    jQuery('#changeForm').submit();
                }
                --%>
            });
        });
    </script>
</content>
</body>
</html>
