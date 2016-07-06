package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Листенер события старта приложения
 */
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext contextParent = contextRefreshedEvent.getApplicationContext().getParent();
        if (contextParent != null) {

        }
    }
}
