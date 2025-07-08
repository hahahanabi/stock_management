<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="helper.Helper, user.UserDataBeans"%>
<%
	Helper h = Helper.getInstance();
	HttpSession hs = request.getSession();
	//エラー画面から戻ってきた時の初期値に入力していたものがあればそれを入れる
	UserDataBeans udb = null;
	boolean reinput = false;
	//元々入力していたものを取得
	  if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
	      reinput = true;
	      udb = (UserDataBeans)hs.getAttribute("udb");
	  }
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h3>ログイン</h3>
	<form action="StockInsert" method="POST">
		<label>メールアドレス：</label>
		<input type="text" name="email" value="<%if(reinput){out.print(udb.getUserEmail());}%>"></input><br>
		<label>パスワード：</label>
		<input type="text" name="password" value="<%if(reinput){out.print(udb.getUserPassword());}%>"></input><br>
		
		<input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="ログイン">
	</form>
</body>
<%=h.home()%>
</html>