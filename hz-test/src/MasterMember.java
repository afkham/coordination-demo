import com.hazelcast.config.Config;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MasterMember {
    public static void main(String[] args) throws Exception {
        Config cfg = new Config();
        cfg.getManagementCenterConfig().setEnabled(true).setUrl("http://localhost:8083/mancenter");
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        // Put int Map
        Map<String, String> map = hz.getMap("map ");
        for (int k = 0; k < 10; k++) {
            map.put(UUID.randomUUID().toString(), "");
        }

        // Execute on keyOwner
        IExecutorService executor = hz.getExecutorService(" exec ");
        for (String key : map.keySet()) {
            executor.executeOnKeyOwner(new VerifyTask(key), key);
        }

        // Async Callable demo
        int n = 5;
        ExecutionCallback<Long> callback = new ExecutionCallback<Long>() {
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }

            public void onResponse(Long response) {
                System.out.println(" Async Result : " + response);
            }
        };
        executor.submit(new FibonacciCallable(n), callback);

        // Sync Callable demo
        Future<Long> future = executor.submit(new FibonacciCallable(n));
        try {
            long result = future.get(10, TimeUnit.SECONDS);
            System.out.println(" Sync Result : " + result);
        } catch (TimeoutException ex) {
            System.out.println("A timeout happened ");
        }
    }
}