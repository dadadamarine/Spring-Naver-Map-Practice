package com.mappractice.demo.acceptanceTest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class CustomMultipartFile extends MockMultipartFile {

    public CustomMultipartFile(
            String name, @Nullable String originalFilename, @Nullable String contentType, @Nullable byte[] content) {
        super(name,originalFilename, contentType,content);
    }

    @Override
    @JsonIgnore
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
