
package com.loginRegistro.dto;

import java.util.Date;
import lombok.Data;

@Data
public class RegistoDTO {
    
    private String nombre;
    private String apellido;
    private String cedula;
    private String correo;
    private Date fechaExpedicion;
    private String telefoo;
    private String ciudad;
    private String identificador;
    private String contrasenia;
    
}
