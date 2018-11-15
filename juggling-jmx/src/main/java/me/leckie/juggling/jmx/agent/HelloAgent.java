package me.leckie.juggling.jmx.agent;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import me.leckie.juggling.jmx.bean.HelloWorld;

/**
 * @author Leckie
 * @version $Id: HelloAgent.java, v0.1 2018/10/24 14:43 Leckie Exp $$
 */
public class HelloAgent implements NotificationListener {

  private MBeanServer mBeanServer;

  public HelloAgent() {
    // this.mBeanServer = MBeanServerFactory.createMBeanServer(getClass().getSimpleName());
    this.mBeanServer = ManagementFactory.getPlatformMBeanServer();
    try {
      ObjectName objectName = new ObjectName("HelloWorld:name=helloWorld");
      mBeanServer.registerMBean(new HelloWorld(), objectName);
      // Registry registry = LocateRegistry.createRegistry(1024);
      JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi://jndi/rmi://localhost:1024/jmxrmi");
      JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory
          .newJMXConnectorServer(jmxServiceURL, null, mBeanServer);
      jmxConnectorServer.start();
    } catch (MalformedObjectNameException e) {
      e.printStackTrace();
    } catch (NotCompliantMBeanException e) {
      e.printStackTrace();
    } catch (InstanceAlreadyExistsException e) {
      e.printStackTrace();
    } catch (MBeanRegistrationException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new HelloAgent();
  }

  @Override
  public void handleNotification(Notification notification, Object handback) {
    System.out.println("handleNotification");
  }
}
