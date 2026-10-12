package medconnect.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entidade Consulta - Representa uma consulta médica
 */
public class Consulta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Paciente paciente;
    private Medico medico;
    private Date dataHora;
    private String motivo;

    public Consulta() {}

    public Consulta(Integer id, Paciente paciente, Medico medico, Date dataHora, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.motivo = motivo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    
    public Date getDataHora() { return dataHora; }
    public void setDataHora(Date dataHora) { this.dataHora = dataHora; }
    
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(id, consulta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", paciente=" + paciente + ", medico=" + medico + '}';
    }
}