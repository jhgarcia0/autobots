package unit.caruru.autobots.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unit.caruru.autobots.Model.Proprietario;
import unit.caruru.autobots.Repository.ClientesRepository;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository repository;
    public void inserirCliente(Proprietario cliente){
        validarCliente(cliente);
        this.repository.inserirCliente(cliente);
    }

    public void validarCliente(Proprietario cliente){

    }
}
