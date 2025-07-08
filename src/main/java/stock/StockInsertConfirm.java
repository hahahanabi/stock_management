package stock;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import product.CapacityDAO;
import product.CapacityDTO;
import product.ColorDAO;
import product.ColorDTO;
import product.ProductCategoriesDAO;
import product.ProductCategoriesDTO;
import product.ProductDAO;
import product.ProductDTO;
import product.ProductMakersDAO;
import product.ProductMakersDTO;

/**
 * Servlet implementation class StockInsertConfirm
 */
@WebServlet("/StockInsertConfirm")
public class StockInsertConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockInsertConfirm() {
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
            
            StockDataBeans sdb = new StockDataBeans();
            //画面からのリクエストセット
            sdb.setProductId(request.getParameter("productId"));
            sdb.setCapacityId(request.getParameter("capacityId"));
            sdb.setPurchaseUnitPrice(request.getParameter("purchaseUnitPrice"));
            sdb.setCategoryId(request.getParameter("categoryId"));
            sdb.setMakerId(request.getParameter("makerId"));
            sdb.setSaleUnitPrice(request.getParameter("SaleUnitPrice"));
            sdb.setColorId(request.getParameter("colorId"));
            sdb.setModelName(request.getParameter("modelName"));
            sdb.setStockQuantity(request.getParameter("stockQuantity"));
            sdb.setOtherInfo(request.getParameter("otherInfo"));
            
            
            //画面からのリクエストで表示用データ検索、セット
            ProductDTO pd = ProductDAO.getInstance().getSearchProductById(sdb.getProductId());
            sdb.setProductName(pd.getProductName());
            CapacityDTO cd = CapacityDAO.getInstance().getSearchCapacityById(sdb.getCapacityId());
            sdb.setCapacity(cd.getCapacity());
            sdb.setCapacityType(String.valueOf(cd.getCapacityType()));
            ProductCategoriesDTO proCatDTO = ProductCategoriesDAO.getInstance().getSearchProductCategoryById(sdb.getCategoryId());
            sdb.setCategoryName(proCatDTO.getCategoryName());
            ProductMakersDTO proMakDTO =  ProductMakersDAO.getInstance().getSearchProductMakerById(sdb.getMakerId());
            sdb.setMakerName(proMakDTO.getMakerName());
            ColorDTO colDTO = ColorDAO.getInstance().getSearchColorById(sdb.getColorId());
            sdb.setColorName(colDTO.getColorName());
            
            request.setAttribute("sdb", sdb);
            request.getRequestDispatcher("stock/stockInsertConfirm.jsp").forward(request, response);
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
