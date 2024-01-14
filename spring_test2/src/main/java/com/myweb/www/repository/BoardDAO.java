package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	void readCount(int bno);

	int delete(int bno);

	int modify(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

	long selectOneBno();

	void updateCommentCount();

	void updateFileCount();

	void readCount(int bno, int cnt);


}
