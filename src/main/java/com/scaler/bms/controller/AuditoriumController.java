package com.scaler.bms.controller;

import com.scaler.bms.dto.AuditoriumReqDTO;
import com.scaler.bms.dto.AuditoriumResDTO;
import com.scaler.bms.services.interfaces.AuditoriumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auditoriums")
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    public AuditoriumController(AuditoriumService as) {
        this.auditoriumService = as;
    }


    @PostMapping
    public ResponseEntity<AuditoriumResDTO> addNewAuditoriumDetails(@RequestBody AuditoriumReqDTO req) {
        AuditoriumResDTO response = this.auditoriumService.addNewAuditorium(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
