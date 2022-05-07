package PropertiesTest;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class OwnerTests {
    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    @Tag("owner")
    @Test
    void loginTest(){
        String login = config.login();
        String password = config.password();

        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
    }
}
