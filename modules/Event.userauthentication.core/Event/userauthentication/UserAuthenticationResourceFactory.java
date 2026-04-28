package Event.userauthentication;

import Event.userauthentication.core.resource.UserAuthenticationResource;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class UserAuthenticationResourceFactory {
    private static final Logger LOGGER = Logger.getLogger(UserAuthenticationResourceFactory.class.getName());

    public UserAuthenticationResourceFactory() {}

    public static UserAuthenticationResource createUserAuthenticationResource(String fullyQualifiedName, Object ... base) {
        UserAuthenticationResource record = null;
        try {
            Class<?> clz = Class.forName(fullyQualifiedName);
            Constructor<?> constructor = clz.getDeclaredConstructors()[0];
            record = (UserAuthenticationResource) constructor.newInstance(base);
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to create instance of UserAuthenticationResource.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            System.exit(20);
        } catch (ClassCastException e) {
            LOGGER.severe("Failed to cast to UserAuthenticationResource.");
            System.exit(30);
        } catch (ClassNotFoundException e) {
            LOGGER.severe("Class not found: " + fullyQualifiedName);
            System.exit(40);
        } catch (Exception e) {
            LOGGER.severe("Unexpected error when creating UserAuthenticationResource.");
            System.exit(50);
        }
        return record;
    }
}
