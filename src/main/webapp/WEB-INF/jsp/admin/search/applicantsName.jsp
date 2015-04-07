<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>    </title>
</head>
<body>


<div class="content">
<div class="con_tit">
    <h2><span>지원자검색</span></h2>
    <div class="location"> HOME &gt; 지원자관리 &gt; <span>성명검색</span> </div>

</div>


    <div class="con_section">
        <form:form commandName="searchPageForm"  method="post" role="form" action="${contextPath}/admin/search/applicants/nameSearch" id="search-form">
            <div class="srch_box">

                <input type="hidden" id="page-number-hidden" name="page.no" value="${searchPageForm.page.no}" />
                <p class="srch_tit"><i class="fa fa-search"></i>지원자명 검색</p>

                <label for="korName">한글 이름</label>
                <form:input path="korName" id="korName" />&nbsp;&nbsp;&nbsp;
                <label for="rgstNo">주민등록번호</label>
                <form:input path="rgstNo" id="rgstNo" />&nbsp;&nbsp;&nbsp;
                <p class="srch_tit"><i class="fa fa-search"></i>영문성명 검색</p>
                <label for="engSur">SUR</label>
                <form:input path="engSur" id="engSur" onClick ="resetInputs()" />&nbsp;&nbsp;&nbsp;
                <label for="engName">NAME</label>
                <form:input path="engName" id="engName" onClick ="resetInputs()"/>&nbsp;&nbsp;&nbsp;


      <span class="btnBlueS">
          <input type="submit" value="검색" class="btnBox" id='nameSearchBtn' />
          </span>
            </div>
        </form:form>
        <table class="tbl_typeA text-center" summary="전형별 지원현황">
            <caption>전형별 지원현황</caption>
            <thead>
            <tr>
                <th>수험번호</th>
                <th>캠퍼스</th>
                <th>지원학과<br>세부전공</th>
                <th>지원전형<br>지원과정</th>
                <th>성명<br>생년월일</th>
                <th>전화번호<br>이메일</th>
                <th>결제방법<br>결제금액</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${applList.size() == 0}" >
                <tr >
                    <td colspan="7">해당 정보 없음</td>
                </tr>
            </c:if>
            <c:forEach var="applList" items="${applList}" varStatus="status">
                <tr class="applList"  applNo="${applList.applNo}">
                    <td>${applList.applId}</td>
                    <td>${applList.campName}</td>
                    <td>${applList.deptName}
                        <c:if test ="${applList.detlMajCode != 'DM000'}">
                            <br>${applList.detlMajName}
                        </c:if>
                    </td>
                    <td>
                        <c:if test ="${applList.admsNo == '15B'}">
                            ${applList.applAttrName}<br>
                        </c:if>
                            ${applList.corsTypeName}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${applList.admsNo == '15D'}">
                                ${applList.engName},${applList.engSur} <br> ${applList.bornDay}
                            </c:when>
                            <c:otherwise>
                                ${applList.korName} <br> ${applList.rgstBornDate}
                            </c:otherwise>

                        </c:choose>
                    </td>
                    <td>${applList.mobiNum} <br>${applList.mailAddr} </td>
                    <td>${applList.payTypeName}<br>${applList.admsFee} </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="page_number">
            <fmt:parseNumber var="indexCount" integerOnly= "true" value="${searchPageForm.page.totalCount/searchPageForm.page.rows + 1}" />
            <c:if test="${indexCount != 0}">
            <a class="Lbegin"><span><a href="#" class="end" onclick="movePage(1); return false;">1page</a>
        <c:if test="${searchPageForm.page.no-1 > 0}">
           <a href="#" onclick="movePage(${searchPageForm.page.no-1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_previous.gif" alt="이전페이지" /></a>
        </c:if>
        <c:forEach begin="1" end="${indexCount}" var="pageNumIndex">
            <c:if test="${searchPageForm.page.no==pageNumIndex}">
               <span class="active">${pageNumIndex}</span>
            </c:if>
            <c:if test="${searchPageForm.page.no!=pageNumIndex}">
                <a href="#" onclick="movePage(${pageNumIndex}); return false;">${pageNumIndex}</a>
            </c:if>
        </c:forEach>
        <c:if test="${searchPageForm.page.no < indexCount}">
            <a href="#" onclick="movePage(${searchPageForm.page.no+1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_next.gif" alt="다음페이지" /></a>
        </c:if>
        <a href="#" class="end" onclick="movePage(${indexCount}); return false;">${indexCount}page</a>
    </c:if>
        </div>


        <%--

                    <div id="LblockcChgInfoBtn" class="con_btn text-right">
                        <a class="btn_set btnBlueS" id="downBtn"  href="#"><span>엑셀파일 다운로드</span></a>
                    </div>
                    --%>
    </div>
    <!-- /con_section -->
