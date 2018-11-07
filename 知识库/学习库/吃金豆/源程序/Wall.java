import java.awt.*;

/**
 * ����ǽ�߽����
 */
public class Wall{

	private int xPos;
	private int yPos;
	private int IndexOfImage;
	private Rectangle wall;

	/**
	 * ����x��y��ͼƬ��������
	 */
	public Wall(int a, int b, int c){
		xPos = a;
		yPos = b;
		IndexOfImage = c;
		wall = new Rectangle(xPos - 10, yPos - 10, 21, 21);
	}
	
	/**
	 * ����ǽ�ı߽�
	 */
	public Rectangle getWallBorder(){
		return wall;
	}

	/**
	 * ����ͼƬ��������ֵ
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
