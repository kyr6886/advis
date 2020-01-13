package com.noaa.base.reporting.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.tidy.Tidy;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.noaa.base.BaseService;
import com.noaa.base.utils.DateHelper;

@Service("reportingPdfService")
public class ReportingPdfServiceImpl extends BaseService implements IReportingPdfService {
	@Value("${file.pdf.path}")
	private String filePdfPath;
	@Override
	public String createPdf(String cssPath,String fontPath,String htmlStr) {
		Document document =null;
		String fileFullPath=null;
		String fileName=null;
		try{
			document = new Document(PageSize.A4, 50, 50, 50, 50); // 용지 및 여백 설정
			
			fileName=DateHelper.getNextYYYYMMDDHHmmssSSS();
			fileFullPath=filePdfPath+fileName+".pdf";
			File dirTarget = new File(filePdfPath);
			if (!dirTarget.exists()) {
				dirTarget.mkdirs();
			}
			
			// PdfWriter 생성
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileFullPath)); // 바로 다운로드.
			//PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			writer.setInitialLeading(12.5f);
			// Document 오픈
			document.open();
			XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
			     
			// CSS
			CSSResolver cssResolver = new StyleAttrCSSResolver();
			CssFile cssFile = helper.getCSS(new FileInputStream(cssPath));
			cssResolver.addCss(cssFile);
			     
			// HTML, 폰트 설정
			XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
			fontProvider.register(fontPath, "MalgunGothic"); // MalgunGothic은 alias,
			CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
			 
			HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
			
			 htmlContext.setImageProvider(new Base64ImageProvider());
			// Pipelines
			PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
			
			HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
			
			CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
			 
			XMLWorker worker = new XMLWorker(css, true);
			XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
			StringWriter sw = new StringWriter(); 
			StringReader sr = new StringReader(htmlStr); 
			
			Tidy tidy = new Tidy(); 
			tidy.setDropEmptyParas(true); 
			tidy.setShowWarnings(false); //to hide errors 
			tidy.setQuiet(true); //to hide warning 
			tidy.setUpperCaseAttrs(false); 
			tidy.setUpperCaseTags(false); 
			tidy.setXmlOut(true); 
			tidy.parse(sr, sw); 
			String htmlS = sw.toString(); 
			
			StringReader strReader = new StringReader(htmlS); 
			xmlParser.parse(strReader);
			
			

			document.close();
			writer.close();
			
		}catch(Exception ex){
			document.close();
			document=null;
			System.out.println(ex.getMessage());
			
		}
		return fileName;
	}
	
	@Override
	public String readPdf(String paramFileName) {
		
		return filePdfPath+paramFileName+".pdf";
	}
	
	
	class Base64ImageProvider extends AbstractImageProvider {
		 
		@Override
        public Image retrieve(String src) {
            int pos = src.indexOf("base64,");
            try {
                if (src.startsWith("data") && pos > 0) {
                    byte[] img = com.itextpdf.text.pdf.codec.Base64.decode(src.substring(pos + 7));
                    return Image.getInstance(img);
                }
                else {
                    return Image.getInstance(src);
                }
            } catch (BadElementException ex) {
                return null;
            } catch (IOException ex) {
                return null;
            }
        }
 
        @Override
        public String getImageRootPath() {
            return null;
        }
    }
	
}
