package unit.caruru.autobots.Model;
import java.text.NumberFormat;
import java.time.LocalDate;

public class Multa {
    String data;
    String local;
    Long valor;
    String descricao;
    public String valorParaReal(){
        NumberFormat formatoReal = NumberFormat.getCurrencyInstance();
        return formatoReal.format(this.valor);
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
