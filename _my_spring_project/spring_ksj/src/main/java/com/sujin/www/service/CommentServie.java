package com.sujin.www.service;

import java.util.List;

import com.sujin.www.domain.CommentVO;
import com.sujin.www.domain.PagingVO;
import com.sujin.www.handler.PagingHandler;

public interface CommentServie {
	int post(CommentVO cvo);
	List<CommentVO> getList(long bno, PagingVO pgvo);
	int getTotal(long bno);
}
