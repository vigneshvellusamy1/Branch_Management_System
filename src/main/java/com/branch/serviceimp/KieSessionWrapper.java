package com.branch.serviceimp;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.io.Closeable;

public class KieSessionWrapper implements Closeable {
	private final KieSession kieSession;

	public KieSessionWrapper(KieSession kieSession) {
		this.kieSession = kieSession;
	}

	public KieSession getSession() {
		return kieSession;
	}

	@Override
	public void close() {
		if (kieSession != null) {
			kieSession.dispose();
		}
	}

	// Convenience method to create and wrap a KieSession
	public static KieSessionWrapper create(KieContainer kieContainer) {
		return new KieSessionWrapper(kieContainer.newKieSession());
	}
}
