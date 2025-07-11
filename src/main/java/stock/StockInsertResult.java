package stock;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import product.MachineProductIncetancesDAO;
import product.MachineProductIncetancesDTO;
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
            sdb.setStockAddFlag(request.getParameter("stockAddFlag"));
            sdb.setUserName(udb.getUserName());
            //DB操作用ジャバビーンズにセット
            StockDTO sd = new StockDTO();
            sdb.SD2DTOMapping(sd);
            //同じ在庫が在庫テーブルにある場合、探してきて、そこの個数にプラス＋機械製品単体テーブルには追加フラグ立ててプラス、
            //ない場合は在庫新規追加＋機械製品単体テーブルに、追加フラグ0のまま追加
            //→在庫登録画面からは新規在庫登録。追加は検索して追加の画面からする。ようにする。＝画面からの単体データは追加フラグ0
            //stocks（在庫）テーブルに新規登録
            StockDAO.getInstance().insert(sd);
           
            //機械製品単体テーブルに在庫分登録
            //登録した在庫検索→在庫DTOに入れる→その中の在庫数分だけ回して単体テーブルに入れる
            MachineProductIncetancesDTO mpiDTO = new MachineProductIncetancesDTO();
            mpiDTO.setProductId(sd.getProductId());
            mpiDTO.setCapacityId(sd.getCapacityId());
            mpiDTO.setColorId(sd.getColorId());
            mpiDTO.setStockId(sd.getStockId());
            mpiDTO.setStockAddFlag(sdb.getStockAddFlag());
            mpiDTO.setUserName(sdb.getUserName());
            //在庫登録と同じユーザーで登録
            MachineProductIncetancesDAO.getInstance().insert(mpiDTO, sd);
            
           
            
            
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
