package mcr.controller;

import mcr.entity.domain.User;
import mcr.entity.request.UserLoginRequest;
import mcr.entity.request.UserRegisterRequest;
import mcr.entity.result.BaseResult;
import mcr.service.UserService;
import mcr.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public BaseResult userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return BaseResult.getFailedResult(400, "Username or password cannot be empty");
        }
        String userAccount = userRegisterRequest.getUsername();
        String userPassword = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return BaseResult.getFailedResult(400, "Username or password cannot be empty");
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public BaseResult userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return BaseResult.getFailedResult(400, "Username or password cannot be empty");
        }
        String userAccount = userLoginRequest.getUsername();
        String userPassword = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return BaseResult.getFailedResult(400, "Username or password cannot be empty");
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    @PostMapping("/getUserByToken")
    public BaseResult getUserByToken(@RequestBody String token) {
        return userService.getUserByToken(token);
    }

    @PostMapping("/getUserById")
    public BaseResult getUserById(@RequestBody Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @PostMapping("/userGraphList")
    public BaseResult getUserGraphList(@RequestHeader("token") String token) {
        return userService.getUserGraphList(token);
    }
}
