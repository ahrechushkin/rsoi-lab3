/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplemessageclient; 

import javax.annotation.Resource; 
import javax.jms.Connection; 
import javax.jms.ConnectionFactory; 
import javax.jms.JMSException; 
import javax.jms.MessageProducer; 
import javax.jms.Queue; 
import javax.jms.Session; 
import javax.jms.TextMessage; 
import java.util.Scanner;


public class SimpleMessageClient { 
@Resource(mappedName = "jms/ConnectionFactory") 
private static ConnectionFactory connectionFactory; 

@Resource(mappedName = "jms/Queue")
private static Queue queue; 

public static void main(String[] args) { 
try { 

Connection connection = connectionFactory.createConnection(); 
Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
MessageProducer messageProducer = session.createProducer(queue); 
Scanner scan= new Scanner(System.in);
TextMessage message = session.createTextMessage(); 

for (int i = 0; i < 20; i++) {
String msg= scan.nextLine();  
message.setText(msg); 
System.out.println("Sending message: " + 
message.getText()); 
messageProducer.send(message); 
} 
} catch (JMSException e) { 
e.printStackTrace(); 
} 
} 

}
