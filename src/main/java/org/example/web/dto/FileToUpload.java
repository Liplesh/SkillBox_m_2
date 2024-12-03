package org.example.web.dto;

import org.example.app.services.Utils.annotation.NotEmptyFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;

public class FileToUpload {
    @NotEmptyFile
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
