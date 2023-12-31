package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		
		return cdao.insert(cvo);
	}

	@Transactional //lock...해당 시점에서 값이 일정하게 유지되도록...
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		//comment list로 setting
		//ph 객체 안에 cmtList를 구성해야함
		//totalCount도 구해와야함
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		List<CommentVO> list = cdao.getList(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}

	@Override
	public int modify(CommentVO cvo) {
		
		return cdao.update(cvo);
	}
}
