package unit.caruru.autobots.Model;

import java.time.LocalDate;

public class Proprietario {
    private String nome;
    private String cpf;
    private String categoria;
    private String validade;
    public Proprietario(String nome, String cpf, String categoria, String validade){
        this.nome = nome;
        this.cpf = cpf;
        this.categoria = categoria;
        this.validade = validade;
    }
    public String getValidade() {return validade;}

    public void setValidade(String validade) {this.validade = validade;}

    public String getCpf() {
        return cpf;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
