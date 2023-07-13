import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





public class VisionMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graph Visualization");
        final JPanel overall = new JPanel();
        final CardLayout c1 = new CardLayout();
        overall.setLayout(c1);
        frame.add(overall);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final BinaryPanel binPanel = new BinaryPanel();
        final GraphPanel graphPanel = new GraphPanel();
        final FlowPanel flowPanel = new FlowPanel();
        overall.add(binPanel, "Binary Tree Builder");
        overall.add(graphPanel, "Graph Builder");
        overall.add(flowPanel, "Flow Chart Builder");
        JMenuBar bar = new JMenuBar();
        JMenu options = new JMenu("Actions");
        
        final JMenuItem helpGraph = new JMenuItem("Help");
        final JMenuItem helpFlow = new JMenuItem("Help");
        
        final JMenuItem addNGraph = new JMenuItem("Add new Node");
        final JMenuItem addNFlow = new JMenuItem("Add new Flow Structure");
        final JMenuItem graphScreen = new JMenuItem("Go to \"Graph work\" space");
        final JMenuItem flowScreen = new JMenuItem("Go to \"Flow Chart\" work space");
        
        final JMenuItem binScreen = new JMenuItem("Go to \"Binary Tree\" work space");
        //Binary Tree Main Menu Items:
        final JMenuItem OrganizeTree = new JMenuItem("Organize Binary Tree");
        final JMenuItem helpBin = new JMenuItem("Help");
        final JMenuItem addNBin = new JMenuItem("Add new Node");
        
        graphScreen.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

        		  c1.show(overall, "Graph Builder");
        		  graphScreen.setVisible(false);
        		  flowScreen.setVisible(true);
        		  binScreen.setVisible(true);
        		  addNBin.setVisible(false);
        		  addNGraph.setVisible(true);
        		  addNFlow.setVisible(false);
        		  helpBin.setVisible(false);
        		  helpGraph.setVisible(true);
        		  helpFlow.setVisible(false);
        		  OrganizeTree.setVisible(false);
        	  } 
        	} );
        
        
        flowScreen.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

        		  c1.show(overall, "Flow Chart Builder");
        		  graphScreen.setVisible(true);
        		  flowScreen.setVisible(false);
        		  binScreen.setVisible(true);
        		  addNBin.setVisible(false);
        		  addNGraph.setVisible(false);
        		  addNFlow.setVisible(true);
        		  helpBin.setVisible(false);
        		  helpGraph.setVisible(false);
        		  helpFlow.setVisible(true);
        		  OrganizeTree.setVisible(false);
        	  } 
        	} );
        
        binScreen.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 

      		  c1.show(overall, "Binary Tree Builder");
      		graphScreen.setVisible(true);
  		    flowScreen.setVisible(true);
  		    binScreen.setVisible(false);
  		  addNBin.setVisible(true);
  		 addNGraph.setVisible(false);
		  addNFlow.setVisible(false);
  		  helpBin.setVisible(true);
		  helpGraph.setVisible(false);
		  helpFlow.setVisible(false);
		  OrganizeTree.setVisible(true);
      	  } 
      	} );
        
        OrganizeTree.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  
      		  for(DragNode node :  binPanel.getNodeList())
      		  {
      			 if(node.getLevel() == 0)
      			 {
      				 node.setX(730);
      				 node.setY(0);
      			 }
      		  }
      		  
    	  } 
    	} );
 
        addNBin.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

          		  String name = "";
          		  name = JOptionPane.showInputDialog("name for new node:");
          		  
          		  if(name != null)
          		  binPanel.addNode(name, 100, 100);
          		  binPanel.repaint(); 
        	  } 
        	} );
     
        helpBin.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 

        		  JFrame help = new JFrame("Binary Tree Builder Help");
        		  help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		  help.setVisible(true);
      	  } 
      	} );
        
        helpGraph.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

        		  JFrame help = new JFrame("Graph Builder Help");
        		  help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		  help.setVisible(true);
          		
        	  } 
        	} );
        
        helpFlow.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

          		  JFrame help = new JFrame("Flow Chart Builder Help");
          		  help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          		  help.setVisible(true);
        	  } 
        	} );
        
        OrganizeTree.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  
      		  for(DragNode node : binPanel.getNodeList())
      		  {
      		     if(node.getLevel() == 0) {
      		    	 node.organize(70, 50);
      		    	 binPanel.align();
      		    	 break;
      		     }
      		  }

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
        
       

    	options.add(helpBin);
    	options.add(helpGraph);
    	options.add(helpFlow);
    	options.add(addNBin);
    	options.add(addNGraph);
    	options.add(addNFlow);
    	options.add(disp);
    	options.add(flowScreen);
    	options.add(graphScreen);
    	options.add(binScreen);
    	options.add(OrganizeTree);
    	//initialize visible options to Binary Tree Screen defaults:
    	graphScreen.setVisible(true);
		flowScreen.setVisible(true);
		binScreen.setVisible(false);
		addNBin.setVisible(true);
		addNGraph.setVisible(false);
	    addNFlow.setVisible(false);
		helpBin.setVisible(true);
	    helpGraph.setVisible(false);
	    helpFlow.setVisible(false);
    	
    	bar.add(options);
    	frame.setJMenuBar(bar);
    	options.setVisible(true);
    	
        
        
        
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
     
        frame.setVisible(true); 

        binPanel.addNode("Tree Node", 200, 200);
        binPanel.getNodeList().get(0).setLevel(0);
        
        graphPanel.addNode("Graph Node", 200, 200);
        

    }
	
}
