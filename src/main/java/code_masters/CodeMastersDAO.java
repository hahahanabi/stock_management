package code_masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;

public class CodeMastersDAO {
	
	//インスタンスオブジェクトを返却させてコードの簡略化
    public static CodeMastersDAO getInstance(){
        return new CodeMastersDAO();
    }

    /**
     * code_typeでcodeデータの検索処理を行う。
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public List<CodeMastersDTO> getSearchCodeByCodeType(String codeType) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM code_masters WHERE code_type = ?";
            
            st = con.prepareStatement(sql);
            st.setString(1, codeType);
            ResultSet rs = st.executeQuery();
        
            List <CodeMastersDTO> codeList = new ArrayList<>();
            while(rs.next()) {
            	CodeMastersDTO resultCodeDTO = new CodeMastersDTO();
            	resultCodeDTO.setCodeMasterId(rs.getInt(1));
            	resultCodeDTO.setCodeName(rs.getString(2));
            	resultCodeDTO.setCodeType(rs.getString(3));
            	resultCodeDTO.setCode(rs.getString(4));
            	
            	codeList.add(resultCodeDTO);
            }
            
            System.out.println("getSearchCodeByCodeType completed");

            return codeList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
}
