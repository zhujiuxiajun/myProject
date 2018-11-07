import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TCPThread extends Thread{
	
	public static InetAddress hostAddress;
	
	//��С�Ķ˿ں�
	public static int MIN_port;
	//���Ķ˿ں�
	public static int MAX_port;
	
	//�߳�����
	private int threadnum;    
	
	//��ѯ��ʽ��0Ϊip��1Ϊ������
	public static int type;

	//ip��ַǰ3λ
	public static int ip1;
	//ip��ַ4~6λ
	public static int ip2;
	//ip��ַ7~9λ
	public static int ip3;
	//��ʼip��ַ�����4λ
	public static int ipstart;
	//����ip��ַ�����4λ
	public static int ipend;
	//������ip��ַ
	public static String ipAll;

	//ɨ����������ƻ�ip
	String hostname = "";
	//�˿ڵ����
	String porttype = "0";
		
	/*
	 *���캯��
	 */
	public TCPThread(String name,int threadnum){
		super(name);        
		this.threadnum = threadnum;    
	}    
	
	/*
	 *���к���
	 */
	public void run() {
		
		//ip��ַ
		int h = 0;
		//�˿ں�
		int i = 0;
		Socket theTCPsocket;

		//����ip��ַ����ɨ��
		if(type == 0){
			
			//ip��ַѭ��ɨ��
			for(h = ipstart; h <=ipend; h++){
				
				//���������ip��ַ
				ipAll = "" + ip1 + "." + ip2 + "." + ip3 + "." + h;
				hostname = ipAll;
				
				try{
					//�ڸ����������������ȷ�������� IP ��ַ
					hostAddress=InetAddress.getByName(ipAll);
				}
				catch(UnknownHostException e){
				}
				
				//��ͬ�Ķ˿�ѭ��ɨ��
				for (i = MIN_port+threadnum; i < MAX_port + Integer.parseInt(ThreadScan.maxThread.getText()); i += Integer.parseInt(ThreadScan.maxThread.getText())){

					try{
						theTCPsocket=new Socket(hostAddress,i);
						theTCPsocket.close();
						ThreadScan.Result.append(hostname+":"+i);
						
						//�ж϶˿ڵ����
						switch(i){
							case 21:
								porttype = "(FTP)";
								break;
							case 23:
								porttype = "(TELNET)";
								break;
							case 25:
								porttype = "(SMTP)";
								break; 
							case 80:
								porttype = "(HTTP)";	
								break;
							case 110:
								porttype = "(POP)";
								break;
							case 139:
								porttype = "(netBIOS)";
								break;
							case 1433:
								porttype = "(SQL Server)";
								break;
							case 3389:
								porttype = "(Terminal Service)";
								break;
							case 443:
								porttype = "(HTTPS)";
								break;
							case 1521:
								porttype = "(Oracle)";
								break;
						}
						
						//�˿�û���ض����
						if(porttype.equals("0")){
							ThreadScan.Result.append("\n");
						}
						else{
							ThreadScan.Result.append(":"+porttype+"\n");
						}
					}
					catch (IOException e){
					}
				}
			}
			
			//ɨ����ɺ���ʾɨ����ɣ�������ȷ������ť����Ϊ����
			if (i==MAX_port+Integer.parseInt(ThreadScan.maxThread.getText())){
				ThreadScan.Result.append("\n"+"ɨ�����...");
				
				//��"ȷ��"��ť���ó�Ϊ����
				if(!ThreadScan.Submit.isEnabled()){
					ThreadScan.Submit.setEnabled(true);
				}
			}
		}
		
		//�������������ж˿�ɨ��
		if(type == 1){

			for (i = MIN_port+threadnum; i < MAX_port+Integer.parseInt(ThreadScan.maxThread.getText()); i += Integer.parseInt(ThreadScan.maxThread.getText())){

				try{
					theTCPsocket=new Socket(hostAddress,i);
					theTCPsocket.close();
					ThreadScan.Result.append(" "+i);
					switch(i){
							case 21:
								porttype = "(FTP)";
								break;
							case 23:
								porttype = "(TELNET)";
								break;
							case 25:
								porttype = "(SMTP)";
								break; 
							case 80:
								porttype = "(HTTP)";	
								break;
							case 110:
								porttype = "(POP)";
								break;
							case 139:
								porttype = "(netBIOS)";
								break;
							case 1433:
								porttype = "(SQL Server)";
								break;
							case 3389:
								porttype = "(Terminal Service)";
								break;
							case 443:
								porttype = "(HTTPS)";
								break;
							case 1521:
								porttype = "(Oracle)";
								break;
						}
						
						//�˿�û���ض����
						if(porttype.equals("0")){
							ThreadScan.Result.append("\n");
						}
						else{
							ThreadScan.Result.append(":"+porttype+"\n");
						}
				}
				catch (IOException e){
				}
			}
			
			//ɨ����ɺ���ʾɨ����ɣ�������ȷ������ť����Ϊ����
			if (i==MAX_port+Integer.parseInt(ThreadScan.maxThread.getText())){
				ThreadScan.Result.append("\n"+"ɨ�����...");
				
				//����ȷ������ť���ó�Ϊ����
				if(!ThreadScan.Submit.isEnabled()){
					ThreadScan.Submit.setEnabled(true);
				}
			}
		}
	}
}