package com.web.drainInfo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.drainInfo.domain.User;

import lombok.Data;

@Data
public class PrinsipalDetails implements UserDetails {
	private User user;
	
	public PrinsipalDetails(User user) {
		this.user = user;
	}
	//계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을수있어서 루프를 돌아야 하는데  우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		user.getUserRoles().forEach(role -> {
			grantedAuthorities.add(() -> role); //다음주 role이 아니고 변수를 왜 함수로 받는지
		});
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getId();
	}
	//계정이 만료되지 않았는지 리턴한다. ( true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 감져있지 않았는지 리턴한다. ( true : 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴한다. ( true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화(사용가능)인지 리턴한다. ( true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

}
