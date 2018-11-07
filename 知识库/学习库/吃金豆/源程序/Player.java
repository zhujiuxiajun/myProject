import java.awt.*;

/**
 * 定义吃豆者的类
 */
public class Player{

	private int xPos;
	private int yPos;
	private int xVPos;
	private int yVPos;
	private int speed;
	private int direction;
	private Rectangle player;
	//吃豆者的张开角度
	private int mouthDegree;
	//吃豆者是否移动
	private boolean isMoving = true;
	//吃豆者的张、合情况
	private boolean mouthOpen, mouthClose;
	//是否可以上下左右移动
	private boolean canMoveUp, canMoveDown, canMoveLeft, canMoveRight;
	//游戏是否结束
	private boolean dead;

	/**
	 **构造函数：初始化吃豆者的属性
	 */
	public Player(){
		xPos = 210;
		yPos = 357;
		xVPos = xPos;
		yVPos = yPos;
		direction = 2;
		//构造一个新的 Rectangle，其左上角被指定为（xPos - 10, yPos - 5），而其宽度和高度为（11,11）。 
		player = new Rectangle(xPos - 10, yPos - 5, 11, 11);
		//吃豆者的初始张开角度为30度
		mouthDegree = 30;
		//初始张合状态：张开
		mouthOpen = true;
		speed = 2;
	}

	public void draw(Graphics g){
		int DirectionAngle = 180;
		g.setColor(Color.yellow);
		if(direction == 0)
			DirectionAngle = 90;
		if(direction == 1)
			DirectionAngle = -90;
		if(direction == 2)
			DirectionAngle = 180;
		if(direction == 3)
			DirectionAngle = 0;
		if(dead)
			DirectionAngle = 90;

		//填充覆盖指定矩形的圆弧或椭圆弧。
		//得到的弧由（mouthDegree + DirectionAngle）并跨越（180 - mouthDegree）角度。
		//弧的中心是矩形的中心，此矩形的原点为 (xPos - 10, yPos - 10)，大小为（21, 21） 
		g.fillArc(xPos - 10, yPos - 10, 21, 21, mouthDegree + DirectionAngle, 180 - mouthDegree);
		
		g.fillArc(xPos - 10, yPos - 10, 21, 21, 180 + DirectionAngle, 180 - mouthDegree);
	}

	public void move(Wall[] wall){
		
		isMoving = true;

		canMoveUp = true;
		canMoveDown = true;
		canMoveLeft = true;
		canMoveRight = true;

		if(!dead){
			if(mouthDegree == 70){
				mouthClose = true;
				mouthOpen = false;
			}
			if(mouthDegree == -10){
				mouthOpen = true;
				mouthClose = false;
			}
		}


		Rectangle R;
		
		// 构造一个新的 Rectangle，其左上角被指定为（xVPos - 10，yVPos - 12），而其宽度和高度为（21，21）。
		Rectangle UP = new Rectangle(xVPos - 10, yVPos - 12, 21, 21);
		Rectangle DOWN = new Rectangle(xVPos - 10, yVPos - 8, 21, 21);
		Rectangle LEFT = new Rectangle(xVPos - 12, yVPos - 10, 21, 21);
		Rectangle RIGHT = new Rectangle(xVPos - 8, yVPos - 10, 21, 21);

		for(int i = 0; i < wall.length; i++){
			
			R = new Rectangle(wall[i].getxPos() - 10, wall[i].getyPos() - 10, 21, 21);
			
			//确定此 Rectangle R是否与指定 Rectangle UP 相交。如果两个矩形的交集为非空，则它们是相交的。 
			//如果R与UP相交，则返回 true；否则返回 false。
			if(R.intersects(UP))
				canMoveUp = false;
			if(R.intersects(UP) && direction == 0)
				yPos = yVPos;

			if(R.intersects(DOWN))
				canMoveDown = false;
			if(R.intersects(DOWN) && direction == 1)
				yPos = yVPos;

			if(R.intersects(LEFT))
				canMoveLeft = false;
			if(R.intersects(LEFT) && direction == 2)
				xPos = xVPos;

			if(R.intersects(RIGHT))
				canMoveRight = false;
			if(R.intersects(RIGHT) && direction == 3)
				xPos = xVPos;
		}

		//如果吃豆者可以移动，则新坐标位置=原坐标位置+速度
		if(direction == 0 && canMoveUp)
			yPos-=speed;
		else if(direction == 1 && canMoveDown)
			yPos+=speed;
		else if(direction == 2 && canMoveLeft)
			xPos-=speed;
		else if(direction == 3 && canMoveRight)
			xPos+=speed;
		//将吃豆者的移动状态设置成为：不能移动
		else
			isMoving = false;

		if(direction == 2 && xPos < 0)
			xPos = 420;
		if(direction == 3 && xPos > 420)
			xPos = 0;

		player = new Rectangle(xPos - 5, yPos - 5, 11, 11);

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

		int angularSpeed = 10;
		if(dead && mouthDegree <= 175){
			isMoving = true;
			mouthOpen = true;
			mouthClose = false;
			angularSpeed = 5;
		}

		if(dead && mouthDegree > 175){
			isMoving = false;
		}

		if(mouthOpen && isMoving){
			mouthDegree+=angularSpeed ;
		}
		
		if(mouthClose && isMoving){
			mouthDegree-=angularSpeed ;
		}

	}

	/**
	 **方法：改变前进方向
	 */
	public void ChangeDirection(int a){
		if(a == 0 && canMoveUp){
			direction = 0;
			xPos = xVPos;
		}
		if(a == 1 && canMoveDown){
			direction = 1;
			xPos = xVPos;
		}

		if(a == 2 && canMoveLeft){
			direction = 2;
			yPos = yVPos;
		}

		if(a == 3 && canMoveRight){
			direction = 3;
			yPos = yVPos;
		}
	}

	/**
	 **返回值：int
	 **方法：返回xPos
	 */
	public int getxPos(){
		return xPos;
	}

	/**
	 **返回值：int
	 **方法：返回yPos
	 */
	public int getyPos(){
		return yPos;
	}

	public Rectangle getBorder(){
		return player;
	}
	
	/**
	 **方法：吃豆者停止运动
	 */
	public void stop(){
		speed = 0;
	}

	/**
	 **方法：游戏结束，设置吃豆者的角度为30
	 */
	public void Dead(){
		dead = true;
		mouthDegree = 30;
	}
}