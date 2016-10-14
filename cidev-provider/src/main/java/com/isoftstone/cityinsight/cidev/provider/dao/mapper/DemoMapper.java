package com.isoftstone.cityinsight.cidev.provider.dao.mapper;

import java.util.List;

import com.isoftstone.cityinsight.cidev.api.domain.Demo;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoMapper {

	public List<Demo> list();
	
}
