package com.metacoding.authblog.user;

import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO){
        userRepository.save(joinDTO.toEntity(passwordEncoder));
    }

/*    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User userPs = userRepository.findByUsername(loginDTO.getUsername());
        // 걸러내기,,,,
        if(!userPs.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
            // 로그를 남겨서 서버에 패스워드 실패 카운팅
        }
        return userPs;
    }*/




    // POST 요청
    // /login에서 호출됨
    // key값 -> username, password( 필터에서 변경가능하지만 굳이?)
    // content-type 디폴트값인 x-www-form-urlencoded 사용( 필터에서 변경가능하지만 굳이?)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        User user = userRepository.findByUsername(username);
        return user;
    }

}
