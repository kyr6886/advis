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
@Repository("metaLawDao")
public class MetaLawDaoImpl extends BaseDao implements IMetaLawDao {
	
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public int createTable() {		
		return update("metaLaw.createTable",null);
	}

	@Override
	public int createIndex() {		
		return update("metaLaw.createIndex",null);
	}

	@Override
	public int createData(List<MetaLawDTO> paramList) {
		Connection con = null;
        PreparedStatement pstmt = null ;
        String sql = "insert into tc_law (law_code,law_sido,law_sigungu,law_dong,law_ri,code_create_day,code_delete_day) values (?,?,?,?,?,?,?)" ;
        try{
            con = jdbcTemplate.getDataSource().getConnection();
            pstmt = con.prepareStatement(sql) ;
             
            for(int i=0; i < paramList.size(); i++){
                pstmt.setString(1, paramList.get(i).getLaw_code());
                pstmt.setString(2, paramList.get(i).getLaw_sido());
                pstmt.setString(3, paramList.get(i).getLaw_sigungu());
                pstmt.setString(4, paramList.get(i).getLaw_dong());
                pstmt.setString(5, paramList.get(i).getLaw_ri());
                pstmt.setString(6, paramList.get(i).getCode_create_day());
                pstmt.setString(7, paramList.get(i).getCode_delete_day());
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
	public MetaLawDTO detail(HashMap<String, Object> paramMap){
		return (MetaLawDTO)selectOne("metaLaw.detail",paramMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MetaLawDTO> listSido() {		
		return selectList("metaLaw.listBySido",null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaLawDTO> listGungu(HashMap<String, Object> paramMap) {
		return selectList("metaLaw.listByGungu",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaLawDTO> listDong(HashMap<String, Object> paramMap) {
		return selectList("metaLaw.listByDong",paramMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MetaLawDTO> listRi(HashMap<String, Object> paramMap) {
		return selectList("metaLaw.listByRi",paramMap);
	}
}
