package com.sujin.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sujin.www.domain.CommentVO;
import com.sujin.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(@Param("bno") long bno,@Param("pgvo") PagingVO pgvo);

	int getTotal(long bno);

}
