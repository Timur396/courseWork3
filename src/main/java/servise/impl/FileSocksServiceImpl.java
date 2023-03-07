package servise.impl;


import org.springframework.stereotype.Service;
import servise.FileSocksService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;

@Service
public class FileSocksServiceImpl implements FileSocksService {
    @Value("${path.to.data.file}")
    private String socksFilePath;
    @Value("${name.to.data.file}")
    private String socksFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(socksFilePath, socksFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() throws RuntimeException {
        try {
            return Files.readString(Path.of(socksFilePath, socksFileName));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла");
        }
    }

    @Override
    public File getDataFile() {
        return new File(socksFilePath + " /" + socksFileName);
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(socksFilePath, socksFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(socksFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
