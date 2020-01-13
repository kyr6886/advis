package kr.dis.std.category.service;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.BaseService;
import com.noaa.base.global.SysKeyword;

import kr.dis.std.advis.dao.SbjRptOcrDto;
import kr.dis.std.advis.dao.UniOcrDto;
import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.dao.ICategoryDao;

import kr.dis.std.category.dao.TbOcrFileDTO;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventItemDto;
import kr.dis.std.event.dao.IEventDao;
import kr.dis.std.event.dao.IEventItemDao;
import kr.dis.std.log.dao.ILogDisSearchDao;
import kr.dis.std.log.dao.LogDisSearchDto;

@Service("categoryService")
public class CategoryServiceImpl extends BaseService implements ICateoryService {
	@Autowired
	private ICategoryDao categoryDao;
	
	@Autowired
	private IEventDao eventDao;
	
	@Autowired
	private IEventItemDao eventItemDao;
	
	@Autowired
	private ILogDisSearchDao logDisSearchDao;
	
	@Value("${file.ocr.path}")
	private String folderPath;
	
	@Override
	public int insertCategory(CategoryDTO paramBean,String loginUserId) {
		
		List<CategoryDTO> rs=null;
		paramBean.setCreate_user_id(loginUserId);
		ArrayList<Integer> ctgArr=new ArrayList<Integer>();
		ArrayList<Integer> ctgDepthArr=new ArrayList<Integer>();
		ArrayList<Integer> ctgSortArr=new ArrayList<Integer>();
		rs=categoryDao.createCategory(paramBean);

		if (rs.size() > 0) {
			if (rs.size() == 1 && rs.get(0).getDepth() == 1) {
				paramBean.setSort(rs.get(0).getSort()-1);
			}
			
			if (paramBean.getDepth() != 1) {
				for(int i=0; i < rs.size(); i++) {
					if (  rs.get(i).getDepth() != 1) {
						ctgDepthArr.add(rs.get(i).getDepth());
						if (rs.get(i).getDepth() == paramBean.getDepth()) {
							ctgArr.add(rs.get(i).getSort());
						} else {
							ctgSortArr.add(rs.get(i).getSort());
						}
						//update
						if (paramBean.getDepth() <=  rs.get(i).getDepth()) {
							rs.get(i).setSort(rs.get(i).getSort()-1);
							categoryDao.sortUpdate(rs.get(i));	
						}
					}
				}
			}
			if (ctgArr.size() > 0) {
				Collections.sort(ctgArr);
				Collections.reverse(ctgArr);
				paramBean.setSort(ctgArr.get(0));
			}
			if ( ctgSortArr.size() > 0) {
				Collections.sort(ctgDepthArr);
				Collections.reverse(ctgDepthArr);
				Collections.sort(ctgSortArr);
				
				if (ctgDepthArr.get(0) < paramBean.getDepth()) {
					paramBean.setSort(ctgSortArr.get(0)-1);	
				}
			}
		}
		if (rs.size() == 0) {
			CategoryDTO paramDTO = new CategoryDTO();
			paramDTO.setCtg_group(null);
			rs=categoryDao.createCategory(paramDTO);
		}
		
		if (paramBean.getDepth() == 1) {
			paramBean.setParent_ctg_id(paramBean.getPrint_id());
			paramBean.setPrint_id(paramBean.getPrint_id());
			paramBean.setCtg_group(paramBean.getPrint_id());
			paramBean.setSort(rs.get(0).getSort()+10000);
		}
		
		//return 0;
		return categoryDao.insertCategory(paramBean);
	}

