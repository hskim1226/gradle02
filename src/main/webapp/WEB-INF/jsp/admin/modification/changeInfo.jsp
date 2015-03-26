<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>

</head>
<body>
<div class="content">
    <div class="con_tit">
        <h2><span>원서수정</span></h2>
        <div class="location"> HOME &gt; 지원변경/원서수정 &gt; <span>원서수정</span> </div>
    </div>

    <div class="con_section">
        <form id ="applicantSearchForm" action="${contextPath}/admin//modification/changeInfo" method="post">
        <div class="srch_box"> <strong><label for="applId">수험번호</label></strong>
            <input type="text" name="applId" id="applId" class="ipt_txt1"  value="${applicantSearchForm.applId}" >
          <span class="btnBlueS">
          <input type="submit" value="검색" class="btnBox" id="searchBtn"  />
          </span> </div>
        </form>


        <%@include file="applicationInfo.jsp"%>

        <h3 class="tit1">지원서 상세정보</h3>
        <table class="tbl_typeA mb15" summary="지원서 상세정보">
            <caption>
                지원자 상세정보
            </caption>
            <colgroup>
                <col width="20%" />
                <col width="33%" />
                <col width="14%" />
                <col width="33%" />
            </colgroup>

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
            </c:if>
            </tbody>
        </table>
        <table class="tbl_typeA" summary="지원서 상세정보">
            <caption>
                지원자 상세정보
            </caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
            <tbody>


            <tr>
            <th><label >변경이전정보</label></th>
            <td colspan="5"><label  id="befVal" ></label></td>
            <input type="hidden" id="defValInput" name ="befVal"> </input>
            </tr>
            <tr>
            <th>변경이후정보</th>
            <td colspan="5"><input type="text"  name="aftVal"  id="aftVal" class="ipt_txt1" ></td>
            </tr>
            <tr>
            <th>변경사유</th>
            <td colspan="5"><textarea  r rows ="5" cols="60" name="cnclResn"  id="cnclResn" class="ipt_area1" ></textarea></td>
            </tr>
            </tbody>
        </table>

        <c:if test="${applInfo.applNo != null}" >
            <div class="con_btn text-right">
                <a class="btn_set btnRedS" id="changeBtn" ><span>수정요청</span></a>
            </div>
        </c:if>
    </div>
    <!-- /con_section -->
</div>
<!-- /content -->

	    
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