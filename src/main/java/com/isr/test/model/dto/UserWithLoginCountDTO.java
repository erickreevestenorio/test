package com.isr.test.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithLoginCountDTO {

    @ApiModelProperty(notes = "name of the user")
    private String key;
    @ApiModelProperty(notes = "login count of a user", position = 1)
    private Long value;

}
