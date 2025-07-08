package stock;

/**
 * 在庫情報を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * @version 1.00
 * @author enomoto
 */
public class StockDTO {
	
	private int stockId;
	private int stockQuantity;
	private int purchaseUnitPrice;
	private int saleUnitPrice;
	private String otherInfo;
	private int productId;
	private int capacityId;
	private int colorId;
	private String userName;
	
	   //在庫idセット・取得
		public int getStockId() {
			return stockId;
		}
		
	    public void setStockId(int stockId) {
	        this.stockId = stockId;
	    }
	    
	    //在庫数セット・取得
		public int getStockQuantity() {
			return stockQuantity;
		}
		
	    public void setStockQuantity(int stockQuantity) {
	        this.stockQuantity = stockQuantity;
	    }

	    //仕入単価セット・取得
		public int getPurchaseUnitPrice() {
			return purchaseUnitPrice;
		}
		
	    public void setPurchaseUnitPrice(int purchaseUnitPrice) {
	        this.purchaseUnitPrice = purchaseUnitPrice;
	    }
	    
	    //販売単価セット・取得
		public int getSaleUnitPrice() {
			return saleUnitPrice;
		}
		
	    public void setSaleUnitPrice(int saleUnitPrice) {
	        this.saleUnitPrice = saleUnitPrice;
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
  		
  	    public void setProductId(int productId) {
  	        this.productId = productId;
  	    }
  	    
  	    //製品容量中間テーブルidセット・取得
  		public int getCapacityId() {
  			return capacityId;
  		}
  		
  	    public void setCapacityId(int capacityId) {
  	        this.capacityId = capacityId;
  	    }
  	    
  	    //製品色彩中間テーブルidセット・取得
  		public int getColorId() {
  			return colorId;
  		}
  		
  	    public void setColorId(int colorId) {
  	        this.colorId = colorId;
  	    }
  	    
  	    //登録時ユーザー名セット・取得
  		public String getUserName() {
  			return userName;
  		}
  		
  	    public void setUserName(String userName) {
  	        this.userName = userName;
  	    }


}
