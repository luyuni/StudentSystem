package cn.niyulu.view;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.niyulu.entity.Teacher;
import cn.niyulu.factory.ServiceFactory;
import cn.niyulu.util.ValidateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TeacherRegView extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public TeacherRegView() {
		setTitle("教师注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.CYAN );
		
		JLabel idLabel = new JLabel("工  号:");
		idLabel.setBounds(70, 80, 72, 20);
		contentPane.add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(156, 78, 168, 24);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("姓  名:");
		nameLabel.setBounds(70, 150, 72, 18);
		contentPane.add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(156, 147, 168, 24);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("密  码:");
		passwordLabel.setBounds(70, 220, 72, 18);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 217, 168, 24);
		contentPane.add(passwordField);
		
		JButton commitButton = new JButton("提  交");
		commitButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(ValidateUtils.validateEmpty(idTextField.getText())||ValidateUtils.validateEmpty(nameTextField.getText())||ValidateUtils.validateEmpty(passwordField.getText())){
					JOptionPane.showMessageDialog(null, "请把信息填完噢!");
				}else {
					if(!"222".equals(idTextField.getText().substring(0, 3))) {
						JOptionPane.showMessageDialog(null, "工号以222开头!");
					}else {
						Teacher teacher = new Teacher();
						teacher.setId(Integer.parseInt(idTextField.getText()));
						teacher.setName(nameTextField.getText());
						teacher.setPassword(passwordField.getText());
						if(ServiceFactory.getTeacherServiceInstance().teacherReg(teacher)) {
							JOptionPane.showMessageDialog(null, "注册成功!");
							setVisible(false);
							new LoginView();
						}else {
							JOptionPane.showMessageDialog(null, "注册失败!再试一次?或联系管理员!");
						}
					}
					
				}
				
				
			}
		});
		commitButton.setBounds(115, 287, 113, 27);
		contentPane.add(commitButton);
		
		JButton button = new JButton("返回");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new SelectStatus();
			}
		});
		button.setBounds(452, 0, 80, 27);
		contentPane.add(button);
		
		setVisible(true);
	}
}
