package com.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.util.SqlHelper;
import java.awt.Window.Type;
import java.awt.Color;

public class SalaryForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton button_1;
	public static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private JButton button_2;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SalaryForm() {
		setType(Type.UTILITY);
		setTitle("\u5DE5\u8D44\u8BB0\u5F55");
	
		setBounds(100, 100, 754, 451);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 718, 365);
		contentPane.add(scrollPane);
		
		button_1 = new JButton("\u672C\u6708\u5DE5\u8D44\u7ED3\u7B97");
		button_1.setBackground(new Color(192, 192, 192));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] datePart=sdf.format(new Date()).split("-");
				String yearAndMonth=datePart[0]+"-"+datePart[1];
				
				List<Object[]>list=SqlHelper.executeQuery("select employee.eid,ename,pname,esalary,abcount from salary,employee,partment where salary.eid=employee.eid and employee.pid=partment.pid and syearmonth=?", new String[]{yearAndMonth});
				
				List<Object []>list2 =SqlHelper.executeQuery("select eid,ename,pname,esalary from employee , partment where employee.pid=partment.pid and eid not in(select eid from absence)", null);
				
				Object[][]object3=new Object[list.size()+list2.size()][6];
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < 5; j++) {
						
						if(j==4){//缺勤一天扣300
							object3[i][j]=Integer.parseInt(list.get(i)[3].toString())-Integer.parseInt((list.get(i)[4].toString()))*300;
							object3[i][5]=yearAndMonth;
						}else{
							object3[i][j]=list.get(i)[j];
						}
					}
				}
				for (int i = list.size(); i < list.size()+list2.size(); i++) {
					for (int j = 0; j < 5; j++) {
						
						if(j==4){
							object3[i][j]=object3[i][3];
							object3[i][5]=yearAndMonth;
						}else{
							object3[i][j]=list2.get(i-list.size())[j];
						}
					}
				}
				
				
				
				table = new JTable(object3,new String[]{
				  "工号","姓名","部门","应发工资","实发工资","年月"
				});
				table.setEnabled(false);
				scrollPane.setViewportView(table);
			}
		});
		button_1.setBounds(345, 385, 125, 23);
		contentPane.add(button_1);
		
		button_2 = new JButton("\u5956\u60E9\u8BB0\u5F55");
		button_2.setBackground(new Color(192, 192, 192));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				List<Object[]> list=SqlHelper.executeQuery("select salary.eid,ename,pname,abcount,syearmonth from salary,employee,partment where salary.eid=employee.eid and employee.pid=partment.pid",null);
				Object [][] object=new Object[list.size()][6];
				
				for (int i = 0; i < object.length; i++) {
					for (int j = 0; j < 6; j++) {
						
						if(j==4){
							object[i][j]=Integer.parseInt(list.get(i)[3].toString())*(-300);
							
						}else{
							if(j>4){
								object[i][j]=list.get(i)[j-1];
							}else
							object[i][j]=list.get(i)[j];
						}
					}
				}
				table=new JTable(object,new String[]{
				"工号","姓名","部门","缺勤次数","奖惩金额","年月"		
				});
				
				table.setEnabled(false);
				scrollPane.setViewportView(table);
				
			}
		});
		button_2.setBounds(205, 385, 93, 23);
		contentPane.add(button_2);
		this.setVisible(true);
	}
}
