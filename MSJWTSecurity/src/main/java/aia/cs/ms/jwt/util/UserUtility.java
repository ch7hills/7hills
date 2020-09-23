package aia.cs.ms.jwt.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import aia.cs.ms.jwt.entities.User;
import aia.cs.ms.jwt.repositories.RoleRepository;
import aia.cs.ms.jwt.repositories.UserRepository;

@Service
public class UserUtility {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepository;
	
	public User findUserByToken(HttpServletRequest request) {
    	final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if(null != username) {
        	return userRepository.findByEmail(username);
        }
        return null;
    }
}
