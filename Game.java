/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author erik
 */
public class Game extends JPanel implements KeyListener{
    
    
    public boolean running = true;
    public ArrayList <Aliens> listaAliens;
    public ArrayList <Shot> listaShot;
    Player player;
    
    private static final int screenX =20;
    private static final int screenY =12;
            
    private int[][] screen = new int[screenX][screenY];
    private boolean won = false;
    private boolean lost = false; 
    
    
    public Game (){
        
        //CRIA PLAYER
        this.player =  new Player(0,11);
        
        //CRIA 55 ALIENS 
        this.listaAliens = new ArrayList();
        for (int i = 0; i < 11; i++){
            for (int j =0; j<5; j++){
                listaAliens.add(new Aliens(i, j));
            }
        }
        
        //CRIA LISTA VAZIA DE TIROS
        this.listaShot = new ArrayList ();
        
        this.run();
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
        }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_A:
                        player.x--;
                        break;
                    case KeyEvent.VK_D:
                        player.x++;
                        break;
                    case KeyEvent.VK_SPACE:
                        listaShot.add(new Shot(player.x, player.y, 1));
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                        throw new UnsupportedOperationException("Not supported yet.");
            }
    
    
    public void paintComponent(Graphics g){
        System.out.print("I");
        super.paintComponent(g);
    }


private void printScreen(){
        for(int i =0; i< screenX; i++){
            for(int j =0; j< screenY; j++){
                screen[i][j] = 0;
         //       System.out.print(' ');
            }
            
        }
    }
    
    /**
     *
     * @param screenX
     * @param array
     * @param len
     * @param path
     */
    public int aliensMove(int screenX, ArrayList<Aliens> array ,int len, int path){
        if(path ==1){
            if(array.get(0).x == 8){
                for(int i =0; i< len; i++){
                    array.get(i).y++;
                    path =2;
                }
            }
            else{
                for(int i =0; i< len; i++){
                    array.get(i).x++;
                    
                }
            }
        }
        else{
            if(array.get(0).x == 0){
                for(int i =0; i< len; i++){
                    array.get(i).y++;
                    path =1;
                }
            }
            else{
                for(int i =0; i< len; i++){
                    array.get(i).x--;
                }
            }
        }
        
        return path;
    }
    
    private void run() {
        int path =1;
        int aux =1;
        int delay =1;
        while(running){
            int contador =0;
            if(won || lost){ 
                System.exit(0);
            }
            
            //update em todas as entidades
            this.printScreen();
           
            for(int i =0; i< listaAliens.size(); i++){
                screen[listaAliens.get(i).x][listaAliens.get(i).y] = listaAliens.get(i).lives;
            }
            
            screen[player.x][player.y] = 9;
            
            for(int i=0; i< listaShot.size(); i++){
                screen[listaShot.get(i).x][listaShot.get(i).y] = 8;
            }
            
            int count =0;
            for(int i=0; i< screenY; i++){
                for(int j=0; j<screenX; j++){
                    if(screen[j][i] == 9){
                        System.out.print("S");
                    }
                    else if(screen[j][i] == 8){
                        System.out.print('I');
                    }
                    else if(screen[j][i] ==0){
                        System.out.print(' ');
                    }
                    else{
                        System.out.print(screen[j][i]);
                    }
                    count ++;
                }
                System.out.println();
            }
            for (int j =0; j< listaShot.size(); j++){
                for(int k =0; k<listaAliens.size(); k++){
                    if( listaShot.get(j).conflict(listaAliens.get(k))){
                        //mata alien
                        
                        //faz dps
                    }
                }
                if(listaShot.get(j).conflict(player)){
                    //tirar vida nave
                    player.lives--;
                }
            }
            
            for (int i =0; i< listaAliens.size(); i++){
                if (listaAliens.get(i).y == 34){
                    this.lost = true;
                }
                    
                if (listaAliens.get(i).dead) listaAliens.remove(i);
                //ta no final da tela
                    //se final de tela: muda de posicao
            }
            
            if (player.lives == 0){
                this.lost = true;
            }
            if (listaAliens.isEmpty()){
                this.won = true;
            this.printScreen();
            }
            if(delay >10){
                path = aliensMove(screenX, listaAliens, listaAliens.size(), aux);
                aux= path;
                contador++;
                delay =0;
            }
            if(listaAliens.get(0).y == 7 && path ==2){
                lost = true;
            }
            
            try{
                Thread.sleep(100);
            } catch(InterruptedException e){
                
            }
            delay++;
        }
    }
}

