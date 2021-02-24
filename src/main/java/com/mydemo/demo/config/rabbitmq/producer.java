package com.mydemo.demo.config.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class producer {
    //创建队列，发送消息
    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = easy.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明创建队列
        channel.queueDeclare("QUEUE_NAME",false,false,false,null);
        //消息内容
        String message = "Hello World!";
        channel.basicPublish("","QUEUE_NAME2",null,message.getBytes());
        System.out.println("发送消息："+message);
        //关闭连接和通道
        channel.close();
        connection.close();
    }
}
