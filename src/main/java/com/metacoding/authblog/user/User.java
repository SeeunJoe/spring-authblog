package com.metacoding.authblog.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="user_tb")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id; //자동생성되는...id 자동생성되느라 null값을 넣어야하기 때문에 Integer
    @Column(unique=true,nullable=false) //unique 자주 조회할 때 넣어라 -> unique조건 걸면 index 자동 생성
    private String username; // 내가 생각하는 id
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;



    // 사용자의 권한을 반환한다. -> user의 role..
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 계정이 만료되지 않았는지 확인한다.-> olap....
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }
    // 계정이 잠겨 있지 않은지 확인한다.
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    // 인증 자격 증명(비밀번호 같은...)이 만료되지 않았는지 확인한다.
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    // 계정이 활성화 상태인지 확인한다.
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
