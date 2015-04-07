<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<div class="content">
<div class="con_tit">
    <h2><span>변경처리현황</span></h2>
    <div class="location"> HOME &gt; 지원변경/취소관리 &gt; <span>변경처리현황</span> </div>

</div>

<div class="con_section">
    <form:form commandName="changeSearchPageForm"  method="post" role="form" action="${contextPath}/admin/modification/changeList" id="search-form">
    <div class="srch_box">

        <input type="hidden" id="page-number-hidden" name="page.no" value="${searchPageForm.page.no}" />
        <p class="srch_tit"><i class="fa fa-search"></i>지원현황 검색조건</p>



        <label for="applChgCode"><strong>변경요청구분</strong></label>
        <form:select path="applChgCode" id="applChgCode" class="ipt_slt1" >
            <form:option value="" label="--전체--" />
            <form:options items="${selection.applChgCodeList}" itemValue="code" itemLabel="codeVal"/>
        </form:select>&nbsp;&nbsp;&nbsp;
        <label for="chgStsCode"><strong>처리결과</strong></label>
        <form:select path="chgStsCode" id="chgStsCode" class="ipt_slt1">
            <form:option value="" label="--전체--" />
            <form:options items="${selection.chgStsCodeList}" itemValue="code" itemLabel="codeVal"/>
        </form:select>&nbsp;&nbsp;&nbsp;

        <p class="srch_tit"><i class="fa fa-search"></i>지원현황 검색조건</p>
        <label for="applChgCode"><strong>변경요청구분</strong></label>
        <form:select path="admsNo" id="admsNo" class="ipt_slt1">
            <form:option value="" label="--전체--" />
            <form:options items="${selection.admsList}" itemValue="admsNo" itemLabel="admsNo"/>
        </form:select>&nbsp;&nbsp;&nbsp;

        <label for="campCode"><strong>캠퍼스</strong></label>
        <form:select path="campCode" id="campCode" class="ipt_slt1">
            <form:option value="" label="--전체--" />
            <form:options items="${selection.campList}" itemValue="campCode" itemLabel="campName" />
        </form:select>&nbsp;&nbsp;&nbsp;

        <label for="collCode"  ><strong>대학</strong></label>
        <form:select path="collCode" id="collCode" class="ipt_slt1">
            <form:option value="" label="--전체--" />
            <form:options items="${selection.collList}" itemValue="collCode" itemLabel="collName" />
        </form:select>&nbsp;&nbsp;&nbsp;

          <span class="btnBlueS">
              <input type="submit" value="검색" class="btnBox" id='deptSearchBtn' />
              </span>
    </div>
    </form:form>

    <table class="tbl_typeA text-center" summary="변경처리내역">
        <caption>변경처리내역</caption>
        <thead>
        <tr>
            <th>요청번호</th>
            <th>변경대상자</th>
            <th>변경구분</th>
            <th>변경항목</th>
            <th>변경전</th>
            <th>변경후</th>
            <th>요청일자</th>
            <th>반영일자</th>
            <th>처리결과</th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${chgList.size() == 0}" >
            <tr >
                <td colspan="9">해당 정보 없음</td>
            </tr>
        </c:if>
        <c:forEach var="chgList" items="${chgList}" varStatus="status">
            <tr  chgList="${chgList.applNo}">
                <td>${chgList.admsNo}-${chgList.chgNo}</td>
                <td>
                    <c:choose>
                        <c:when test="${chgList.admsNo == '15D'}">
                            ${chgList.engName},${chgList.engSur}
                        </c:when>
                        <c:otherwise>
                            ${chgList.korName}
                        </c:otherwise>
                    </c:choose>
                    <br>${chgList.applId}</td>
                <td>${chgList.applChgCodeName}</td>
                <td >${chgList.chgColmName}</td>
                <td >${chgList.befVal}</td>
                <td >${chgList.aftVal}</td>
                <td >${chgList.reqDay}</td>
                <td >${chgList.actDay}</td>
                <td >${chgList.chgStsCodeName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page_number">
        <fmt:parseNumber var="indexCount" integerOnly= "true" value="${changeSearchPageForm.page.totalCount/changeSearchPageForm.page.rows + 1}" />
        <c:if test="${indexCount != 0}">
        <a class="Lbegin"><span><a href="#" class="end" onclick="movePage(1); return false;">1page</a>
        <c:if test="${changeSearchPageForm.page.no-1 > 0}">
           <a href="#" onclick="movePage(${changeSearchPageForm.page.no-1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_previous.gif" alt="이전페이지" /></a>
        </c:if>
        <c:forEach begin="1" end="${indexCount}" var="pageNumIndex">
            <c:if test="${changeSearchPageForm.page.no==pageNumIndex}">
               <span class="active">${pageNumIndex}</span>
            </c:if>
            <c:if test="${changeSearchPageForm.page.no!=pageNumIndex}">
                <a href="#" onclick="movePage(${pageNumIndex}); return false;">${pageNumIndex}</a>
            </c:if>
        </c:forEach>
        <c:if test="${changeSearchPageForm.page.no < indexCount}">
            <a href="#" onclick="movePage(${changeSearchPageForm.page.no+1}); return false;"><img src="<spring:eval expression="@app.getProperty(\"path.static\")"/>/img/admin/repository/list_page_next.gif" alt="다음페이지" /></a>
        </c:if>
        <a href="#" class="end" onclick="movePage(${indexCount}); return false;">${indexCount}page</a>
    </c:if>
    </div>


<content tag="local-script">
    <script>
        jQuery(document).ready( function(){
            /*
            jQuery(".chgList").on('click', function(){
                location.href = "${contextPath}/admin/modification/chgInfoDetail?chgNo="+jQuery(this).attr('chgNo');
            }).css("cursor","pointer");
            */
            jQuery("#deptSearchBtn").on('click', function(e) {
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
