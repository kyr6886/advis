package com.noaa.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
	protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	 protected void addPageIndex(HashMap<String,Object> paramHash, int pageNum, int rowSize)
    {
		 pageNum=pageNum==0?1:pageNum;
		 rowSize=rowSize==0?100:rowSize;
        int firstIndex = (pageNum - 1) * rowSize ;
        int lastIndex = firstIndex + rowSize - 1;
        //firstIndex=firstIndex<=0?0:firstIndex;
        paramHash.put("FirstIndex", firstIndex);
        paramHash.put("LastIndex", lastIndex);
    }
	 
	 protected String createID(){
		 Date date=new Date();
		SimpleDateFormat curDate=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return curDate.format(date);
	 }
}
