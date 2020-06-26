import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Menus extends JPanel implements ActionListener
{
	JLabel profileImg;
	
	JPanel pnlDept;
	JLabel lblDept;
	
	JPanel pnlEmp;
	JLabel lblEmp;
	Font customFont;
	
	public Menus()
	{
		setBounds(0,0,400,700);
		setBackground(new Color(107, 104, 232));
		setLayout(null);
		customFont = new Font("Comic Sans MS", Font.BOLD, 25);
		
		profileImg = new JLabel();
		Image img = new ImageIcon("img.png").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
		profileImg.setIcon(new ImageIcon(img));
		profileImg.setBounds(100,100,150,150);
		
		ImageIcon menuIcon = new ImageIcon(new ImageIcon("img.png").getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
		
		pnlDept = new JPanel();
		pnlDept.setLayout(null);
		pnlDept.setBounds(10,300,400,50);
		
		
		JLabel deptIcon = new JLabel();
		deptIcon.setIcon(menuIcon);
		deptIcon.setBounds(5,5,35,35);
		
		lblDept = new JLabel("Departments");
		lblDept.setBounds(80,0,400,50);
		lblDept.setFont(customFont);
		
		
		pnlDept.add(deptIcon);
		pnlDept.add(lblDept);
		
		pnlEmp = new JPanel();
		pnlEmp.setLayout(null);
		pnlEmp.setBounds(10,352,400,50);
		
		
		JLabel empIcon = new JLabel();
		empIcon.setIcon(menuIcon);
		empIcon.setBounds(5,5,35,35);
		
		lblEmp = new JLabel("Employees");
		lblEmp.setBounds(80,0,400,50);
		lblEmp.setFont(customFont);
		
		
		pnlEmp.add(empIcon);
		pnlEmp.add(lblEmp);
		
		resetMenuOptionsStyling();
		
		add(pnlDept);
		add(pnlEmp);
		add(profileImg);
	}
	
	public void resetMenuOptionsStyling()
	{
		pnlDept.setBackground(new Color(107, 104, 232));
		lblDept.setForeground(Color.WHITE);
		pnlEmp.setBackground(new Color(107, 104, 232));
		lblEmp.setForeground(Color.WHITE);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
	}
}