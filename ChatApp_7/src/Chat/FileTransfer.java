package Chat;

import java.io.File;

import javax.swing.JFileChooser;

public class FileTransfer {
public static void main(String[] args) {
	String   file1 = "D/ABC";
	//设置对话框打开时的根目录
	JFileChooser fileChooser = new JFileChooser(file1);
	//显示出选择文件的对话框
	fileChooser.showOpenDialog(null);
	//获得用户选择的文件
	File file = fileChooser.getSelectedFile();
	//判断文件是否选择，若没有选择系统退出
	if (file  == null) {
		System.out.println("系统退出");
		System.exit(0);
	}
}
}
