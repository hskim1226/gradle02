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
                    <form action="" method="post">
                        <table summary="원서수정 대상자검색">
                            <caption>원서수정 대상자검색</caption>
                            <tbody>
                            <tr>
                                <th><label for="applId">수험번호</label></th>
                                <td><input type="text" class="Ltext" id="applId" name="applId" size="15" />${applInfo.applId}</td>
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
                <form id ="changeForm" action="${contextPath}/admin/modification/requestChangeInfo" method="post">
                    <input type="hidden" name="applNo" value=${applInfo.applNo}> </input>
                    <input type="hidden" name="admsNo" value=${applInfo.admsNo}> </input>
                <tr>
                    <th>성명</th>
                    <td><input type="radio" class="Lradio" id="korName" name="infoRadio" value ="korName" ><label id="lbkorName"  for="korName" >${applInfo.korName}</label></td>
                    <th>Name</th>
                    <td><input type="radio" class="Lradio" id="engName" name="infoRadio" value ="engName"><label id="lbengName" for="engName">${applInfo.engName}</td>
                </tr>
                <tr>
                    <th>생년월일/주민번호</th>
                    <td><input type="radio" class="Lradio" id="rgstNo" name="infoRadio" value ="rgstNo"><label id="lbrgstNo" for="rgstNo">${applInfo.rgstNo}</td>
                    <th>Sur Name</th>
                    <td><input type="radio" class="Lradio" id="engSur" name="infoRadio" value ="engSur"><label id="lbengSur" for="engSur">${applInfo.engSur}</td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td><input type="radio" class="Lradio" id="telNum" name="infoRadio" value ="telNum"><label id="lbtelNum" for="telNum">${applInfo.telNum}</td>
                    <th>핸드폰 번호</th>
                    <td><input type="radio" class="Lradio" id="mobiNum" name="infoRadio" value ="mobiNum"><label id="lbmobiNum"  for="mobiNum">${applInfo.mobiNum}</td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td><input type="radio" class="Lradio" id="addr" name="infoRadio" value ="addr"><label id="lbaddr"  for="addr">${applInfo.addr}<br>${applInfo.detlAddr}</td>
                    <th>E-mail</th>
                    <td><input type="radio" class="Lradio" id="mailAddr" name="infoRadio" value ="mailAddr"><label id="lbmailAddr"  for="mailAddr">${applInfo.mailAddr}</td>
                </tr>
                <tr>
                    <th>비상연락대상</th>
                    <td><input type="radio" class="Lradio" id="emerContName" name="infoRadio" value ="emerContName" ><label id="lbemerContName" for="emerContName">${applInfo.emerContName}</td>
                    <th>비상연락처</th>
                    <td><input type="radio" class="Lradio" id="emerContTel" name="infoRadio" value ="emerContTel"><label id="lbemerContTel" for="emerContTel">${applInfo.emerContTel}</td>
                </tr>
                </tr>
                <tr>
                <tr>
                    <th><label >변경이전정보</label></th>
                    <td><label  id="beforeItem" ></label></td>
                    <th>변경요청정보</th>
                    <td ><input type="text"  name="afterItem"  id="afterItem" ></td>
                </tr>
                </form>
                </tbody>

            </table>
        </div>
    </div>

    <div id="LblockButton">
        <a href="#"><input type="button" id="changeBtn" value="수정요청"  /></a>
    </div>
    
 
	    
    	      
	</div>
	
	    
<content tag="local-script">
    <script>
    jQuery(document).ready(function() {
        jQuery('input[name=infoRadio]:radio').click(function() {

            var chgItem = jQuery("input[name='infoRadio']:checked").val();
            jQuery('#beforeItem').text( jQuery('#lb'+chgItem).text());

        });

        jQuery('#changeBtn').on('click', function(e) {
            event.preventDefault();
            alert("아직 개발중입니다")
            <%--
            if (confirm('지원자 정보를 수정하시겠습니까?')) {
                jQuery('#changeForm').submit();
            }
            --%>
        });
        jQuery('#searchBtn').on('click', function(e) {
            event.preventDefault();
            alert("아직 개발중입니다")
                var newUrl= "${contextPath}/admin/modification/changeInfo";
            <%--
                /* 			newUrl = newUrl +"?applId="+jQuery("#applId").val();
                 newUrl = newUrl +"&korName="+jQuery("#korName").val();
                 newUrl = newUrl +"&rsdnNo="+jQuery("#rsdnNo").val();    */
                location.href =newUrl;
                /* 			jQuery('#searchBtn').submit(); */
            --%>
         });
    });
    </script>
</content>
</body>
</html>
