package stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * 在庫データの検索処理を行う。
     * @param sdb 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public List<StockDTO> search(StockDataBeans sdb) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        
        int productId = sdb.getProductId();
        int capacityId = sdb.getCapacityId();
        int categoryId = sdb.getCategoryId();
        int makerId = sdb.getMakerId();
        int colorId = sdb.getColorId();
        String modelName = sdb.getModelName();
        int stockQuantity = sdb.getStockQuantity();
         System.out.print("検索時SQLproductId" + productId);
         System.out.print("検索時SQLcapacityId" + capacityId);
         System.out.print("検索時SQLcategoryId" +categoryId);
         System.out.print("検索時SQLmakerId" + makerId);
        try{
            con = DBManager.getConnection();
            //検索時のリクエストの有無で条件とリクエストセット
            String sql = "SELECT * FROM stocks";
            sql += " LEFT JOIN products pro ON stocks.product_id = pro.id";
            sql += " WHERE 1=1";
            List<Object> searchParams = new ArrayList<>();
            if (productId != 0) {
            	sql += " AND product_id=?";
            	searchParams.add(productId);
            }
            if (capacityId != 0) {
            	sql += " AND capacity_id=?";
            	searchParams.add(capacityId);
            }
            if (categoryId != 0) {
            	sql += " AND pro.product_category_id=?";
            	searchParams.add(categoryId);
            }
            if (makerId != 0) {
            	sql += " AND pro.product_maker_id=?";
            	searchParams.add(makerId);
            }
            if (colorId != 0) {
            	sql += " AND color_id=?";
            	searchParams.add(colorId);
            }
            if (!modelName.equals("")) {
            	sql += " AND pro.model_name LIKE ?";
            	searchParams.add("%" + modelName + "%");
            }
            if (stockQuantity != 0) {
            	sql += " AND stock_quantity >= ?";
            	searchParams.add(stockQuantity);
            }
            System.out.print("sql中身" + sql);
            System.out.print("searchParams中身" + searchParams);
            st =  con.prepareStatement(sql);
            //検索条件＋リクエスト　セット
            for (int i = 0; i < searchParams.size(); i++) {
            	st.setObject(i + 1, searchParams.get(i));
            }
          
            ResultSet rs = st.executeQuery();
            List<StockDTO> stockList = new ArrayList<>();
            while (rs.next()) {
	            StockDTO resultSd = new StockDTO();
	            resultSd.setStockId(rs.getInt("id")); // stocks.id
	            resultSd.setStockQuantity(rs.getInt("stock_quantity"));
	            resultSd.setPurchaseUnitPrice(rs.getInt("purchase_unit_price"));
	            resultSd.setSaleUnitPrice(rs.getInt("sale_unit_price"));
	            resultSd.setProductId(rs.getInt("product_id"));
	            resultSd.setCapacityId(rs.getInt("capacity_id"));
	            resultSd.setColorId(rs.getInt("color_id"));
//	            resultSd.setStockId(rs.getInt(1));
//	            resultSd.setStockQuantity(rs.getInt(2));
//	            resultSd.setPurchaseUnitPrice(rs.getInt(3));
//	            resultSd.setSaleUnitPrice(rs.getInt(4));
//	            resultSd.setProductId(rs.getInt(5));
//	            resultSd.setCapacityId(rs.getInt(6));
//	            resultSd.setColorId(rs.getInt(7));
	            stockList.add(resultSd);
            } 
            for (StockDTO stock : stockList) {
            	System.out.print("検索結果" + stock.getStockId());
            	System.out.print("検索結果容量" + stock.getCapacityId());
            }
            return stockList;
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
