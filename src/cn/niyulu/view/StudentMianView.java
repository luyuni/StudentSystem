package cn.niyulu.view;

import java.awt.BorderLayout;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;
import cn.niyulu.factory.ServiceFactory;
import cn.niyulu.util.ValidateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class StudentMianView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane jtp;
	private JPanel informationPane;
	private JPanel scorePane;
	private JPanel setPane;
	private JTextField textFieldPassword;
	private JTextField beforeTextField;
	/**
	 * Create the frame.
	 */
	public StudentMianView(Student student) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		setLocationRelativeTo(null);
		jtp = new JTabbedPane();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.CYAN);
		
		informationPane = new JPanel();
		informationPane.setBackground(Color.CYAN);
		
		scorePane = new JPanel();
		scorePane.setBackground(Color.CYAN);
		
		
		jtp.add("我的信息", informationPane);
		informationPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("姓  名:");
		nameLabel.setBounds(0, 25, 72, 18);
		informationPane.add(nameLabel);
		
		JLabel idLabel = new JLabel("学  号:");
		idLabel.setBounds(0, 55, 72, 18);
		informationPane.add(idLabel);
		
		JLabel sexLabel = new JLabel("性  别:");
		sexLabel.setBounds(0, 85, 72, 18);
		informationPane.add(sexLabel);
		
		JLabel departmentLabel = new JLabel("院  系:");
		departmentLabel.setBounds(0, 115, 72, 18);
		informationPane.add(departmentLabel);
		
		JLabel professionLabel = new JLabel("专  业:");
		professionLabel.setBounds(0, 145, 72, 18);
		informationPane.add(professionLabel);
		
		Student studentInfo = ServiceFactory.getStudentServiceInstance().showInfo(student);
		
		JLabel labelName = new JLabel(studentInfo.getName());
		labelName.setBounds(70, 25, 222, 18);
		informationPane.add(labelName);
		
		JLabel labelId = new JLabel(String.valueOf(studentInfo.getId()));
		labelId.setBounds(70, 55, 222, 18);
		informationPane.add(labelId);
		
		JLabel labelSex = new JLabel(studentInfo.getSex());
		labelSex.setBounds(70, 85, 222, 18);
		informationPane.add(labelSex);
		
		JLabel labelDepartment = new JLabel(studentInfo.getDepartment());
		labelDepartment.setBounds(70, 115, 222, 18);
		informationPane.add(labelDepartment);
		
		JLabel labelProfession = new JLabel(studentInfo.getProfession());
		labelProfession.setBounds(70, 145, 222, 18);
		informationPane.add(labelProfession);
		
		
		JButton updateInfoButton = new JButton("更改信息");
		updateInfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new UpdateInfoView(studentInfo);
			}
		});
		updateInfoButton.setBounds(30, 228, 113, 27);
		informationPane.add(updateInfoButton);
		jtp.add("我的成绩", scorePane);
		scorePane.setLayout(null);
		
		JLabel javaLabel = new JLabel("java:");
		javaLabel.setBounds(0, 30, 72, 18);
		scorePane.add(javaLabel);
		
		JLabel databaseLabel = new JLabel("数据库:");
		databaseLabel.setBounds(0, 70, 72, 18);
		scorePane.add(databaseLabel);
		
		JLabel cLabel = new JLabel("C语言:");
		cLabel.setBounds(0, 110, 72, 18);
		scorePane.add(cLabel);
		
		JLabel lineLabel = new JLabel("线性代数:");
		lineLabel.setBounds(0, 150, 72, 18);
		scorePane.add(lineLabel);
		
		Score score = ServiceFactory.getStudentServiceInstance().showScore(student);
		
		JLabel labelJava = new JLabel(String.valueOf(score.getJava()));
		labelJava.setBounds(100, 30, 72, 18);
		scorePane.add(labelJava);
		
		JLabel labelDatabase = new JLabel(String.valueOf(score.getDatabase()));
		labelDatabase.setBounds(100, 70, 72, 18);
		scorePane.add(labelDatabase);
		
		JLabel labelC = new JLabel(String.valueOf(score.getC()));
		labelC.setBounds(100, 110, 72, 18);
		scorePane.add(labelC);
		
		JLabel labelLine = new JLabel(String.valueOf(score.getLinearalgebra()));
		labelLine.setBounds(100, 150, 72, 18);
		scorePane.add(labelLine);
		
		setPane = new JPanel();
		setPane.setBackground(Color.CYAN);
		jtp.add("设置", setPane);
		setPane.setLayout(null);
		
		JButton layoutButton = new JButton("注销");
		layoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		layoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new LoginView();
			}
		});
		layoutButton.setBounds(404, 0, 113, 27);
		setPane.add(layoutButton);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(100, 147, 133, 24);
		setPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		beforeTextField = new JTextField();
		beforeTextField.setBounds(100, 111, 133, 24);
		setPane.add(beforeTextField);
		beforeTextField.setColumns(10);
		
		JButton updatePassButton = new JButton("修改密码");
		updatePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidateUtils.validateEmpty(textFieldPassword.getText())||ValidateUtils.validateEmpty(beforeTextField.getText())) {
					JOptionPane.showMessageDialog(null, "新旧密码都要填上噢!");
				}else {
					if(beforeTextField.getText().equals(student.getPassword())) {
						student.setPassword(textFieldPassword.getText());
						if(ServiceFactory.getTeacherServiceInstance().deleteStuInfo(student)) {
							JOptionPane.showMessageDialog(null, "修改密码成功!");
						}else {
							JOptionPane.showMessageDialog(null, "修改密码失败!再试一次?或联系作者!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "修改失败,原密码输错了!");
					}
				}
				
			}
		});
		updatePassButton.setBounds(120, 200, 113, 27);
		setPane.add(updatePassButton);
		
		
		
		JLabel PasswordLabel = new JLabel("新密码:");
		PasswordLabel.setBounds(14, 150, 72, 18);
		setPane.add(PasswordLabel);
		
		
		
		JLabel beforePasswordLabel = new JLabel("原密码:");
		beforePasswordLabel.setBounds(14, 114, 72, 18);
		setPane.add(beforePasswordLabel);

		
		contentPane.add(jtp,BorderLayout.CENTER);
		
		setVisible(true);
	}
}
