package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	void readCount(int bno);

	BoardVO getDetail(int bno);

	void update(BoardVO bvo);

	int delete(int bno);

	int getTotalCount(PagingVO pgvo);


}
