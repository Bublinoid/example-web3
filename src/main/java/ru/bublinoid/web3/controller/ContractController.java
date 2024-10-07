package ru.bublinoid.web3.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bublinoid.web3.service.ContractService;

import java.math.BigInteger;

@Controller
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/set")
    public String setValue(@RequestMapping BigInteger value) {
        try {
            contractService.setValue(value);
            return "Value successfully set to: " + value;
        } catch (Exception e) {
            return "Error while setting the value: " + e.getMessage();
        }
    }


    @PostMapping("/get")
    public BigInteger getValue() {
        try {
            return contractService.getValue();
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving the value: " + e.getMessage());
        }
    }
}
