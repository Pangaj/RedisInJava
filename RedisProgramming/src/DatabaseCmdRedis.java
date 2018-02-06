import redis.clients.jedis.Jedis;

public class DatabaseCmdRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        System.out.println("del - Removes the specified keys. A key is ignored if it does not exist");
        System.out.println(jedis.set("key1", "New Key"));
        System.out.println(jedis.del("key1"));
        System.out.println(jedis.del("key1"));
        System.out.println();

        System.out.println("dump - Serialise item");
        System.out.println(jedis.dump("serialiseData")); // null value because key1 is already deleted
        jedis.set("serialiseData", "serialiseData");
        byte[] serialiseContent = jedis.dump("serialiseData");
        System.out.println(serialiseContent); //not print the serialised content
        System.out.println();

        jedis.set("key1", "key1");
        System.out.println("exists - check for key");
        System.out.println(jedis.exists("key1"));
        System.out.println(jedis.exists("key2"));
        System.out.println(jedis.exists("key1", "key2"));   //return total number of data present in db
        System.out.println();

        System.out.println("expire");
        jedis.expire("key1", 1);

        //It you need to check the expire, then remove the comment and check the output
        /*System.out.println(jedis.ttl("key1"));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(jedis.ttl("key1"));
                timer.cancel();
            }
        }, 1000);*/
        System.out.println();

        System.out.println("ExpireAt - It takes an absolute Unix timestamp");
        System.out.println(jedis.expireAt("key1", 1518004800000L)); //7 February 2018 12:00:00
        System.out.println();

        //in general 0th db is selected, so we are now moving the generated pin to second db
        //will throw exception - ERR source and destination objects are the same
        System.out.println("move");
        System.out.println(jedis.move("key1", 1));
        System.out.println();

        System.out.println("keys");
        jedis.set("key2", "key2");
        jedis.set("key3", "key3");
        System.out.println(jedis.keys("key*")); // (*) will show all the keys present in the db
        System.out.println();

        System.out.println("object");
        System.out.print("objectEncoding : ");  //returns the kind of internal representation used in order to store the value associated with a key
        System.out.println(jedis.objectEncoding("key1"));
        System.out.print("objectIdletime : ");  //returns the number of seconds since the object stored at the specified key is idle
        System.out.println(jedis.objectIdletime("key1"));
        System.out.print("objectRefcount : ");  //returns the number of references of the value associated with the specified key
        System.out.println(jedis.objectRefcount("key1"));
        System.out.println();

        System.out.println("persist - Remove timeout");
        jedis.expire("key1", 1);
        System.out.println("TTL : " + jedis.ttl("key1"));
        System.out.println("PTTL : " + jedis.pttl("key1"));
        jedis.persist("key1");
        System.out.println(jedis.get("key1") + " : " + jedis.ttl("key1"));
        System.out.println();

        System.out.println("pExpire - timeout in milliseconds");
        System.out.println(jedis.pexpire("key1", 200000L));
        System.out.println();

        System.out.println("randomKey");
        System.out.println(jedis.randomKey());
        System.out.println();

        System.out.println("renameNX");
        System.out.println(jedis.renamenx("key1", "key2")); //already exists, so not copied
        System.out.println();

        System.out.println("rename");
        System.out.println(jedis.rename("key1", "key2"));
        System.out.println();

        System.out.println("restore");
        jedis.del("serialiseData"); //Need to delete the "serialiseData" before copying the content, else BUSYKEY Target key name already exists
        jedis.restore("serialiseData", 0, serialiseContent);
        System.out.println(jedis.get("serialiseData"));
        System.out.println();

        System.out.println("type");
        System.out.println(jedis.type("serialiseData"));    //string
        System.out.println(jedis.type("hashes"));           //hash
        System.out.println(jedis.type("sortedSet"));        //zSet
        System.out.println();
    }
}
