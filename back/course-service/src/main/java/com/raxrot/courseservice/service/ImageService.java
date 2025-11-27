package com.raxrot.courseservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {
    Map uploadImage(MultipartFile file);

    void deleteImage(String publicId);
}
