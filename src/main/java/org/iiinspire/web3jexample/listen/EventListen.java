package org.iiinspire.web3jexample.listen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventListen implements ApplicationRunner {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials credentials;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //The contract address that needs to be monitored
        List<String> contractAddressList = new ArrayList<>();

        //Get the latest block
        DefaultBlockParameter latest = DefaultBlockParameter.valueOf("latest");
        EthBlock ethBlock = web3j.ethGetBlockByNumber(latest, false).send();
        BigInteger fromBlock = ethBlock.getBlock().getNumber().subtract(BigInteger.valueOf(1000L));

        EthFilter ethFilter = new EthFilter(DefaultBlockParameter.valueOf(fromBlock), latest, contractAddressList);

        //Get eth logs
        EthLog ethLog = web3j.ethGetLogs(ethFilter).send();
    }
}
