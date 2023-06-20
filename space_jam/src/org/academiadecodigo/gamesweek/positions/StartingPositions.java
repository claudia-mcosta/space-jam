package org.academiadecodigo.gamesweek.positions;

import org.academiadecodigo.gamesweek.Game;

public enum StartingPositions {
    POSITION_0(0,0), //Court
    POSITION_1(Game.screenWidth/2+25,Game.screenHeight/2), //Monstar1
    POSITION_2(Game.screenWidth/2+50,Game.screenHeight/4), //Monstar2
    POSITION_3(Game.screenWidth/2+50,(Game.screenHeight/4)*3), //Monstar3
    POSITION_4((Game.screenWidth/4)*3,(Game.screenHeight/6)*2), //Monstar4
    POSITION_5((Game.screenWidth/4)*3,(Game.screenHeight/6)*4), //Monstar5
    POSITION_6(Game.screenWidth/2,Game.screenHeight/2), //Ball
    POSITION_7(Game.screenWidth/3,Game.screenHeight/2), //MJ
    POSITION_8(1120,360); //Hoop

    private Position position;

    private StartingPositions(double x, double y){

        position = new Position(x,y);
    }

    public Position getPosition(){
        return position;
    }

}