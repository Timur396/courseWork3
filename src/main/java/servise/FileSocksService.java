package servise;

import java.io.File;
import java.nio.file.Path;

public interface FileSocksService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();



}
