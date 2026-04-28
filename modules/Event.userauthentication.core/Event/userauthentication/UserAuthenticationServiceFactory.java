package Event.userauthentication;

import Event.userauthentication.core.service.UserAuthenticationService;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class UserAuthenticationServiceFactory {
    private static final Logger LOGGER = Logger.getLogger(UserAuthenticationServiceFactory.class.getName());

    public UserAuthenticationServiceFactory() {}

    public static UserAuthenticationService createUserAuthenticationService(String fullyQualifiedName, Object ... base) {
        UserAuthenticationService record = null;
        try {
            Class<?> clz = Class.forName(fullyQualifiedName);
            Constructor<?> constructor = clz.getDeclaredConstructors()[0];
            record = (UserAuthenticationService) constructor.newInstance(base);
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to create instance of UserAuthenticationService.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            System.exit(20);
        } catch (ClassCastException e) {
            LOGGER.severe("Failed to cast to UserAuthenticationService.");
            System.exit(30);
        } catch (ClassNotFoundException e) {
            LOGGER.severe("Class not found: " + fullyQualifiedName);
            System.exit(40);
        } catch (Exception e) {
            LOGGER.severe("Unexpected error when creating UserAuthenticationService.");
            System.exit(50);
        }
        return record;
    }
}
