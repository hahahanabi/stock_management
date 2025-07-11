package stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import base.DBManager;

public class StockDAO {
	
	 //インスタンスオブジェクトを返却させてコードの簡略化
    public static StockDAO getInstance(){
        return new StockDAO();
    }
    
    /**
     * 在庫データの登録処理を行う。現在時刻は挿入直前に生成
     * @param sd 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insert(StockDTO sd) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null; 
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO stocks(stock_quantity, purchase_unit_price, sale_unit_price, other_info, product_id, capacity_id, color_id, created_at, updated_at, created_user, updated_user) VALUES(?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, sd.getStockQuantity());
            st.setInt(2, sd.getPurchaseUnitPrice());
            st.setInt(3, sd.getSaleUnitPrice());
            st.setString(4, sd.getOtherInfo());
            st.setInt(5, sd.getProductId());
            st.setInt(6, sd.getCapacityId());
            st.setInt(7, sd.getColorId());
            st.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            st.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            st.setString(10, sd.getUserName());
            st.setString(11, sd.getUserName());
           int insertedRow = st.executeUpdate();
            System.out.println("stock insert completed");
            //登録した在庫データのidをジャバビーンズにセット
           if (insertedRow > 0) {
        	   rs = st.getGeneratedKeys();
        	   if (rs.next()) {
        		   int stockId = rs.getInt(1);
        		   sd.setStockId(stockId);
        	   }
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
