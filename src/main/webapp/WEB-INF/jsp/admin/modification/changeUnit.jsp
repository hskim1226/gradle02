<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<div class="content">
    <div class="con_tit">
        <h2><span>지원단위변경</span></h2>
        <div class="location"> HOME &gt; 지원변경/원서수정 &gt; <span>지원단위변경</span> </div>
    </div>

    <div class="con_section">
        <form id ="applicantSearchForm" action="${contextPath}/admin//modification/changeUnit" method="post">
            <div class="srch_box"> <strong><label for="applId">수험번호</label></strong>
                <input type="text" name="applId" id="applId" class="ipt_txt1" style  value="${applicantSearchForm.applId}" />
          <span class="btnBlueS">
          <input type="submit" value="검색" class="btnBox" id="searchBtn"  />
          </span> </div>
        </form>


        <%@include file="applicationInfo.jsp"%>
        <%@include file="applicantInfo.jsp"%>

    <c:if test="${applInfo.applNo != null}" >
        <h3 class="tit1">지원단위 변경사항</h3>

        <form:form commandName="customApplicationChange"  method="post" role="form" action="${contextPath}/admin/modification/requestChangeUnit" id="changeUnitt-form">
        <input type="hidden" name="applNo" value=${applInfo.applNo}>
        <input type="hidden" id="befValInput" name ="befVal">
        <input type="hidden" id="aftValInput" name ="aftVal">
        <table class="tbl_typeA mb15" summary="지원단위 변경사항">
            <caption>
                지원단위 변경사항
            </caption>
            <colgroup>
                <col width="12%" />
                <col width="20%" />
                <col width="12%" />
                <col />
                <col width="12%" />
                <col width="25%" />
            </colgroup>
            <tbody>
            <tr>
                <th><label for="admsNo">지원 전형</label></th>
                <td>
                    <form:select path="admsNo" id="admsNo" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <form:options items="${selection.admsList}" itemValue="admsNo" itemLabel="admsName"/>
                    </form:select></td>
                <th><label for="applAttrCode" >지원 구분</label></th>
                <td>
                    <form:select path="applAttrCode" id="applAttrCode" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <form:options items="${selection.applAttrList}" itemValue="code" itemLabel="codeVal"/>
                    </form:select>
                </td>
                <th><label for="ariInstCode">학·연·산 연구기관</label></th>
                <td>
                    <form:select path="ariInstCode" id="ariInstCode" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <c:if test="${entireApplication.application.applAttrCode == '00002'}">
                            <form:options items="${common.ariInstList}" itemValue="ariInstCode" itemLabel="ariInstName" />
                        </c:if>
                    </form:select>
                </td>
            </tr>
            <tr>
                <th><label for="campCode">캠퍼스</label></th>
                <td>
                    <form:select path="campCode" id="campCode" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <form:options items="${selection.campList}" itemValue="campCode" itemLabel="campName" />
                    </form:select>
                </td>
                <th><label for="collCode"  >대학</label></th>
                <td>
                    <form:select path="collCode" id="collCode" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <form:options items="${selection.collList}" itemValue="collCode" itemLabel="collName" />
                    </form:select>
                </td>
                <th><label for="deptCode">지원 학과</label></th>
                <td>
                    <form:select path="deptCode" id="deptCode" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <form:options items="${common.deptList}" itemValue="deptCode" itemLabel="deptName" />
                    </form:select>
                </td>
            </tr>
            <tr>

                <th><label for="corsTypeCode" >지원 과정</label></th>
                <td>
                    <form:select path="corsTypeCode" id="corsTypeCode" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <form:options items="${common.corsTypeList}" itemValue="corsTypeCode" itemLabel="codeVal" />
                    </form:select>
                </td>
                <th><label for="detlMajCode" >세부 전공</label></th>
                <td>
                    <form:select path="detlMajCode" id="detlMajCode" class="ipt_slt1">
                        <form:option value="" label="--선택--" />
                        <form:options items="${common.detlMajList}" itemValue="detlMajCode" itemLabel="detlMajName" />
                    </form:select>
                </td>
                <th>금액변경</th>
                <td></td>
            </tr>
            <tr>
                <th>변경사유</th>
                <td colspan="5"><textarea  r rows ="5" cols="60" name="cnclResn"  id="cnclResn" ></textarea></td>
            </tr>
            </tbody>
            </table>
            </form:form>

            </c:if>
            <c:if test="${applInfo.applNo != null}" >
                <div class="con_btn text-right">
                    <a class="btn_set btnRedS" id="changeBtn" ><span>변경요청</span></a>
                </div>
            </c:if>
    </div>
    <!-- /con_section -->
</div>
<!-- /content -->
</body>

