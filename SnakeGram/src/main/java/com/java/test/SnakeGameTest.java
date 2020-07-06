package com.java.test;

import com.java.Control.Controller;
import com.java.entity.Food;
import com.java.entity.Ground;
import com.java.entity.Snake;
import com.java.view.GamePanel;

import javax.swing.*;

public class SnakeGameTest {
    public static void main(String[] args) {
        mainx();
    }

    public static void mainx(){
        //实体对象
        Snake snake = new Snake();
        Food food = new Food();
        Ground ground = new Ground();
        //试图对像
        GamePanel gamePanel = new GamePanel();
        //控制器>蛇的监听器>键盘监听器。
        Controller c=new Controller(snake,food,ground,gamePanel);
        //蛇和面板添加监听器
        snake.addSnakeListener(c);
        gamePanel.addKeyListener(c);
        JFrame frame=new JFrame("SnakeGram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //获取焦点
        gamePanel.setFocusable(true);
        frame.add(gamePanel);
        //启动游戏
        c.startGame();
        frame.setVisible(true);
    }

}
