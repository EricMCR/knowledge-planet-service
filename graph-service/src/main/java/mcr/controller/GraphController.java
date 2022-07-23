package mcr.controller;

import mcr.entity.domain.Graph;
import mcr.entity.domain.Node;
import mcr.entity.domain.Relation;
import mcr.entity.request.CreateGraphRequest;
import mcr.entity.result.BaseResult;
import mcr.entity.vo.GraphData;
import mcr.service.GraphService;
import mcr.service.NodeService;
import mcr.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private GraphService graphService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private RelationService relationService;

    @PostMapping("/create")
    BaseResult createGraph(@RequestBody CreateGraphRequest createGraphRequest, @RequestHeader("token") String token) {
        return graphService.createGraph(createGraphRequest, token);
    }

    @PostMapping("/update")
    BaseResult updateGraph(@RequestBody Graph graphForm, @RequestHeader("token") String token) {
        return graphService.updateGraph(graphForm, token);
    }

    @PostMapping("/getGraphById")
    BaseResult getGraphById(@RequestBody Graph graph) {
        return graphService.getGraphById(graph.getId());
    }

    @PostMapping("/getGraphData")
    BaseResult getGraphData(@RequestBody Graph graph) {
        List<Node> nodeList = nodeService.getGraphNodeList(graph);
        List<Relation> relationList = relationService.getGraphRelationList(graph);
        GraphData graphData = new GraphData();
        graphData.setNodes(nodeList);
        graphData.setRelations(relationList);
        return BaseResult.getSuccessResult(graphData);
    }

    @PostMapping("/popularGraphList")
    BaseResult popularGraphList() {
        return graphService.popularGraphList();
    }
}
