package mcr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mcr.clients.UserClient;
import mcr.entity.domain.Graph;
import mcr.entity.domain.User;
import mcr.entity.request.CreateGraphRequest;
import mcr.entity.result.BaseResult;
import mcr.entity.vo.GraphVo;
import mcr.service.GraphService;
import mcr.mapper.GraphMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
* @author mcr98
* @description 针对表【graph】的数据库操作Service实现
* @createDate 2022-07-07 14:09:47
*/
@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph>
    implements GraphService {

    @Autowired
    private UserClient userClient;

    @Override
    public BaseResult createGraph(CreateGraphRequest createGraphRequest, String token) {
        String name = createGraphRequest.getName();
        String description = createGraphRequest.getDescription();
        if (Strings.isBlank(name)) {
            return BaseResult.getFailedResult(403, "Please input name");
        }

        BaseResult result = userClient.getUserByToken(token);
        if (!result.isSuccess()) {
            return BaseResult.getFailedResult(403, "Please sign in.");
        }

        Graph graph = new Graph();
        graph.setName(name);
        if (Strings.isNotBlank(description)) {
            graph.setDescription(description);
        }

        Map<String, Object> map = (HashMap)result.getData();
        graph.setUserId((Long)map.get("id"));

        if (this.save(graph)) {
            return BaseResult.getSuccessResult(graph);
        }
        return BaseResult.getFailedResult();
    }

    @Override
    public BaseResult updateGraph(Graph graphForm, String token) {
        QueryWrapper<Graph> graphQueryWrapper = new QueryWrapper<>();
        graphQueryWrapper.eq("id", graphForm.getId());
        Graph graph = this.getOne(graphQueryWrapper);

        BaseResult result = userClient.getUserByToken(token);
        Map<String, Object> map = (HashMap)result.getData();
        Long currUserId = (Long)map.get("id");

        if (!Objects.equals(currUserId, graph.getUserId())) {
            return BaseResult.getFailedResult(400, "No modification permission.");
        }

        graph.setName(graphForm.getName());
        graph.setDescription(graphForm.getDescription());
        if (this.saveOrUpdate(graph)) {
            return BaseResult.getSuccessResult();
        }
        return BaseResult.getFailedResult();
    }

    @Override
    public BaseResult getGraphById(Long id) {
        QueryWrapper<Graph> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", id);
        Graph graphObj = this.getOne(queryWrapper1);
        if (graphObj == null) {
            return BaseResult.getFailedResult(404, "Graph not found");
        }
        graphObj.setViews(graphObj.getViews()+1);
        this.saveOrUpdate(graphObj);
        BaseResult result = userClient.getUserById(graphObj.getUserId());
        Map<String, Object> map = (HashMap)result.getData();

        GraphVo graphVo = new GraphVo(graphObj, (String)map.get("username"));
        return BaseResult.getSuccessResult(graphVo);
    }

    @Override
    public BaseResult popularGraphList() {
        QueryWrapper<Graph> graphQueryWrapper = new QueryWrapper<Graph>();
        graphQueryWrapper.orderByDesc("views");
        List<Graph> graphList = this.list(graphQueryWrapper);
        List<User> userList = userClient.list();
        Map<Long, String> usernameMap = new HashMap<>();
        for (User user: userList) {
            usernameMap.put(user.getId(), user.getUsername());
        }
        List<GraphVo> list = new ArrayList<>();
        for (Graph graph: graphList) {
            list.add(new GraphVo(graph, usernameMap.get(graph.getUserId())));
        }
        return BaseResult.getSuccessResult(list);
    }

    @Override
    public List<Graph> getGraphListByUserId(Long id) {
        QueryWrapper<Graph> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        List<Graph> graphList = this.list(queryWrapper);

        return graphList;
    }
}




