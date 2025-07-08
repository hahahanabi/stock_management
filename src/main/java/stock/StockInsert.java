package stock;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
 * Servlet implementation class StockInsert
 */
@WebServlet("/StockInsert")
public class StockInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスタート
        HttpSession session = request.getSession();
        try{
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
         
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
            	String organizationTypeCode = od.getOrganizationTypeCode();
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
            request.getRequestDispatcher("stock/stockInsert.jsp").forward(request, response);
        }catch(Exception e){
        	e.printStackTrace();
			request.setAttribute("error", e.toString());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
