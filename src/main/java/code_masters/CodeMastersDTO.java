package code_masters;

public class CodeMastersDTO {
	
	private int codeMasterId;
	private String codeName;
	private String codeType;
	private String code;
	
	//凡庸マスタテーブルidセット・取得
	public int getCodeMasterId() {
		return codeMasterId;
	}
	public void setCodeMasterId(int codeMasterId) {
        this.codeMasterId = codeMasterId;
    }
	
	//コードネームセット・取得
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
	
	//コードタイプセット・取得
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
	
	//コードセット・取得
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
        this.code = code;
    }

}
