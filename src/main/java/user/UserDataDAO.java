package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import base.DBManager;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author enomoto
 */
public class UserDataDAO {
	
	  //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * ユーザーデータの登録処理を行う。現在時刻は挿入直前に生成
     * @param udd 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insert(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO users(user_name, user_email, user_password, organization_id, created_at, updated_at, created_user, updated_user) VALUES(?,?,?,?,?,?,?,?)");
            st.setString(1, udd.getUserName());
            st.setString(2, udd.getUserEmail());
            st.setString(3, udd.getUserPassword());
            st.setInt(4, udd.getOrganizationId());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.setString(7, udd.getUserName());
            st.setString(8, udd.getUserName());
            st.executeUpdate();
            System.out.println("user insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /**
     * データの検索処理を行う。
     * @param udb 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public UserDataDTO search(UserDataBeans udb) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM users WHERE user_email=? AND user_password=?");
            st.setString(1, udb.getUserEmail());
            st.setString(2, udb.getUserPassword());
          
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
	            UserDataDTO resultUdd = new UserDataDTO();
	            resultUdd.setUserId(rs.getInt(1));
	            resultUdd.setUserName(rs.getString(2));
	            resultUdd.setUserEmail(rs.getString(3));
	            resultUdd.setOrganizationId(rs.getInt(5));
	            
	            return resultUdd;
            } else {
            	return null;
            }
           
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

}
