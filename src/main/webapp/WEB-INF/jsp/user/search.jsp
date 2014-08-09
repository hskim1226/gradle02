<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-08-08
  Time: 오후 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/env.jsp"%>

<html>
<head>
    <title></title>
    <script>
        $(document).ready(function() {
            $('#search-form').submit( function(e) {
                var $form = $(this);
                var $formUrl = $form.attr('action');
                var $formData = $form.serializeArray();
                var $button = $form.find(':submit');
                $.ajax({
                    url: $formUrl,
                    type: 'POST',
                    data: $formData,
                    timeout: 5000,
                    beforeSend: function() {
                        $button.attr('disable', true);
                    },
                    complete: function() {
                        $button.attr('disable', false);
                    },
                    success: function (data) {
                        if (data.result == 'SUCCESS') {
                            var innerData = data.data;
                            alert(innerData);
                        }
                    },
                    error: function() {

                    }
                });
                e.preventDefault();
            });
        });
    </script>
</head>
<body>
<section class="featured">
    <div class="container">
        <form class="form-horizontal" id="search-form" role="form" action="${contextPath}/user/search/id" method="post">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" id="name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">E-mail</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="email" id="email"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-default" id="search-btn" value="Search"/>
                </div>
            </div>
        </form>
    </div>
</section>
</body>
</html>
