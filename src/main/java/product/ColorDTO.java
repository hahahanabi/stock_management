package product;

public class ColorDTO {
	
	private int colorId;
	private String colorName;
	private String colorCode;
	
	
	//色彩マスタテーブルidセット・取得
	public int getColorId() {
		return colorId;
	}
	
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
    
    //色名セット・取得
  	public String getColorName() {
  		return colorName;
  	}
  	
	public void setColorName(String colorName) {
	    this.colorName = colorName;
	}
	
	 //色コードセット・取得
  	public String getColorCode() {
  		return colorCode;
  	}
  	
	public void setColorCode(String colorCode) {
	    this.colorCode = colorCode;
	}
	
}
