package store.tools;

import lombok.extern.slf4j.Slf4j;
import store.dto.OrderDTO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Slf4j
public class EMailSender {
    /**
     * Method for email and sms sending
     * @param eMail user's email
     * @param name user's name
     * @param password user's password
     */

    public static void registration(String eMail, String name, String password) {
        String subject = "We glad to see you in PowerTrade";
        String content = String.format("Hello, %s, we glad to see in our Store" +
                "\n Your email is: %s!\n Your password is: %s", name, eMail, password);
        bareSend(eMail, subject, content);
    }

    public static void passwordUpdate(String eMail, String name, String password){
        String subject = "Your password is Updated";
        String content = String.format("Hello, %s!\n You have updated your password.\n Here is your new password: %s",
                name, password);
        bareSend(eMail, subject, content);
    }

//    public static void createOrder(String eMail, String name, OrderDTO orderDTO){
//        String subject = "Your order is confirm";
//        String content = String.format("Hello, %s!\n You have updated your password.\n Here is your new password: %s",
//                name,);
//        bareSend(eMail, subject, content);
//    }

    public static void bareSend(String eMail, String subject, String content){
        String to = eMail;
        String from = "sales@powertrade.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html");
            Transport.send(message);
        } catch (MessagingException ex) {
            log.info("",ex);
        }
    }


}
