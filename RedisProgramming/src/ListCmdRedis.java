import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

public class ListCmdRedis {
    public static void main(String[] args) {
        String list = "lists";
        Jedis jedis = new Jedis();

        jedis.del(list);
        System.out.println("lPush - Insert all the specified values at the head of the list stored at key");
        System.out.println(jedis.lpush(list, "ZOHO"));
        System.out.println(jedis.lpush(list, "Welcome"));
        System.out.println();

        System.out.println("lRange - Returns the specified elements of the list stored at key, 0, 1, 2......, -2, -1");
        System.out.println(jedis.lrange(list, 0, -1));
        System.out.println();

        System.out.println("lInsert - Inserts value in the list stored at key either before or after the reference value pivot");
        System.out.println(jedis.linsert(list, BinaryClient.LIST_POSITION.BEFORE, "ZOHO", "me"));
        System.out.println(jedis.lrange(list, 0, -1));
        System.out.println();

        System.out.println("lIndex - Returns the element at index index in the list stored at key");
        System.out.println(jedis.lindex(list, 2));  //0-welcome, 1-me, and 2-ZOHO or (-1)-ZOHO, (-2)-me, (-3)-welcome
        System.out.println();

        System.out.println("lLen");
        System.out.println(jedis.llen(list));
        System.out.println();

        System.out.println("lPop - pop from start");
        System.out.println(jedis.lpop(list));
        System.out.println();

        System.out.println("rPop - pop from end");
        System.out.println(jedis.rpop(list));
        System.out.println();

        System.out.println("rPush - Insert all the specified values at the TAIL of the list");
        System.out.println(jedis.rpush(list, "insert at last"));
        System.out.println();

        System.out.println("rPushHX - Push at end only if key exists, here the key is \"list\"");
        System.out.println(jedis.rpushx(list, "insert at last - if present"));
        System.out.println(jedis.rpushx("anotherList", "insert at last - if present")); //return 0, as anotherList is not present, so item is not inserted
        System.out.println();

        System.out.println("lPushHX - Push at end only if key exists, here the key is \"list\"");
        System.out.println(jedis.lpushx(list, "insert at first - if present"));
        System.out.println(jedis.lpushx("anotherList", "insert at first - if present")); //return 0, as anotherList is not present, so item is not inserted
        System.out.println();

        System.out.println("rPopLPush -  Atomically returns and removes the last element (tail) of the list stored at source, and pushes the element at the first element (head) of the list stored at destination");
        System.out.println(jedis.lrange(list, 0, -1));
        System.out.println(jedis.rpoplpush(list, list));
        System.out.println(jedis.lrange(list, 0, -1));
        System.out.println();

        System.out.println("lSet - Sets the list element at index to value");
        System.out.println(jedis.lset(list, 0, "NewRecord"));
        System.out.println(jedis.lrange(list, 0, -1));
        System.out.println();

        System.out.println("lRem - Removes the first count occurrences of elements equal to value from the list stored at key");
        System.out.println(jedis.lpush(list, "NewRecord"));  //already one element of "NewRecord" exists in db
        //0 will delete all the element of "NewRecord",
        //If 1, then only one will get deleted.
        //Can also give -1 and other, which will delete only 1 element from the last and so on.
        System.out.println(jedis.lrem(list, 1, "NewRecord"));
        System.out.println(jedis.lrange(list, 0, -1));
        System.out.println();

        System.out.println("lTrim - List will contain only specify range of elements from existing list");
        System.out.println(jedis.ltrim(list, 1, -2));   //total 4 elements, so 1 to -2 will give you only two
        System.out.println(jedis.lrange(list, 0, -1));
        System.out.println();
    }
}
