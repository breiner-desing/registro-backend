package com.loginRegistro.service.Impl;

import com.loginRegistro.config.ContrasenaConfig;
import com.loginRegistro.dto.LoginDTO;
import com.loginRegistro.dto.RegistoDTO;
import com.loginRegistro.repository.RegistrarRepository;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loginRegistro.service.RegistrarService;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@Service
public class RegistrarServiceImpl implements RegistrarService {
    
    @Autowired
    RegistrarRepository registrarRepository;
    
    @Override
    public int registrar(RegistoDTO persona) {
        
        try {
            
            log.info("valores : {}", persona);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            String Identificador = cadenaAleatoria();
            persona.setIdentificador(Identificador);
            
            LoginDTO nuevoUsuario = new LoginDTO();
            
            nuevoUsuario.setCorreo(persona.getCorreo());
            nuevoUsuario.setContrasenia(ContrasenaConfig.create(persona.getContrasenia()));
            nuevoUsuario.setIdentificador(Identificador);
            
            return (registrarRepository.registroPersona(persona, Date.valueOf(formatter.format(persona.getFechaExpedicion()))) == 1) ? registrarRepository.registroLogin(nuevoUsuario) : 0;
            
        } catch (Exception e) {
            log.error("error - registrar(): {} ", e);
            return 0;
        }
        
    }
    
    @Override
    public List<RegistoDTO> login(LoginDTO login) {
        
        try {
            
            List<LoginDTO> login1 = registrarRepository.login(login, ContrasenaConfig.create(login.getContrasenia()));
            log.info("lgeado : {}", login1);
            return registrarRepository.getLogin(login1.get(0).getIdentificador());
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegistrarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String cadenaAleatoria() {
        
        int longitud = 18;
        
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadena = "";
        
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = ThreadLocalRandom.current().nextInt(0, (banco.length() - 1) + 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        
        return cadena;
    }
    
}
