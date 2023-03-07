package controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.Socks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servise.FileSocksService;
import servise.SocksService;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/socks")
@Tag(name = "Список носков файл", description = "Файл для скачивания")
public class SocksConroller {
    private final SocksService socksService;

    public SocksConroller(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    @Operation(summary = "Носки")
    public String Socks() {
        return "Магазин носков";
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление новых носков", description = "Размер,цвет,состав,количество")
    public ResponseEntity<Socks> addNewSocks(@RequestBody Socks socks) {
        Socks socks1 = socksService.addNewSocks(socks);
        return new ResponseEntity<>(socks1, HttpStatus.CREATED);
    }

    @PutMapping("/move")
    @Operation(summary = "Перемещение товара на склад", description = "вводим :размер,цвет,состав,количество")
    public ResponseEntity<List<Socks>> moveSokc(@RequestParam(name = "Размер: ") Integer size1,
                                                @RequestParam(name = "Цвет: ") String colors1,
                                                @RequestParam(name = "Доля хлопка : ") Integer cotton1,
                                                @RequestParam(name = "Количество: ") Integer unit1) {
        List<Socks> socksList = socksService.moveSocks(size1, colors1, cotton1, unit1);
        return new ResponseEntity<>(socksList, HttpStatus.CREATED);
    }

    @DeleteMapping("/faulty")
    @Operation(summary = "Списание брака", description = "водим: размер,цвет,состав,количество")
    public ResponseEntity<List<Socks>> deleteSocks(@RequestParam(name = "Размер: ") Integer size1,
                                                   @RequestParam(name = "Цвет") String colors1,
                                                   @RequestParam(name = "Хлопок") Integer cotton1,
                                                   @RequestParam(name = "Количество") Integer unit1) {
        List<Socks> socksList = socksService.deleteSocks(size1, colors1, cotton1, unit1);
        return new ResponseEntity<>(socksList, HttpStatus.CREATED);
    }


}
