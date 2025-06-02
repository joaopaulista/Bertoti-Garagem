package com.garagem.controller;

import com.garagem.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private List<Veiculo> veiculos = new ArrayList<>();

    private String geraId() {
        int nextId = veiculos.size() + 1;
        return String.format("%04d", nextId);
    }

    @PostConstruct
    public void init() {
        veiculos.add(new Carro("0001", "Ford", "Ka", 2015, "Hatch"));
        veiculos.add(new Carro("0002", "Toyota", "Corolla", 2020, "Sedan"));
        veiculos.add(new Moto("0003", "Honda", "CG 160", 2021, 160));
        veiculos.add(new Van("0004", "Fiat", "Ducato", 2019, 15));
        veiculos.add(new Caminhao("0005", "Volvo", "FH", 2018, 20.5));
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable String id) {
        return veiculos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Veiculo criarVeiculo(@RequestBody Veiculo veiculo) {
        veiculo.setId(geraId());
        veiculos.add(veiculo);
        return veiculo;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable String id, @RequestBody Veiculo veiculo) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getId().equals(id)) {
                veiculo.setId(id);
                veiculos.set(i, veiculo);
                return ResponseEntity.ok(veiculo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable String id) {
        boolean removido = veiculos.removeIf(v -> v.getId().equals(id));
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
