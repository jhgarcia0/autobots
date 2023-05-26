package unit.caruru.autobots.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unit.caruru.autobots.Model.Proprietario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
<<<<<<< HEAD:back-end/src/main/java/unit/caruru/autobots/Controller/ProprietarioController.java
public class ProprietarioController {

    public List<Proprietario> proprietarios = new ArrayList<>();
    @GetMapping("/cadastrar/cnh")
=======
public class CnhController {
    private List<Proprietario> proprietarios = new ArrayList<>();
    @PostMapping("/cadastrar/cnh")
    @ResponseBody
>>>>>>> da6fff1c3a56665817c673e73494a54143d52894:back-end/src/main/java/unit/caruru/autobots/Controller/CnhController.java
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
