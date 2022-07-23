package mcr.controller;

import mcr.entity.domain.Node;
import mcr.entity.request.CreateNodeRequest;
import mcr.entity.result.BaseResult;
import mcr.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @PostMapping("/create")
    BaseResult createNode(@RequestBody CreateNodeRequest request) {
        return nodeService.createNode(request);
    }

    @PostMapping("/update")
    BaseResult updateNode(@RequestBody Node node) {
        return nodeService.updateNode(node);
    }

    @PostMapping("/delete")
    BaseResult deleteNode(@RequestBody Node node) {
        return nodeService.deleteNode(node);
    }
}
