package com.ftn.adriabike.web;

import com.ftn.adriabike.model.Magacin;
import com.ftn.adriabike.service.MagacinService;
import com.ftn.adriabike.service.MagacinskaKarticaService;
import com.ftn.adriabike.service.PrometMagacinskeKarticeService;
import com.ftn.adriabike.web.dto.AnalyticDTO;
import com.ftn.adriabike.web.dto.MagacinResponseDTO;
import com.ftn.adriabike.web.dto.MagacinskaKarticaResponse;
import com.ftn.adriabike.web.dto.PrometMagacinskeKarticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    @Autowired
    private MagacinskaKarticaService magacinskaKarticaService;


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

    @PostMapping("/analytics")
    public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getAnalyticsByDate(@RequestBody AnalyticDTO analytic){

        return ResponseEntity
                .ok()
                .body(prometMagacinskeKarticeService.getAnalitika(analytic));
    }


    @GetMapping("/{id}/analytics/{dateStart}/{dateEnd}")
    public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getAnalyticsByDateKartica(@PathVariable("id") Integer id,@PathVariable("dateStart")Date dateStart, @PathVariable("dateEnd")Date dateEnd){
        return ResponseEntity
                .ok()
                .body(prometMagacinskeKarticeService.getAnalitikaByKartica(dateStart, dateEnd, id));
    }


    @PostMapping()
    public ResponseEntity createMagacin(@RequestBody MagacinResponseDTO magacinResponseDTO) throws URISyntaxException {
        magacinService.create(magacinResponseDTO);
        return ResponseEntity
                .created(new URI("/api/warehouse/"))
                .body(magacinService.getAll());
    }

    @PostMapping("/otvaranje-stanja")
    @ResponseStatus(value = HttpStatus.OK)
    public void otvoriStanje(){
        magacinskaKarticaService.createPocetnoStanje();
    }


}
