package com.sujin.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sujin.www.domain.BoardVO;
import com.sujin.www.domain.PagingVO;
import com.sujin.www.handler.PagingHandler;
import com.sujin.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller

//작성,수정 => post 목록,상세,삭제 => get
public class BoardController {	
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	//글작성
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info("register bvo >>> {} ", bvo);
		bsv.insert(bvo);
		return "index";
	}
	
	//글목록
	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		log.info(">> pgvo >>> {} ", pgvo);
		//페이징 처리 시작-!
		List<BoardVO> list = bsv.getList(pgvo);
		//총 페이지 수
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list",list);
		m.addAttribute("ph", ph);
	}
	
	//글수정, 상세페이지
	@GetMapping({"/modify","/detail"})
	public void detail(Model m, @RequestParam("bno") int bno) {
		log.info("detail bno >>> {} ", bno);
		m.addAttribute("bvo", bsv.getDetail(bno));
		bsv.readCount(bno);
	}
	
	//글삭제
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") int bno) {
		log.info("remove bno >>> {} ", bno);
		bsv.remove(bno);
		return "redirect:/board/list";
	}
	
	//글수정
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		log.info("modify bvo >>> {} ", bvo);
		bsv.modify(bvo);
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
		
}