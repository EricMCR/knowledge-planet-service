package mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mcr.entity.domain.Graph;
import mcr.entity.domain.Node;
import mcr.entity.domain.Relation;
import mcr.entity.result.BaseResult;
import mcr.service.RelationService;
import mcr.mapper.RelationMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mcr98
* @description 针对表【relation】的数据库操作Service实现
* @createDate 2022-07-07 14:09:47
*/
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation>
    implements RelationService {

    @Override
    public List<Relation> getGraphRelationList(Graph graph) {
        Long graphId = graph.getId();
        QueryWrapper<Relation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("graph_id", graphId);
        List<Relation> relationList = this.list(queryWrapper);
        return relationList;
    }

    @Override
    public BaseResult updateRelation(Relation relation) {
        if (StringUtils.isBlank(relation.getRelationship())) {
            return BaseResult.getFailedResult(403, "Relationship can not be empty");
        }
        if (this.saveOrUpdate(relation)) {
            QueryWrapper<Relation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", relation.getId());
            return BaseResult.getSuccessResult(this.getOne(queryWrapper));
        }
        return BaseResult.getFailedResult();
    }
}




