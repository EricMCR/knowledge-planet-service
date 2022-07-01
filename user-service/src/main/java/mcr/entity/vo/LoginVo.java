package mcr.entity.vo;

import lombok.Data;
import mcr.entity.domain.SafeUser;

@Data
public class LoginVo {

    /**
     * Desensitised user object
     */
    private SafeUser userInfo;

    /**
     * User's Token
     */
    private String token;

    public LoginVo(SafeUser safeUser, String token) {
        this.setUserInfo(safeUser);
        this.setToken(token);
    }
}
