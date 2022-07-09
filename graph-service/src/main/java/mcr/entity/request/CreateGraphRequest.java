package mcr.entity.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateGraphRequest implements Serializable{

    private String name;

    private String description;
}
