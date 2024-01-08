package com.sujin.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingVO {
	private int pageNo; //현재 페이지번호
	private int qty; //한 페이지당 표시될 리스트 개수
	
	private String type;
	private String keyword;
	
	//기본세팅
	public PagingVO() {
		this.pageNo = 1; //1페이지부터 시작
		this.qty = 10; //한페이지에 10개씩
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	//시작 번지
	public int getPageStart() {
		return (this.pageNo-1)*qty; //0번부터 시작해서...
	}
	
	//type
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	
	
	
}
