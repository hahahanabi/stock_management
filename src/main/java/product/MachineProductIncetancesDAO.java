package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import base.DBManager;
import code_masters.CodeMastersDAO;
import code_masters.CodeMastersDTO;
import stock.StockDTO;

public class MachineProductIncetancesDAO {
	
	//インスタンスオブジェクトを返却させてコードの簡略化
    public static MachineProductIncetancesDAO getInstance(){
        return new MachineProductIncetancesDAO();
    }
    
    /**
     * 機械製品単体テーブルデータの登録処理を行う。現在時刻は挿入直前に生成
     * @param mpiDTO 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insert(MachineProductIncetancesDTO mpiDTO, StockDTO sd) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        Random random = new Random();
        //必要データ設定
        ProductDTO pd = ProductDAO.getInstance().getSearchProductById(sd.getProductId());
        ProductCategoriesDTO pcd = ProductCategoriesDAO.getInstance().getSearchProductCategoryById(pd.getProductCategoryId());
        CapacityDTO cd = CapacityDAO.getInstance().getSearchCapacityById(sd.getCapacityId());
        ColorDTO cod = ColorDAO.getInstance().getSearchColorById(sd.getColorId());
        List<CodeMastersDTO> codeList = CodeMastersDAO.getInstance().getSearchCodeByCodeType("stock_status_type_code");
        
        //商品コード作成用データ
        String categoryCodeFirstThree = pcd.getCategoryTypeCode().substring(0,3);
        String ProductCodeFirstThree = pd.getProductCode().substring(0,3);
        int capacity = cd.getCapacity();
        String colorCodeFirstThree = cod.getColorCode().substring(0,3);
        String productUnitCode = categoryCodeFirstThree + ProductCodeFirstThree + capacity + colorCodeFirstThree;
        
        //在庫ステータス取得
        int stockStatusTypeCodeId = 0;
        for (CodeMastersDTO codeData : codeList) {
        	//初回登録在庫（追加で登録したものでない）の場合は「在庫中」のステータスを登録
        	if (mpiDTO.getStockAddFlag() == 0 && codeData.getCode().equals("stocked")) {
        		stockStatusTypeCodeId = codeData.getCodeMasterId();
        		break;
        	}
        }
        
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO machine_product_incetances(product_id, product_unit_code, capacity_id, color_id, stock_id, stock_add_flag, stock_status_type_code_id, created_at, updated_at, created_user, updated_user) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        	for (int i = 0; i < sd.getStockQuantity(); i++) {
        		//商品コード用ランダムな数
        		int randomCode = random.nextInt(900) + 100;
        		
        		 st.setInt(1, mpiDTO.getProductId());
        		 st.setString(2, productUnitCode + randomCode);
        		 st.setInt(3, mpiDTO.getCapacityId());
        		 st.setInt(4, mpiDTO.getColorId());
        		 st.setInt(5, mpiDTO.getStockId());
        		 st.setInt(6, mpiDTO.getStockAddFlag());
        		 st.setInt(7, stockStatusTypeCodeId);
        		 st.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
        		 st.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
        		 st.setString(10, mpiDTO.getUserName());
        		 st.setString(11, mpiDTO.getUserName());
        		 
        		 st.executeUpdate();
        	}
            
            System.out.println("stock　incetances insert completed");
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
