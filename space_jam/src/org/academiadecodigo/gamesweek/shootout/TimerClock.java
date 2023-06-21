package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
public class TimerClock {

    private Picture timeBoard;
    private Text timeText;
    private TimerClock time;
    private long startTime;
    private long endTime;
    private boolean isRunning = false;

    public TimerClock(long endTime) {
        this.endTime = endTime;
    }

    public void start(){
        if(!isRunning){
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop(){
        if(isRunning){
            endTime = System.currentTimeMillis();
            isRunning = false;
        }
    }

    // Unnecessary ??
    public long getTimeInSeconds(){
        long time = endTime - startTime;
        return (long) (time * 0.001);
    }

    public long getCurrentTime(){
        return System.currentTimeMillis();
    }

    public long getTimeSinceStartInSeconds(){
        long timeSinceStart = getCurrentTime() - startTime;
        return (long) (timeSinceStart * 0.001);
    }


    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getTimeLeft() {
        return getEndTime() - getTimeSinceStartInSeconds();
    }

}