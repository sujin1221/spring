package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<MemberVO> list();

	int remove(String email);

	void authremove(String email);

	void noPwdUpdat(MemberVO mvo);

	void pwdUpdate(MemberVO mvo);

	MemberVO checkEmail(String email);
	
}
