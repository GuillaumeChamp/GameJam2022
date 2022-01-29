package GameEngine.Entity;

import GUI.AnimatedImage;
import GameEngine.Loader.ItemsLoader;
import GameEngine.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WorkBench extends Entity {

    private Item[] itemsHold;
    Image[] workbench_frames = {new Image("Resources/Sprites/workbench.png")};

    public WorkBench(Item[] items, int x, int y) {
        this.itemsHold = items;
        xPos = x;
        yPos = y;
        width = 150;
        height = 150;
        skin = new AnimatedImage(workbench_frames);
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

    public void setItemsHold(int index1, int index2, int index3) {
        itemsHold[0] = ItemsLoader.allItems.get(index1);
        itemsHold[1] = ItemsLoader.allItems.get(index2);
        itemsHold[2] = ItemsLoader.allItems.get(index3);
    }

}
