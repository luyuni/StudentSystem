package cn.niyulu.view;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class SelectStatus extends JFrame {

	private JPanel contentPane;

	public SelectStatus() {
		setTitle("选择你的身份");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.CYAN);
		
		JButton teacherButton = new JButton("我是老师");
		teacherButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new TeacherRegView();
			}
		});
		teacherButton.setBounds(151, 56, 113, 27);
		contentPane.add(teacherButton);
		
		JButton studentButton = new JButton("我是学生");
		studentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new StudentRegView();
			}
		});
		studentButton.setBounds(151, 118, 113, 27);
		contentPane.add(studentButton);
		
		JButton button = new JButton("返回");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new LoginView();
			}
		});
		button.setBounds(359, 0, 80, 27);
		contentPane.add(button);
		
		setVisible(true);
	}

}
