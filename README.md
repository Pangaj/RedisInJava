![RedisInJava](https://raw.githubusercontent.com/Pangaj/RedisInJava/master/redisLogo.png)

## Pros of Redis
- **Blazing fast**
- **Supports wide variety of data types**
- **Open source** and has an **active community**
- **Simple to install & no dependencies**
- **Stores generic data types for any purpose**
- works even in **single cheap/free server**

## Cons of Redis
- Your **dataset has to fit** comfortably **in memory**
- **No joins or query language**
- You have to **learn lua** if you want something like **stored procedures**

## Datatypes supported by Redis
 - Strings
 - Lists
 - Sets
 - Hashes
 - Sorted sets
 - Bitmaps & HyperLogLogs

## Why JedisPool instead of Jedis?
- **Strange error** will encounter, if you **use same instance from different threds**
- Creating lot of instance means **more sockets & connections** leads to same strange error
- Use **threadsafe pool of network connection**, i.e., ***JedisPool*** which gives **great performance**