<content tag="local-script">
    <script>
        jQuery(document).ready(function() {
            jQuery('#changeBtn').on('click', function(e) {
                event.preventDefault();
                if (confirm('지원단위를 변경요청 하시겠습니까?')) {
                    var aris ='';
                    var aftAris='';
                    if("${applInfo.ariInstName}"!=""){
                        aris ="${applInfo.ariInstName}"+"<br>";
                    }else{
                        aris = "${applInfo.campName}" +"${applInfo.collName}"+"<br>";
                    }

                    jQuery('#befValInput').val(
                        "${applInfo.admsName}" +"-"+"${applInfo.applAttrName}"+"<br>"+
                     aris +
                    "${applInfo.deptName}" +"-"+
                    "${applInfo.corsTypeName}" +"<br>"+
                    "${applInfo.detlMajName}"  );

                   if(jQuery('#ariInstCode option:selected').val()!=''){
                      aftAris = jQuery('#ariInstCode option:selected').attr('label') +"<br>";
                   }else{
                      aftAris = jQuery('#campCode option:selected').attr('label')+"-"+
                                jQuery('#collCode option:selected').attr('label') +"<br>";
                   }
                    jQuery('#aftValInput').val(
                        jQuery('#admsNo option:selected').text() +"-"+
                        jQuery('#applAttrCode option:selected').text() +"<br>"+
                                aftAris+
                        jQuery('#deptCode option:selected').attr('label') +"-"+
                        jQuery('#corsTypeCode option:selected').attr('label') +"<br>"+
                        jQuery('#detlMajCode option:selected').attr('label') );

                    jQuery('#changeUnitt-form').submit();
                }

            });


            jQuery('#searchBtn').on('click', function(event) {
                jQuery('#applicantSearchForm').submit();
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
                            var container = JSON.parse(e),
                                    data = JSON.parse(container.data);
                            if(container.result && container.result === 'SUCCESS') {
                                var $target = $('#' + targetId);
//                            var data = JSON && JSON.parse(e.data) || $.parseJSON(e.data);

                                $(data).each(function (i, item) {
                                    var $op = $('<option>').attr({
                                        'value': item[valueKey],
                                        'label': item[labelKey]
                                    });
                                    $op.html(item[labelKey]);
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
            <%-- select 폼 change 이벤트 처리 끝 --%>

            <%--지원사항 select 폼 change 이벤트 핸들러 등록 시작 --%>
            <%-- 지원구분 변경 --%>



            attachChangeEvent( 'applAttrCode',
                    {
                        '00002': {targetId: 'ariInstCode', valueKey: 'ariInstCode', labelKey: 'ariInstName', url: '/ariInst'}, // applAttrCode == '02'
                        targetId: 'campCode',
                        valueKey: 'campCode',
                        labelKey: '${pageContext.response.locale == 'en' ? 'campNameXxen' : 'campName'}',
                        clean: ['collCode', 'ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],

                        url: function(arg) {
                            return '/campus';
                        }
                    }
            );

            <%-- 캠퍼스 변경 --%>
            attachChangeEvent( 'campCode',
                    {
                        targetId: 'collCode',
                        valueKey: 'collCode',
//                    labelKey: 'collName',
                        labelKey: '${pageContext.response.locale == 'en' ? 'collNameXxen' : 'collName'}',
                        clean: ['ariInstCode', 'deptCode', 'corsTypeCode', 'detlMajCode'],
                        url: function(arg) {
                            var admsNo = $('#admsNo').val();
                            return '/admscollege/' + admsNo + '/' + arg;
                        }
                    }
            );

            <%-- 대학변경 --%>
            attachChangeEvent( 'collCode',
                    {
                        targetId: 'deptCode',
                        valueKey: 'deptCode',
//                    labelKey: 'deptName',
                        labelKey: '${pageContext.response.locale == 'en' ? 'deptNameXxen' : 'deptName'}',
                        clean: ['corsTypeCode', 'detlMajCode'],
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
//                    labelKey: 'deptName',
                        labelKey: '${pageContext.response.locale == 'en' ? 'deptNameXxen' : 'deptName'}',
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
//                    labelKey: 'codeVal',
                        labelKey: '${pageContext.response.locale == 'en' ? 'codeValXxen' : 'codeVal'}',
                        clean: ['detlMajCode'],
                        url: function(arg) {   <%-- 지원과정 조회 --%>
                            var admsNo = $('#admsNo').val();
                            var applAttrCode = $('#applAttrCode').val();
                            if (applAttrCode == '00001') {
                                return '/general/course/' + admsNo + '/' + arg;
                            } else if (applAttrCode == '00002') {
                                return '/ariInst/course/' + admsNo + "/" + arg + "/" + $('#ariInstCode').val();
                            } else if (applAttrCode == '00003') {
                                return '/commission/course/' + admsNo + '/' + arg;
                            } else if (applAttrCode == '00004') {
                                return '/northDefector/course/' + admsNo + '/' + arg;
                            }
                        }
                    }
            );

            <%-- 지원과정 변경 --%>
            attachChangeEvent( 'corsTypeCode',
                    {
                        targetId: 'detlMajCode',
                        valueKey: 'detlMajCode',
//                    labelKey: 'detlMajName',
                        labelKey: '${pageContext.response.locale == 'en' ? 'detlMajNameXxen' : 'detlMajName'}',
                        url: function(arg) {
                            var admsNo = $('#admsNo').val();
                            var applAttrCode = $('#applAttrCode').val();
                            if (applAttrCode == '00001') {
                                return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                            } else if (applAttrCode == '00002') {
                                return '/ariInst/detailMajor/' + admsNo + "/" + $('#deptCode').val() + "/" + $('#ariInstCode').val() + '/' + arg;
                            } else if (applAttrCode == '00003') {
                                return '/general/detailMajor/' + admsNo + '/' + $('#deptCode').val() + '/' + arg;
                            } else if (applAttrCode == '00004') {
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
