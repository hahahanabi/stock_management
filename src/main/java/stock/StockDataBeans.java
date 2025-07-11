package stock;

public class StockDataBeans {
	
	private int stockId;
	private int stockQuantity;
	private int purchaseUnitPrice;
	private int saleUnitPrice;
	private String modelName;
	private String otherInfo;
	private int productId;
	private String productName;
	private int capacityId;
	private int capacity;
	private String capacityType;
	private int colorId;
	private String colorName;
	private int makerId;
	private String makerName;
	private int categoryId;
	private String categoryName;
	private String userName;
	private int stockAddFlag;
	//配列クラスを作って、それをリストに入れるのが、各データを配列で渡す方法っぽい
	//private List capacityList; 
	
	 public StockDataBeans(){
	        this.stockId = 0;
	        this.stockQuantity = 0;
	        this.purchaseUnitPrice = 0;
	        this.saleUnitPrice = 0;
	        this.modelName = "";
	        this.otherInfo ="";
	        this.productId = 0;
	        this.productName = "";
	        this.capacityId = 0;
	        this.capacity = 0;
	        this.capacityType = "";
	        this.colorId = 0;
	        this.colorName = "";
	        this.makerId = 0;
	        this.makerName = "";
	        this.categoryId = 0;
	        this.categoryName = "";
	        this.userName = "";
	    }
	 
	 
	 	//在庫idセット・取得
		public int getStockId() {
			return stockId;
		}
		
	    public void setStockId(String stockId) {
	        this.stockId = Integer.parseInt(stockId);
	    }
	    
	    //商品名セット・取得
  		public String getProductName() {
  			return productName;
  		}
  		
  	    public void setProductName(String productName) {
  	        this.productName = productName;
  	    }
	    
	    //在庫数セット・取得
		public int getStockQuantity() {
			return stockQuantity;
		}
		
	    public void setStockQuantity(String stockQuantity) {
	        this.stockQuantity = Integer.parseInt(stockQuantity);
	    }

	    //仕入単価セット・取得
		public int getPurchaseUnitPrice() {
			return purchaseUnitPrice;
		}
		
	    public void setPurchaseUnitPrice(String purchaseUnitPrice) {
	        this.purchaseUnitPrice =  Integer.parseInt(purchaseUnitPrice);
	    }
	    
	    //販売単価セット・取得
		public int getSaleUnitPrice() {
			return saleUnitPrice;
		}
		
	    public void setSaleUnitPrice(String saleUnitPrice) {
	        this.saleUnitPrice =  Integer.parseInt(saleUnitPrice);
	    }
	    
	    //型番セット・取得
  		public String getModelName() {
  			return modelName;
  		}
  		
  	    public void setModelName(String modelName) {
  	        this.modelName = modelName;
  	    }
	    
	    //その他特記事項セット・取得
		public String getOtherInfo() {
			return otherInfo;
		}
		
	    public void setOtherInfo(String otherInfo) {
	        this.otherInfo = otherInfo;
	    }
	    
	    //製品idセット・取得
		public int getProductId() {
			return productId;
		}
		
	    public void setProductId(String productId) {
	        this.productId =  Integer.parseInt(productId);
	    }
	    
	    //製品容量テーブルidセット・取得
		public int getCapacityId() {
			return capacityId;
		}
		
	    public void setCapacityId(String capacityId) {
	        this.capacityId =  Integer.parseInt(capacityId);
	    }
	    
	    //製品容量セット・取得
 		public int getCapacity() {
 			return capacity;
 		}
 		
 	    public void setCapacity(int capacity) {
 	        this.capacity = capacity;
 	    }
 	    
 	    //製品容量タイプセット・取得
 		public String getCapacityType() {
 			return capacityType;
 		}
 		
 	    public void setCapacityType(String capacityType) {
 	        this.capacityType = capacityType;
 	    }
	    
	    //製品色彩テーブルidセット・取得
		public int getColorId() {
			return colorId;
		}
		
	    public void setColorId(String colorId) {
	        this.colorId =  Integer.parseInt(colorId);
	    }
	    
	    //製品色彩名セット・取得
		public String getColorName() {
			return colorName;
		}
		
	    public void setColorName(String colorName) {
	        this.colorName = colorName;
	    }
	    
	    //製品メーカーidセット・取得
		public int getMakerId() {
			return makerId;
		}
		
	    public void setMakerId(String makerId) {
	        this.makerId =  Integer.parseInt(makerId);
	    }
	    
	    //製品メーカー名セット・取得
  		public String getMakerName() {
  			return makerName;
  		}
  		
  	    public void setMakerName(String makerName) {
  	        this.makerName = makerName;
  	    }
	    
	    //製品カテゴリーidセット・取得
		public int getCategoryId() {
			return categoryId;
		}
		
	    public void setCategoryId(String categoryId) {
	        this.categoryId =  Integer.parseInt(categoryId);
	    }
	    
	    //製品カテゴリー名セット・取得
  		public String getCategoryName() {
  			return categoryName;
  		}
  		
  	    public void setCategoryName(String categoryName) {
  	        this.categoryName =  categoryName;
  	    }
  	    
  	    //登録時ユーザー名セット・取得
  		public String getUserName() {
  			return userName;
  		}
  		
  	    public void setUserName(String userName) {
  	        this.userName = userName;
  	    }
  	    
  	    //在庫追加フラグセット・取得
  		public int getStockAddFlag() {
  			return stockAddFlag;
  		}
  		
  	    public void setStockAddFlag(String stockAddFlag) {
  	        this.stockAddFlag = Integer.parseInt(stockAddFlag);
  	    }
  	    

  	    
  	    //DB操作用のジャバビーンズにセット
  	    public void SD2DTOMapping(StockDTO sd){
  	        sd.setStockQuantity(this.stockQuantity);
  	        sd.setPurchaseUnitPrice(this.purchaseUnitPrice);
  	        sd.setSaleUnitPrice(this.saleUnitPrice);
  	        sd.setOtherInfo(this.otherInfo);
  	        sd.setProductId(this.productId);
  	        sd.setCapacityId(this.capacityId);
  	        sd.setColorId(this.colorId);
  	        sd.setUserName(this.userName);
  	    }
  	    
//  	    public List getCapacityList() {
//  	    	return capacityList;
//  	    }
//  	    
//  	    public void setCapacityList(List capacityList) {
//  	    	this.capacityList = capacityList;
//  	    }

}
