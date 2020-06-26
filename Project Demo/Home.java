import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Home extends Frame implements ActionListener, MouseListener, MouseMotionListener
{
	CardLayout card;
	Menus panelMenu;
	JPanel panelScreen;
	JLabel lblClose;
	Point lastMousePoint;
	JPanel panelHeader;
	public Home()
	{
		setTitle("AWT Panel Layout Demo");
		setBounds(0,0,1200,700);
		setLayout(null);
		setUndecorated(true);
	
		panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBounds(400,0,800,50);
		
		JLabel lblHeader = new JLabel("Office Management System");
		lblHeader.setBounds(20,5,500,40);
		lblHeader.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		
		lblClose = new JLabel();
		ImageIcon closeIcon = new ImageIcon(new ImageIcon("close.png").getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
		lblClose.setIcon(closeIcon);
		lblClose.setBounds(750,5,35,35);
		lblClose.addMouseListener(this);
		
		panelHeader.add(lblHeader);
		panelHeader.add(lblClose);
	
		panelMenu = new Menus();
		panelMenu.pnlDept.addMouseListener(this);
		panelMenu.pnlEmp.addMouseListener(this);
		
		panelScreen = new JPanel();
		card = new CardLayout();
		panelScreen.setLayout(card);
		panelScreen.setBounds(400,50,800,650);
		panelScreen.setBackground(new Color(206, 68, 227));
		panelScreen.add("departments",new Departments());
		panelScreen.add("employees",new Employees());
		
		
		
		add(panelHeader);
		add(panelMenu);
		add(panelScreen);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
	}
	
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource() == panelMenu.pnlDept)
		{
			card.show(panelScreen,"departments");
			panelMenu.resetMenuOptionsStyling();
			panelMenu.pnlDept.setBackground(Color.WHITE);
			panelMenu.lblDept.setForeground(new Color(107, 104, 232));
		}else if(me.getSource() == panelMenu.pnlEmp)
		{
			card.show(panelScreen,"employees");
			panelMenu.resetMenuOptionsStyling();
			panelMenu.pnlEmp.setBackground(Color.WHITE);
			panelMenu.lblEmp.setForeground(new Color(107, 104, 232));
		}else if(me.getSource() == lblClose)
		{
			System.exit(0);
		}
	}
	
	public void mousePressed(MouseEvent me)
	{
		lastMousePoint = me.getPoint();
	}
	
	
	public void mouseReleased(MouseEvent me)
	{
		lastMousePoint = null;
	}
	
	
	public void mouseEntered(MouseEvent me)
	{
		
	}
	
	
	public void mouseExited(MouseEvent me)
	{
		
	}
	
	public void mouseDragged(MouseEvent me)
	{
		Point currentPoint = me.getLocationOnScreen();
		setLocation(currentPoint.x - lastMousePoint.x , currentPoint.y - lastMousePoint.y);
	}
	
	public void mouseMoved(MouseEvent me)
	{
		
	}
	
	public static void main(String args[])
	{
		new Home();
	}
}