import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.applet.*;

public class Packman extends JApplet implements KeyListener, ActionListener{

	private Ticker t;
	private Image offScreenImage;
	//定义吃豆者
	private Player player;
	//定义四个敌人
	private Enemy Red, Cyan, Pink, Orange;
	//健康值与分数
	private int health, Scores;
	//游戏是否结束
	private boolean gameover;
	//游戏是否开始
	private boolean gameStarted;
	private int alarmTime;
	private boolean alarm;

	private Fruit fruit;//定义水果

	private boolean UP_TYPED, DOWN_TYPED, LEFT_TYPED, RIGHT_TYPED;

	//定义墙
	private Image[] WALL;
	private AudioClip[] SOUND;

	private Wall[] wall;
	//定义豆子的数组
	private Gold[] gold;

	private int gameFlow;

	private boolean levelComplete;

	/**
	 * Applet初始化
	 */
	public void init(){
		
		player = new Player();
		health = 100;
		
		//初始化敌人
		Red = new Enemy(210, 189, 1, false, 0);
		Cyan = new Enemy(210, 231, 2, true, 0);
		Pink = new Enemy(220, 231, 3, true, 66);
		Orange = new Enemy(200, 231, 4, true, 132);

		//添加键盘事件侦听
		addKeyListener(this);
		requestFocus();

		t = new Ticker(30);
		t.addActionListener(this);

		gold = golds();

		wall = walls();
		WALL = new Image[47];
		for(int i = 0; i < 47; i++)
			WALL[i] = getImage(getDocumentBase(), "Image\\" + "Wall" + (i + 1) + ".jpg");
		SOUND = new AudioClip[8];
		for(int i = 0; i < 8; i++)
			SOUND[i] = getAudioClip(getDocumentBase(), "Sound\\" + (i + 1) + ".au");

	}

	public void start (){
		if(t != null)
			t.start();
	}

	public void stop(){
		SOUND[2].stop();
		SOUND[5].stop();
		t.stop();
		t = null;
	}

