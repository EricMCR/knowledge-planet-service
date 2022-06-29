package mcr.entity.vo;

import lombok.Data;
import mcr.entity.domain.SafeUser;

@Data
public class LoginVo {

    /**
     * 脱敏后的用户对象
     */
    private SafeUser userInfo;

    /**
     * 用户Token
     */
    private String token;

    public LoginVo(SafeUser safeUser, String token) {
        this.setUserInfo(safeUser);
        this.setToken(token);
    }
}
