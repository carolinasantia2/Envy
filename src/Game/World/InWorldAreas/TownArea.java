package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.ShinyJirachi;
import Game.Entities.Statics.LightStatue;
import Game.World.Walls;

public class TownArea extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInTown = false;

    private int imageWidth = 3680, imageHeight = 4000;
    public final static int playerXSpawn = -850, playerYSpawn = -3180;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> townWalls;

    public TownArea(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="Town"; 
        handler.setXInWorldDisplacement(playerXSpawn);
        handler.setYInWorldDisplacement(playerYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;
        this.entityManager.AddEntity(new ShinyJirachi (handler, imageWidth/3, imageHeight/2+200));
        
        townWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : townWalls) {
            w.tick();
        }
        if(!GameSetUp.LOADING) {
            entityManager.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        super.render(g);


        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.fill(background);

        g.drawImage(Images.TownArea, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null); // TOWN BACKGROUND

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : townWalls) {

                if (w.getType().equals("Wall"))
                    g2.setColor(Color.black);
                else
                    g2.setColor(Color.PINK);

                w.render(g2);
            }
        }


        entityManager.render(g);

    }

    private void AddWalls() {





        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 150, imageHeight-30, 300, 30, "Start Exit"));	
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 150, imageHeight-500, 5000, 500, "Wall")); // Trees at the bottom right
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 1400, imageHeight-4000, 500, imageHeight, "Wall"));
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 50, imageHeight-4100, 2000, 500, "Wall"));
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1750, imageHeight-4000, 1350, 500, "Wall")); // Bush next to cave top
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1850, imageHeight-4000, 600, 1500, "Wall")); // Cave top
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1850, imageHeight-2500, 450, 100, "Wall")); // Cave top small bump
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1975, imageHeight-2400, 100, 450, "Wall")); // left road limit
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1125, imageHeight-3350, 725, 1000, "Wall")); // fences with trees
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1850, imageHeight-500, 1700, 500, "Wall")); // Trees at the bottom left
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1850, imageHeight-600, 1100, 100, "Wall")); // Trees at the bottom left (small)
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1850, imageHeight-2000, 500, 1500, "Wall")); // Cave bottom
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1000, imageHeight-1400, 450, 500, "Wall")); // pool
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1300, imageHeight-1700, 300, 200, "Wall")); // trees next to pool
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1000, imageHeight-1600, 200, 200, "Wall")); // trees next to pool
        townWalls.add(new InWorldWalls(handler, imageWidth/2, imageHeight-1725, 375, 400, "Wall")); // Poke Center
        townWalls.add(new InWorldWalls(handler, imageWidth/2, imageHeight-2450, 375, 400, "Wall")); // Small house
        townWalls.add(new InWorldWalls(handler, imageWidth/2, imageHeight-3150, 375, 350, "Wall")); // Small house
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 775, imageHeight-2475, 300, 440, "Wall")); // Poke Mart
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 400, imageHeight-2475, 1000, 30, "Wall")); // Poke Mart Fence
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 700, imageHeight-3375, 450, 400, "Wall")); // Gym Center
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 400, imageHeight-2750, 1000, 30, "Wall")); // Gym Center bump 
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 50, imageHeight-950, 1500, 30, "Wall")); // Long bottom bump 
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1320, imageHeight-950, 1000, 30, "Wall")); // Pool bottom bump 
        
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 625, imageHeight-1750, 100, 100, "Wall")); // Dawn block
        
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 400, imageHeight/2-2100, 450, 100, "Wall")); // uproad limit
       






       
    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return townWalls;
    }
}




