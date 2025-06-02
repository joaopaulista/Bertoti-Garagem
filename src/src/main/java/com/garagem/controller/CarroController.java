package com.garagem.controller;

import jakarta.annotation.PostConstruct;
import com.garagem.model.Carro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carros")
public class CarroController {

    private List<Carro> carros = new ArrayList<>();

    private String geraId() {
        int nextId = carros.size() + 1;
        return String.format("CARRO-%04d", nextId);
    }

    @PostConstruct
    public void init() {
        carros.add(new Carro("CARRO-0001", "Ford", "Ka", 2015, "Hatch"));
        carros.add(new Carro("CARRO-0002", "Toyota", "Corolla", 2020, "Sedan"));
    }

    @GetMapping
    public List<Carro> listarTodos() {
        return carros;
    }

    @GetMapping("/{id}")
    public Optional<Carro> buscarPorId(@PathVariable String id) {
        return carros.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @PostMapping
    public Carro criarCarro(@RequestBody Carro carro) {
        carro.setId(geraId());
        carros.add(carro);
        return carro;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable String id, @RequestBody Carro carro) {
        for (int i = 0; i < carros.size(); i++) {
            if (carros.get(i).getId().equals(id)) {
                carro.setId(id); // manter o ID
                carros.set(i, carro);
                return new ResponseEntity<>(carro, HttpStatus.OK);
            }
        }
        carro.setId(geraId());
        carros.add(carro);
        return new ResponseEntity<>(carro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deletarCarro(@PathVariable String id) {
        carros.removeIf(c -> c.getId().equals(id));
    }
}

