package servise;

import java.io.File;

public interface FileSocksService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();



}
