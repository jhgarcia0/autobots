package unit.caruru.autobots.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

import unit.caruru.autobots.Controller.ProprietarioController;
import unit.caruru.autobots.Excecoes.ProprietarioNotFoundException;
import unit.caruru.autobots.Excecoes.VeiculoNotFoundException;
import unit.caruru.autobots.Model.Multa;
import unit.caruru.autobots.Model.Proprietario;
import unit.caruru.autobots.Model.Veiculo;

import java.util.ArrayList;
import java.util.List;

@RestController
public class veiculoController {
    public List<Veiculo> veiculos = new ArrayList<>();
    @Autowired
    private ProprietarioController cnhController;

    private final RedirectView redirectView = new RedirectView();
    @PostMapping("/cadastrar/veiculo")
    @ResponseBody
    public RedirectView cadastrarVeiculo(@RequestParam(name = "placa") String placa,
                                 @RequestParam(name = "modelo") String modelo,
                                 @RequestParam(name = "marca") String marca,
                                 @RequestParam(name = "cor") String cor,
                                 @RequestParam(name = "cpf") String cpf,
                                 @RequestParam(name = "ano") int ano) throws ProprietarioNotFoundException{
        Proprietario proprietario = cnhController.getCnhByCpf(cpf);
        Veiculo veiculo = new Veiculo(placa, modelo, marca, cor, proprietario, ano);
        veiculos.add(veiculo);

        redirectView.setUrl("/create-vehicle.html");
        return redirectView;
    }
    @GetMapping("/veiculo/{identificador}")
    public Veiculo getVeiculo(@PathVariable String identificador) throws VeiculoNotFoundException{
        if(identificador.length() == 11){
            return getVeiculoByCpf(identificador);
        }
        return getVeiculoByPlaca(identificador);
    }
    private Veiculo getVeiculoByPlaca(String placa) throws VeiculoNotFoundException{
        for(int i = 0; i<veiculos.size(); i++){
            if (veiculos.get(i).getPlaca().equals(placa)){
                return veiculos.get(i);
            }
        }
        throw new VeiculoNotFoundException("Veiculo não foi encontrado. Identificador: "+placa);
    }

    private Veiculo getVeiculoByCpf(String cpf) throws VeiculoNotFoundException{
        for(int i = 0; i < veiculos.size(); i++){
            if (veiculos.get(i).getProprietario().getCpf().equals(cpf)){
                return veiculos.get(i);
            }
        }
        throw new VeiculoNotFoundException("Veiculo não foi encontrado. Identificador: "+cpf);
    }
    @GetMapping("multas/{identificador}")
    private List<Multa> getMultas(@PathVariable String identificador) throws VeiculoNotFoundException{
        if(identificador.length() == 11){
            return getMultasByCpf(identificador);
        }
        return getMultasByPlaca(identificador);
    }

    private List<Multa> getMultasByCpf(String cpf) throws VeiculoNotFoundException{
        for (int i = 0; i<veiculos.size(); i++){
            if(veiculos.get(i).getProprietario().getCpf().equals(cpf)){
                return veiculos.get(i).getMultas();
            }
        }
        throw new VeiculoNotFoundException("Veiculo não foi encontrado. Identificador: "+cpf);
    }

    private List<Multa> getMultasByPlaca(String placa) throws VeiculoNotFoundException {
        for (int i = 0; i<veiculos.size(); i++){
            if(veiculos.get(i).getPlaca().equals(placa)){
                return veiculos.get(i).getMultas();
            }
        }
        throw new VeiculoNotFoundException("Veiculo não foi encontrado. Identificador: "+placa);
    }
    @PostMapping("/excluir/{placa}")
    public ResponseEntity<String> excluirVeiculo(@RequestBody Map<String, String> requestBody) {
      String placa = requestBody.get("placa");
    
      for (int i = 0; i < veiculos.size(); i++) {
        if (veiculos.get(i).getPlaca().equals(placa)) {
          veiculos.remove(i);
          return ResponseEntity.ok().build();
        }
      }
    
      return ResponseEntity.notFound().build();
    }
    
    private int excluirVeiculoPorCpf(String cpf) {
        for (int i = 0; i<veiculos.size(); i++){
            if (veiculos.get(i).getProprietario().getCpf().equals(cpf)){
                veiculos.remove(i);
                return 0;
            }
        }
        return 1;
    }
    @PostMapping("/editar/veiculo")
    public void editarVeiculo(@RequestParam(name="identificador") String identificador,
                              @RequestParam(name = "placa") String placa,
                              @RequestParam(name = "modelo") String modelo,
                              @RequestParam(name = "marca") String marca,
                              @RequestParam(name = "cor") String cor,
                              @RequestParam(name = "cpf") String cpf,
                              @RequestParam(name = "ano") int ano,
                              @RequestParam(name = "multas") List<Multa> multas) throws ProprietarioNotFoundException {
        cadastrarVeiculo(placa,modelo,marca,cor,cpf,ano);
        cadastrarMultas(identificador, multas);
        Map<String,String> requestBody = Map.of("placa", identificador);
        excluirVeiculo(requestBody);

    }

    @PostMapping("cadastrar/multa")
    public int cadastrarMultas(@RequestParam(name="identificador") String identificador,
                               @RequestBody List<Multa> multas){
        try{
            Veiculo veiculo = getVeiculo(identificador);
            List<Multa> multas_antigo = veiculo.getMultas();
            if (multas_antigo == null){
                veiculo.setMultas(multas);
                return 0;
            }
            for(Multa multa: multas){
                multas_antigo.add(multa);
            }
            veiculo.setMultas(multas_antigo);
            return 0;
        }catch(VeiculoNotFoundException err){
            return 1;
        }
    }
    @GetMapping("/veiculos")
    public List<Veiculo> getAllVeiculos() {
        return veiculos;
    }

    @GetMapping("/multas/placa/{identificador}")
    private ResponseEntity<List<Multa>> getMultasByPlaca(@PathVariable String placa) throws VeiculoNotFoundException {
    try {
        Veiculo veiculo = getVeiculoByPlaca(placa);
        List<Multa> multas = veiculo.getMultas();
        return ResponseEntity.ok().body(multas);
    } catch (VeiculoNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
    }
    @PostMapping("/transferencia-veiculo")
    public RedirectView transferirVeiculo(@RequestParam(name = "inputPlaca") String placa,
                                          @RequestParam(name = "inputAntigoProprietario") String antigoProprietarioCpf,
                                          @RequestParam(name = "inputCPFProprietario") String novoProprietarioCpf) throws ProprietarioNotFoundException, VeiculoNotFoundException {

        Veiculo veiculo = getVeiculoByPlaca(placa);
        Proprietario antigoProprietario = veiculo.getProprietario();
        if (antigoProprietario == null || !antigoProprietario.getCpf().equals(antigoProprietarioCpf)) {
            throw new ProprietarioNotFoundException("Antigo proprietário não foi encontrado. CPF: " + antigoProprietarioCpf);
        }
        Proprietario novoProprietario = cnhController.getCnhByCpf(novoProprietarioCpf);

        veiculo.setProprietario(novoProprietario);

        return new RedirectView("/transfer.html");
    

    }

    
}
