package com.ftn.adriabike.web;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.service.ArtikalService;
import com.ftn.adriabike.web.dto.ArtikalResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin("*")
public class ArtikalController {

    @Autowired
    private ArtikalService artikalService;


    @GetMapping
    public ResponseEntity<List<ArtikalResponseDTO>> getAll(){
        return ResponseEntity
                .ok()
                .body(artikalService.findAll());
    }

}
