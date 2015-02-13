<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
<meta charset="utf-8"/>
<title>지원서정보</title>
<link rel="stylesheet" type="text/css" href="../css/import.css" />
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/dui_base.js"></script>
<script type="text/javascript" src="../js/dui_effect.js"></script>
<script type="text/javascript" src="../js/dui_dragndrop.js"></script>
<script type="text/javascript" src="../js/dui_hhmenu.js"></script>
<script type="text/javascript" src="../js/dui_tree.js"></script>
<script type="text/javascript" src="../js/dui_slidemenu.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>
<body>




<div id="LblockMain">
<div id="LblockPageTitle">
<div id="LblockPageLocation">
	<ul>
		<li class="Lfirst"><span><a href="#">HOME</a></span></li>
		<li><span><a href="#">지원서정보</a></span></li>
		<li class="Llast"><span>지원서정보</span></li>
	</ul>
</div>

	<h1>지원서정보</h1>
</div>

<div id="LblockMainBody" >
<div id="LblockPageSubtitle01" class="LblockPageSubtitle">
	<h2>지원단위</h2>
</div>

<div id="LblockDetail01" class="LblockDetail">
<form id= "searchForm" method="post"><input type="hidden" name="applNo" value="${applInfo.applNo}" /></form>
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
                <th>지원과정</th>
                <td>${applInfo.corsTypeName}</td>
                <th>전형료</th>
                <td><fmt:formatNumber type="currency"   maxFractionDigits="3" value="${applInfo.admsFee}" /></td>

            </tr>
			<tr>
                <th>대학</th>
                <td>${applInfo.collName}</td>
                <th>세부전공</th>
                <td>${applInfo.detlMajName}</td>
                <th>결제방법</th>
                <td></td>
			</tr>				
			<tr>
                <th>학과</th>
                <td>${applInfo.deptName}</td>
                <th>학연산 기관</th>
                <td><c:if test="${empty applInfo.ariInstName}" >해당없음</c:if><c:if test="${not empty applInfo.ariInstName}" >${applInfo.ariInstName}</c:if></td>
                <th>지원상태</th>
				<td>${applInfo.applStsName}</td>					
			</tr>
            <tr>


            </tr>
        </tbody>
	</table>
</div>
       


<div id="LblockPageSubtitle02" class="LblockPageSubtitle">
	<h2>지원자개인정보</h2>
</div>

<div id="LblockDetail02" class="LblockDetail">
	<table summary="지원자개인정보">
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
</div>
<div id="LblockSubbutton01" class="LblockSubbutton">
    <a href="#"><input type="button"  id="chgDeptBtn" value="지원단위 변경"  /></a>
</div>
<div id="LblockcChgInfoBtn" class="LblockSubbutton">
	<a href="#"><input type="button"  id="chgInfoBtn" value="개인 정보수정" /></a>	
</div>
<div id="LblockCancelBtn"class="LblockSubbutton">
	<a href="#"><input type="button"  id="cancelBtn" value="지원 취소"  /></a>
</div>
    <div id="LblockBacklBtn"class="LblockSubbutton">
        <a href="#"><input type="button"  id="backBtn" value="목록"  /></a>
    </div>
</div>

</div>



</div>



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
    	jQuery('#cancelBtn').on('click', function(event) {
	   		event.preventDefault();
            alert("아직 개발중입니다")
            <%--
            jQuery('#searchForm').attr("action", '${contextPath}/admin//modification/cancel');
            document.getElementById('searchForm').submit();--%>
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

