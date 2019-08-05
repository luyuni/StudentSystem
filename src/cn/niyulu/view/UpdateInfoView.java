package cn.niyulu.view;

import java.awt.Color;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.niyulu.entity.Student;
import cn.niyulu.factory.ServiceFactory;
import cn.niyulu.util.DepProSelect;

@SuppressWarnings("serial")
public class UpdateInfoView extends JFrame {
	
	private JPanel contentPane;
	private JLabel nameLabel;
	private JTextField nameTextField;
	@SuppressWarnings("unused")
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
	public UpdateInfoView(Student studentInfo) {
		setTitle("更改个人信息");
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
		

		JLabel labelId = new JLabel(String.valueOf(studentInfo.getId()));
		labelId.setBounds(134, 50, 189, 18);
		contentPane.add(labelId);
		
		nameLabel = new JLabel("姓   名：");
		nameLabel.setBounds(58, 80, 143, 59);
		contentPane.add(nameLabel);
		
		
		nameTextField = new JTextField(studentInfo.getName());
		nameTextField.setBounds(134, 99, 224, 24);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		

		JLabel sexLabel = new JLabel("性   别：");
		sexLabel.setBounds(58, 130, 143, 59);
		contentPane.add(sexLabel);
		
		
		JRadioButton maleRadioButton = new JRadioButton("男");
		maleRadioButton.setBounds(141, 148, 43, 27);
		contentPane.add(maleRadioButton);
		
		JRadioButton femaleRadioButton = new JRadioButton("女");
		femaleRadioButton.setBounds(202, 148, 43, 27);
		contentPane.add(femaleRadioButton);
		if("男".equals(studentInfo.getSex())) {
			maleRadioButton.setSelected(true);
		}else if("女".equals(studentInfo.getSex())) {
			femaleRadioButton.setSelected(true);
		}
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(maleRadioButton);
		bg.add(femaleRadioButton);
		
		JLabel departmentLabel = new JLabel("院   系：");
		departmentLabel.setBounds(58, 180, 143, 59);
		contentPane.add(departmentLabel);

		departmentComboBox = new JComboBox();
		departmentComboBox.setModel(new DefaultComboBoxModel(DepProSelect.getDepartment()));
		departmentComboBox.setSelectedItem(studentInfo.getDepartment());
		departmentComboBox.setBounds(134, 197, 111, 24);
		contentPane.add(departmentComboBox);
		departmentComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				itemChange();
				
			}
		});
		
		JLabel professionLabel = new JLabel("专   业：");
		professionLabel.setBounds(58, 230, 143, 59);
		contentPane.add(professionLabel);
		
		professionComboBox = new JComboBox();
		professionComboBox.setModel(new DefaultComboBoxModel(DepProSelect.getProfession((String) departmentComboBox.getSelectedItem())));
		professionComboBox.setSelectedItem(studentInfo.getProfession());
		professionComboBox.setBounds(134, 247, 111, 24);
		contentPane.add(professionComboBox);
		
		
		commitButton = new JButton("确定更改");
		commitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Student student = new Student();
				student.setId(studentInfo.getId());
				student.setName(nameTextField.getText());
				if(femaleRadioButton.isSelected()) {
					student.setSex("女");
				}else {
					student.setSex("男");
				}		
				student.setDepartment((String)departmentComboBox.getSelectedItem());
				student.setProfession((String)professionComboBox.getSelectedItem());
				student.setPassword(studentInfo.getPassword());
				if(ServiceFactory.getStudentServiceInstance().updateInfo(student)) {
					JOptionPane.showMessageDialog(null, "修改成功");
					setVisible(false);
					new StudentMianView(student);
				} else {
					JOptionPane.showMessageDialog(null, "修改失败!再试一次?或联系作者!");
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
				new StudentMianView(studentInfo);
			}
		});
		button.setBounds(453, 0, 80, 27);
		contentPane.add(button);
		
		setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void itemChange() {
        String selectDepartment = (String) departmentComboBox.getSelectedItem();
        professionComboBox.removeAllItems(); // 清空专业列表
        String[] arrCity = DepProSelect.getProfession(selectDepartment); // 获取专业
        professionComboBox.setModel(new DefaultComboBoxModel(arrCity)); // 重新添加专业列表的值
	}
}
