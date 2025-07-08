package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;

public class ProductMakersDAO {
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static ProductMakersDAO getInstance() {
		return new ProductMakersDAO();
	}
	
	/**
     * idで製品メーカーデータの検索処理を１つ行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public ProductMakersDTO getSearchProductMakerById(int makerId) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM product_makers WHERE id = ?";
            
            st = con.prepareStatement(sql);
            st.setInt(1, makerId);
            ResultSet rs = st.executeQuery();
        
            rs.next();
            ProductMakersDTO resultMd = new ProductMakersDTO();
            	resultMd.setProductMakerId(rs.getInt(1));
            	resultMd.setMakerName(rs.getString(2));
            	resultMd.setMakerTypeCode(rs.getString(3));
            
            System.out.println("getSearchCapacityById completed");

            return resultMd;
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
