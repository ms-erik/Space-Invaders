/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

/**
 *
 * @author erik
 */
public class Entity {
    public int x;
    public int y;
    public int lives;
    boolean alive;
    public void Dx(int x){
        if(x > 0 && this.x < 19) this.x += x;
        else if (x < 0 && this.x > 0) this.x += x;
    }
    public void Dy(int y){
        this.y += y;
    }
    public Entity(int x, int y){
        this.x = x;
        this.y =y;
        lives = 3;
        alive = true;
    }
    
    
}
