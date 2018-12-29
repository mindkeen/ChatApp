package Chat;

//聊天室窗体

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
	private JFrame jf;// 窗体
	private Container c;
	private JTextArea jta;// 聊天内容显示文本域
	private JTextField jtf;// 自己输入内容的文本框
	private JButton jb;// 发送按钮
	
	private JLabel label;// 聊天窗体中的用户名
	private BufferedReader br;
	private PrintWriter pw;// 向服务器写入的输出流
	private Socket s;// 实现客户端与服务端的连接
	private Set<Socket> allSockets;
	private JTextField ne;
	private String name;

	public ChatJFram(String name) {
		
		this.name=name;
		do {
			try {
				s = new Socket("127.0.0.1", 6666);// 创建连接 建立连接
				pw = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				allSockets = new HashSet<Socket>();// 对集合进行初始化
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (s == null);
		jf = new JFrame("聊天室客户端");
		jta = new JTextArea(20, 40);
		// 设置聊天内容字体
		jta.setFont(new Font("宋体", Font.BOLD, 12));
		// 文本域不可编辑
		jta.setEditable(false);
		jtf = new JTextField(28);
		jtf.addActionListener(new GlaoTf());
		jb = new JButton("发送");
		jb.addMouseListener(new fasong());
		
		label = new JLabel(this.name+  ":");
		
		new MessageThread().start();
		init();
	}
	public void init() {

		// 可滚动面板
		JScrollPane jsp = new JScrollPane(jta);
		// 布局方式
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
		// 设置窗体大小
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	// 为聊天室发送按钮添加一个监听器
	class fasong implements MouseListener {

		private String mesg;
		@Override
		public void mouseClicked(MouseEvent e) {
			Writeable();
		}
		public void Writeable() {

			if (jtf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(jf, "不能发送空消息");
				return;
			}
			pw.println(name+":" + jtf.getText());//
			pw.flush();
			jtf.setText("");// 清空输入框
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
	// 给聊天输入框加一个事件监听
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
					// 显示聊天内容文本域
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
		
		// 遍利所有的socket对象
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