	public void actionPerformed(ActionEvent e){
		if(gameStarted)
			gameFlow++;

		if(alarm)
			alarmTime++;

		if(alarm && alarmTime == 330 && !gameover){
			alarm = false;
			SOUND[2].stop();
			SOUND[5].loop();
		}

		if(gameFlow == 110 && !gameover)
			SOUND[5].loop();

		if(health <= 0 && !gameover){
			gameover = true;
			gameFlow = 0;
			Red.stop();
			Cyan.stop();
			Pink.stop();
			Orange.stop();
			player.stop();
			if(fruit != null)
				fruit = null;
			SOUND[5].stop();
			SOUND[2].stop();
		}

		if(gameover && gameFlow == 66){
			player.Dead();
			SOUND[3].play();
		}

		if(fruit != null && fruit.getAppearTime() >660)
			fruit = null;
		if(fruit == null && gameFlow != 0 && gameFlow%1980 ==0 && !gameover)
			fruit = new Fruit();
		
		levelComplete = true;
		for(int i = 0; i < gold.length; i++){
			if(gold[i] != null){
				levelComplete = false;
				break;
			}
		}

		if(levelComplete){
			gold = null;
			gold = golds();
		}
		if(fruit != null)
			fruit.move(wall);

		if(gameStarted){
			player.move(wall);
			Red.move(player.getxPos(), player.getyPos(), wall);
			Cyan.move(player.getxPos(), player.getyPos(), wall);
			Pink.move(player.getxPos(), player.getyPos(), wall);
			Orange.move(player.getxPos(), player.getyPos(), wall);
		}

		if(fruit != null){
			if((fruit.getBorder()).intersects(player.getBorder())){
				fruit = null;
				Scores+=75;
				health+=50;
				if(health > 100)
					health = 100;
				SOUND[1].play();
			}
		}

		for(int i = 0; i < gold.length; i++){
			//如果吃豆者吃的是小金豆
			if(gold[i] != null){
				if((gold[i].getBorder()).intersects(player.getBorder()) && !(gold[i].bigGold())){
					if(gameFlow > 110)
						SOUND[0].play();
					gold[i] = null;
					Scores+=10;
					break;
				}
			}
			if(gold[i] != null){
				if((gold[i].getBorder()).intersects(player.getBorder()) && gold[i].bigGold()){
					gold[i] = null;
					Red.Alarm(1);
					Cyan.Alarm(1);
					Pink.Alarm(1);
					Orange.Alarm(1);
					Scores+=40;
					if(gameFlow > 110){
						SOUND[5].stop();
						SOUND[2].loop();
						alarm = true;
						alarmTime = 0;
					}
					break;
				}
			}
		}

		if((player.getBorder()).intersects(Red.getBorder())){
			if(Red.status() == 1){
				Red.Ghost();
				Scores+=50;
				SOUND[7].play();
			}
			if(Red.status() == 0 && !gameover)
				health--;
		}
		if((player.getBorder()).intersects(Cyan.getBorder())){
			if(Cyan.status() == 1){
				Cyan.Ghost();
				Scores+=50;
				SOUND[7].play();
			}
			if(Cyan.status() == 0 && !gameover)
				health-=2;
		}
		if((player.getBorder()).intersects(Pink.getBorder())){
			if(Pink.status() == 1){
				Pink.Ghost();
				Scores+=50;
				SOUND[7].play();
			}
			if(Pink.status() == 0 && !gameover)
				health-=3;
		}
		if((player.getBorder()).intersects(Orange.getBorder())){
			if(Orange.status() == 1){
				Orange.Ghost();
				Scores+=50;
				SOUND[7].play();
			}
			if(Orange.status() == 0 && !gameover)
				health-=4;
		}
		if(UP_TYPED)
			player.ChangeDirection(0);
		if(DOWN_TYPED)
			player.ChangeDirection(1);
		if(LEFT_TYPED)
			player.ChangeDirection(2);
		if(RIGHT_TYPED)
			player.ChangeDirection(3);

		repaint();
	}

	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 545, 482);

		for(int i = 0; i < wall.length; i++)
			g.drawImage(WALL[wall[i].getImageIndex() - 1], wall[i].getxPos() - 10, wall[i].getyPos() - 10, this);
		for(int i = 0; i < gold.length; i++){
			if(gold[i] != null && !(gold[i].bigGold() && gameFlow%16 > 7))
				gold[i].draw(g);
		}

		g.setColor(Color.black);
		g.fillRect(179, 200, 21, 21);
		g.fillRect(200, 200, 21, 21);
		g.fillRect(221, 200, 21, 21);
		g.setColor(Color.pink);
		g.fillRect(179, 204, 63, 2);

		player.draw(g);
		if(fruit != null)
			fruit.draw(g);
		if(!(gameover && gameFlow > 66)){
			Red.draw(g);
			Cyan.draw(g);
			Pink.draw(g);
			Orange.draw(g);
		}

		g.setColor(Color.black);
		g.fillRect(0, 220, 10, 23);
		g.fillRect(410, 220, 21, 25);
		g.fillRect(-10, 200, 21, 21);
		g.fillRect(-10, 242, 21, 21);
		g.fillRect(410, 200, 21, 21);
		g.fillRect(410, 242, 21, 21);

		g.setColor(Color.white);
		g.drawString("生命: " + health + "%", 420, 84);
		g.drawString("积分: " + Scores, 420, 105);

		if(!gameStarted){
			g.setColor(Color.cyan);
			g.drawString("       按空格开始",  153, 273);
		}
		if(gameover && gameFlow > 100){
			g.setColor(Color.red);
			g.drawString("游戏结束",  179, 238);
		}
	}

	public void keyPressed(KeyEvent e){
		
		//点击“空格”，游戏开始
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			SOUND[6].play();
			gameStarted = true;
		}

		//点击方向键，执行相应的操作
		if(e.getKeyCode() == KeyEvent.VK_UP){
			player.ChangeDirection(0);
			UP_TYPED = true;
			DOWN_TYPED = false;
			LEFT_TYPED = false;
			RIGHT_TYPED = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			player.ChangeDirection(1);
			UP_TYPED = false;
			DOWN_TYPED = true;
			LEFT_TYPED = false;
			RIGHT_TYPED = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT ){
			player.ChangeDirection(2);
			UP_TYPED = false;
			DOWN_TYPED = false;
			LEFT_TYPED = true;
			RIGHT_TYPED = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.ChangeDirection(3);
			UP_TYPED = false;
			DOWN_TYPED = false;
			LEFT_TYPED = false;
			RIGHT_TYPED = true;
		}
	}


	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

	public void update(Graphics g) {
		Graphics offScreenGraphics;
		if (offScreenImage == null) {
			//返回创建一幅用于双缓冲的、可在屏幕外绘制的图像。
			//如果组件是不可显示的，则返回值可能为 null。
			offScreenImage = createImage(545, 482);
		}
		offScreenGraphics = offScreenImage.getGraphics();
		offScreenGraphics.setColor(Color.white);
		offScreenGraphics.fillRect(0, 0, 545, 482);
		paint(offScreenGraphics);
		g.drawImage(offScreenImage, 0, 0, this);
	}

	/**
	 * 布置豆子
	 */
	private static Gold[] golds(){
		Gold[] Golds = new Gold[157];
		for(int i = 0; i < 8; i++)
			Golds[i] = new Gold(42 + (i - 0)*21, 42, 1);
		for(int i = 8; i < 16; i++)
			Golds[i] = new Gold(231 + (i - 8)*21, 42, 1);
		Golds[16] = new Gold(42, 63, 2);
		Golds[17] = new Gold(378, 63, 2);
		Golds[18] = new Gold(189, 63, 1);
		Golds[19] = new Gold(189, 84, 1);
		Golds[20] = new Gold(231, 63, 1);
		Golds[21] = new Gold(231, 84, 1);
		for(int i = 22; i < 26; i++)
			Golds[i] = new Gold(42, 84 + (i - 22)*21, 1);
		for(int i = 26; i < 30; i++)
			Golds[i] = new Gold(378, 84 + (i - 26)*21, 1);
		Golds[30] = new Gold(63, 105, 1);
		Golds[31] = new Gold(84, 105, 1);
		Golds[32] = new Gold(336, 105, 1);
		Golds[33] = new Gold(357, 105, 1);
		for(int i = 34; i < 43; i++)
			Golds[i] = new Gold(126 + (i - 34)*21, 105, 1);
		for(int i = 43; i < 60; i++)
			Golds[i] = new Gold(105, 63 + (i - 43)*21, 1);
		for(int i = 60; i < 77; i++)
			Golds[i] = new Gold(315, 63 + (i - 60)*21, 1);
		Golds[77] = new Gold(63, 147, 1);
		Golds[78] = new Gold(84, 147, 1);
		Golds[79] = new Gold(336, 147, 1);
		Golds[80] = new Gold(357, 147, 1);
		Golds[81] = new Gold(147, 126, 1);
		Golds[82] = new Gold(273, 126, 1);
		for(int i = 83; i < 86; i++)
			Golds[i] = new Gold(147 + (i - 83)*21, 147, 1);
		for(int i = 86; i < 89; i++)
			Golds[i] = new Gold(231 + (i - 86)*21, 147, 1);
		Golds[89] = new Gold(42, 315, 1);
		Golds[90] = new Gold(42, 336, 1);
		Golds[91] = new Gold(42, 357, 2);
		for(int i = 92; i < 95; i++)
			Golds[i] = new Gold(63, 357 + (i - 92)*21, 1);
		for(int i = 95; i < 98; i++)
			Golds[i] = new Gold(42, 399 + (i - 95)*21, 1);
		Golds[98] = new Gold(63, 315, 1);
		Golds[99] = new Gold(84, 315, 1);
		for(int i = 100; i < 104; i++)
			Golds[i] = new Gold(126 + (i - 100)*21, 315, 1);
		for(int i = 104; i < 108; i++)
			Golds[i] = new Gold(231 +(i - 104)*21, 315, 1);
		Golds[108] = new Gold(189, 336, 1);
		Golds[109] = new Gold(231, 336, 1);
		for(int i = 110; i < 119; i++)
			Golds[i] = new Gold(126 + (i - 110)*21, 357, 1);
		for(int i = 119; i < 122; i++)
			Golds[i] = new Gold(336 + (i - 119)*21, 315, 1);
		Golds[122] = new Gold(378, 336, 1);
		Golds[123] = new Gold(378, 357, 2);
		for(int i = 124; i < 127; i++)
			Golds[i] = new Gold(357, 357 + (i - 124)*21, 1);
		for(int i = 127; i < 130; i++)
			Golds[i] = new Gold(378, 399 + (i - 127)*21, 1);
		Golds[130] = new Gold(84, 399, 1);
		Golds[131] = new Gold(336, 399, 1);
		Golds[132] = new Gold(147, 378, 1);
		Golds[133] = new Gold(189, 420, 1);
		Golds[134] = new Gold(273, 378, 1);
		Golds[135] = new Gold(231, 420, 1);
		for(int i = 136; i < 139; i++)
			Golds[i] = new Gold(147 + (i - 136)*21, 399, 1);
		for(int i = 139; i < 142; i++)
			Golds[i] = new Gold(231 + (i - 139)*21, 399, 1);
		for(int i = 142; i < 157; i++)
			Golds[i] = new Gold(63 + (i - 142)*21, 441, 1);
		return Golds;
	}

	private static Wall[] walls(){
		Wall[] Walls = new Wall[216];
		Walls[0] = new Wall(21, 21, 29);
		Walls[1] = new Wall(399, 21, 30);
		Walls[2] = new Wall(21, 462, 28);
		Walls[3] = new Wall(399, 462, 31);

		for(int i = 4; i < 12; i++)
			Walls[i] = new Wall(42 + (i - 4)*21, 21, 27);
		Walls[12] = new Wall(210, 21, 22);
		for(int i = 13; i < 21; i++)
			Walls[i] = new Wall(231 + (i - 13)*21, 21, 27);

		Walls[21] = new Wall(210, 42, 2);
		Walls[22] = new Wall(210, 63, 2);
		Walls[23] = new Wall(210, 84, 5);

		for(int i = 24; i < 30; i++)
			Walls[i] = new Wall(21, 42 + 21*(i - 24), 26);

		Walls[30] = new Wall(63, 63, 32);
		Walls[31] = new Wall(84, 63, 33);
		Walls[32] = new Wall(63, 84, 35);
		Walls[33] = new Wall(84, 84, 34);

		Walls[34] = new Wall(63, 126, 6);
		Walls[35] = new Wall(84, 126, 4);

		Walls[36] = new Wall(126, 63, 32);
		Walls[37] = new Wall(126, 84, 35);
		Walls[38] = new Wall(147, 63, 19);
		Walls[39] = new Wall(147, 84, 17);
		Walls[40] = new Wall(168, 63, 33);
		Walls[41] = new Wall(168, 84, 34);

		Walls[42] = new Wall(252, 63, 32);
		Walls[43] = new Wall(252, 84, 35);
		Walls[44] = new Wall(273, 63, 19);
		Walls[45] = new Wall(273, 84, 17);
		Walls[46] = new Wall(294, 63, 33);
		Walls[47] = new Wall(294, 84, 34);

		Walls[48] = new Wall(336, 63, 32);
		Walls[49] = new Wall(357, 63, 33);
		Walls[50] = new Wall(336, 84, 35);
		Walls[51] = new Wall(357, 84, 34);

		Walls[52] = new Wall(336, 126, 6);
		Walls[53] = new Wall(357, 126, 4);

		for(int i = 54; i < 60; i++)
			Walls[i] = new Wall(399, 42 + (i - 54)*21, 24);

		Walls[60] = new Wall(21, 168, 28);
		Walls[61] = new Wall(42, 168, 25);
		Walls[62] = new Wall(63, 168, 25);
		Walls[63] = new Wall(84, 168, 37);
		Walls[64] = new Wall(84, 189, 26);
		Walls[65] = new Wall(84, 210, 38);
		for(int i = 66; i < 69; i++)
			Walls[i] = new Wall(63 - (i - 66)*21, 210, 27);

		for(int i = 69; i < 72; i++)
			Walls[i] = new Wall(21 + (i - 69)*21, 252, 25);
		Walls[72] = new Wall(84, 252, 37);
		Walls[73] = new Wall(84, 273, 26);
		Walls[74] = new Wall(84, 294, 38);
		Walls[75] = new Wall(63, 294, 27);
		Walls[76] = new Wall(42, 294, 27);

		Walls[77] = new Wall(126, 126, 3);
		Walls[78] = new Wall(126, 147, 2);
		Walls[79] = new Wall(126, 168, 13);
		Walls[80] = new Wall(147, 168, 1);
		Walls[81] = new Wall(168, 168, 4);
		Walls[82] = new Wall(126, 189, 2);
		Walls[83] = new Wall(126, 210, 5);

		Walls[84] = new Wall(168, 126, 6);
		Walls[85] = new Wall(189, 126, 1);
		Walls[86] = new Wall(210, 126, 14);
		Walls[87] = new Wall(231, 126, 1);
		Walls[88] = new Wall(252, 126, 4);
		Walls[89] = new Wall(210, 147, 2);
		Walls[90] = new Wall(210, 168, 5);

		Walls[91] = new Wall(294, 126, 3);
		Walls[92] = new Wall(294, 147, 2);
		Walls[93] = new Wall(294, 168, 15);
		Walls[94] = new Wall(273, 168, 1);
		Walls[95] = new Wall(252, 168, 6);
		Walls[96] = new Wall(294, 189, 2);
		Walls[97] = new Wall(294, 210, 5);

		Walls[98] = new Wall(399, 168, 31);
		Walls[99] = new Wall(378, 168, 25);
		Walls[100] = new Wall(357, 168, 25);
		Walls[101] = new Wall(336, 168, 36);
		Walls[102] = new Wall(336, 189, 24);
		Walls[103] = new Wall(336, 210, 39);
		for(int i = 104; i < 107; i++)
			Walls[i] = new Wall(357 + (i - 104)*21, 210, 27);

		for(int i = 107; i < 110; i++)
			Walls[i] = new Wall(357 + (i -  107)*21, 252, 25);
		Walls[110] = new Wall(336, 252, 36);
		Walls[111] = new Wall(336, 273, 24);
		Walls[112] = new Wall(336, 294, 39);
		Walls[113] = new Wall(357, 294, 27);
		Walls[114] = new Wall(378, 294, 27);

		Walls[115] = new Wall(168, 210, 40);
		Walls[116] = new Wall(168, 231, 44);
		Walls[117] = new Wall(168, 252, 43);
		for(int i = 118; i < 121; i++)
			Walls[i] = new Wall(189 + (i - 118)*21, 252, 47);
		Walls[121] = new Wall(252, 252, 42);
		Walls[122] = new Wall(252, 231, 46);
		Walls[123] = new Wall(252, 210, 41);

		Walls[124] = new Wall(126, 252, 3);
		Walls[125] = new Wall(126, 273, 2);
		Walls[126] = new Wall(126, 294, 5);
		Walls[127] = new Wall(294, 252, 3);
		Walls[128] = new Wall(294, 273, 2);
		Walls[129] = new Wall(294, 294, 5);

		Walls[130] = new Wall(21, 294, 29);
		for(int i = 131; i < 134; i++)
			Walls[i] = new Wall(21, 315 + (i - 131)*21, 26);
		Walls[134] = new Wall(21, 378, 21);
		for(int i = 135; i < 138; i++)
			Walls[i] = new Wall(21, 399 + (i - 135)*21, 26);

		Walls[138] = new Wall(399, 294, 30);
		for(int i = 139; i < 142; i++)
			Walls[i] = new Wall(399, 315 + (i - 139)*21, 24);
		Walls[142] = new Wall(399, 378, 23);
		for(int i = 143; i < 146; i++)
			Walls[i] = new Wall(399, 399 + (i - 143)*21, 24);

		for(int i = 146; i < 163; i++)
			Walls[i] = new Wall(42 + (i - 146)*21, 462, 25);

		Walls[163] = new Wall(42, 378, 4);
		Walls[164] = new Wall(378, 378, 6);

		Walls[165] = new Wall(168, 294, 6);
		Walls[166] = new Wall(189, 294, 1);
		Walls[167] = new Wall(210, 294, 14);
		Walls[168] = new Wall(231, 294, 1);
		Walls[169] = new Wall(252, 294, 4);
		Walls[170] = new Wall(210, 315, 2);
		Walls[171] = new Wall(210, 336, 5);

		Walls[172] = new Wall(168, 378, 6);
		Walls[173] = new Wall(189, 378, 1);
		Walls[174] = new Wall(210, 378, 14);
		Walls[175] = new Wall(231, 378, 1);
		Walls[176] = new Wall(252, 378, 4);
		Walls[177] = new Wall(210, 399, 2);
		Walls[178] = new Wall(210, 420, 5);

		Walls[179] = new Wall(126, 336, 6);
		Walls[180] = new Wall(147, 336, 1);
		Walls[181] = new Wall(168, 336, 4);
		Walls[182] = new Wall(252, 336, 6);
		Walls[183] = new Wall(273, 336, 1);
		Walls[184] = new Wall(294, 336, 4);

		Walls[185] = new Wall(63, 336, 6);
		Walls[186] = new Wall(84, 336, 9);
		Walls[187] = new Wall(84, 357, 2);
		Walls[188] = new Wall(84, 378, 5);

		Walls[189] = new Wall(336, 336, 8);
		Walls[190] = new Wall(336, 357, 2);
		Walls[191] = new Wall(336, 378, 5);
		Walls[192] = new Wall(357, 336, 4);

		Walls[193] = new Wall(63, 420, 6);
		Walls[194] = new Wall(84, 420, 1);
		Walls[195] = new Wall(105, 420, 1);
		Walls[196] = new Wall(126, 420, 12);
		Walls[197] = new Wall(147, 420, 1);
		Walls[198] = new Wall(168, 420, 4);
		Walls[199] = new Wall(126, 399, 2);
		Walls[200] = new Wall(126, 378, 3);

		Walls[201] = new Wall(252, 420, 6);
		Walls[202] = new Wall(273, 420, 1);
		Walls[203] = new Wall(294, 420, 12);
		Walls[204] = new Wall(315, 420, 1);
		Walls[205] = new Wall(336, 420, 1);
		Walls[206] = new Wall(357, 420, 4);
		Walls[207] = new Wall(294, 399, 2);
		Walls[208] = new Wall(294, 378, 3);

		Walls[209] = new Wall(0, 210, 1);
		Walls[210] = new Wall(0, 252, 1);
		Walls[211] = new Wall(420, 210, 1);
		Walls[212] = new Wall(420, 252, 1);
		Walls[213] = new Wall(189, 210, 1);
		Walls[214] = new Wall(210, 210, 1);
		Walls[215] = new Wall(231, 210, 1);
		return Walls;
	}
}