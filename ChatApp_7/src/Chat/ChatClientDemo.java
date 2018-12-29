package Chat;

//聊天室 客户端   登陆窗口

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChatClientDemo extends JFrame {
	private JFrame jf;// 窗体
	private Container c;
	private String name;// 用户名
	private JLabel label;// 聊天窗体中的用户名
	private JLabel jl1;// 创建"ip地址:"标签
	private JTextField jtf1;// 创建用户输入窗
	private JTextField jpf1;// 创建端口输入窗
	private JLabel jl2;// 创建"密码:"标签
	private JButton jb1;
	private JButton jb2;
	private JTextArea jta;// 聊天内容显示文本域
	private JTextField jtf;// 自己输入内容的文本框
	private JButton jb;// 发送按钮
	private Socket s;// 实现客户端与服务端的连接
	private PrintWriter pw;// 向服务器写入的输出流
	private BufferedReader br;
	private String name1;// 登陆名称
	private JTextField jname; 
	private JTextField jname1; 
	

	public ChatClientDemo() {

		// 设置标题x
		setTitle("聊天室");
		setAlwaysOnTop(true);
		// 绝对布局
		setLayout(null);
		// 定义一个容器
		Container c = getContentPane();
		// 创建"ip:"标签
		JLabel jl1 = new JLabel("ip：");
		// 创建文本框
		final JTextField jtf1 = new JTextField();
		// 创建"端口号:"标签
		JLabel jl2 = new JLabel("端口号: ");
		// 创建密码框
		final JPasswordField jpf1 = new JPasswordField();
		// 设置密码字符为*
		JLabel jname = new JLabel("名称: ");
		jname1 = new JTextField();
		

		// 创建"提交"按钮
		JButton jb1 = new JButton("登陆");
		jb1.addMouseListener(new mouse());
		// 创建"清空"按钮
		JButton jb2 = new JButton("清空");

		do {

			try {
				s = new Socket("127.0.0.1", 6666);// 创建连接 建立连接
				pw = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (s == null);
		c.add(jl1);
		c.add(jtf1);
		c.add(jl2);
		c.add(jpf1);
		c.add(jb1);
		c.add(jb2);
		c.add(jname);
		c.add(jname1);

		// 设置各组件的位置以及大小
		jl1.setBounds(10, 20, 90, 30);
		jtf1.setBounds(60, 20, 210, 30);
		jl2.setBounds(10, 60, 90, 30);
		jpf1.setBounds(60, 60, 210, 30);
		jname.setBounds(10,100,90,30);
		jname1.setBounds(60,100,210,30);
		jb1.setBounds(80, 140, 70, 50);
		jb2.setBounds(150, 140, 70, 50);
		// 设置窗体大小、关闭方式、不可拉伸
		setLocationRelativeTo(null);
		setSize(300, 220);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		

	}

	public void showMe() {
		jf.pack();
		// 可建
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	// 注册监听器
	class mouse implements MouseListener {
		

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			

			ChatJFram jFram = new ChatJFram(jname1.getText());
			dispose();
			

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}

	class ChongZhi implements MouseListener {


		@Override
		public void mouseClicked(MouseEvent e) {

			jtf1.setText("");
			jpf1.setText("");
			jname1.setText("");
		

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	

	public static void main(String[] args) {
		ChatClientDemo d = new ChatClientDemo();
		
	}

}
