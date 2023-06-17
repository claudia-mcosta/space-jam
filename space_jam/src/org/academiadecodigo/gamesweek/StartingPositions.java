package org.academiadecodigo.gamesweek;

public enum StartingPositions {
    POSITION_1(Game.screenWidth/2+25,Game.screenHeight/2),
    POSITION_2(Game.screenWidth/2+50,Game.screenHeight/4),
    POSITION_3(Game.screenWidth/2+50,(Game.screenHeight/4)*3),
    POSITION_4((Game.screenWidth/4)*3,(Game.screenHeight/6)*2),
    POSITION_5((Game.screenWidth/4)*3,(Game.screenHeight/6)*4);

    private Position position;

    private StartingPositions(double x, double y){
        this.position.setX(x);
        this.position.setY(y);
    }

    public Position getPosition(){
        return position;
    }

}
