package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;
public class TimerClock {

    private long startTime;
    private int endTime;
    private boolean isRunning = false;
    private Picture[] timerDisplay = new Picture[4];
    private Picture[][] timerNumbers = new Picture[4][10];

    public TimerClock(int minutes, int seconds) {
        // Convert time given to seconds
        int timeInSeconds = (minutes * 60) + seconds;
        // Default to 99 minutes and 59 seconds if time given is superior to timer display capacity
        this.endTime = Math.min(timeInSeconds, 5999);
    }

    public void init(){
        for (int i = 0; i < timerNumbers.length; i++) {
            for (int j = 0; j < timerNumbers[i].length; j++) {
                timerNumbers[i][j] = new Picture(Game.PADDING, Game.PADDING, "resources/timer" + j + ".png");
                switch (i) {
                    case 0:
                        timerNumbers[i][j].translate((Game.screenWidth / 2) + 33, 8);
                        break;
                    case 1:
                        timerNumbers[i][j].translate((Game.screenWidth / 2) + 10, 8);
                        break;
                    case 2:
                        timerNumbers[i][j].translate((Game.screenWidth / 2) - 33, 8);
                        break;
                    case 3:
                        timerNumbers[i][j].translate((Game.screenWidth / 2) - 56, 8);
                        break;
                    default:
                        break;
                }
            }
        }

        int seconds = endTime % 60;
        int minutes = endTime / 60;

        int secondsUnit = Math.abs(seconds % 10);
        int secondsDecimal = Math.abs((seconds / 10) % 10);
        int minutesUnit = Math.abs(minutes % 10);
        int minutesDecimal = Math.abs((minutes / 10) % 10);

        timerDisplay[0] = timerNumbers[0][secondsUnit];
        timerDisplay[1] = timerNumbers[1][secondsDecimal];
        timerDisplay[2] = timerNumbers[2][minutesUnit];
        timerDisplay[3] = timerNumbers[3][minutesDecimal];

        for (Picture td : timerDisplay) {
            td.draw();
        }
    }

    public void start(){
        if(!isRunning){
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop(){
        if(isRunning){
            isRunning = false;
        }
    }

    public long getTimeInSeconds(long time){
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
        return endTime - getTimeSinceStartInSeconds();
    }

    public void updateDisplay(){

        for (Picture td : timerDisplay) {
            td.delete();
        }

        int seconds = (int) getTimeLeft() % 60;
        int minutes = (int) getTimeLeft() / 60;

        int secondsUnit = Math.abs(seconds % 10);
        int secondsDecimal = Math.abs((seconds / 10) % 10);
        int minutesUnit = Math.abs(minutes % 10);
        int minutesDecimal = Math.abs((minutes / 10) % 10);

        timerDisplay[0] = timerNumbers[0][secondsUnit];
        timerDisplay[1] = timerNumbers[1][secondsDecimal];
        timerDisplay[2] = timerNumbers[2][minutesUnit];
        timerDisplay[3] = timerNumbers[3][minutesDecimal];

        for (Picture td : timerDisplay) {
            td.draw();
        }

    }

}