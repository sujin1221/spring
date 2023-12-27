package com.ezen.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	@Inject
	private BoardService bsv;
	
	@Inject
	private FileHandler fhd;
	
	//경로와 리턴의 값이 같을 경우 생략 가능
	// /board/register => /board/register
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")	
	//@RequestParam("name") String name => 파라미터 보낼때는 이런식으로 
	//required: 필수 여부, false를 주면 파라미터가 없어도 예외발생 x
	public String register(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>> bvo >> {} ", bvo);
		log.info(">>> files >> {}",files.toString());
				
	//파일 핸들러 처리
	List<FileVO> flist = null;
	
	//파일이 있을 경우에만 fhd 호출
	if(files[0].getSize() > 0) {
		//0번지에 값이 있다면
		flist = fhd.uploadFiles(files); 
		log.info(">>> flist >> {} ", flist);
	} else {
		log.info("file null");
	}
	BoardDTO bdto = new BoardDTO(bvo, flist);
	
	int isOk = bsv.register(bdto);
	log.info("board register >>> {} "+(isOk > 0 ? "ok":"fail"));
	//리스트로 갔다가 리스트 로직 함 타고 다시 ㄱㄱ
	return "redirect:/board/list"; //목적지 경로
	}
	
	// /board/list => /board/list
	//void 처리해도 상관없음!
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		log.info(">>> pgvo >> {} ", pgvo); //pageNo, qty, type, keyword
		
		//리턴타입은 목적지 경로에 대한 타입 (destPage가 리턴이라고 생각)
	    //Model 객체 => setAttribute 역할을 하는 객체
		m.addAttribute("list", bsv.getList(pgvo));
		//pagingHandler 객체 다시 생성
		//pgvo, totalCount
		int totalCount = bsv.getTotalCount(pgvo);
		log.info("totalCount >>> {} ",totalCount);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("ph", ph);
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("bno") int bno) {
		log.info(">>> bno >>> {} "+bno);
		//파일 내용도 포함해서 같이 보내기
		
		m.addAttribute("boardDTO", bsv.getDetail(bno));
		bsv.read_count(bno);
	}
	@PostMapping("/modify") 
	public String modify(BoardVO bvo) {
	 log.info(">>>>> bvo >>> {} "+bvo);	
	//update
	bsv.update(bvo);
	//m.addAttribute("bno",bvo.getBno()); => model 객체 가져와서
	 return "redirect:/board/detail?bno="+bvo.getBno(); //bno가 필요함	 
	}
	@GetMapping("/remove") //변수명보다...이게 중요한거임...맞게 잘 적어줘야함...
	public String remove(@RequestParam("bno") int bno, RedirectAttributes re) {
		log.info(">>>>> bno >> {} "+bno);
	int isOk = bsv.remove(bno);
	//페이지가 새로고침 될 때 남아있을 필요가 없는 데이터
	//리다이렉트 될 때 데이터를 보내는 객체(redirectAttribute)
	//일회성으로 데이터를 보낼때 사용 => addFlashAttribute
	re.addFlashAttribute("isDel",isOk);
	return "redirect:/board/list"; 
	}	
	
	@DeleteMapping(value="/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable("uuid") String uuid) {
		log.info("delete file id >>> {} ", uuid);
		int isOk = bsv.remove(uuid);
		return isOk > 0 ? new ResponseEntity<String>("1",HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}
