package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	void read_count(int bno);

	BoardVO getDetail(int bno);

	void update(BoardVO bvo);

	int remove(int bno);

	int getTotalCount();


}
