package com.example.hibernateJpa.controller;

import com.example.hibernateJpa.models.ResourceDTO;
import com.example.hibernateJpa.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<ResourceDTO> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable Integer id) {
        Optional<ResourceDTO> resourceDTO = resourceService.getResourceById(id);
        return resourceDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResourceDTO createResource(@RequestBody ResourceDTO resourceDTO) {
        return resourceService.createResource(resourceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Integer id) {
        resourceService.deleteResource(id);
    }
}
