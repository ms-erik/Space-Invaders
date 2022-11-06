/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spaceinvaders;

/**
 *
 * @author erik
 */

import javax.swing.JFrame;

public class SpaceInvaders {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame Tela = new JFrame("Space Invaders");
        Tela.setExtendedState(2);
        Tela.setLayout(null);
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();

        Tela.add(game);
        Tela.addKeyListener(game);
            
        Tela.setVisible(true);

    }
    
}
    

