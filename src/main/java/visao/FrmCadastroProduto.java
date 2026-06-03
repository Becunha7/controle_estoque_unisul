/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Produto;

/**
 *
 * @author PICHAU
 */
public class FrmCadastroProduto extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmCadastroProduto.class.getName());

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private int produtoId = -1;
    private List<Categoria> categorias = new ArrayList<>();

    /**
     * Creates new form FrmCadastroProduto
     */
    public FrmCadastroProduto() {
        initComponents();
        configurarTela();
        carregarCategorias();
    }

    public FrmCadastroProduto(Produto produto) {
        initComponents();
        configurarTela();
        carregarCategorias();
        carregarProduto(produto);
    }

    private void configurarTela() {
        setTitle("Cadastro de Produto");
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new java.awt.Color(250, 250, 250));
        getRootPane().setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18));
        setMinimumSize(new java.awt.Dimension(420, 430));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLUnidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLPreco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLMinimo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLMaximo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTFNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTFQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTFPreco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTFMinimo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTFMaximo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JCBUnidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JCBcategoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));

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

    private void carregarCategorias() {
        categorias = categoriaDAO.listar();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Categoria categoria : categorias) {
            model.addElement(categoria.getNome());
        }
        JCBcategoria.setModel(model);
        if (model.getSize() == 0) {
            model.addElement("Sem categorias");
        }
        JCBUnidade.setModel(new DefaultComboBoxModel<>(new String[] {"UN", "KG", "L", "PACOTE", "CX"}));
    }

    private void carregarProduto(Produto produto) {
        produtoId = produto.getId();
        JTFNome.setText(produto.getNome());
        JTFQuantidade.setText(String.valueOf(produto.getQuantidade()));
        JTFPreco.setText(String.valueOf(produto.getPreco()));
        JTFMinimo.setText(String.valueOf(produto.getQntdMin()));
        JTFMaximo.setText(String.valueOf(produto.getQntdMax()));
        JCBUnidade.setSelectedItem(produto.getUnidade());
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdCategoria() == produto.getId_categoria()) {
                JCBcategoria.setSelectedIndex(i);
                break;
            }
        }
        JBSalvar.setText("Atualizar");
    }

    private void limparCampos() {
        produtoId = -1;
        JTFNome.setText("");
        JTFQuantidade.setText("");
        JTFPreco.setText("");
        JTFMinimo.setText("");
        JTFMaximo.setText("");
        JCBUnidade.setSelectedIndex(0);
        if (JCBcategoria.getItemCount() > 0) {
            JCBcategoria.setSelectedIndex(0);
        }
        JTFNome.requestFocus();
        JBSalvar.setText("Salvar");
    }

    private void salvarProduto() {
        try {
            String nome = JTFNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o nome do produto.", "Atenção", JOptionPane.WARNING_MESSAGE);
                JTFNome.requestFocus();
                return;
            }

            if (categorias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cadastre uma categoria antes de salvar o produto.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int quantidade = Integer.parseInt(JTFQuantidade.getText().trim());
            double preco = Double.parseDouble(JTFPreco.getText().trim());
            int minimo = Integer.parseInt(JTFMinimo.getText().trim());
            int maximo = Integer.parseInt(JTFMaximo.getText().trim());
            String unidade = JCBUnidade.getSelectedItem().toString();
            Categoria categoria = categorias.get(JCBcategoria.getSelectedIndex());

            Produto produto = new Produto();
            produto.setId(produtoId);
            produto.setNome(nome);
            produto.setQuantidade(quantidade);
            produto.setPreco(preco);
            produto.setQntdMin(minimo);
            produto.setQntdMax(maximo);
            produto.setUnidade(unidade);
            produto.setId_categoria(categoria.getIdCategoria());

            if (produtoId <= 0) {
                produtoDAO.cadastrar(produto);
            } else {
                produtoDAO.atualizar(produto);
            }

            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preencha corretamente os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
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
        JLNome = new javax.swing.JLabel();
        JTFNome = new javax.swing.JTextField();
        JLCategoria = new javax.swing.JLabel();
        JCBcategoria = new javax.swing.JComboBox<>();
        JLUnidade = new javax.swing.JLabel();
        JCBUnidade = new javax.swing.JComboBox<>();
        JLPreco = new javax.swing.JLabel();
        JTFPreco = new javax.swing.JTextField();
        JLQuantidade = new javax.swing.JLabel();
        JTFQuantidade = new javax.swing.JTextField();
        JLMinimo = new javax.swing.JLabel();
        JTFMinimo = new javax.swing.JTextField();
        JLMaximo = new javax.swing.JLabel();
        JTFMaximo = new javax.swing.JTextField();
        JBSalvar = new javax.swing.JButton();
        JBLimpar = new javax.swing.JButton();
        JBFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cadastro de Produto");

        JLNome.setText("Nome:");

        JLCategoria.setText("Categoria:");

        JLUnidade.setText("Unidade:");

        JLPreco.setText("Preço unitário:");

        JLQuantidade.setText("Quantidade em estoque:");

        JLMinimo.setText("Quantidade mínima:");

        JLMaximo.setText("Quantidade máxima:");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JLNome)
                                    .addComponent(JLUnidade)
                                    .addComponent(JLPreco)
                                    .addComponent(JLQuantidade)
                                    .addComponent(JLMinimo)
                                    .addComponent(JLMaximo)
                                    .addComponent(JLCategoria))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JTFMaximo)
                                    .addComponent(JTFMinimo)
                                    .addComponent(JTFQuantidade)
                                    .addComponent(JTFPreco)
                                    .addComponent(JCBUnidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JCBcategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JTFNome)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JBSalvar)
                                .addGap(32, 32, 32)
                                .addComponent(JBLimpar)
                                .addGap(34, 34, 34)
                                .addComponent(JBFechar)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLNome)
                    .addComponent(JTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLCategoria)
                    .addComponent(JCBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLUnidade)
                    .addComponent(JCBUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLPreco)
                    .addComponent(JTFPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLQuantidade)
                    .addComponent(JTFQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLMinimo)
                    .addComponent(JTFMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLMaximo)
                    .addComponent(JTFMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        salvarProduto();
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

        java.awt.EventQueue.invokeLater(() -> new FrmCadastroProduto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBUnidade;
    private javax.swing.JComboBox<String> JCBcategoria;
    private javax.swing.JButton JBFechar;
    private javax.swing.JButton JBLimpar;
    private javax.swing.JButton JBSalvar;
    private javax.swing.JLabel JLMaximo;
    private javax.swing.JLabel JLMinimo;
    private javax.swing.JLabel JLCategoria;
    private javax.swing.JLabel JLNome;
    private javax.swing.JLabel JLPreco;
    private javax.swing.JLabel JLQuantidade;
    private javax.swing.JLabel JLUnidade;
    private javax.swing.JTextField JTFMaximo;
    private javax.swing.JTextField JTFMinimo;
    private javax.swing.JTextField JTFNome;
    private javax.swing.JTextField JTFPreco;
    private javax.swing.JTextField JTFQuantidade;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
