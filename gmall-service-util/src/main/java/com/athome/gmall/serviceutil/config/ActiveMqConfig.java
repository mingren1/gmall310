package com.athome.gmall.serviceutil.config;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.Session;

@Configuration
public class ActiveMqConfig {

    @Value("${spring.activemq.broker-url:disable}")
    private String brokerURL;
    @Value("${activemq.listener.enable:disable}")
    private String listenerEnable;
    @Bean
    public ActiveMqUtil getActiveMqUtil() {

        if ("disable".equals(brokerURL)){
            return null;
        }
        ActiveMqUtil activeMqUtil = new ActiveMqUtil();
        activeMqUtil.init(brokerURL);
        return activeMqUtil;

    }

    @Bean(name = "jmsQueueListener")
    public DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        if ("disable".equals(listenerEnable)) {
            return null;
        }
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        // 设置事务
        factory.setSessionTransacted(false);
        // 自动签收
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        // 设置并发数
        factory.setConcurrency("5");
        // 重连间隔时间
        factory.setRecoveryInterval(5000L);
        return factory;

    }

    @Bean
    public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
        return activeMQConnectionFactory;
    }


}
