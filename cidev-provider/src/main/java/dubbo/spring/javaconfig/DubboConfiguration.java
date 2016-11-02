package dubbo.spring.javaconfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.isoftstone.dubbo.registry.KubeRegistry;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

import java.util.HashMap;
import java.util.Map;

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
//		registryConfig.setGroup(env.getProperty("DUBBO_REGISTRY_GROUP"));
		return registryConfig;
	}
	
	@Bean
	public ProtocolConfig protocolConfig() {
		Map<String,String> param = new HashMap<String,String>();
		if(StringUtils.isNotBlank(env.getProperty("DUBBO_PROTOCOL_KUBE_HOST"))) {
			param.put(KubeRegistry.KUBE_HOST, env.getProperty("DUBBO_PROTOCOL_KUBE_HOST"));
		}
		System.out.println(env.getProperty("DUBBO_PROTOCOL_KUBE_PORT"));
		if(StringUtils.isNotBlank(env.getProperty("DUBBO_PROTOCOL_KUBE_PORT"))) {
			param.put(KubeRegistry.KUBE_PORT, env.getProperty("DUBBO_PROTOCOL_KUBE_PORT"));
		}
		ProtocolConfig protoCfg = new ProtocolConfig();
		protoCfg.setParameters(param);
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
