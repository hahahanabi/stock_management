<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDataBeans, user.UserDataDTO, helper.Helper, product.ProductDTO, product.ProductsToCapacitiesDTO, 
product.CapacityDTO, product.ProductCategoriesDTO, product.ProductMakersDTO, product.ProductsToColorsDTO, product.ColorDTO" %>
<%@ page import="java.util.ArrayList, java.util.List" %>
<%
	Helper h = Helper.getInstance();
	HttpSession hs = request.getSession();
	//ログイン時入力データと、見つかったユーザーデータ
	UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
	UserDataDTO udd = (UserDataDTO)request.getAttribute("udd");
	//ログイン時バリデーション用データ
	ArrayList<String> chkLoginPropaties = udb.chkLoginPropaties();
	//ユーザー情報表示用データ
	String userName = udb.getUserName();
	String organizationName = udb.getOrganizationName();
	String organizationStoreName = udb.getOrganizationStoreName();
	
	//オートコンプリート用データ
	List<ProductDTO> allProducts = new ArrayList<>();
	List<ProductsToCapacitiesDTO> allProductsToCapacities = new ArrayList<>();
	List<CapacityDTO> allCapacities = new ArrayList<>();
	List<ProductCategoriesDTO> allProductCategories = new ArrayList<>();
	List<ProductMakersDTO> allProductmakers = new ArrayList<>();
	List<ProductsToColorsDTO> allProductsToColors = new ArrayList<>();
	List<ColorDTO> allColors = new ArrayList<>();
	//ログインできたらオートコンプリート用データセット
	if (request.getAttribute("allProducts") !=null) {
		allProducts = (List<ProductDTO>)request.getAttribute("allProducts");
	}
	if (request.getAttribute("allProductsToCapacities") !=null) {
		allProductsToCapacities = (List<ProductsToCapacitiesDTO>)request.getAttribute("allProductsToCapacities");
	}
	if (request.getAttribute("allCapacities") !=null) {
		allCapacities = (List<CapacityDTO>)request.getAttribute("allCapacities");
	}
	if (request.getAttribute("allProductCategories") !=null) {
		allProductCategories = (List<ProductCategoriesDTO>)request.getAttribute("allProductCategories");
	}
	if (request.getAttribute("allProductmakers") !=null) {
		allProductmakers = (List<ProductMakersDTO>)request.getAttribute("allProductmakers");
	}
	if (request.getAttribute("allProductsToColors") !=null) {
		allProductsToColors = (List<ProductsToColorsDTO>)request.getAttribute("allProductsToColors");
	}
	if (request.getAttribute("allColors") !=null) {
		allColors = (List<ColorDTO>)request.getAttribute("allColors");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫登録・表示画面</title>
<style>
    /* モーダルの背景 */
    .modal-overlay {
      display: none; /* 初期は非表示 */
      position: fixed;
      top: 0; left: 0;
      width: 100%; height: 100%;
      background: rgba(0,0,0,0.5);
      justify-content: center;
      align-items: center;
      z-index: 1000;
    }

    /* モーダルの中身 */
    .modal-content {
      background: #fff;
      padding: 20px;
      border-radius: 10px;
      width: 300px;
      text-align: center;
      box-shadow: 0 4px 10px rgba(0,0,0,0.3);
    }

    /* モーダルの表示用クラス */
    .modal-overlay.active {
      display: flex;
    }
  </style>
</head>
<body>
	<%if (chkLoginPropaties.size() == 0 && udd != null) {%>
		<div>
			<div>
				<a>ユーザー：<%
				 if (!organizationStoreName.isEmpty()) {
					out.print(organizationName + organizationStoreName + " " + userName);
				} else {
					out.print(organizationName + " " + userName); 
				} 
				%></a>
				 <button id="openLogoutModalBtn">ログアウト</button>
				 <div id="modal" class="modal-overlay">
				    <div class="modal-content">
				      <p>ログアウトしますか？</p>
				      <button id="closeModalBtn">キャンセル</button>
				      <form action="Logout" method="POST">
				      	<input type="submit" value="はい">
				      </form>
				    </div>
				 </div>
				<div>
					<h3>商品在庫登録</h3>
					<form action="StockInsertConfirm" method="POST">
						<label>商品名：</label>
						<input list="products" id="productDisplay"  onchange="selectProduct()">
						<input type="hidden" name="productId" id="productId">
						<datalist id="products">
						  <% for (ProductDTO product : allProducts) { %>
						    <option data-id="<%= product.getProductId() %>" value="<%= product.getProductName() %>"></option>
						  <% } %>
						</datalist>
						<label>容量：</label>
						<select name="capacityId" id="capacityId"></select>
						<label>仕入単価：</label>
						<input type="text" name="purchaseUnitPrice"></input>
						<label>カテゴリー：</label>
						<select id="categoryId" name="categoryId"></select>
						<label>メーカー：</label>
						<select id="makerId" name="makerId"></select><br>
						<label>販売価格：</label>
						<input type="text" name="SaleUnitPrice"></input>
						<label>色：</label>
						<select id="colorId" name="colorId"></select>
						<label>型番：</label>
						<input type="text" id="modelName" name="modelName" />
						<label>初回仕入（在庫）数：</label>
						<input type="text" name="stockQuantity"></input>
						<label>その他記載事項：</label>
						<input type="text" name="otherInfo"></input>
						<input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
						
						<button type="submit" name="btnSubmit">登録</button>
					</form>
				</div>
			</div>
			<div>
				<h3>商品検索</h3>
				<form action="StockSearch" method="POST">
					<label>商品名：</label>
					<select name="productName">
						<option>あいほん</option>
					</select>
					<label>容量：</label>
					<select name="productCapacity">
						<option>128</option>
					</select>
					<label>カテゴリー：</label>
					<select name="productCategory">
						<option>スマホ</option>
					</select>
					<label>メーカー：</label>
					<select name="productMaker">
						<option>Apple</option>
					</select><br>
					<label>色：</label>
					<select name="productColor">
						<option>赤</option>
					</select>
					<label>型番：</label>
					<input type="text" name="modelName"></input>
					<label>在庫数：
						<input type="text" name="purchaseUnitPrice"></input>
						個以下
					</label>
					
					<button type="submit" name="btnSubmit">検索</button>
				</form>
			</div>
			<div>
				<h3>商品一覧</h3>
				<table>
					<thead>
						<tr>
							<th>商品名</th>
							<th>色</th>
							<th>カテゴリー</th>
							<th>メーカー</th>
							<th>型番</th>
							<th>容量</th>
							<th>在庫数</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Iphone8</td>
							<td>赤</td>
							<td>スマホ</td>
							<td>Apple</td>
							<td>ip8004</td>
							<td>128</td>
							<td>20</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	<%} else if (chkLoginPropaties.size() != 0) { %>
		<h3>ログインエラー</h3>
		<%=h.loginDataChkinput(chkLoginPropaties)%>
		<form action="Login" method="POST">
			<input type="submit" name="no" value="ログイン画面に戻る">
			<input type="hidden" name="mode" value="REINPUT">
  		</form>
	<% 
	} else if (chkLoginPropaties.size() == 0 && udd != null) {
	%>
		<h3>ユーザーが見つかりません。</h3>
		<%=h.login() %>
	<%}%>
</body>
</html>

<script>
	//ログアウトモーダル処理
    const openBtn = document.getElementById('openLogoutModalBtn');
    const closeBtn = document.getElementById('closeModalBtn');
    const modal = document.getElementById('modal');

    openBtn.addEventListener('click', () => {
      modal.classList.add('active');
    });

    closeBtn.addEventListener('click', () => {
      modal.classList.remove('active');
    });

    //容量中間データjacascriptに
    const allProductToCapacities = [
	    <% for (ProductsToCapacitiesDTO dto : allProductsToCapacities) { %>
	      {
	        id: <%= dto.getProductToCapacityId() %>,
	        product_id: <%= dto.getProductId() %>,
	        capacity_id: <%= dto.getCapacityId() %>
	      }<%= (allProductsToCapacities.indexOf(dto) != allProductsToCapacities.size() - 1) ? "," : "" %>
	    <% } %>
	  ];

  　//容量データjacascriptに
    const allCapacities = [
	    <% for (CapacityDTO capaDto : allCapacities) { %>
	      {
		      id: <%= capaDto.getCapacityId() %>,
		      capacity: <%= capaDto.getCapacity() %>,
		      capacity_type: "<%= capaDto.getCapacityType()%>",
		      ram: <%= capaDto.getRam()%>
	      }<%= (allCapacities.indexOf(capaDto) != allCapacities.size() - 1) ? "," : "" %>
	    <% } %>
	  ];
    console.log("allcapacities", allCapacities);

    //カテゴリーデータjavascriptに
    const allProductCategories = [
		<% for (ProductCategoriesDTO categoryDto : allProductCategories) { %>
		{
			id: <%=categoryDto.getProductCategoryId() %>,
			category_name: "<%=categoryDto.getCategoryName() %>",
			category_type_code:	"<%=categoryDto.getCategoryTypeCode() %>"
		}	
		<%= (allProductCategories.indexOf(categoryDto) != allProductCategories.size() - 1) ? "," : "" %>
	    <% } %>
    ];
    console.log("allProductCategories", allProductCategories);
    //商品データjavascriptに
    const allProducts = [
		<% for (ProductDTO proDto : allProducts) { %>
		{
			id: <%= proDto.getProductId() %>,
			product_name: "<%= proDto.getProductName() %>",
			product_code: "<%= proDto.getProductCode() %>",
			model_name: "<%= proDto.getModelName() %>",
			product_category_id: <%= proDto.getProductCategoryId() %>,
			product_maker_id: <%= proDto.getProductMakerId() %>
		}
		<%= (allProducts.indexOf(proDto) != allProducts.size() - 1) ? "," : "" %>
	    <% } %>
    ];

    //メーカーデータjavascriptに
    const allProductmakers = [
    	<% for (ProductMakersDTO makerDto : allProductmakers) { %>
		{
			id: <%= makerDto.getProductMakerId() %>,
			maker_name: "<%= makerDto.getMakerName() %>",
			maker_type_code: "<%= makerDto.getMakerTypeCode() %>"
		}
		<%= (allProductmakers.indexOf(makerDto) != allProductmakers.size() - 1) ? "," : "" %>
	    <% } %>
     ];
    console.log("allProductmakers", allProductmakers);

  	//色中間データjacascriptに
    const allProductsToColors = [
	    <% for (ProductsToColorsDTO dto : allProductsToColors) { %>
	      {
	        id: <%= dto.getProductToColorId() %>,
	        product_id: <%= dto.getProductId() %>,
	        color_id: <%= dto.getColorId() %>
	      }<%= (allProductsToColors.indexOf(dto) != allProductsToColors.size() - 1) ? "," : "" %>
	    <% } %>
	  ];

    //色データjavascriptに
    const allColors = [
    	<% for (ColorDTO colorDto : allColors) { %>
		{
			id: <%= colorDto.getColorId() %>,
			color_name: "<%= colorDto.getColorName() %>",
			color_code: "<%= colorDto.getColorCode() %>"
		}
		<%= (allColors.indexOf(colorDto) != allColors.size() - 1) ? "," : "" %>
	    <% } %>
        ];
    
    //容量表示初期値
    const capacityIdSelect = document.getElementById("capacityId");
  	//容量表示初期値
    const categoryIdSelect = document.getElementById("categoryId");
    //カテゴリー初期表示
    const makerIdSelect = document.getElementById("makerId");
    //色初期表示
    const colorIdSelect = document.getElementById("colorId");
    

    //型番取得関数
    function changeModelName(selectedProduct) {
		if (selectedProduct !=null) {
			document.getElementById("modelName").value = selectedProduct[0].model_name;
		}
    }
    
  	//商品選択した際の他の条件の挙動（オートコンプリート）
   function selectProduct() {
	   //datalistだとvalueにidが入り、それが画面表示されるから、画面には名前が表示されるように
	   const input = document.getElementById("productDisplay");
	   const datalist = document.getElementById("products");
	   const options = datalist.options;
	   //const capacityIdSelect = document.getElementById("capacity");
	   const hiddenProductId = document.getElementById("productId");
	   const inputValue = input.value;

	   hiddenProductId.value = "";
	   let selectedProductId = null;

	   // 商品名からIDを探す
	   for (let i = 0; i < options.length; i++) {
	     if (options[i].value === inputValue) {
	       selectedProductId = parseInt(options[i].dataset.id, 10);
	       hiddenProductId.value = selectedProductId;
	       break;
	     }
	   }
	   console.log("selectedProductId", selectedProductId);

		
		
		
	   //商品名に対応する容量表示処理
	   // 対象容量中間テーブルのデータ検索
	   const matchedProductToCapacity = allProductToCapacities.filter(item => item.product_id === selectedProductId);
	   console.log("対象の中間テーブル容量", matchedProductToCapacity);
	   const matchedCapacities = matchedProductToCapacity.map(item => {
				return allCapacities.find(capacity => capacity.id === item.capacity_id);
		   });
	   console.log("matchedCapacities", matchedCapacities);
	   //商品選択があったら選択された商品の容量、商品入力値が削除されたら全容量
	   displayCapacities = selectedProductId !== null ? matchedCapacities : allCapacities;
	   // 容量セレクトボックスを再構築
	   capacityIdSelect.innerHTML = "";
	   //容量表示
	   displayCapacities.forEach(cap => {
		    const option = document.createElement("option");
		    option.value = cap.id;
		    option.textContent = cap.capacity + cap.capacity_type; 
		    capacityIdSelect.appendChild(option);
		});

	   　//選択された商品データ
   		const selectedProduct = selectedProductId !== null ? allProducts.filter(product => product.id === selectedProductId) : null;
   		console.log("選択製品データオブジェクト", selectedProduct);

   		
		//商品名に対応するカテゴリ表示
		const displayCategory = selectedProductId !== null ? allProductCategories.filter(category => category.id === selectedProduct[0].product_category_id) : allProductCategories;
		console.log("カテゴリ表示用オブジェクト", displayCategory);
   		categoryIdSelect.innerHTML = "";
   		//カテゴリー表示
	   	displayCategory.forEach(cat => {
		    const option = document.createElement("option");
		    option.value = cat.id;
		    option.textContent = cat.category_name; 
		   	categoryIdSelect.appendChild(option);
		});

		//商品名に対応するメーカー表示
		const displayMaker = selectedProductId !== null ? allProductmakers.filter(maker => maker.id === selectedProduct[0].product_maker_id) : allProductmakers;
		console.log("メーカー表示用オブジェクト", displayMaker);
		makerIdSelect.innerHTML = "";
   		//メーカー表示
	   	 displayMaker.forEach(maker => {
		    const option = document.createElement("option");
		    option.value = maker.id;
		    option.textContent = maker.maker_name; 
		   	makerIdSelect.appendChild(option);
		});
			
	   //商品名に対応する色表示
	   // 対象色彩中間テーブルのデータ検索
	   const matchedProductToColors = allProductsToColors.filter(item => item.product_id === selectedProductId);
	   console.log("対象の色中間テーブル容量", matchedProductToColors);
	   const matchedColors = matchedProductToColors.map(item => {
				return allColors.find(color => color.id === item.color_id);
		   });
	   console.log("matchedColors", matchedColors);
	   //商品選択があったら選択された商品の容量、商品入力値が削除されたら全容量
	   displayColors = selectedProductId !== null ? matchedColors : allColors;
	   // 色セレクトボックスを再構築
	   colorIdSelect.innerHTML = "";
	   //色表示
	   displayColors.forEach(color => {
		    const option = document.createElement("option");
		    option.value = color.id;
		    option.textContent = color.color_name; 
		    colorIdSelect.appendChild(option);
		});

	   //型番取得関数呼び出し
	   changeModelName(selectedProduct);

	   
	}

   //初期表示用
   window.onload = function () {
		 //容量初期表示
		 allCapacities.forEach(cap => {
		    const option = document.createElement("option");
		    option.value = cap.id;
		    option.textContent = cap.capacity + cap.capacity_type; 
		    capacityIdSelect.appendChild(option);
		});
		//カテゴリー表示
	   	allProductCategories.forEach(cat => {
		    const option = document.createElement("option");
		    option.value = cat.id;
		    option.textContent = cat.category_name; 
		   	categoryIdSelect.appendChild(option);
		});
	  	//メーカー表示
  	 		allProductmakers.forEach(maker => {
		    const option = document.createElement("option");
		    option.value = maker.id;
		    option.textContent = maker.maker_name; 
		   	makerIdSelect.appendChild(option);
		});
  	 		//色表示
	   allColors.forEach(color => {
		    const option = document.createElement("option");
		    option.value = color.id;
		    option.textContent = color.color_name; 
		    colorIdSelect.appendChild(option);
		});
   }
	
  </script>