<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%--
  Created by IntelliJ IDEA.
  User: hanmomhanda
  Date: 14. 8. 10
  Time: 오후 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8"/>
</head>
<body>
<%
    String origin = "p2하루살이";
    String urlEncoded = URLEncoder.encode(origin, "UTF-8");
    String urlEncoded2 = URLEncoder.encode(urlEncoded, "UTF-8");
    String urlEncoded3 = URLEncoder.encode(urlEncoded2, "UTF-8");
    String urlDecoded = URLDecoder.decode(urlEncoded, "UTF-8");
    String urlDecoded2 = URLDecoder.decode(urlDecoded, "UTF-8");

    String utf8decoded = new String(urlEncoded.getBytes(), "UTF-8");
%>
<p>origin      : <%=origin%></p>
<p>urlEncoded  : <%=urlEncoded%></p>
<p>utf8decoded  : <%=utf8decoded%></p>
<p>urlEncoded2 : <%=urlEncoded2%></p>
<p>urlEncoded3 : <%=urlEncoded3%></p>
<p>urlDecoded  : <%=urlDecoded%></p>
<p>urlDecoded2  : <%=urlDecoded2%></p>
<p>message : <%=%></p>
<form id="test">

</form>
<script>
    var form = document.getElementById('test');
//    console.log(form);
    var input1 = document.createElement('input');
    input1.type="hidden";
    input1.id = "LGD_PRODUCTINFO";
    var tmp = '에예예~~';
    input1.value=tmp;
//    console.log(input1);
    form.appendChild(input1);
//    console.log(form);
    console.log(document.getElementById('LGD_PRODUCTINFO').value);
</script>
</body>
</html>
