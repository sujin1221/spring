package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	void read_count(int bno);

	BoardDTO getDetail(int bno);

	void update(BoardDTO boardDTO);

	int remove(int bno);

	int getTotalCount(PagingVO pgvo);

	int remove(String uuid);


}
