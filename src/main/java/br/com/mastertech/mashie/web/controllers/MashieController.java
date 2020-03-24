package br.com.mastertech.mashie.web.controllers;

import br.com.mastertech.mashie.data.models.Mashie;
import br.com.mastertech.mashie.web.dto.MashieRequest;
import br.com.mastertech.mashie.services.MashieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mashie")
public class MashieController {
    @Autowired
    private MashieService mashieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mashie create(@RequestBody MashieRequest mashieRequest){
        return mashieService.create(mashieRequest.toEntity());
    }
}
