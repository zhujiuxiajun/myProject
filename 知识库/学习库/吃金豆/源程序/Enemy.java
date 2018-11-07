import java.awt.*;

/**
 * ������˵���
 */
public class Enemy{
	private int xPos;
	private int yPos;
	private int xVPos;
	private int yVPos;
	private int direction;
	
	//�����Ƿ�ʧȥ��������
	private boolean alarm;
	//����ʧȥ����������ʱ��
	private int alarmTime;
	private boolean ghostMode;
	private Rectangle Enemy;
	private int speed;
	private int interval;
	private int Colour;
	private int AI_time1, AI_time2;
	private int lefttime, uptime, righttime, downtime;
	private int gameFlow;
	private boolean ghost;
	private boolean canMoveUp, canMoveDown, canMoveLeft,canMoveRight;
	private boolean cantGoAnyWhere;
	private int prisonTime;
	private boolean stop;
	
	/**
	 **����x��y����ɫ���Ƿ��Ѿ���������ʣ��೤ʱ��ſɳ�����
	 **������˶���
	 */
	public Enemy(int a, int b, int c, boolean cantGoAnyWhere, int prisonTime){
		xPos = a;
		yPos = b;
		xVPos = xPos;
		yVPos = yVPos;
		direction = 3;
		speed = 3;
		interval = 63 + 21*((int)(Math.random()*10));
		AI_time1 = 0;
		Colour = c;
		this.prisonTime = prisonTime;
		this.cantGoAnyWhere = cantGoAnyWhere;
		Enemy = new Rectangle(xPos -10, yPos - 10, 21, 21);
	}

