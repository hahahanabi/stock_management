package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;

public class ProductCategoriesDAO {

	//インスタンスオブジェクトを返却させてコードの簡略化
		public static ProductCategoriesDAO getInstance() {
			return new ProductCategoriesDAO();
		}
		
		/**
	     * idで製品カテゴリーデータの検索処理を１つ行う。
	     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
	     * @return 検索結果
	     */
	    public ProductCategoriesDTO getSearchProductCategoryById(int categoryId) throws SQLException{
	        Connection con = null;
	        PreparedStatement st = null;
	        try{
	            con = DBManager.getConnection();
	            
	            String sql = "SELECT * FROM product_categories WHERE id = ?";
	            
	            st = con.prepareStatement(sql);
	            st.setInt(1, categoryId);
	            ResultSet rs = st.executeQuery();
	        
	            rs.next();
	            ProductCategoriesDTO resultProCaDTO = new ProductCategoriesDTO();
	            	resultProCaDTO.setProductCategoryId(rs.getInt(1));
	            	resultProCaDTO.setCategoryName(rs.getString(2));
	            	resultProCaDTO.setCategoryTypeCode(rs.getString(3));    
	            
	            System.out.println("getSearchProductCategoryById completed");

	            return resultProCaDTO;
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
