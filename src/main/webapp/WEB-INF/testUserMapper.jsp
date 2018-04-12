<%--
  Created by IntelliJ IDEA.
  User: chenzhihao
  Date: 2018/4/12
  Time: 下午2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>id</td>
            <td>name</td>
        </tr>
        <c:forEach items="${user}" var="c" varStatus="st">
            <tr>
                <td>${c.userId}</td>
                <td>${c.name}</td>

            </tr>
        </c:forEach>
    </table>
</head>
<body>

</body>
</html>
