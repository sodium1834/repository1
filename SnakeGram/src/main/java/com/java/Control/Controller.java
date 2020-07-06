package com.java.Control;

import com.java.entity.Food;
import com.java.entity.Ground;
import com.java.entity.Snake;
import com.java.listener.SnakeListener;
import com.java.util.Global;
import com.java.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 控制器
 */
public class Controller extends KeyAdapter implements SnakeListener {
    private Snake snake;
    private Food food;
    private Ground ground;
    private GamePanel gamePanel;
    public Controller() {
    }
    public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }

    /**
     * 控制游戏开始
     */
    public void startGame(){
        snake.start();
        food.setFood(getPoint());
        ground.setGround(getIntArr());
    }

    /**
     * 获取一个随机位置
     * @return
     */
    public Point getPoint(){
        int x=new Random().nextInt(Global.WIDTH);
        int y=new Random().nextInt(Global.HEIGHT);
        if (x==0||y==0||x>=Global.WIDTH-1||y>=Global.HEIGHT-1){
            getPoint();
        }
        System.out.println("食物位置" +x+","+y);
        return new Point(x,y);
    }
    /**
     * 获取障碍物矩阵
     * @return
     */
    public int[][] getIntArr(){
        int[][] ints = new int[Global.HEIGHT][Global.WIDTH];
        for (int i=0;i<Global.HEIGHT;i++){
            for (int j=0;j<Global.WIDTH;j++){
                if (i==0||j==0||i==Global.HEIGHT-1||j==Global.WIDTH-1){
                    ints[i][j]=1;
                }else {
                    ints[i][j]=0;
                }
            }
        }
        return ints;
    }


    /**
     *重写snakeListener的snakeMoved方法
     * @param snake
     */
    @Override
    public void snakeMoved(Snake snake) {
        System.out.println("判断蛇，是否碰到身体，是否碰到食物，障碍物！");
        if (food.isEatBySnake(snake)){
            snake.eatFood(food);
            food.setFood(getPoint());
        }
        if (ground.isEatBySnake(snake)==true){
            snake.setP(true);
            return;
        }
        if (snake.isEatSelf()){
            snake.setP(true);
            return;
        }
        //画出蛇，食物，障碍物 isEatBySnake
        gamePanel.display(snake,food,ground);
    }
    /**
     * Invoked when a key has been pressed.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyconde=e.getKeyCode();
        switch (keyconde){
            case KeyEvent.VK_UP:
            snake.changeDirection(snake.UP);
            break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection(snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(snake.RIGHT);
                break;

        }
        super.keyPressed(e);
    }
}
