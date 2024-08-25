package com.example.hibernateJpa.mappers;

import com.example.hibernateJpa.entities.File;
import com.example.hibernateJpa.entities.Resource;
import com.example.hibernateJpa.entities.Text;
import com.example.hibernateJpa.entities.Video;
import com.example.hibernateJpa.models.FileDTO;
import com.example.hibernateJpa.models.ResourceDTO;
import com.example.hibernateJpa.models.TextDTO;
import com.example.hibernateJpa.models.VideoDTO;

import  org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    ResourceDTO toResourceDTO(Resource resource);
    TextDTO toTextDTO(Text text);
    VideoDTO toVideoDTO(Video video);
    FileDTO toFileDTO(File file);

    Resource toResource(ResourceDTO resourceDTO);
    Text toText(TextDTO textDTO);
    Video toVideo(VideoDTO videoDTO);
    File toFile(FileDTO fileDTO);

}
