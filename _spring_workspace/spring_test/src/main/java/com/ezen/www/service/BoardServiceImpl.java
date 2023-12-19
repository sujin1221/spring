package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그
@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(BoardVO bvo) {
		log.info("register service impl");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}

	@Override
	public void read_count(int bno) {
		bdao.readCount(bno);		
	}

	@Override
	public BoardVO getDetail(int bno) {	
		//bdao.updateReadCount(bno);
		return bdao.getDetail(bno);
	}

	@Override
	public void update(BoardVO bvo) {
		bdao.update(bvo);		
	}

	@Override
	public int remove(int bno) {		
		return bdao.delete(bno);
	}

	@Override
	public int getTotalCount() {
		
		return bdao.getTotalCount();
	}


}
