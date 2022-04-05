//package com.panandafog.mt_server.service;
//
//import com.panandafog.mt_server.entity.MTUserDetails;
//import com.panandafog.mt_server.entity.User;
//import com.panandafog.mt_server.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class MTUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UsersRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new MTUserDetails(user);
//    }
//}
