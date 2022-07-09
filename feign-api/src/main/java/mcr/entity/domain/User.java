package mcr.entity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class User implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDelete;

    /**
     * 0 - normal user 1 - manager
     */
    private Integer userRole;

    private static final long serialVersionUID = 1L;
}