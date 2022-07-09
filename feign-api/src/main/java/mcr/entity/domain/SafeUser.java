package mcr.entity.domain;

import lombok.Data;

import java.util.Date;

/**
 * Desensitised user object
 *
 * @author mcr
 */
@Data
public class SafeUser {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     *
     */
    private Date createTime;

    /**
     * user role: 0 - normal user, 1 - manager
     */
    private Integer userRole;

    public SafeUser(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setCreateTime(user.getCreateTime());
        this.setUserRole(user.getUserRole());
    }
}
