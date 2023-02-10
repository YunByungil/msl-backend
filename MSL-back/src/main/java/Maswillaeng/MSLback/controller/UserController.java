package Maswillaeng.MSLback.controller;

import Maswillaeng.MSLback.domain.entity.User;
import Maswillaeng.MSLback.domain.repository.UserRepository;
import Maswillaeng.MSLback.dto.user.reponse.LoginResponseDto;
import Maswillaeng.MSLback.dto.user.reponse.UserLoginResponseDto;
import Maswillaeng.MSLback.dto.user.request.LoginRequestDto;
import Maswillaeng.MSLback.dto.user.request.UserJoinDto;
import Maswillaeng.MSLback.dto.user.request.UserUpdateRequestDto;
import Maswillaeng.MSLback.jwt.JwtTokenProvider;
import Maswillaeng.MSLback.service.UserService;
import Maswillaeng.MSLback.utils.auth.AuthCheck;
import Maswillaeng.MSLback.utils.auth.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;









    @AuthCheck(role = AuthCheck.Role.USER)
    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo() {
        return ResponseEntity.ok().body(
                userService.getUser(UserContext.userId.get()));
    }



    @AuthCheck(role = AuthCheck.Role.USER)
    @PutMapping("/user")
    public ResponseEntity<Object> updateUserInfo(
            @RequestBody @Valid UserUpdateRequestDto requestDto) {
        if (requestDto.getPassword() == null && requestDto.getNickName() == null) {
            return ResponseEntity.badRequest().build();
        }
        userService.updateUser(UserContext.userId.get(), requestDto);
        return ResponseEntity.ok().build();
    }

    @AuthCheck(role = AuthCheck.Role.USER)
    @DeleteMapping("/user")
    public ResponseEntity<Object> userWithDraw() {
        userService.userWithdraw(UserContext.userId.get());
        return ResponseEntity.ok().build();
    }
}
