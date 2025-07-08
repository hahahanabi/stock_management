package organization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;

/**
 * 組織情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author enomoto
 */
public class OrganizationDAO {
	 //インスタンスオブジェクトを返却させてコードの簡略化
    public static OrganizationDAO getInstance(){
        return new OrganizationDAO();
    }
    
    
    /**
     * 対象部署全部のデータの検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<OrganizationDTO> getAllOrganizations() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM organizations";
            
            st = con.prepareStatement(sql);
            //st.setInt(1, ud.getUserID());
            
            ResultSet rs = st.executeQuery();
            List<OrganizationDTO> resultOdList = new ArrayList<>(); 
            while (rs.next()) {
            	OrganizationDTO resultOd = new OrganizationDTO();
            	resultOd.setOrganizationId(rs.getInt(1));
            	resultOd.setOrganizationName(rs.getString(2));
            	resultOd.setOrganizationParentId(rs.getInt(3));
            	resultOd.setOrganizationTypeCode(rs.getString(4));
            	
            	resultOdList.add(resultOd);
//            UserDataDTO resultUd = new UserDataDTO();
//            resultUd.setUserID(rs.getInt(1));
//            resultUd.setName(rs.getString(2));
//            resultUd.setBirthday(rs.getDate(3));
//            resultUd.setTell(rs.getString(4));
//            resultUd.setType(rs.getInt(5));
//            resultUd.setComment(rs.getString(6));
//            resultUd.setNewDate(rs.getTimestamp(7));
            }
            
            System.out.println("getAllOrganizations completed");

            return resultOdList;
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
     * 組織IDによる1件のデータの検索処理を行う。
     * @param organizationId 組織id
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public  OrganizationDTO searchByOrganizationId(int organizationId) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM organizations WHERE id = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, organizationId);
            
            ResultSet rs = st.executeQuery();
            rs.next();
            OrganizationDTO resultOd = new OrganizationDTO();
            resultOd.setOrganizationId(rs.getInt(1));
            resultOd.setOrganizationName(rs.getString(2));
            resultOd.setOrganizationParentId(rs.getInt(3));
            resultOd.setOrganizationTypeCode(rs.getString(4));
            
            System.out.println("searchByOrganizationId completed");

            return resultOd;
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
