import java.awt.*;

/**
 * ���嶹�ӵ���
 */
public class Gold{
	private int xPos, yPos;
	private Rectangle gold;
	private int goldSpecies;

	/**
	 * ����x��y�Ͷ��ӵ����͹���һ�����ӵĶ���
	 */
	public Gold(int a, int b, int c){
		xPos = a;
		yPos = b;
		goldSpecies = c;
		gold = new Rectangle(xPos - 2, yPos - 2, 5, 5);
	}

	/**
	 * ����x��y�Ͷ��ӵ����͹���һ�����ӵĶ���
	 */
	public void draw(Graphics g){
		g.setColor(Color.yellow);
		//��ͨ��С����
		if(goldSpecies == 1){
			g.fillOval(xPos - 2, yPos - 2, 4, 4);
		}
		//����ʹ����ʧȥ���������Ĵ���
		if(goldSpecies == 2){
			g.fillOval(xPos - 4, yPos - 4, 9, 9);
		}
	}

	public Rectangle getBorder(){
		return gold;
	}

	/**
	 * �ж��Ƿ��Ǵ���
	 */
	public boolean bigGold(){
		return goldSpecies == 2;
	}
}