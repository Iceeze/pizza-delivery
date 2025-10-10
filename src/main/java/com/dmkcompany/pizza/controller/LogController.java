package com.dmkcompany.pizza.controller;

import com.dmkcompany.pizza.service.proxy.ProductServiceLoggingProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final ProductServiceLoggingProxy productServiceProxy;

    @GetMapping("/stats")
    public String getLogStats() {
        productServiceProxy.logProductAccessStatistics();
        return "Статистика логирования обновлена - проверьте логи приложения";
    }
}
