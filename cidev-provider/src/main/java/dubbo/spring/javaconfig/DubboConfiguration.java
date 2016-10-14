package dubbo.spring.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

@Configuration
@PropertySource(value={"classpath:/application.properties", "classpath:/dubbo.properties"})
public class DubboConfiguration {
	
	public static final String ANNOTATION_PACKAGE = "com.isoftstone.cityinsight.cidev.provider.service";

	@Autowired
	private Environment env;
	
	@Bean
    public static AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(ANNOTATION_PACKAGE);
        return annotationBean;
    }

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationCfg = new ApplicationConfig();
		applicationCfg.setName(env.getProperty("DUBBO_APPLICATION_NAME"));
		applicationCfg.setOrganization(env.getProperty("DUBBO_APPLICATION_ORGANIZATION"));
		applicationCfg.setOwner(env.getProperty("DUBBO_APPLICATION_OWNER"));
		return applicationCfg;
	}
	
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(env.getProperty("DUBBO_REGISTRY_ADDRESS"));
		registryConfig.setRegister(Boolean.parseBoolean(env.getProperty("DUBBO_REGISTRY_REGISTER")));
		return registryConfig;
	}
	
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protoCfg = new ProtocolConfig();
		protoCfg.setName(env.getProperty("DUBBO_PROTOCOL_NAME"));
		protoCfg.setPort(Integer.parseInt(env.getProperty("DUBBO_PROTOCOL_PORT")));
		return protoCfg;
	}
	
	@Bean
	public ProviderConfig providerConfig(ProtocolConfig protocolConfig) {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setProtocol(protocolConfig);
		return providerConfig;
	}
	
}
