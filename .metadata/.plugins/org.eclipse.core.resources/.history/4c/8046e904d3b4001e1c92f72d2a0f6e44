package com.myweb.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/member/*")
@Slf4j
@Controller
public class MemberController {
	private final MemberService msv;
	private final BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/register")
	private void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		log.info(">> register >>> mvo >> {}", mvo);
		int isOk = msv.register(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		//로그인 실패시 다시 로그인 페이지로 돌아와 오류 메세지 전송하도록
		//다시 로그인하도록 유도
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errorMsg"));
		return "redirect:/member/login";
	}
	
	//@RequestParam("email") String email => 쿼리스트링(파라미터 받기)
	@GetMapping("/modify")
	public void modify(Principal p, Model m) {
		log.info(">> principal >>> email >>> {} ",p.getName()); //내 이메일 나오는지 확인
		String email = p.getName();
		m.addAttribute("mvo",msv.detail(email));
	}
	
	@GetMapping("/list")
	public String memberList(Model m) {
		List<MemberVO> list = msv.list();
		m.addAttribute("list", list);
		return "/member/list";
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes re, @RequestParam("email") String email) {
	int isOk = msv.remove(email);
	log.info(isOk>0 ? "ok":"fail");
	return "/"; 
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response) {
		if(mvo.getPwd().isEmpty()) {
			//비번없는 업데이트 진행
			msv.noPwdUpdate(mvo);
		} else {
			//비번이 있다면 비번을 다시 세팅해서 인코딩해서 업데이트 진행
			mvo.setPwd(bcEncoder.encode(mvo.getPwd())); //세팅 => 인코딩
			msv.pwdUpdate(mvo);	//업데이트	
		}
		//로그아웃 진행
			logout(request, response);
		return "/member/login";
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication 
		= SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler()
		.logout(request, response, authentication);
	}

	
		
}
