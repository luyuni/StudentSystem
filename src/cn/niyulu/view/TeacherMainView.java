package cn.niyulu.view;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import cn.niyulu.entity.Score;
import cn.niyulu.entity.Student;
import cn.niyulu.entity.Teacher;
import cn.niyulu.factory.ServiceFactory;
import cn.niyulu.util.DepProSelect;
import cn.niyulu.util.ValidateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TeacherMainView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane jtp;
	private JPanel showScorePane;
	private JPanel setPane;
	private JPanel serchStudent;
	private JScrollPane scoreJsp;
	private JScrollPane studentJsp;
	private JTable scoreTable;
	private JTable studentTable;	
	private ScoreTableModel scoreModel;
	private StudentTableModel studentModel;
	private JTextField idTextField;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textFieldPassword;
	private JTextField beforeTextField;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TeacherMainView(Teacher teacher) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		setLocationRelativeTo(null);
		
		jtp = new JTabbedPane();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.CYAN);
		setContentPane(contentPane);

		showScorePane = new JPanel();
		showScorePane.setBackground(Color.CYAN);
		jtp.add("全部学生成绩", showScorePane);
		scoreModel = new ScoreTableModel(20);
		scoreTable = new JTable(scoreModel);
		scoreTable.setBackground(Color.CYAN);
		TableColumnModel tcm = scoreTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(80);
		tcm.getColumn(1).setPreferredWidth(100);
		tcm.getColumn(2).setPreferredWidth(70);
		tcm.getColumn(3).setPreferredWidth(70);
		tcm.getColumn(4).setPreferredWidth(70);
		tcm.getColumn(5).setPreferredWidth(70);
		tcm.getColumn(6).setPreferredWidth(0);
		scoreJsp = new JScrollPane(scoreTable);
		showScorePane.add(scoreJsp, BorderLayout.CENTER);
		scoreModel.addRows();
		TableColumn tc = scoreTable.getTableHeader().getColumnModel().getColumn(6);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMinWidth(0);
		scoreTable.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
		scoreTable.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
		
		serchStudent = new JPanel();
		serchStudent.setBackground(Color.CYAN);
		jtp.add("查询修改学生", serchStudent);
		serchStudent.setLayout(null);
		
		JLabel idLabel = new JLabel("学号:");
		idLabel.setBounds(0, 0, 45, 18);
		serchStudent.add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(50, -3, 86, 24);
		serchStudent.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("姓名:");
		nameLabel.setBounds(150, 0, 45, 18);
		serchStudent.add(nameLabel);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(200, -3, 86, 24);
		serchStudent.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel sexLabel = new JLabel("性别:");
		sexLabel.setBounds(300, 0, 45, 18);
		serchStudent.add(sexLabel);
		String[] sex = {"男","女"};
		JComboBox sexComboBox = new JComboBox(sex);
		sexComboBox.setBounds(350, -3, 45, 24);
		serchStudent.add(sexComboBox);
		
		JLabel departmentLabel = new JLabel("院系:");
		departmentLabel.setBounds(0, 29, 45, 18);
		serchStudent.add(departmentLabel);
		
		JComboBox departmentComboBox = new JComboBox();
		departmentComboBox.setModel(new DefaultComboBoxModel(DepProSelect.getDepartment()));
		departmentComboBox.setBounds(37, 26, 99, 24);
		serchStudent.add(departmentComboBox);
		
		
		JLabel professionLabel = new JLabel("专业:");
		professionLabel.setBounds(143, 29, 45, 18);
		serchStudent.add(professionLabel);
		
		JComboBox professionComboBox = new JComboBox();
		professionComboBox.setModel(new DefaultComboBoxModel(DepProSelect.getProfession((String) departmentComboBox.getSelectedItem())));
		professionComboBox.setBounds(186, 26, 99, 24);
		serchStudent.add(professionComboBox);
		
		departmentComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				itemChange(departmentComboBox,professionComboBox);
				
			}
		});
		JLabel passwordLabel = new JLabel("密码:");
		passwordLabel.setBounds(287, 29, 45, 18);
		serchStudent.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(326, 26, 110, 24);
		serchStudent.add(passwordField);
		
		JButton submitButton = new JButton("添加");
		submitButton.addMouseListener(new MouseAdapter() {
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
					student.setSex((String)sexComboBox.getSelectedItem());
					student.setDepartment((String)departmentComboBox.getSelectedItem());
					student.setProfession((String)professionComboBox.getSelectedItem());
					student.setPassword(passwordField.getText());
					if(ServiceFactory.getStudentServiceInstance().studentReg(student)) {
						JOptionPane.showMessageDialog(null, "添加信息成功!");
						TeacherMainView.removeData(scoreModel, scoreTable);
						TeacherMainView.removeData(studentModel, studentTable);
						scoreModel.addRows();
						studentModel.addRows();
					}else {
						JOptionPane.showMessageDialog(null, "添加信息失败!再试一次?或联系管理员!");
					}
				}
				
				
			}
		});
		submitButton.setBounds(440, 0, 77, 52);
		serchStudent.add(submitButton);

		JPanel studentPanel = new JPanel();
		studentPanel.setBounds(0, 60, 517, 257);
		studentPanel.setBackground(Color.CYAN);
		serchStudent.add(studentPanel);
		
		studentModel = new StudentTableModel(20);
		studentTable = new JTable(studentModel);
		studentTable.setBackground(Color.CYAN);

		JComboBox sexCom = new JComboBox(sex);
		JComboBox departmentCom = new JComboBox(DepProSelect.getDepartment());
		JComboBox professionCom = new JComboBox(DepProSelect.getProfession((String) departmentCom.getSelectedItem()));
		departmentCom.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				itemChange(departmentCom,professionCom);
				
			}
		});
		TableColumnModel tcm1 = studentTable.getColumnModel();
		tcm1.getColumn(0).setPreferredWidth(70);
		tcm1.getColumn(1).setPreferredWidth(50);
		tcm1.getColumn(2).setPreferredWidth(50);
		tcm1.getColumn(2).setCellEditor(new DefaultCellEditor(sexCom));
		tcm1.getColumn(3).setPreferredWidth(100);
		tcm1.getColumn(3).setCellEditor(new DefaultCellEditor(departmentCom));
		tcm1.getColumn(4).setPreferredWidth(100);
		tcm1.getColumn(4).setCellEditor(new DefaultCellEditor(professionCom));
		tcm1.getColumn(5).setPreferredWidth(70);
		studentJsp = new JScrollPane(studentTable);
		studentPanel.add(studentJsp);
		studentModel.addRows();
		
		JLabel label = new JLabel("学号:");
		label.setBounds(10, 330, 57, 18);
		serchStudent.add(label);
		
		textField = new JTextField();
		textField.setBounds(50, 327, 146, 24);
		serchStudent.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("删除");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(ValidateUtils.validateEmpty(textField.getText())) {
					JOptionPane.showMessageDialog(null, "请填写要删除的学生学号!");
				}else {
					int n = JOptionPane.showConfirmDialog(null, "确认删除吗?不能找回噢!", "确认对话框", JOptionPane.YES_NO_OPTION);   
					if (n == JOptionPane.YES_OPTION) {   
						Student student = new Student();
						student.setId(Integer.parseInt(textField.getText()));
						if(ServiceFactory.getTeacherServiceInstance().deleteStuInfo(student)) {
							JOptionPane.showMessageDialog(null, "删除成功!");
							TeacherMainView.removeData(scoreModel, scoreTable);
							TeacherMainView.removeData(studentModel, studentTable);
							scoreModel.addRows();
							studentModel.addRows();
						} else {
							JOptionPane.showMessageDialog(null, "删除失败!再试一次?或联系作者!");
						}
					}   
				}
				
			}
		});
		button.setBounds(210, 326, 113, 27);
		serchStudent.add(button);
		
		JButton serchButton = new JButton("查询");
		serchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(ValidateUtils.validateEmpty(textField.getText())) {
					JOptionPane.showMessageDialog(null, "请填写要查询的学生学号!");
				}else {
					Student student = new Student();
					Student a = new Student();
					student.setId(Integer.parseInt(textField.getText()));
					a = ServiceFactory.getStudentServiceInstance().showInfo(student);
					JOptionPane.showMessageDialog(null, "学号:    "+a.getId()+"    姓名:    "+a.getName()+"    性别:    "+a.getSex()+"    院系:    "+a.getDepartment()+"    专业:    "+a.getProfession());  
				}
			}
		});
		
		serchButton.setBounds(350, 326, 113, 27);
		serchStudent.add(serchButton);
		
		

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
		
		JButton updatePassButton = new JButton("修改密码");
		updatePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidateUtils.validateEmpty(textFieldPassword.getText())||ValidateUtils.validateEmpty(beforeTextField.getText())) {
					JOptionPane.showMessageDialog(null, "新旧密码都要填上噢!");
				}else {
					if(beforeTextField.getText().equals(teacher.getPassword())) {
						teacher.setPassword(textFieldPassword.getText());
						if(ServiceFactory.getTeacherServiceInstance().updatePassword(teacher)) {
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
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(100, 147, 133, 24);
		setPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel PasswordLabel = new JLabel("新密码:");
		PasswordLabel.setBounds(14, 150, 72, 18);
		setPane.add(PasswordLabel);
		
		beforeTextField = new JTextField();
		beforeTextField.setBounds(100, 111, 133, 24);
		setPane.add(beforeTextField);
		beforeTextField.setColumns(10);
		
		JLabel beforePasswordLabel = new JLabel("原密码:");
		beforePasswordLabel.setBounds(14, 114, 72, 18);
		setPane.add(beforePasswordLabel);

		contentPane.add(jtp, BorderLayout.CENTER);
		setVisible(true);
	}

	class ScoreTableModel extends AbstractTableModel {
		@SuppressWarnings("rawtypes")
		private Vector scoreContent = null;

		private String[] titleName = { "学号", "姓名", "Java", "数据库", "C语言", "线性代数","" };

		@SuppressWarnings("rawtypes")
		public ScoreTableModel() {
			scoreContent = new Vector();
		}

		@SuppressWarnings("rawtypes")
		public ScoreTableModel(int count) {
			scoreContent = new Vector(count);
		}
		public void removeRow(int row) {
			scoreContent.remove(row);
		}

		public void removeRows(int row, int count) {
			for (int i = 0; i < count; i++) {
				if (scoreContent.size() > row) {
					scoreContent.remove(row);
				}
			}
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void addRows() {
			Vector<Student> all = ServiceFactory.getTeacherServiceInstance().showAllStudent();
			
			for (Student student : all) {
				Vector v = new Vector();
				v.add(0, student.getId());
				v.add(1, student.getName());
				Score score = ServiceFactory.getTeacherServiceInstance().showStudentScore(student);
				v.add(2, score.getJava());
				v.add(3, score.getDatabase());
				v.add(4, score.getC());
				v.add(5, score.getLinearalgebra());
				v.add(6, score.getId());
				scoreContent.add(v);
			}
			scoreTable.updateUI();
		}

		/**
		 * 让表格中某些值可修改，但需要setValueAt(Object value, int row, int col)方法配合才能使修改生效
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 0||columnIndex == 1) {
				return false;
			}
			return true;
		}

		/**
		 * 使修改的内容生效
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void setValueAt(Object value, int row, int col) {
			((Vector) scoreContent.get(row)).remove(col);
			((Vector) scoreContent.get(row)).add(col, value);
			Vector v = ((Vector) scoreContent.get(row));
			Score score = new Score();
			score.setId((Integer) v.get(6));
			score.setC(Double.parseDouble(String.valueOf(v.get(4))));
			score.setDatabase(Double.parseDouble(String.valueOf(v.get(3))));
			score.setLinearalgebra(Double.parseDouble(String.valueOf(v.get(5))));
			score.setJava(Double.parseDouble(String.valueOf(v.get(2))));
			System.out.println(ServiceFactory.getTeacherServiceInstance().updateStuScore(score));
			this.fireTableCellUpdated(row, col);
		}

		public String getColumnName(int col) {
			return titleName[col];
		}

		public int getColumnCount() {
			return titleName.length;
		}

		public int getRowCount() {
			return scoreContent.size();
		}

		@SuppressWarnings("rawtypes")
		public Object getValueAt(int row, int col) {
			return ((Vector) scoreContent.get(row)).get(col);
		}

	}
	class StudentTableModel extends AbstractTableModel {
		@SuppressWarnings("rawtypes")
		private Vector scoreContent = null;

		private String[] titleName = { "学号", "姓名", "性别", "院系", "专业", "密码" };

		@SuppressWarnings("rawtypes")
		public StudentTableModel() {
			scoreContent = new Vector();
		}

		@SuppressWarnings("rawtypes")
		public StudentTableModel(int count) {
			scoreContent = new Vector(count);
		}
		public void removeRow(int row) {
			scoreContent.remove(row);
		}

		public void removeRows(int row, int count) {
			for (int i = 0; i < count; i++) {
				if (scoreContent.size() > row) {
					scoreContent.remove(row);
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		public void addRows() {
			Vector<Student> all = ServiceFactory.getTeacherServiceInstance().showAllStudent();
			
			for (Student student : all) {
				@SuppressWarnings("rawtypes")
				Vector v = new Vector();
				v.add(0, student.getId());
				v.add(1, student.getName());
				v.add(2,student.getSex());
				v.add(3,student.getDepartment());
				v.add(4,student.getProfession());
				v.add(5,student.getPassword());
				scoreContent.add(v);
			}
			scoreTable.updateUI();
		}

		/**
		 * 让表格中某些值可修改，但需要setValueAt(Object value, int row, int col)方法配合才能使修改生效
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 0||columnIndex == 1) {
				return false;
			}
			return true;
		}

		/**
		 * 使修改的内容生效
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void setValueAt(Object value, int row, int col) {
			((Vector) scoreContent.get(row)).remove(col);
			((Vector) scoreContent.get(row)).add(col, value);
			Vector v = ((Vector) scoreContent.get(row));
			Student student = new Student();
			student.setId((Integer)v.get(0));
			student.setName((String)v.get(1));
			student.setSex((String)v.get(2));
			student.setDepartment((String)v.get(3));
			student.setProfession((String)v.get(4));
			student.setPassword((String)v.get(5));
			System.out.println(ServiceFactory.getStudentServiceInstance().updateInfo(student));
			this.fireTableCellUpdated(row, col);
		}

		public String getColumnName(int col) {
			return titleName[col];
		}

		public int getColumnCount() {
			return titleName.length;
		}

		public int getRowCount() {
			return scoreContent.size();
		}

		@SuppressWarnings("rawtypes")
		public Object getValueAt(int row, int col) {
			return ((Vector) scoreContent.get(row)).get(col);
		}

	}
	private static void removeData(StudentTableModel model,JTable table) {
		model.removeRows(0, model.getRowCount());
		table.updateUI();
	}
	private static void removeData(ScoreTableModel model,JTable table) {
		model.removeRows(0, model.getRowCount());
		table.updateUI();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void itemChange(JComboBox departmentComboBox,JComboBox professionComboBox) {
        String selectDepartment = (String) departmentComboBox.getSelectedItem();
        professionComboBox.removeAllItems(); // 清空专业列表
        String[] arrCity = DepProSelect.getProfession(selectDepartment); // 获取专业
        professionComboBox.setModel(new DefaultComboBoxModel(arrCity)); // 重新添加专业列表的值
	}
}
