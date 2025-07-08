package product;

public class ProductCategoriesDTO {
	
	private int productCategoryId;
	private String categoryName;
	private String categoryTypeCode;

	//カテゴリマスタidセット・取得
	public int getProductCategoryId() {
		return productCategoryId;
	}
	
    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
    
    //カテゴリ名セット・取得
  	public String getCategoryName() {
  		return categoryName;
  	}
  	
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    //カテゴリコードセット・取得
  	public String getCategoryTypeCode() {
  		return categoryTypeCode;
  	}
  	
    public void setCategoryTypeCode(String categoryTypeCode) {
        this.categoryTypeCode = categoryTypeCode;
    }
}
