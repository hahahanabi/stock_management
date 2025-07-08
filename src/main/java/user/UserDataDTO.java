package user;

import java.sql.Timestamp;

public class UserDataDTO {
	private int userId;
	private String userName;
    private String userEmail;
    private String userPassword;
    private int organizationId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String createdUser;
    private String updatedUser;
    private String deletedUser;
    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String getUserEmail(){
        return userEmail;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    
    public String getUserPassword(){
        return userPassword;
    }
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    
    public int getOrganizationId(){
        return organizationId;
    }
    public void setOrganizationId(int organizationId){
        this.organizationId = organizationId;
    }

    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt){
        this.updatedAt = updatedAt;
    }
    
    public Timestamp getDeletedAt(){
        return deletedAt;
    }
    public void setDeletedAt(Timestamp deletedAt){
        this.deletedAt = deletedAt;
    }
    
    public String getCreatedUser(){
        return createdUser;
    }
    public void setCreatedUser(String createdUser){
        this.createdUser = createdUser;
    }
    
    public String getUpdatedUser(){
        return updatedUser;
    }
    public void setUpdatedUser(String updatedUser){
        this.updatedUser = updatedUser;
    }
    
    public String getDeletedUser(){
        return deletedUser;
    }
    public void setDeletedUser(String deletedUser){
        this.deletedUser = deletedUser;
    }
    
   
    
}
