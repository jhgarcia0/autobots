package unit.caruru.autobots.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unit.caruru.autobots.Model.Proprietario;
import unit.caruru.autobots.Repository.ProprietarioRepository;

@Service
public class ProprietariosService {
    @Autowired
    private ProprietarioRepository repository;
    public void inserirProprietario(Proprietario proprietario){
        validarCliente(proprietario);
        this.repository.inserirProprietario(proprietario);
    }

    public void validarCliente(Proprietario proprietario){

    }
}
