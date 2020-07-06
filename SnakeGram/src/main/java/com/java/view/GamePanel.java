package com.java.view;

import com.java.entity.Food;
import com.java.entity.Ground;
import com.java.entity.Snake;
import com.java.util.Global;

import javax.swing.*;
import java.awt.*;

/**
 * 显示界面
 */
public class GamePanel  extends JPanel {
    private Snake snake;
    private Food food;
    private Ground ground;
    private Global global;
    public void display(Snake snake, Food food,Ground ground){
        System.out.println("画板正在显示。。");
        this.snake=snake;
        this.food=food;
        this.ground=ground;
        repaint();
    }
    /**
     * 重写画的方法
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (snake!=null&&food!=null&&ground!=null){
            snake.drawMe(g);
            food.drawMe(g);
            ground.drawMe(g);
        }

    }
}
