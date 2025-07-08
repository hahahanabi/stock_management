package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;


public class ProductDAO {
	
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}
	
	/**
     * idで製品データの検索処理を１つ行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public ProductDTO getSearchProductById(int productId) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM products WHERE id = ?";
            
            st = con.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
        
            rs.next();
            	ProductDTO resultPd = new ProductDTO();
            	resultPd.setProductId(rs.getInt(1));
            	resultPd.setProductName(rs.getString(2));
            	resultPd.setProductCode(rs.getString(3));
            	resultPd.setModelName(rs.getString(4));
            	resultPd.setProductCategoryId(rs.getInt(5));
            	resultPd.setProductMakerId(rs.getInt(6));
            
            
            System.out.println("getSearchProductById completed");

            return resultPd;
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
     * 対象製品全部のデータの検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<ProductDTO> getAllProducts() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM products";
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            List<ProductDTO> resultPdList = new ArrayList<>(); 
            while (rs.next()) {
            	ProductDTO resultPd = new ProductDTO();
            	resultPd.setProductId(rs.getInt(1));
            	resultPd.setProductName(rs.getString(2));
            	resultPd.setProductCode(rs.getString(3));
            	resultPd.setModelName(rs.getString(4));
            	resultPd.setProductCategoryId(rs.getInt(5));
            	resultPd.setProductMakerId(rs.getInt(6));
            	resultPdList.add(resultPd);
            }
            
            System.out.println("getAllProducts completed");

            return resultPdList;
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
     * 対象製品全部の容量中間テーブルデータの検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<ProductsToCapacitiesDTO> getAllProductsToCapacities() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM products_to_capacities";
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            List<ProductsToCapacitiesDTO> resultPtcDTOList = new ArrayList<>(); 
            while (rs.next()) {
            	ProductsToCapacitiesDTO resultPtcDTO = new ProductsToCapacitiesDTO();
            	resultPtcDTO.setProductToCapacityId(rs.getInt(1));
            	resultPtcDTO.setProductId(rs.getInt(2));
            	resultPtcDTO.setCapacityId(rs.getInt(3));
            	resultPtcDTOList.add(resultPtcDTO);
            }
            
            System.out.println("getAllProducts completed");

            return resultPtcDTOList;
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
     * 容量データ全ての検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<CapacityDTO> getAllCapacities() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM capacities";
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            List<CapacityDTO> resultCdList = new ArrayList<>(); 
            while (rs.next()) {
            	CapacityDTO resultCd = new CapacityDTO();
            	resultCd.setCapacityId(rs.getInt(1));
            	resultCd.setCapacity(rs.getInt(2));
            	resultCd.setCapacityType(rs.getString(3));
            	resultCd.setRam(rs.getInt(4));
            	resultCdList.add(resultCd);
            }
            
            System.out.println("getAllProducts completed");

            return resultCdList;
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
     * カテゴリーデータ全ての検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<ProductCategoriesDTO > getAllProductCategories() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM product_categories";
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            List<ProductCategoriesDTO > resultOroCatDTOList = new ArrayList<>(); 
            while (rs.next()) {
            	ProductCategoriesDTO resultProCatDTO = new ProductCategoriesDTO ();
            	resultProCatDTO.setProductCategoryId(rs.getInt(1));
            	resultProCatDTO.setCategoryName(rs.getString(2));
            	resultProCatDTO.setCategoryTypeCode(rs.getString(3));
            	resultOroCatDTOList.add(resultProCatDTO);
            }
            
            System.out.println("getAllProducts completed");

            return resultOroCatDTOList;
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
     * 製品メーカーデータ全ての検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<ProductMakersDTO> getAllProductMakers() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM product_makers";
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            List<ProductMakersDTO> resultProMakDTOList = new ArrayList<>(); 
            while (rs.next()) {
            	ProductMakersDTO resultProMakDTO = new ProductMakersDTO();
            	resultProMakDTO.setProductMakerId(rs.getInt(1));
            	resultProMakDTO.setMakerName(rs.getString(2));
            	resultProMakDTO.setMakerTypeCode(rs.getString(3));
            	resultProMakDTOList.add(resultProMakDTO);
            }
            
            System.out.println("getAllProducts completed");

            return resultProMakDTOList;
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
     * 対象製品全部の色彩中間テーブルデータの検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<ProductsToColorsDTO> getAllProductsToColors() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM products_to_colors";
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            List<ProductsToColorsDTO> resultPtcDTOList = new ArrayList<>(); 
            while (rs.next()) {
            	ProductsToColorsDTO resultPtcDTO = new ProductsToColorsDTO();
            	resultPtcDTO.setProductToColorId(rs.getInt(1));
            	resultPtcDTO.setProductId(rs.getInt(2));
            	resultPtcDTO.setColorId(rs.getInt(3));
            	resultPtcDTOList.add(resultPtcDTO);
            }
            
            System.out.println("getAllProducts completed");

            return resultPtcDTOList;
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
     * 色彩マスタデータ全ての検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<ColorDTO> getAllColors() throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM colors";
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            List<ColorDTO> resultCdList = new ArrayList<>(); 
            while (rs.next()) {
            	ColorDTO resultCd = new ColorDTO();
            	resultCd.setColorId(rs.getInt(1));
            	resultCd.setColorName(rs.getString(2));
            	resultCd.setColorCode(rs.getString(3));
            	resultCdList.add(resultCd);
            }
            
            System.out.println("getAllProducts completed");

            return resultCdList;
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
