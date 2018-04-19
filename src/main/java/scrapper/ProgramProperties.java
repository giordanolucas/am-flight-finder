package scrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProgramProperties {
    private static Properties properties = new Properties();
    private static boolean initialized = false;

    private static void initialize(){
        InputStream input = null;

        try {
            input = Scrapper.class.getClassLoader().getResourceAsStream("config-local.properties");
            properties.load(input);
            initialized = true;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Properties getProperties(){
        if(!initialized)
            initialize();

        return properties;
    }

    public static String getSmtpHost(){
        return getProperties().getProperty("mail.smtphost");
    }

    public static String getSmtpPort(){
        return getProperties().getProperty("mail.smtpport");
    }

    public static String getMailUsername(){
        return getProperties().getProperty("mail.username");
    }

    public static String getMailPassword(){
        return getProperties().getProperty("mail.password");
    }

    public static boolean getMailEnableTls(){
        return getProperties().getProperty("mail.enabletls").equalsIgnoreCase("true");
    }

    public static String getMailReceiptent(){
        return getProperties().getProperty("mail.receiptent");
    }

    public static String getDatabaseUsername(){
        return getProperties().getProperty("database.username");
    }

    public static String getDatabasePassword(){
        return getProperties().getProperty("database.password");
    }

    public static String getDatabaseHostPort(){
        return getProperties().getProperty("database.hostport");
    }

    public static String getDatabaseName(){
        return getProperties().getProperty("database.name");
    }

}
