package servise.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Socks;
import org.springframework.stereotype.Service;
import servise.FileSocksService;
import servise.SocksService;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class SocksServiseImpl implements SocksService {

    final private FileSocksService fileSocksService;
    private static List<Socks> sockList = new LinkedList<>();

    public SocksServiseImpl(FileSocksService fileSocksService) {
        this.fileSocksService = fileSocksService;
    }

    @Override
    public Socks addNewSocks(Socks socks) {
        if (socks.getSize() != null && socks.getColors() != null && socks.getCotton() > 0) {
            List<Socks> collect = sockList.stream()
                    .filter(socks1 -> (socks.getSize().size.equals(socks1.getSize().size)) &&
                            (socks.getCotton().equals(socks1.getCotton())) &&
                            (socks.getColors().colors.equals(socks1.getColors().colors))).distinct().toList();
            if (!collect.isEmpty()) {
                collect.stream()
                        .peek(socks1 ->
                                socks1.setUnits(socks.getUnits() +
                                        socks1.getUnits())).collect(Collectors.toList());
                saveToFile();
            }
        }
        return socks;
    }

    @Override
    public List<Socks> moveSocks(Integer size, String colors, Integer cotton, Integer units) {
        List<Socks> collect = sockList.stream()
                .filter(socks ->
                        socks.getSize().size.equals(size) &&
                                socks.getCotton().equals(cotton))
                .filter(socks ->
                        socks.getColors().colors.equals(colors))
                .filter(socks ->
                        socks.getUnits() >= (units)).toList();
        if (!collect.isEmpty()) {
            collect.stream()
                    .peek(socks1 ->
                            socks1.setUnits(socks1.getUnits() - units))
                    .collect(Collectors.toUnmodifiableList());
            saveToFile();
        }
        return collect;
    }

    @Override
    public List<Socks> deleteSocks(Integer size, String colors, Integer cotton, Integer units) {
        List<Socks> collect = sockList.stream()
                .filter(socks ->
                        socks.getSize().size.equals(size) &&
                                socks.getCotton().equals(cotton))
                .filter(socks ->
                        socks.getColors().colors.equals(colors))
                .filter(socks ->
                        socks.getUnits() >= (units)).toList();
        if (!collect.isEmpty()) {
            collect.stream()
                    .peek(socks1 ->
                            socks1.setUnits(socks1.getUnits() - units));
            saveToFile();
        }
        return collect;
    }


    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(sockList);
            fileSocksService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при записи файла");
        }
    }
}
