package com.sujin.www.repository;

import java.util.List;

import com.sujin.www.domain.BoardVO;
import com.sujin.www.domain.PagingVO;

public interface BoardDAO {

	void insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	Object getDetail(int bno);

	void readCount(int bno);

	void delete(int bno);

	void modify(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

}
