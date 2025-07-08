package user;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import organization.OrganizationDAO;
import organization.OrganizationDTO;

/**
 * Servlet implementation class UserConfirm
 */
@WebServlet("/UserConfirm")
public class UserConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserConfirm() {
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
            System.out.print("店舗データリクエスト" + request.getParameter("storeSelect"));
            Logger.getLogger("Debug").info("デバッグ出力：storeSelect = " + request.getParameter("storeSelect"));

            //画面からきたデータをユーザー情報のジャバビーンズにセット
            UserDataBeans udb = new UserDataBeans();
            String storeSelectId = request.getParameter("storeSelect");
            udb.setUserName(request.getParameter("name"));
            
            //課or店舗の選択の有無
            if (storeSelectId == null) {
            	System.out.print("店舗なし");
            	udb.setOrganizationId(request.getParameter("organization"));
            } else {
            	System.out.print("店舗あり");
            	udb.setOrganizationId(request.getParameter("organization"));
            	udb.setOrganizationStoreId(request.getParameter("storeSelect"));
            }
            udb.setUserEmail(request.getParameter("email"));
            udb.setUserPassword(request.getParameter("password"));
            //組織名も画面に返したいので、検索して、ジャバビーンズにセット
            OrganizationDTO od = OrganizationDAO.getInstance().searchByOrganizationId(udb.getOrganizationId());
            udb.setOrganizationName(od.getOrganizationName());
            
            //セッションにユーザーデータ保存
            session.setAttribute("udb", udb);
            System.out.println("Session updated!!");
            
            request.getRequestDispatcher("/userConfirm.jsp").forward(request, response);
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
