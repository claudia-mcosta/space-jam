package org.academiadecodigo.gamesweek;

public enum Direction {
    UP,
    UP_RIGHT,
    RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT,
    LEFT,
    UP_LEFT,
    NONE;

    public boolean isOpposite(Direction dir) {
        if(dir.equals(oppositeDirection()[0]) || dir.equals(oppositeDirection()[1]) || dir.equals(oppositeDirection()[2])){
            return true;
        }
        return false;
    }

    //TO BE REVISED
    public Direction[] oppositeDirection() {

        Direction[] opposite = new Direction[]{Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT};

        switch (this) {
            case UP:
                //opposite = new Direction[]{Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT};
                break;
            case DOWN:
                opposite = new Direction[]{Direction.UP,Direction.UP_LEFT,Direction.UP_RIGHT};
                break;
            case LEFT:
                opposite = new Direction[]{Direction.RIGHT,Direction.UP_RIGHT,Direction.UP_LEFT};
                break;
            case RIGHT:
                opposite = new Direction[]{Direction.LEFT,Direction.DOWN_LEFT,Direction.UP_LEFT};
                break;
            case UP_RIGHT:
                opposite = new Direction[]{Direction.DOWN_LEFT,Direction.DOWN,Direction.LEFT};
                break;
            case UP_LEFT:
                opposite = new Direction[]{Direction.DOWN_LEFT,Direction.DOWN,Direction.RIGHT};
                break;
            case DOWN_LEFT:
                opposite = new Direction[]{Direction.UP_RIGHT,Direction.UP,Direction.RIGHT};
                break;
            case DOWN_RIGHT:
                opposite = new Direction[]{Direction.UP_LEFT,Direction.UP,Direction.LEFT};
                break;
        }

        return opposite;
    }

}
