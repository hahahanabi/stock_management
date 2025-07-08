package product;

public class ProductDTO {

	private int productId;
	private String productName;
	private String productCode;
	private String modelName;
	private int productCategoryId;
	private int productMakerId;
	
	
	//製品idセット・取得
	public int getProductId() {
		return productId;
	}
	
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    //製品名セット・取得
  	public String getProductName() {
  		return productName;
  	}
  	
	public void setProductName(String productName) {
	   this.productName = productName;
	}
	
	//製品コードセット・取得
  	public String getProductCode() {
  		return productCode;
  	}
  	
	public void setProductCode(String productCode) {
	   this.productCode = productCode;
	}
	
	//型番セット・取得
  	public String getModelName() {
  		return modelName;
  	}
  	
	public void setModelName(String modelName) {
	   this.modelName = modelName;
	}
	
	//製品カテゴリーidセット・取得
  	public int getProductCategoryId() {
  		return productCategoryId;
  	}
  	
	public void setProductCategoryId(int productCategoryId) {
	   this.productCategoryId = productCategoryId;
	}
	
	//製品メーカーidセット・取得
  	public int getProductMakerId() {
  		return productMakerId;
  	}
  	
	public void setProductMakerId(int productMakerId) {
	   this.productMakerId = productMakerId;
	}
}
