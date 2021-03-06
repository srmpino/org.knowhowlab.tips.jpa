package org.knowhowlab.tips.jpa.hibernate;

import org.hibernate.ejb.HibernatePersistence;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import javax.persistence.spi.PersistenceProvider;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author dpishchukhin
 */
public class Activator implements BundleActivator {
    private static final String JAVAX_PERSISTENCE_PROVIDER_PROP = "javax.persistence.provider";
    private ServiceRegistration serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        HibernatePersistence persistence = new HibernatePersistence();
        Dictionary<String, String> props = new Hashtable<String, String>();
        props.put(JAVAX_PERSISTENCE_PROVIDER_PROP, persistence.getClass().getName());
        serviceRegistration = context.registerService(PersistenceProvider.class.getName(), persistence, props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}
