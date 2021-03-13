package net.buscacio.vacccinationApi.exceptionhandler;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude
@Data
public class Exception {

    private Integer status;
    private LocalDateTime dateTime;
    private String title;

}
