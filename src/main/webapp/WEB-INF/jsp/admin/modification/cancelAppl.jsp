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
                <li><span><a href="#">지원취소</a></span></li>
                <li class="Llast"><span>지원취소</span></li>
            </ul>
        </div>

        <h1>지원단위변경</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="" method="post">
                        <table summary="지원취소 대상자검색">
                            <caption>지원취소 대상자검색</caption>
                            <tr>
                                <th><label for="applId">수험번호</label></th>
                                <td><input type="text" class="Ltext" id="applId" name="applId" size="15" readonly ="true" value="${applInfo.applId}"><img class="Lbtn" src="../images/btn_icon_search.gif" alt="검색버튼" /></td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="image" class="Limage"  id="searchBtn" src="${contextPath}/img/admin/btn_search.gif" /></a>

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
                    <td>${applInfo.applId}</td>
                    <th>전형구분</th>
                    <td>${applInfo.admsTypeName}</td>
                    <th>지원일자</th>
                    <td><fmt:formatDate value="${applInfo.applDate}" pattern="yyyy년 MM월 dd일 HH시 mm분" /></td>
                </tr>
                <tr>
                    <th>캠퍼스</th>
                    <td>${applInfo.campName}</td>
                    <th>대학</th>
                    <td>${applInfo.collName}</td>
                    <th>학과</th>
                    <td>${applInfo.deptName}</td>
                </tr>
                <tr>
                    <th>학연산 기관</th>
                    <td><c:if test="${empty applInfo.ariInstName}" >해당없음</c:if><c:if test="${not empty applInfo.ariInstName}" >${applInfo.ariInstName}</c:if></td>
                    <th>지원과정</th>
                    <td>${applInfo.corsTypeName}</td>
                    <th>세부전공</th>
                    <td>${applInfo.detlMajName}</td>
                </tr>
                <tr>
                    <th>전형료</th>
                    <td><fmt:formatNumber type="currency"   maxFractionDigits="3" value="${applInfo.admsFee}" /></td>
                    <th>결제방법</th>
                    <td></td>
                    <th>지원상태</th>
                    <td>${applInfo.applStsName}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="LblockPageSubtitle02" class="LblockPageSubtitle">
            <h2>지원서 상세정보</h2>
        </div>

        <div id="LblockDetail02" class="LblockDetail">
                <table summary="지원서 상세정보">
                    <caption>지원자개인정보</caption>

                    <tbody>

                    <tr>
                        <th>성명</th>
                        <td>${applInfo.korName}</td>
                        <th>Name</th>
                        <td>${applInfo.engName}</td>
                    </tr>
                    <tr>
                        <th>생년월일/주민번호</th>
                        <td>${applInfo.rgstNo}</td>
                        <th>Sur Name</th>
                        <td>${applInfo.engSur}</td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td>${applInfo.telNum}</td>
                        <th>핸드폰 번호</th>
                        <td>${applInfo.mobiNum}</td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>${applInfo.detlAddr}</td>
                        <th>E-mail</th>
                        <td>${applInfo.mailAddr}</td>
                    </tr>
                    <tr>
                        <th>비상연락대상</th>
                        <td>${applInfo.emerContName}</td>
                        <th>비상연락처</th>
                        <td>${applInfo.emerContTel}</td>
                    </tr>
                    </tr>
                    <tr></tr>
                    </tbody>
                </table>
                <div id="LblockPageSubtitle03" class="LblockPageSubtitle">
                    <h2>지원 취소내용</h2>
                </div>
                <div id="LblockDetail03" class="LblockDetail">

                    <form id ="changeForm" action="${contextPath}/admin/modification/requestCancel" method="post">
                        <input type="hidden" name="applNo" value=${applInfo.applNo}> </input>
                        <input type="hidden" name="admsNo" value=${applInfo.admsNo}> </input>
                        <input type="hidden" id="befValInput" name ="befVal"> </input>
                        <input type="hidden" id="aftValInput" name ="aftVal"> </input>
                    <table summary="변경요청 지원정보">
                        <caption>변경요청 지원정보</caption>
                        <tbody>
                        <tr>
                            <th>취소사유</th>
                            <td colspan="5"><textarea  r rows ="5" cols="60" name="cnclResn"  id="cnclResn" ></textarea></td>
                        </tr>
                        </tbody>
                    </table>
                    </form>
                </div>
        </div>

        <div id="LblockButton">
            <a href="#"><input type="button"  id="backBtn" value="상세정보"  /></a>
            <a href="#"><input type="button"  id="changeBtn" value="지원취소요청" /></a>
        </div>
    </div>
</div>

<content tag="local-script">
    <script>
        jQuery(document).ready(function() {
            jQuery('#changeBtn').on('click', function(e) {
                event.preventDefault();
                if (confirm('지원을 취소 하시겠습니까?')) {

                    jQuery('#befValInput').val(
                                    "${applInfo.admsTypeName}" +"-"+
                                    "${applInfo.ariInstName}" +"-"+
                                    "${applInfo.campName}" +"<br>"+
                                    "${applInfo.collName}" +"-"+
                                    "${applInfo.deptName}" +"<br>"+
                                    "${applInfo.corsTypeName}" +"<br>"+
                                    "${applInfo.detlMajName}" );
                    jQuery('#aftValInput').val('--지원 취소--');
                    jQuery('#changeForm').submit();
                }

            });
            jQuery('#backBtn').on('click', function(event) {
                event.preventDefault();
                history.go(-1);
            });

        });
    </script>
</content>
</body>
</html>
