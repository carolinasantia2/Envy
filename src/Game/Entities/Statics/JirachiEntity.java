package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Main.Handler;
import Resources.Images;

public class JirachiEntity extends BaseStaticEntity {

	Rectangle collision;
	int width, height;

	public JirachiEntity(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);
		width = 40;
		height = 40;

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);


		collision = new Rectangle();
	}


	@Override
	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(Images.Jirachi, (int)(handler.getXDisplacement() + xPosition),(int)(handler.getYDisplacement() + yPosition), width, height, null);
		collision = new Rectangle((int)(handler.getXDisplacement() + xPosition), (int)(handler.getYDisplacement() + yPosition + 30), width, height);
		if(this.handler.getEntityManager().getPlayer().getCollision().intersects(this.getCollision())) {
			if(!handler.getEntityManager().getPlayer().questComplete){
				g2.setColor(Color.WHITE);
				g.fillRect((int) (this.xPosition + this.handler.getXDisplacement() + 50),(int) (60-10 + this.handler.getYDisplacement() - 50), 300, 45);
				g2.setColor(Color.BLACK);
				g.drawRect((int) (this.xPosition + this.handler.getXDisplacement() + 50),(int) (60-10 + this.handler.getYDisplacement() - 50), 300, 45);
				g2.setFont(new Font("ARIAL", 15, 15));
				g2.drawString("Only those who are worthy may enter!", (int) (this.xPosition + this.handler.getXDisplacement() + 50 + 8),(int) (60-10 + this.handler.getYDisplacement() - 50 + 20));
				g2.drawString("Prove it with a quest!", (int) (this.xPosition + this.handler.getXDisplacement() + 50 + 70),(int) (60-10 + this.handler.getYDisplacement() - 50 + 2 + 35));
			}
			else{
				this.handler.getWorldManager().getJWall().setBounds(0, 0, 0, 0);
			}
		}	
		g2.draw(collision);
	}

	@Override
	public Rectangle getCollision() {
		return collision;
	}

	@Override
	public double getXOffset() {
		return xPosition;
	}


}
