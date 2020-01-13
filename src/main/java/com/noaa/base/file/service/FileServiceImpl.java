package com.noaa.base.file.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.BaseService;
import com.noaa.base.file.dao.AttachFileDTO;
import com.noaa.base.file.dao.DBInfoDTO;
import com.noaa.base.file.dao.FolderDTO;
import com.noaa.base.file.dao.IFileDao;
import com.noaa.base.global.SysKeyword;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


@Service("fileService")
public class FileServiceImpl extends BaseService implements IFileService {
	@Autowired
	private IFileDao fileDao;
	@Value("${file.ext.img}")
	private String fileImgExt;
	@Value("${file.ext.doc}")
	private String fileDocExt;
	@Value("#{'${file.path}'.split(',')}")
	private List<String> folderList;
	@Value("${file.temp.path}")
	private String tempPath;
	
	@Override
	public String create(List<MultipartFile> paramFiles, String filePath,String fileGrpID) {
		
		

		List<AttachFileDTO> rs = createFile(paramFiles, filePath, fileGrpID);
		for (AttachFileDTO attachFileDTO : rs) {
			fileDao.create(attachFileDTO);
		}

		if (rs.size() > 0) {
			return rs.get(0).getFile_grp_id();
		}
		return null;

	}

	@Override
	public String create(List<MultipartFile> paramFiles, String filePath) {

		List<AttachFileDTO> rs = createFile(paramFiles, filePath);
		for (AttachFileDTO attachFileDTO : rs) {
			fileDao.create(attachFileDTO);
		}

		if (rs.size() > 0) {
			return rs.get(0).getFile_grp_id();
		}
		return null;

	}
	
	@Override
	public List<AttachFileDTO> createObj(List<MultipartFile> paramFiles, String filePath) {

		List<AttachFileDTO> rs = createFile(paramFiles, filePath);
		for (AttachFileDTO attachFileDTO : rs) {
			fileDao.create(attachFileDTO);
		}

		if (rs.size() > 0) {
			return rs;
		}
		return null;

	}

	@Override
	public int drop(int paramSeq) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("file_seq", paramSeq);

