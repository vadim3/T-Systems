package store.tools;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import java.io.File;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Stateless(name = "ejb/MessageSender")
public class SenderImpl implements SenderLocal {

    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jms/topic/js-topic")
    private Topic topic;

    @Schedule(hour = "*", minute = "*", second= "*/5", persistent = false)
    public void sendMessage() {
        File dataDir = new File(System.getProperty("jboss.server.data.dir"));
        File file = new File(dataDir, "local.txt");
        if (file.exists()){
            context.createProducer().send(topic, "text");
            file.delete();
        }
    }

}
