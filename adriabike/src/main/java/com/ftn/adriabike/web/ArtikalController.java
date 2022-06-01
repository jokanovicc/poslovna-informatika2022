package com.ftn.adriabike.web;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.service.ArtikalService;
import com.ftn.adriabike.service.PoreskaKategorijaService;
import com.ftn.adriabike.service.PrijemnicaService;
import com.ftn.adriabike.web.dto.ArtikalResponseDTO;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import com.ftn.adriabike.web.dto.PoreskaKategorijaResponseDTO;
import com.ftn.adriabike.web.dto.PrijemnicaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin("*")
public class ArtikalController {

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private PoreskaKategorijaService poreskaService;

    @Autowired
    private PrijemnicaService prijemnicaService;


    @GetMapping
    public ResponseEntity<List<ArtikalResponseDTO>> getAll(){
        return ResponseEntity
                .ok()
                .body(artikalService.findAll());
    }

    @PostMapping
    public ResponseEntity dodajRobu(@RequestBody List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTO) throws URISyntaxException {
        artikalService.dobavljenjeNoveRobe(dobavljanjeNoveRobeDTO);
        return ResponseEntity
                .created(new URI("/api/articles/"))
                .body(artikalService.findAll());
    }

    @GetMapping("/poreske-kategorije")
    public List<PoreskaKategorijaResponseDTO> getPoreskeKategorije(){
        return poreskaService.getAll();
    }




}
