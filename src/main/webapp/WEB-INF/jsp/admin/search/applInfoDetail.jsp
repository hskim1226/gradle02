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


<form id= "searchForm" method="post">
    <input type="hidden" name="applNo" value="${applInfo.applNo}" />
    <input type="hidden" name="userId" value="${applInfo.userId}" />
    <input type="hidden" name="admsNo" value="${applInfo.admsNo}" />
</form>

        <%@include file="../modification/applicationInfo.jsp"%>
        <%@include file="../modification/applicantInfo.jsp"%>
            <c:if test = "${applInfo.citzCntrCode != '118'}">
        <%@include file="../modification/applicantFron.jsp"%>
            </c:if>
        <div class="con_btn text-right">
            <a class="btn_set btnWhiteS" id="backBtn" href="#"><span>목록</span></a>
            <a class="btn_set btnWhiteS" id="pdfDownBtn" href="#"><span>원서다운로드</span></a>
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

        jQuery("#pdfDownBtn").on('click', function(event){
            event.preventDefault();
            location.href = "${contextPath}/admin/search/pdfDownload?applNo=${applInfo.applNo}";

    });


    });
</script>
</content>
</body>
</html>