</div>
<!-- /content -->

<content tag="local-script">

    <script>

    jQuery(document).ready( function(){
        jQuery(".applList").on('click', function(){
            location.href = "${contextPath}/admin/search/applicant/applInfoDetail?applNo="+jQuery(this).attr('applNo');
        }).css("cursor","pointer"); 
        
	        
		jQuery('#applId').on('click', function(event) {
        	event.preventDefault();
    		jQuery("#korName").val('');
    		jQuery("#rsdnNo").val('');
    		jQuery("#engSur").val('');
    		jQuery("#engName").val('');
    		jQuery("#admsNo").val(''); 
    		jQuery("#campCode").val('');    
    		jQuery("#collCode").val('');        		
    	});	  

		jQuery('#korName, #rsdnNo, #engSur, #engName').on('click', function(event) {
        	event.preventDefault();
    		jQuery("#applId").val('');
    		jQuery('#admsNo, #campCode, #collCode').val('');
    	});	
		
		jQuery('#admsNo, #campCode, #collCode').on('click', function(event) {
        	event.preventDefault();
    		jQuery("#applId").val('');
    		jQuery('#korName, #rsdnNo, #engSur, #engName').val(''); 	
    	});

        jQuery(".btnBox").on('click', function(e) {
            e.preventDefault();
            submitForm();
        });

        function submitForm(){
            jQuery("#page-number-hidden").val(1);
            jQuery("#search-form").submit();

        };
        function attachChangeEvent( sourceId, context ) {
            var $source = jQuery('#' + sourceId);

            $source.on('change', function(event) {
                var info, targetId, valueKey, labelKey, url, clean, addon, i;
                var baseUrl = '${contextPath}/common/code';
                var val = this.options[this.selectedIndex].value;

                info = context;
                if (context.hasOwnProperty($source.val())) {
                    info = context[$source.val()];
                }

                targetId = info.targetId ? info.targetId : context.targetId;
                if( !targetId ) {
                    return;
                }

                valueKey = info.valueKey ? info.valueKey : context.valueKey;
                labelKey = info.labelKey ? info.labelKey : context.labelKey;
                url = info.url ? info.url : context.url;
                if( url && typeof url === 'function' ) {
                    baseUrl += url(val);
                } else if( url ) {
                    baseUrl += url;
                }

                clean = info.clean ? info.clean : context.clean;
                if (typeof clean === 'string') {
                    clean = [].concat( clean );
                }
                clean = [].concat( targetId, clean );
                for (i = 0; i < clean.length; i++) {
                    jQuery('#' + clean[i]).children('option').filter(function() {
                        return this.value !== '';
                    }).remove();
                    jQuery('#' + clean[i]).trigger('change');
                }

                jQuery.ajax({
                    type: 'GET',
                    url: baseUrl,
                    success: function(e) {
                        var ec = JSON && JSON.parse(e) || $.parseJSON(e);
                        if(ec.result && ec.result === 'SUCCESS') {
                            var $target = jQuery('#' + targetId);
                            var data = JSON && JSON.parse(ec.data) || $.parseJSON(ec.data);
                            jQuery(data).each(function (i, item) {
                                var $op = jQuery('<option>').attr({
                                            'value': item[valueKey],
                                            'label': item[labelKey]}
                                )
                                for (var key in item) {
                                    if (key !== valueKey && key !== labelKey) {
                                        $op.attr(key, item[key]);
                                    }
                                }
                                $op.appendTo($target);
                            });
                        }
                    },
                    error: function(e) {
                        if(console) console.log(e);
                    }
                });
            });
        }

        attachChangeEvent( 'campCode',
                {
                    targetId: 'collCode',
                    valueKey: 'collCode',
                    labelKey: 'collName',
                    url: function(arg) {
                        return '/college/' + arg;
                    }
                }
        );
    });
    function movePage(pageNumIndex){
        jQuery("#page-number-hidden").val(pageNumIndex);
        jQuery("#search-form").submit();
    };

    </script>
</content>
</body>
</html>
