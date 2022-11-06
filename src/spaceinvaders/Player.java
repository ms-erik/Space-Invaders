/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

/**
 *
 * @author erik
 */
public class Player extends Entity implements shooting{

    boolean canShoot = true;
    
    
    public Player(int x, int y) {
        super(x, y);
        
    }

 
    @Override
    public Shot shot() {
        canShoot = false;
        return new Shot(this.x, this.y -1, 1);
    }


    
}
