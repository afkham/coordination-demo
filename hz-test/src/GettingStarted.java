import com.hazelcast.core.*;
import com.hazelcast.config.*;

import java.util.Map;
import java.util.Queue;

public class GettingStarted {
    public static void main(String[] args) {
        Config cfg = new Config();
        cfg.getManagementCenterConfig().setEnabled(true).setUrl("http://localhost:8083/mancenter");
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Map<Integer, String> mapCustomers = instance.getMap("customers");
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Avi");

        System.out.println("Customer with key 1: "+ mapCustomers.get(1));
        System.out.println("Map Size:" + mapCustomers.size());

        Queue<String> queueCustomers = instance.getQueue("customers");
        queueCustomers.offer("Tom");
        queueCustomers.offer("Mary");
        queueCustomers.offer("Jane");
        System.out.println("First customer: " + queueCustomers.poll());
        System.out.println("Second customer: "+ queueCustomers.peek());
        System.out.println("Queue size: " + queueCustomers.size());

        IExecutorService testExecutor = instance.getExecutorService("TestExecutor");
//        testExecutor.executeOnAllMembers(new TestRunnable());

        for (int i = 0; i < 1000; i++) {
            testExecutor.execute(new TestRunnable(i));
        }
//        testExecutor.execute(new TestRunnable());
    }
}