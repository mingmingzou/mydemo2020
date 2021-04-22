package com.mydemo.demo.config.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class easy {
    /**
     * 获取连接
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.56.229.155");
        factory.setPort(5672);
        //设置vhost
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        //通过工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }




}
