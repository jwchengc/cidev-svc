package dubbo.spring.javaconfig;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages={"com.isoftstone.cityinsight.cidev.provider.dao.mapper"})
public class MybatisConfig {
	
	@Bean
	public DataSource dataSource(Environment env) {
		
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setInitialSize(env.getProperty("jdbc.pool.initialPoolSize", Integer.class));
        dataSource.setMinIdle(env.getProperty("jdbc.pool.minPoolSize", Integer.class));
        dataSource.setMaxActive(env.getProperty("jdbc.pool.maxPoolSize", Integer.class));
		
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
//		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/isoftstone/cityinsight/cidev/provider/dao/mapper/maps/*Mapper.xml"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mybatis/*Mapper.xml"));
		return sqlSessionFactoryBean;
	}
	
	/**
     * 执行初始化脚本
     */
    @Value("classpath:/db/mysql/schema.sql")
    private Resource schemaScript;
    /**
     * 执行初始化脚本
     */
    @Value("classpath:/db/mysql/data.sql")
    private Resource initDataScript;
    
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }
    
    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("utf-8");
        populator.addScript(schemaScript);
        populator.addScript(initDataScript);
        populator.setContinueOnError(true);
        return populator;
    }

}
