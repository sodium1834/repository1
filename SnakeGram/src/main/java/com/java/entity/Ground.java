package com.java.entity;

import com.java.util.Global;

import java.awt.*;

/**
 * 障碍物
 */
public class Ground{
    private int[][] ints;
    public void drawMe(Graphics g){
        g.setColor(Color.yellow);
        for (int i=0;i<Global.HEIGHT;i++){
            for (int j=0;j<Global.WIDTH;j++){
              if (ints[i][j]==1){
                  g.fill3DRect(j* Global.CEll_SIZE,i*Global.CEll_SIZE,Global.CEll_SIZE,Global.CEll_SIZE,true);
              }
            }
        }
    }
    public boolean isEatBySnake(Snake snake){
        System.out.println("判断蛇是否碰到障碍物");
        Point point = snake.geHead();
        System.out.println(point.x+"-"+point.y);
        for (int i=0;i<Global.HEIGHT;i++){
            for (int j=0;j<Global.WIDTH;j++){
                if (ints[i][j]==1){
                    if (point.x==j&&point.y==i) return true;
                }
            }
        }
        return false;
    }
    public void setGround(int[][] ints){
        this.ints=ints;
    }

}
