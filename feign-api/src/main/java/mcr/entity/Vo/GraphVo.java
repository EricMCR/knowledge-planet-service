package mcr.entity.Vo;

import lombok.Data;
import mcr.entity.domain.Graph;

import java.util.Date;

@Data
public class GraphVo {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long userId;

    private String username;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Integer isDelete;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Integer views;

    public GraphVo(Graph graph, String username) {
        this.id = graph.getId();
        this.userId = graph.getUserId();
        this.username = username;
        this.name = graph.getName();
        this.description = graph.getDescription();
        this.updateTime = graph.getUpdateTime();
        this.isDelete = graph.getIsDelete();
        this.createTime = graph.getCreateTime();
        this.views = graph.getViews();

    }
}
