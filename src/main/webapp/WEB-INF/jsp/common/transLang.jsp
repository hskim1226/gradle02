<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html lang='ko'>
<head>
    <title><spring:message code="L00014"/><%--언어 변경--%></title>
    <style>
        .input-text {
            height: 50px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
    </style>
</head>
    <body>
    <section class="normal-white">
        <div class="container">
            <form id="transLang" name="transLang" cssClass="form-horizontal">
                <div class="col-md-offset-2 col-md-8">
                    <div class="inner-container-white">
                        <div class="col-sm-12 text-gray">
                            <i class="fa fa-thumb-tack fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b><spring:message code="L00014"/><%--언어 변경--%></b></span>
                        </div>
                        <div class="spacer-small">&nbsp;</div>
                        <div class="form-group col-sm-12">
                            <div class="col-sm-offset-1 col-sm-10">
                                <div class="col-sm-6">
                                    <button id="korean" class="btn btn-primary btn-lg btn-block btn-save input-text" data-lang="ko"><spring:message code="L00012"/><%--한국어--%></button>
                                </div>
                                <div class="col-sm-6">
                                    <button id="english" class="btn btn-info btn-lg btn-block btn-save input-text" data-lang="en"><spring:message code="L00013"/><%--영어--%></button>
                                </div>
                            </div>
                        </div>
                        <div class="spacer-tiny">&nbsp;</div>
                    </div>
                </div>
                <input type="hidden" id="lang" name="lang"/>
            </form>
        </div>
    </section>
    <content tag="local-script">
    <script type="text/javascript">
    $(document).ready(function() {

        <%-- 하단 버튼 처리 --%>
        $('.btn-save').on('click', function(e) {
            e.preventDefault();
            var form = document.getElementById('transLang'),
                hiddenLang = document.getElementById('lang');
            hiddenLang.value = this.getAttribute('data-lang');
            form.action = '${contextPath}/common/transLang';
            form.method = 'post';
            form.submit();
        });
        <%-- 하단 버튼 처리 --%>

    });
    </script>
    </content>
</body>
</html>