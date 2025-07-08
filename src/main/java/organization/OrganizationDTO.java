package organization;

/**
 * 組織情報を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * @version 1.00
 * @author enomoto
 */
public class OrganizationDTO {
	
	private int organizationId;
	private String organizationName;
	private int organizationParentId;
	private String organizationTypeCode;
	
	//組織idセット・取得
	public int getOrganizationId() {
		return organizationId;
	}
	
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
    
    //組織名セット・取得
    public String getOrganizationName() {
		return organizationName;
	}
	
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    
    //組織の親idセット・取得
    public int getOrganizationParentId() {
  		return organizationParentId;
  	}
  	
	public void setOrganizationParentId(int organizationParentId) {
      this.organizationParentId = organizationParentId;
	}
	
	 //組織タイプセット・取得
    public String getOrganizationTypeCode() {
  		return organizationTypeCode;
  	}
  	
	public void setOrganizationTypeCode(String organizationTypeCode) {
      this.organizationTypeCode = organizationTypeCode;
	}

}
