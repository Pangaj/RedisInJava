import redis.clients.jedis.Jedis;

public class StringsCmdRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        //set
        System.out.println("Set, Append & Get");
        jedis.set("MyName", "Pan");
        //append
        jedis.append("MyName", " Gaj");
        //get
        System.out.println(jedis.get("MyName"));
        System.out.println();

        /*//setBit
        jedis.setbit("ten", 6, true);

        //getBit
        System.out.println(jedis.getbit("ten", 7));*/

        System.out.println("getRange");
        //getRange
        jedis.set("text", "Hello world, I am @ ZOHO");
        System.out.println(jedis.getrange("text", 0, -1));  //whole text
        System.out.println(jedis.getrange("text", 0, 1));   //He
        System.out.println(jedis.getrange("text", -2, -1));   //HO
        System.out.println(jedis.getrange("text", 10, -11));   //d, I
        System.out.println(jedis.getrange("text", 10, -20));   //empty
        System.out.println(jedis.getrange("text", 10, 2000));   //d, I am @ ZOHO
        System.out.println();

        System.out.println("getSet");
        //get the older value and set the new value
        System.out.println(jedis.getSet("MyName", "PanGaj"));
        System.out.println(jedis.get("MyName"));
        System.out.println();

        System.out.println("incr, incrBy, incryByFloat, decr, decrBy");
        jedis.set("number", "10");
        //NOTE: after INCREMENTING BY FLOAT, both INCR and DECR causes "ERR value is not an integer or out of range" exception
        //incr by float
        System.out.println(jedis.incrByFloat("number", 0.5));
        System.out.println(jedis.incrByFloat("number", 0.5));

        //incr
        System.out.println(jedis.incr("number"));
        System.out.println(jedis.incrBy("number", 20));

        //decr
        System.out.println(jedis.decrBy("number", 20));
        System.out.println(jedis.decr("number"));
        System.out.println();

        System.out.println("mSet & mGet");
        jedis.mset("value1", "Pangaj", "value2", "Zoho", "value3", "Cliq");
        System.out.println(jedis.mget("value1", "value2", "value3", "MyName"));
        System.out.println();

        System.out.println("strLen");
        //strLen
        jedis.strlen("MyName");
        System.out.println();

        System.out.println("setRange");
        //setRange
        jedis.setrange("text", 21, "pan"); //Hello world, I am @ Zpan
        System.out.println(jedis.get("text"));
        jedis.setrange("text", 35, "pan"); //Hello world, I am @ Zpan           pan
        System.out.println(jedis.get("text"));
//        jedis.setrange("text",  -12, "pan"); //ERR offset is out of range "EXCEPTION"
        System.out.println();

        System.out.println("setNX");
        //set if not exists
        jedis.setnx("value1", "one");
        System.out.println(jedis.get("value1"));    //will print Pangaj, because it is already exists ("mSet" Line no.: 56)
        System.out.println();

        System.out.println("setEX");
        //setEX - Set key to hold the string value and set key to timeout after a given number of seconds
        //can use "pSetEX with millisecond" - but it is "DEPRECATED"
        System.out.println("setEX starts");
        jedis.setex("expiryNumber", 2, "this will shown only for 2 secs");
        for (int i = 0; i < 3; i++) {
            System.out.println(jedis.get("expiryNumber"));
            System.out.println(jedis.ttl("expiryNumber")); //Returns the remaining time to live of a key that has a timeout - In DB cheat sheet
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("setEX ends");
        System.out.println();

        System.out.println("mSetNX");
        //set multiple is not exists
        jedis.msetnx("value10", "Pangaj", "value20", "Zoho", "value3", "Cliq"); //will not perform any operation because already "value3" exists
        System.out.println(jedis.mget("value10", "value20", "value3"));
        System.out.println();
    }
}
