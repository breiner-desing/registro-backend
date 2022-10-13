package com.loginRegistro.repository;

import com.loginRegistro.dto.LoginDTO;
import com.loginRegistro.dto.RegistoDTO;
import java.sql.Date;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class RegistrarRepository {

    @Autowired
    @Qualifier("jdbcRegistrar")
    private JdbcTemplate jdbcRegistrar;

    public int registroPersona(RegistoDTO persona, Date fecha) {

        try {
            String sql = "Insert into registro (nombre,apellido,cedula,correo,fecha_expedicion, telefono, ciudad, identificador) values ( :nombre , :apellido, :cedula, :correo, :fecha, :telefono, :ciudad, :identificador)";

            NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(this.jdbcRegistrar);

            MapSqlParameterSource parametros = new MapSqlParameterSource();
            parametros.addValue("nombre", persona.getNombre());
            parametros.addValue("apellido", persona.getApellido());
            parametros.addValue("cedula", persona.getCedula());
            parametros.addValue("correo", persona.getCorreo());
            parametros.addValue("fecha", fecha);
            parametros.addValue("telefono", persona.getTelefoo());
            parametros.addValue("ciudad", persona.getCiudad());
            parametros.addValue("identificador",  persona.getIdentificador());

            return namedTemplate.update(sql, parametros);
        } catch (Exception e) {
            log.error("errors - registro() : {}", e.getCause());
        }
        return 0;
    }

    public int registroLogin(LoginDTO persona) {

        try {
            String sql = "INSERT INTO login\n"
                    + "(identificador, correo, contrasenia)\n"
                    + "VALUES(?, ?, ?);";
            return jdbcRegistrar.update(sql, new Object[]{persona.getIdentificador(), persona.getCorreo(), persona.getContrasenia()});
        } catch (Exception e) {
            log.error("errors - login() : {}", e.getCause());
        }
        return 0;
    }

    public List<LoginDTO> login(LoginDTO persona, String password) {

        try {
            log.info(password);
            String sql = "select * from login l where correo = ? and contrasenia = ? LIMIT 1";
            return jdbcRegistrar.query(sql, new Object[]{persona.getCorreo(), password},new BeanPropertyRowMapper<>(LoginDTO.class));
        } catch (Exception e) {
            log.error("errors - login() : {}", e.getCause());
        }
        return null;
    }
    
    public List<RegistoDTO> getLogin(String identificador) {

        try {
            String sql = "select * from registro where identificador = ? ";
            return jdbcRegistrar.query(sql, new Object[]{identificador},new BeanPropertyRowMapper<>(RegistoDTO.class));
        } catch (Exception e) {
            log.error("errors - login() : {}", e.getCause());
        }
        return null;
    }

}
