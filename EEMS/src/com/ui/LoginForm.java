package com.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.util.SqlHelper;
import java.awt.Window.Type;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtMName;
	private JPasswordField txtMPwd;
	private JButton btnLogin;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setType(Type.POPUP);
		setForeground(SystemColor.desktop);
		setBackground(SystemColor.infoText);
		setTitle("XXX\u4F01\u4E1A\u4EBA\u4E8B\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 406);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(47, 79, 79), new Color(0, 139, 139), new Color(47, 79, 79), new Color(0, 139, 139)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel(" \u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("ËÎÌå", Font.BOLD, 12));
		label.setBackground(SystemColor.menu);
		label.setBounds(167, 108, 65, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("ËÎÌå", Font.BOLD, 12));
		label_1.setBounds(178, 154, 54, 15);
		contentPane.add(label_1);
		
		txtMName = new JTextField();
		txtMName.setBounds(242, 105, 137, 21);
		contentPane.add(txtMName);
		txtMName.setColumns(10);
		
		txtMPwd = new JPasswordField();
		txtMPwd.setBounds(242, 151, 137, 21);
		contentPane.add(txtMPwd);
		
		btnLogin = new JButton(" \u767B\u5F55  ");
		btnLogin.setBackground(new Color(176, 196, 222));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txtMName.getText();
				String pwd=new String(txtMPwd.getPassword());
				List<Object[]> list= SqlHelper.executeQuery("select * from manager where mid=? and mpwd=?", new String[]{name,pwd});
				if(list.size()>0){
					JOptionPane.showMessageDialog(null, "µÇÂ¼³É¹¦"+list.get(0)[2]);
					new ManagerForm(list.get(0)[2].toString());
					LoginForm.this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü");
				}
			}
		});
		btnLogin.setBounds(200, 211, 75, 23);
		contentPane.add(btnLogin);
		
		btnReset = new JButton(" \u91CD\u8BD5  ");
		btnReset.setBackground(new Color(176, 196, 222));
		btnReset.setBounds(304, 211, 75, 23);
		contentPane.add(btnReset);
	}

}
