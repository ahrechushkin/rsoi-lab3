/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb; 

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger; 
import javax.ejb.ActivationConfigProperty; 
import javax.ejb.MessageDriven; 
import javax.ejb.MessageDrivenContext; 
import javax.jms.JMSException; 
import javax.jms.Message; 
import javax.jms.MessageListener; 
import javax.jms.TextMessage; 

@MessageDriven(activationConfig = { 
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Queue") 
}) 
public class SimpleMessageBean implements MessageListener { 
public SimpleMessageBean() {
 } 
static final Logger logger = Logger.getLogger("SimpleMessageBean"); 
public static List<String> stringList = new ArrayList<>();

private MessageDrivenContext mdc; 
@Override 
public void onMessage(Message inMessage) { 

TextMessage msg = null; 
try { 
if (inMessage instanceof TextMessage) { 
msg = (TextMessage) inMessage; 
logger.info("MESSAGE BEAN: Message received: "+ msg.getText()); 
stringList.add(msg.getText());
} else { 
logger.warning("Message of wrong type: "+ inMessage.getClass().getName()); 
} 
} catch (JMSException e) { 
e.printStackTrace(); 
mdc.setRollbackOnly(); 
} catch (Throwable te) { 
te.printStackTrace(); 
}
  Collections.sort(stringList);
 try(FileWriter writer = new FileWriter("D:\\result.txt", false))
        {
           writer.write(stringList.toString());
           writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
}
}


