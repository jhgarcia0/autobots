package unit.caruru.autobots.Model;

public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private String cor;
    private Proprietario proprietario;
    private int ano;

    public Veiculo(String placa, String modelo, String marca, String cor, Proprietario proprietario, int ano) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.proprietario = proprietario;
        this.ano = ano;
    }

    //Getters and Setters
    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
    public String getCor() {return cor;}
    public void setCor(String cor) {this.cor = cor;}
    public Proprietario getProprietario() {return proprietario;}
    public void setProprietario(Proprietario proprietario) {this.proprietario = proprietario;}
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
}
