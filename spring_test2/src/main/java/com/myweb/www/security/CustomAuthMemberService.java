package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.repository.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthMemberService implements UserDetailsService {
	//컨트롤러로 안가고 바로 여기로 옴	
	
	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//userName DB에 설정되어 있는 이메일이 맞는지 체크
		//인증한 후 해당 객체를 authMember로 리턴
		MemberVO mvo = mdao.selectEmail(username); //username=email
		if(mvo == null) {
			//해당 유저 => 등록되지 않은 유저
			throw new UsernameNotFoundException(username);
		}
		mvo.setAuthList(mdao.selectAuths(username));
		log.info(">> userDetails >>> {} ", mvo);
		return new AuthMember(mvo);
	}

}
