<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th width="100px">车次号</th>
		<th width="80px">起点</th>
		<th width="80px">终点</th>
		<th width="135px">票数</th>
		<th width="135px">票价</th>
	</tr>
	<c:forEach items="${tickets}" var="ticket">
		<tr>
		<ticket>
			<td><carid>${ticket.car_id}</carid></td>
			<td><origin>${ticket.origin}</origin></td>
			<td><point>${ticket.point}</point></td>
			<td><num>${ticket.num }</num></td>
			<td><price>${ticket.price }</price></td>
		</ticket>
		</tr>
	</c:forEach>
</table>
</body>
</html>