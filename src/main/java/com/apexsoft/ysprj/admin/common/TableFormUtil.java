package com.apexsoft.ysprj.admin.common;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableFormUtil {
	private int totalColNum;
	private int headerRowNum;
	private int dataColNum;
	private int titleColNum;
	private int downSumIdx;
	private int rightSumIdx;
	private String countUnit="";
	
  
	ArrayList Header = new ArrayList();
	List <HtmlTag>htmlTag;
	

	public TableFormUtil(String countUnit, int titleColNum, int totalColNum, int dataColNum, int downSumIdx, int rightSumIdx){
		
		this.countUnit=countUnit;
		this.titleColNum=titleColNum;
		this.totalColNum=totalColNum;
		this.dataColNum=dataColNum;
		this.downSumIdx=downSumIdx;
		this.rightSumIdx=rightSumIdx;
	}
	
	public boolean makeTableFromList( ArrayList titleHeaderList, ArrayList dataHeaderList, ResultSet dataList){
		boolean result = false;
		if( titleHeaderList.size() > 1 )
			if( dataHeaderList.size() >1){
				this.titleColNum = titleHeaderList.size();
				this.dataColNum	= dataHeaderList.size();
				if( dataList.size()!= (titleColNum+dataColNum))
					result=true;
			}

		List <String> titleCol;
		List <String> dataCol;
		
		//헤더 생성
		HtmlTag tag = new HtmlTag();
		tag.setTagName("thead");
		htmlTag.add(tag);
		tag=new HtmlTag();
		tag.setTagName("TR");
		htmlTag.add(tag);		
		for (int idx=0; idx < titleColNum;idx++){
			tag = new HtmlTag();			
			tag.setTagName("TH");
			tag.setValue(titleHeaderList.get(idx).toString());
			htmlTag.add(tag);	
		}
		for (int idx=0; idx < dataColNum;idx++){
			tag = new HtmlTag();			
			tag.setTagName("TH");
			tag.setCssClass("LHdata");
			tag.setValue(dataHeaderList.get(idx).toString());
			htmlTag.add(tag);			
		}		
		tag = new HtmlTag();			
		tag.setTagName("TH");
		tag.setCssClass("LHSum");		
		htmlTag.add(tag);	
		//해더생성완료
		
		//데이터 로완료
		tag.setTagName("tbody");
		htmlTag.add(tag);
		tag=new HtmlTag();
		tag.setTagName("TR");
		htmlTag.add(tag);
		HtmlTag formerTag;

		List <HtmlTag> formerTagList = new ArrayList<HtmlTag>();
		for (int idx=0; idx < titleColNum+dataColNum;idx++){
			if( formerTagList.get(formerTagList.size())!=null){//전번테그가 있는경우
				formerTag = formerTagList.get(formerTagList.size());
				
				dataList.
				 if(formerTag.get(formerTag.size()).getValue().equals(dataList.get(idx).toString()))//테그값이 같은경우
					 
				 
				 
				formerTag.get(fo)
				tag = new HtmlTag();			
				tag.setTagName("TD");
				if( idx < titleColNum)
					tag.setCssClass("");
				else
					tag.setCssClass("LRdata");
				htmlTag.add(tag);	
				formerTag.add(tag);
			}
		}
		tag = new HtmlTag();			
		tag.setTagName("TD");
		tag.setCssClass("LCSum");		
		htmlTag.add(tag);			
		
		
		return result;
	}
	
	
	
	
	
	public void setDownSumIdx(int downSumIdx){
		if(titleColNum > 2)
		 if( downSumIdx < (titleColNum-2))
			 this.downSumIdx= downSumIdx;
	}	
	public void setRightSumIdx(int rightSumIdx){
		if(titleColNum > 2)
		 if( rightSumIdx < (titleColNum-2))
			 this.rightSumIdx= rightSumIdx;
	}		
	

	
}
