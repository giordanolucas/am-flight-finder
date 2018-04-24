package scrapper;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.Semaphore;

public class MailService {

    static Semaphore semaphore = new Semaphore(1);

    public static boolean sendMail(String subject, String text) {

        try {
            final String username = ProgramProperties.getMailUsername();
            final String password = ProgramProperties.getMailPassword();

            Properties props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", ProgramProperties.getMailEnableTls());
            props.put("mail.smtp.host", ProgramProperties.getSmtpHost());
            props.put("mail.smtp.port", ProgramProperties.getSmtpPort());

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ProgramProperties.getMailUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ProgramProperties.getMailReceiptent()));
            message.setSubject(subject);
            message.setText(text);

            semaphore.acquire();
            Transport.send(message);
            semaphore.release();

            System.out.println("Mail sent! (" + subject + ")");

            return true;
        } catch (Exception e) {
            System.err.println("Could not send mail");
            System.err.println(subject);
            System.err.println(text);
            System.err.println(e.getMessage());
            e.printStackTrace();

            if (semaphore.availablePermits() == 0) {
                semaphore.release();
            }

            return false;
        }
    }
}
