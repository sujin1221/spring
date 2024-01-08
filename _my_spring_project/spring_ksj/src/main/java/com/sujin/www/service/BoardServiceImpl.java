package com.sujin.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sujin.www.domain.BoardVO;
import com.sujin.www.domain.PagingVO;
import com.sujin.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService{
	private final BoardDAO bdao;

	@Override
	public void insert(BoardVO bvo) {
		log.info("insert service in >>> ");
		bdao.insert(bvo);
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list service in >>> ");
		return bdao.getList(pgvo);
	}

	@Override
	public Object getDetail(int bno) {
		log.info("detail service in >>> ");
		return bdao.getDetail(bno);
	}

	@Override
	public void readCount(int bno) {
		log.info("readCount service in >>> ");
		bdao.readCount(bno);
	}

	@Override
	public void remove(int bno) {
		log.info("remove service in >>> ");
		bdao.delete(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		log.info("modify service in >>> ");
		bdao.modify(bvo);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("getTotalCount service in >>> ");
		return bdao.getTotalCount(pgvo);
	}
}
