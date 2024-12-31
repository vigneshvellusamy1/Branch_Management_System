//package com.branch.config;
//
//import org.apache.naming.factory.ResourceFactory;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.manager.RuntimeEngine;
//import org.kie.api.runtime.manager.RuntimeEnvironment;
//import org.kie.api.runtime.manager.RuntimeManager;
//import org.kie.api.runtime.manager.RuntimeManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//	public class JbpmConfig {
//	    
//	    @Bean
//	    public RuntimeManager runtimeManager() {
//	        RuntimeEnvironment environment = RuntimeEngine.Factory.get()
//	            .newDefaultBuilder()
//	            .userGroupCallback(new JBossUserGroupCallbackImpl("classpath:/users.properties"))
//	            .addAsset(ResourceFactory.newClassPathResource("processes/branch-process.bpmn2""),
//	                     ResourceType.BPMN2)
//	            .get();
//	        
//	        return RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
//	    }
//	}
//
//
