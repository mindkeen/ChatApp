package Chat;

import java.io.File;

import javax.swing.JFileChooser;

public class FileTransfer {
public static void main(String[] args) {
	String   file1 = "D/ABC";
	//���öԻ����ʱ�ĸ�Ŀ¼
	JFileChooser fileChooser = new JFileChooser(file1);
	//��ʾ��ѡ���ļ��ĶԻ���
	fileChooser.showOpenDialog(null);
	//����û�ѡ����ļ�
	File file = fileChooser.getSelectedFile();
	//�ж��ļ��Ƿ�ѡ����û��ѡ��ϵͳ�˳�
	if (file  == null) {
		System.out.println("ϵͳ�˳�");
		System.exit(0);
	}
}
}
