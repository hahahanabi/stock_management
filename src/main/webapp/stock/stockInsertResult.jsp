<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="user.UserDataBeans" %>
<%
HttpSession hs = request.getSession();
UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫登録完了</title>
</head>
<body>
<h3>登録完了しました</h3>
<form action="StockInsert" method="POST">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="hidden" name="email" value="<%= udb.getUserEmail()%>">
  		<input type="hidden" name="password" value="<%= udb.getUserPassword()%>">
  		<button type="submit" name="btnSubmit">登録画面へ戻る</button>
    </form>
</body>
</html>