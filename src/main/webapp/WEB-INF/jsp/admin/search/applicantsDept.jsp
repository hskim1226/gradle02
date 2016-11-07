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
        <div class="location"> HOME &gt; 지원자관리 &gt; <span>지원자검색</span> </div>

    </div>

    <div class="con_section">
        <form:form commandName="searchPageForm"  method="post" role="form" action="${contextPath}/admin/search/applicants/deptSearch" id="search-form">
            <div class="srch_box">
                <input type="hidden" id="page-number-hidden" name="page.no" value="${searchPageForm.page.no}" />
                <p class="srch_tit"><i class="fa fa-search"></i>지원단위검색</p>


                <label for="admsNo"><strong>지원 전형</strong></label>

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
                <label for= "deptCode"><strong>학과</strong></label>
                <form:select path="deptCode" id="deptCode" class="ipt_slt1">
                    <form:option value="" label="--전체--" />
                    <form:options items="${selection.deptList}" itemValue="deptCode" itemLabel="deptName" />
                </form:select>&nbsp;&nbsp;&nbsp;
            <span class="btnBlueS">
          <input type="submit" value="검색" class="btnBox" id='idSearchBtn' />
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
                <th>원서확인</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${applList.size() == 0}" >
                <tr >
                    <td colspan="8">해당 정보 없음</td>
                </tr>
            </c:if>
            <c:forEach var="applList" items="${applList}" varStatus="status">
                <tr class="applList"  applNo="${applList.applNo}">
                    <td class="appl">${applList.applId}</td>
                    <td class="appl">${applList.campName}</td>
                    <td class="appl">${applList.deptName}
                        <c:if test ="${applList.detlMajCode != 'DM000'}">
                            <br>${applList.detlMajName}
                        </c:if>
                    </td>
                    <td class="appl">
                        <c:if test ="${applList.admsNo == app['adms.general']}">
                            ${applList.applAttrName}<br>
                        </c:if>
                            ${applList.corsTypeName}
                    </td>
                    <td class="appl">
                        <c:choose>
                            <c:when test="${applList.admsNo == app['adms.foreign']}">
                                ${applList.engName},${applList.engSur} <br> ${applList.rgstBornDate}
                            </c:when>
                            <c:otherwise>
                                ${applList.korName} <br> ${applList.rgstBornDate}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="appl">${applList.mobiNum} <br>${applList.mailAddr} </td>
                    <c:choose>
                        <c:when test="${applList.payTypeCode == 'PAY001'}">
                            <td>계좌이체<br>${applList.admsFee} </td>
                        </c:when>
                        <c:when test="${applList.payTypeCode == 'PAY002'}">
                            <td>전신환<br>${applList.admsFee} </td>
                        </c:when>
                        <c:when test="${applList.payTypeCode == 'PAY003'}">
                            <td>Paypal<br>${applList.admsFee} </td>
                        </c:when>
                        <c:otherwise>
                            <td>${applList.payTypeName}<br>${applList.admsFee} </td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:choose>
                            <c:when test="${applList.checkYn == 'Y'}">
                                <a class="btn_set btnWhiteS pdfDown" id="pdfDownBtn" applNo="${applList.applNo}" href="#"><span>원서</span></a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn_set btnBlackS pdfDown" id="pdfDownBtn" applNo="${applList.applNo}" href="#"><span>원서</span></a>
                            </c:otherwise>
                        </c:choose>
                        <a class="btn_set btnWhiteS applDown" id="applIdDownBtn" applNo="${applList.applNo}" admsTypeCode="${applList.admsType}" href="#"><span>수험표</span></a>
                    </td>

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


        <div id="LblockcChgInfoBtn" class="con_btn text-right">
            <a class="btn_set btnBlueS" id="downBtn"  href="#"><span>엑셀파일 다운로드</span></a>
        </div>


    </div>
    <!-- /con_section -->
</div>
<!-- /content -->

<content tag="local-script">

    <script>
        jQuery(document).ready( function(){
            jQuery(".appl").on('click', function(){
                location.href = "${contextPath}/admin/search/applicant/applInfoDetail?applNo="+jQuery(this.parentNode).attr('applNo');
            }).css("cursor","pointer");

            jQuery(".pdfDown").on('click', function(event){
                location.href = "${contextPath}/admin/search/pdfDownload?applNo="+jQuery(this).attr('applNo')+'&type=form';
                jQuery("#pdfDownBtn").attr("class","btn_set btnWhiteS pdfDown") ;
            });

            jQuery(".applDown").on('click', function(event){
                <%--location.href = "${contextPath}/admin/search/applSlipDownload?applNo="+jQuery(this).attr('applNo')+'&admsTypeCode='+jQuery(this).attr('admsTypeCode');--%>
                location.href = "${contextPath}/admin/search/pdfDownload?applNo="+jQuery(this).attr('applNo')+'&type=slip';
            });

            jQuery("#idSearchBtn").on('click', function(event) {
                event.preventDefault();
                submitForm();
            });

            function submitForm(){
                jQuery("#page-number-hidden").val(1);
                jQuery("#search-form").submit();

            };
            jQuery('#downBtn').on('click', function(event) {
                event.preventDefault();
                jQuery('#search-form').attr("action", '${contextPath}/admin/search/excelDownload');
                document.getElementById('search-form').submit();

            });

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


            attachChangeEvent( 'collCode',
                    {
                        targetId: 'deptCode',
                        valueKey: 'deptCode',
                        labelKey: 'deptName',
                        url: function(arg) {
//                        var admsNo = jQuery('#admsNo option:selected').val();
                            var admsNo = document.getElementById('admsNo'),
                                    val = admsNo[admsNo.selectedIndex];
                            return '/college/department/' + val.value + '/' + arg;
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
