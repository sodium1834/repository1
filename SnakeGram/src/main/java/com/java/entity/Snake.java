package com.java.entity;

import com.java.listener.SnakeListener;
import com.java.util.Global;
import java.awt.*;
import java.util.LinkedList;

/**
 *蛇类
 */
public class Snake {
    private SnakeListener snakeListener;
    private boolean p=false;
    private int speed=1000;
    /**
     * 蛇身
     */
    private LinkedList<Point> body=new LinkedList<Point>();
    public  static final int UP=1;
    public  static final int DOWN=-1;
    public  static final int LEFT=2;
    public  static final int RIGHT=-2;
    private int oldDirection,newDirection;
    private Point tail;
    public Snake() {
        init();
    }
    public LinkedList<Point> getBody() {
        return body;
    }
    private void init(){
        int x= Global.WIDTH/2;
        int y=Global.HEIGHT/2;
        for (int i=0;i<3;i++){
            body.add(new Point(x-i,y));
        }
        this.oldDirection=this.newDirection=RIGHT;
    }
    //移
    public void move() {
        System.out.println("蛇在移动");
        body.removeLast();
        //获取头部坐标
        int x = body.getFirst().x;
        int y = body.getFirst().y;
        if (this.oldDirection+this.newDirection!=0)
            this.oldDirection=this.newDirection;
        switch (oldDirection){
            case UP:
                y--;
                if (y<=0) y=Global.HEIGHT-1;
                break;
            case DOWN:
                y++;
                if (y>=Global.HEIGHT-1) y=0;
                break;
            case LEFT:
                x--;
                if (x<=0) x=Global.WIDTH-1;
                break;
            case RIGHT:
                x++;
                if (x>=Global.WIDTH-1) x=0;
                break;
        }
        //确定方向才能知道新的头部。默认方向向右
        body.addFirst(new Point(x,y));
    }
    //吃,把去掉的尾巴加回来
    public void eatFood(Food food) {
        System.out.println("蛇在吃食物");
        body.addLast(tail);
        speed=(int)(speed*4/5);
    }
    //转向
    public void changeDirection(int direction) {
        System.out.println("蛇在改变方向");
        this.newDirection=direction;
    }
    //画出自己
    public void drawMe(Graphics g) {
        g.setColor(Color.blue);
        System.out.println("蛇在画出自己");
        for (Point p: body){
            g.fill3DRect(p.x*Global.CEll_SIZE,
                    p.y*Global.CEll_SIZE,
                    Global.CEll_SIZE,
                    Global.CEll_SIZE,
                    true);
        }
    }

    /**
     * 判断是否吃到自己
     * @return
     */
    public boolean isEatSelf() {

        return false;
    }

    public Point geHead() {
        return body.getFirst();
    }

    /**
     * 监听器方法
     *
     * @param snakeListener
     */
    public void addSnakeListener(SnakeListener snakeListener) {
        if (snakeListener != null) {
            this.snakeListener = snakeListener;
        }
    }
    public void start(){
        new SnakeDriver().start();
    }

    private class SnakeDriver extends Thread{
        @Override
        public void run() {
            while (true){
                move();
                snakeListener.snakeMoved(Snake.this);
                try {
                    if (p){
                        System.out.println("游戏结束");
                        this.stop();
                    }
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void setP(boolean b){
        this.p=b;
    }
}
