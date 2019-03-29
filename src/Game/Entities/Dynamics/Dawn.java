package Game.Entities.Dynamics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.sun.glass.events.KeyEvent;


import Game.World.InWorldAreas.TownArea;
import Main.GameSetUp;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Dawn extends BaseDynamicEntity {

	private static Rectangle Dawn;
	private Animation dawn;
	private static int width, height;

	public boolean detectingPlayer = false;
	public boolean cantPass = false;

	public static Rectangle detector;

	public static boolean keyEPressed = false;

	private static int counterQuestCompletedMsgShowed = 0;

	public Dawn(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition, null);

		width = 16 * 5;
		height = 48 * 2 + 24;

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);

		//		dawn = new Animation(3000, Images.Dawn);

		Dawn = new Rectangle();
		detector = new Rectangle();
	}

	@Override
	public void tick() {
		//		dawn.tick();
		//		playerCollisionDetector();

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			keyEPressed = true;

	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		detector = new Rectangle(this.getCollision());
		Dawn = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset() - 15),
				(int) (handler.getYInWorldDisplacement() + getYOffset()), width + 30, height + 20);	
		if (TownArea.isInTown) {


			detector.setRect(detector.getX() - 2 * detector.getWidth(), detector.getY() - detector.getHeight() * 11 / 8,
					detector.getWidth() * 5, detector.getHeight() * 15 / 4);
			g.drawImage(Images.Jirachi, (int)(handler.getXInWorldDisplacement() + getXOffset()),(int)(handler.getYInWorldDisplacement() + getYOffset()), width, height, null);

			if (!keyEPressed && this.handler.getEntityManager().getPlayer().getCollision().intersects(detector)) {
				g2.drawString("Press E", (int)(handler.getXInWorldDisplacement() + getXOffset())-100,(int)(handler.getYInWorldDisplacement() + getYOffset())-100);
			} else if(this.handler.getEntityManager().getPlayer().getCollision().intersects(detector) && keyEPressed) {
					System.out.println("estas en el detector");
					g2.setColor(Color.WHITE);
					g2.fillRect((int)(handler.getXInWorldDisplacement() + getXOffset()),(int)(handler.getYInWorldDisplacement() + getYOffset()), 300, 45);
					g2.setColor(Color.BLACK);
					g2.drawRect( (int)(handler.getXInWorldDisplacement() + getXOffset()),(int)(handler.getYInWorldDisplacement() + getYOffset()), 300, 45);
					g2.setFont(new Font("ARIAL", 15, 15));
					g2.drawString("Only those who are worthy may enter!", (int)(handler.getXInWorldDisplacement() + getXOffset()),(int)(handler.getYInWorldDisplacement() + getYOffset()));
					g2.drawString("Prove it with a quest!", (int)(handler.getXInWorldDisplacement() + getXOffset()),(int)(handler.getYInWorldDisplacement() + getYOffset()));
				}
			}
			g2.draw(Dawn);
			g2.draw(detector);
			
			
	}


	//	protected void playerCollisionDetector() {
	//		detector = new Rectangle(this.getCollision());
	//		detector.setRect(detector.getX() - 2 * detector.getWidth(), detector.getY() - detector.getHeight() * 11 / 8,
	//				detector.getWidth() * 5, detector.getHeight() * 15 / 4);
	//
	//		setDetectingPlayer(handler.getEntityManager().getPlayer().getCollision().intersects(detector));
	//		setCantPass(handler.getEntityManager().getPlayer().getCollision().intersects(this.getCollision()));
	//	}





	protected void showPressE(Graphics g, Color color) {
		g.setFont(new Font("Microsoft Jheng Hei UI", Font.BOLD, 35));
		g.setColor(color);
		g.drawString("PRESS E", (int) (handler.getXInWorldDisplacement() + getXOffset() - 40),
				(int) (handler.getYInWorldDisplacement() + getYOffset() - 50));
	}

	//Getters and Setters
	@Override
	public Rectangle getCollision() {
		return Dawn;
	}

	@Override
	public double getXOffset() {
		return xPosition;
	}
}