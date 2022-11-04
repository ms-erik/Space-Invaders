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
    
    public Player(int x, int y) {
        super(x, y);
        
    }

 
    public Shot shot() {
        return new Shot(this.x, this.y -1, 1);
    }

    
}
