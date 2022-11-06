
package spaceinvaders;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


/**
 *
 * @author erik
 */
public class Game extends JPanel implements KeyListener, Runnable{
    
    public boolean running = true;
    public ArrayList <Aliens> listaAliens;
    public ArrayList <Shot> listaShot;
    Player player;
    public ArrayList <Barrier> listaBarrier;
    
    int direcao = 0;
    
    private static final int screenX =20;
    private static final int screenY =12;
    private int path =1;
    private int aux =1;
    private int delay = 0;
    private final int reloadPlayer = 3;
    private final int reloadAlien = 33;
    private int[][] screen = new int[screenX][screenY];
    private boolean won = false;
    private boolean lost = false; 
    private boolean AlienCanShoot = true; 

    
    public Game (){
        
        //CRIA PLAYER
        this.player =  new Player(5,11);
        
        //cria 55 aliens
        this.listaAliens = new ArrayList();
        for (int i = 0; i < 11; i++){
            for (int j =0; j<5; j++){
                listaAliens.add(new Aliens(i, j));
            }
        }
        
        //cria lista vazia de tiro
        this.listaShot = new ArrayList ();
        this.listaBarrier = new ArrayList ();
        
        //cria barreira
         for(int i=0; i< 3; i++){
                for(int j =0; j<5 ;j++){
                    listaBarrier.add(new Barrier(4*j+i, screenY -3));
                }
        }
         
        Thread GameLoop = new Thread(this);
        GameLoop.start();
    }
    
    
    private void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    private void sleep(){
        try{
            Thread.sleep(200);
        } catch(InterruptedException e){
        } 
    }
    private void updateScreen(){
        for(int i =0; i< screenX; i++){
            for(int j =0; j< screenY; j++){
                screen[i][j] = 0;
            }
        }
        
        

        player.Dx(direcao);
        screen[player.x][player.y] = 9;

        for(int i=0; i< listaShot.size(); i++){
            if(listaShot.get(i).type ==1){
                screen[listaShot.get(i).x][listaShot.get(i).y] = 8;   
            }
            else if(listaShot.get(i).type ==2){
                screen[listaShot.get(i).x][listaShot.get(i).y] = 5;   
            }
        }
        
        for(int i = 0; i< listaAliens.size(); i++){
            screen[listaAliens.get(i).x][listaAliens.get(i).y] = listaAliens.get(i).lives;
        }
            
        for(int i=0; i< listaBarrier.size(); i++){
            if(!listaBarrier.get(i).alive) {
                listaBarrier.remove(i);
                i--;
            }
            else{
                screen[listaBarrier.get(i).x][listaBarrier.get(i).y ] = 5 + listaBarrier.get(i).lives;
            }
        }

        for(int i =0; i< listaAliens.size(); i++){
            if (!listaAliens.get(i).alive) listaAliens.remove(i);
        }

        if(delay % 10 == 0){
            path = aliensMove(listaAliens, listaAliens.size(), aux);
            aux = path;
        }

        System.out.printf("Vidas: %d\nNum Aliens: %d\n", player.lives, listaAliens.size());

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
                else if(screen[j][i] == 7){
                    System.out.print('=');
                }
                else if(screen[j][i] == 6){
                    System.out.print('-');
                }
                else if(screen[j][i] == 5){
                    System.out.print('*');
                }
                else{
                    System.out.print(screen[j][i]);
                }
            }
              System.out.println();
        }
        delay++;
    }

    private int aliensMove(ArrayList<Aliens> aliens, int len, int path){
        if(path ==1){
            if(aliens.get(0).x == 9){
                for(int i =0; i< len; i++){
                    aliens.get(i).y++;
                    path =2;
                }
            }
            else{
                for(int i =0; i< len; i++){
                    aliens.get(i).x++;
                    
                }
            }
        }
        else{
            if(aliens.get(0).x == 0){
                for(int i =0; i< len; i++){
                    aliens.get(i).y++;
                    path =1;
                }
            }
            else{
                for(int i =0; i< len; i++){
                    aliens.get(i).x--;
                }
            }
        }
        
        return path;
    }
    
    private void colision(){
        for(int i = 0; i < listaShot.size(); i++){
                listaShot.get(i).updateShot();
                if(listaShot.get(i).y < 0 || listaShot.get(i).y > 11){
                    listaShot.remove(i);
                    i--;
                }
                else if(listaShot.get(i).type == 1){
                    for(int k =0; k<listaAliens.size(); k++){
                        if(listaShot.get(i).conflict(listaAliens.get(k))){
                            //mata alien
                            listaAliens.get(k).tiraVida();
                            listaShot.remove(i);
                            i--;
                            break;
                        }
                    }
                }
                else if(listaShot.get(i).conflict(player)){
                    //tirar vida nave
                    player.lives--;
                    listaShot.remove(i);
                    i--;
                }
 
        }
            
            for (int i = 0; i < listaShot.size(); i++){
                for(int k = 0; k < listaBarrier.size(); k++){
                    if(listaShot.get(i).conflict(listaBarrier.get(k))){
                        listaBarrier.get(k).tiraVida();
                        listaShot.remove(i);
                        i--;
                        break;
                    }
                }
            }
    }
    
    private void GameOver(){
        if(won){
                System.out.println("Parabens!!\n Voce derrotou o mal\n");
                System.exit(0);
        }
        if(lost){
            System.out.println("Voce perdeu!   \n");
            System.exit(0);
        }
        if (player.lives == 0){
            this.lost = true;
        }
        if (listaAliens.isEmpty()){
            this.won = true;
        }

        for(int i =0; i< listaAliens.size(); i++){
            for(int j=0; j< listaBarrier.size(); j++){
                if(listaAliens.get(i).conflict(listaBarrier.get(j))){
                    lost = true;
                }
            }
        }
            for(int i =0; i< listaAliens.size(); i++){
                for(int j=0; j< listaBarrier.size(); j++){
                    if(listaAliens.get(i).conflict(listaBarrier.get(j))){
                        lost = true;
                    }
                }
            }
            
            for (int i =0; i< listaAliens.size(); i++){
                if (listaAliens.get(i).y == 34){ 
                    this.lost = true;
                }
            }
            
    }
    
    private void RandomShot(){
        Random random = new Random();
        int pos = random.nextInt(listaAliens.size());
        if(delay %91 ==0 ){
            listaShot.add(listaAliens.get(pos).shot());
        }
    }
    private void shootingCondition(){
        if(delay % reloadAlien == 0) AlienCanShoot = true;
        RandomShot();
        if (delay % reloadPlayer == 0) player.canShoot = true;         //reload time
        if (AlienCanShoot){
            int i=0;
            while(i <listaAliens.size()){
                if(listaAliens.get(i).x == player.x){
                    listaShot.add(listaAliens.get(i).shot());
                    AlienCanShoot = false;
                    break;
                }
                i++;
            }
        }

    }
    @Override
    public void run() {
        while(running){
            GameOver();
            shootingCondition();
            colision();
            updateScreen();
            sleep();
            Clear();
        }
        
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) direcao = 1;
        if (e.getKeyCode() == KeyEvent.VK_A) direcao = -1;
        if (e.getKeyCode() == KeyEvent.VK_SPACE && player.canShoot) listaShot.add(player.shot());
    } 

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) direcao = 0;
        if (e.getKeyCode() == KeyEvent.VK_D) direcao = 0;      
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
 
    
}