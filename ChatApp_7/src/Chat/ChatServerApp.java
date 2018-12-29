package Chat;

//�����ҷ�����

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChatServerApp {
	private ServerSocket ss;
	private Set<Socket> allSockets;// ʵ��Ⱥ������ ����set�������ڴ������е�socketʵ��������
	public ChatServerApp() {
		try {
			ss = new ServerSocket(6666);
			allSockets = new HashSet<Socket>();// �Լ��Ͻ��г�ʼ��
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void Judgection() throws IOException {
		System.out.println("����������");
		while (true) {
			try {
				Socket s = ss.accept();
				allSockets.add(s);// ��ȡÿ���ͻ��˵�����
				System.out.println("�ͻ���������");
				new ServerThread(s).start();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	// ����ĳ���ͻ���ͨ��
	class ServerThread extends Thread {
		private Socket s;
		private BufferedReader br;
		// �����Ա���� ��Socket ���г�ʼ��

		public ServerThread(Socket s) {
			this.s = s;
			try {//��ȡ�ͻ�������������͵���Ϣ
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		public void run() {
			while (true) {

				try {
					String str = br.readLine();
					
					
					//
					if (str.indexOf("%EXIT%") == 0) {
						// �Ӽ�����ɾ�� scoket
						allSockets.remove(s);

						s.close();
						// split ����ȡ������Ϣ���зָ�
						sendMessageToAllClient(str.split(":")[1] + "�˳���������");
						// ����while ѭ��
						break;
					}

					sendMessageToAllClient(str);

				} catch (IOException e) {

					
				}
			}

		}

	}

	// ʵ��Ⱥ������
	private void sendMessageToAllClient(String mesg) {
		Date d = new Date();
		// �������е�socket���� 
		for (Socket s : allSockets) {
			PrintWriter pw;
			try {
				pw = new PrintWriter(s.getOutputStream());
				pw.println(mesg + "\t[" + d + "]");
				pw.flush();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws IOException {
		ChatServerApp app = new ChatServerApp();
		
			app.Judgection();
	
		

	}

}
