package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	Object getDetail(int bno);

	void readCount(int bno);

	int delete(int bno);

	void modify(BoardVO bvo);

}
