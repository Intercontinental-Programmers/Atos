package com.ip.services;

import com.ip.data.AppUserRepository;
import com.ip.domain.AppUser;
import com.ip.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private AppUserRepository appUserRepository;

	public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findByUsername(username);
		if (appUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(appUser.getUsername(), appUser.getPassword(), getGrantedAuthorities(appUser.getRoles()));
	}



	private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {

		return roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}
}
