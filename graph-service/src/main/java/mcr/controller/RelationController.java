package mcr.controller;

import mcr.entity.domain.Relation;
import mcr.entity.result.BaseResult;
import mcr.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @PostMapping("/update")
    BaseResult updateRelation(@RequestBody Relation relation) {
        return relationService.updateRelation(relation);
    }
}
