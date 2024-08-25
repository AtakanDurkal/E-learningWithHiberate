package com.example.hibernateJpa.models;

import lombok.Data;

@Data
public class FileDTO extends ResourceDTO {
    private String fileType;
    private int downloadCount;
}
