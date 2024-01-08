package com.sujin.www.handler;

import java.util.List;

import com.sujin.www.domain.CommentVO;
import com.sujin.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {
	private int startPage; //하단 페이지네이션의 시작번호
	private int endPage; //하단 페이지네이션의 끝번호
	private boolean prev, next;
	
	private int totalCount; //총 게시글 수
	private PagingVO pgvo;
	private List<CommentVO> cmtList;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = (int)Math.ceil(pgvo.getPageNo()
				/(double)pgvo.getQty())*pgvo.getQty();
		this.startPage = endPage-9;

		int realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty()); 
		if(realEndPage<endPage) {
			this.endPage = realEndPage;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
		this(pgvo, totalCount);
        this.cmtList = cmtList;
	}	
}
