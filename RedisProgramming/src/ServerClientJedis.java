import redis.clients.jedis.Jedis;

public class ServerClientJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();

        //echo
        System.out.println("echo - Returns message");
        System.out.println(jedis.echo("Hello World"));
        System.out.println();

        System.out.println("ping - returns \"PONG\"");
        System.out.println(jedis.ping());
        System.out.println();

        System.out.println("quit - Ask the server to close the connection ASAP");
        System.out.println(jedis.quit());

//        System.out.println(jedis.ping()); - Throws exception, because we have closed the in-memory db connection

        jedis.disconnect();
        if (jedis.isConnected()) {
            System.out.println(jedis.ping());
        } else {
            System.out.println("Still not connected");
        }

        jedis.connect();
        System.out.println(jedis.ping());
    }
}
