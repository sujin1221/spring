package com.ezen.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	@Inject
	private CommentService csv;
	
	//ResponseEntity 객체 사용: body 내용 + httpStatus 상태
	//@RequestBody: body값 추출하는 역할
	//consumes: 가져오는 데이터의 형태인지 적어주면 됨
	//produces: 보내는 데이터 형식 / 나가는 데이터 타입 => 반드시 MediaType이어야함
	//json: aplication/json / text: text_plain
	//produces = MediaType => spring (tomcat x)
	@PostMapping(value="/post", consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info(">> cvo >>>> {} ", cvo);
		int isOk = csv.post(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1",HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@GetMapping(value="/list/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") int bno){
		log.info(">>>> bno >> {} "+bno);
		List<CommentVO> list = csv.getList(bno);
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	//'댓글 삭제' 성공 여부 => HTTP 응답을 반환
	public ResponseEntity<String> delete(@PathVariable("cno") int cno) {
		log.info("delete cno >>> {} ", cno); //삭제 할 번호 출력
		//특정 댓글을 찾아서 그 댓글을 삭제함
		int isOk = csv.remove(cno); //실제로 대화를 지우는 역할을 여기서 수행
		//성공적으로 수행되면 1을 출력, 아니라면 0을 출력함
		//HttpStatus.OK => HTTP 상태 코드 200을 나타냄
		return isOk > 0 ? new ResponseEntity<String>("1",HttpStatus.OK) :
			//수행이 실패될 시 0을 출력, 에러 500을 나타냄
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/modify", consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody CommentVO cvo){
		log.info(">> cvo >>>> {} ", cvo);
		int isOk = csv.update(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1",HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

