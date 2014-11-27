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
                <li class="Llast"><span>지원단위변경</span></li>
            </ul>
        </div>

        <h1>지원단위변경</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="">
                        <table summary="지원단위변경 대상자검색">
                            <caption>지원단위변경 대상자검색</caption>
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

        <div id="LblockPageSubtitle03" class="LblockPageSubtitle">
            <h2>변경요청 지원정보</h2>
        </div>
        <div id="LblockDetail03" class="LblockDetail">
            <table summary="변경요청 지원정보">
                <caption>변경요청 지원정보</caption>
                <tbody>
                <tr>
                    <th>수험번호</th>
                    <td>15-A-1101111</td>
                </tr>
                <tr>
                    <th>캠퍼스</th>
                    <td>
                        <select id="sCampus">
                            <option value="01">-- 전체 --</option>
                            <option value="02">서울캠퍼스</option>
                            <option value="02">국제캠퍼스</option>
                            <option value="02">원주캠퍼스</option>
                        </select>
                    </td>
                    <th>전형구분</th>
                    <td>
                        <select id="sType">
                            <option value="01">-- 전체 --</option>
                            <option value="02">일반</option>
                            <option value="02">외국인</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>지원단위</th>
                    <td>
                        <select id="sGruop">
                            <option value="01">-- 전체 --</option>
                            <option value="02">문과대학</option>
                            <option value="02">상과대학</option>
                            <option value="02">이과대학</option>
                            <option value="02">공과대학</option>
                        </select>
                    </td>
                    <th>지원과정</th>
                    <td>
                        <select id="sApplyCourse">
                            <option value="01">-- 전체 --</option>
                            <option value="02">석사학위과정</option>
                            <option value="02">박사학위과정</option>
                            <option value="02">석박사통합학위과정</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>전형료</th>
                    <td>85,000</td>
                    <th>금액변경</th>
                    <td>변경없음</td>
                </tr>
                </tbody>
            </table>

        </div>

        <div id="LblockButton">
            <a href="#"><input type="button"  id="changeBtn" value="변경요청" /></a>
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
