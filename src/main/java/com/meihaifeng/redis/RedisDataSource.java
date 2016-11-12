//package com.meihaifeng.redis;
//
//import redis.clients.jedis.ShardedJedis;
//
///**
// * 浙江卓锐科技股份有限公司
// *
// * @author meihf
// * @date 2016/8/18
// * @description
// */
//    public interface RedisDataSource {
//        public abstract ShardedJedis getRedisClient();//取得redis的客户端，可以执行命令了。
//        public void returnResource(ShardedJedis shardedJedis);//将资源返还给pool
//        public void returnResource(ShardedJedis shardedJedis, boolean broken);//出现异常后，将资源返还给pool （其实不需要第二个方法）
//    }
