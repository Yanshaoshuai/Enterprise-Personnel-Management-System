package com.ui;

import java.math.BigDecimal;
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
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.domain.Partment;
import com.util.SqlHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class EmployeeManagerForm extends JFrame {
    public static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private JPanel contentPane;
	private JTable table=null;
	JScrollPane scrollPane;
	JTree tree=null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public EmployeeManagerForm() {
		setTitle("\u5458\u5DE5\u7BA1\u7406");
		setType(Type.UTILITY);
		setBounds(100, 100, 907, 518);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bindTree();
	
		
		contentPane.add(tree);
		
		JLabel lblNewLabel = new JLabel("\u5DE5\u53F7");
		lblNewLabel.setBounds(309, 344, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(373, 344, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6027\u522B\r\n");
		label_1.setBounds(437, 344, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5165\u804C\u65E5\u671F\r\n");
		label_2.setBounds(505, 344, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5E74\u9F84");
		label_3.setBounds(578, 344, 54, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u90E8\u95E8");
		label_4.setBounds(658, 344, 54, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u804C\u4F4D");
		label_5.setBounds(730, 344, 54, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u5DE5\u8D44");
		label_6.setBounds(797, 344, 54, 15);
		contentPane.add(label_6);
		
		textField = new JTextField();
		textField.setBounds(309, 369, 54, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(373, 369, 54, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(437, 369, 54, 21);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(501, 369, 69, 21);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(578, 369, 54, 21);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(642, 369, 78, 21);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(730, 369, 54, 21);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(797, 369, 54, 21);
		contentPane.add(textField_7);
		
		JButton button = new JButton("\u4FEE\u6539");
		button.setBackground(new Color(192, 192, 192));
		button.addActionListener(new ActionListener() {	
			
			public void actionPerformed(ActionEvent e) {
				if((String)table.getValueAt(table.getSelectedRow(), 0)=="-1"){
					return;
				}
				
				try {
					sdf.parse(textField_3.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "日期格式应为2008-8-8形式");
					return;
				}
				String []parameters=new String[]{
						textField_1.getText(),
						textField_2.getText(),textField_3.getText(),
						textField_4.getText(),(String)(SqlHelper.executeQuery("select * from partment where pname=?",
						new String[]{textField_5.getText()}).get(0)[0]),
						textField_6.getText(),textField_7.getText(),textField.getText()};
					if(((String)table.getValueAt(table.getSelectedRow(), 0)).equals(textField.getText())){
					if(SqlHelper.executeUpdate("update employee set ename=?,esex=?,ein_date=to_date(?,'yyyy-mm-dd'),eage=?,pid=?,ejob=?,esalary=? where eid=? ", parameters)){
						
						JOptionPane.showMessageDialog(null, "修改成功");
	
						refresh();
						return;
					}
				}else{
					JOptionPane.showMessageDialog(null, "请不要修改员工号");
					return;
				}
			}
		});
		button.setBounds(309, 416, 69, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u63D2\u5165");
		button_1.setBackground(new Color(192, 192, 192));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String eid=textField.getText();//检查
				String ename=textField_1.getText();
				String esex=textField_2.getText();
				String ein_date=textField_3.getText();
				try {
					sdf.parse(textField_3.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "日期格式应为2008-8-8形式");
					return;
				}
				String eage=textField_4.getText();
				String pname=textField_5.getText();//检查
				String ejob=textField_6.getText();
				String esalary=textField_7.getText();
				if(eid.equals("")||pname.equals("")){
					JOptionPane.showMessageDialog(null, "员工号和部门名不能为空");
					return;
				}
				String  pid=(String)(SqlHelper.executeQuery("select * from partment where pname=?", new String[]{pname}).get(0)[0]);
				boolean isEidExisit=true;
				boolean isPidExisit=false;
				
				if(SqlHelper.executeQuery("select * from employee where eid=?", new String[]{eid}).size()<=0){
					isEidExisit=false;
				}
				if(SqlHelper.executeQuery("select * from partment where pid=?", new String[]{pid}).size()>0){
					isPidExisit=true;
				}
				if((!isEidExisit)&&(isPidExisit)){
					if(SqlHelper.executeUpdate("insert into employee values(?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?)",new String[]{eid,ename,esex,ein_date,eage,pid,ejob,esalary})){
						JOptionPane.showMessageDialog(null, "插入成功");
						refresh();
						return;
					}
				}else{
					JOptionPane.showMessageDialog(null, "请检查员工号是否重复或者是否存在部门");
					return;
				}
			}
		});
		button_1.setBounds(415, 416, 69, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.setBackground(new Color(192, 192, 192));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eid=(String)table.getValueAt(table.getSelectedRow(), 0);
				int i=JOptionPane.showConfirmDialog(null, "是否删除");
				if(i==JOptionPane.CANCEL_OPTION||i==JOptionPane.NO_OPTION){
					return;
				}
				if(SqlHelper.executeUpdate("delete from employee where eid=?", new String[]{eid})){
					JOptionPane.showMessageDialog(null, "删除成功");;
					refresh();
					return;
				}
			}
		});
		button_2.setBounds(617, 416, 69, 23);
		contentPane.add(button_2);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(192, 192, 192));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_2.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				
			}
		});
		btnReset.setBounds(517, 416, 69, 23);
		contentPane.add(btnReset);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 20, 617, 248);
		
		contentPane.add(scrollPane);
		
		
		
		this.setVisible(true);
	}

	protected void refresh() {
		Partment p=(Partment)tree.getLastSelectedPathComponent();
		//绑定表格
		String pid=p.getPid();
		List<Object[]> list=SqlHelper.executeQuery("select * from employee where pid=?", new String[]{ pid});
		int row=list.size();
		Object[][]employees=new Object[row][8];
		for (int j = 0; j <row; j++) {
			Object []o=list.get(j);
			for (int k = 0; k < 8; k++) {
				if(k==5)
					employees[j][k]=p.toString();
				else
				employees[j][k]=o[k];
			}
		}
		table=new JTable(employees, new String[]{
				"工号","姓名","性别","入职日期","年龄","部门","职位","工资"
		}){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
			       return false;
			   }
		};
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						int i=table.getSelectedRow();
						textField.setText((String)table.getValueAt(i, 0));
						textField_1.setText((String)table.getValueAt(i, 1));
						textField_2.setText((String)table.getValueAt(i, 2));
						textField_3.setText(sdf.format((Date)table.getValueAt(i, 3)));
						textField_4.setText(""+((BigDecimal)table.getValueAt(i, 4)));
						textField_5.setText((String)table.getValueAt(i, 5));
						textField_6.setText((String)table.getValueAt(i, 6));
						textField_7.setText(""+(BigDecimal)table.getValueAt(i, 7));
						
					}
				});
				
			}
		});
		scrollPane.setViewportView(table);
		
	
	
		
}

	private void bindTree() {
		List<Object[]> list=SqlHelper.executeQuery("select * from partment", null);
		int row=list.size();
		
		Partment[] partments=new Partment[row];
		for (int i = 0; i < row; i++) {
			partments[i]=new Partment();
			partments[i].setPid((String)list.get(i)[0]);
			partments[i].setPname((String)list.get(i)[1]);
			partments[i].setPpid((String)list.get(i)[2]);
		}
		//动态生成树
		for(int i=0;i<row;i++){
			String ppid=partments[i].getPpid(); 
			for(int j=0;j<row;j++){
				if(partments[j].getPid().equals(ppid)){
					partments[j].add(partments[i]);
				}
			}
			if(ppid.equals("null")){
				tree=new JTree(partments[i], false);
				tree.setBounds(10,20, 244, 440);
				tree.addTreeSelectionListener(new TreeSelectionListener() {
					
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						if(e.getSource()==tree){
							Partment p=(Partment)tree.getLastSelectedPathComponent();
							//绑定表格
							String pid=p.getPid();
							List<Object[]> list=SqlHelper.executeQuery("select * from employee where pid=?", new String[]{ pid});
							int row=list.size();
							Object[][]employees=new Object[row][8];
							for (int j = 0; j <row; j++) {
								Object []o=list.get(j);
								for (int k = 0; k < 8; k++) {
									if(k==5)
										employees[j][k]=p.toString();
									else
									employees[j][k]=o[k];
								}
							}
							table=new JTable(employees, new String[]{
									"工号","姓名","性别","入职日期","年龄","部门","职位","工资"
							}){
								 /**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								public boolean isCellEditable(int row, int column){
								       return false;
								   }
							};
							table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
								
								@Override
								public void valueChanged(ListSelectionEvent e) {
									SwingUtilities.invokeLater(new Runnable() {
										
										@Override
										public void run() {
											int i=table.getSelectedRow();
											textField.setText((String)table.getValueAt(i, 0));
											textField_1.setText((String)table.getValueAt(i, 1));
											textField_2.setText((String)table.getValueAt(i, 2));
											SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
											textField_3.setText(sdf.format((Date)table.getValueAt(i, 3)));
											textField_4.setText(""+((BigDecimal)table.getValueAt(i, 4)));
											textField_5.setText((String)table.getValueAt(i, 5));
											textField_6.setText((String)table.getValueAt(i, 6));
											textField_7.setText(""+(BigDecimal)table.getValueAt(i, 7));
											
										}
									});
									
								}
							});
							scrollPane.setViewportView(table);

						}
						
					}
				});
			}
		}
		
		
	}
}
