import redis.clients.jedis.Jedis;

public class SetsCmdRedis {
    public static void main(String[] args) {
        //add elements in sets
        Jedis jedis = new Jedis();
        System.out.println("sAdd & sMembers");
        jedis.sadd("sets", "member - 1");
        jedis.sadd("sets", "member - 2", "member - 3"); //Can add more than one element from v2.4 and above
        jedis.sadd("sets", "member - 4");
        System.out.println(jedis.smembers("sets"));
        System.out.println();

        System.out.println("sCard");
        //sCard - Returns the set cardinality (number of elements)
        System.out.println(jedis.scard("sets"));
        System.out.println();

        System.out.println("sDiff");
        //sDiff - Returns the members of the set resulting from the difference between the first set and all the successive sets
        jedis.sadd("otherSets1", "member - 1", "member - 2", "member - 5");
        jedis.sadd("otherSets2", "member - 3", "member - 12", "member - 5");
        System.out.println(jedis.sdiff("sets", "otherSets1"));                  //[member - 3, member - 4]
        System.out.println(jedis.sdiff("sets", "otherSets2"));                  //[member - 4, member - 1, member - 2]
        System.out.println(jedis.sdiff("sets", "otherSets1", "otherSets2"));    //[member - 4] compare sets with both otherSets1 & otherSets2
        System.out.println();

        System.out.println("sDiffStore");
        //sDiffStore - store the return value of sDiff
        jedis.sdiffstore("resultantSet", "sets", "otherSets2");          //[member - 1, member - 2, member - 4]
        System.out.println(jedis.smembers("resultantSet"));
        System.out.println();

        System.out.println("sInter"); //Return type - total number of intersecting elements
        //sInter - Returns the members of the set resulting from the difference between the first set and all the successive sets
        jedis.sadd("sets", "member - 1", "member - 2", "member - 3", "member - 4");
        jedis.sadd("otherSets1", "member - 1", "member - 2", "member - 5");
        jedis.sadd("otherSets2", "member - 3", "member - 12", "member - 5");
        System.out.println(jedis.sinter("sets", "otherSets1"));                  //[member - 1, member - 2]
        System.out.println(jedis.sinter("sets", "otherSets2"));                  //[member - 3]
        System.out.println(jedis.sinter("sets", "otherSets1", "otherSets2"));    //[]   no intersecting elements
        System.out.println();

        System.out.println("sInterStore");  //Return type - total number of intersecting elements
        jedis.sinterstore("resultantSet", "sets", "otherSets1");    //[member - 1, member - 2]
        System.out.println(jedis.smembers("resultantSet"));
        System.out.println();

        System.out.println("sIsMember");    //Prints whether the member is present int the set
        System.out.println(jedis.sismember("resultantSet", "member - 1"));
        System.out.println(jedis.sismember("resultantSet", "member - 20"));
        System.out.println();

        System.out.println("sMove"); //Move the element from source to destination set
        jedis.sadd("sets1", "member - 1", "member - 2");
        jedis.sadd("sets2", "member - 3", "member - 12");
        System.out.println(jedis.smove("sets1", "sets2", "member - 1") + " member exists in source");
        System.out.println(jedis.smove("sets1", "sets2", "member - 14") + " member doesn't exists in source");
        System.out.println(jedis.smembers("sets1"));
        System.out.println(jedis.smembers("sets2"));
        System.out.println();

        System.out.println("sPop"); //Removes and returns one or more random elements from the set value store at key
        jedis.sadd("popSet", "member - 1", "member - 2", "member - 3", "member - 4", "member - 5", "member - 6");
        System.out.println(jedis.spop("popSet"));
        System.out.println(jedis.smembers("popSet"));
        System.out.println(jedis.spop("popSet", 3));
        System.out.println(jedis.smembers("popSet"));
        System.out.println();

        //sRandNumber
        //When called just with key argument, return a random element from the set value stored at key
        //when called with additional count argument +ve (POSITIVE) -> return an array of count distinct elements
        //If called with negative count -> the behavior changes and the command is allowed to return the same element MULTIPLE times
        System.out.println("sRandMember");
        jedis.sadd("popSet", "member - 1", "member - 2", "member - 3", "member - 4", "member - 5", "member - 6");
        System.out.println(jedis.srandmember("popSet"));
        System.out.println(jedis.srandmember("popSet", 4));
        System.out.println(jedis.srandmember("popSet", 10));
        System.out.println(jedis.srandmember("popSet", -4));
        System.out.println(jedis.srandmember("popSet", -10));
        System.out.println();

        System.out.println("sRem");
        System.out.println(jedis.srem("popSet", "member - 1"));
        System.out.println(jedis.srem("popSet", "member - 2", "member - 3"));
        System.out.println(jedis.srem("popSet", "member - 6", "member - 200"));
        System.out.println(jedis.smembers("popSet"));
        System.out.println();

        //need to check "sScan" alone

        System.out.println("sUnion");
        jedis.sadd("unionSet1", "member - 1", "member - 2");
        jedis.sadd("unionSet2", "member - 2", "member - 4", "member - 5", "member - 6");
        System.out.println(jedis.sunion("unionSet1", "unionSet2")); //total 6 unique item, "member -2" is in both sets, Only one will display in result
        System.out.println();

        System.out.println("sUnionStore");
        jedis.sadd("unionSet1", "member - 1", "member - 2");
        jedis.sadd("unionSet2", "member - 2", "member - 4", "member - 5", "member - 6");
        jedis.sunionstore("resultantUnion", "unionSet1", "unionSet2"); //total 6 unique item, "member -2" is in both sets, Only one will display in result
        System.out.println(jedis.smembers("resultantUnion"));
        System.out.println();
    }
}