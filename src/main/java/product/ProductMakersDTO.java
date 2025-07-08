package product;

public class ProductMakersDTO {
	
	private int productMakerId;
	private String makerName;
	private String makerTypeCode;

	//メーカーマスタidセット・取得
	public int getProductMakerId() {
		return productMakerId;
	}
	
    public void setProductMakerId(int productMakerId) {
        this.productMakerId = productMakerId;
    }
    
    //メーカー名セット・取得
  	public String getMakerName() {
  		return makerName;
  	}
  	
    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }
    
    //メーカーコードセット・取得
  	public String getMakerTypeCode() {
  		return makerTypeCode;
  	}
  	
    public void setMakerTypeCode(String makerTypeCode) {
        this.makerTypeCode = makerTypeCode;
    }
}
