package mcr.service;

import mcr.entity.domain.Graph;
import mcr.entity.domain.Node;
import com.baomidou.mybatisplus.extension.service.IService;
import mcr.entity.request.CreateNodeRequest;
import mcr.entity.result.BaseResult;

import java.util.List;

/**
* @author mcr98
* @description 针对表【node】的数据库操作Service
* @createDate 2022-07-07 14:09:47
*/
public interface NodeService extends IService<Node> {

    List<Node> getGraphNodeList(Graph graph);

    BaseResult createNode(CreateNodeRequest request);

    BaseResult updateNode(Node node);

    BaseResult deleteNode(Node node);

}
