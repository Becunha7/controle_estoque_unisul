/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;

import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.Movimentacao;
import modelo.Produto;

/**
 *
 * @author PICHAU
 */
public class FrmCadastroMovimentacao extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmCadastroMovimentacao.class.getName());

    private final MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private List<Produto> produtos = new ArrayList<>();

    /**
     * Creates new form FrmCadastroMovimentacao
     */
    public FrmCadastroMovimentacao() {
        initComponents();
        configurarTela();
        carregarProdutos();
    }

    private void configurarTela() {
        setTitle("Cadastro de Movimentação");
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new java.awt.Color(250, 250, 250));
        getRootPane().setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18));
        setMinimumSize(new java.awt.Dimension(450, 360));
        setPreferredSize(new java.awt.Dimension(450, 360));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLCProduto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JCBProduto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTFData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTFQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JCBTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        java.awt.Font buttonFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        JBSalvar.setFont(buttonFont);
        JBLimpar.setFont(buttonFont);
        JBFechar.setFont(buttonFont);
        JBSalvar.setOpaque(true);
        JBSalvar.setBackground(new java.awt.Color(33, 150, 243));
        JBSalvar.setForeground(java.awt.Color.WHITE);
        JBLimpar.setOpaque(true);
        JBLimpar.setBackground(new java.awt.Color(96, 125, 139));
        JBLimpar.setForeground(java.awt.Color.WHITE);
        JBFechar.setOpaque(true);
        JBFechar.setBackground(new java.awt.Color(244, 67, 54));
        JBFechar.setForeground(java.awt.Color.WHITE);
    }

    private void carregarProdutos() {
        produtos = produtoDAO.listar();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Produto produto : produtos) {
            model.addElement(produto.getNome());
        }
        if (model.getSize() == 0) {
            model.addElement("Nenhum produto cadastrado");
        }
        JCBProduto.setModel(model);
        JCBTipo.setModel(new DefaultComboBoxModel<>(new String[] {"ENTRADA", "SAIDA"}));
        JTFData.setText(LocalDate.now().toString());
    }

    private void limparCampos() {
        if (JCBProduto.getItemCount() > 0) {
            JCBProduto.setSelectedIndex(0);
        }
        JTFData.setText(LocalDate.now().toString());
        JTFQuantidade.setText("");
        JCBTipo.setSelectedIndex(0);
        JCBProduto.requestFocus();
    }

    private void salvarMovimentacao() {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há produtos cadastrados para movimentação.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int index = JCBProduto.getSelectedIndex();
            if (index < 0 || index >= produtos.size()) {
                JOptionPane.showMessageDialog(this, "Selecione um produto válido.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Produto produto = produtos.get(index);
            String data = JTFData.getText().trim();
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a data da movimentação.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int quantidade = Integer.parseInt(JTFQuantidade.getText().trim());
            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "Informe uma quantidade válida.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String tipo = JCBTipo.getSelectedItem().toString();
            Movimentacao mov = new Movimentacao(produto.getId(), data, quantidade, tipo);
            movimentacaoDAO.registrar(mov);
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe uma quantidade numérica válida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JLCProduto = new javax.swing.JLabel();
        JCBProduto = new javax.swing.JComboBox<>();
        JLData = new javax.swing.JLabel();
        JTFData = new javax.swing.JTextField();
        JLQuantidade = new javax.swing.JLabel();
        JTFQuantidade = new javax.swing.JTextField();
        JLTipo = new javax.swing.JLabel();
        JCBTipo = new javax.swing.JComboBox<>();
        JBSalvar = new javax.swing.JButton();
        JBLimpar = new javax.swing.JButton();
        JBFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cadastro de Movimentação");

        JLCProduto.setText("Produto:");

        JLData.setText("Data (YYYY-MM-DD):");

        JLQuantidade.setText("Quantidade:");

        JLTipo.setText("Tipo:");

        JBSalvar.setText("Salvar");
        JBSalvar.addActionListener(this::JBSalvarActionPerformed);

        JBLimpar.setText("Limpar");
        JBLimpar.addActionListener(this::JBLimparActionPerformed);

        JBFechar.setText("Fechar");
        JBFechar.addActionListener(this::JBFecharActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLCProduto)
                            .addComponent(JLData)
                            .addComponent(JLQuantidade)
                            .addComponent(JLTipo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTFQuantidade)
                            .addComponent(JTFData)
                            .addComponent(JCBTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JCBProduto, 0, 252, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBSalvar)
                .addGap(18, 18, 18)
                .addComponent(JBLimpar)
                .addGap(18, 18, 18)
                .addComponent(JBFechar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLCProduto)
                    .addComponent(JCBProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLData)
                    .addComponent(JTFData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLQuantidade)
                    .addComponent(JTFQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLTipo)
                    .addComponent(JCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBSalvar)
                    .addComponent(JBLimpar)
                    .addComponent(JBFechar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSalvarActionPerformed
        salvarMovimentacao();
    }//GEN-LAST:event_JBSalvarActionPerformed

    private void JBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_JBLimparActionPerformed

    private void JBFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBFecharActionPerformed
        dispose();
    }//GEN-LAST:event_JBFecharActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new FrmCadastroMovimentacao().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBProduto;
    private javax.swing.JComboBox<String> JCBTipo;
    private javax.swing.JButton JBFechar;
    private javax.swing.JButton JBLimpar;
    private javax.swing.JButton JBSalvar;
    private javax.swing.JLabel JLData;
    private javax.swing.JLabel JLCProduto;
    private javax.swing.JLabel JLQuantidade;
    private javax.swing.JLabel JLTipo;
    private javax.swing.JTextField JTFData;
    private javax.swing.JTextField JTFQuantidade;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
