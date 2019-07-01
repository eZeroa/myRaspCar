package com.zero.car.util;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 9:40 2019/6/11
 * @copy by by seth.yang on Github
 * 可暂停的线程
 */
public abstract class PausableThread extends Thread {
    private final Object locker = new Object ();
    private boolean paused = true;
    private boolean running = false;

    /**
     * 线程逻辑，由具体实现类来决定
     */
    abstract void doWork ();

    /**
     * 构造函数，创建一个指定状态的可暂停线程
     */
    public PausableThread (boolean paused) {
        this.paused = paused;
        start ();
    }

    /**
     * 恢复执行线程
     */
    public void proceed () {
        synchronized (locker) {
            paused = false;
            locker.notifyAll ();
        }
    }

    /**
     * 暂停线程
     */
    public void pause () {
        paused = true;
    }

    public boolean isPaused () {
        return paused;
    }

    /**
     * 退出线程，布尔值block表示是否等待线程退出
     */
    public void shutdown (boolean block) throws InterruptedException {
        running = false;
        if (paused)
            proceed ();
        if (block && (Thread.currentThread () != this))
            this.join ();
    }

    @Override
    public synchronized void start () {
        if (!running) {
            running = true;
            super.start ();
        }
    }

    @Override
    public void run () {
        while (running) {
            while (paused) {
                synchronized (locker) {
                    try {
                        locker.wait ();
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
            }

            doWork ();
        }
    }
}