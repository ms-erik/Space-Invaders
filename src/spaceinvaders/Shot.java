/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

/**
 *
 * @author erik
 */
public class Shot extends Entity {
    public int type;
    
    public Shot(int x, int y,int type) {
        super(x, y);
        this.type = type;
    }
    
    public void updateShot(){
        if(type ==1 ){
            this.y --;
            
        }else{
            this.y ++;
        }
    } 
    
    public boolean conflict(Entity e) {
        return (this.x == e.x && this.y == e.y);
    }

}
