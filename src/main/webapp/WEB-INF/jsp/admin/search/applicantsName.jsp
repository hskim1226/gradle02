<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title>    </title>
    <script type="text/javascript">



  
    </script>
</head>
<body>

<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
            <ul>
                <li class="Lfirst"><span><a href="#">HOME</a></span></li>
                <li><span><a href="#">지원자관리</a></span></li>
                <li class="Llast"><span>지원자검색</span></li>
            </ul>
        </div>

        <h1>지원자검색</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <form:form commandName="searchPageForm"  method="post" role="form" action="${contextPath}/admin/search/applicants/nameSearch" id="search-form">
                    <input type="hidden" id="page-number-hidden" name="page.no" value="${searchForm.page.no}" />
                    <table summary="지원현황 검색조건">
                        <caption>지원현황 검색조건</caption>
                        <tbody>
                        <tr><th class="Cat">지원자검색 : </th>
                            <th>
                                <form:label path="korName">한글 이름</form:label></th>
                            <td>
                            <div class="col-sm-9">
                                <form:input path="korName" cssClass="Ltext"/>
                            </div></td>
                            <th>
                                <form:label path="rgstNo">주민등록번호</form:label></th>
                            <td>
                                <div class="col-sm-9">
                                    <form:input path="rgstNo" cssClass="Ltext"/>
                                </div></td>
                        </tr>
                        <tr><th class="Cat"></th>
                            <th>
                                <form:label path="engSur">SUR</form:label></th>
                            <td>
                                <div class="col-sm-9">
                                    <form:input path="engSur" cssClass="Ltext" onClick ="resetInputs()"/>
                                </div></td>
                            <th>
                                <form:label path="engName">NAME</form:label></th>
                            <td>
                                <div class="col-sm-9">
                                    <form:input path="engName" cssClass="Ltext" onClick ="resetInputs()"/>
                                </div></td>
                        </tr>
                        </tbody>
                    </table>
                    <input id="nameSearchBtn" type='image' class="Limage" src="${contextPath}/img/admin/btn_search.gif" />
                </form:form>
            </div>
        </div>

        <div id="LblockListTable01" class="LblockListTable">
            <table summary="전형별 지원현황" >
                <caption>전형별 지원현황</caption>
                <thead>
                <tr>
                    <th class="Lfirst">수험번호</th>
                    <th>캠퍼스</th>
                    <th>지원학과</th>
                    <th>지원전형</th>
                    <th>학생정보</th>
                    <th>연락처</th>
                    <th>결제내역</th>
                </tr>
                </thead>

                    <c:if test="${applList.size() == 0}" >
                        <tr >
                            <td colspan="7">해당 정보 없음</td>
                        </tr>
                    </c:if>
                   <c:forEach var="applList" items="${applList}" varStatus="status">
                    <tr class="<c:if test="${status.index == 0}">Lfirst </c:if>applList" applNo="${applList.applNo}">
                        <td>${applList.applId}</td>
                        <td>${applList.campName}</td>
                        <td>${applList.deptName}</td>
                        <td >${applList.corsTypeName}</td>
                        <td >${applList.korName} <br> ${applList.rgstNo}</td>
                        <td >${applList.mobiNum} <br>${applList.mailAddr} </td>
                        <td >${applList.applStsName}</td>
                    </tr>
                    </c:forEach>
            </table>
            <ul>
                <fmt:parseNumber var="indexCount" integerOnly= "true" value="${searchPageForm.page.totalCount/searchPageForm.page.rows + 1}" />
                <c:if test="${indexCount != 0}">
                    <li class="Lbegin"><span><a href="#" onclick="movePage(1); return false;">1page</a></span></li>
                    <c:if test="${searchPageForm.page.no-1 > 0}">
                        <li class="Lprevious"><span><a href="#" onclick="movePage(${searchPageForm.page.no-1}); return false;"><img src="${contextPath}/img/admin/list_page_previous.gif" alt="이전페이지" /></a></span></li>
                    </c:if>
                    <c:forEach begin="1" end="${indexCount}" var="pageNumIndex">
                        <c:if test="${searchPageForm.page.no==pageNumIndex}">
                            <li class="Lfirst"><span>${pageNumIndex}</span></li>
                        </c:if>
                        <c:if test="${searchPageForm.page.no!=pageNumIndex}">
                            <li><span><a href="#" onclick="movePage(${pageNumIndex}); return false;">${pageNumIndex}</a></span></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${searchPageForm.page.no < indexCount}">
                        <li class="Lnext"><span><a href="#" onclick="movePage(${searchPageForm.page.no+1}); return false;"><img src="${contextPath}/img/admin/list_page_next.gif" alt="다음페이지" /></a></span></li>
                    </c:if>
                    <li class="Lend"><span><a href="#" onclick="movePage(${indexCount}); return false;">${indexCount}page</a></span></li>
                </c:if>
            </ul>
            
        </div>
    </div>
</div>

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

        jQuery(".Limage").on('click', function(e) {
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
                        if(e.result && e.result === 'SUCCESS') {
                            var $target = jQuery('#' + targetId);
                            var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);
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
