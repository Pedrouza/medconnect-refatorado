package medconnect.dao;

import medconnect.model.Consulta;
import medconnect.model.Paciente;
import medconnect.model.Medico;
import medconnect.repository.Repository;
import medconnect.exception.BancoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para acesso a dados de Consulta
 */
public class ConsultaDAO implements Repository<Consulta> {
    
    private ConexaoDB conexaoDB;
    private PacienteDAO pacienteDAO;
    
    public ConsultaDAO() {
        this.conexaoDB = new ConexaoDB();
        this.pacienteDAO = new PacienteDAO();
    }
    
    @Override
    public void salvar(Consulta consulta) {
        String sql = "INSERT INTO consultas (id_paciente, id_medico, data_hora, motivo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, consulta.getPaciente().getId());
            stmt.setInt(2, consulta.getMedico().getId());
            stmt.setTimestamp(3, new java.sql.Timestamp(consulta.getDataHora().getTime()));
            stmt.setString(4, consulta.getMotivo());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao salvar consulta: " + e.getMessage());
        }
    }
    
    @Override
    public void atualizar(Consulta consulta) {
        String sql = "UPDATE consultas SET id_paciente=?, id_medico=?, data_hora=?, motivo=? WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, consulta.getPaciente().getId());
            stmt.setInt(2, consulta.getMedico().getId());
            stmt.setTimestamp(3, new java.sql.Timestamp(consulta.getDataHora().getTime()));
            stmt.setString(4, consulta.getMotivo());
            stmt.setInt(5, consulta.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao atualizar consulta: " + e.getMessage());
        }
    }
    
    @Override
    public void excluir(Integer id) {
        String sql = "DELETE FROM consultas WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao excluir consulta: " + e.getMessage());
        }
    }
    
    @Override
    public Consulta buscarPorId(Integer id) {
        String sql = "SELECT * FROM consultas WHERE id=?";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSet(rs);
            }
            return null;
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao buscar consulta: " + e.getMessage());
        }
    }
    
    @Override
    public List<Consulta> listarTodos() {
        String sql = "SELECT * FROM consultas ORDER BY data_hora";
        List<Consulta> consultas = new ArrayList<>();
        
        try (Connection conn = ConexaoDB.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                consultas.add(mapearResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new BancoException("Erro ao listar consultas: " + e.getMessage());
        }
        
        return consultas;
    }
    
    @Override
    public List<Consulta> buscarPorNome(String nome) {
        // Implementação específica para consultas
        return new ArrayList<>();
    }
    
    private Consulta mapearResultSet(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(rs.getInt("id"));
        
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        
        consulta.setPaciente(pacienteDAO.buscarPorId(rs.getInt("id_paciente")));
        consulta.setMedico(medicoDAO.buscarPorId(rs.getInt("id_medico")));
        consulta.setDataHora(rs.getTimestamp("data_hora"));
        consulta.setMotivo(rs.getString("motivo"));
        
        return consulta;
    }
}