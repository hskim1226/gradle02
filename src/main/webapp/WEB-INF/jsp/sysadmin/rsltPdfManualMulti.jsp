<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>

<html>
<head>
    <title></title>
    <style>
        .input-text {
            height: 35px;
            font-size: 100%;
            opacity: 1.0;
            margin-bottom: 5%;
        }
    </style>
</head>
<body>
<section class="normal-white">
    <div class="container">
        <form id="PDF_DO" name="user" cssClass="form-horizontal" role="form" method="post">
            <div class="col-md-offset-2 col-md-8">
                <div class="inner-container-white">
                    <div class="col-sm-12 text-gray">
                        <i class="fa fa-check-square-o fa-3x" style="vertical-align: middle; line-height:40px;"></i>&nbsp;<span style="font-size: 35px; vertical-align: middle; line-height:40px;"><b> PDF 처리</b></span>
                    </div>
                    <div class="spacer-small">&nbsp;</div>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> ${okCount} 건 성공 / 총 ${totalCount} 건 </b></span>
                    </div>
                    <hr/>
                    <div class="col-sm-12 text-gray">
                        <span style="font-size: 25px; vertical-align: middle; line-height:70px;"><b> 실패 리스트 </b></span>
                    </div>
                    <div class="col-sm-12 text-gray">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>NO</th>
                                    <th>APPL_NO</th>
                                    <th>USER_ID</th>
                                    <th>APPL_ID</th>
                                </tr>
                            </thead>
                            <tbody>
<c:forEach items="${failedList}" var="appl" varStatus="failedStatus">
                                <tr>
                                    <td>${failedStatus.index + 1}</td>
                                    <td>${appl.applNo}</td>
                                    <td>${appl.userId}</td>
                                    <td>${appl.applId}</td>
                                </tr>
</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>

<content tag="local-script">
    <script type="text/javascript">
        $(document).ready( function() {


        });

    </script>
</content>

</body>
</html>
