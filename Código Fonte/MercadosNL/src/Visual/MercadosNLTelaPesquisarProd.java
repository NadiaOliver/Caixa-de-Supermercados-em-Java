/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import DAO.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author user
 */
public class MercadosNLTelaPesquisarProd extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public MercadosNLTelaPesquisarProd() throws ClassNotFoundException {
        initComponents();
        con = ConectaBanco.conectabanco();
        listarProdutos();
       
    }

    public void listarProdutos() throws ClassNotFoundException {
       
        String sql = "select  id_produto,nome,cod_barras,trunc(preco::numeric,2),marca  from produto order by id_produto Asc";

        try {

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabelaProdutos.setModel(DbUtils.resultSetToTableModel(rs));
            
             tabelaProdutos.getColumnModel().getColumn(0).setHeaderValue("Cod Produto");
            tabelaProdutos.getColumnModel().getColumn(1).setHeaderValue("Nome");
            tabelaProdutos.getColumnModel().getColumn(2).setHeaderValue("Código de Barras");
            tabelaProdutos.getColumnModel().getColumn(3).setHeaderValue("Preço");
            tabelaProdutos.getColumnModel().getColumn(4 ).setHeaderValue("Marca");
            tabelaProdutos.getTableHeader().resizeAndRepaint();
            
            
          

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void pesquisarProdutos() {
        String sql = "select  id_produto, nome,cod_barras,trunc(preco::numeric,2),marca from produto where nome like ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tabelaProdutos.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabelaProdutos.getColumnModel().getColumn(0).setHeaderValue("Cod Prod");
            tabelaProdutos.getColumnModel().getColumn(1).setHeaderValue("Nome");
            tabelaProdutos.getColumnModel().getColumn(2).setHeaderValue("Código de Barras");
            tabelaProdutos.getColumnModel().getColumn(3).setHeaderValue("Preço");
            tabelaProdutos.getColumnModel().getColumn(4).setHeaderValue("Marca");
            tabelaProdutos.getTableHeader().resizeAndRepaint();
        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);
        }

    }

    public void deletar() throws ClassNotFoundException {

        String sql = "delete from produto where id_produto = ?";
        int row = tabelaProdutos.getSelectedRow();
        String valor = tabelaProdutos.getValueAt(row, 0).toString();

        Object[] options = {"Confirmar", "Cancelar"};
        switch (JOptionPane.showOptionDialog(null, "Deseja mesmo excluir?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0])) {
            case 0:
                try {
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(valor));
                    pst.execute();

                    listarProdutos();
                } catch (SQLException error) {

                    JOptionPane.showMessageDialog(null, error);
                }
                break;
        }
    }
    
    
    

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        ButtonEditar = new javax.swing.JButton();
        ButtonExcluir = new javax.swing.JButton();
        ButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        tabelaProdutos.setBorder(new javax.swing.border.MatteBorder(null));
        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        jScrollPane2.setViewportView(tabelaProdutos);

        ButtonEditar.setText("Editar");
        ButtonEditar.setMaximumSize(new java.awt.Dimension(105, 23));
        ButtonEditar.setMinimumSize(new java.awt.Dimension(105, 23));
        ButtonEditar.setPreferredSize(new java.awt.Dimension(105, 23));
        ButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEditarActionPerformed(evt);
            }
        });

        ButtonExcluir.setText("  Excluir");
        ButtonExcluir.setMaximumSize(new java.awt.Dimension(105, 23));
        ButtonExcluir.setMinimumSize(new java.awt.Dimension(105, 23));
        ButtonExcluir.setPreferredSize(new java.awt.Dimension(105, 23));
        ButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExcluirActionPerformed(evt);
            }
        });

        ButtonCancelar.setText("Cancelar");
        ButtonCancelar.setMaximumSize(new java.awt.Dimension(105, 23));
        ButtonCancelar.setMinimumSize(new java.awt.Dimension(105, 23));
        ButtonCancelar.setPreferredSize(new java.awt.Dimension(105, 23));
        ButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/magnifier.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(ButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(ButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
       
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void ButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_ButtonCancelarActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisarProdutos();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void ButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExcluirActionPerformed
        try {
            deletar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MercadosNLTelaPesquisarProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonExcluirActionPerformed

    private void ButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEditarActionPerformed
        
        this.dispose();
        int seleciona = tabelaProdutos.getSelectedRow();
        String nome = tabelaProdutos.getModel().getValueAt(seleciona, 1).toString();
        String marca = tabelaProdutos.getModel().getValueAt(seleciona, 3).toString();
        String preco = tabelaProdutos.getModel().getValueAt(seleciona, 4).toString();
        String codigoBarras = tabelaProdutos.getModel().getValueAt(seleciona, 2).toString();
        String codigo =   tabelaProdutos.getModel().getValueAt(seleciona, 0).toString();
       
        
        MercadosNLTelaEdicaoProd enviaTexto;
        enviaTexto = null;
        try {
            enviaTexto = new MercadosNLTelaEdicaoProd();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MercadosNLTelaPesquisarProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        enviaTexto.setVisible(true);
        enviaTexto.recebeDados(nome,preco,marca,codigoBarras,codigo);
      
    }//GEN-LAST:event_ButtonEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    /*public void preencherTabela(String SQL){
        ArrayList dados = new ArrayList();
        String [] colunas = new String[]{"Código", "Nome", "Marca", "Preço"};
        con = ConectaBanco.conectabanco();
        
    }   */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MercadosNLTelaPesquisarProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MercadosNLTelaPesquisarProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MercadosNLTelaPesquisarProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MercadosNLTelaPesquisarProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MercadosNLTelaPesquisarProd().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MercadosNLTelaPesquisarProd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancelar;
    private javax.swing.JButton ButtonEditar;
    private javax.swing.JButton ButtonExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
