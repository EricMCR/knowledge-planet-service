package mcr.clients;

import mcr.entity.domain.User;
import mcr.entity.result.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/user/getUserByToken")
    BaseResult getUserByToken(@RequestBody String token);

    @PostMapping("/user/getUserById")
    BaseResult getUserById(@RequestBody Long id);

    @PostMapping("/user/list")
    List<User> list();

}
