package com.mydemo.demo.config.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class consumer {
    //消费者消费消息
    public static void main(String[] args) throws Exception {
        //获取连接和通道
        Connection connection = easy.getConnection();
        Channel channel = connection.createChannel();
        //声明通道
        channel.queueDeclare("QUEUE_NAME",false,false,false,null);
        //监听队列
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [Consumer] Received '" + message + "'");
            }
        };
        channel.basicConsume("QUEUE_NAME", true, consumer);

    }
}
