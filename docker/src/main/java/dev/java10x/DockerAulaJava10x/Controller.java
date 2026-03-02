package dev.java10x.DockerAulaJava10x;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/docker")
    public String mensagem(){
        return "Essa é uma aplicação DOCKER";
    }
}
