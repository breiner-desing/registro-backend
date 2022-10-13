
package com.loginRegistro.service;

import com.loginRegistro.dto.LoginDTO;
import com.loginRegistro.dto.RegistoDTO;
import java.util.List;


public interface RegistrarService {
    
    public int registrar( RegistoDTO persona );
    public List<RegistoDTO> login( LoginDTO login );
    
}
