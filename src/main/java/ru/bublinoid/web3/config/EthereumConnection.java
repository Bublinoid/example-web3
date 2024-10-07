package ru.bublinoid.web3.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class EthereumConnection {

    private static final Logger logger = LoggerFactory.getLogger(EthereumConnection.class);

    @Bean
    public static Web3j connectionToEthereum() {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8455"));
        logger.info("Connecting to Ethereum success");
        return web3j;
    }
}
