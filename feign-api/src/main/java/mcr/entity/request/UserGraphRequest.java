package mcr.entity.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserGraphRequest implements Serializable {

    private Long id;
    
    private String username;

    private String searchText;
}
