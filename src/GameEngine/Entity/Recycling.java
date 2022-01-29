package GameEngine.Entity;

import GameEngine.Player;

public class Recycling extends Entity {

    public Recycling(int x,int y) {
        xPos=x;
        yPos=y;
    }

    public void exchange(Player player, Collectible give1, Collectible give2, Collectible take) {
        if (canExchange(player,give1,give2,take)) {
            switch (give1.getType()) {
                case YELLOW : player.setYellowStack(player.getYellowStack()-1); break;
                case GREEN : player.setGreenStack(player.getGreenStack()-1); break;
                case BLACK : player.setBlackStack(player.getBlackStack()-1); break;
            }
            switch (give2.getType()) {
                case YELLOW : player.setYellowStack(player.getYellowStack()-1); break;
                case GREEN : player.setGreenStack(player.getGreenStack()-1); break;
                case BLACK : player.setBlackStack(player.getBlackStack()-1); break;
            }
            switch (take.getType()) {
                case YELLOW : player.setYellowStack(player.getYellowStack()+1); break;
                case GREEN : player.setGreenStack(player.getGreenStack()+1); break;
                case BLACK : player.setBlackStack(player.getBlackStack()+1); break;
            }
        }
    }

    public boolean canExchange(Player player, Collectible give1, Collectible give2, Collectible take) {

        if ((give1.getType()==take.getType())||(give2.getType()==take.getType())) {
            return false;
        }
        if (give1.getType()==give2.getType()) {
            switch (give1.getType()) {
                case YELLOW : return player.getYellowStack()>2;
                case GREEN : return player.getGreenStack()>2;
                case BLACK : return player.getBlackStack()>2;
            }
        }
        boolean give1bool = false;
        boolean give2bool = false;

        switch (give1.getType()) {
            case YELLOW : give1bool = player.getYellowStack()>1; break;
            case GREEN : give1bool = player.getGreenStack()>1; break;
            case BLACK : give1bool = player.getBlackStack()>1; break;
        }

        switch (give2.getType()) {
            case YELLOW : give2bool = player.getYellowStack()>1; break;
            case GREEN : give2bool = player.getGreenStack()>1; break;
            case BLACK : give2bool = player.getBlackStack()>1; break;
        }
        return give1bool&&give2bool;
    }

}
