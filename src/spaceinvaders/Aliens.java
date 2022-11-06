/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

/**
 *
 * @author erik    public Aliens(int x, int y){

 */
public class Aliens extends Entity implements shooting{
    
    
      public Aliens(int x, int y){
        super(x,y);
         this.lives=2;
    }
    
    public Shot shot(){
        return new Shot(this.x, this.y+1, 2);
    }
    
     public void tiraVida(){
        this.lives--;
        if(this.lives == 0) alive = false;
    }
    
    public boolean conflict(Entity e) {
        return (this.x == e.x && this.y == e.y);
    }
    
}
