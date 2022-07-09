package mcr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mcr.entity.domain.Node;
import mcr.service.NodeService;
import mcr.mapper.NodeMapper;
import org.springframework.stereotype.Service;

/**
* @author mcr98
* @description 针对表【node】的数据库操作Service实现
* @createDate 2022-07-07 14:09:47
*/
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node>
    implements NodeService{

}




