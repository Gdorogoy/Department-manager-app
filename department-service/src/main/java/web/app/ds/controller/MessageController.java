package web.app.ds.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api/scoper")

public class MessageController {

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("/get")
    public String getMessage(){
        return this.message;
    }
}
