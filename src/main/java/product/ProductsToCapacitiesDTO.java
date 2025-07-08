package product;

public class ProductsToCapacitiesDTO {
	
	private int productToCapacityId;
	private int productId;
	private int capacityId;
	
	//容量中間テーブルidセット・取得
	public int getProductToCapacityId() {
		return productToCapacityId;
	}
	
    public void setProductToCapacityId(int productToCapacityId) {
        this.productToCapacityId = productToCapacityId;
    }
    
    //製品idセット・取得
  	public int getProductId() {
  		return productId;
  	}
  	
	public void setProductId(int productId) {
	    this.productId = productId;
	}
	
	//容量マスタidセット・取得
  	public int getCapacityId() {
  		return capacityId;
  	}
  	
	public void setCapacityId(int capacityId) {
	    this.capacityId = capacityId;
	}

}
