package Chat;

//�����Ҵ���

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatJFram extends JFrame {
	private JFrame jf;// ����
	private Container c;
	private JTextArea jta;// ����������ʾ�ı���
	private JTextField jtf;// �Լ��������ݵ��ı���
	private JButton jb;// ���Ͱ�ť
	
	private JLabel label;// ���촰���е��û���
	private BufferedReader br;
	private PrintWriter pw;// �������д��������
	private Socket s;// ʵ�ֿͻ��������˵�����
	private Set<Socket> allSockets;
	private JTextField ne;
	private String name;

	public ChatJFram(String name) {
		
		this.name=name;
		do {
			try {
				s = new Socket("127.0.0.1", 6666);// �������� ��������
				pw = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				allSockets = new HashSet<Socket>();// �Լ��Ͻ��г�ʼ��
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (s == null);
		jf = new JFrame("�����ҿͻ���");
		jta = new JTextArea(20, 40);
		// ����������������
		jta.setFont(new Font("����", Font.BOLD, 12));
		// �ı��򲻿ɱ༭
		jta.setEditable(false);
		jtf = new JTextField(28);
		jtf.addActionListener(new GlaoTf());
		jb = new JButton("����");
		jb.addMouseListener(new fasong());
		
		label = new JLabel(this.name+  ":");
		
		new MessageThread().start();
		init();
	}
	public void init() {

		// �ɹ������
		JScrollPane jsp = new JScrollPane(jta);
		// ���ַ�ʽ
		jf.add(jsp, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(jtf);
		panel.add(jb);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setSize(500, 500);
		jf.add(panel, BorderLayout.SOUTH);
	}
	private void showMe() {
		// ���ô����С
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	// Ϊ�����ҷ��Ͱ�ť���һ��������
	class fasong implements MouseListener {

		private String mesg;
		@Override
		public void mouseClicked(MouseEvent e) {
			Writeable();
		}
		public void Writeable() {

			if (jtf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(jf, "���ܷ��Ϳ���Ϣ");
				return;
			}
			pw.println(name+":" + jtf.getText());//
			pw.flush();
			jtf.setText("");// ��������
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	// ������������һ���¼�����
	class GlaoTf implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			jtf.addActionListener(this);
		}
	}
	class MessageThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					String str = br.readLine();
					// ��ʾ���������ı���
					jta.append(str + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void sendMessageToAllClient(String mesg) {
		Date d = new Date();
		SimpleDateFormat simple=new SimpleDateFormat("HH:mm:ss");
		
		// �������е�socket����
		for (Socket s : allSockets) {
			PrintWriter pw;
			try {
				pw = new PrintWriter(s.getOutputStream());
				System.out.println(mesg + "\t[" + simple.format(d) + "]");
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}