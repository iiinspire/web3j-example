package org.iiinspire.web3jexample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    @Value("${web3j.http.rpc}")
    private String httpRpc;

    @Value("${wallet.private,key}")
    private String privateKey;

    @Bean
    public Web3j web3j(){
        return Web3j.build(new HttpService(httpRpc));
    }

    @Bean
    public Credentials credentials(){
        return Credentials.create(privateKey);
    }
}
