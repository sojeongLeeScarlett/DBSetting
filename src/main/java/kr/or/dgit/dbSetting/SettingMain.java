package kr.or.dgit.dbSetting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sbSetting.service.BackupService;
import kr.or.dgit.sbSetting.service.DaoService;
import kr.or.dgit.sbSetting.service.InitService;
import kr.or.dgit.sbSetting.service.LoadService;

import java.awt.GridLayout;
import javax.swing.JButton;

public class SettingMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingMain frame = new SettingMain();
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
	public SettingMain() {
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 0));
		
		JButton btnIntial = new JButton(new AbstractBtnAction("초기화", this));
		contentPane.add(btnIntial);
		
		JButton btnBackup = new JButton(new AbstractBtnAction("백업", this));
		contentPane.add(btnBackup);
		
		JButton btnDataload = new JButton(new AbstractBtnAction("복원", this));
		contentPane.add(btnDataload);
	}
	
	public void initial() {
		InitService.getInstance().service();
		JOptionPane.showMessageDialog(null, "초기화 완료");
		
	}
	
	public void backupData() {
		BackupService.getInstance().service();
		JOptionPane.showMessageDialog(null, "백업 완료");
	}
	
	public void loadData() {
		LoadService.getInstance().service();
		JOptionPane.showMessageDialog(null, "복원");
	}
}
