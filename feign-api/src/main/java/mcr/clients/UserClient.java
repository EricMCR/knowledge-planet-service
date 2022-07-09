package mcr.clients;

import mcr.entity.result.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/user/getUserByToken")
    BaseResult getUserByToken(@RequestBody String token);

}
