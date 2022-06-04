package com.ftn.adriabike.web;

import com.ftn.adriabike.service.MagacinService;
import com.ftn.adriabike.service.PrometMagacinskeKarticeService;
import com.ftn.adriabike.web.dto.MagacinResponseDTO;
import com.ftn.adriabike.web.dto.MagacinskaKarticaResponse;
import com.ftn.adriabike.web.dto.PrometMagacinskeKarticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/warehouse")
@CrossOrigin("*")
public class MagacinController {

    @Autowired
    private MagacinService magacinService;
    @Autowired
    private PrometMagacinskeKarticeService prometMagacinskeKarticeService;


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

    @GetMapping("/analytics/{dateStart}/{dateEnd}")
    public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getAnalyticsByDate(@PathVariable("dateStart")Date dateStart, @PathVariable("dateEnd")Date dateEnd){
        return ResponseEntity
                .ok()
                .body(prometMagacinskeKarticeService.getAnalitika(dateStart, dateEnd));
    }



}
