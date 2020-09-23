package aia.cs.ms.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aia.cs.ms.jwt.entities.User;
import aia.cs.ms.jwt.models.AuthenticationRequest;
import aia.cs.ms.jwt.models.AuthenticationResponse;
import aia.cs.ms.jwt.services.MyUserDetailsService;
import aia.cs.ms.jwt.util.JwtUtil;
import aia.cs.ms.jwt.util.UserUtility;

@RestController
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtTokenUtil;
	
	@Autowired
	UserUtility userUtility;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			System.out.println(" authenticationRequest :" + authenticationRequest);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@/n" + e.getMessage());
			//System.out.println(e);
			log.error("Exception:{}",e);
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(HttpServletRequest request) {
		log.info("authenticateUser HttpServletRequest:",request);
		
		User user = userUtility.findUserByToken(request);
		log.info("authenticatedUser user:",user);
		return ResponseEntity.ok(user);
	}
	

}
