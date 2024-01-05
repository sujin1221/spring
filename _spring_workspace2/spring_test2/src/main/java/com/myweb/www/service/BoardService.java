package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	Object getDetail(int bno);

	void reatCount(int bno);

	int remove(int bno);


	void modify(BoardVO bvo);

}
