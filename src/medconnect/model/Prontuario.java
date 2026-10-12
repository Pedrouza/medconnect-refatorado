package medconnect.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Prontuario - Representa um prontuário eletrônico
 */
public class Prontuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Consulta consulta;
    private String observacoes;
    private String receita;

    public Prontuario() {}

    public Prontuario(Integer id, Consulta consulta, String observacoes, String receita) {
        this.id = id;
        this.consulta = consulta;
        this.observacoes = observacoes;
        this.receita = receita;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
    
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    
    public String getReceita() { return receita; }
    public void setReceita(String receita) { this.receita = receita; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prontuario prontuario = (Prontuario) o;
        return Objects.equals(id, prontuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Prontuario{" + "id=" + id + ", consulta=" + consulta + '}';
    }
}