package com.sujin.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sujin.www.domain.CommentVO;
import com.sujin.www.domain.PagingVO;
import com.sujin.www.handler.PagingHandler;
import com.sujin.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentServie{
	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(long bno, PagingVO pgvo) {
		// TODO Auto-generated method stub
		return cdao.getList(bno, pgvo);
	}

	@Override
	public int getTotal(long bno) {
		// TODO Auto-generated method stub
		return cdao.getTotal(bno);
	}
}
