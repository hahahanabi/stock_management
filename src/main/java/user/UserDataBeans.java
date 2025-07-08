package user;

import java.util.ArrayList;

public class UserDataBeans {
	private String userName;
    private String userEmail;
    private String userPassword;
    private int organizationId;
    private String organizationName;
    private int organizationStoreId;
    private String organizationStoreName;
    
    public UserDataBeans(){
        this.userName = "";
        this.userEmail = "";
        this.userPassword = "";
        this.organizationId = 0;
        this.organizationName = "";
        this.organizationStoreId = 0;
        this.organizationStoreName = "";
    }
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        //空文字(未入力)の場合空文字をセット
        if(userName.trim().length()==0){
            this.userName = "";
        }else{
            this.userName =userName;
        }
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        //空文字(未入力)の場合空文字をセット
        if(userEmail.trim().length()==0){
            this.userEmail = "";
        }else{
            this.userEmail = userEmail;
        }
    }
    
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        //空文字(未入力)の場合空文字をセット
        if(userPassword.trim().length()==0){
            this.userPassword = "";
        }else{
            this.userPassword = userPassword;
        }
    }
    
    public int getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(String organizationId) {
        //空文字(未入力)の場合0をセット
        if(organizationId.equals("")){
            this.organizationId = 0;
        }else{
            this.organizationId = Integer.parseInt(organizationId);
        }
    }
    
    public String getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(String organizationName) {
    	  this.organizationName = organizationName;
    }
    
    public int getOrganizationStoreId() {
        return organizationStoreId;
    }
    public void setOrganizationStoreId(String organizationStoreId) {
    	  //空文字(未入力)の場合0をセット
          if(organizationStoreId.equals("")){
              this.organizationStoreId = 0;
          }else{
              this.organizationStoreId = Integer.parseInt(organizationStoreId);
          }
    }
    
    public String getOrganizationStoreName() {
        return organizationStoreName;
    }
    public void setOrganizationStoreName(String organizationStoreName) {
    	  this.organizationStoreName = organizationStoreName;
    }
    
    
    //ユーザー登録時の未入力データがあるか
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.userName.equals("")){
            chkList.add("userName");
        }
        if(this.userEmail.equals("")){
            chkList.add("userEmail");
        }
        if(this.userPassword.equals("")){
            chkList.add("userPassword");
        }
        if(this.organizationId == 0){
            chkList.add("organizationId");
        }
        if(this.organizationName.equals("")){
            chkList.add("organizationName");
        }
        
        return chkList;
    }
    
    //DB操作用のジャバビーンズにセット
    public void UD2DTOMapping(UserDataDTO udd){
        udd.setUserName(this.userName);
        udd.setUserEmail(this.userEmail);
        udd.setUserPassword(this.userPassword);
        if (this.organizationStoreId != 0) {
        	udd.setOrganizationId(this.organizationStoreId);
        } else {
        	udd.setOrganizationId(this.organizationId);
        }
    }
    
    //ログイン時の未入力データがあるか
    public ArrayList<String> chkLoginPropaties(){
    	ArrayList<String> chkList = new ArrayList<String>();
    	 if(this.userEmail.equals("")){
             chkList.add("userEmail");
         }
         if(this.userPassword.equals("")){
             chkList.add("userPassword");
         }
         
         return chkList;
    }
}
