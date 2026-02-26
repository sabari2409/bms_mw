package com.scaler.bms.controller;

import com.scaler.bms.dto.TheatreReqDTO;
import com.scaler.bms.dto.TheatreResDTO;
import com.scaler.bms.entity.Theatre;
import com.scaler.bms.services.impl.TheatreServiceImpl;
import com.scaler.bms.services.interfaces.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public ResponseEntity<TheatreResDTO> addNewTheatre(@RequestBody TheatreReqDTO request)
            throws RuntimeException, SQLException {
        if (request == null) {
            throw new RuntimeException("Invalid request");
        }
        TheatreResDTO resp = theatreService.addNewTheatre(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
