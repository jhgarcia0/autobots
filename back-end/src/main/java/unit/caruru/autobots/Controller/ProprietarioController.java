package unit.caruru.autobots.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unit.caruru.autobots.Model.Proprietario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProprietarioController {
    private List<Proprietario> proprietarios = new ArrayList<>();
    @PostMapping("/cadastrar/cnh")
    @ResponseBody
    private void inserirCnh(@RequestParam(name="nome") String nome, @RequestParam(name="cpf") String cpf,
                            @RequestParam(name="categoria")String categoria, @RequestParam(name="validade") String validade){
        Proprietario pessoa = new Proprietario(nome, cpf, categoria, validade);
        proprietarios.add(pessoa);
        System.out.println(pessoa);
    }

    @GetMapping("/cnh/{cpf}")
    public Proprietario getCnhByCpf(@PathVariable("cpf") String cpf){
        for (int i=0; i<proprietarios.size(); i++) {
            if (Objects.equals(proprietarios.get(i).getCpf(), cpf)){
                return proprietarios.get(i);
            }
        }
        return new Proprietario("errado","errado","errado",null);
    }
}
