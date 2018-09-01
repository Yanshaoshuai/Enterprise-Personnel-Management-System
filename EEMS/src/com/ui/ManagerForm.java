package com.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import java.awt.Font;


import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class ManagerForm extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public ManagerForm(String managerName) {
		setType(Type.POPUP);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 509);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u5458\u5DE5\u7BA1\u7406");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new EmployeeManagerForm();
					}
					
					
				});
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u8003\u52E4\u7BA1\u7406");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new AbsenceForm();
					}
					
					
				});
				
			}
		});
		menuBar.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u8D44\u85AA\u53D1\u653E");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new SalaryForm();
					}
					
					
				});
				
			}
		});
		menuBar.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, new Color(0, 139, 139), SystemColor.textHighlight, new Color(0, 139, 139)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnqqcom = new JRadioButton("");
		rdbtnqqcom.setForeground(new Color(0, 128, 128));
		rdbtnqqcom.setBounds(205, 176, 21, 27);
		rdbtnqqcom.setBackground(SystemColor.activeCaption);
		rdbtnqqcom.setEnabled(false);
		rdbtnqqcom.setFont(new Font("宋体", Font.PLAIN, 15));
		rdbtnqqcom.setSelected(true);
		contentPane.add(rdbtnqqcom);
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setForeground(new Color(0, 128, 128));
		radioButton.setBounds(205, 221, 21, 27);
		radioButton.setBackground(SystemColor.activeCaption);
		radioButton.setEnabled(false);
		radioButton.setSelected(true);
		radioButton.setFont(new Font("宋体", Font.PLAIN, 15));
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setSelected(true);
		radioButton_1.setForeground(new Color(0, 128, 128));
		radioButton_1.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButton_1.setEnabled(false);
		radioButton_1.setBackground(SystemColor.activeCaption);
		radioButton_1.setBounds(205, 137, 21, 27);
		contentPane.add(radioButton_1);
		
		JLabel label = new JLabel(" \u7BA1 \u7406 \u5458 \u987B \u77E5");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("宋体", Font.BOLD, 12));
		label.setBounds(240, 137, 110, 27);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(" \u672C \u7CFB \u7EDF \u4EC5 \u4F9B \u516C \u53F8 \u5185 \u90E8 \u4F7F \u7528");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("宋体", Font.BOLD, 12));
		label_1.setBounds(240, 176, 245, 27);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5458 \u5DE5 \u9690 \u79C1 \uFF0C\u4E0D \u5F97 \u5916 \u6CC4");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("宋体", Font.BOLD, 12));
		label_2.setBounds(250, 221, 178, 27);
		contentPane.add(label_2);
		
		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setSelected(true);
		radioButton_2.setForeground(new Color(0, 128, 128));
		radioButton_2.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButton_2.setEnabled(false);
		radioButton_2.setBackground(SystemColor.activeCaption);
		radioButton_2.setBounds(205, 262, 21, 27);
		contentPane.add(radioButton_2);
		
		JLabel lblqqcom = new JLabel(" \u8054 \u7CFB 1446999156@qq.com \u83B7 \u53D6 \u66F4 \u591A \u5E2E \u52A9");
		lblqqcom.setForeground(Color.BLUE);
		lblqqcom.setFont(new Font("宋体", Font.BOLD, 12));
		lblqqcom.setBounds(240, 262, 313, 27);
		contentPane.add(lblqqcom);
		

		

		this.setTitle("管理员"+managerName);
		this.setVisible(true);
	}
}
