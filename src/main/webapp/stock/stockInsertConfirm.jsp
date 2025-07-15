<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="stock.StockDataBeans, user.UserDataBeans" %>
<%
HttpSession hs = request.getSession();
UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
StockDataBeans sdb = (StockDataBeans)request.getAttribute("sdb");
System.out.print("登録画面に戻る際のユーザーメール"+ udb.getUserEmail());
System.out.print("登録画面に戻る際のユーザーパス"+ udb.getUserPassword());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫登録確認</title>
</head>
<body>
	<h3>以下の情報で登録します<br>よろしいでしょうか？</h3><br>
	<p>商品名：<%=sdb.getProductName() %></p>
	<p>容量：<%=sdb.getCapacity() + sdb.getCapacityType()%></p>
	<p>色：<%=sdb.getColorName() %></p>
	<p>カテゴリー：<%=sdb.getCategoryName() %></p>
	<p>メーカー：<%=sdb.getMakerName() %></p>
	<p>初回仕入（在庫）数：
	<%
	out.print(sdb.getStockQuantity());
	if (sdb.getStockQuantity() == 0) {
		out.print(" (未入荷)");
	}
	%></p>
	<p>型番：<%=sdb.getModelName() %></p>
	<p>仕入単価：<%=sdb.getPurchaseUnitPrice() %></p>
	<p>販売価格：
	<%
	out.print(sdb.getSaleUnitPrice());
	if (sdb.getSaleUnitPrice() == 0) {
		out.print(" (未確定)");
	}
	%></p>
	<p>その他特記事項：<%=sdb.getOtherInfo() %></p>
  	<form action="StockInsert" method="POST">
        <input type="submit" name="no" value="キャンセル">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="hidden" name="email" value="<%= udb.getUserEmail()%>">
  		<input type="hidden" name="password" value="<%= udb.getUserPassword()%>">
    </form>
	<form action="StockInsertResult" method="POST">
		<input type="hidden" name="stockQuantity" value="<%=sdb.getStockQuantity() %>">
		<input type="hidden" name="purchaseUnitPrice" value="<%=sdb.getPurchaseUnitPrice() %>">
		<input type="hidden" name="saleUnitPrice" value="<%=sdb.getSaleUnitPrice() %>">
		<input type="hidden" name="otherInfo" value="<%=sdb.getOtherInfo() %>">
		<input type="hidden" name="productId" value="<%=sdb.getProductId() %>">
		<input type="hidden" name="capacityId" value="<%=sdb.getCapacityId() %>">
		<input type="hidden" name="colorId" value="<%=sdb.getColorId() %>">
		<input type="hidden" name="stockAddFlag" value="<%= sdb.getStockAddFlag()%>">
		<input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
		
		<button type="submit" name="btnSubmit">登録</button>
	</form>
</body>
</html>