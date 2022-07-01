package mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mcr.entity.domain.SafeUser;
import mcr.entity.domain.User;
import mcr.entity.vo.LoginVo;
import mcr.result.BaseResult;
import mcr.service.UserService;
import mcr.mapper.UserMapper;
import mcr.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author mcr98
* @description Service implementation of database operations on table [user]
* @createDate 2022-06-29 22:35:16
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * Salt values for obfuscated passwords
     */
    private static final String SALT = "machaoran";

    @Override
    public BaseResult userRegister(String username, String password, String checkPassword) {
        // Validation
        if (StringUtils.isAnyBlank(username, password, checkPassword)) {
            return BaseResult.getFailedResult(400, "Username or password cannot be empty");
        }
        if (username.length() < 4) {
            return BaseResult.getFailedResult(400, "The length of the username must not be less than 4 digits");
        }
        if (password.length() < 8 || checkPassword.length() < 8) {
            return BaseResult.getFailedResult(400, "The length of the password cannot be less than 8 digits");
        }
        // Username cannot contain special characters
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            return BaseResult.getFailedResult(400, "Username cannot contain special characters");
        }
        // Same password and verification password
        if (!password.equals(checkPassword)) {
            return BaseResult.getFailedResult(400, "The password entered twice does not match");
        }
        // No duplication of usernames
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        long count = this.count(queryWrapper);
        if (count > 0) {
            return BaseResult.getFailedResult(400, "This username has been registered");
        }
        // Encrypted passwords
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // Insert data
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptedPassword);
        this.save(user);
        return BaseResult.getSuccessResult();
    }

    @Override
    public BaseResult userLogin(String username, String password, HttpServletRequest request) {
        // Validation
        if (StringUtils.isAnyBlank(username, password)) {
            return BaseResult.getFailedResult(400, "Username or password cannot be empty");
        }
        if (username.length() < 4) {
            return BaseResult.getFailedResult(400, "The length of the username must not be less than 4 digits");
        }
        if (password.length() < 8) {
            return BaseResult.getFailedResult(400, "The length of the password cannot be less than 8 digits");
        }
        // Username cannot contain special characters
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            return BaseResult.getFailedResult(400, "Username cannot contain special characters");
        }
        // Encrypted passwords
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // Check if the username already exists
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", encryptedPassword);
        User user = userMapper.selectOne(queryWrapper);
        // Incorrect username or password
        if (user == null) {
            return BaseResult.getFailedResult(400, "Incorrect username or password");
        }

        // Generate desensitised user objects
        SafeUser safeUser = new SafeUser(user);

        // Generate token
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        map.put("username", user.getUsername());
        map.put("userRole", String.valueOf(user.getUserRole()));
        String token = JWTUtils.getToken(map);

        // Generate login information object
        LoginVo loginVo = new LoginVo(safeUser, token);
        return BaseResult.getSuccessResult(loginVo);
    }
}




