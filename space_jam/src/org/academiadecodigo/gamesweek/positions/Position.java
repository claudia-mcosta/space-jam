package org.academiadecodigo.gamesweek.positions;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y){
        this.x=x;
        this.y=y;
    }
    public Position(StartingPositions position){
        this.x= position.getPosition().getX();
        this.y=position.getPosition().getY();
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x=x;
    }

    public void setY(double y){
        this.y=y;
    }

    public void translatePosition(double x, double y){
        this.x +=x;
        this.y +=y;
    }

    public String toString(){
        return "X= "+x+"Y= "+y;
    }
}
