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
            <div><div>
                <form id ="applicantSearchForm" action="${contextPath}/admin//modification/changeInfo" method="post">
                <table summary="원서수정 대상자검색">
                    <caption>원서수정 대상자검색</caption>
                    <tbody>

                        <th><label for="applId">수험번호</label></th>
                        <td><input type="text" class="Ltext" id="applId" name="applId" size="15"  value="${applicantSearchForm.applId}">
                            <img class="Limage" id="searchBtn"  src="${contextPath}/img/admin/repository/btn_search.gif" alt="검색버튼" />
                        </td>

                    </tbody>
                </table>
                </form>
            </div></div>
        </div>
    </div>


        <%@include file="applicationInfo.jsp"%>


        <div id="LblockPageSubtitle02" class="LblockPageSubtitle">
            <h2>지원서 상세정보</h2>
        </div>

        <div id="LblockDetail02" class="LblockDetail">
            <form id ="changeForm" action="${contextPath}/admin/modification/requestChangeInfo" method="post">
                <input type="hidden" name="applNo" value=${applInfo.applNo}> </input>
                <input type="hidden" name="admsNo" value=${applInfo.admsNo}> </input>
            <table summary="지원서 상세정보">
                <caption>지원자개인정보</caption>

                <tbody>
                <c:if test="${applInfo.applNo == null}" >
                    <tr >
                        <td colspan="7">해당 정보 없음</td>
                    </tr>
                </c:if>
                <c:if test="${applInfo.applNo != null}" >
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
                <tr></tr>
                </tbody>
                </c:if>
                </table>

                <c:if test="${applInfo.applNo != null}" >
                <div><br>
                <table summary="지원서 상세정보">
                    <tbody>
                <tr>
                    <th><label >변경이전정보</label></th>
                    <td colspan="5"><label  id="befVal" ></label></td>
                    <input type="hidden" id="defValInput" name ="befVal"> </input>
                </tr>
                <tr>
                    <th>변경이후정보</th>
                    <td colspan="5"><input type="text"  name="aftVal"  id="aftVal" ></td>
                </tr>
                <tr>
                    <th>변경사유</th>
                    <td colspan="5"><textarea  r rows ="5" cols="60" name="cnclResn"  id="cnclResn" ></textarea></td>
                </tr>

                    </tbody>

            </table></div>
                    </c:if>
            </form>
        </div>
    <c:if test="${applInfo.applNo != null}" >
        <div id="LblockButton">

            <a href="#"><input type="button" id="changeBtn" value="수정요청"  /></a>
        </div>
    </c:if>
        </div>

 
	    </body>

	</div>
	
	    
<content tag="local-script">
    <script>
    jQuery(document).ready(function() {
        jQuery('input[name=infoRadio]:radio').click(function() {

            var chgItem = jQuery("input[name='infoRadio']:checked").val();
            if( jQuery('#lb'+chgItem).text() != null && jQuery('#lb'+chgItem).text()!='') {
                jQuery('#befVal').text(jQuery('#lb' + chgItem).text());
                jQuery('#defValInput').val(jQuery('#lb' + chgItem).text());
            }else{
                jQuery('#befVal').text('-미입력-');
                jQuery('#defValInput').val('-미입력-');
            }

        });

        jQuery('#changeBtn').on('click', function(e) {
            event.preventDefault();

            if (confirm('지원자 정보를 수정하시겠습니까?')) {
                jQuery('#changeForm').submit();
            }

        });

        jQuery('#searchBtn').on('click', function(event) {
            jQuery('#applicantSearchForm').submit();
        });

    });
    </script>
</content>
</body>
</html>
