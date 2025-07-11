package product;

/**
 * 商品情報単体を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * @version 1.00
 * @author enomoto
 */
public class MachineProductIncetancesDTO {

	private int machineProductIncetanceId;
	private int productId;
	private String productUnitCode;
	private int capacityId;
	private int colorId;
	private int stockId;
	private int stockAddFlag;
	private int stockStatusTypeCodeId;
	private String stockStatusTypeCodeType;
	private String userName;
	
	
	//機械製品単体テーブルidセット・取得
	public int getMachineProductIncetanceId() {
		return machineProductIncetanceId;
	}
	
    public void setMachineProductIncetanceId(int machineProductIncetanceId) {
        this.machineProductIncetanceId = machineProductIncetanceId;
    }
    
    //製品idセット・取得
  	public int getProductId() {
  		return productId;
  	}
  	
	public void setProductId(int productId) {
	     this.productId = productId;
	}
	
	//商品コードセット・取得
  	public String getProductUnitCode() {
  		return productUnitCode;
  	}
  	
	public void setProductUnitCode(String productUnitCode) {
	     this.productUnitCode = productUnitCode;
	}
	
	//容量idセット・取得
  	public int getCapacityId() {
  		return capacityId;
  	}
  	
	public void setCapacityId(int capacityId) {
	     this.capacityId = capacityId;
	}
	
	//色彩idセット・取得
  	public int getColorId() {
  		return colorId;
  	}
  	
	public void setColorId(int colorId) {
	     this.colorId = colorId;
	}
	
	//在庫idセット・取得
  	public int getStockId() {
  		return stockId;
  	}
  	
	public void setStockId(int stockId) {
	     this.stockId = stockId;
	}
	
	//在庫追加フラグセット・取得
  	public int getStockAddFlag() {
  		return stockAddFlag;
  	}
  	
	public void setStockAddFlag(int stockAddFlag) {
	     this.stockAddFlag = stockAddFlag;
	}
	
	//在庫ステータスidセット・取得
  	public int getStockStatusTypeCodeId() {
  		return stockStatusTypeCodeId;
  	}
  	
	public void setStockStatusTypeCodeId(int stockStatusTypeCodeId) {
	     this.stockStatusTypeCodeId = stockStatusTypeCodeId;
	}
	
	//在庫ステータスコードセット・取得
  	public String getStockStatusTypeCodeType() {
  		return stockStatusTypeCodeType;
  	}
  	
	public void setStockStatusTypeCodeType(String stockStatusTypeCodeType) {
	     this.stockStatusTypeCodeType = stockStatusTypeCodeType;
	}
	
	//登録時ユーザー名セット・取得
	public String getUserName() {
		return userName;
	}
	
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
