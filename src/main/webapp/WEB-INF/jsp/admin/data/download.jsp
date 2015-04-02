<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
<meta charset="utf-8"/>
<title>데이터다운로드</title>
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
		<li><span><a href="#">데이터다운로드</a></span></li>
		<li class="Llast"><span>데이터다운로드</span></li>
	</ul>
</div>

	<h1>데이터다운로드</h1>
</div>

<div id="LblockMainBody" >
<div id="LblockSearch">
	<div>
		<div>
			<form action="">
				<table summary="다운로드 검색조건">
					<caption>다운로드 검색조건</caption>
					<tbody>
						<tr>
							<th><label for="sCampName">캠퍼스</label></th>
							<td>
								<select id="sCampName">
									<option value="01">-- 전체 --</option> 
									<option value="02">서울캠퍼스</option> 
									<option value="02">국제캠퍼스</option> 
									<option value="02">원주캠퍼스</option> 
								</select> 
							</td>
							<th><label for="sAdmsType">전형구분</label></th>
							<td>
								<select id="sAdmsType">
									<option value="01">-- 전체 --</option> 
									<option value="02">일반</option> 
									<option value="02">외국인</option> 
								</select> 
							</td>
							<th><label for="sCorsType">지원과정</label></th>
							<td>
								<select id="sCorsType">
									<option value="01">-- 전체 --</option> 
									<option value="02">석사학위과정</option> 
									<option value="02">박사학위과정</option> 
								</select> 
							</td>	
						</tr>
						<tr>																									
							<th><label for="sMidGruop">계열(대학)</label></th>
							<td>
								<select id="sMidGruop">
									<option value="01">-- 전체 --</option> 
									<option value="02">문과대학</option> 
									<option value="02">상과대학</option>
									<option value="02">이과대학</option> 
									<option value="02">공과대학</option> 									 
								</select> 
							</td>
							<th><label for="sAdmsDept">모집단위</label></th>
							<td>
								<select id="sAdmsDept">
									<option value="01">-- 전체 --</option> 
									<option value="02">기계공학과</option> 
									<option value="02">전기공학과</option>
									<option value="02">건축공학과</option> 
									<option value="02">토목환경공학과</option> 									 
								</select> 
							</td>																
					
						</tr>
					</tbody>
				</table>
                <input type="image" class="Limage" src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/btn_search.gif" /></a>
			</form>
		</div>
	</div>
</div>

<div id="LblockListTable01" class="LblockListTable">
	<table summary="전형별 지원현황">
		<caption>전형별 지원현황</caption>
		<thead>
			<tr>
				<th class="Lfirst">캠퍼스</th>
				<th>지원학과</th>
				<th>지원전형</th>
				<th>수험번호</th>				
				<th>학생정보</th>
				<th>연락처</th>				
				<th>지원일시</th>						
        <th class="Llast">결제정보</th>
			</tr>
		</thead>
		<tbody>
		  <tr class="Lfirst">
				<td>서울캠퍼스</td>
				<td>토목환경공학과</td>
				<td>일반<br>석사학위과정</td>		
				<td>15-A010001</td>					    	
				<td>홍길동<br>850101</td>
				<td>010-5555-****<br>hongildong@<br>yonsei.kr</td>
				<td>2014-09-29<br>15:00:11</td>
				<td>계좌이체<br>85,00000</td>					
	    </tr>
		  <tr>
				<td class="Lfirst">서울캠퍼스</td>
				<td>토목공학과</td>
				<td>일반<br>박사학위과정</td>		
				<td>15-A010002</td>					    	
				<td>김일남<br>830101</td>
				<td>010-5555-****<br>hongil@<br>yonsei.kr</td>
				<td>2014-09-29<br>15:00:11</td>
				<td>계좌이체<br>85,00000</td>				
	    </tr>
	    <tr>
				<td class="Lfirst">서울캠퍼스</td>
				<td>건축공학과</td>
				<td>일반<br>석사학위과정</td>		
				<td>15-A020001</td>					    	
				<td>이순신<br>840101</td>
				<td>010-5555-****<br>ngildong@<br>yonsei.kr</td>
				<td>2014-09-29<br>15:00:11</td>
				<td>계좌이체<br>85,00000</td>				
	    </tr>
	    <tr class="Llast">
				<td class="Lfirst">서울캠퍼스</td>
				<td>토목공학과</td>
				<td>일반<br>석사학위과정</td>
				<td>15-A010003</td>							    	
				<td>권율<br>900101</td>
				<td>010-5555-****<br>ongildong@<br>yonsei.kr</td>
				<td>2014-09-29<br>15:00:11</td>
				<td>계좌이체<br>85,00000</td>				
	    </tr>    
		</tbody>
	</table>
</div>

</div>

<div id="LblockButton">
	<a href="#"><input type="button" value="지원정보 일괄 다운로드" onclick="doSomething(); return false;" /></a>
	<a href="#"><input type="button" value="지원서류 일괄 다운로드" onclick="doSomething(); return false;" /></a>	
</div>


</div>


<content tag="local-script">
    <script>

    </script>
</content>
</body>
</html>

