<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
<meta charset="utf-8"/>
<title>전형료정산</title>
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
		<li><span><a href="#">전형료정산</a></span></li>
		<li class="Llast"><span>전형료정산</span></li>
	</ul>
</div>

	<h1>전형료정산</h1>
</div>

<div id="LblockMainBody" >

<div id="LblockListTable01" class="LblockListTable">
	<table summary="전형별 지원현황">
		<caption>전형별 지원현황</caption>
		<thead>
			<tr>
				<th class="Lfirst">캠퍼스</th>
				<th>모집단위</th>
				<th>전형</th>
				<th>과정</th>
				<th>전형료</th>
				<th>지원자수</th>		
        <th class="LHSum">소계</th>									
        <th class="LHSum">합계</th>
			</tr>
		</thead>
		<tbody>
		  <tr>
	    	<td class="Lfirst" rowspan=6>서울캠퍼스</td>
				<td>화학공학과</td>
				<td>일반</td>
				<td>석사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    	<td rowspan=6 class="LCSum">175,000,000</td>	    	
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>일반</td>
				<td>박사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>일반</td>
				<td>통합</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>	    	    
		  <tr>
				<td>화학공학과</td>
				<td>외국인</td>
				<td>석사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>외국인</td>
				<td>박사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>외국인</td>
				<td>통합</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>	 		
		  <tr>
	    	<td class="Lfirst" rowspan=6>서울캠퍼스</td>
				<td>화학공학과</td>
				<td>일반</td>
				<td>석사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    	<td rowspan=6 class="LCSum">175,000,000</td>	    	
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>일반</td>
				<td>박사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>일반</td>
				<td>통합</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>	    	    
		  <tr>
				<td>화학공학과</td>
				<td>외국인</td>
				<td>석사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>외국인</td>
				<td>박사</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>
		  <tr>
				<td>화학공학과</td>
				<td>외국인</td>
				<td>통합</td>
				<td >75,000</td>
				<td >100</td>
	    	<td class="LCSum">75,000,000</td>
	    </tr>	    	    

	    <tr class="LTdata">
	    	<td colspan = 5>총계</td>
				<td >500</td>
				<td colspan=2>775,000,000</td>    
	    </tr>
		</tbody>
	</table>
</div>

</div>

<div id="LblockButton">
	<a href="#"><input type="button" value="엑셀파일 다운로드" onclick="doSomething(); return false;" /></a>
</div>

</div>



<content tag="local-script">
    <script>

    </script>
</content>
</body>
</html>

