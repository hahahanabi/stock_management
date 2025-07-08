package stock;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import user.UserDataBeans;

/**
 * Servlet implementation class StockInsertResult
 */
@WebServlet("/StockInsertResult")
public class StockInsertResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockInsertResult() {
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
            UserDataBeans udb = (UserDataBeans)session.getAttribute("udb");
            sdb.setStockQuantity(request.getParameter("stockQuantity"));
            sdb.setPurchaseUnitPrice(request.getParameter("purchaseUnitPrice"));
            sdb.setSaleUnitPrice(request.getParameter("saleUnitPrice"));
            sdb.setOtherInfo(request.getParameter("otherInfo"));
            sdb.setProductId(request.getParameter("productId"));
            sdb.setCapacityId(request.getParameter("capacityId"));
            sdb.setColorId(request.getParameter("colorId"));
            sdb.setUserName(udb.getUserName());
            //DB操作用ジャバビーンズにセット
            StockDTO sd = new StockDTO();
            sdb.SD2DTOMapping(sd);
            StockDAO.getInstance().insert(sd);
            
            
            request.getRequestDispatcher("stock/stockInsertResult.jsp").forward(request, response);
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
