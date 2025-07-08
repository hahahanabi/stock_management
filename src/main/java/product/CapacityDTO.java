package product;

public class CapacityDTO {
	
	private int capacityId;
	private int capacity;
	private String capacityType;
	private int ram;

	
	//容量マスタテーブルidセット・取得
	public int getCapacityId() {
		return capacityId;
	}
	
    public void setCapacityId(int capacityId) {
        this.capacityId = capacityId;
    }
    
    //容量セット・取得
  	public int getCapacity() {
  		return capacity;
  	}
  	
	public void setCapacity(int capacity) {
	    this.capacity = capacity;
	}
	
	 //容量タイプセット・取得
  	public String getCapacityType() {
  		return capacityType;
  	}
  	
	public void setCapacityType(String capacityType) {
	    this.capacityType = capacityType;
	}
	
	//ramセット・取得
  	public int getRam() {
  		return ram;
  	}
  	
	public void setRam(int ram) {
	    this.ram = ram;
	}
}
