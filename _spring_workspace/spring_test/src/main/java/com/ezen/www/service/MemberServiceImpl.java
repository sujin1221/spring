package com.ezen.www.service;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO mdao;
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	@Inject
	HttpServletRequest request;

	@Override
	public int signUp(MemberVO mvo) {
		//id가 중복되면 회원가입 실패가 뜨도록 설정
		// => id만 주고 db에서 일치하는 mvo 객체를 리턴
		// 일치하는 유저가 있다면 가입실패, 없다면 가입 가능
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		if(tempMvo != null) {
			//기존 id가 있는 경우
			return 0;
		}
		//id가 중복되지 않는다면 회원가입을 진행
		//password가 null or 값이 존재하지 않는다면 가입불가
		if(mvo.getId() == null || mvo.getId().length() == 0) {
			return 0;
		}
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return 0;
		}
		//회원가입 진행
		//password는 암호화하여 가입
		//암호화(encode) / matches(입력된 비번, 암호화된 비번) => true / false
		String pw = mvo.getPw();
		String encodePw = passwordEncoder.encode(pw); //pw 암호화
		mvo.setPw(encodePw);
		//회원가입
		int isOk = mdao.insert(mvo);
		return isOk;
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		//login 유저 확인
		//id를 주고 해당 id의 객체를 리턴
		MemberVO tempMvo = mdao.getUser(mvo.getId()); //회원가입할때 했던 메서드를 호출함
		//해당 아이디가 없는 경우
		if(tempMvo == null) {
			return null;
		}
		//matches(원래비번, 암호화된 비번) 비교
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {
			return tempMvo;
		}
		return null;
	}

	@Override
	public void lastLogin(String id) {
		mdao.lastLogin(id);
	}

	@Override
	public int modify(MemberVO mvo) {
		//PW 여부에 따라서 변경사항을 나누어 처리
		//PWrk 없다면 기존값으로 세팅, 있다면 암호화 처리하여 수정
	if(mvo.getPw() == null || mvo.getPw().length() == 0) {
		MemberVO sesMvo = (MemberVO)request.getSession().getAttribute("ses");
		mvo.setPw(sesMvo.getPw());
	} else {
		//값이 있다면..
		String setPw = passwordEncoder.encode(mvo.getPw());
		mvo.setPw(setPw);
	}
	log.info(">>> pw 수정 후 mvo >> {} ", mvo);
	return mdao.update(mvo);
	}

	@Override
	public int remove(String id) {
		return mdao.delete(id);
	}
}
