import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class VisionMain {
    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("CS Vision");
        final JPanel overall = new JPanel();
        final CardLayout c1 = new CardLayout();
        overall.setLayout(c1);
        frame.add(overall);
        frame.setMinimumSize(new java.awt.Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        //frame.setResizable(false);
        try {
        	
        final BinaryPanel binPanel = new BinaryPanel();
        final GraphPanel graphPanel = new GraphPanel();
        final FlowPanel flowPanel = new FlowPanel();
        final LogicPanel logPanel = new LogicPanel();
        overall.add(binPanel, "Binary Tree Builder");
        overall.add(graphPanel, "Graph Builder");
        overall.add(flowPanel, "Flow Chart Builder");
        overall.add(logPanel, "Digital Electronics Builder");
        JMenuBar bar = new JMenuBar();
        JMenu options = new JMenu("Actions");
        JMenu nav = new JMenu("Navigation");
        //Digital Electronics Main Menu Items:
        final JMenuItem addNLog = new JMenuItem("Add new Logic gate or input"); //add new flow structure
        final JMenuItem  helpLog = new JMenuItem("Help"); // help screen for logic space
        final JMenuItem logScreen = new JMenuItem("Go to \"Digital Electronics\" work space");//go to Digital electronics space
        final JMenuItem logClear = new JMenuItem("Clear Workspace");//clear
        //----------------------------------------------------------------------------------------------
        
        //Flow Chart Main Menu Items:
        final JMenuItem addNFlow = new JMenuItem("Add new Flow Structure"); //add new flow structure
        final JMenuItem helpFlow = new JMenuItem("Help"); //help screen for flow
        final JMenuItem flowScreen = new JMenuItem("Go to \"Flow Chart\" work space");//go to flow space
        final JMenuItem flowClear = new JMenuItem("Clear Workspace");//clear
        //----------------------------------------------------------------------------------------------
        
        //Graph Main Menu Items:
        final JMenuItem helpGraph = new JMenuItem("Help"); //help screen for graph
        final JMenuItem addNGraph = new JMenuItem("Add new Node"); //add graph node
        final JMenuItem graphScreen = new JMenuItem("Go to \"Graph\" work space");//go to graph space
        final JMenuItem setUndirected = new JMenuItem("make graph non-directional");//set all edges to undirected 
        final JMenuItem adjMatrix = new JMenuItem("Generate Adjacency Matrix");//clear
        final JMenuItem graphClear = new JMenuItem("Clear Workspace");//clear
        final JMenuItem genGraph = new JMenuItem("Generate Graph from Adjacency matrix");
        //----------------------------------------------------------------------------------------------
         
        //Binary Tree Main Menu Items:
        final JMenuItem OrganizeTree = new JMenuItem("Organize Binary Tree"); //organizes tree in level order
        final JMenuItem helpBin = new JMenuItem("Help"); // help screen for tree
        final JMenuItem addNBin = new JMenuItem("Add new Node"); // adds a new tree node
        final JMenuItem binScreen = new JMenuItem("Go to \"Binary Tree\" work space");// go to binary tree space
        final JMenuItem binDisp = new JMenuItem("Change Display"); // change display mode of nodes (name, value, level)
        final JMenuItem binClear = new JMenuItem("Clear Workspace");//clear
        final JMenuItem binTraverse = new JMenuItem("Traverse Tree");//traverse tree with a slected mode of traversal
        //----------------------------------------------------------------------------------------------
        
        logScreen.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 

      		  c1.show(overall, "Digital Electronics Builder");
      		  binClear.setVisible(false);
      		  logClear.setVisible(true);
      		  flowClear.setVisible(false);
      		  graphClear.setVisible(false);
      		  logScreen.setVisible(false);
      		  helpLog.setVisible(true);
      		  addNLog.setVisible(true);
      		  graphScreen.setVisible(true);
      		  flowScreen.setVisible(true);
      		  binScreen.setVisible(true);
      		  addNBin.setVisible(false);
      		  addNGraph.setVisible(false);
      		  addNFlow.setVisible(false);
      		  helpBin.setVisible(false);
      		  helpGraph.setVisible(false);
      		  helpFlow.setVisible(false);
      		  OrganizeTree.setVisible(false);
      		  binDisp.setVisible(false);
      		  setUndirected.setVisible(false);
      		  adjMatrix.setVisible(false);
      		  genGraph.setVisible(false);
      		  binTraverse.setVisible(false);
      	  } 
      	} );
      
        
        graphScreen.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

        		  c1.show(overall, "Graph Builder");
        		  binClear.setVisible(false);
          		  logClear.setVisible(false);
          		  flowClear.setVisible(false);
          		  graphClear.setVisible(true);
        		  logScreen.setVisible(true);
          		  helpLog.setVisible(false);
          		  addNLog.setVisible(false);
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
        		  binDisp.setVisible(false);
        		  setUndirected.setVisible(true);
        		  adjMatrix.setVisible(true);
        		  genGraph.setVisible(true);
        		  binTraverse.setVisible(false);
        	  } 
        	} );
        
        
        flowScreen.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

        		  c1.show(overall, "Flow Chart Builder");
        		  binClear.setVisible(false);
          		  logClear.setVisible(false);
          		  flowClear.setVisible(true);
          		  graphClear.setVisible(false);
        		  logScreen.setVisible(true);
          		  helpLog.setVisible(false);
          		  addNLog.setVisible(false);
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
        		  binDisp.setVisible(false);
        		  setUndirected.setVisible(false);
        		  adjMatrix.setVisible(false);
        		  genGraph.setVisible(false);
        		  binTraverse.setVisible(false);
        	  } 
        	} );
        
        binScreen.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 

      		  c1.show(overall, "Binary Tree Builder");
      		  binClear.setVisible(true);
      		  logClear.setVisible(false);
      		  flowClear.setVisible(false);
      		  graphClear.setVisible(false);
      		  logScreen.setVisible(true);
      		  helpLog.setVisible(false);
      		  addNLog.setVisible(false);
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
			  binDisp.setVisible(true);
			  setUndirected.setVisible(false);
			  adjMatrix.setVisible(false);
			  genGraph.setVisible(false);
			  binTraverse.setVisible(true);
      	  } 
      	} );
        
        
        
        binTraverse.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		// Option Operators for drop-down menu
                  String[] options = {"Preorder", "Inorder", "Postorder", "Level order" };
                  String selection = (String) JOptionPane.showInputDialog(null, "Choose traversal Technique", "Menu",
                          JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                  // Change color based on choice
                  if(selection != null)
                  {
                  switch (selection) {
                      case "Preorder":
					try {
						binPanel.traverse("pre");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                          break;
                      case "Inorder":
					try {
						binPanel.traverse("in");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                          break;
                      case "Postorder":
					try {
						binPanel.traverse("post");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                          break;
                      case "Level order":
					try {
						binPanel.traverse("lvl");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                          break;
                      
                     }
                  }
        		 
                 binPanel.repaint();
                  
        	  }
        	} );
        
        genGraph.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  
    		  
    		  int n = 0;
    		  while (true) {
                  try {
                	  String choice = JOptionPane.showInputDialog(null,
                              "How many verticies are in your graph?", "generate graph",
                              JOptionPane.YES_NO_OPTION);
                	  if(choice == null)
                	  {
                		  break;
                	  }
                      n = Integer.parseInt(choice);
                      if(n <= 0)
                      {
                    	  throw new Exception();
                      }
                      graphPanel.inputGrid(n);
                      break;
                  } catch (Exception e1) {
                      JOptionPane.showMessageDialog(null, "not a valid number try again", "error", JOptionPane.PLAIN_MESSAGE);
                      
                  }
              }
              
    		  
              
              graphPanel.repaint();
              
    	  }
    	} );
    
        
        setUndirected.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
          		  
        		  int choice = JOptionPane.showConfirmDialog(null,
                          "Are you sure you want to set all edges to undirected? ", "Confirm",
                          JOptionPane.YES_NO_OPTION);

                  if (choice == 0) 
                  {
                	  for(LineComp line : graphPanel.getLineList())
                	  {
                		  line.setDirected(false);
                		  
                      }
          		  
                  } 
                  
                  graphPanel.repaint();
                  
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
        
        addNGraph.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 

      		  String name = "";
      		  name = JOptionPane.showInputDialog("name for new node:");
      		  
      		  if(name != null)
      		  graphPanel.addNode(name, 100, 100);
      		  graphPanel.repaint(); 
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
      
        
        binDisp.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 

          		  String disp = "";
          		  String[] options = {"names", "values", "levels"};
          		  disp = (String) JOptionPane.showInputDialog(null, "Choose Display Type", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
          		  if(disp != null) {
          		  //change color based on choice
          		  binPanel.changeDisplay(disp);
          		  }
        	  } 
        	} );
        
        addNLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                // Option Operators for drop-down menu
                String[] options = {"NOT", "AND", "OR", "XOR", "NOR", "NAND", "XNOR"};
                String selection = (String) JOptionPane.showInputDialog(null, "Choose logic gate", "Menu",
                        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                // Change color based on choice
                if(selection != null)
                {
                switch (selection) {
                    case "NOT":
                        logPanel.addLog();
                        break;
                    case "AND":
                        logPanel.addLog(DragNode.AND);
                        break;
                    case "OR":
                        logPanel.addLog(DragNode.OR);
                        break;
                    case "XOR":
                        logPanel.addLog(DragNode.XOR);
                        break;
                    case "NOR":
                        logPanel.addLog(DragNode.NOR);
                        break;
                    case "NAND":
                        logPanel.addLog(DragNode.NAND);
                        break;
                    case "XNOR":
                        logPanel.addLog(DragNode.XNOR);
                        break;
                        
                    
                   }
                }
                logPanel.repaint();
                logPanel.align();
            }
        });

        binClear.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
        		  
      		  int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to clear this work space?", "Confirm",
                        JOptionPane.YES_NO_OPTION);

                if (choice == 0) 
                {
              	  binPanel.getLineList().clear();
              	  binPanel.getNodeList().clear();
        		  
                } 
                
                binPanel.repaint();
                
      	  }
      	} );
        
   
        adjMatrix.addActionListener(new ActionListener() {

        	
			public void actionPerformed(ActionEvent e) {
				graphPanel.generateMatrix();
				
			}
            
               
            
        });
        
        logClear.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
          		  
        		  int choice = JOptionPane.showConfirmDialog(null,
                          "Are you sure you want to clear this work space?", "Confirm",
                          JOptionPane.YES_NO_OPTION);

                  if (choice == 0) 
                  {
                	  logPanel.getLineList().clear();
                	  logPanel.getNodeList().clear();
          		  
                  } 
                  
                  logPanel.repaint();
                  
        	  }
        	} );
        
        graphClear.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
          		  
        		  int choice = JOptionPane.showConfirmDialog(null,
                          "Are you sure you want to clear this work space?", "Confirm",
                          JOptionPane.YES_NO_OPTION);

                  if (choice == 0) 
                  {
                	  graphPanel.getLineList().clear();
                	  graphPanel.getNodeList().clear();
          		  
                  } 
                  
                  graphPanel.repaint();
                  
        	  }
        	} );
        
        
    	nav.add(flowScreen);
    	nav.add(graphScreen);
    	nav.add(binScreen);
    	nav.add(logScreen);
    	options.add(helpBin);
    	options.add(helpGraph);
    	options.add(helpFlow);
    	options.add(helpLog);
    	options.add(addNBin);
    	options.add(addNGraph);
    	options.add(addNFlow);
    	options.add(addNLog);
    	options.add(binDisp);
    	options.add(OrganizeTree);
    	options.add(setUndirected);
    	options.add(binClear);
    	options.add(logClear);
    	options.add(graphClear);
    	options.add(flowClear);
    	options.add(adjMatrix);
    	options.add(genGraph);
    	options.add(binTraverse);
    
    	  
    	//initialize visible options to Binary Tree Screen defaults:
    	binClear.setVisible(true);
 		logClear.setVisible(false);
 		flowClear.setVisible(false);
 		graphClear.setVisible(false);
 		logScreen.setVisible(true);
 		helpLog.setVisible(false);
 		addNLog.setVisible(false);
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
	  	binDisp.setVisible(true);
	  	setUndirected.setVisible(false);
	  	adjMatrix.setVisible(false);
		genGraph.setVisible(false);
		binTraverse.setVisible(true);
		bar.add(nav);
		//bar.add(new JSeparator(SwingConstants.VERTICAL));
    	bar.add(options);
    	frame.setJMenuBar(bar);
    	nav.setVisible(true);
    	options.setVisible(true);
    	
        
    	ImageIcon img = new ImageIcon("CS_Vision_Icon.jpg");
        frame.setIconImage(img.getImage());
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
     
        frame.setVisible(true); 

        binPanel.addNode("Tree Node", 200, 200);
        binPanel.getNodeList().get(0).setLevel(0);
        
        graphPanel.addNode("Graph Node", 200, 200);
      

    }
    	catch(Exception e)
    	{
    		JOptionPane.showMessageDialog(null, "An error occured, the application will now close " +'\n'+ "Error: " + e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE );
    		frame.dispose();
    	}
    
	
}
}
