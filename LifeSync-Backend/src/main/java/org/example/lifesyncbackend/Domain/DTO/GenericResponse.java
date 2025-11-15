package org.example.lifesyncbackend.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {
    private String message;
    private Object data;

    @Builder.Default
    @JsonIgnore
    private HttpStatus status = HttpStatus.OK;

    public ResponseEntity<GenericResponse> buildResponse() {
        return ResponseEntity.status(status).body(this);
    }
}

