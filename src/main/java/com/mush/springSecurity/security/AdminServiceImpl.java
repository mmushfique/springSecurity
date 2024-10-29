//package com.mush.springSecurity.security;
//
//import com.mush.springSecurity.domain.Admin;
//import com.mush.springSecurity.repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class AdminServiceImpl implements UserDetailsService {
//
//	@Autowired
//	private AdminRepository repo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Admin admin=repo.getAdminByUsername(username);
//		if(admin==null) {
//			throw new UsernameNotFoundException("Could not find user");
//		}
//		return new AdminDetails(admin);
//	}
//
//}
