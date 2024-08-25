package com.example.hibernateJpa.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VideoDTO extends ResourceDTO {
    private int length;
}
