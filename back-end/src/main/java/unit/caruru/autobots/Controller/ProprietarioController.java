package unit.caruru.autobots.Controller;

import org.springframework.web.bind.annotation.*;
import unit.caruru.autobots.Excecoes.ProprietarioNotFoundException;
import unit.caruru.autobots.Model.Proprietario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProprietarioController {
    private List<Proprietario> proprietarios = new ArrayList<>();

    @PostMapping("/cadastrar/cnh")
    public void inserirCnh(@RequestParam(name = "nome") String nome, @RequestParam(name = "cpf") String cpf,
                           @RequestParam(name = "categoria") String categoria, @RequestParam(name = "validade") String validade) {
        Proprietario pessoa = new Proprietario(nome, cpf, categoria, validade);
        proprietarios.add(pessoa);
    }

    @GetMapping("/cnh/{cpf}")
    public Proprietario getCnhByCpf(@PathVariable("cpf") String cpf) throws ProprietarioNotFoundException {
        for (int i=0; i<proprietarios.size(); i++) {
            if (Objects.equals(proprietarios.get(i).getCpf(), cpf)){
                return proprietarios.get(i);
            }
        }
        throw new ProprietarioNotFoundException("Proprietário não foi encontrado com o cpf: "+cpf);
    }

    @GetMapping("/excluir/cnh/{cpf}")
    public int excluirCnh(@PathVariable String cpf){
        for (int i = 0; i<proprietarios.size(); i++){
            if (proprietarios.get(i).getCpf().equals(cpf)){
                proprietarios.remove(i);
                return 0;
            }
        }
        return 1;
    }
    @PostMapping("/editar/cnh")
    public void editarCnh(@RequestParam String identificador,
                              @RequestParam(name = "nome") String nome,
                              @RequestParam(name = "cpf") String cpf,
                              @RequestParam(name = "categoria") String categoria,
                              @RequestParam(name = "validade") String validade) {
        excluirCnh(identificador);
        inserirCnh(nome,cpf,categoria,validade);
    }
    @GetMapping("/cnh")
    public List<Proprietario> getAllCnh() {
        return proprietarios;
    }
}

