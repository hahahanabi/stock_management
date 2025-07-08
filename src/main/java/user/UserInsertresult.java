package user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserInsertresult
 */
@WebServlet("/UserInsertresult")
public class UserInsertresult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertresult() {
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

            UserDataBeans udb = (UserDataBeans)session.getAttribute("udb");
          //DTOオブジェクトにマッピング。ユーザーDB専用のパラメータに変換
            UserDataDTO userdata = new UserDataDTO();
            udb.UD2DTOMapping(userdata);
            
            //DBへデータの挿入
            UserDataDAO.getInstance().insert(userdata);

            //成功したのでセッションの値を削除
            session.invalidate();
            
            request.getRequestDispatcher("/userInsertResult.jsp").forward(request, response);
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
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
