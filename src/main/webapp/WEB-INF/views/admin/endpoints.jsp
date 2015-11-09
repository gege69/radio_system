<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Endpoint list</title></head>

<style>
      tr:nth-of-type(odd) {
      background-color:#9CB5C4;
    }
</style>

<body>
<table>
  <thead>
  <tr>
    <th>path</th>
    <th>methods</th>
    <th>consumes</th>
    <th>produces</th>
    <th>params</th>
    <th>headers</th>
    <th>custom</th>
    <th>Controller</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${endPoints}" var="endPoint">
    <tr>
      <td>${endPoint.patternsCondition}</td>
      <td>${endPoint.methodsCondition}</td>
      <td>${endPoint.consumesCondition}</td>
      <td>${endPoint.producesCondition}</td>
      <td>${endPoint.paramsCondition}</td>
      <td>${endPoint.headersCondition}</td>
      <td>${empty endPoint.customCondition ? "none" : endPoint.customCondition}</td>
      <td>${endPoint.controller}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>