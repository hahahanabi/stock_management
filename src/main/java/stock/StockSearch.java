package stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import util.StockUtils;

/**
 * Servlet implementation class StockSearch
 */
@WebServlet("/StockSearch")
public class StockSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockSearch() {
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
            
            //検索処理
            StockDataBeans sdb = new StockDataBeans();
            String productId = request.getParameter("productId");
            String colorId = request.getParameter("colorId");
            String capacityId = request.getParameter("capacityId");
            String categoryId = request.getParameter("categoryId");
            String makerId = request.getParameter("makerId");
            if (!productId.equals("")) {
            	sdb.setProductId(productId);
            }
            if (!capacityId.equals("")) {
            	sdb.setCapacityId(capacityId);
            }
            if (!categoryId.equals("")) {
            	sdb.setCategoryId(categoryId);
            }
            if (!makerId.equals("")) {
            	sdb.setMakerId(makerId);
            }
            if (!colorId.equals("")) {
            	 sdb.setColorId(colorId);
            }
            if (!request.getParameter("modelName").equals("")) {
            	sdb.setModelName(request.getParameter("modelName"));
            }
            if (!request.getParameter("stockQuantity").equals("")) {
            sdb.setStockQuantity(request.getParameter("stockQuantity"));
            }
            System.out.print("検索時リクエストproductId" + productId);
            System.out.print("検索時sdb.getProductid" + sdb.getProductId());
            List<StockDTO> stockList = StockDAO.getInstance().search(sdb);
            //画面で必要な在庫データをジャバビーンズにセット
            List<StockDataBeans> resultSdbList = new ArrayList<>();
            for (StockDTO stock : stockList) {
            	StockDataBeans resultSdb = new StockDataBeans();
            	resultSdb.SDTOTOSDBMapping(stock);
            	resultSdbList.add(resultSdb);	
            }
            
            request.setAttribute("resultSdbList", resultSdbList);
            request.setAttribute("searchFlag", Integer.parseInt(request.getParameter("searchFlag")));
            //登録・検索画面にて初期表示セット
            StockUtils.setStockInfo(session, request);

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
