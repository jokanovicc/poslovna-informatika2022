package com.ftn.adriabike.web;

import com.ftn.adriabike.model.PoreskaKategorija;
import com.ftn.adriabike.service.PoreskaKategorijaService;
import com.ftn.adriabike.web.dto.PoreskaKategorijaDTO;
import com.ftn.adriabike.web.dto.PoreskaStopaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax")
@CrossOrigin("*")
public class PoreskaKategorijaController {


    @Autowired
    private PoreskaKategorijaService poreskaKategorijaService;





    @GetMapping("/{id}")
    public PoreskaKategorijaDTO getOne(@RequestParam("id") Integer id){
        return poreskaKategorijaService.getById(id);
    }

    @GetMapping()
    public List<PoreskaKategorijaDTO> getOne(){
        return poreskaKategorijaService.findAllPoreskaKategorija();
    }

    @PostMapping
    public PoreskaKategorijaDTO createPoreskaKategorija(@RequestBody PoreskaStopaDTO poreskaStopa){
        return poreskaKategorijaService.createPoreskaKategorija(poreskaStopa.getNaziv());
    }


    @PostMapping("/{id}/stopa")
    public void addPoreskaStopa(@RequestBody PoreskaStopaDTO poreskaStopa, @PathVariable("id") Integer id){
        poreskaKategorijaService.createPoreskaStopa(id, poreskaStopa);
    }


}
