package com.worbun.shareMusic.service;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
public class GoogleCloudStorageService {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    @Value("${gcp.storage.bucket-name}")
    private String bucketName;

    // Método para subir el archivo MP3
    public String uploadFile(MultipartFile file, String userId) throws IOException {
        String objectName = userId + "/" + file.getOriginalFilename();  // El archivo se almacena en una carpeta por usuario
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        return objectName;  // Devuelve el nombre del archivo almacenado
    }

    // Método para obtener una URL temporal de acceso al archivo
    public String getFileUrl(String objectName) {
        BlobId blobId = BlobId.of(bucketName, objectName);
        Blob blob = storage.get(blobId);

        // URL temporal con acceso público (válida por 1 día)
        URL signedUrl = blob.signUrl(1, TimeUnit.DAYS);
        return signedUrl.toString();
    }
}


