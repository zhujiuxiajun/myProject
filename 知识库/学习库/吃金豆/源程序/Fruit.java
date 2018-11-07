import java.awt.*;

/**
 * 定义水果的类
 */
public class Fruit{
	private int xPos, yPos, xVPos, yVPos;
	private int direction;
	private Rectangle fruit;
	private int appearTime;
	private int AI_time1;
	private int interval;

	public Fruit(){
		xPos = 210;
		yPos = 189;
		xVPos = 210;
		yVPos = 189;
		fruit = new Rectangle(xPos - 6, yPos - 6, 13, 13);
		direction = 2 + (int)(Math.random()*2);
		AI_time1 = 0;
		interval = 63 + 21*((int)(Math.random()*10));
	}

	/**
	 * 移动水果
	 */
	public void move(Wall[] wall){
		appearTime++;

		boolean canMoveUp, canMoveDown, canMoveRight, canMoveLeft, changeMovement;

		canMoveUp = true;
		canMoveDown = true;
		canMoveLeft = true;
		canMoveRight = true;
		changeMovement = false;

		Rectangle R;
		Rectangle UP = new Rectangle(xVPos - 10, yVPos - 10 - 1, 21, 21);
		Rectangle DOWN = new Rectangle(xVPos - 10, yVPos - 10 + 1, 21, 21);
		Rectangle LEFT = new Rectangle(xVPos - 10 - 1, yVPos - 10, 21, 21);
		Rectangle RIGHT = new Rectangle(xVPos - 10 + 1, yVPos - 10, 21, 21);

		for(int i = 0; i < wall.length; i++){
			R = new Rectangle(wall[i].getxPos() - 10, wall[i].getyPos() - 10, 21, 21);
			if(R.intersects(UP))
				canMoveUp = false;
			if(R.intersects(UP) && direction == 0){
				yPos = yVPos;
				changeMovement = true;
			}

			if(R.intersects(DOWN))
				canMoveDown = false;
			if(R.intersects(DOWN) && direction == 1){
				yPos = yVPos;
				changeMovement = true;
			}

			if(R.intersects(LEFT))
				canMoveLeft = false;
			if(R.intersects(LEFT) && direction == 2){
				xPos = xVPos;
				changeMovement = true;
			}

			if(R.intersects(RIGHT))
				canMoveRight = false;
			if(R.intersects(RIGHT) && direction == 3){
				xPos = xVPos;
				changeMovement = true;
			}
		}

		interval-=1;
		if(interval <=0)
			changeMovement = true;

		int u = (int)(Math.random()*4);
		if(canMoveUp && direction != 1 && AI_time1 > 10 && u == 0){
			direction = 0;
			xPos = xVPos;
			AI_time1 = 0;
		}
		if(canMoveDown && direction != 0 && AI_time1 > 10 && u == 1){
			direction = 1;
			xPos = xVPos;
			AI_time1 = 0;
		}
		if(canMoveLeft && direction != 3 && AI_time1 > 10 && u == 2){
			direction = 2;
			xPos = xVPos;
			AI_time1 = 0;
		}
		if(canMoveRight && direction != 2 && AI_time1 > 10 && u == 3){
			direction = 3;
			xPos = xVPos;
			AI_time1 = 0;
		}

		if(changeMovement){
			for(;;){
				u = (int)(Math.random()*2);
				if(direction == 0 || direction == 1){
					if(u == 0){
						if(canMoveLeft)
							direction = 2;
						if(canMoveRight)
							direction = 3;
					}
					if(u == 1){
						if(canMoveRight)
							direction = 3;
						if(canMoveLeft)
							direction = 2;
					}
					AI_time1 = 0;
					break;
				}
				if(direction == 2 || direction == 3){
					if(u == 0){
						if(canMoveUp)
							direction = 0;
						if(canMoveDown)
							direction = 1;
					}
					if(u == 1){
						if(canMoveDown)
							direction = 1;
						if(canMoveUp)
							direction = 0;
					}
					AI_time1 = 0;
					break;
				}
			}
			interval = 63 + 21*((int)(Math.random()*10));
		}

		if(direction == 0 && canMoveUp)
			yPos-=1;
		if(direction == 1 && canMoveDown)
			yPos+=1;
		if(direction == 2 && canMoveLeft)
			xPos-=1;
		if(direction == 3 && canMoveRight)
			xPos+=1;


		if(direction == 2 && xPos < 0)
			xPos = 420;
		if(direction == 3 && xPos > 420)
			xPos = 0;

		fruit = new Rectangle(xPos - 6, yPos - 6, 13, 13);

		int a = (xPos - 10)/21;
		int b = (xPos - 10)%21;
		if(b < 6)
			b = 0;
		if(b > 16)
			b = 21;
		if(b < 17 && b > 5)
			b = 11;
		xVPos = a*21 + b + 10;

		int c = (yPos - 10)/21;
		int d = (yPos - 10)%21;
		if(d < 6)
			d = 0;
		if(d > 16)
			d = 21;
		if(d < 17 && d > 5)
			d = 11;
		yVPos = c*21 + d + 10;
	}

	public void draw(Graphics g){
		double y = yPos +  + 3*(Math.sin(6*appearTime));
		g.setColor (new Color (254, 184, 4));
		g.fillOval(xPos - 5, (int)(y - 5), 11, 11);
		g.setColor (new Color (28, 232, 4));
		g.fillRect(xPos - 5, (int)(y - 5), 3, 3);
		g.fillRect(xPos - 7, (int)(y - 7), 3, 3);
	}

	public int getAppearTime(){
		return appearTime;
	}

	public Rectangle getBorder(){
		return fruit;
	}
}



