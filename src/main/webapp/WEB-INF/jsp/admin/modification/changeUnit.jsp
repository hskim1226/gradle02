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
                <li><span><a href="#">지원변경/원서수정</a></span></li>
                <li class="Llast"><span>지원단위변경</span></li>
            </ul>
        </div>

        <h1>지원단위변경</h1>
    </div>

    <div id="LblockMainBody" >
        <div id="LblockSearch">
            <div>
                <div>
                    <form action="" method="post">
                        <table summary="지원단위변경 대상자검색">
                            <caption>지원단위변경 대상자검색</caption>
                            <tr>
                                <th><label for="applId">수험번호</label></th>
                                <td><input type="text" class="Ltext" id="applId" name="applId" size="15" value="${applInfo.applId}"></td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="image" class="Limage"  id="searchBtn" src="${contextPath}/img/admin/btn_search.gif" /></a>

                    </form>
                </div>
            </div>
        </div>
        <div id="LblockPageSubtitle01" class="LblockPageSubtitle">
            <h2>지원정보</h2>
        </div>

        <div id="LblockDetail01" class="LblockDetail">
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
                    <th>대학</th>
                    <td>${applInfo.collName}</td>
                    <th>학과</th>
                    <td>${applInfo.deptName}</td>
                </tr>
                <tr>
                    <th>학연산 기관</th>
                    <td><c:if test="${empty applInfo.ariInstName}" >해당없음</c:if><c:if test="${not empty applInfo.ariInstName}" >${applInfo.ariInstName}</c:if></td>
                    <th>지원과정</th>
                    <td>${applInfo.corsTypeName}</td>
                    <th>세부전공</th>
                    <td>${applInfo.detlMajName}</td>
                </tr>
                <tr>
                    <th>전형료</th>
                    <td><fmt:formatNumber type="currency"   maxFractionDigits="3" value="${applInfo.admsFee}" /></td>
                    <th>결제방법</th>
                    <td></td>
                    <th>지원상태</th>
                    <td>${applInfo.applStsName}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="LblockPageSubtitle02" class="LblockPageSubtitle">
            <h2>지원서 상세정보</h2>
        </div>

        <div id="LblockDetail02" class="LblockDetail">
            <form id ="changeForm" action="${contextPath}/admin/modification/requestChangeInfo" method="post">
                <input type="hidden" name="applNo" value=${applInfo.applNo}> </input>
                <input type="hidden" name="admsNo" value=${applInfo.admsNo}> </input>
                <table summary="지원서 상세정보">
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
                        <td>${applInfo.mobiNum}</td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>${applInfo.detlAddr}</td>
                        <th>E-mail</th>
                        <td>${applInfo.mailAddr}</td>
                    </tr>
                    <tr>
                        <th>비상연락대상</th>
                        <td>${applInfo.emerContName}</td>
                        <th>비상연락처</th>
                        <td>${applInfo.emerContTel}</td>
                    </tr>
                    </tr>
                    <tr></tr>
                    </tbody>
                </table>
                <div>
                    <input type="hidden" id="defValInput" name ="befVal"> </input>
                    <input type="hidden" id="aftValInput" name ="aftVal"> </input>

                </div>

                <div id="LblockPageSubtitle03" class="LblockPageSubtitle">
                    <h2>변경요청 지원정보</h2>
                </div>
                <div id="LblockDetail03" class="LblockDetail">
                    <form:form commandName="applicationChange"  method="post" role="form" action="${contextPath}/admin/modification/requestChangeUnit" id="search-form">
                    <table summary="변경요청 지원정보">
                        <caption>변경요청 지원정보</caption>
                        <tbody>
                        <tr>
                            <th><label for="admsNo">지원 전형</label></th>
                            <td>
                                <form:select path="admsNo" id="admsNo" cssClass="form-control base-info">
                                    <form:option value="" label="--전체--" />
                                    <form:options items="${selection.admsList}" itemValue="admsNo" itemLabel="admsNo"/>
                                 </form:select></td>
                            <th><label for="applAttrCode" >지원 구분</label></th>
                            <td>
                                <form:select path="applAttrCode" id="applAttrCode" cssClass="form-control base-info">
                                    <form:options items="${common.applAttrList}" itemValue="code" itemLabel="codeVal"/>
                                </form:select>
                            </td>
                            <th>금액변경</th>
                            <td>변경없음</td>
                        </tr>
                        <tr>
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
                            <th><label for="deptCode">지원 학과</label></th>
                            <td>
                                <form:select path="deptCode" id="deptCode" cssClass="form-control base-info">
                                    <form:option value="" label="--선택--" />
                                    <form:options items="${common.deptList}" itemValue="deptCode" itemLabel="deptName" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="ariInstCode">학·연·산 연구기관</label></th>
                            <td>
                                <form:select path="ariInstCode" id="ariInstCode" cssClass="form-control base-info">
                                    <form:option value="" label="--선택--" />
                                    <c:if test="${entireApplication.application.applAttrCode == '00002'}">
                                        <form:options items="${common.ariInstList}" itemValue="ariInstCode" itemLabel="ariInstName" />
                                    </c:if>
                                </form:select>
                            </td>
                            <th><label for="corsTypeCode" >지원 과정</label></th>
                            <td>
                                <form:select path="corsTypeCode" id="corsTypeCode" cssClass="form-control base-info">
                                    <form:option value="" label="--선택--" />
                                    <form:options items="${common.corsTypeList}" itemValue="corsTypeCode" itemLabel="codeVal" />
                                </form:select>
                            </td>
                            <th><label for="detlMajCode" >세부 전공</label></th>
                            <td>
                                <form:select path="detlMajCode" id="detlMajCode" cssClass="form-control base-info">
                                    <form:option value="" label="--선택--" />
                                    <form:options items="${common.detlMajList}" itemValue="detlMajCode" itemLabel="detlMajName" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <th>변경사유</th>
                            <td colspan="5"><textarea  r rows ="5" cols="60" name="cnclResn"  id="cnclResn" ></textarea></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </form>
        </div>

        <th></th>
        <td>

        </td>
        <th></th>
        <td>

        </td>
        <th></th>
        <td>

        </td>

        <div id="LblockButton">
            <a href="#"><input type="button"  id="changeBtn" value="변경요청" /></a>
        </div>
    </div>
