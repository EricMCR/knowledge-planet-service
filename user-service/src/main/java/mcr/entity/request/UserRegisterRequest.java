package mcr.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * User Registration Request Object
 *
 * @author mcr
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -7969466361696586245L;

    private String username;

    private String password;

    private String checkPassword;
}
