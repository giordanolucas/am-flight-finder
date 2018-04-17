package scrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProgramProperties {
    private static Properties properties = new Properties();

    public static void initialize(){
        InputStream input = null;

        try {
            input = Scrapper.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);

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

    public static String getSmtpHost(){
        return properties.getProperty("mail.smtphost");
    }

    public static String getSmtpPort(){
        return properties.getProperty("mail.smtpport");
    }

    public static String getMailUsername(){
        return properties.getProperty("mail.username");
    }

    public static String getMailPassword(){
        return properties.getProperty("mail.password");
    }

    public static boolean getMailEnableTls(){
        return properties.getProperty("mail.enabletls").equalsIgnoreCase("true");
    }

    public static String getMailReceiptent(){
        return properties.getProperty("mail.receiptent");
    }

}
