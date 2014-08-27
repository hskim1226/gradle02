<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <script type="text/javascript">
        $.noConflict();
        jQuery(document).ready( function(){
            jQuery(".qna").on('click', function(){
                location.href = "${contextPath}/admin/qna/detail?id="+jQuery(this).attr('qna-id');
            }).css("cursor","pointer");

            jQuery("#answer-button").on('click', function(){
                jQuery("#answer-form").submit();
            }).css("cursor","pointer");
        });
    </script>
</head>
<body>
<div id="LblockMain">
    <div id="LblockPageTitle">
        <div id="LblockPageLocation">
            <ul>
                <li class="Lfirst"><span><a href="#">HOME</a></span></li>
                <li><span><a href="#">Q&A</a></span></li>
                <li class="Llast"><span>Q&A 상세</span></li>
            </ul>
        </div>

        <h1>Q&A 상세</h1>
    </div>

    <div id="LblockMainBody" >
    <form id="answer-form" action="${contextPath}/admin/qna/answer" method="post">
        <input type="hidden" name="id" value="${qna.id}" />
        <div id="LblockDetail02" class="LblockDetail">
            <table summary="사원 상세정보">
                <caption>사원 상세정보</caption>
                <tbody>
                <tr>
                    <th><label for="title">제목</label></th>
                    <td id="title">${qna.title}</td>
                </tr>
                <tr>
                    <th><label for="contents">질문</label></th>
                    <td id="contents">${qna.contents}</td>
                </tr>
                <tr>
                    <th><label for="answer">답변</label></th>
                    <td id="answer">
                        <textarea name="answer" rows="5" cols="70">${fn:escapeXml(qna.answer)}</textarea>

                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
        <div id="LblockSubbutton01" class="LblockSubbutton">
            <a href="#"><input type="button" id="answer-button" value="답변 저장" /></a>
            <a href="#"><input type="button" id="list-button" value="목록" /></a>
        </div>
    </div>


</div>
<content tag="local-script">
    <script>

    </script>
</content>

</body>
</html>
