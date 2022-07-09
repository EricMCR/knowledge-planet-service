package mcr.controller;

import mcr.entity.domain.Graph;
import mcr.entity.request.CreateGraphRequest;
import mcr.entity.result.BaseResult;
import mcr.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private GraphService graphService;

    @PostMapping("/create")
    BaseResult createGraph(@RequestBody CreateGraphRequest createGraphRequest, @RequestHeader("token") String token) {
        return graphService.createGraph(createGraphRequest, token);
    }
}
