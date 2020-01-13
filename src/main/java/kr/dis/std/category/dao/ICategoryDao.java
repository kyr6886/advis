package kr.dis.std.category.dao;

import java.util.HashMap;
import java.util.List;

import kr.dis.std.advis.dao.SbjRptOcrDto;
import kr.dis.std.advis.dao.UniOcrDto;

public interface ICategoryDao  {

	public int insertCategory(CategoryDTO paramBean);
	
	public List<CategoryDTO> listCategory(CategoryDTO paramBean);
	
	public int sortUpdate(CategoryDTO paramBean);

	public List<CategoryDTO> listDiscode(CategoryDTO paramBean);
	
	public int updateCategory(CategoryDTO paramBean);

	public int deleteCategory(CategoryDTO paramBean);

	public List<CategoryDTO> createCategory(CategoryDTO paramBean);

	public List<CategoryDTO> listCtgId(CategoryDTO paramBean);

	public List<TbOcrFileDTO> listTbOcrFile(TbOcrFileDTO dto);

	public int countTbOcrFile(TbOcrFileDTO dto);

	public int insertTbOcrFile(TbOcrFileDTO dto);

	public TbOcrFileDTO detailOcrFile(HashMap<String, Object> paramMap);
	public List<SbjRptOcrDto> detailSbjRptOcr(HashMap<String, Object> paramMap);
	public int deleteTbOcrFile(TbOcrFileDTO dto);
	public int createOcrUni(UniOcrDto paramBean);
	public UniOcrDto maxOcrUniInfo(HashMap<String,Object> paramMap);
	
}