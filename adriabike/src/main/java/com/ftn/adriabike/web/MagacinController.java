package com.ftn.adriabike.web;

import com.ftn.adriabike.service.MagacinService;
import com.ftn.adriabike.web.dto.MagacinResponseDTO;
import com.ftn.adriabike.web.dto.MagacinskaKarticaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
@CrossOrigin("*")
public class MagacinController {

    @Autowired
    private MagacinService magacinService;


    @GetMapping
    public ResponseEntity<List<MagacinResponseDTO>> getAll(){
        return ResponseEntity
                .ok()
                .body(magacinService.getAll());
    }

    @GetMapping("/{magacinId}")
    public ResponseEntity<List<MagacinskaKarticaResponse>> getMagacinskaKarticaByMagacin(@PathVariable("magacinId") Integer magacinId){
        return ResponseEntity
                .ok()
                .body(magacinService.getMagacinskeKarticeByMagacin(magacinId));
    }


    @GetMapping("/{id}/kartica")
    public ResponseEntity<MagacinskaKarticaResponse> getMagacinskaKartica(@PathVariable("id") Integer id){
        return ResponseEntity
                .ok()
                .body(magacinService.findOne(id));
    }



}
