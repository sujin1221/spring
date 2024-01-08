package com.sujin.www.controller;

import java.util.List;

import javax.print.attribute.standard.MediaTray;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sujin.www.domain.CommentVO;
import com.sujin.www.domain.PagingVO;
import com.sujin.www.handler.PagingHandler;
import com.sujin.www.service.CommentServie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	private final CommentServie csv;
	
	@PostMapping(value="/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info(">> post cvo >>> {} ", cvo);
		int isOk = csv.post(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1",HttpStatus.OK) :
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagingHandler> list(@PathVariable("bno") long bno
            ,@PathVariable("page") int page){
        log.info(">>>bno>>> {}", bno);
        log.info(">>>page>>> {}", page);
        PagingVO pgvo = new PagingVO(page,5);
        List<CommentVO> list = csv.getList(bno,pgvo);
        int totalcount = csv.getTotal(bno);
        PagingHandler pghr = new PagingHandler(pgvo,totalcount,list);
        log.info(">>pght>>{}", pghr);
        return new ResponseEntity<PagingHandler>(pghr, HttpStatus.OK);
    }
}
