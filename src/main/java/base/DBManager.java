package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* DB接続
*/
public class DBManager {
	 public static Connection getConnection() throws SQLException{
    	 //public static Connection getConnection(){ 登録確認画面▶︎結果画面でエラー出たから一旦コメントアウト、変更
    	Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/stock_management","root","root");
            System.out.println("DBConnected!!");
            return con;
//        }catch(ClassNotFoundException e){
//            throw new IllegalMonitorStateException();
//        }catch(SQLException e){
//            throw new IllegalMonitorStateException();
//        }catch(Exception e){
//            throw new IllegalMonitorStateException();
//        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // ここでログ出力
            throw new SQLException("JDBCドライバが見つかりません", e);
        } catch (SQLException e) {
            e.printStackTrace(); // DB接続エラーのログ出力
            throw e;
        }
    }

}
