package hva;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    @Value("${server.port}")
    private String serverPort;

    public static void main(String[] args) {
        // Start the webserver
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Application application = context.getBean(Application.class);

        LOGGER.info("Application url: http://localhost:{}", application.serverPort);

    }
}
