import java.awt.*;

/**
 * 定义墙边界的类
 */
public class Wall{

	private int xPos;
	private int yPos;
	private int IndexOfImage;
	private Rectangle wall;

	/**
	 * 根据x、y和图片索引构造
	 */
	public Wall(int a, int b, int c){
		xPos = a;
		yPos = b;
		IndexOfImage = c;
		wall = new Rectangle(xPos - 10, yPos - 10, 21, 21);
	}
	
	/**
	 * 返回墙的边界
	 */
	public Rectangle getWallBorder(){
		return wall;
	}

	/**
	 * 返回图片索引构的值
	 */
	public int getImageIndex(){
		return IndexOfImage;
	}

	public int getxPos(){
		return xPos;
	}

	public int getyPos(){
		return yPos;
	}

}