</div>

<content tag="local-script">
    <script>
        jQuery(document).ready(function() {
            jQuery('#changeBtn').on('click', function(e) {
                event.preventDefault();
                alert("아직 개발중입니다")
                <%--
                if (confirm('지원자 정보를 수정하시겠습니까?')) {
                    jQuery('#changeForm').submit();
                }
                --%>
            });

            function attachChangeEvent( sourceId, context ) {
                var $source = $('#' + sourceId);

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
                    clean = [].concat( targetId, clean );
                    var $clean, oldVal;
                    for (i = 0; i < clean.length; i++) {
                        if (clean[i]) {
                            $clean = $('#' + clean[i]);
                            oldVal = $clean.val();
                            $clean.children('option').filter(function() {
                                return this.value !== '';
                            }).remove();
                            if (oldVal !== $clean.val()) {
                                $clean.trigger('change');
                            }
                        }
                    }

                    if (!val || val == '') {
                        return;
                    }

                    $.ajax({
                        type: 'GET',
                        url: baseUrl,
                        success: function(e) {
                            var ec = JSON && JSON.parse(e) || $.parseJSON(e);
                            if(ec.result && ec.result === 'SUCCESS') {
                                var $target = jQuery('#' + targetId);
                                var data = JSON && JSON.parse(ec.data) || $.parseJSON(ec.data);
                                $(data).each(function (i, item) {
                                    var $op = $('<option>').attr({
                                                'value': item[valueKey],
                                                'label': item[labelKey]}
                                    )
                                    if ('detlMajCode' == targetId) {
                                        for (var key in item) {
                                            if (key !== valueKey && key !== labelKey) {
                                                $op.attr(key, item[key]);
                                            }
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

            attachChangeEvent( 'applAttrCode',
                    {
                        '00002': {targetId: 'ariInstCode', valueKey: 'ariInstCode', labelKey: 'ariInstName', url: '/ariInst'}, // applAttrCode == '02'
                        targetId: 'campCode',
                        valueKey: 'campCode',
                        labelKey: 'campName',
                        clean: ['collCode', 'ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],
                        url: '/campus'
                    }
            );

            <%-- 캠퍼스 변경 --%>
            attachChangeEvent( 'campCode',
                    {
                        targetId: 'collCode',
                        valueKey: 'collCode',
                        labelKey: 'collName',
                        // clean: ['ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],
                        url: function(arg) {
                            return '/college/' + arg;
                        }
                    }
            );

            <%-- 대학변경 --%>
            attachChangeEvent( 'collCode',
                    {
                        targetId: 'deptCode',
                        valueKey: 'deptCode',
                        labelKey: 'deptName',
                        // clean: ['corsTypeCode', 'detlMajCode'],
                        url: function(arg) {
                            var admsNo = $('#admsNo').val();
                            return '/general/department/' + admsNo + '/' + arg;
                        }
                    }
            );

            <%-- 학연산 변경 --%>
            attachChangeEvent( 'ariInstCode',
                    {
                        targetId: 'deptCode',
                        valueKey: 'deptCode',
                        labelKey: 'deptName',
                        // clean: ['corsTypeCode', 'detlMajCode'],
                        url: function(arg) {
                            var admsNo = $('#admsNo').val();
                            return '/ariInst/department/' + admsNo + '/' + arg;
                        }
                    }
            );

            <%-- 지원학과 변경 --%>
            attachChangeEvent( 'deptCode',
                    {
                        targetId: 'corsTypeCode',
                        valueKey: 'corsTypeCode',
                        labelKey: 'codeVal',
                        // clean: ['detlMajCode'],
                        url: function(arg) {   <%-- 지원과정 조회 --%>
                            var admsNo = $('#admsNo').val();
                            var applAttrCode = $('#applAttrCode').val();
                            if (applAttrCode == '00001') {
                                return '/general/course/' + admsNo + '/' + arg;
                            } else if (applAttrCode == '00002') {
                                return '/ariInst/course/' + admsNo + "/" + arg + "/" + $('#ariInstCode').val();
                            } else if (applAttrCode == '00003') {
                                return '/commission/course/' + admsNo + '/' + arg;
                            }
                        }
                    }
            );

            <%-- 지원과정 변경 --%>
            attachChangeEvent( 'corsTypeCode',
                    {
                        targetId: 'detlMajCode',
                        valueKey: 'detlMajCode',
                        labelKey: 'detlMajName',
                        url: function(arg) {
                            var admsNo = $('#admsNo').val();
                            var applAttrCode = $('#applAttrCode').val();
                            if (applAttrCode == '00001') {
                                return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                            } else if (applAttrCode == '00002') {
                                return '/ariInst/detailMajor/' + admsNo + "/" + $('#deptCode').val() + "/" + $('#ariInstCode').val() + '/' + arg;
                            } else if (applAttrCode == '00003') {
                                return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                            }
                        }
                    }
            );
        });
    </script>
</content>
</body>
</html>
