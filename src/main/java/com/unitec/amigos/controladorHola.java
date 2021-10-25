package com.unitec.amigos;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class controladorHola {

@GetMapping("/hola")

    public String saludar(){
    return "hola DESDE MI SERVICIO REST ";
}

}
