package com.ftn.adriabike.web;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.service.ArtikalService;
import com.ftn.adriabike.service.CenovnikService;
import com.ftn.adriabike.service.PoreskaKategorijaService;
import com.ftn.adriabike.service.PrijemnicaService;
import com.ftn.adriabike.web.dto.*;
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
    private CenovnikService cenovnikService;


    @GetMapping
    public ResponseEntity<List<ArtikalResponseDTO>> getAll(){
        return ResponseEntity
                .ok()
                .body(artikalService.findAll());
    }

    @GetMapping("/listbox")
    public List<ArtikliListboxDTO> getForListbox(){
        return artikalService.getForListbox();
    }

    @PutMapping("/{id}/update")
    public void updateArtikal(@PathVariable("id") Integer artikalId, @RequestBody ArtikalResponseDTO artikalResponseDTO){
        artikalService.updateArtikal(artikalResponseDTO, artikalId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtikalResponseDTO> getById(@PathVariable("id") Integer artikalId){
        return ResponseEntity
                .ok()
                .body(artikalService.getById(artikalId));      }

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


    @GetMapping("/cenovnici")
    public CenovniciPagingResult getCenovnici(@RequestParam(defaultValue = "0") Integer page){
        return cenovnikService.getAllCenovnici(page);
    }

    @PostMapping("/poskupljenje")
    public ResponseEntity<CenovnikDTO> poskupi(@RequestBody RastCenovnikaDTO rastCenovnikaDTO){
        return ResponseEntity
                .ok()
                .body(cenovnikService.makePoskupljenje(rastCenovnikaDTO));
    }

    @PostMapping("/korigovanje-cene")
    public void korigovanjeCene(@RequestBody KorigovanjeCeneDTO korigovanjeCene){
        cenovnikService.korigujCenu(korigovanjeCene);
    }

    @GetMapping("/{id}/cenovnik")
    public ResponseEntity<CenovnikDTO> getByIdCenovnik(@PathVariable Integer id){
        return ResponseEntity
                .ok()
                .body(cenovnikService.getById(id));    }




}
