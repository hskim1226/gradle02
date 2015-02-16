<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
            <ul>
                <li class="Lfirst"><span><a href="#">HOME</a></span></li>
                <li><span><a href="#">지원변경/취소관리</a></span></li>
                <li class="Llast"><span>변경처리현황</span></li>
            </ul>
        </div>

        <h1>변경처리현황</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form:form commandName="changeSearchPageForm"  method="post" role="form" action="${contextPath}/admin/modification/changeList" id="search-form">
                        <input type="hidden" id="page-number-hidden" name="page.no" value="${changeSearchPageForm.page.no}" />
                        <table summary="지원현황 검색조건">
                            <caption>지원현황 검색조건</caption>
                            <tbody>
                            <tr><th class="Cat">처리현황검색 : </th>
                                <th><label for="applChgCode">변경요청구분</label></th>
                                <td>
                                    <form:select path="applChgCode" id="applChgCode" cssClass="form-control base-info">
                                        <form:option value="" label="--전체--" />
                                        <form:options items="${selection.applChgCodeList}" itemValue="code" itemLabel="codeVal"/>
                                    </form:select>
                                </td>
                                <th><label for="chgStsCode">처리결과</label></th>
                                <td>
                                    <form:select path="chgStsCode" id="chgStsCode" cssClass="form-control base-info">
                                        <form:option value="" label="--전체--" />
                                        <form:options items="${selection.chgStsCodeList}" itemValue="code" itemLabel="codeVal"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr><th class="Cat">지원단위검색 : </th>
                                <th><label for="admsNo">지원 전형</label></th>
                                <td>
                                    <form:select path="admsNo" id="admsNo" cssClass="form-control base-info">
                                        <form:option value="" label="--전체--" />
                                        <form:options items="${selection.admsList}" itemValue="admsNo" itemLabel="admsNo"/>
                                    </form:select>
                                </td>
                                <th><label for="campCode">캠퍼스</label></th>
                                <td>
                                    <form:select path="campCode" id="campCode" cssClass="form-control base-info">
                                        <form:option value="" label="--전체--" />
                                        <form:options items="${selection.campList}" itemValue="campCode" itemLabel="campName" />
                                    </form:select>
                                </td>
                                <th><label for="collCode"  >대학</label></th>
                                <td>
                                    <form:select path="collCode" id="collCode" cssClass="form-control base-info">
                                        <form:option value="" label="--전체--" />
                                        <form:options items="${selection.collList}" itemValue="collCode" itemLabel="collName" />
                                    </form:select>
                                </td>
                            </tr>
                            <tr><th class="Cat">지원자검색 : </th>
                                <th>
                                    <form:label path="korName">한글 이름</form:label></th>
                                <td>
                                    <div class="col-sm-9">
                                        <form:input path="korName" cssClass="Ltext"/>
                                    </div></td>
                            </tr>
                            </tbody>
                        </table>
                        <input id="deptSearchBtn" type='image' class="Limage" src="${contextPath}/img/admin/btn_search.gif" />
                    </form:form>
                </div>
            </div>
        </div>

        <div id="LblockListTable01" class="LblockListTable">
            <table summary="전형별 지원현황">
                <caption>전형별 지원현황</caption>
                <thead>
                <tr>
                    <th class="Lfirst">요청번호</th>
                    <th>변경대상자</th>
                    <th>변경구분</th>
                    <th>변경정보</th>
                    <th>변경전</th>
                    <th>변경후</th>
                    <th>요청일자</th>
                    <th>반영일자</th>
                    <th class="Llast" >처리결과</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${chgList.size() == 0}" >
                    <tr >
                        <td colspan="9">해당 정보 없음</td>
                    </tr>
                </c:if>
                <c:forEach var="chgList" items="${chgList}" varStatus="status">
                    <tr class="<c:if test="${status.index == 0}">Lfirst </c:if>chgList" chgList="${chgList.applNo}">
                        <td>${chgList.admsNo}-${chgList.chgNo}</td>
                        <td>${chgList.korName}<br>${chgList.applNo}</td>
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
            <ul>
                <fmt:parseNumber var="indexCount" integerOnly= "true" value="${changeSearchPageForm.page.totalCount/changeSearchPageForm.page.rows + 1}" />
                <c:if test="${indexCount != 0}">
                    <li class="Lbegin"><span><a href="#" onclick="movePage(1); return false;">1page</a></span></li>
                    <c:if test="${changeSearchPageForm.page.no-1 > 0}">
                        <li class="Lprevious"><span><a href="#" onclick="movePage(${changeSearchPageForm.page.no-1}); return false;"><img src="${contextPath}/img/admin/list_page_previous.gif" alt="이전페이지" /></a></span></li>
                    </c:if>
                    <c:forEach begin="1" end="${indexCount}" var="pageNumIndex">
                        <c:if test="${changeSearchPageForm.page.no==pageNumIndex}">
                            <li class="Lfirst"><span>${pageNumIndex}</span></li>
                        </c:if>
                        <c:if test="${changeSearchPageForm.page.no!=pageNumIndex}">
                            <li><span><a href="#" onclick="movePage(${pageNumIndex}); return false;">${pageNumIndex}</a></span></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${changeSearchPageForm.page.no < indexCount}">
                        <li class="Lnext"><span><a href="#" onclick="movePage(${changeSearchPageForm.page.no+1}); return false;"><img src="${contextPath}/img/admin/list_page_next.gif" alt="다음페이지" /></a></span></li>
                    </c:if>
                    <li class="Lend"><span><a href="#" onclick="movePage(${indexCount}); return false;">${indexCount}page</a></span></li>
                </c:if>
            </ul>
        </div>
    </div>
    <%--
    <div id="LblockButton">
        <a href="#"><input type="button" value="엑셀파일 다운로드" onclick="doSomething(); return false;" /></a>
        <a href="#"><input type="button" value="신규요청등록" onclick="doSomething(); return false;" /></a>
    </div>
    --%>
</div>

<content tag="local-script">
    <script>
        jQuery(document).ready( function(){
            jQuery(".chgList").on('click', function(){
                location.href = "${contextPath}/admin/modification/chgInfoDetail?chgNo="+jQuery(this).attr('chgNo');
            }).css("cursor","pointer");

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
