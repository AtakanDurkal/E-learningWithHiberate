package com.example.hibernateJpa.service;

import com.example.hibernateJpa.entities.Resource;
import com.example.hibernateJpa.mappers.ResourceMapper;
import com.example.hibernateJpa.models.ResourceDTO;
import com.example.hibernateJpa.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceService {
    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }


    public List<ResourceDTO> getAllResources() {
        List<Resource> resources = repository.findAll();
        return resources.stream()
                .map(ResourceMapper.INSTANCE::toResourceDTO)
                .collect(Collectors.toList());
    }

    public Optional<ResourceDTO> getResourceById(Integer id) {
        Optional<Resource> resource = repository.findById(id);
        return resource.map(ResourceMapper.INSTANCE::toResourceDTO);
    }

    public ResourceDTO createResource(ResourceDTO resourceDTO) {
        Resource resource = ResourceMapper.INSTANCE.toResource(resourceDTO);
        Resource savedResource = repository.save(resource);
        return ResourceMapper.INSTANCE.toResourceDTO(savedResource);
    }

    public void deleteResource(Integer id) {
        repository.deleteById(id);
    }
}
