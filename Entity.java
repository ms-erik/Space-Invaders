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
    boolean moveRight;
    boolean moveLeft;
    public int lives;
    boolean alive;
    public void Dx(int x){
        this.x += x;
    }
    public void Dy(int y){
        this.y += y;
    }
    public Entity(int x, int y){
        this.x = x;
        this.y =y;
        moveRight = false;
        moveLeft = false;
        lives = 3;
        alive = true;
    }
    
    
}
