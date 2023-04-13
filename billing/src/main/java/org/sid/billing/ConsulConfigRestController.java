package org.sid.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RefreshScope
public class ConsulConfigRestController {

    private final MyConsulConfig myConsulConfig;


    @Value("${token.accessTokenTimeout}")
    private long accessTokenTimeout;
    @Value("${token.refreshTokenTimeout}")
    private long refreshTokenTimeout;



    @GetMapping("/myConsulConfig")
    public Map<String, Object> myConsulConfig() {
        return Map.of("accessTokenTimeout", accessTokenTimeout, "refreshTokenTimeout", refreshTokenTimeout);
    }

    @GetMapping("/anotherMyConfig")
    public MyConsulConfig getMyConsulConfig() {
        return myConsulConfig;
    }
}
