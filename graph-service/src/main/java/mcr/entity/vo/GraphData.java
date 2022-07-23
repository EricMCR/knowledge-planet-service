package mcr.entity.vo;

import lombok.Data;
import mcr.entity.domain.Node;
import mcr.entity.domain.Relation;

import java.util.List;

@Data
public class GraphData {

    private List<Node> nodes;

    private List<Relation> relations;
}
