package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Statics.LightStatue;
import Game.World.Walls;

public class TownArea extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInTown = false;

    private int imageWidth = 3680, imageHeight = 4000;
    public final static int playerXSpawn = -850, playerYSpawn = -3180
    		;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> townWalls;

    public TownArea(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="Town";
        handler.setXInWorldDisplacement(playerXSpawn);
        handler.setYInWorldDisplacement(playerYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;

        

        this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,700, 2000,"InWorldState","Sergio","Cave","EnemyOne",150,25,80,1,8,12,20,10,20,10,1,10,"None","Thunder",null,null)); //lvl 2 difficulty
        this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,3000, 1000,"InWorldState","Cave Dweller","Cave","EnemyOne",100,25,60,10,1,12,20,10,20,13,1,10,"None","Thunder",null,null)); // lvl 1 difficulty

        this.entityManager.AddEntity(new LightStatue (handler, 2080, 1770));
        
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
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 150, imageHeight-500, 5000, 500, "Wall"));
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 1400, imageHeight-4000, 500, imageHeight, "Wall"));
        townWalls.add(new InWorldWalls(handler, imageWidth/2 + 50, imageHeight-4100, 5000, 500, "Wall"));
        townWalls.add(new InWorldWalls(handler, imageWidth/2 - 1750, imageHeight-4000, 1400, 500, "Wall"));
       
    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return townWalls;
    }
}




