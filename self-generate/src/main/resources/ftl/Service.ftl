package ${package_name}.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jt.base.BaseMapper;
import com.jt.base.BaseService;
import com.jt.dao.mapper.UserMapper;
import com.jt.entity.User;

@Service
public class ${class_name}Service extends BaseService<Integer, ${class_name}> {

	private ${class_name}Mapper ${class_name?uncap_first}Mapper;

	@Resource(type = ${class_name}Mapper.class)
	public void setMapper(BaseMapper<Integer, ${class_name}> mapper) {
		this.mapper = mapper;
		this.${class_name?uncap_first}Mapper = (${class_name}Mapper) mapper;
	}

}