	/**
	 **���������˵��ƶ�
	 */
	public void move(int a, int b, Wall[] wall){
		AI_time1++;
		AI_time2++;
		uptime++;
		downtime++;
		lefttime++;
		righttime++;
		gameFlow++;
		
		//�������û�з�������
		if(alarm)
			alarmTime++;
		boolean changeMovement = false;

		//������˲����ƶ����ͽ�������ʱ�䲻ͣ����
		if(cantGoAnyWhere)
			prisonTime++;

		//������ʧȥ�����������ٶȽ���
		if(alarm)
			speed = 1;
		else
			speed = 2;

		//ʧȥ����������ָ��ʱ��󣬻ָ�ԭ̬
		if(alarmTime >= 330){
			alarmTime = 0;
			alarm = false;
		}


		canMoveUp = true;
		canMoveDown = true;
		canMoveLeft = true;
		canMoveRight = true;

		Rectangle R;
		Rectangle UP = new Rectangle(xVPos - 10, yVPos - 10 - speed, 21, 21);
		Rectangle DOWN = new Rectangle(xVPos - 10, yVPos - 10 + speed, 21, 21);
		Rectangle LEFT = new Rectangle(xVPos - 10 - speed, yVPos - 10, 21, 21);
		Rectangle RIGHT = new Rectangle(xVPos - 10 + speed, yVPos - 10, 21, 21);

		for(int i = 0; i < wall.length; i++){
			R = new Rectangle(wall[i].getxPos() - 10, wall[i].getyPos() - 10, 21, 21);
			if(R.intersects(UP))
				canMoveUp = false;
			if(prisonTime < 231){
				//ȷ���� Rectangle R�Ƿ���ָ�� Rectangle UP �ཻ������������εĽ���Ϊ�ǿգ����������ཻ�ġ� 
				//���R��UP�ཻ���򷵻� true�����򷵻� false��
				if(R.intersects(UP) && direction == 0){
					yPos = yVPos;
					changeMovement = true;
				}
			}

			if(!(ghost && (yVPos >= 189 && yVPos < 231 && xVPos == 210))){
				if(R.intersects(DOWN))
					canMoveDown = false;
				if(R.intersects(DOWN) && direction == 1){
					yPos = yVPos;
					changeMovement = true;
				}
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

		interval-=speed;
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

		if(!alarm && !cantGoAnyWhere){
			if(AI_time2 > 99){
				if(canMoveUp && yPos > b && AI_time2 > 99){
					direction = 0;
					xPos = xVPos;
					AI_time2 = 0;
				}
				if(canMoveDown && yPos < b && AI_time2 > 99){
					direction = 1;
					xPos = xVPos;
					AI_time2 = 0;
				}
				if(canMoveLeft && xPos > a && AI_time2 > 99){
					direction = 2;
					yPos = yVPos;
					AI_time2 = 0;
				}
				if(canMoveRight && xPos < a && AI_time2 > 99){
					direction = 3;
					yPos = yVPos;
					AI_time2 = 0;
				}
			}
		}
		if(alarm){
			if(AI_time2 > 99){
				if(canMoveDown && yPos > b && AI_time2 > 99){
					direction = 1;
					xPos = xVPos;
					AI_time2 = 0;
				}
				if(canMoveUp && yPos < b && AI_time2 > 99){
					direction = 0;
					xPos = xVPos;
					AI_time2 = 0;
				}
				if(canMoveRight && xPos > a && AI_time2 > 99){
					direction = 3;
					yPos = yVPos;
					AI_time2 = 0;
				}
				if(canMoveLeft && xPos < a && AI_time2 > 99){
					direction = 2;
					yPos = yVPos;
					AI_time2 = 0;
				}
			}
		}
		if(ghost){
			a = 210;
			b = 189;
			speed = 4;
			alarm = false;
			alarmTime = 0;
			if(AI_time2 > 99){
				if(canMoveUp && yPos > b && AI_time2 > 99){
					direction = 0;
					xPos = xVPos;
					AI_time2 = 0;
				}
				if(canMoveDown && yPos < b && AI_time2 > 99){
					direction = 1;
					xPos = xVPos;
					AI_time2 = 0;
				}
				if(canMoveLeft && xPos > a && AI_time2 > 99){
					direction = 2;
					yPos = yVPos;
					AI_time2 = 0;
				}
				if(canMoveRight && xPos < a && AI_time2 > 99){
					direction = 3;
					yPos = yVPos;
					AI_time2 = 0;
				}
			}
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
					if(cantGoAnyWhere){
						if(canMoveLeft)
							direction = 2;
						if(canMoveRight)
							direction = 3;
					}
					AI_time1 = 0;
					break;
				}
			}

			if(!alarm && !ghost && !cantGoAnyWhere){
				if(canMoveLeft && xPos > a && lefttime > 66){
					direction = 2;
					yPos = yVPos;
					AI_time2 = 0;
					lefttime = 0;
				}
				if(canMoveRight && xPos < a && righttime> 66){
					direction = 3;
					yPos = yVPos;
					AI_time2 = 0;
					righttime = 0;
				}
				if(canMoveUp && yPos > b && uptime > 66){
					direction = 0;
					xPos = xVPos;
					AI_time2 = 0;
					uptime = 0;
				}
				if(canMoveDown && yPos < b && downtime > 66){
					direction = 1;
					xPos = xVPos;
					AI_time2 = 0;
					downtime = 0;
				}
			}
			if(alarm && !ghost){
				if(canMoveRight && xPos > a && righttime > 66){
					direction = 3;
					yPos = yVPos;
					AI_time2 = 0;
					lefttime = 0;
				}
				if(canMoveLeft && xPos < a && lefttime> 66){
					direction = 2;
					yPos = yVPos;
					AI_time2 = 0;
					righttime = 0;
				}
				if(canMoveDown && yPos > b && downtime > 66){
					direction = 1;
					xPos = xVPos;
					AI_time2 = 0;
					uptime = 0;
				}
				if(canMoveUp && yPos < b && uptime > 66){
					direction = 0;
					xPos = xVPos;
					AI_time2 = 0;
					downtime = 0;
				}
			}
			if(ghost){
				a = 210;
				b = 189;
				if(canMoveLeft && xPos > a && lefttime > 66){
					direction = 2;
					yPos = yVPos;
					AI_time2 = 0;
					lefttime = 0;
				}
				if(canMoveRight && xPos < a && righttime> 66){
					direction = 3;
					yPos = yVPos;
					AI_time2 = 0;
					righttime = 0;
				}
				if(canMoveUp && yPos > b && uptime > 66){
					direction = 0;
					xPos = xVPos;
					AI_time2 = 0;
					uptime = 0;
				}
				if(canMoveDown && yPos < b && downtime > 66){
					direction = 1;
					xPos = xVPos;
					AI_time2 = 0;
					downtime = 0;
				}
			}
			interval = 63 + 21*((int)(Math.random()*10));
		}

		if(ghost){
			if(xVPos == 189 && yVPos == 441)
				direction = 0;
			if(xVPos == 231 && yVPos == 441)
				direction = 0;
			if(xVPos == 189 && yVPos == 399)
				direction = 2;
			if(xVPos == 231 && yVPos == 399)
				direction = 3;
			if(xVPos == 189 && yVPos == 357)
				direction = 0;
			if(xVPos == 231 && yVPos == 357)
				direction = 0;
			if(xVPos == 189 && yVPos == 315)
				direction = 2;
			if(xVPos == 231 && yVPos == 315)
				direction = 3;
			if(xVPos == 147 && yVPos == 315)
				direction = 0;
			if(xVPos == 273 && yVPos == 315)
				direction = 0;
			if(xVPos == 105 && yVPos == 231)
				direction = 3;
			if(xVPos == 315 && yVPos == 231)
				direction = 2;
			if(xVPos == 147 && yVPos == 105)
				direction = 1;
			if(xVPos == 273 && yVPos == 105)
				direction = 1;
			if(yVPos >= 189 && yVPos < 231 && xVPos == 210)
				direction = 1;
			if(xVPos == 210 && yVPos == 231){
				ghost = false;
				speed = 2;
				cantGoAnyWhere = true;
				prisonTime = 0;
			}
		}

		if(prisonTime > 231){
			if(yVPos > 189 && yVPos <= 231 && xVPos == 210){
				direction = 0;
				canMoveUp = true;
			}
			if(yVPos == 189){
				prisonTime = 0;
				cantGoAnyWhere = false;
			}
		}

		if(stop)
			speed = 0;
		if(direction == 0 && canMoveUp)
			yPos-=speed;
		if(direction == 1 && canMoveDown)
			yPos+=speed;
		if(direction == 2 && canMoveLeft)
			xPos-=speed;
		if(direction == 3 && canMoveRight)
			xPos+=speed;


		if(direction == 2 && xPos < 0)
			xPos = 420;
		if(direction == 3 && xPos > 420)
			xPos = 0;

		Enemy = new Rectangle(xPos -10, yPos - 10, 21, 21);

		a = (xPos - 10)/21;
		b = (xPos - 10)%21;
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
		if(Colour == 1)
			g.setColor(Color.red);
		if(Colour == 2)
			g.setColor(Color.cyan);
		if(Colour == 3)
			g.setColor(Color.pink);
		if(Colour == 4)
			g.setColor(Color.orange);
		
		//Polygon ���װ������ռ��з�յĶ�ά��������������������������߶�Ϊ�߽磬ÿ���߶ζ��Ƕ���ε�һ����
		Polygon Ghost = null;
		if(gameFlow% 16 > 8){
			int[] xPoints = {xPos - 5,
						xPos - 9,
						xPos - 9,
						xPos - 6,
						xPos - 3,
						xPos,
						xPos + 3,
						xPos + 6,
						xPos + 10,
						xPos + 10,
						xPos + 5,
					};
			int[] yPoints = new int[] {yPos - 9,
						yPos - 4,
						yPos + 9,
						yPos + 4,
						yPos + 9,
						yPos + 4,
						yPos + 9,
						yPos + 4,
						yPos + 9,
						yPos - 4,
						yPos - 9,
					};
			//��ʼ���µ� Polygon�� 
			//������xPointsΪx��������飻yPointsΪy��������飻xPoints.lengthΪ��Polygon�е������ 
			Ghost = new Polygon (xPoints, yPoints, xPoints.length);
		}

		if(gameFlow% 16 < 9){
			int[] xPoints = {xPos - 5,
						xPos - 9,
						xPos - 9,
						xPos - 6,
						xPos,
						xPos + 6,
						xPos + 10,
						xPos + 10,
						xPos + 5,
					};
			int[] yPoints = new int[] {yPos - 9,
						yPos - 4,
						yPos + 9,
						yPos + 4,
						yPos + 9,
						yPos + 4,
						yPos + 9,
						yPos - 4,
						yPos - 9,
					};
			Ghost = new Polygon (xPoints, yPoints, xPoints.length);
		}
		
		if(!alarm && !ghost){
			//��ͼ�������ĵĵ�ǰ��ɫ�����ָ����Polygon������Ķ����Ghost��
			g.fillPolygon(Ghost);
		}

		//�������û��ʧȥ����������������ɫ���������˵����壬�ð�ɫ�����˵��۾���
		if(!alarm || ghost){
			//�������۾��İ�ɫ����
			g.setColor(Color.white);
			g.fillOval(xPos - 8, yPos - 5, 7, 8);
			g.fillOval(xPos + 2, yPos - 5, 7, 8);
			
			//�������۾��е���ɫʮ�ֲ���
			g.setColor(Color.blue);
			if(direction == 0){
				//ʹ�õ�ǰ��ɫ������ָ�����ο����Բ��
				g.fillOval(xPos - 6, yPos - 4, 3, 4);
				g.fillOval(xPos + 4, yPos - 4, 3, 4);
			}
			if(direction == 1){
				g.fillOval(xPos - 6, yPos - 2, 3, 4);
				g.fillOval(xPos + 4, yPos - 2, 3, 4);
			}
			if(direction == 2){
				g.fillOval(xPos - 7, yPos - 3, 3, 4);
				g.fillOval(xPos + 3, yPos - 3, 3, 4);
			}
			if(direction == 3){
				g.fillOval(xPos - 5, yPos - 3, 3, 4);
				g.fillOval(xPos + 5, yPos - 3, 3, 4);
			}
		}
		//�������ʧȥ��������
		if(alarm){
			g.setColor(Color.blue);
			
			//�����˼����ָ���������ʱ��������ɫ��ʾΪ��ɫ
			if(alarmTime >= 231 && alarmTime%6 > 2)
				g.setColor(Color.lightGray);
			g.fillPolygon(Ghost);
			
			g.setColor(Color.white);
			g.fillOval(xPos - 6, yPos - 6, 5, 6);
			g.fillOval(xPos + 2, yPos - 6, 5, 6);
			
			//�ڴ�ͼ�������ĵ�����ϵͳ�У�ʹ�õ�ǰ��ɫ����ɫ����
			//�ڵ�(xPos - 6, yPos + 3)��(xPos - 4, yPos + 1)֮�仭һ���ߡ� 
			g.drawLine(xPos - 6, yPos + 3, xPos - 4, yPos + 1);
			g.drawLine(xPos - 4, yPos + 1, xPos - 2, yPos + 3);
			g.drawLine(xPos - 2, yPos + 3, xPos, yPos + 1);
			g.drawLine(xPos, yPos + 1, xPos + 2, yPos + 3);
			g.drawLine(xPos + 2, yPos + 3, xPos + 4, yPos + 1);
			g.drawLine(xPos + 4, yPos + 1, xPos + 6, yPos + 3);
		}
	}
	
	/**
	 **���������õ����Ƿ���ʧȥ����������״̬
	 */
	public void Alarm(int a){
		if(a == 1)
			alarm = true;
		if(a == 2)
			alarm = false;
		alarmTime = 0;
	}

	public void Ghost(){
		ghost = true;
		alarmTime = 0;
	}

	public Rectangle getBorder(){
		return Enemy;
	}

	public int status(){
		int a = 0;
		if(alarm)
			a = 1;
		if(ghost)
			a = 2;
		return a;
	}

	public void stop(){
		stop = true;
	}
}