package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService{
	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		log.info("insert service in >>> ");
		//bvo -> boardMapper, flist -> fileMapper에 등록
		int isOk = bdao.insert(bdto.getBvo());
		if(bdto.getFlist() == null) {
			return isOk;
		}
		//bvo insert 후 파일도 있다면...?
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			//bno setting
			long bno = bdao.selectOneBno(); //가장 마지막에 등록된 bno를 가져옴
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk = fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("getList service in >>> ");
		return bdao.getList(pgvo);
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
	public int modify(BoardVO bvo) {
		log.info("modify service in >>> ");
		 return bdao.modify(bvo);		
	}


	
	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("getTotalCount service in >>> ");
		 return bdao.getTotalCount(pgvo);		
	}
		
}
