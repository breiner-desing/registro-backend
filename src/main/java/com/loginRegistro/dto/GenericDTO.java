package com.loginRegistro.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericDTO {

    public int status;
    public Object payload;

    public static GenericDTO sucess(Object data) {
        GenericDTO genericDto = new GenericDTO();
        genericDto.setStatus(HttpStatus.OK.value());
        genericDto.setPayload(data);

        return genericDto;
    }

    public static GenericDTO failed(Object data) {
        GenericDTO genericDto = new GenericDTO();
        genericDto.setStatus(HttpStatus.UNAUTHORIZED.value());
        genericDto.setPayload(data);

        return genericDto;
    }

}
