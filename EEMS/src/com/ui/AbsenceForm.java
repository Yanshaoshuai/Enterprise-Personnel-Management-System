package com.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.util.SqlHelper;
import java.awt.Window.Type;
import java.awt.Color;

public class AbsenceForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JScrollPane scrollPane = new JScrollPane();
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AbsenceForm() {
		setType(Type.UTILITY);
		setTitle("\u7F3A\u52E4\u8BB0\u5F55");
		setBounds(100, 100, 585, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(10, 10, 549, 234);
		bindTable();
		textField = new JTextField();
		textField.setBounds(56, 295, 90, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(170, 295, 99, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(289, 295, 107, 21);
		contentPane.add(textField_2);
		
		JLabel label = new JLabel("\u5DE5\u53F7");
		label.setBounds(56, 270, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u65E5\u671F");
		label_1.setBounds(170, 270, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u4E8B\u7531");
		label_2.setBounds(289, 270, 54, 15);
		contentPane.add(label_2);
		
		JButton button = new JButton("\u63D2\u5165\u7F3A\u52E4\u8BB0\u5F55");
		button.setBackground(new Color(192, 192, 192));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")||textField_1.equals("")){
					JOptionPane.showMessageDialog(null, "工号和部门号必填");
					return;
				}
				String eid=textField.getText();
				String date=textField_1.getText();
				String reason=(textField_2.getText().equals("")?"无故旷工":textField_2.getText());
				if(SqlHelper.executeQuery("select * from employee where eid=?", new String[]{eid}).size()==0){
					JOptionPane.showMessageDialog(null, "员工号不存在");
					return;
				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date d;
				try {
					 d=sdf.parse(date);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "日期格式应为2008-8-8形式");
					return;
				}
				if(SqlHelper.executeUpdate("insert into absence values(?,to_date(?,'yyyy-MM-dd'),?)", new String[]{eid,date,reason})){
					String []datePart=sdf.format(d).split("-");
					String yearMonth=datePart[0]+"-"+datePart[1];
					if(SqlHelper.executeQuery("select * from salary where eid=? and syearMonth=?", new String[]{eid,yearMonth}).size()==0){
						SqlHelper.executeUpdate("insert into salary values(?,?,?)", new String[]{eid,"1",yearMonth});
					}else{
						SqlHelper.executeUpdate("update salary set abcount=abcount+1 where eid=? and syearmonth=?", new String[]{eid,yearMonth});
					}
					JOptionPane.showMessageDialog(null, "插入成功");
					refresh();
					return;
				}
			}
		});
		button.setBounds(422, 294, 137, 23);
		contentPane.add(button);
		this.setVisible(true);
	}

	private void bindTable() {
		
		Object [][]data;
		List<Object []> list=SqlHelper.executeQuery("select employee.eid,employee.ename,pname,abdate,abreason from absence ,employee,partment  where absence.eid=employee.eid and employee.pid=partment.pid", null);
		
		data=new Object[list.size()][5];
		for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < 5; j++) {
					data[i][j]=list.get(i)[j];
					
				}
		}
		table = new JTable(data,new String[]{"工号","姓名","部门","日期","事由"});
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
	}
	private void refresh() {//实现刷新表格功能
		
		Object [][]data;
		List<Object []> list=SqlHelper.executeQuery("select employee.eid,employee.ename,pname,abdate,abreason from absence ,employee,partment  where absence.eid=employee.eid and employee.pid=partment.pid", null);
		
		data=new Object[list.size()][5];
		for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < 5; j++) {
					data[i][j]=list.get(i)[j];
					
				}
		}
		table = new JTable(data,new String[]{"工号","姓名","部门","日期","事由"});
		scrollPane.setViewportView(table);
		
		
	}
}
