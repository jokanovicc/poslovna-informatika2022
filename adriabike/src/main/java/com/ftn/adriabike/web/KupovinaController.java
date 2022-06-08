package com.ftn.adriabike.web;

import com.ftn.adriabike.service.FakturaService;
import com.ftn.adriabike.service.KupovinaService;
import com.ftn.adriabike.web.dto.DodajUKorpuDTO;
import com.ftn.adriabike.web.dto.KorpaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value ="/api/cart")
@RestController
@CrossOrigin("*")
public class KupovinaController {

    @Autowired
    private KupovinaService kupovinaService;

    @Autowired
    private FakturaService fakturaService;



    @PostMapping
    public ResponseEntity<Void> dodajUKorpu(@RequestBody DodajUKorpuDTO dodajUKorpu, Authentication authentication){
        kupovinaService.dodajUKorpu(dodajUKorpu, authentication);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<KorpaResponseDTO> getKorpa(Authentication authentication){
        return ResponseEntity
                .ok()
                .body(kupovinaService.getKorpa(authentication));
    }

    @DeleteMapping("/{stavka-id}")
    public ResponseEntity<Void> removeStavkaFromKorpa(@PathVariable("stavka-id") Integer stavkaId){
        kupovinaService.removeFromKorpa(stavkaId);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @PostMapping("/create-faktura")
    public ResponseEntity<Void> createFaktura(Authentication authentication){
        fakturaService.createFaktura(authentication);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
