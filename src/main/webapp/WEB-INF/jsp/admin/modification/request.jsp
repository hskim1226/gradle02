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
                <li class="Llast"><span>원서수정</span></li>
            </ul>
        </div>

        <h1>원서수정</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="">
                        <table summary="원서수정 대상자검색">
                            <caption>원서수정 대상자검색</caption>
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
                    <td><input type="radio" class="Lradio" id="dApplyItem_1" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_1"> 홍길동</label></td>
                    <th>영문명</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_2" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_2"> Gildong Hong</label></td>
                </tr>
                <tr>
                    <th>생년월일</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_3" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_3"> 1991-01-01</label></td>
                    <th>주민번호</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_4" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_4"> 910101-*******</label></td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_7" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_7"> 02-6363-0923</label></td>
                    <th>핸드폰 번호</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_8" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_8"> 019-111-1111</label></td>
                </tr>			<tr>
                    <th>주소</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_5" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_5"> 서울시 서대문구 신촌동 111-111</label></td>
                    <th>E-mail</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_6" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_6"> yonseiApply@yonsei.co.kr</label></td>
                </tr>
                <tr>
                    <th>지원세부전공</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_9" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_9"> 심리학과_김교수님</label></td>
                    <th>연락처</th>
                    <td><input type="radio" class="Lradio" id="dApplyItem_10" name="dApplyItem" onClick="selectChangeItem();"><label for="dApplyItem_10"> 019-111-1111</label></td>
                </tr>
                <tr>
                    <th>변경이전정보</th>
                    <td><label name="beforeItem"  class="beforItem">주소<br>서울시 서대문구 신촌동 11-111</label></td>
                    <th>변경요청정보</th>
                    <td ><textarea  rows="5"></textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div id="LblockButton">
        <a href="#"><input type="button" value="수정요청" onclick="doSomething(); return false;" /></a>
    </div>
</div>

<content tag="local-script">
    <script>

    </script>
</content>
</body>
</html>
