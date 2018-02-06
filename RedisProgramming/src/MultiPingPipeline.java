import redis.clients.jedis.Jedis;

import java.util.Date;

public class MultiPingPipeline {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        new MultiPingPipeline().pingForMultipleTimes(jedis);

        jedis.pipelined();
        new MultiPingPipeline().pingForMultipleTimes(jedis);
    }

    private void pingForMultipleTimes(Jedis jedis) {
        long startTime, endTime;
        startTime = new Date().getTime();
        for (int i = 0; i < 100000; i++) {
            jedis.ping();
        }

        endTime = new Date().getTime();
        System.out.println(endTime - startTime);
    }
}
