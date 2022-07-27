package com.ftn.adriabike.web;

import com.ftn.adriabike.service.PrijemnicaService;
import com.ftn.adriabike.web.dto.PrijemnicaPagingResponseDTO;
import com.ftn.adriabike.web.dto.PrijemnicaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prijemnice")
@CrossOrigin("*")
public class PrijemniceController {

    @Autowired
    private PrijemnicaService prijemnicaService;


    @GetMapping("/{prijemnica-id}")
    public ResponseEntity<PrijemnicaResponseDTO> getPrijemnica(@PathVariable("prijemnica-id") Integer prijemnicaId){
        return ResponseEntity
                .ok()
                .body(prijemnicaService.getDetaljnaPrijemnicaById(prijemnicaId));

    }

    @GetMapping
    public ResponseEntity<PrijemnicaPagingResponseDTO> getAllPrijemnice(@RequestParam(defaultValue = "0") Integer page){
        return ResponseEntity
                .ok()
                .body(prijemnicaService.getAllDetaljnaPrijemnica(page));

    }

}
