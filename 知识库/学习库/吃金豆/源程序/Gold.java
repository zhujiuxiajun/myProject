import java.awt.*;

/**
 * 定义豆子的类
 */
public class Gold{
	private int xPos, yPos;
	private Rectangle gold;
	private int goldSpecies;

	/**
	 * 根据x、y和豆子的类型构造一个豆子的对象
	 */
	public Gold(int a, int b, int c){
		xPos = a;
		yPos = b;
		goldSpecies = c;
		gold = new Rectangle(xPos - 2, yPos - 2, 5, 5);
	}

	/**
	 * 根据x、y和豆子的类型构造一个豆子的对象
	 */
	public void draw(Graphics g){
		g.setColor(Color.yellow);
		//普通的小豆子
		if(goldSpecies == 1){
			g.fillOval(xPos - 2, yPos - 2, 4, 4);
		}
		//可以使敌人失去防抗能力的大豆子
		if(goldSpecies == 2){
			g.fillOval(xPos - 4, yPos - 4, 9, 9);
		}
	}

	public Rectangle getBorder(){
		return gold;
	}

	/**
	 * 判断是否是大豆子
	 */
	public boolean bigGold(){
		return goldSpecies == 2;
	}
}