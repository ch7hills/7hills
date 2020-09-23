package aia.cs.ms.jwt.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aia.cs.ms.jwt.controller.LoginController;
import aia.cs.ms.jwt.entities.Privilege;
import aia.cs.ms.jwt.entities.Role;
import aia.cs.ms.jwt.entities.User;
import aia.cs.ms.jwt.repositories.RoleRepository;
import aia.cs.ms.jwt.repositories.UserRepository;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	private Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messages;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("loadUserByUsername:{}",email);
		System.out.println(email);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true,
					getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
		}
		log.info("loadUserByUsername:{}",user);
		System.out.println(user);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
