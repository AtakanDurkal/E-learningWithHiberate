package com.example.hibernateJpa.models;

import lombok.Data;

@Data
public class ResourceDTO {
    private Integer id;
    private String name;
    private int size;
    private String url;
    private Integer lectureId;
}

