package mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import mcr.entity.domain.Graph;
import mcr.entity.domain.Node;
import mcr.entity.domain.Relation;
import mcr.entity.request.CreateNodeRequest;
import mcr.entity.result.BaseResult;
import mcr.service.NodeService;
import mcr.mapper.NodeMapper;
import mcr.service.RelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mcr98
* @description 针对表【node】的数据库操作Service实现
* @createDate 2022-07-07 14:09:47
*/
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node>
    implements NodeService{

    @Autowired
    private RelationService relationService;

    @Override
    public List<Node> getGraphNodeList(Graph graph) {
        Long graphId = graph.getId();
        QueryWrapper<Node> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("graph_id", graphId);
        List<Node> nodeList = this.list(queryWrapper);

        return nodeList;
    }

    @Override
    public BaseResult createNode(CreateNodeRequest request) {
        Node node = new Node();
        node.setName(request.getName());
        node.setContent(request.getContent());
        node.setGraphId(request.getGraphId());

        if (!this.save(node)) {
            return BaseResult.getFailedResult(403, "Node create failed");
        }

        if (StringUtils.isNotBlank(request.getSource())) {
            Relation relation = new Relation();
            relation.setGraphId(request.getGraphId());
            relation.setSource(request.getSource());
            relation.setTarget(String.valueOf(node.getId()));
            relation.setRelationship(request.getRelationship());
            if (!relationService.save(relation)) {
                return BaseResult.getFailedResult(403, "Relation create failed");
            }
        }

        return BaseResult.getSuccessResult();
    }

    @Override
    public BaseResult updateNode(Node node) {
        if (StringUtils.isBlank(node.getName())) {
            return BaseResult.getFailedResult(403, "Name can not be empty");
        }
        if (this.saveOrUpdate(node)) {
            return BaseResult.getSuccessResult(node);
        }

        return BaseResult.getFailedResult();
    }

    @Override
    public BaseResult deleteNode(Node node) {
        if (this.removeById(node)) {
            return BaseResult.getSuccessResult();
        }
        return BaseResult.getFailedResult();
    }
}




