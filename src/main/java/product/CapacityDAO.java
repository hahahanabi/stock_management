package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;

public class CapacityDAO {
	
	//インスタンスオブジェクトを返却させてコードの簡略化
		public static CapacityDAO getInstance() {
			return new CapacityDAO();
		}
	
	
	/**
     * idで容量データの検索処理を１つ行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public CapacityDTO getSearchCapacityById(int capacityId) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM capacities WHERE id = ?";
            
            st = con.prepareStatement(sql);
            st.setInt(1, capacityId);
            ResultSet rs = st.executeQuery();
        
            rs.next();
            CapacityDTO resultCd = new CapacityDTO();
            	resultCd.setCapacityId(rs.getInt(1));
            	resultCd.setCapacity(rs.getInt(2));
            	resultCd.setCapacityType(rs.getString(3));
            	resultCd.setRam(rs.getInt(4));
            
            
            System.out.println("getSearchCapacityById completed");

            return resultCd;
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
