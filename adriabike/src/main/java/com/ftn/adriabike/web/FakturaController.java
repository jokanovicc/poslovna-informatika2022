package com.ftn.adriabike.web;

import com.ftn.adriabike.service.FakturaService;
import com.ftn.adriabike.web.dto.IzlaznaFakturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value ="/api/faktura")
@RestController
@CrossOrigin("*")
public class FakturaController {

    @Autowired
    private FakturaService fakturaService;

    @GetMapping()
    public ResponseEntity<List<IzlaznaFakturaDTO>> findNepotvrdjene(){
        return ResponseEntity
                .ok()
                .body(fakturaService.getIzlazneFaktureNaCekanju());}


    @GetMapping("/{faktura-id}")
    public ResponseEntity<IzlaznaFakturaDTO> getIzlaznaFaktura(@PathVariable("faktura-id") Integer fakturaId){
        return ResponseEntity.ok().body(fakturaService.getFaktura(fakturaId));
    }

    @PostMapping("/{faktura-id}")
    public ResponseEntity<String> endFaktura(@PathVariable("faktura-id") Integer fakturaId){
        return ResponseEntity.ok().body(fakturaService.checkKolicina(fakturaId));
    }


}
