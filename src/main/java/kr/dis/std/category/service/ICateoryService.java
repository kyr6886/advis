package kr.dis.std.category.service;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.dis.std.advis.dao.SbjRptOcrDto;
import kr.dis.std.category.dao.CategoryDTO;

import kr.dis.std.category.dao.TbOcrFileDTO;
import kr.dis.std.event.dao.EventDto;

public interface ICateoryService {
	public int insertCategory(CategoryDTO paramBean,String loginUserId);
	public List<CategoryDTO> listCategory(String paramCtgId,int paramDepth,String paramPrintId);
	public List<CategoryDTO> listCategory(String paramCtgId,int paramDepth,String paramPrintId,String paramTitle);
	public int updateCategory(CategoryDTO paramBean,String loginUserId);
	public int deleteCategory(CategoryDTO paramBean);
	public List<CategoryDTO> listCategory(CategoryDTO paramBean);
	public List<EventDto> listCtgId(CategoryDTO paramBean);
	public List<TbOcrFileDTO> listTbOcrFile(int pageNo, int pageSize);
	
	public List<SbjRptOcrDto> detailOcrFile(String ctg_id,String evt_id);
	public TbOcrFileDTO detailTbOcrFile(String ctg_id, String evt_id);
	public int countTbOcrFile(String ctg_id, String evt_id);
	public int createTbOcrImgFile(String ctg_id, String evt_id, String title, MultipartFile file);
	public int deleteTbOcrImgFile(String ctg_id, String evt_id);
	
	
}