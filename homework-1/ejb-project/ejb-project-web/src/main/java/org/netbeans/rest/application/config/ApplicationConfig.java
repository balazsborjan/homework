package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("/webshop")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mycompany.exceptionmappers.GeneralExceptionMapper.class);
        resources.add(com.mycompany.exceptionmappers.IllegalRestRequestExceptionMapper.class);
        resources.add(com.mycompany.exceptionmappers.IllegalValidationExceptionMapper.class);
        resources.add(com.mycompany.rest.CartRESTService.class);
        resources.add(com.mycompany.rest.InventoryRESTService.class);
        resources.add(com.mycompany.rest.UserRESTService.class);
    }

}
