package cn.niyulu.view;


import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.niyulu.entity.Student;
import cn.niyulu.entity.Teacher;
import cn.niyulu.factory.ServiceFactory;
import cn.niyulu.util.ValidateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setTitle("学生管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 350);	
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.CYAN);
		
		JLabel idLabel = new JLabel("账  号：");
		idLabel.setBounds(114, 80, 72, 18);
		contentPane.add(idLabel);
		
		idTextField = new JTextField("20161111");
		idTextField.setBounds(200, 77, 175, 24);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("密  码：");
		passwordLabel.setBounds(114, 137, 72, 18);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField("123456");
		passwordField.setBounds(200, 134, 175, 24);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("登  录");
		loginButton.setBounds(114, 191, 113, 27);
		loginButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if(ValidateUtils.validateEmpty(idTextField.getText())||ValidateUtils.validateEmpty(passwordField.getText())) {
					JOptionPane .showMessageDialog(null, "用户名或密码不能为空!");
				}else {
					
					if("222".equals(idTextField.getText().substring(0, 3))) {
						Teacher teacher = new Teacher();
						teacher.setId(Integer.parseInt(idTextField.getText()));
						teacher.setPassword(passwordField.getText());
						if(ServiceFactory.getTeacherServiceInstance().teacherLogin(teacher)) {
							JOptionPane.showMessageDialog(null, teacher.getName()+"欢迎你!");
							setVisible(false);
							new TeacherMainView(teacher);
						}else {
							JOptionPane.showMessageDialog(null, "账号或密码错误");
						}
					}else {
						Student student = new Student();
						student.setId(Integer.parseInt(idTextField.getText()));
						student.setPassword(passwordField.getText());
						if(ServiceFactory.getStudentServiceInstance().studentLogin(student)) {
							JOptionPane.showMessageDialog(null, student.getName()+"欢迎你!");
							setVisible(false);
							new StudentMianView(student);
						}else {
							JOptionPane.showMessageDialog(null, "账号或密码错误");
						}
					}
					
				}
			}
		});
		contentPane.add(loginButton);
		
		JButton registrationButton = new JButton("注  册");
		registrationButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new SelectStatus();
			}
		});
		registrationButton.setBounds(262, 191, 113, 27);
		contentPane.add(registrationButton);
		setVisible(true);
	}
}
