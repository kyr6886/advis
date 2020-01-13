package kr.dis.std.category.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

import kr.dis.std.advis.dao.SbjRptOcrDto;
import kr.dis.std.advis.dao.UniOcrDto;
import kr.dis.std.category.viewmodel.CategoryViewModel;

@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDao implements ICategoryDao {
	
	@Override
	public int insertCategory(CategoryDTO paramBean) {
		return update("disCategory.insert", paramBean);
	}

	@Override
	public List<CategoryDTO> listCategory(CategoryDTO paramBean) {
		return selectList("disCategory.selectCtg",paramBean);
	}

	@Override
	public int sortUpdate(CategoryDTO paramBean) {
		return update("disCategory.sortUpdate",paramBean);
	}

	@Override
	public List<CategoryDTO> listDiscode(CategoryDTO paramBean) {
		return selectList("disCategory.disCodeList",paramBean);
	}

	@Override
	public int updateCategory(CategoryDTO paramBean) {
		return update("disCategory.update",paramBean);
	}

	@Override
	public int deleteCategory(CategoryDTO paramBean) {
		return update("disCategory.delete", paramBean);
	}

	@Override
	public List<CategoryDTO> createCategory(CategoryDTO paramBean) {
		return selectList("disCategory.createSelectCode",paramBean);
	}
	
	@Override
	public List<CategoryDTO> listCtgId(CategoryDTO paramBean) {
		return selectList("disCategory.searchCtgIdList",paramBean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbOcrFileDTO> listTbOcrFile(TbOcrFileDTO dto) {
		return selectList("disCategory.listTbOcrFile",dto);
	}

	@Override
	public int countTbOcrFile(TbOcrFileDTO dto) {
		return (Integer)selectOne("disCategory.countTbOcrFile", dto);
	}

	@Override
	public int insertTbOcrFile(TbOcrFileDTO dto) {
		return update("disCategory.insertTbOcrFile", dto);
	}

	@Override
	public int deleteTbOcrFile(TbOcrFileDTO dto) {
		return update("disCategory.deleteTbOcrFile", dto);
	}

	@Override
	public int createOcrUni(UniOcrDto paramBean) {

		return update("ocrPrg.create",paramBean);
	}

	@Override
	public UniOcrDto maxOcrUniInfo(HashMap<String, Object> paramMap) {

		return (UniOcrDto)selectOne("ocrPrg.maxOcrUniInfo",paramMap);
	}

	@Override
	public TbOcrFileDTO detailOcrFile(HashMap<String, Object> paramMap) {
		
		return (TbOcrFileDTO)selectOne("disCategory.detailTbOcrFile",paramMap);
	}

	@Override
	public List<SbjRptOcrDto> detailSbjRptOcr(HashMap<String, Object> paramMap) {
		
		return selectList("ocrPrg.detail",paramMap);
	}
	
	
}