package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	Object getDetail(int bno);

	void reatCount(int bno);

	int remove(int bno);

	int modify(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

}
