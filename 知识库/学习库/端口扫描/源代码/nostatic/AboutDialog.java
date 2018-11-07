import javax.swing.*;
import java.awt.*;


/*
 **�����ڡ�����
 */
public class AboutDialog extends JDialog
{
	JPanel jMainPane = new JPanel();

	JTabbedPane jTabbedPane = new JTabbedPane();
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();

	private JTextArea jt1 = new JTextArea(6,6); 
	private JTextArea jt2 = new JTextArea(6,6); 

	/*
	 **���캯��
	 */
	public AboutDialog()
	{
		setTitle("�˿�ɨ��");
		setSize(300,200);
		setResizable(false);
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE); 
		
		Container c = this.getContentPane();
		
		jt1.setSize(260,200);
		jt2.setSize(260,200);
		
		jt1.setEditable(false);
		jt2.setEditable(false);
		
		jt1.setLineWrap(true); 
		jt2.setLineWrap(true); 

		jt1.setText("��ͬһ��Ϣ��Ŀ����������������ɨ��Ķ˿ڽ��з��ͣ�Ȼ����ݷ��ض˿�״̬������Ŀ�������Ķ˿��Ƿ�򿪡��Ƿ���á�");
		jt2.setText("1��ѡ��ɨ�跽ʽ\n"+"2���������ʼɨ�衱\n"+"3�����������ɨ����������ɨ�����ı���");

		jt1.setFont(new Font("����_GB2312", java.awt.Font.BOLD, 13));
		jt1.setForeground(Color.blue);

		jt2.setFont(new Font("����_GB2312", java.awt.Font.BOLD, 13));
		jt2.setForeground(Color.black);
		
		jPanel1.add(jt1);
		jPanel2.add(jt2);
		
		jTabbedPane.setSize(300,200);
		jTabbedPane.addTab("ɨ��ԭ��", null, jPanel1, null);
		jTabbedPane.addTab("ʹ��˵��", null, jPanel2, null);
		
		jMainPane.add(jTabbedPane);
		c.add(jMainPane);

		pack();
		this.setVisible(true);
	}
} 
