package com.storeapi.controllers;

import com.storeapi.dtos.JwtResponse;
import com.storeapi.dtos.LoginRequest;
import com.storeapi.dtos.UserDto;
import com.storeapi.mappers.UserMapper;
import com.storeapi.repositories.UserRepository;
import com.storeapi.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController @RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private final UserRepository userRepository;
    private UserMapper userMapper;

    @PostMapping("validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader){
        var token = authHeader.replace("Bearer ", "");
        return jwtService.validateToken(token);
    }


    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest request){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               request.getEmail(), request.getPassword()
       ));

       var token = jwtService.generateToken(request.getEmail());

       return ResponseEntity.ok(new JwtResponse(token));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @RequestMapping("/me")
    public ResponseEntity<UserDto> me(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = (String) authentication.getPrincipal();

        var user = userRepository.findByEmail(email).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();

        var userDto = userMapper.toDto(user);

        return ResponseEntity.ok(userDto);
    }



}
