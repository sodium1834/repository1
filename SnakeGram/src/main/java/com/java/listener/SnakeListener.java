package com.java.listener;

import com.java.entity.Snake;

/**
 * 蛇的监听器
 * 主要监听蛇的移动，是否碰到食物，自己，障碍物
 */
public interface SnakeListener {
    public void snakeMoved(Snake snake);

}
