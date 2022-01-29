package GameEngine.Entity;

import GameEngine.Player;

public class WorkBench extends Entity {

    private Item[] itemsHold;

    public WorkBench(Item[] items) {
        this.itemsHold = items;
        xPos = 100;
        yPos = 100;
        width = 50;
        height = 50;
    }

    public void craft(Player player, Item item) {
        if (canBuy(player,item)) {
            player.get(item);
        }
    }

    private boolean canBuy(Player player, Item item) {
        return item.getCost()[0]<player.getYellowStack()
                && item.getCost()[1]<player.getGreenStack()
                && item.getCost()[2]<player.getBlackStack();
    }

}
