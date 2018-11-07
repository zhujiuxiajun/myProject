import java.awt.*;

/**
 * ����Զ��ߵ���
 */
public class Player{

	private int xPos;
	private int yPos;
	private int xVPos;
	private int yVPos;
	private int speed;
	private int direction;
	private Rectangle player;
	//�Զ��ߵ��ſ��Ƕ�
	private int mouthDegree;
	//�Զ����Ƿ��ƶ�
	private boolean isMoving = true;
	//�Զ��ߵ��š������
	private boolean mouthOpen, mouthClose;
	//�Ƿ�������������ƶ�
	private boolean canMoveUp, canMoveDown, canMoveLeft, canMoveRight;
	//��Ϸ�Ƿ����
	private boolean dead;

	/**
	 **���캯������ʼ���Զ��ߵ�����
	 */
	public Player(){
		xPos = 210;
		yPos = 357;
		xVPos = xPos;
		yVPos = yPos;
		direction = 2;
		//����һ���µ� Rectangle�������ϽǱ�ָ��Ϊ��xPos - 10, yPos - 5���������Ⱥ͸߶�Ϊ��11,11���� 
		player = new Rectangle(xPos - 10, yPos - 5, 11, 11);
		//�Զ��ߵĳ�ʼ�ſ��Ƕ�Ϊ30��
		mouthDegree = 30;
		//��ʼ�ź�״̬���ſ�
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

		//��串��ָ�����ε�Բ������Բ����
		//�õ��Ļ��ɣ�mouthDegree + DirectionAngle������Խ��180 - mouthDegree���Ƕȡ�
		//���������Ǿ��ε����ģ��˾��ε�ԭ��Ϊ (xPos - 10, yPos - 10)����СΪ��21, 21�� 
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
		
		// ����һ���µ� Rectangle�������ϽǱ�ָ��Ϊ��xVPos - 10��yVPos - 12���������Ⱥ͸߶�Ϊ��21��21����
		Rectangle UP = new Rectangle(xVPos - 10, yVPos - 12, 21, 21);
		Rectangle DOWN = new Rectangle(xVPos - 10, yVPos - 8, 21, 21);
		Rectangle LEFT = new Rectangle(xVPos - 12, yVPos - 10, 21, 21);
		Rectangle RIGHT = new Rectangle(xVPos - 8, yVPos - 10, 21, 21);

		for(int i = 0; i < wall.length; i++){
			
			R = new Rectangle(wall[i].getxPos() - 10, wall[i].getyPos() - 10, 21, 21);
			
			//ȷ���� Rectangle R�Ƿ���ָ�� Rectangle UP �ཻ������������εĽ���Ϊ�ǿգ����������ཻ�ġ� 
			//���R��UP�ཻ���򷵻� true�����򷵻� false��
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

		//����Զ��߿����ƶ�����������λ��=ԭ����λ��+�ٶ�
		if(direction == 0 && canMoveUp)
			yPos-=speed;
		else if(direction == 1 && canMoveDown)
			yPos+=speed;
		else if(direction == 2 && canMoveLeft)
			xPos-=speed;
		else if(direction == 3 && canMoveRight)
			xPos+=speed;
		//���Զ��ߵ��ƶ�״̬���ó�Ϊ�������ƶ�
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
	 **�������ı�ǰ������
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
	 **����ֵ��int
	 **����������xPos
	 */
	public int getxPos(){
		return xPos;
	}

	/**
	 **����ֵ��int
	 **����������yPos
	 */
	public int getyPos(){
		return yPos;
	}

	public Rectangle getBorder(){
		return player;
	}
	
	/**
	 **�������Զ���ֹͣ�˶�
	 */
	public void stop(){
		speed = 0;
	}

	/**
	 **��������Ϸ���������óԶ��ߵĽǶ�Ϊ30
	 */
	public void Dead(){
		dead = true;
		mouthDegree = 30;
	}
}