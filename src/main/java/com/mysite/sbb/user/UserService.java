package com.mysite.sbb.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 필드값만 파라미터로 받는 생성자를 자동으로 만들어줌
@Service
public class UserService {
	private final SiteUserRepository siteUserRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	
	private SiteUserDto of(SiteUser siteUser) {
		return this.modelMapper.map(siteUser, SiteUserDto.class);
	}
	
	public SiteUserDto create(String username, String password, String email) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		this.siteUserRepository.save(user);
		return of(user);
		
	}
}
