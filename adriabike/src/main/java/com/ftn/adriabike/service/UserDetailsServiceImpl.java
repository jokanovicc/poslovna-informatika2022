package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikServis userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Korisnik user = userService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("There is no user with username " + username);
        }else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            String role = "ROLE_" + user.getRoles().toString();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername().trim(),
                    user.getLozinka().trim(),
                    grantedAuthorities);
        }

    }




}
