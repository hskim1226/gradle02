<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        section.application-mylist {
            padding: 200px 0 60px;
            background: #443355;
            color: #fdfdfd;
            position:relative;
        }

        section.application-mylist h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section.application-mylist .spacer-big {
            margin-bottom: 7em;
        }

        section.application-mylist .spacer-mid {
            margin-bottom: 5em;
        }

        section.application-mylist .spacer-small {
            margin-bottom: 3em;
        }

        section.application-mylist .spacer-tiny {
            margin-bottom: 1em;
        }

    </style>
</head>
<body>

<section id="section-contact" class="section appear clearfix application-mylist">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-10 col-md-offset-1">
                <h2 class="slogan">Q&A</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="cform" id="contact-form">
                    <form action="contact/contact.php" method="post" role="form" class="contactForm">
                        <div class="form-group">
                            <label for="title">제목</label>
                            <p id="title">${qna.title}</p>
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <label for="contents">내용</label>
                            <p id="contents">${qna.contents}</p>
                        </div>
                        <sec:authorize
                        <button type="button" class="btn btn-theme btn-sm">답글 달기</button>
                        <button type="button" class="btn btn-theme btn-sm">SEND MESSAGE</button>
                    </form>

                </div>
            </div>
            <!-- ./span12 -->
        </div>

    </div>
</section>


    <script type="text/javascript">
        $(document).ready( function(){
            $(".qna").on('click', function(){
                location.href = "${contextPath}/qna/detail?id="+$(this).attr('qna-id');
            }).css("cursor","pointer");
        });
    </script>
</body>


</html>