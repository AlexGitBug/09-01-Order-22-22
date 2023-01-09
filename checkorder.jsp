
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
  <title>Check booking</title>
</head>
<body>
<%@ include file="header.jsp" %>
____________________________________________________________________________________
<h1>Confirm</h1>
<ul>
  ${requestScope.order.userInfoId} - ${requestScope.order.room} - ${requestScope.order.beginTimeOfTheOrder} - ${requestScope.order.endTimeOfTheOrder} -${requestScope.order.condition} - ${requestScope.order.message}
</ul>

<form action="${pageContext.request.contextPath}/checkorder" method="get">
  <button type="submit" name="orderId" value="${requestScope.order.id}"> Check </button>
</form>
</form>
</body>
</html>