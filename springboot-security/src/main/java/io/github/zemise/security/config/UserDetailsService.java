//package io.github.zemise.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public interface UserDetailsService {
//    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
//
////    @Bean
////    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
////        List<UserDetails> usersList = new ArrayList<>();
////
////        usersList.add(new User("buzz",
////                passwordEncoder.encode("password"),
////                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
////        ));
////
////        usersList.add(new User("woody",
////                passwordEncoder.encode("password"),
////                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
////        ));
////
////        return new InMemoryUserDetailsManager(usersList);
////    }
//}
