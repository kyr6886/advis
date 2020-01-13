package com.noaa.base.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.noaa.base.BaseController;
import com.noaa.base.BaseViewModel;
import com.noaa.base.file.dao.AttachFileDTO;
import com.noaa.base.file.service.IFileService;

@Controller("adminFileController")
public class AdminFileController extends BaseController {
	@Autowired
	private IFileService fileService;

	@RequestMapping(value = "/file/download/{fileSeq}", method = RequestMethod.GET)
	public void fileDownloadAction(@PathVariable int fileSeq,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AttachFileDTO fileEntity = fileService.detail(fileSeq);
		File file = new File(fileEntity.getFile_path()+ fileEntity.getFile_new_name());
		if (file.isFile()) {
			String dispositionPrefix = "attachment; filename=";
			String filename = fileEntity.getFile_org_name();
			String browser = getBrowser(request);
			String encodedFilename = "";
			if (browser.equals("MSIE")) {
				encodedFilename = URLEncoder.encode(filename, "UTF-8")
						.replaceAll("\\+", "%20");
			} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
				encodedFilename = URLEncoder.encode(filename, "UTF-8")
						.replaceAll("\\+", "%20");
			} else if (browser.equals("Firefox")) {
				encodedFilename = "\""
						+ new String(filename.getBytes("UTF-8"), "8859_1")
						+ "\"";
				encodedFilename = URLDecoder.decode(encodedFilename);
			} else if (browser.equals("Opera")) {
				encodedFilename = "\""
						+ new String(filename.getBytes("UTF-8"), "8859_1")
						+ "\"";
			} else if (browser.equals("Chrome")) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < filename.length(); i++) {
					char c = filename.charAt(i);
					if (c > '~') {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				encodedFilename = sb.toString();
			} else if (browser.equals("Safari")) {
				encodedFilename = "\""
						+ new String(filename.getBytes("UTF-8"), "8859_1")
						+ "\"";
				encodedFilename = URLDecoder.decode(encodedFilename);
			} else {
				encodedFilename = "\""
						+ new String(filename.getBytes("UTF-8"), "8859_1")
						+ "\"";
			}

			response.setHeader("Content-Disposition", dispositionPrefix
					+ encodedFilename);
			if ("Opera".equals(browser)) {
				response.setContentType("application/octet-stream;charset=UTF-8");
			}

			OutputStream out = response.getOutputStream();
			FileInputStream fis = null;

			try {
				int temp;
				fis = new FileInputStream(file);
				while ((temp = fis.read()) != -1) {
					out.write(temp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	@RequestMapping(value = "/api/file/unzip/image/list", method = RequestMethod.POST)
	public @ResponseBody List<String> listUnzipFileAction(
			@RequestBody BaseViewModel vm, ModelMap model) {
		return fileService.listImageUnzipFiles(vm.getId());
	}

	@RequestMapping(value = "/api/file/upload", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> createFile(BaseViewModel vm, ModelMap model)
			throws Exception {
		String rs = fileService.create(vm.getUploadFiles());
		List<String> paramFileGrp = new ArrayList<String>();
		paramFileGrp.add(rs);
		List<AttachFileDTO> rsFile = fileService.list(paramFileGrp);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("file_grp_id", rs);
		map.put("file_grp_key", String.valueOf(rsFile.get(0).getFile_seq()));
		return map;

	}

	@RequestMapping(value = "/file/download/group/{fileGrp}", method = RequestMethod.GET)
	public void fileGrpDownloadAction(@PathVariable String fileGrp,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<String> paramFileGrp = new ArrayList<String>();
		paramFileGrp.add(fileGrp);
		List<AttachFileDTO> files = fileService.list(paramFileGrp);
		if (files.size() == 1) {
			AttachFileDTO fileEntity = files.get(0);
			File file = new File(fileEntity.getFile_path()+ fileEntity.getFile_new_name());
			if (file.isFile()) {
				String dispositionPrefix = "attachment; filename=";
				String filename = fileEntity.getFile_org_name();
				String browser = getBrowser(request);
				String encodedFilename = "";
				if (browser.equals("MSIE")) {encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
				} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
					encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
				} else if (browser.equals("Firefox")) {
					encodedFilename = "\""+ new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
					encodedFilename = URLDecoder.decode(encodedFilename);
				} else if (browser.equals("Opera")) {
					encodedFilename = "\""+ new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
				} else if (browser.equals("Chrome")) {
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < filename.length(); i++) {
						char c = filename.charAt(i);
						if (c > '~') {
							sb.append(URLEncoder.encode("" + c, "UTF-8"));
						} else {
							sb.append(c);
						}
					}
					encodedFilename = sb.toString();
				} else if (browser.equals("Safari")) {encodedFilename = "\""+ new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
					encodedFilename = URLDecoder.decode(encodedFilename);
				} else {
					encodedFilename = "\""+ new String(filename.getBytes("UTF-8"), "8859_1")
							+ "\"";
				}

				response.setHeader("Content-Disposition", dispositionPrefix
						+ encodedFilename);
				if ("Opera".equals(browser)) {
					response.setContentType("application/octet-stream;charset=UTF-8");
				}

				OutputStream out = response.getOutputStream();
				FileInputStream fis = null;

				try {
					int temp;
					fis = new FileInputStream(file);
					while ((temp = fis.read()) != -1) {
						out.write(temp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (fis != null) {
						try {
							fis.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else if (files.size() > 1) {
			
			response.setHeader("Content-Disposition", "attachment;filename=download.zip;"); 
			OutputStream out = response.getOutputStream(); 
			FileInputStream fis = null;
			try { 
				int temp; fis = new FileInputStream(fileService.createZip(fileGrp)); 
			while ((temp= fis.read()) != -1) { 
				out.write(temp); 
				} 
			} catch (Exception e) {
				e.printStackTrace(); 
				} 
			finally { 
				if (fis != null) { 
				try {
					fis.close(); 
				} catch (Exception e){ 
				  e.printStackTrace(); 
				} 
			} 
		}
		}

		
	}

	public String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		} else if (header.indexOf("Safari") > -1) {
			return "Safari";
		}
		return "Firefox";
	}

}
