package mcr.service;

import mcr.entity.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import mcr.result.BaseResult;

import javax.servlet.http.HttpServletRequest;

/**
* @author mcr98
* @description Database operation Service for table [user]
* @createDate 2022-06-29 22:35:16
*/
public interface UserService extends IService<User> {

    /**
     * User register
     *
     * @param username
     * @param password
     * @param checkPassword
     * @return
     */
    BaseResult userRegister(String username, String password, String checkPassword);

    /**
     * User login
     *
     * @param username
     * @param password
     * @return Return desensitised user information
     */
    BaseResult userLogin(String username, String password, HttpServletRequest request);
}
