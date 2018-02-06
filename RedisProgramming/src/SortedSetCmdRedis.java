import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class SortedSetCmdRedis {
    public static void main(String[] args) {
        String sortedSet = "sortedSet";
        Jedis jedis = new Jedis();

        //VALUES ARE CASE SENSITIVE - there is different in adding "Ten" & "ten"

        System.out.println("zAdd - add item - return type - number of elements added");
        HashMap<String, Double> hashMap = new HashMap<>();
        //insert in score "1"
        hashMap.put("Five", 1d);
        hashMap.put("Six", 1d);
        hashMap.put("Seven", 1d);
        hashMap.put("Eight", 1d);
        hashMap.put("Nine", 1d);
        hashMap.put("Ten", 1d);
        System.out.println(jedis.zadd(sortedSet, 1, "One"));
        System.out.println(jedis.zadd(sortedSet, 1, "Two"));
        System.out.println(jedis.zadd(sortedSet, 1, "Three"));
        System.out.println(jedis.zadd(sortedSet, 1, "Four"));
        System.out.println(jedis.zadd(sortedSet, hashMap));
        //insert in score "2"
        System.out.println(jedis.zadd(sortedSet, 2, "Ten"));
        System.out.println(jedis.zadd(sortedSet, 2, "Twenty"));
        System.out.println();

        System.out.println("zCard - get total number of items");
        System.out.println(jedis.zcard(sortedSet));
        System.out.println();

        System.out.println("zCount - Number of items within score range");
        System.out.println(jedis.zcount(sortedSet, 1, 1));  //9 - 1 to 9, Does not take 10, because It is present in score 2
        System.out.println(jedis.zcount(sortedSet, 2, 2));  //2 - 10 and 20
        System.out.println(jedis.zcount(sortedSet, 2, 1));  //returns Zero

        //remember the values are case sensitive
        System.out.println(jedis.zadd(sortedSet, 1, "Ten"));
        System.out.println(jedis.zcount(sortedSet, 1, 1));  //10 - again 10 is added in score "1"
        System.out.println(jedis.zcount(sortedSet, 2, 2));  //1 - 10 is removed from Score "2" as score "1" added again
        System.out.println();

        System.out.println("zScore - Get item score");
        System.out.println(jedis.zscore(sortedSet, "Five"));
        System.out.println(jedis.zscore(sortedSet, "Twenty"));
        System.out.println();

        System.out.println("zRank - Returns the rank of member in the sorted set ordered from low to high. Lowest rank is 0");
        System.out.println(jedis.zrank(sortedSet, "Eight"));
        System.out.println(jedis.zrank(sortedSet, "Five"));
        System.out.println(jedis.zrank(sortedSet, "Four"));
        System.out.println(jedis.zrank(sortedSet, "Nine"));
        System.out.println(jedis.zrank(sortedSet, "One"));
        System.out.println(jedis.zrank(sortedSet, "Seven"));
        System.out.println(jedis.zrank(sortedSet, "Six"));
        System.out.println(jedis.zrank(sortedSet, "Ten"));
        System.out.println(jedis.zrank(sortedSet, "Three"));
        System.out.println(jedis.zrank(sortedSet, "Two"));
        System.out.println(jedis.zrank(sortedSet, "Twenty"));
        System.out.println(jedis.zadd(sortedSet, 5, "Double"));
        System.out.println(jedis.zrank(sortedSet, "Double"));
        System.out.println();

        System.out.println("zRevRank - Returns the rank of member in the sorted set ordered from high to low. highest rank is 0");
        System.out.println(jedis.zrevrank(sortedSet, "Eight"));
        System.out.println(jedis.zrevrank(sortedSet, "Five"));
        System.out.println(jedis.zrevrank(sortedSet, "Four"));
        System.out.println(jedis.zrevrank(sortedSet, "Nine"));
        System.out.println(jedis.zrevrank(sortedSet, "One"));
        System.out.println(jedis.zrevrank(sortedSet, "Seven"));
        System.out.println(jedis.zrevrank(sortedSet, "Six"));
        System.out.println(jedis.zrevrank(sortedSet, "Ten"));
        System.out.println(jedis.zrevrank(sortedSet, "Three"));
        System.out.println(jedis.zrevrank(sortedSet, "Two"));
        System.out.println(jedis.zrevrank(sortedSet, "Twenty"));
        System.out.println(jedis.zrevrank(sortedSet, "Double"));
        System.out.println();

        System.out.println("zRem - Removes the specified members from the sorted set stored at key. Non existing members are ignored");
        System.out.println(jedis.zrem(sortedSet, "Ten"));
        System.out.println(jedis.zrem(sortedSet, "TEN"));
        System.out.println();

        System.out.println("zIncrBy");
        System.out.println(jedis.zincrby(sortedSet, 5, "Ten")); //if already present, get the score and increment with given, 5 + 0 = 5
        System.out.println(jedis.zincrby(sortedSet, 5, "Twenty"));  //if already present, get the score and increment with given, 5 + 2 = 7
        System.out.println();

        System.out.println("zLexCount - Number of elements in the sorted set between min and max value in lexicographical ordering");
        System.out.println(jedis.zlexcount(sortedSet, "[One", "[Six"));
        System.out.println();

        System.out.println("zRange");
        System.out.println(jedis.zrange(sortedSet, 0, -1));
        System.out.println();

        System.out.println("zRevRange");
        System.out.println(jedis.zrevrange(sortedSet, 0, -1));
        System.out.println();

        System.out.println("zRangeByScore");
        System.out.println(jedis.zrangeByScore(sortedSet, 5, 7));
        System.out.println();

        System.out.println("zRevRangeByScore");
        System.out.println(jedis.zrevrangeByScore(sortedSet, 7, 5));
        System.out.println();

        System.out.println("zRangeByLex");
        System.out.println(jedis.zrangeByLex(sortedSet, "[E", "[N"));
        System.out.println();

        System.out.println("zRemRangeByLex");
        System.out.println(jedis.zremrangeByLex(sortedSet, "[E", "[F"));
        System.out.println(jedis.zrange(sortedSet, 0, -1));
        System.out.println();

        System.out.println("zRemRangeByRank - Removes all elements in the sorted set stored at key with rank between start and stop");
        System.out.println(jedis.zremrangeByRank(sortedSet, 6, 7));
        System.out.println(jedis.zrange(sortedSet, 0, -1));
        System.out.println();

        System.out.println("zRemRangeByScore");
        System.out.println(jedis.zremrangeByScore(sortedSet, 6, 7));
        System.out.println(jedis.zrange(sortedSet, 0, -1));
        System.out.println();
    }
}
