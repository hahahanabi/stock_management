package util;

import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import code_masters.CodeMastersDAO;
import code_masters.CodeMastersDTO;
import organization.OrganizationDAO;
import organization.OrganizationDTO;
import product.CapacityDTO;
import product.ColorDTO;
import product.ProductCategoriesDTO;
import product.ProductDAO;
import product.ProductDTO;
import product.ProductMakersDTO;
import product.ProductsToCapacitiesDTO;
import product.ProductsToColorsDTO;
import user.UserDataBeans;
import user.UserDataDAO;
import user.UserDataDTO;


/**
 * 在庫周りの共通処理記述
 * @author enomoto
 */
public class StockUtils {
	
	/**
     * 在庫登録時などに使う在庫関連の初期値出力処理を行う
     * @param session セッション
     * @param request Servletに来ているリクエスト
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
	public static void setStockInfo (HttpSession session, HttpServletRequest request) throws SQLException {
		
		//登録確認画面からの戻りかどうか
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UserDataBeans udb = new UserDataBeans();
        System.out.print("画面からのメアど" + email);
        udb.setUserEmail(email);
        udb.setUserPassword(password);
        UserDataDTO udd = UserDataDAO.getInstance().search(udb);
        
        //ユーザーが見つかった場合、ユーザー情報をユーザーのジャバビーンズに格納
        if (udd != null) {
        	udb.setUserName(udd.getUserName());
        	udb.setOrganizationId(String.valueOf(udd.getOrganizationId()));
        	
        	//組織データ検索、組織名をユーザーデータのジャバビーンズに格納
        	OrganizationDTO od = OrganizationDAO.getInstance().searchByOrganizationId(udb.getOrganizationId());
        	int organizationTypeCodeId = od.getOrganizationTypeCodeId();
        	//凡庸マスタテーブルから対象のcode名取得
        	List<CodeMastersDTO> organizationTypeCodes = CodeMastersDAO.getInstance().getSearchCodeByCodeType("organization_type_code");
        	String organizationTypeCode = null;
        	for(CodeMastersDTO typeCode : organizationTypeCodes) {
        		if (organizationTypeCodeId == typeCode.getCodeMasterId()) {
        			organizationTypeCode = typeCode.getCode();
        			break;
        		}
        	}
        	
        	if (organizationTypeCode.equals("department") || organizationTypeCode.equals("head_office")) {
        		//部or本社の場合はそのまま部署のところにセット
        		udb.setOrganizationId(String.valueOf(od.getOrganizationId()));
        		udb.setOrganizationName(od.getOrganizationName());
        	} else if (organizationTypeCode.equals("store")) {
        		//storeの時は１つ上位の部署を取得、それぞれユーザーデータのジャバビーンズにセット
        		udb.setOrganizationStoreId(String.valueOf(od.getOrganizationId()));
        		udb.setOrganizationStoreName(od.getOrganizationName());
        		int parentOrganizationId = od.getOrganizationParentId();
        		OrganizationDTO parentOd = OrganizationDAO.getInstance().searchByOrganizationId(parentOrganizationId);
        		udb.setOrganizationId(String.valueOf(parentOd.getOrganizationId()));
        		udb.setOrganizationName(String.valueOf(parentOd.getOrganizationName()));
        	}
        	
        	//登録画面に商品をセレクトで、オートコンプリートで選択できるようにデータ取得
        	List<ProductDTO> allProducts = ProductDAO.getInstance().getAllProducts();
        	List<ProductsToCapacitiesDTO> allProductsToCapacities = ProductDAO.getInstance().getAllProductsToCapacities();
        	List<CapacityDTO> allCapacities = ProductDAO.getInstance().getAllCapacities();
        	List<ProductCategoriesDTO> allProductCategories = ProductDAO.getInstance().getAllProductCategories();
        	List<ProductMakersDTO> allProductmakers = ProductDAO.getInstance().getAllProductMakers();
        	List<ProductsToColorsDTO> allProductsToColors = ProductDAO.getInstance().getAllProductsToColors();
        	List<ColorDTO> allColors = ProductDAO.getInstance().getAllColors();
        	request.setAttribute("allProducts", allProducts);
        	request.setAttribute("allProductsToCapacities", allProductsToCapacities);
        	request.setAttribute("allCapacities", allCapacities);
        	request.setAttribute("allProductCategories", allProductCategories);
        	request.setAttribute("allProductmakers", allProductmakers);
        	request.setAttribute("allProductsToColors", allProductsToColors);
        	request.setAttribute("allColors", allColors);
        }
        
        request.setAttribute("udd", udd);
        session.setAttribute("udb", udb);
	}

}