	@Override
	public List<CategoryDTO> listCategory(String paramCtgId,int paramDepth,String paramPrintId) {
		
		CategoryDTO paramDTO = new CategoryDTO();
		paramDTO.setParent_ctg_id(paramCtgId);
		paramDTO.setDepth(paramDepth);
		paramDTO.setPrint_id(paramPrintId);
		List<CategoryDTO> rs=null;
		rs=categoryDao.listDiscode(paramDTO);
		return rs;
	}
	@Override
	public List<CategoryDTO> listCategory(String paramCtgId,int paramDepth,String paramPrintId,String paramTitle) {
		
		CategoryDTO paramDTO = new CategoryDTO();
		paramDTO.setCtg_group(paramCtgId);
		paramDTO.setDepth(paramDepth);
		paramDTO.setPrint_id(paramPrintId);
		paramDTO.setTitle(paramTitle.replace(" ",""));
		List<CategoryDTO> rs=null;
		rs=categoryDao.listDiscode(paramDTO);
		return rs;
	}
	public int updateCategory(CategoryDTO param,String loginUserId) {
		
		int rs = 0;
		
		if (param.getLargeId() != null && !( "").equals(param.getLargeId())) {
			CategoryDTO paramDTO = new CategoryDTO();
			paramDTO.setTitle(param.getLargeTitle());
			paramDTO.setPrint_id(param.getLargeId());
			paramDTO.setUpdate_user_id(loginUserId);
			categoryDao.updateCategory(paramDTO);
			rs = 1;
		}  
		if (param.getMediumId() != null && !( "").equals(param.getMediumId())) {
			CategoryDTO paramDTO = new CategoryDTO();
			paramDTO.setTitle(param.getMediumTitle());
			paramDTO.setPrint_id(param.getMediumId());
			paramDTO.setUpdate_user_id(loginUserId);
			categoryDao.updateCategory(paramDTO);
			rs = 1;
		} 
		if (param.getSmallId() != null && !( "").equals(param.getSmallId())) {
			CategoryDTO paramDTO = new CategoryDTO();
			paramDTO.setTitle(param.getSmallTitle());
			paramDTO.setPrint_id(param.getSmallId());
			paramDTO.setUpdate_user_id(loginUserId);
			categoryDao.updateCategory(paramDTO);
			rs = 1;
		}
		if (param.getSmallerId() != null && !( "").equals(param.getSmallerId())) {
			CategoryDTO paramDTO = new CategoryDTO();
			paramDTO.setTitle(param.getSmallerTitle());
			paramDTO.setPrint_id(param.getSmallerId());
			paramDTO.setUpdate_user_id(loginUserId);
			categoryDao.updateCategory(paramDTO);
			rs = 1;
		}
		if (param.getDetailedId() != null && !( "").equals(param.getDetailedId())) {
			CategoryDTO paramDTO = new CategoryDTO();
			paramDTO.setTitle(param.getDetailedTitle());
			paramDTO.setPrint_id(param.getDetailedId());
			paramDTO.setUpdate_user_id(loginUserId);
			categoryDao.updateCategory(paramDTO);
			rs = 1;
		}
		return rs;
	}

	public int deleteCategory(CategoryDTO paramBean) {
		int result = 0;
		CategoryDTO paramDTO = new CategoryDTO();
		paramDTO.setUse_yn(SysKeyword.COMMON_PARAM_USE_N);
		paramDTO.setCtg_id(paramBean.getCtg_id());
		paramDTO.setDepth(paramBean.getDepth());
		
		result = categoryDao.deleteCategory(paramDTO);
		 
		return result;
	}

	@Override
	public List<CategoryDTO> listCategory(CategoryDTO paramBean) {
		return categoryDao.listCategory(paramBean);
	}

	@Override
	public List<EventDto> listCtgId(CategoryDTO paramBean) {
		ArrayList<String> arrList = new ArrayList<String>();
		List<CategoryDTO> ctgAllList = null;
		ctgAllList = categoryDao.listCtgId(paramBean);
		if(ctgAllList.size()>0){
			for(int i=0; i<ctgAllList.size(); i++){
				int resultIndex = paramBean.getTitle().indexOf( ctgAllList.get(i).getTitle() );
				if(resultIndex > -1){
					arrList.add(ctgAllList.get(i).getTitle()); //keyword list
					LogDisSearchDto dto = new LogDisSearchDto();
					dto.setCategory_id(ctgAllList.get(i).getCtg_id());
					dto.setSession_id(paramBean.getSession_id());
					logDisSearchDao.insert(dto);
				}
			}
		}
		
		EventDto eventDto = new EventDto();
		eventDto.setContents(paramBean.getTitle());
		eventDto.setKeyWordList(arrList);
		List<EventDto> eventList = eventDao.searchCtgList(eventDto); //keword->eventItem search->eventId list
		
		for(int i=0; i<eventList.size(); i++){
			EventItemDto paramdto = new EventItemDto();
			paramdto.setEvt_id(eventList.get(i).getEvt_id());
			List<EventItemDto> eventItemList = null;
			eventItemList = eventItemDao.selectDepthEventItemList(paramdto);

			if(eventItemList.size()>0){
				eventList.get(i).setEventItemList(eventItemList);
			}
			
//			if(eventItemList.size()>0){
//				eventList.get(i).setEvt_id(eventItemList.get(0).getEvt_id());
//				eventList.get(i).setEvt_num(eventItemList.get(0).getEvt_num());
//				eventList.get(i).setContents(eventItemList.get(0).getContents());
//				eventList.get(i).setDivision(eventItemList.get(0).getContentsTitle());
//			}
		}
		
		if(arrList.size() == 0){
			if(eventList.size()>0){
				for(int i=0; i<eventList.size(); i++){
					if(!arrList.contains(eventList.get(i).getCtg_id())){
						arrList.add(eventList.get(i).getCtg_id().toString());
					}
				}
				for(int i=0; i<arrList.size(); i++){
					LogDisSearchDto dto = new LogDisSearchDto();
					dto.setCategory_id(arrList.get(i));
					logDisSearchDao.insert(dto);
				}
			}
		}
		return eventList;
	}

