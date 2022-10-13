/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loginRegistro.controller;

import com.loginRegistro.dto.GenericDTO;
import com.loginRegistro.dto.LoginDTO;
import com.loginRegistro.dto.RegistoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.loginRegistro.service.RegistrarService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RestController
@RequestMapping("registrar")
@CrossOrigin(origins = "*")
public class RegistroController {
    
    @Autowired
    private RegistrarService registrarService;
    
    
    @PostMapping("registrar")
    public ResponseEntity<GenericDTO> getInformeSimple(@RequestBody RegistoDTO persona) {
        log.info("Registrar : {}", persona);
        return ResponseEntity.ok().body(GenericDTO.sucess(this.registrarService.registrar(persona)));
    }
    
    @PostMapping("login")
    public ResponseEntity<GenericDTO> login(@RequestBody LoginDTO persona) {
        log.info("Registrar : {}", persona);
        return ResponseEntity.ok().body(GenericDTO.sucess(this.registrarService.login(persona)));
    }
    
}
