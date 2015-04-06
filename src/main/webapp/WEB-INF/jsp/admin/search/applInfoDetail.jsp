<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
</head>
<body>



<div class="content">
    <div class="con_tit">
        <h2><span>지원서정보</span></h2>
        <div class="location"> HOME &gt; 지원서정보 &gt; <span>지원서정보</span> </div>

    </div>
    <div class="con_section">
        <h3 class="tit1">지원단위</h3>




<form id= "searchForm" method="post">
    <input type="hidden" name="applNo" value="${applInfo.applNo}" />
    <input type="hidden" name="userId" value="${applInfo.userId}" />
    <input type="hidden" name="admsNo" value="${applInfo.admsNo}" />
</form>
    <table class="tbl_typeA mb15" summary="지원 기본정보">
        <caption>지원 기본정보</caption>
        <colgroup>
            <col width="12%" />
            <col width="20%" />
            <col width="12%" />
            <col />
            <col width="12%" />
            <col width="25%" />
        </colgroup>
        <tbody>
			<tr>
				<th>수험번호</th>
				<td>${applInfo.applId}</td>				
				<th>전형구분</th>
				<td>${applInfo.admsTypeName}</td>
                <th>학연산 기관</th>
                <td><c:if test="${empty applInfo.ariInstName}" >해당없음</c:if><c:if test="${not empty applInfo.ariInstName}" >${applInfo.ariInstName}</c:if></td>
			</tr>
			<tr>
				<th>캠퍼스</th>
				<td>${applInfo.campName}</td>
                <th>지원구분</th>
                <td>${applInfo.applAttrName}</td>
                <th>지원일자</th>
                <td><fmt:formatDate value="${applInfo.applDate}" pattern="yyyy년 MM월 dd일 HH시 mm분" /></td>
            </tr>
			<tr>
                <th>대학</th>
                <td>${applInfo.collName}</td>
                <th>지원과정</th>
                <td>${applInfo.corsTypeName}</td>
                <th>전형료</th>
                <td>${applInfo.admsFee}</td>
			</tr>				
			<tr>
                <th>학과</th>
                <td>${applInfo.deptName}</td>
                <th>세부전공</th>
                <td>${applInfo.detlMajName}</td>
                <th>결제방법</th>
                <td>${applInfo.payTypeName}</td>
			</tr>
        </tbody>
	</table>

        <h3 class="tit1">지원자개인정보</h3>
        <table class="tbl_typeA mb15" summary="지원자개인정보">
            <caption>지원자개인정보</caption>
            <colgroup>
                <col width="20%" />
                <col width="33%" />
                <col width="14%" />
                <col width="33%" />
            </colgroup>

            <tbody>
			<tr>
				<th>성명</th>
				<td>${applInfo.korName}</td>
				<th>Name</th>
				<td>${applInfo.engName}</td>			
			</tr>
			<tr>
                <th>생년월일/주민번호</th>
                <c:choose>
                    <c:when test="${applList.admsNo == '15D'}">
                        <td>${applInfo.bornDay}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${applInfo.rgstBornDate}</td>
                    </c:otherwise>
                </c:choose>
				<th>Sur Name</th>
				<td>${applInfo.engSur}</td>			
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${applInfo.telNum}</td>		
				<th>핸드폰 번호</th>
				<td>${applInfo.mobiNum}"</td>				
			</tr>			
			<tr>
				<th>주소</th>
				<td>${applInfo.addr}<br>${applInfo.detlAddr}</td>
				<th>E-mail</th>
				<td>${applInfo.mailAddr}</td>				
			</tr>
			<tr>
				<th>비상연락대상</th>
				<td>${applInfo.emerContName}</td>
				<th>비상연락처</th>
				<td>${applInfo.emerContTel}</td>			
			</tr>												
		</tbody>
	</table>

        <div class="con_btn text-right">
            <a class="btn_set btnWhiteS" id="backBtn" href="#"><span>목록</span></a>
            <a class="btn_set btnRedS"  id="chgInfoBtn" href="#"><span>개인정보수정</span></a>
            <a class="btn_set btnBlueS" id="chgDeptBtn" href="#"><span>지원단위 변경요청</span></a>
            <a class="btn_set btnBlueS" id="chgEtcBtn" href="#"><span>기타정보 변경요청</span></a>
            <a class="btn_set btnBlackS" id="cancelBtn" href="#"><span>지원 취소요청</span></a>

        </div>
    </div>
    <!-- /con_section -->
</div>
<!-- /content -->
</div>
<!-- /container -->


<content tag="local-script">
    <script>
    
    jQuery(document).ready( function(){

    	jQuery('#chgDeptBtn').on('click', function(event) {
	   		event.preventDefault();
	   		jQuery('#searchForm').attr("action", '${contextPath}/admin/modification/changeUnit');
	   		document.getElementById('searchForm').submit();

	   	});
    	jQuery('#chgInfoBtn').on('click', function(event) {
	   		event.preventDefault();
	   		jQuery('#searchForm').attr("action", '${contextPath}/admin/modification/changeInfo');
	   		document.getElementById('searchForm').submit();	   		
	   	});
    	jQuery('#chgEtcBtn').on('click', function(event) {
	   		event.preventDefault();
            jQuery('#searchForm').attr("action", '${contextPath}/admin//modification/changeEtc');
            document.getElementById('searchForm').submit();
        });
        jQuery('#cancelBtn').on('click', function(event) {
            event.preventDefault();
            jQuery('#searchForm').attr("action", '${contextPath}/admin//modification/cancelAppl');
            document.getElementById('searchForm').submit();
        });
        jQuery('#downloadPdf').on('click', function(event) {
            event.preventDefault();
            jQuery('#searchForm').attr("action", '${contextPath}/admin//search/download');
            document.getElementById('searchForm').submit();
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

