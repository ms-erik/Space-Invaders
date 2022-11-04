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
    
    
    boolean dead;
    
    public Aliens(int x, int y){
        super(x,y);
        
    }
    
    public Shot shot(){
        return new Shot(this.x, this.y+1, 2);
    }
    
    public void aliensMove(int screenX, Aliens[] array ,int len, int path){
        if(path ==1){
            if(screenX - array[0].x == 8){
                for(int i =0; i< len; i++){
                    array[i].y++;
                }
            }
            else{
                for(int i =0; i< len; i++){
                    array[i].x++;
                }
            }
        }
        else{
            if(screenX - x == 20){
                for(int i =0; i< len; i++){
                    array[i].y++;
                }
            }
            else{
                for(int i =0; i< len; i++){
                    array[i].x--;
                }
            }
        }
    }
    
    
    
    
    
    
    
    
}
