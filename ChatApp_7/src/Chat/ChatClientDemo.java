package Chat;

//������ �ͻ���   ��½����

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
	private JFrame jf;// ����
	private Container c;
	private String name;// �û���
	private JLabel label;// ���촰���е��û���
	private JLabel jl1;// ����"ip��ַ:"��ǩ
	private JTextField jtf1;// �����û����봰
	private JTextField jpf1;// �����˿����봰
	private JLabel jl2;// ����"����:"��ǩ
	private JButton jb1;
	private JButton jb2;
	private JTextArea jta;// ����������ʾ�ı���
	private JTextField jtf;// �Լ��������ݵ��ı���
	private JButton jb;// ���Ͱ�ť
	private Socket s;// ʵ�ֿͻ��������˵�����
	private PrintWriter pw;// �������д��������
	private BufferedReader br;
	private String name1;// ��½����
	private JTextField jname; 
	private JTextField jname1; 
	

	public ChatClientDemo() {

		// ���ñ���x
		setTitle("������");
		setAlwaysOnTop(true);
		// ���Բ���
		setLayout(null);
		// ����һ������
		Container c = getContentPane();
		// ����"ip:"��ǩ
		JLabel jl1 = new JLabel("ip��");
		// �����ı���
		final JTextField jtf1 = new JTextField();
		// ����"�˿ں�:"��ǩ
		JLabel jl2 = new JLabel("�˿ں�: ");
		// ���������
		final JPasswordField jpf1 = new JPasswordField();
		// ���������ַ�Ϊ*
		JLabel jname = new JLabel("����: ");
		jname1 = new JTextField();
		

		// ����"�ύ"��ť
		JButton jb1 = new JButton("��½");
		jb1.addMouseListener(new mouse());
		// ����"���"��ť
		JButton jb2 = new JButton("���");

		do {

			try {
				s = new Socket("127.0.0.1", 6666);// �������� ��������
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

		// ���ø������λ���Լ���С
		jl1.setBounds(10, 20, 90, 30);
		jtf1.setBounds(60, 20, 210, 30);
		jl2.setBounds(10, 60, 90, 30);
		jpf1.setBounds(60, 60, 210, 30);
		jname.setBounds(10,100,90,30);
		jname1.setBounds(60,100,210,30);
		jb1.setBounds(80, 140, 70, 50);
		jb2.setBounds(150, 140, 70, 50);
		// ���ô����С���رշ�ʽ����������
		setLocationRelativeTo(null);
		setSize(300, 220);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		

	}

	public void showMe() {
		jf.pack();
		// �ɽ�
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	// ע�������
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
