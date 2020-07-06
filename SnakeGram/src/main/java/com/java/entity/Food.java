package com.java.entity;

import com.java.util.Global;

import java.awt.*;
import java.util.Random;

/**
 * 食物类
 */
public class Food extends Point{
    public void drawMe(Graphics g){
        g.setColor(Color.red);
        System.out.println("食物证正在画出自己");
        g.fill3DRect(x*Global.CEll_SIZE,y*Global.CEll_SIZE,Global.CEll_SIZE,Global.CEll_SIZE,true);
    }
    public boolean isEatBySnake(Snake snake){
        System.out.println("判断蛇是否吃到食物");
        Point point = snake.geHead();
       if(this.equals(point))
            return true;
        return false;
    }
    //设置食物位置
    public void setFood(Point p){
        this.move(p.x,p.y);
    }
}
