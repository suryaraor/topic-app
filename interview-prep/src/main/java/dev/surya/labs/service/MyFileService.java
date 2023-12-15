package dev.surya.labs.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class MyFileService {

    private final ResourceLoader resourceLoader;

    public MyFileService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource readFile(String fileName) {
        Resource resource = resourceLoader.getResource("file:C:/temp/" + fileName);

        return resource;
    }
}