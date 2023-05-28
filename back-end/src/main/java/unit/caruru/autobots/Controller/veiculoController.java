package unit.caruru.autobots.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping("/cadastrar/veiculo")
    @ResponseBody
    public void cadastrarVeiculo(@RequestParam(name = "placa") String placa,
                                 @RequestParam(name = "modelo") String modelo,
                                 @RequestParam(name = "marca") String marca,
                                 @RequestParam(name = "cor") String cor,
                                 @RequestParam(name = "cpf") String cpf,
                                 @RequestParam(name = "ano") int ano,
                                 @RequestParam(name = "multas") List<Multa> multas) throws ProprietarioNotFoundException{
        Proprietario proprietario = cnhController.getCnhByCpf(cpf);
        Veiculo veiculo = new Veiculo(placa, modelo, marca, cor, proprietario, ano, multas);
        veiculos.add(veiculo);
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
    @GetMapping("/excluir/veiculo/{identificador}")
    public int excluirVeiculo(@PathVariable String identificador){
        if(identificador.length() == 11){
            return excluirVeiculoPorCpf(identificador);
        }
        return excluirVeiculoPorPlaca(identificador);
    }
    private int excluirVeiculoPorPlaca(String placa) {
        for (int i = 0; i<veiculos.size(); i++){
            if (veiculos.get(i).getPlaca().equals(placa)){
                veiculos.remove(i);
                return 0;
            }
        }
        return 1;
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
    public void editarVeiculo(@RequestParam String identificador,
                              @RequestParam(name = "placa") String placa,
                              @RequestParam(name = "modelo") String modelo,
                              @RequestParam(name = "marca") String marca,
                              @RequestParam(name = "cor") String cor,
                              @RequestParam(name = "cpf") String cpf,
                              @RequestParam(name = "ano") int ano,
                              @RequestParam(name = "multas") List<Multa> multas) throws ProprietarioNotFoundException {
        excluirVeiculo(identificador);
        cadastrarVeiculo(placa,modelo,marca,cor,cpf,ano,multas);
    }

}
