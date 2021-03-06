package Kasir_Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JPasswordField;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnLogin;
	private JPasswordField passwordField;
	private ResultSet hasil;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		}
			catch (Exception ax) {
		}
		
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
	
	public LoginForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						passwordField.requestFocus();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		textField.setBounds(44, 47, 250, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(44, 22, 82, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(44, 78, 82, 14);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!textField.getText().equals("")&&!passwordField.getText().equals("")){
						hasil = Connector.GetConnection().GetUser(textField.getText(), passwordField.getText());
						if(hasil.last()){
							new MainForm().setVisible(true);
							setVisible(false);
						}else{
							JOptionPane.showMessageDialog(null, "Username - Password tidak terdaftar !");
							passwordField.requestFocus();
						}
					}else{
						JOptionPane.showMessageDialog(null, "Input masi belum lengkap !");
						passwordField.requestFocus();
					}
				} catch (Exception e) {
					System.out.println(e);
				}				
			}
		});
		btnLogin.setBounds(205, 136, 89, 23);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						btnLogin.requestFocus();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		passwordField.setBounds(44, 103, 250, 20);
		contentPane.add(passwordField);
		setLocationRelativeTo(null);
	}
}
