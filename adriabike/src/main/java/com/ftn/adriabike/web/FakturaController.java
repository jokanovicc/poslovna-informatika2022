package com.ftn.adriabike.web;

import com.ftn.adriabike.service.FakturaService;
import com.ftn.adriabike.web.dto.FakturaEndResponse;
import com.ftn.adriabike.web.dto.IzlaznaFakturaDTO;
import com.ftn.adriabike.web.dto.PorukaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value ="/api/faktura")
@RestController
@CrossOrigin("*")
public class FakturaController {

    @Autowired
    private FakturaService fakturaService;

    @GetMapping()
    public ResponseEntity<List<IzlaznaFakturaDTO>> findNepotvrdjene(@RequestParam(defaultValue = "nepotvrdjena") String status){
        return ResponseEntity
                .ok()
                .body(fakturaService.getIzlazneFakture(status));}


    @GetMapping("/{faktura-id}")
    public ResponseEntity<IzlaznaFakturaDTO> getIzlaznaFaktura(@PathVariable("faktura-id") Integer fakturaId){
        return ResponseEntity.ok().body(fakturaService.getFaktura(fakturaId));
    }

    @PostMapping("/{faktura-id}")
    public ResponseEntity<FakturaEndResponse> endFaktura(@PathVariable("faktura-id") Integer fakturaId, Authentication authentication){
        return ResponseEntity.ok().body(fakturaService.zavrsiFakturu(fakturaId, authentication));
    }

    @GetMapping ("/user")
    public ResponseEntity<List<IzlaznaFakturaDTO>> getFakturaByUser(Authentication authentication){
        return ResponseEntity.ok().body(fakturaService.getFaktureByUser(authentication));
    }


    @PostMapping ("/send-poruka")
    public void posaljiPoruku(@RequestBody PorukaDTO poruka){
        fakturaService.posaljiPoruku(poruka);
    }





}
