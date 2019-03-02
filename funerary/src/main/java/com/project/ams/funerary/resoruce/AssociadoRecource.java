package com.project.ams.funerary.resoruce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("associados")
public class AssociadoRecource {

    @GetMapping
    public List<String> all() {
        return Arrays.asList("João da Neves", "Macelo Pimentel", "Vitor Felipe");
    }
}
