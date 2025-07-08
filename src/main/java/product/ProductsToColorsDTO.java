package product;

public class ProductsToColorsDTO {
	
	private int productToColorId;
	private int productId;
	private int colorId;
	
	//色彩中間テーブルidセット・取得
	public int getProductToColorId() {
		return productToColorId;
	}
	
    public void setProductToColorId(int productToColorId) {
        this.productToColorId = productToColorId;
    }
    
    //製品idセット・取得
  	public int getProductId() {
  		return productId;
  	}
  	
	public void setProductId(int productId) {
	    this.productId = productId;
	}
	
	//色彩マスタidセット・取得
  	public int getColorId() {
  		return colorId;
  	}
  	
	public void setColorId(int colorId) {
	    this.colorId = colorId;
	}

}
