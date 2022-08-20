package mcr.service;

import mcr.entity.domain.Graph;
import com.baomidou.mybatisplus.extension.service.IService;
import mcr.entity.request.CreateGraphRequest;
import mcr.entity.request.UserGraphRequest;
import mcr.entity.result.BaseResult;
import mcr.entity.vo.GraphVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author mcr98
* @description 针对表【graph】的数据库操作Service
* @createDate 2022-07-07 14:09:47
*/
public interface GraphService extends IService<Graph> {

    BaseResult createGraph(CreateGraphRequest createGraphRequest, String token);

    BaseResult updateGraph(Graph graph, String token);

    BaseResult getGraphById(Long id);

    BaseResult popularGraphList();

    BaseResult searchGraphList(String keywords);

    List<Graph> getGraphListByUserId(UserGraphRequest userGraphRequest);

}
