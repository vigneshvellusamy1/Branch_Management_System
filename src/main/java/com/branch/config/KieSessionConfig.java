//package com.branch.config;
//
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.process.ProcessRuntime;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//import jakarta.annotation.PreDestroy;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class KieSessionConfig {
//    private final List<KieSession> activeSessions = new ArrayList<>();
//    private final KieContainer kieContainer;
//
//    // Constructor injection
//    public KieSessionConfig(KieContainer kieContainer) {
//        this.kieContainer = kieContainer;
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public KieSession kieSession() {
//        KieSession session = kieContainer.newKieSession();
//        activeSessions.add(session);
//        return session;
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public ProcessRuntime processRuntime() {
//        return kieContainer.newKieSession();
//    }
//
//    @PreDestroy
//    public void cleanUp() {
//        for (KieSession session : activeSessions) {
//            try {
//                if (session != null) {
//                    session.dispose();
//                }
//            } catch (Exception e) {
//                System.err.println("Error disposing KieSession: " + e.getMessage());
//            }
//        }
//        activeSessions.clear();
//    }
//
//    public void releaseSession(KieSession session) {
//        if (session != null) {
//            session.dispose();
//            activeSessions.remove(session);
//        }
//    }
//}