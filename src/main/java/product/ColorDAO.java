package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;

public class ColorDAO {
	
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static ColorDAO getInstance() {
		return new ColorDAO();
	}
	
	/**
     * idで色彩データの検索処理を１つ行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public ColorDTO getSearchColorById(int colorId) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM colors WHERE id = ?";
            
            st = con.prepareStatement(sql);
            st.setInt(1, colorId);
            ResultSet rs = st.executeQuery();
        
            rs.next();
            ColorDTO resultColDTO = new ColorDTO();
            	resultColDTO.setColorId(rs.getInt(1));
            	resultColDTO.setColorName(rs.getString(2));
            	resultColDTO.setColorCode(rs.getString(3));
            
            System.out.println("getSearchColorById completed");

            return resultColDTO;
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
