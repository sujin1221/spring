package com.ezen.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	@Inject
	private MemberService msv;
	@GetMapping("/register")
	public void regiter() {}
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info(">>>>> mvo >> {} ",mvo);
		int isOk = msv.signUp(mvo);
		log.info(">>>>> signUp ? "+(isOk > 0 ? "Ok":"Fail"));
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	
	@PostMapping("/login")
	public String login(MemberVO mvo, Model m,HttpServletRequest request) {
		log.info(">>>>> mvo >> {} ", mvo);
		//mvo 객체가 db 값과 일치하는지 체크
		MemberVO loginMvo = msv.isUser(mvo);
		if(loginMvo != null) {
			//로그인 성공시
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginMvo); //session에 로그인 객체 저장
			ses.setMaxInactiveInterval(60*10); //로그인 유지 시간
		} else {
			//로그인 실패시 
			m.addAttribute("msg_login", "1");
		}
		return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.lastLogin(mvo.getId());
		//Session 객체 삭제 => 세션 끊기
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();	
		m.addAttribute("msg_logout", "1"); //이 메세지를... 
		return "index"; //리턴 객체가 가는 곳으로 보내줌		
	}	
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, RedirectAttributes re) {
		log.info(">>>>> mvo >> {} "+mvo);
		int isOk = msv.modify(mvo);
		log.info(">>>>> modify? >> "+(isOk > 0 ? "OK":"FAIL"));
		re.addFlashAttribute("msg_modify", isOk);
		return "redirect:/member/logout";
		}
	@GetMapping("/remove")
	public String remove(RedirectAttributes re, HttpServletRequest request) {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		int isOk = msv.remove(mvo.getId()); //id만 가져와서...
		re.addFlashAttribute("msg_remove", isOk); //메세지 출력...		
		return "redirect:/member/logout"; //로그아웃으로 가자...
	}
	}