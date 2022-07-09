package mcr.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName relation
 */
@TableName(value ="relation")
@Data
public class Relation implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "node1_id")
    private Long node1Id;

    /**
     * 
     */
    @TableField(value = "node2_id")
    private Long node2Id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}