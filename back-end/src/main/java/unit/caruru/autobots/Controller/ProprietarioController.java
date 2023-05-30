package unit.caruru.autobots.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import unit.caruru.autobots.Excecoes.ProprietarioNotFoundException;
import unit.caruru.autobots.Model.Proprietario;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProprietarioController {
    private List<Proprietario> proprietarios = new ArrayList<>();

    @PostMapping("/cadastrar/cnh")
    public RedirectView inserirCnh(@RequestParam(name = "nome") String nome, @RequestParam(name = "cpf") String cpf,
                           @RequestParam(name = "categoria") String categoria, @RequestParam(name = "validade") String validade) {
        Proprietario pessoa = new Proprietario(nome, cpf, categoria, validade);
        proprietarios.add(pessoa);
        
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/create-CNH.html");
        return redirectView;
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

    @PostMapping("/excluir/cnh")
    public ResponseEntity<String> excluirCnh(@RequestBody Map<String, String> requestBody) {
      String cpf = requestBody.get("cpf");
    
      for (int i = 0; i < proprietarios.size(); i++) {
        if (Objects.equals(proprietarios.get(i).getCpf(), cpf)) {
          proprietarios.remove(i);
          return ResponseEntity.ok().build();
        }
      }
    
      return ResponseEntity.notFound().build();
    }
    
    
    @PostMapping("/editar/cnh")
    public void editarCnh(@RequestParam String identificador,
                              @RequestParam(name = "nome") String nome,
                              @RequestParam(name = "cpf") String cpf,
                              @RequestParam(name = "categoria") String categoria,
                              @RequestParam(name = "validade") String validade) {
        inserirCnh(nome,cpf,categoria,validade);
    }
    @GetMapping("/cnh")
    public List<Proprietario> getAllCnh() {
        return proprietarios;
    }
}

