package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그
@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO bdao;
    @Inject	
    private FileDAO fdao;

	@Override
	public int register(BoardDTO bdto) {
		log.info("register service impl");
		//기존 board 내용을 db에 저장
		int isOk = bdao.insert(bdto.getBvo());
		//flist를 db에 저장
		if(bdto.getFlist() == null) {
			//파일의 값이 없다면...
			isOk *= 1; //그냥 성공한걸로 처리...
		} else {
			//파일 저장
			if(isOk > 0 && bdto.getFlist().size() > 0) {
				//flist에 값이 있다면...이미 있기에 통과했겠지만 그럼에도...(다시 체크)
				//fvo는 bno 세팅이 설정되기 전임
				//현재 bdto 시점에서는 아직 bno가 생성되기 전임!
				//insert를 통해서 자동생성이 된 것을 db에서 검색 후 가져와야함...
			int bno = bdao.selectBno();
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				//파일 저장
				isOk *= fdao.insertFile(fvo);
			}
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		int isOk = bdao.updateCommentCount();
		if(isOk == 0) {
			log.info("updateCommentCount error");
		}
		int isOkf = bdao.updateFileCount();
		if(isOk == 0) {
			log.info("updateFileCount error");
		}
		return bdao.selectList(pgvo);
	}

	@Override
	public void read_count(int bno) {
		bdao.readCount(bno);		
	}

	@Override
	public BoardDTO getDetail(int bno) {	
		//bdao.updateReadCount(bno);
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBvo(bdao.getDetail(bno)); //게시글 내용 채우기
		boardDTO.setFlist(fdao.getFileList(bno)); //bno에 해당하는 모든 파일 리스트 검색
		return boardDTO;
	}

	@Override
	public void update(BoardDTO bdto) {
		int isOk = bdao.update(bdto.getBvo()); //보드 내용 수정		
		if(bdto.getFlist() == null) {
			isOk *= 1; //이미 처리된것처럼...
		} else {
			if(isOk > 0 && bdto.getFlist().size() > 0) {
				int bno = bdto.getBvo().getBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
	}

	@Override
	public int remove(int bno) {		
		return bdao.delete(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int remove(String uuid) {
		
		return fdao.delete(uuid);
	}


}
