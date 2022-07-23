package mcr.entity.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateNodeRequest implements Serializable {

    private String name;

    private String content;

    private Long graphId;

    private String source;

    private String relationship;
}
