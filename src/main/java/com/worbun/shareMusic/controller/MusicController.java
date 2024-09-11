package com.worbun.shareMusic.controller;

import com.worbun.shareMusic.service.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private GoogleCloudStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMusicFile(@RequestParam("file") MultipartFile file, @RequestParam String userId) {
        try {
            String objectName = storageService.uploadFile(file, userId);
            return ResponseEntity.ok("Archivo subido exitosamente: " + objectName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir el archivo");
        }
    }

    @GetMapping("/files/{userId}/{fileName}")
    public ResponseEntity<String> getMusicFileUrl(@PathVariable String userId, @PathVariable String fileName) {
        String objectName = userId + "/" + fileName;
        String fileUrl = storageService.getFileUrl(objectName);
        return ResponseEntity.ok(fileUrl);
    }
}

