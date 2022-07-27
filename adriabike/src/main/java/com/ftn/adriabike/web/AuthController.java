package com.ftn.adriabike.web;

import com.ftn.adriabike.model.Korisnik;
import com.ftn.adriabike.security.TokenUtils;
import com.ftn.adriabike.service.KorisnikService;
import com.ftn.adriabike.web.dto.KorisnikDTO;
import com.ftn.adriabike.web.dto.LoginDTO;
import com.ftn.adriabike.web.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {


    @Autowired
    KorisnikService korisnikServis;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TokenUtils tokenUtils;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginDTO userDto) {

        Korisnik korisnik = korisnikServis.findByUsername(userDto.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/user-info")
    public ResponseEntity<UserInfoDTO> getKorisnikById(Authentication authentication){
        return ResponseEntity.ok().body(korisnikServis.myInfo(authentication));
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@RequestBody UserInfoDTO userInfoDTO) throws URISyntaxException {
        korisnikServis.register(userInfoDTO);
    }

    @PostMapping("/update")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody UserInfoDTO userInfoDTO, Authentication authentication) throws URISyntaxException {
        korisnikServis.editKorisnik(authentication, userInfoDTO);
    }




}
