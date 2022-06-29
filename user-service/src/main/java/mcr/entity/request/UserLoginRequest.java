package mcr.entity.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -3751961188233949230L;

    private String username;

    private String password;
}
