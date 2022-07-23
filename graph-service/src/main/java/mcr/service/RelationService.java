package mcr.service;

import mcr.entity.domain.Graph;
import mcr.entity.domain.Relation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mcr98
* @description 针对表【relation】的数据库操作Service
* @createDate 2022-07-07 14:09:47
*/
public interface RelationService extends IService<Relation> {

    List<Relation> getGraphRelationList(Graph graph);

}
