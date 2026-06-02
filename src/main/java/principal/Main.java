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

        FrmMenuPrincipal menu = new FrmMenuPrincipal();
        menu.setVisible(true);

    }
}
