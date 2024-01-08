package com.sujin.www.service;

import java.util.List;

import com.sujin.www.domain.BoardVO;
import com.sujin.www.domain.PagingVO;

public interface BoardService {

	void insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	Object getDetail(int bno);

	void readCount(int bno);

	void remove(int bno);

	void modify(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

}
