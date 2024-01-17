package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	MemberVO detail(String email);

	List<MemberVO> list();

	int remove(String email);

	void noPwdUpdate(MemberVO mvo);

	void pwdUpdate(MemberVO mvo);

	MemberVO checkEmail(String email);

}
