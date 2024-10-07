package ru.bublinoid.web3.service;

import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;
import ru.bublinoid.web3.config.EthereumConnection;
import ru.bublinoid.web3.generate.SimpleStorage;

import java.math.BigInteger;

@Service
public class ContractService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final SimpleStorage contract;
    private static final Logger log = LoggerFactory.getLogger(EthereumConnection.class);

    @Autowired
    public ContractService(Web3j web3j) {
        this.web3j = web3j;
        this.credentials = Credentials.create("");
        this.contract = SimpleStorage.load("", web3j, credentials, new DefaultGasProvider());
    }

    public void setValue(BigInteger value) throws Exception {
        contract.set(value).send();
        log.info("SimpleStorage contract loaded at address: {}", contract.getContractAddress());
    }

    public BigInteger getValue() throws Exception {
        BigInteger storedData = contract.get().send();
        log.info("Retrieved value from the contract: {}", storedData);
        return storedData;
    }
}
