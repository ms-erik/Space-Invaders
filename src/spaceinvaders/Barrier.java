/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

/**
 *
 * @author erik
 */
public class Barrier extends Entity {
    
    
    public Barrier(int x, int y){
        super(x,y);
        this.lives = 2;
    }

    public void tiraVida(){
        this.lives--;
        if(this.lives == 0) alive = false;
    }
    
    
}
