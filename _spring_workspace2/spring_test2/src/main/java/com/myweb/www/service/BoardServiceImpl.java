package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService{
	private final BoardDAO bdao;

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert service in >>> ");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info("getList service in >>> ");
		return bdao.getList();
	}

	@Override
	public Object getDetail(int bno) {
		log.info("getDetail service in >>> ");
		return bdao.getDetail(bno);
	}

	@Override
	public void reatCount(int bno) {
		bdao.readCount(bno);
	}

	@Override
	public int remove(int bno) {
		log.info("remove service in >>> ");
		return bdao.delete(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		log.info("modify service in >>> ");
		bdao.modify(bvo);		
	}



	
}
