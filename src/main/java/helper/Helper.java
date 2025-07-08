package helper;

import java.util.ArrayList;

public class Helper {
	//トップへのリンクを定数として設定
	private final String homeURL = "index.jsp";
	private final String loginURL = "login.jsp";
	
	 public static Helper getInstance(){
	        return new Helper();
	 }
	//トップへのリンクを返却
    public String home(){
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }
    
    //ログイン画面へのリンクを返却
    public String login(){
        return "<a href=\""+loginURL+"\">ログイン画面へ戻る</a>";
    }
    
    
    /**
     * 入力されたユーザーデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が
     * 未入力なのかのhtml文を返却する
     * @param chkList　UserDataBeansで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */
    public String userDataChkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("userName")){
                    output += "名前";
                }
                if(val.equals("userEmail")){
                    output +="メールアドレス";
                }
                if(val.equals("userPassword")){
                    output +="パスワード";
                }
                if(val.equals("organizationId")){
                    output +="所属";
                }
                if(val.equals("organizationName")){
                    output +="所属名";
                }
          
                output +="が未入力です<br>";
            }
        return output;
    }
    
    /**
     * 入力されたログインデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が
     * 未入力なのかのhtml文を返却する
     * @param chkList　UserDataBeansで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */
    public String loginDataChkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("userEmail")){
                    output +="メールアドレス";
                }
                if(val.equals("userPassword")){
                    output +="パスワード";
                }
                
          
                output +="が未入力です<br>";
            }
        return output;
    }
}
