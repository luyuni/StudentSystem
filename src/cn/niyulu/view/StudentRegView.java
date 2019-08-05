package cn.niyulu.view;


import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.niyulu.entity.Student;
import cn.niyulu.factory.ServiceFactory;
import cn.niyulu.util.DepProSelect;
import cn.niyulu.util.ValidateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class StudentRegView extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JPasswordField passwordField;
	private JButton commitButton;
	@SuppressWarnings("rawtypes")
	private JComboBox departmentComboBox;
	@SuppressWarnings("rawtypes")
	private JComboBox professionComboBox;
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StudentRegView() {
		setTitle("学生注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.CYAN);
		
		JLabel idLabel = new JLabel("学   号：");
		idLabel.setBounds(58, 30, 134, 59);
		contentPane.add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(134, 44, 224, 24);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		nameLabel = new JLabel("姓   名：");
		nameLabel.setBounds(58, 80, 143, 59);
		contentPane.add(nameLabel);
		
		
		nameTextField = new JTextField();
		nameTextField.setBounds(134, 99, 224, 24);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JRadioButton maleRadioButton = new JRadioButton("男");
		maleRadioButton.setBounds(141, 148, 43, 27);
		contentPane.add(maleRadioButton);

		JLabel sexLabel = new JLabel("性   别：");
		sexLabel.setBounds(58, 130, 143, 59);
		contentPane.add(sexLabel);
		
		JRadioButton femaleRadioButton = new JRadioButton("女");
		femaleRadioButton.setBounds(202, 148, 43, 27);
		contentPane.add(femaleRadioButton);
		
		JLabel departmentLabel = new JLabel("院   系：");
		departmentLabel.setBounds(58, 180, 143, 59);
		contentPane.add(departmentLabel);
		
		departmentComboBox = new JComboBox();
		departmentComboBox.setModel(new DefaultComboBoxModel(DepProSelect.getDepartment()));
		departmentComboBox.setBounds(134, 197, 111, 24);
		contentPane.add(departmentComboBox);
		departmentComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				itemChange(departmentComboBox,professionComboBox);
				
			}
		});
		
		JLabel professionLabel = new JLabel("专   业：");
		professionLabel.setBounds(58, 230, 143, 59);
		contentPane.add(professionLabel);
		
		professionComboBox = new JComboBox();
		professionComboBox.setModel(new DefaultComboBoxModel(DepProSelect.getProfession((String) departmentComboBox.getSelectedItem())));
		professionComboBox.setBounds(134, 247, 111, 24);
		contentPane.add(professionComboBox);
		
		JLabel passwordLabel = new JLabel("密   码：");
		passwordLabel.setBounds(58, 280, 143, 59);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(134, 297, 224, 24);
		contentPane.add(passwordField);
		
		commitButton = new JButton("提  交");
		commitButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if(ValidateUtils.validateEmpty(idTextField.getText())||
						ValidateUtils.validateEmpty(nameTextField.getText())||
						ValidateUtils.validateEmpty((String)departmentComboBox.getSelectedItem())||
						ValidateUtils.validateEmpty((String)professionComboBox.getSelectedItem())||
						ValidateUtils.validateEmpty(passwordField.getText())
					) {
					JOptionPane.showMessageDialog(null, "请把信息填完噢!");
				}else {
					Student student = new Student();
					student.setId(Integer.parseInt(idTextField.getText()));
					student.setName(nameTextField.getText());
					if(femaleRadioButton.isSelected()) {
						student.setSex("女");
					}else {
						student.setSex("男");
					}		
					student.setDepartment((String)departmentComboBox.getSelectedItem());
					student.setProfession((String)professionComboBox.getSelectedItem());
					student.setPassword(passwordField.getText());
					if(ServiceFactory.getStudentServiceInstance().studentReg(student)) {
						JOptionPane.showMessageDialog(null, "注册成功!");
						setVisible(false);
						new LoginView();
					}else {
						JOptionPane.showMessageDialog(null, "注册失败!再试一次?或联系管理员!");
					}
				}
				
			}
		});
		commitButton.setBounds(210, 340, 113, 27);
		contentPane.add(commitButton);
		
		JButton button = new JButton("返回");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new SelectStatus();
			}
		});
		button.setBounds(453, 0, 80, 27);
		contentPane.add(button);
		
		setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void itemChange(JComboBox departmentComboBox,JComboBox professionComboBox) {
        String selectDepartment = (String) departmentComboBox.getSelectedItem();
        professionComboBox.removeAllItems(); // 清空专业列表
        String[] arrCity = DepProSelect.getProfession(selectDepartment); // 获取专业
        professionComboBox.setModel(new DefaultComboBoxModel(arrCity)); // 重新添加专业列表的值
	}
}