	@Override
	public List<TbOcrFileDTO> listTbOcrFile(int pageNo, int pageSize) {
		TbOcrFileDTO dto = new TbOcrFileDTO();
		dto.setPageNo(pageNo);
		dto.setPageSize(pageSize);
		
		return categoryDao.listTbOcrFile(dto);
	}

	@Override
	public int countTbOcrFile(String ctg_id, String evt_id) {
		TbOcrFileDTO dto = new TbOcrFileDTO();
		dto.setCtg_id(ctg_id);
		dto.setEvt_id(evt_id);
		
		return categoryDao.countTbOcrFile(dto);
	}
	
	@Override
	public int createTbOcrImgFile(String ctg_id, String evt_id, String title, MultipartFile file) {
		int rs = 0;
		
		File folder = new File(folderPath);
		
		if(!folder.exists())
			folder.mkdirs();
		
		TbOcrFileDTO dto = new TbOcrFileDTO();
		dto.setCtg_id(ctg_id);
		dto.setEvt_id(dto.getCtg_id() + "-" + evt_id + "-1");
		dto.setTitle(title);
		dto.setFile_org_name(file.getOriginalFilename());
		
		if(SysKeyword.EXT_IMG.indexOf(dto.getFile_org_name().toLowerCase().substring(
										dto.getFile_org_name().lastIndexOf(".")+1)) > -1){
			dto.setFile_new_name(dto.getEvt_id() + 
					dto.getFile_org_name().substring(dto.getFile_org_name().
													lastIndexOf(".")).toLowerCase());
			
			try {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
										new File(folderPath + "\\" + dto.getFile_new_name())));
				stream.write(file.getBytes());
				stream.close();
				
				rs = categoryDao.insertTbOcrFile(dto);
				HashMap<String,Object> paramMap=new HashMap<String,Object>();
				paramMap.put("u_id", dto.getEvt_id());
				UniOcrDto ocrPrg=categoryDao.maxOcrUniInfo(paramMap);
				if(ocrPrg==null){
					ocrPrg=new UniOcrDto();
				}
				
				ocrPrg.setU_id(dto.getEvt_id());
				ocrPrg.setU_seq(ocrPrg.getU_seq()+1);
				ocrPrg.setU_name(dto.getFile_org_name());
				ocrPrg.setC_u_name(dto.getFile_new_name());
				ocrPrg.setU_status("NO");
				rs+= categoryDao.createOcrUni(ocrPrg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return rs;
	}
	
	@Override
	public int deleteTbOcrImgFile(String ctg_id, String evt_id) {
		int rs = 0;
		
		TbOcrFileDTO dto = new TbOcrFileDTO();
		dto.setCtg_id(ctg_id);
		dto.setEvt_id(evt_id);
		
		List<TbOcrFileDTO> list = categoryDao.listTbOcrFile(dto);
		
		if(list.size() > 0){
			rs = categoryDao.deleteTbOcrFile(dto);
			
			File file = new File(folderPath + "\\" + list.get(0).getFile_new_name());
			
			if(file.exists())
				file.delete();
		}
		
		return rs;
	}

	@Override
	public List<SbjRptOcrDto> detailOcrFile(String ctg_id, String evt_id) {
		TbOcrFileDTO paramObj=detailTbOcrFile(ctg_id,evt_id);
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("file_new_name",paramObj.getFile_new_name());
		return categoryDao.detailSbjRptOcr(paramMap);
	}

	@Override
	public TbOcrFileDTO detailTbOcrFile(String ctg_id, String evt_id) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("ctg_id", ctg_id);
		paramMap.put("evt_id", evt_id);
		return categoryDao.detailOcrFile(paramMap);
	}
}