package org.example.lifesyncbackend.Domain.DTO.common;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @JsonProperty("statusname") // Esto lo hacemos para que al momento de  mandar el json no se mande como statusName y mantener una convencion en los nombres
    private String statusName;


}
