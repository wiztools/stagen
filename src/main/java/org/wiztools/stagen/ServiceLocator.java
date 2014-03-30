package org.wiztools.stagen;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.mycila.guice.ext.jsr250.Jsr250Module;

/**
 *
 * @author subwiz
 */
public class ServiceLocator {
    private static final Injector injector =  Guice.createInjector(
            Stage.PRODUCTION, new Jsr250Module());
    
    private ServiceLocator() {}
    
    public static <T> T getInstance(Class<T> c) {
        return injector.getInstance(c);
    }
}
