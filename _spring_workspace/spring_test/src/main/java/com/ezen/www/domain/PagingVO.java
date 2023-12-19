package com.ezen.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingVO {
private int pageNo; //현재 페이지 번호
private int qty; //한 화면에 보여줄 게시글 수 (10개)

public PagingVO() {
	this.pageNo = 1;
	this.qty = 10;
}
public PagingVO(int pageNo, int qty) {
	this.pageNo = pageNo;
	this.qty = qty;
}
public int getPageStart() {
	//db상에서 limit의 시작값을 구하는 메서드
	return (this.pageNo-1)*this.qty;
}
}
