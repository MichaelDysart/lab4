package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"buddy"})
public class ServeAddressBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(webapp.ServeAddressBookApplication.class, args);
    }
}

