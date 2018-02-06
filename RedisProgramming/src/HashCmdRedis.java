import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class HashCmdRedis {
    public static void main(String[] args) {
        String hashes = "hashes";
        Jedis jedis = new Jedis();
        //"hSet"
        jedis.hset(hashes, "1", "One");
        jedis.hset(hashes, "2", "Two");

        System.out.println("hVals");
        System.out.println(jedis.hvals(hashes));
        System.out.println();

        System.out.println("hSetNX - if \"0\" already present, if \"1\" newly inserted");
        System.out.println(jedis.hsetnx(hashes, "1", "One already present"));
        System.out.println(jedis.hsetnx(hashes, "3", "Three"));
        System.out.println();

        System.out.println("hMSet - add in Map<?,?> and then add it in Jedis");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("4", "Four");
        hashMap.put("5", "Five");
        System.out.println(jedis.hmset(hashes, hashMap));
        System.out.println();

        System.out.println("hMGet - return type - List<String>");
        System.out.println(jedis.hmget(hashes, "1", "2", "3", "10"));
        System.out.println();

        System.out.println("hGet");
        System.out.println(jedis.hget(hashes, "5"));
        System.out.println();

        System.out.println("hKeys - return all keys - return type - Set<String>");
        System.out.println(jedis.hkeys(hashes));
        System.out.println();

        System.out.println("hLen - Returns total no. of keys stored in hash");
        System.out.println(jedis.hlen(hashes));
        System.out.println();

        System.out.println("hGetAll - gives {Key=value,....} pair - return type - Map<String, String>");
        System.out.println(jedis.hgetAll(hashes));
        System.out.println();

        System.out.println("hExists - Check for item - return type - boolean");
        System.out.println(jedis.hexists(hashes, "1"));
        System.out.println(jedis.hexists(hashes, "10"));
        System.out.println();

        System.out.println("hDel - if \"1\" deleted, if\"0\" not present");
        System.out.println(jedis.hdel(hashes, "5"));
        System.out.println(jedis.hdel(hashes, "10"));
        System.out.println();

        System.out.println("hIncrBy & hincrByFloat");
        System.out.println("Can add both with Positive and negative numbers");
        System.out.println("Throws exception if other than integer - ERR hash value is not an integer");
        jedis.hset(hashes, "incValue", "1");
        System.out.println(jedis.hincrBy(hashes, "incValue", 10));
        System.out.println(jedis.hincrBy(hashes, "incValue", -10));
        System.out.println(jedis.hincrByFloat(hashes, "incValue", 0.5));
        System.out.println(jedis.hincrByFloat(hashes, "incValue", -0.5));
        System.out.println(jedis.hincrBy(hashes, "incValue", 0));
    }
}
