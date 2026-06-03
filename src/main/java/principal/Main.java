package principal;

import visao.FrmMenuPrincipal;

/**
 * Classe principal do sistema de controle de estoque.  Inicia a
 * aplicação e exibe a tela de menu principal.
 *
 * @author BeCunha7 
 * @see visao.FrmMenuPrincipal
 */
public class Main{

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            FrmMenuPrincipal menu = new FrmMenuPrincipal();
            menu.setVisible(true);
        });
    }
}
