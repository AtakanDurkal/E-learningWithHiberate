package com.example.hibernateJpa.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextDTO extends ResourceDTO {
    private String content;
}
