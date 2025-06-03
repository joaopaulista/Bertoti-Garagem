package com.garagem.controller;

import com.garagem.model.Veiculo;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private List<Veiculo> veiculos = new ArrayList<>();

    private int lastId = 0;

    private String geraId() {
        lastId++;
        return String.format("%04d", lastId);
    }


//    @PostConstruct
//    public void init() {
//        veiculos.add(new Veiculo("0001", "carro", "Ford", "Ka", 2015, 85));
//        veiculos.add(new Veiculo("0002", "carro", "Toyota", "Corolla", 2020, 110));
//        veiculos.add(new Veiculo("0003", "moto", "Honda", "CG 160", 2021, 15));
//        veiculos.add(new Veiculo("0004", "van", "Fiat", "Ducato", 2019, 130));
//        veiculos.add(new Veiculo("0005", "caminhao", "Volvo", "FH", 2018, 400));
//    }

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
        Optional<Veiculo> veiculoRemovido = veiculos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();

        if (veiculoRemovido.isPresent()) {
            veiculos.remove(veiculoRemovido.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
