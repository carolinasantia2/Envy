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

public class ShinyJirachi extends BaseDynamicEntity {

	private static Rectangle SJ;

	private static int width, height;

	public static boolean keyEPressed = false;

	public static Rectangle detector;

	private static int messageTimer = 0; // makes sure both messages appear after some time


	public ShinyJirachi(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition, null);

		width = 120;
		height = 120;

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);


		SJ = new Rectangle();
		detector = new Rectangle();
	}

	@Override
	public void tick() {

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && !keyEPressed)
			keyEPressed = true;
		else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && keyEPressed)
			keyEPressed = false;

	}

	@Override
	public void render(Graphics g) { // DRAWS DYNAMIC ENTITY AND DELIVERS QUEST

		Graphics2D g2 = (Graphics2D) g;
		detector = new Rectangle(this.getCollision());
		SJ = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset() - 315),
				(int) (handler.getYInWorldDisplacement() + getYOffset()), width + 30, height + 20);	

		if (TownArea.isInTown) {
			detector.setRect(handler.getXInWorldDisplacement() + getXOffset() - 50,
					(int) (handler.getYInWorldDisplacement() + getYOffset()), width + 100, height + 100);
			g.drawImage(Images.ShinyJirachi, (int)(handler.getXInWorldDisplacement() + getXOffset()),(int)(handler.getYInWorldDisplacement() + getYOffset()), width, height, null);

			if (!keyEPressed && this.handler.getEntityManager().getPlayer().getCollision().intersects(detector)) {
				g2.setFont(new Font("ARIAL", Font.BOLD, 15));
				g2.drawString("Press E", (int)(handler.getXInWorldDisplacement() + getXOffset()),(int)(handler.getYInWorldDisplacement() + getYOffset()));

			} else if(this.handler.getEntityManager().getPlayer().getCollision().intersects(detector) && keyEPressed) {
					if(handler.getEntityManager().getPlayer().enemyKilled) {
						g2.setColor(Color.WHITE);
						g2.fillRect((int)(handler.getXInWorldDisplacement() + getXOffset() - 300),(int)(handler.getYInWorldDisplacement() + getYOffset()), 275, 45);
						g2.setColor(Color.BLACK);
						g2.drawRect( (int)(handler.getXInWorldDisplacement() + getXOffset()- 300),(int)(handler.getYInWorldDisplacement() + getYOffset()), 275, 45);
						g2.setFont(new Font("ARIAL", 15, 15));
						g2.drawString("Nice! I think you can do to the cave now.", (int)(handler.getXInWorldDisplacement() + getXOffset() - 300),(int)(handler.getYInWorldDisplacement() + getYOffset())+ 15);
						g2.drawString("Also, here's a new 'ICE'skill; good luck!", (int)(handler.getXInWorldDisplacement() + getXOffset() - 300),(int)(handler.getYInWorldDisplacement() + getYOffset())+ 35);

						handler.getEntityManager().getPlayer().questComplete = true;
					}
				

				else if(!handler.getEntityManager().getPlayer().enemyKilled) {	
					g2.setColor(Color.WHITE);
					g2.fillRect((int)(handler.getXInWorldDisplacement() + getXOffset() - 300),(int)(handler.getYInWorldDisplacement() + getYOffset()), 275, 45);
					g2.setColor(Color.BLACK);
					g2.drawRect( (int)(handler.getXInWorldDisplacement() + getXOffset()- 300),(int)(handler.getYInWorldDisplacement() + getYOffset()), 275, 45);
					g2.setFont(new Font("ARIAL", 15, 15));
					g2.drawString("Oh, you want to enter the cave?", (int)(handler.getXInWorldDisplacement() + getXOffset() - 300),(int)(handler.getYInWorldDisplacement() + getYOffset())+ 15);
					if(messageTimer == 180) {
						g2.setColor(Color.WHITE);
						g2.fillRect((int)(handler.getXInWorldDisplacement() + getXOffset() - 300),(int)(handler.getYInWorldDisplacement() + getYOffset()), 275, 45);
						g2.setColor(Color.BLACK);
						g2.drawRect( (int)(handler.getXInWorldDisplacement() + getXOffset()- 300),(int)(handler.getYInWorldDisplacement() + getYOffset()), 275, 45);
						g2.setFont(new Font("ARIAL", 15, 15));
						g2.drawString("Defeat the ghost Fernando!", (int)(handler.getXInWorldDisplacement() + getXOffset() - 300),(int)(handler.getYInWorldDisplacement() + getYOffset())+ 15);
					}
					else
						messageTimer++;
				}

			}
		}
		g2.draw(SJ);
		g2.draw(detector);


	}

	protected void showPressE(Graphics g, Color color) {
		g.setFont(new Font("Microsoft Jheng Hei UI", Font.BOLD, 35));
		g.setColor(color);
		g.drawString("PRESS E", (int) (handler.getXInWorldDisplacement() + getXOffset() - 40),
				(int) (handler.getYInWorldDisplacement() + getYOffset() - 50));
	}

	//Getters and Setters
	@Override
	public Rectangle getCollision() {
		return SJ;
	}

	@Override
	public double getXOffset() {
		return xPosition;
	}
}