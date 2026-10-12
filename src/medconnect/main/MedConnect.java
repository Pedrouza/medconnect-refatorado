package medconnect.main;

import medconnect.dao.ConexaoDB;
import medconnect.view.Dashboard;
import medconnect.view.Login;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe principal do sistema MedConnect
 */
public class MedConnect {
    
    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : 
                 javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Testar conexão com banco
        testarConexao();
        
        // Abrir tela de login
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
    
    private static void testarConexao() {
        try {
            ConexaoDB db = new ConexaoDB();
            Connection conn = ConexaoDB.getConexao();
            if (conn != null) {
                System.out.println("✓ Conexão com banco estabelecida com sucesso!");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("✗ Erro ao conectar com banco: " + e.getMessage());
        }
    }
}