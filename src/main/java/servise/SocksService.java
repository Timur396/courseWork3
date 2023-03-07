package servise;

import model.Socks;


import java.util.List;

public interface SocksService  {
    Socks addNewSocks(Socks socks);

    List<Socks> moveSocks(Integer size, String colors, Integer cotton, Integer units);

    List<Socks> deleteSocks(Integer size, String colors, Integer cotton, Integer units);


}
