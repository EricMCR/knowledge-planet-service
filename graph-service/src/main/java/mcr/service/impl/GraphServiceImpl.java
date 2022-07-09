package mcr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mcr.clients.UserClient;
import mcr.entity.domain.Graph;
import mcr.entity.domain.User;
import mcr.entity.request.CreateGraphRequest;
import mcr.entity.result.BaseResult;
import mcr.service.GraphService;
import mcr.mapper.GraphMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
}




