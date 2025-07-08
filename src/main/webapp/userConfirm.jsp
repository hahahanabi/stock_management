<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, helper.Helper, user.UserDataBeans" %>
<%
    Helper h = Helper.getInstance();
    HttpSession hs = request.getSession();
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
    ArrayList<String> validationChkList = udb.chkproperties();
    //ArrayList<String> chkList = udb.chkproperties(); //入力値空文字の場合、登録確認のところでジャバビーンズでそれぞれの空文字の名前がローマ字で入ってる
    
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ユーザー登録確認</title>
</head>
<body>
	<%if (validationChkList.size() == 0) { %>
		<h1>登録確認</h1>
		<p>名前:<%= udb.getUserName()%></p><br>
		<p>所属:<%= udb.getOrganizationName()%></p><br>
		<p>メールアドレス:<%= udb.getUserEmail()%></p><br>
		<p>パスワード:</p>
		<p id="maskedPassword">●●●●●●</p>
		<p id="realPassword" style="display: none;"><%= udb.getUserPassword()%></p>
		<button type="button" onclick="togglePassword()">表示/非表示</button><br>
		上記の内容で登録します。よろしいですか？
		 <form action="UserInsertresult" method="POST">
	            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
	            <input type="submit" name="yes" value="はい">
	        </form>
	 <%} else { %>
	 	<h2>入力が不完全です</h2>
	 	<%=h.userDataChkinput(validationChkList)%>
	 <%} %>
	  <form action="UserInsert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
            <input type="hidden" name="mode" value="REINPUT">
      </form>
        <%=h.home()%>
</body>
</html>

<script>
  function togglePassword() {
    const masked = document.getElementById("maskedPassword");
    const real = document.getElementById("realPassword");

    if (masked.style.display !== "none") {
      masked.style.display = "none";
      real.style.display = "inline";
    } else {
      masked.style.display = "inline";
      real.style.display = "none";
    }
  }
</script>