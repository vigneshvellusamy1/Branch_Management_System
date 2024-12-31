//package com.branch.config;
//
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieModule;
//import org.kie.api.builder.Message;
//import org.kie.api.runtime.KieContainer;
//import org.kie.internal.io.ResourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Configuration
//public class KieConfig {
//    private static final Logger logger = LoggerFactory.getLogger(KieConfig.class);
//    
//    @Bean
//    public KieServices kieServices() {
//        return KieServices.Factory.get();
//    }
//    
//    @Bean
//    public KieFileSystem kieFileSystem(KieServices kieServices) {
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        
//        // Add Drools rules
//        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/annual-report-rules.drl"));
//        
//        // Optionally add BPMN processes
//        kieFileSystem.write(ResourceFactory.newClassPathResource("annual-process.bpmn2"));
//        
//        return kieFileSystem;
//    }
//    
//    @Bean
//    public KieModule kieModule(KieServices kieServices, KieFileSystem kieFileSystem) {
//        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
//        
//        // Check for build errors
//        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
//            for (Message message : kieBuilder.getResults().getMessages()) {
//                logger.error("Error in KIE module: {}", message);
//            }
//            throw new RuntimeException("Build Errors in KIE Module:\n" + kieBuilder.getResults());
//        }
//        
//        return kieBuilder.getKieModule();
//    }
//    
//    @Bean
//    public KieContainer kieContainer(KieServices kieServices) {
//        return kieServices.getKieClasspathContainer();
//    }
//}