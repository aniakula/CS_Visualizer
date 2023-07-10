import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





public class GraphMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final BinaryPanel binPanel = new BinaryPanel();
        JMenuBar bar = new JMenuBar();
        JMenu options = new JMenu("Actions");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem addN = new JMenuItem("Add new Node");
        
        addN.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 

        		  String name = "";
        		  name = JOptionPane.showInputDialog("name for new node:");
        		  
        		  if(name != null)
        		  binPanel.addNode(name, 100, 100);
        		  binPanel.repaint(); 
      	  } 
      	} );
      
        JMenuItem disp = new JMenuItem("Change Display");
        disp.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

          		  String disp = "";
          		  String[] options = {"names", "values", "levels"};
          		  disp = (String) JOptionPane.showInputDialog(null, "Choose Display Type", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
          		  //change color based on choice
          		  binPanel.changeDisplay(disp);
        	  } 
        	} );
        
       
        JMenuItem connect = new JMenuItem("Add a connection");
        JMenuItem delConnect = new JMenuItem("Remove existing connection");
    	options.add(help);
    	options.add(addN);
    	options.add(connect);
    	options.add(delConnect);
    	options.add(disp);
    	
    	bar.add(options);
    	frame.setJMenuBar(bar);
    	options.setVisible(true);
    	
        frame.add(binPanel);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
     
        frame.setVisible(true); 

        binPanel.addNode("Node 1", 200, 200);
        binPanel.getNodeList().get(0).setLevel(0);
        

    }
	
}
