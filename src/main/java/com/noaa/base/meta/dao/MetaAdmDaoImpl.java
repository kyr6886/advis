package com.noaa.base.meta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("metaAdmDao")
public class MetaAdmDaoImpl extends BaseDao implements IMetaAdmDao {
	
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public int createTable() {		
		return update("metaAdm.createTable",null);
	}

	@Override
	public int createIndex() {		
		return update("metaAdm.createIndex",null);
	}

	@Override
	public int createData(List<MetaAdmDTO> paramList) {
		Connection con = null;
        PreparedStatement pstmt = null ;
        String sql = "insert into tc_adm (adm_code,adm_sido,adm_sigungu,adm_dong,code_create_day,code_delete_day) values (?,?,?,?,?,?)" ;
        try{
            con = jdbcTemplate.getDataSource().getConnection();
            pstmt = con.prepareStatement(sql) ;
            con.setAutoCommit(false);
             
            for(int i=0; i < paramList.size(); i++){
                pstmt.setString(1, paramList.get(i).getAdm_code());
                pstmt.setString(2, paramList.get(i).getAdm_sido());
                pstmt.setString(3, paramList.get(i).getAdm_sigungu());
                pstmt.setString(4, paramList.get(i).getAdm_dong());
                pstmt.setString(5, paramList.get(i).getCode_create_day());
                pstmt.setString(6, paramList.get(i).getCode_delete_day());
                pstmt.addBatch();// addBatch에 담기                
                pstmt.clearParameters() ;// 파라미터 Clear
                // OutOfMemory를 고려하여 만건 단위로 커밋
                if( (i % 1500) == 0){
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                    con.commit() ;
                }
            }
            pstmt.executeBatch() ;
            con.commit() ;
        }catch(Exception e){
            e.printStackTrace();
            try {
                con.rollback() ;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            if (pstmt != null) try {pstmt.close();pstmt = null;} catch(SQLException ex){}
            if (con != null) try {con.close();con = null;} catch(SQLException ex){}
        }
		return 0;
	}
	
	@Override
	public MetaAdmDTO detail(HashMap<String, Object> paramMap){
		return (MetaAdmDTO)selectOne("metaAdm.detail",paramMap);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MetaAdmDTO> listSido() {		
		return selectList("metaAdm.listBySido",null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaAdmDTO> listGungu(HashMap<String, Object> paramMap) {
		return selectList("metaAdm.listByGungu",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaAdmDTO> listDong(HashMap<String, Object> paramMap) {
		return selectList("metaAdm.listByDong",paramMap);
	}
	
}