		return fileDao.drop(paramMap);
	}

	@Override
	public List<AttachFileDTO> list(List<String> fileGrps) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("file_grp_ids", fileGrps);
		List<AttachFileDTO> rs = fileDao.list(paramMap);
		try {
			for (AttachFileDTO attachFileDTO : rs) {
				int n=SysKeyword.EXT_IMG.indexOf(attachFileDTO.getFile_ext());
				if (SysKeyword.EXT_IMG.indexOf(attachFileDTO.getFile_ext()) > -1) {

					attachFileDTO.setImgBase64(getImageBase64(attachFileDTO));

				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return rs;
	}

	private List<AttachFileDTO> createFile(List<MultipartFile> paramFiles,
			String filePath) {
		List<AttachFileDTO> rs = new ArrayList<AttachFileDTO>();
		Date date = new Date();
		SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dayDir = new SimpleDateFormat("yyyyMMdd");
		filePath = filePath + File.separator + dayDir.format(date)
				+ File.separator;
		File dirTarget = new File(filePath);

		String fileGrp = UUID.randomUUID().toString();

		if (!dirTarget.exists()) {
			dirTarget.mkdirs();
		}
		if (paramFiles != null && paramFiles.size() > 0) {
			try {
				int i = 1;
				for (MultipartFile multipartFile : paramFiles) {

					String orgFileName = multipartFile.getOriginalFilename();
					String newFileName = curDate.format(date) + i;
					String fileType = orgFileName.substring(
							orgFileName.lastIndexOf(".") + 1, orgFileName.length());
					;

					if (multipartFile.getBytes().length > 0) {
						File file = new File(filePath + newFileName + "."
								+ fileType);
						multipartFile.transferTo(file);
						AttachFileDTO temp = new AttachFileDTO();
						temp.setFile_ext(fileType);
						temp.setFile_grp_id(fileGrp);
						temp.setFile_new_name(newFileName + "." + fileType);
						temp.setFile_org_name(orgFileName);
						temp.setFile_size(multipartFile.getSize());
						temp.setFile_path(filePath);
						rs.add(temp);
						i++;
					}
				}

			} catch (Exception ex) {
				logger.debug(ex.getMessage());
			}

		}
		return rs;
	}

	private List<AttachFileDTO> createFile(List<MultipartFile> paramFiles,
			List<String> paramTitles, String filePath) {
		List<AttachFileDTO> rs = new ArrayList<AttachFileDTO>();
		Date date = new Date();
		SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dayDir = new SimpleDateFormat("yyyyMMdd");
		filePath = filePath + File.separator + dayDir.format(date)
				+ File.separator;
		File dirTarget = new File(filePath);

		String fileGrp = UUID.randomUUID().toString();

		if (!dirTarget.exists()) {
			dirTarget.mkdirs();
		}
		if (paramFiles != null && paramFiles.size() > 0) {
			try {
				int i = 1;
				for (MultipartFile multipartFile : paramFiles) {

					String orgFileName = multipartFile.getOriginalFilename();
					String newFileName = curDate.format(date) + i;
					String fileType = orgFileName.substring(
							orgFileName.indexOf(".") + 1, orgFileName.length());
					;

					if (multipartFile.getBytes().length > 0) {
						File file = new File(filePath + newFileName + "."
								+ fileType);
						multipartFile.transferTo(file);
						AttachFileDTO temp = new AttachFileDTO();
						temp.setFile_ext(fileType);
						temp.setFile_grp_id(fileGrp);
						temp.setFile_new_name(newFileName + "." + fileType);
						temp.setFile_org_name(orgFileName);
						temp.setFile_size(multipartFile.getSize());
						temp.setFile_path(filePath);
						temp.setFile_title(paramTitles.get(i - 1));
						rs.add(temp);
						i++;
					}
				}

			} catch (Exception ex) {
				logger.debug(ex.getMessage());
			}

		}
		return rs;
	}

	private List<AttachFileDTO> createFile(List<MultipartFile> paramFiles,
			String filePath, String fileGrpID) {
		List<AttachFileDTO> rs = new ArrayList<AttachFileDTO>();
		Date date = new Date();
		SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dayDir = new SimpleDateFormat("yyyyMMdd");
		filePath = filePath + File.separator + dayDir.format(date)
				+ File.separator;
		File dirTarget = new File(filePath);

		String fileGrp = fileGrpID;

		if (!dirTarget.exists()) {
			dirTarget.mkdirs();
		}
		if (paramFiles != null && paramFiles.size() > 0) {
			try {
				int i = 1;
				for (MultipartFile multipartFile : paramFiles) {

					String orgFileName = multipartFile.getOriginalFilename();
					String newFileName = curDate.format(date) + i;
					String fileType = orgFileName.substring(
							orgFileName.indexOf(".") + 1, orgFileName.length());
					;

					if (multipartFile.getBytes().length > 0) {
						File file = new File(filePath + newFileName + "."
								+ fileType);
						multipartFile.transferTo(file);
						AttachFileDTO temp = new AttachFileDTO();
						temp.setFile_ext(fileType);
						temp.setFile_grp_id(fileGrp);
						temp.setFile_new_name(newFileName + "." + fileType);
						temp.setFile_org_name(orgFileName);
						temp.setFile_size(multipartFile.getSize());
						temp.setFile_path(filePath);
						rs.add(temp);
						i++;
					}
				}

			} catch (Exception ex) {
				logger.debug(ex.getMessage());
			}

		}
		return rs;
	}

	@Override
	public String create(List<MultipartFile> paramFiles,
			List<String> paramTitles, String filePath) {
		List<AttachFileDTO> rs = createFile(paramFiles, paramTitles, filePath);
		for (AttachFileDTO attachFileDTO : rs) {
			fileDao.create(attachFileDTO);
		}

		if (rs.size() > 0) {
			return rs.get(0).getFile_grp_id();
		}
		return null;
	}

	@Override
	public AttachFileDTO detail(int paramSeq) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("file_seq", paramSeq);
		return fileDao.detail(paramMap);
	}

	@Override
	public String create(List<MultipartFile> paramFiles,
			List<String> paramTitles, String filePath, String fileGrpID) {
		List<AttachFileDTO> rs = createFile(paramFiles, paramTitles, filePath,
				fileGrpID);
		for (AttachFileDTO attachFileDTO : rs) {
			fileDao.create(attachFileDTO);
		}

		if (rs.size() > 0) {
			return rs.get(0).getFile_grp_id();
		}
		return null;
	}

	private List<AttachFileDTO> createFile(List<MultipartFile> paramFiles,
			List<String> paramTitles, String filePath, String fileGrpID) {
		List<AttachFileDTO> rs = new ArrayList<AttachFileDTO>();
		Date date = new Date();
		SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dayDir = new SimpleDateFormat("yyyyMMdd");
		filePath = filePath + File.separator + dayDir.format(date)
				+ File.separator;
		File dirTarget = new File(filePath);

		String fileGrp = fileGrpID;

		if (!dirTarget.exists()) {
			dirTarget.mkdirs();
		}
		if (paramFiles != null && paramFiles.size() > 0) {
			try {
				int i = 1;
				for (MultipartFile multipartFile : paramFiles) {

					String orgFileName = multipartFile.getOriginalFilename();
					String newFileName = curDate.format(date) + i;
					String fileType = orgFileName.substring(
							orgFileName.indexOf(".") + 1, orgFileName.length());
					;

					if (multipartFile.getBytes().length > 0) {
						File file = new File(filePath + newFileName + "."
								+ fileType);
						multipartFile.transferTo(file);
						AttachFileDTO temp = new AttachFileDTO();
						temp.setFile_ext(fileType);
						temp.setFile_grp_id(fileGrp);
						temp.setFile_new_name(newFileName + "." + fileType);
						temp.setFile_org_name(orgFileName);
						temp.setFile_size(multipartFile.getSize());
						temp.setFile_path(filePath);
						temp.setFile_title(paramTitles.get(i - 1));
						rs.add(temp);
						i++;
					}
				}

			} catch (Exception ex) {
				logger.debug(ex.getMessage());
			}

		}
		return rs;
	}

	@Override
	public boolean isExtImgCheck(List<MultipartFile> paramFiles) {
		boolean result = false;
		for (int i = 0; i < paramFiles.size(); i++) {
			int ext = paramFiles.get(i).getOriginalFilename().lastIndexOf(".");
			String paramExt = paramFiles.get(i).getOriginalFilename()
					.substring(ext + 1);
			System.out.println("index: " + fileImgExt.indexOf(paramExt.toLowerCase()));
			if (fileImgExt.indexOf(paramExt.toLowerCase()) == -1) {
				break;
			} else {
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean isExtDocCheck(List<MultipartFile> paramFiles) {
		boolean result = false;
		for (int i = 0; i < paramFiles.size(); i++) {
			int ext = paramFiles.get(i).getOriginalFilename().lastIndexOf(".");
			String paramExt = paramFiles.get(i).getOriginalFilename()
					.substring(ext + 1);
			System.out.println("index: " + fileDocExt.indexOf(paramExt));
			if (fileDocExt.indexOf(paramExt) == -1) {
				break;
			} else {
				result = true;
			}
		}
		return result;
	}

	@Override
	public String getImageBase64(AttachFileDTO paramFile) throws Exception {
		String fileString = new String();
		FileInputStream inputStream = null;
		ByteArrayOutputStream byteOutStream = null;

		try {
			inputStream = new FileInputStream(paramFile.getFile_path()+paramFile.getFile_new_name());
			byteOutStream = new ByteArrayOutputStream();

			int len = 0;
			byte[] buf = new byte[(int) paramFile.getFile_size()];
			while ((len = inputStream.read(buf)) != -1) {
				byteOutStream.write(buf, 0, len);
			}

			byte[] fileArray = byteOutStream.toByteArray();
			fileString = new String(Base64.encodeBase64(fileArray));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
			byteOutStream.close();
		}
		return "data:image/" + paramFile.getFile_ext() + ";base64,"
				+ fileString;
	}

	private Double getFileSize(String path) {
		Double l = new Double(0); 
		File file =new File(path);
		File[] list = file.listFiles();
		if(list != null){
			for (int i=0; i<list.length; i++) {
				if (list[i].isDirectory()){
					l = new Double(l.longValue() + getFileSize(list[i].getPath()).longValue());
				}else {
					l = new Double(l.longValue() + list[i].length());
				}
			}
		}
		
	     return l; 
	}
	@Override
	public List<FolderDTO> getFileSizeList() {
		List<FolderDTO>  result = new ArrayList<FolderDTO>();
		for(int i=0;i<folderList.size();i++){
			FolderDTO fDTO = new FolderDTO();
			Double folderSize = getFileSize(folderList.get(i));
			fDTO.setFolder_ext(folderList.get(i));
			fDTO.setFolder_size(folderSize.doubleValue());
			result.add(fDTO);  
		}
		return result;
	}
	@Override
	public List<DBInfoDTO> dbList() {
		return fileDao.dbList();
	}

	@Override
	public boolean isNotFileEmpty(List<MultipartFile> paramFiles) {
		boolean result = true;
		for(int i=0;i<paramFiles.size();i++){
			if(paramFiles.get(i).isEmpty()){
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public String create(List<MultipartFile> paramFiles) {
		
		return create(paramFiles,tempPath);
	}

	
	
	@Override
	public void moveFileFromTmep(String fileGrpID, String filePath)throws Exception {
		List<String> fileGrpIds=new ArrayList<String>();
		fileGrpIds.add(fileGrpID);
		List<AttachFileDTO> files=list(fileGrpIds);
		Date date = new Date();
		SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dayDir = new SimpleDateFormat("yyyyMMdd");
		for (AttachFileDTO attachFileDTO : files) {
		  String dir=filePath+File.separator+dayDir.format(date)+File.separator;
		  
		  File dirTarget = new File(dir);
		  if (!dirTarget.exists()) {
				dirTarget.mkdirs();
		  }
		  String newFileName=dir+attachFileDTO.getFile_new_name();
		  String tempFileName=attachFileDTO.getFile_path()+attachFileDTO.getFile_new_name();
		  Files.copy(new File(tempFileName).toPath(), new File(newFileName).toPath(),StandardCopyOption.REPLACE_EXISTING);
		  attachFileDTO.setFile_path(dir);
		}
		update(files);
	}

	@Override
	public int update(List<AttachFileDTO> paramFile) {
		int rs=0;
		for (AttachFileDTO attachFileDTO : paramFile) {
			rs+=fileDao.update(attachFileDTO);
		}
		return rs;
	}

	@Override
	public List<String> listImageUnzipFiles(String fileGrpID) {
		List<String> rs=new ArrayList<String>();
		
		List<String> param=new ArrayList<String>();
		param.add(fileGrpID);
		List<AttachFileDTO> listFiles=list(param);
		/*List<AttachFileDTO> listFiles=new ArrayList<AttachFileDTO>();
		AttachFileDTO test=new AttachFileDTO();
		test.setFile_ext("zip");
		test.setFile_path("G:\\");
		test.setFile_new_name("testzips.zip");
		listFiles.add(test);*/
		try{
			for (AttachFileDTO attachFileDTO : listFiles) {
				if(attachFileDTO.getFile_ext().toLowerCase().equals("zip")){
					 ZipInputStream zipIn = new ZipInputStream(new FileInputStream(attachFileDTO.getFile_path() + attachFileDTO.getFile_new_name()),Charset.forName("CP866"));
					 ZipEntry entry = zipIn.getNextEntry();
					 byte[] bytesIn = new byte[1024];
					 while(entry != null){
						 String fileExt=entry.getName().substring(entry.getName().lastIndexOf(".")+1).toLowerCase();
						 if(fileExt.equals("jpeg")||fileExt.equals("jpg")||fileExt.equals("gif")||fileExt.equals("png")){
							 int read=0;
							 ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
							 while ((read=zipIn.read(bytesIn))!=-1) {
								 byteOutStream.write(bytesIn,0,read);
							}
							 byte[] fileArray = byteOutStream.toByteArray();
							 String	fileString = new String(Base64.encodeBase64(fileArray));
							
							 String base64File= "data:image/" + entry.getName().substring(entry.getName().lastIndexOf(".")+1) + ";base64,"+ fileString;
							 rs.add(base64File);
						 } 
						 entry = zipIn.getNextEntry();
					 
					 }
					 
				}
			}
		}catch(Exception ex){
			
		}
		return rs;
	}

	@Override
	public String createZip(String fileGrpID)throws Exception {
		List<String> param=new ArrayList<String>();
		param.add(fileGrpID);
		List<AttachFileDTO> listFiles=list(param);
		
		Date date = new Date(); 
		SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String outFilename = tempPath + curDate.format(date) + ".zip";
		FileOutputStream fout = null;
		ZipOutputStream zout = null; 
		try{
			fout = new FileOutputStream(outFilename);
			zout = new ZipOutputStream(fout); 
			zout.setLevel(9);
			for (AttachFileDTO attachFileDTO : listFiles) {
				String targetFile =attachFileDTO.getFile_path() + attachFileDTO.getFile_new_name();
				ZipEntry ze = new ZipEntry(attachFileDTO.getFile_org_name());
				FileInputStream fin = new FileInputStream(targetFile);
				zout.putNextEntry(ze);
				try{
					for (int c = fin.read(); c != -1; c =fin.read()) { 
						zout.write(c); 
					}
				}catch(Exception ex){
					zout.close();
					fin.close();
					
					break;
				}
				
				
			}
		}catch(Exception ex){
			outFilename=null;
		} finally{
			if(zout!=null) zout.close();
			if(fout!=null) fout.close();
		
			
		}
		return outFilename;
	}
	@Override
	public AttachFileDTO detailFileInfo(String paramFileGrpId) {
		List<String> fileGrps=new ArrayList<String>();
		AttachFileDTO fileInfo = new AttachFileDTO();
		if(paramFileGrpId != null){
			fileGrps.add(paramFileGrpId);
		}
		if(fileGrps!=null && fileGrps.size()>0){
			List<AttachFileDTO> files=list(fileGrps);
			for (AttachFileDTO attachFileDTO : files) {
				if(attachFileDTO.getFile_grp_id().equals(paramFileGrpId)){
					fileInfo = attachFileDTO;
				}
			}
		}
		return fileInfo;
	}

	@Override
	public String createBase64ImageSave(String paramImgString,String paramFilePath) {
		String fileName=UUID.randomUUID().toString()+".png";
		String path=paramFilePath+File.separator+fileName;
		String data=paramImgString.split(",")[1];
		byte[] images=DatatypeConverter.parseBase64Binary(data);
		try{
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(images));
			ImageIO.write(bufImg, "png", new File(path));
		}catch(Exception ex){
			ex.printStackTrace();
			fileName=null;
		}
		return fileName;
	}
}
