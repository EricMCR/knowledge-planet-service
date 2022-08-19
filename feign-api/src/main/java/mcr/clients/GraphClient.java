package mcr.clients;

import mcr.entity.Vo.GraphVo;
import mcr.entity.domain.Graph;
import mcr.entity.request.UserGraphRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("graph-service")
public interface GraphClient {

    @PostMapping("/graph/getGraphListByUserId")
    List<Graph> getGraphListByUserId(@RequestBody UserGraphRequest userGraphRequest);
}
