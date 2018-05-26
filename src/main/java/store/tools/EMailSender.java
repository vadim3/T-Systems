package store.tools;

import lombok.extern.slf4j.Slf4j;
import store.dto.OrderDTO;
import store.dto.ProductDTO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Slf4j
public class EMailSender {
    /**
     * Method for email sending after registration
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

    /**
     * Method for email sending after password Update
     * @param eMail user's email
     * @param name user's name
     * @param password user's password
     */
    public static void passwordUpdate(String eMail, String name, String password){
        String subject = "Your password is Updated";
        String content = String.format("Hello, %s!\n You have updated your password.\n Here is your new password: %s",
                name, password);
        bareSend(eMail, subject, content);
    }

    /**
     * Method for email sending after create order
     * @param eMail user's email
     * @param name user's name
     * @param orderDTO order
     */
    public static void createOrder(String eMail, String name, OrderDTO orderDTO){
        String subject = "Your order is created";
        String content = String.format("Hello, %s!\n You have create order:\n",
                name);
        StringBuilder products = new StringBuilder();
        for (Map.Entry<ProductDTO, Integer> entry : orderDTO.getProducts().entrySet()){
            String productNote = entry.getKey().getName() + " " + entry.getKey().getPrice() + " x " + entry.getValue() +
                    "pieces\n";
            products.append(productNote);
        }
        String date = String.format("Date of order is %s\n", orderDTO.getDateTime());
        products.append(date);
        content += products.toString();
        bareSend(eMail, subject, content);
    }

    /**
     * Method for email sending when order status changed
     * @param eMail user's email
     * @param name user's name
     * @param orderDTO order
     */
    public static void OrderStatusChanged(String eMail, String name, OrderDTO orderDTO){
        String subject = "Order status is changed";
        String content = String.format("Hello, %s!\n Your order status is changed\n New order status: %s",
                name, orderDTO.getOrderStatus().getStatus());
        bareSend(eMail, subject, content);
    }

    /**
     * Bare method for email sending
     * @param eMail user's email
     * @param subject subject of message
     * @param content content of message
     */
    private static void bareSend(String eMail, String subject, String content){
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
