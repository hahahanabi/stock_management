<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.List, java.util.ArrayList" %>
<%-- <%@ page import="javax.servlet.http.HttpSession"%> --%>
<%@ page import="helper.Helper"%>
<%@ page import="organization.OrganizationDTO" %>  
<%@ page import="user.UserDataBeans" %>
 <%
    Helper h = Helper.getInstance();
 	HttpSession hs = request.getSession();
	UserDataBeans udb = null;
	boolean reinput = false;
	//元々入力していたものを取得
	    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
	        reinput = true;
	        udb = (UserDataBeans)hs.getAttribute("udb");
	    }
    List<OrganizationDTO> allOrganizations = (List<OrganizationDTO>)request.getAttribute("allOrganizations");
    List<OrganizationDTO> allDepartments = new ArrayList<>();
    List<OrganizationDTO> allStores = new ArrayList<>();
    //部署データ飲みの配列作成
    for (OrganizationDTO organization : allOrganizations) {
    	String type = organization.getOrganizationTypeCode();
    	if ("head_office".equals(type) || "department".equals(type)) {
    		allDepartments.add(organization);
    	}
    }
	//課or店舗を格納
    for (OrganizationDTO organization : allOrganizations) {
    	String type = organization.getOrganizationTypeCode();
    	if ("store".equals(type)) {
    		allStores.add(organization);
    	}
    }
    System.out.print("セッション" + hs.getAttribute("ac"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>ユーザー登録</title>
</head>
<body>
	<h3>ユーザー登録</h3>
	<form action="UserConfirm" method="POST">
		<label>名前：</label>
		<input type="text" name="name" value="<% if(reinput){out.print(udb.getUserName());}%>"></input><br>
		<label>所属：</label>
		<select name="organization" id="organization" onchange="selectDepartment()">
			<%for (OrganizationDTO department : allDepartments) {
				String selected = "";
				if (reinput && department.getOrganizationId() == udb.getOrganizationId()) {
					selected = "selected";
				}
			%>	
				<option value="<%= department.getOrganizationId() %>" <%=selected %>><%= department.getOrganizationName() %></option>
		    <%} %>
		</select> 
		<div id="storeSelectArea" style="display:none;">
		  <label for="storeSelect">課or店舗を選択：</label>
		  <select id="storeSelect" name="storeSelect"></select>
		</div>
		<br>
		<label>メールアドレス：</label>
		<input type="text" name="email"></input><br>
		<label>パスワード：</label>
		<input type="text" name="password"></input><br>
		
		<input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面へ">
	</form>
	<br>
</body>
<%=h.home()%>
</html>

<script>
  // allStoresをJavaScriptの配列として用意
  const allStores = [
    <% for (OrganizationDTO store : allStores) { %>
      { id: <%= store.getOrganizationId() %>, name: '<%= store.getOrganizationName() %>', type: '<%= store.getOrganizationTypeCode() %>' },
    <% } %>
  ];
  //確認画面から戻ってきた際にデータ使用するためにreinput変数とudbをそれぞれjavaScriptに
  const reinput = <%=reinput%>;
  const storeIdByUdb = <% 
		  int storeId = 0;
		  if (reinput == true && (udb != null && udb.getOrganizationStoreId() != 0)) {
			  storeId = udb.getOrganizationStoreId();
		  }
		  out.print(storeId);
		  %>; 
</script>
<script>
//部署選択した際の課or店舗選択の関数
  function selectDepartment() {
	  const orgSelect = document.getElementById("organization");
	  const selectedDeptName = orgSelect.options[orgSelect.selectedIndex].text.trim();

	  const storeDiv = document.getElementById("storeSelectArea");
	  const storeSelect = document.getElementById("storeSelect");

	  if (selectedDeptName === "営業部") {
	    // 営業部なら店舗を絞って表示
	    storeSelect.innerHTML = ""; // 一旦クリア

	    // 営業部に紐づく店舗だけフィルター（type === 'store' など条件付け可能）
console.log('ジャバスクリプトのallStores', allStores);
	    allStores.forEach(store => {
	      const option = document.createElement("option");
	      option.value = store.id;
	      option.text = store.name;
	console.log("じゃばすく　store.id", store.id);
	console.log("じゃばすくstoreIdByUd",storeIdByUdb);
		//確認画面から戻ってきた際、入力値があった場合はそのままセット
		if (reinput && store.id === storeIdByUdb) {
			 option.selected = true;
		} 
	     
	      storeSelect.appendChild(option);
	    });

	    storeDiv.style.display = "block";
	  } else {
	    storeDiv.style.display = "none";
	  }
  }

//ページ読み込み時にselectDepartment()を実行
  window.addEventListener("DOMContentLoaded", function () {
    selectDepartment();
  });
</script>