import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LogicPanel extends JPanel {
    private List<DragNode> nodes;
    private List<LineComp> lines;
    private DragNode selectedNode;
    private LineComp selectedLine;
    private Point dragOffset;
    // Pop-up menu for editing Nodes
    public JPopupMenu menu = new JPopupMenu("Menu");
    //Pop-up menu for editing Lines
    public JPopupMenu menuLine = new JPopupMenu("Menu");
    public JMenuItem c = new JMenuItem("Color");
    public JMenuItem m = new JMenuItem("merge node");
    public JMenuItem r = new JMenuItem("Rename Input");
    public JMenuItem dC = new JMenuItem("Delete Connection");
 
    public JMenuItem aC = new JMenuItem("Add Non-directed Edge");
    public JMenuItem dN = new JMenuItem("Delete Node");
    public JMenuItem v = new JMenuItem("Set Node Value");
    public JMenuItem aLI = new JMenuItem("Set Node as input for another logic structure");
    public JMenuItem aLO = new JMenuItem("Set Node as output for another logic structure");
    public JMenuItem E = new JMenuItem("Evaluate to this output");
    public JMenuItem ET = new JMenuItem("Generate Truth Table at this Output");
    
    public JMenuItem cD =  new JMenuItem("Toggle display (value or name)");
    
    public JMenuItem cL = new JMenuItem("Set Edge Color");
    public JMenuItem sT = new JMenuItem("Set Edge Thickness");

    public LogicPanel() {
        // List of all nodes and lines currently on the workspace
        nodes = new ArrayList<>();
        lines = new ArrayList<>();

        // Options in the pop-up menu for Nodes
        menu.add(c);
        menu.add(r);
        menu.add(dC);
        menu.add(dN);
        menu.add(v);
        menu.add(aLI);
        menu.add(aLO);
        menu.add(cD);
        menu.add(m);
        menu.add(E);
        menu.add(ET);
        
        //Edge menu
        menuLine.add(cL);
        menuLine.add(sT);

        
       
        //renaming a node
        r.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    
        		  menu.setVisible(false);
	
        		  String name = selectedNode.getName();
        		  name = JOptionPane.showInputDialog("rename the node:", name);
        		  selectedNode.setName(name);
        		  
        		  if(!name.equals(null))
        		  repaint(); 
	
        	  } 
        	} );
        
        // Re-coloring a node
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                System.out.println(selectedNode.dumpChildren());
                System.out.println(selectedNode.dumpParent());
                // Option colors for drop-down menu
                String[] options = {"White", "Cyan", "Green", "Yellow", "Magenta", "Orange", "Gray"};
                DragNode node = selectedNode;
                String selection = (String) JOptionPane.showInputDialog(null, "Choose color", "Menu",
                        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                // Change color based on choice
                if(selection != null)
                {
                switch (selection) {
                    case "White":
                        selectedNode.setCol(Color.white);
                        break;
                        
                    case "Cyan":
                        selectedNode.setCol(Color.cyan);
                        break;
                        
                    case "Green":
                        selectedNode.setCol(Color.green);
                        break;
                        
                    case "Yellow":
                        selectedNode.setCol(Color.yellow);
                        break;
                        
                    case "Magenta":
                        selectedNode.setCol(Color.magenta);
                        break;
                        
                    case "Orange":
                    	selectedNode.setCol(Color.orange);
                        break;
                        
                    case "Gray":
                        selectedNode.setCol(Color.gray);
                        break;
                   }
                }
                repaint();
            }
        });

       

        // Deleting Node, and all connections this node had with other nodes
        dN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete " + selectedNode.getName() + "?", "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (choice == 0) {
                    for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).getStartNode().equals(selectedNode)
                                || lines.get(i).getEndNode().equals(selectedNode)) {
                            lines.remove(i);
                            i--;
                        }
                    }
                    nodes.remove(selectedNode);

                    repaint();
                }
            }
        });

        cD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);

                if (selectedNode.getDisplay().equals("values")) 
                    selectedNode.setDisplay("names");
                
                
                else
                	selectedNode.setDisplay("values");
                
                repaint();
            }
        });
        //setting value of node
        v.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);

                String value = selectedNode.getValue();
                value = JOptionPane.showInputDialog("Set Node Value:", value);
               
              
                if (!(value == null)) {
                	if((value.equals("1") || value.equals("0"))) {
                		 selectedNode.setValue(value);
                		 for(DragNode node: nodes)
                		 {
                			 if(node.getType() == DragNode.OUT)
                				 node.setDisplay("names");
                		 }
                		   repaint();
                	}
                 
                	
                	else
                    JOptionPane.showMessageDialog(null, "An input to a logic gate must either be \"0\"(false) or \"1\"(true)", "non-boolean input", JOptionPane.ERROR_MESSAGE);
                	
                }
            }
        });
        
        //adding another Logic structure using selected as input
        aLI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
           
                        // Option Operators for drop-down menu
                        String[] options = {"NOT", "AND", "OR", "XOR", "NOR", "NAND", "XNOR"};
                        String selection = (String) JOptionPane.showInputDialog(null, "Choose logic gate", "Menu",
                                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        
                        if(selection != null)
                        {
                         switch (selection) {
                            case "NOT":
                                addLogIn(selectedNode);
                                
                                break;
                            case "AND":
                                addLogIn(selectedNode, DragNode.AND);
                                break;
                            case "OR":
                                addLogIn(selectedNode, DragNode.OR);
                                break;
                            case "XOR":
                                addLogIn(selectedNode, DragNode.XOR);
                                break;
                            case "NOR":
                                addLogIn(selectedNode, DragNode.NOR);
                                break;
                            case "NAND":
                                addLogIn(selectedNode, DragNode.NAND);
                                break;
                            case "XNOR":
                                addLogIn(selectedNode, DragNode.XNOR);
                                break;
                                
                            
                           }
                        }
                        repaint();
                        align();
            }
            
        });
        
        aLO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);

                
                
                        // Option Operators for drop-down menu
                        String[] options = {"NOT", "AND", "OR", "XOR", "NOR", "NAND", "XNOR"};
                        String selection = (String) JOptionPane.showInputDialog(null, "Choose logic gate", "Menu",
                                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        
                        if(selection != null)
                        {
                         switch (selection) {
                            case "NOT":
                                addLogOut(selectedNode);
                                break;
                            case "AND":
                                addLogOut(selectedNode, DragNode.AND);
                                break;
                            case "OR":
                                addLogOut(selectedNode, DragNode.OR);
                                break;
                            case "XOR":
                                addLogOut(selectedNode, DragNode.XOR);
                                break;
                            case "NOR":
                                addLogOut(selectedNode, DragNode.NOR);
                                break;
                            case "NAND":
                                addLogOut(selectedNode, DragNode.NAND);
                                break;
                            case "XNOR":
                                addLogOut(selectedNode, DragNode.XNOR);
                                break;
                                
                            
                           }
                        }
                        repaint();
                        align();
            }
            
        });
        
      
        
        cL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuLine.setVisible(false);
                // Option colors for drop-down menu
                String[] options = {"Black", "Cyan", "Green", "Yellow", "Magenta", "Orange", "Gray"};
                LineComp line = selectedLine;
                String selection = (String) JOptionPane.showInputDialog(null, "Choose color", "Menu",
                        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                // Change color based on choice
                if(selection != null)
                {
                switch (selection) {
                    case "Black":
                        selectedLine.setCol(Color.black);
                        break;
                        
                    case "Cyan":
                        selectedLine.setCol(Color.cyan);
                        break;
                        
                    case "Green":
                        selectedLine.setCol(Color.green);
                        break;
                        
                    case "Yellow":
                        selectedLine.setCol(Color.yellow);
                        break;
                        
                    case "Magenta":
                        selectedLine.setCol(Color.magenta);
                        break;
                        
                    case "Orange":
                    	selectedNode.setCol(Color.orange);
                        break;
                        
                    case "Gray":
                        selectedLine.setCol(Color.gray);
                        break;
                   }
                }
                repaint();
            }
        });

        
        sT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	menu.setVisible(false);
            	 String[] options = {"Light", "Bold", "Ultra-Bold"};
                String directed = "";
                directed = (String) JOptionPane.showInputDialog(null, "Choose Edge Specifics", "Menu",
                        JOptionPane.OK_OPTION, null, options, options[0]);
                if (directed != null)
                {
                	switch(directed)
                	{
                	 case "Light":
                		 selectedLine.setThick(1);
                		 break;
                	
                	 case "Bold":
                		 selectedLine.setThick(3);
                		 break;
                		 
                	 case "Ultra-Bold":
                		 selectedLine.setThick(5);
                		 break;
                	
                	
                	
                	}
                   
                    
                }
                repaint();
                
            }
        });
        
        m.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                if(nodes.size() > 1 && selectedNode.getType() == DragNode.IN)
                {
                  final DragNode startNode = selectedNode; 
                  JOptionPane.showMessageDialog(null, "Select Node to merge with (must be an Output or an Input node)", "Merge Node", JOptionPane.PLAIN_MESSAGE);

                  addMouseListener(new MouseAdapter() {
                     public void mouseClicked(MouseEvent e) {
                        selectNode(e.getX(), e.getY());
                        
                        if(selectedNode != null ) {
                        	
                       
                         	
                        		if(selectedNode.getType() == DragNode.IN || selectedNode.getType() == DragNode.OUT ) {
                        			 if(DragNode.validMerge(startNode, selectedNode)) {
                        			
                        			if(selectedNode.equals(startNode))	
                        			{
                        				removeMouseListener(this);
                        				repaint();
                        			}
                        			
                        			else
                        			{
                        				
                        				for(LineComp line: lines)
                        				{
                        					if(line.getStartNode().equals(startNode))
                        					{
                        						line.setStartNode(selectedNode);
                        						selectedNode.getChildren().add(line.getEndNode());
                        						line.getEndNode().getParent().add(selectedNode);
                        					}
                        					
                        					else if(line.getEndNode().equals(startNode))
                        					{
                        						line.setEndNode(selectedNode);
                        						selectedNode.getParent().add(line.getEndNode());
                        						line.getEndNode().getChildren().add(selectedNode);
                        						
                        					}
                        					
                        				}
                        				for(DragNode C: startNode.getChildren())
                        				{
                        					C.getParent().remove(startNode);
                        				}
                        				for(DragNode P: startNode.getParent())
                        				{
                        					P.getChildren().remove(startNode);
                        				}
                        				
                        				nodes.remove(startNode);
                        				align();
                        				repaint();
                        				
                        			}
                        		
                        			
                        		}
                        		
                        			 else {
                                     	removeMouseListener(this);
                                     	JOptionPane.showConfirmDialog(null, "merge failed, value of output cannot depend on itself", "Merge error" , JOptionPane.PLAIN_MESSAGE);
                                     	repaint();
                                         }
                        	}
                        		
                        		else
                            	{
                            		removeMouseListener(this);
                            		JOptionPane.showConfirmDialog(null, "not mergable, selected node is not an input or output node", "Merge error", JOptionPane.PLAIN_MESSAGE);
                            	}
                            		
                        		
                        	
                        
                        
                        removeMouseListener(this);
                        }
                        
                      }
                        
                });

                }
               
            }
        });

        
        E.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                
                
                selectedNode.evaluate();
            
                repaint();
                JOptionPane.showConfirmDialog(null, "this node evaluated to: " + DragNode.convert(Integer.parseInt(selectedNode.getValue())),"Evaluation Successful" ,JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
           
            }
        });
        
        
        
        ET.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);

                ArrayList<DragNode> ins = new ArrayList<DragNode>();
                for (DragNode node : nodes) {
                    if (node.getType() == DragNode.IN) {
                        ins.add(node);
                    }
                }

                DragNode[] inputs = new DragNode[ins.size()];
                String[] names = new String[ins.size()];
                for (int i = 0; i < ins.size(); i++) {
                    inputs[i] = ins.get(i);
                    names[i] = ins.get(i).getName();
                }

                ArrayList<String> combos = (ArrayList<String>) generateBinaryCombinations(inputs.length);
                String[][] data = new String[combos.size()][inputs.length + 1];
                for (int i = 0; i < combos.size(); i++) {
                    for (int j = 0; j < inputs.length; j++) {
                        data[i][j] = "" + DragNode.convert(Integer.parseInt("" + combos.get(i).charAt(j)));
                        inputs[j].setValue(combos.get(i).charAt(j) + "");
                    }
                    selectedNode.evaluate();
                    data[i][inputs.length] = "" + DragNode.convert(Integer.parseInt(selectedNode.getValue()));
                }

                // Create an array of column names
                String[] columnNames = new String[inputs.length + 1];
                for (int i = 0; i < inputs.length; i++) {
                    columnNames[i] = names[i];
                }
                columnNames[inputs.length] = selectedNode.getName();

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);

                // Creating the JFrame and adding the JScrollPane to it
                JFrame tableFrame = new JFrame("Evaluation Results");
                tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                tableFrame.add(scrollPane); // Adding the JScrollPane to the JFrame

                tableFrame.pack(); // Adjusts the JFrame size to fit its components
                tableFrame.setVisible(true); // Make the JFrame visible

                repaint();
                JOptionPane.showConfirmDialog(null, "This node evaluated to: " + selectedNode.convert(Integer.parseInt(selectedNode.getValue())), "Evaluation Successful", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        });


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                selectNode(e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                selectedNode = null;
                selectedLine = null;
            }

            public void mouseClicked(MouseEvent e) {
                selectNode(e.getX(), e.getY());
                selectLine(e.getX(), e.getY());
                if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2) {
                    menu.setVisible(false);
                    menuLine.setVisible(false);
                }

                if (selectedNode != null) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                    	
                    	if(!(selectedNode.getType() == DragNode.IN))
                    	{
                    		v.setVisible(false);
                    		aLI.setVisible(false);
                    		aLO.setVisible(false);
                    		cD.setVisible(false);
                    		m.setVisible(false);
                    		E.setVisible(false);
                    		ET.setVisible(false);
                    		if(selectedNode.getType() == DragNode.OUT)
                        	{
                        		aLI.setVisible(true);
                        		E.setVisible(true);
                        		ET.setVisible(true);
                        	
                        	}
                    		 menu.show(null, selectedNode.getX(), selectedNode.getY());
                    	}
                    	else
                    	{
                    		v.setVisible(true);
                    		aLI.setVisible(true);
                    		aLO.setVisible(true);
                    		cD.setVisible(true);
                    		m.setVisible(true);
                    		E.setVisible(false);
                    		ET.setVisible(false);
                    		r.setVisible(true);
                        menu.show(null, selectedNode.getX(), selectedNode.getY());
                    	}
                    }
                }
                else if(selectedLine != null) {
                	menuLine.show(null, (selectedLine.getEndX() + selectedLine.getStX())/2, (selectedLine.getEndY() + selectedLine.getStY())/2 );
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (selectedNode != null) {
                    int newX = e.getX() - dragOffset.x;
                    int newY = e.getY() - dragOffset.y;
                    selectedNode.setX(newX);
                    selectedNode.setY(newY);
                   
                 for(LineComp line : lines) {   
                    if(line.getStartNode().equals(selectedNode)||line.getEndNode().equals(selectedNode))
              	  {
              		  int p1 = line.Dist(line.getStartNode().getX() + 7, line.getStartNode().getY() + 7, line.getEndNode().getX() + 43, line.getEndNode().getY() + 43);
              		  int p2 = line.Dist(line.getStartNode().getX(), line.getStartNode().getY() + 25, line.getEndNode().getX() + 50, line.getEndNode().getY() + 25);
              		  int p3 = line.Dist(line.getStartNode().getX() + 7, line.getStartNode().getY() + 43, line.getEndNode().getX() + 43, line.getEndNode().getY() + 7);
              		  int p4 = line.Dist(line.getStartNode().getX() + 25, line.getStartNode().getY() + 50, line.getEndNode().getX() + 25, line.getEndNode().getY());
              		  int p5 = line.Dist(line.getStartNode().getX() + 43, line.getStartNode().getY() + 43, line.getEndNode().getX() + 7, line.getEndNode().getY() + 7);
              		  int p6 = line.Dist(line.getStartNode().getX() + 50, line.getStartNode().getY() + 25, line.getEndNode().getX() + 0, line.getEndNode().getY() + 25);
              		  int p7 = line.Dist(line.getStartNode().getX() + 43, line.getStartNode().getY() + 7, line.getEndNode().getX() + 7, line.getEndNode().getY() + 43);
              		  int p8 = line.Dist(line.getStartNode().getX() + 25, line.getStartNode().getY(), line.getEndNode().getX() + 25, line.getEndNode().getY() + 50);
              		  int dist = line.Dist(line.getStartNode().getX(), line.getStartNode().getY(), line.getEndNode().getX(), line.getEndNode().getY());
              		  
              		  int pf = Math.min(Math.min(Math.min(Math.min(dist, p1), Math.min(p2, p3)),Math.min(Math.min(p4, p5), Math.min(p6, p7))), p8);
              		 
              		  if(pf == p1)
              			  line.setPreset(1);
              		  else if( pf == p2)
              			  line.setPreset(2);
              		  else if( pf == p3)
              			  line.setPreset(3);
              		  else if( pf == p4)
              			  line.setPreset(4);
              		  else if( pf == p5)
              			  line.setPreset(5);
              		  else if( pf == p6)
              			  line.setPreset(6);			  
              		  else if( pf == p7)
              			  line.setPreset(7);				  
              		  else if(pf == p8)
              			  line.setPreset(8);
              			  
              	  }
                 }
                    repaint();
                }
            }
        });
    }

    public void addNode(String name, int x, int y) {
        nodes.add(new DragNode(name, x, y));
        repaint();
    }
    
//    public void addNode(DragNode startNode, String name, int x, int y) {
//        nodes.add(new DragNode(name, x, y));
//        lines.add(new LineComp("", startNode.getX() + 25, startNode.getY() + 50, nodes.get(nodes.size()-1).getX() + 25, nodes.get(nodes.size()-1).getY()));
//        lines.get(lines.size()-1).setEndNode(nodes.get(nodes.size()-1));
//        lines.get(lines.size()-1).setStartNode(startNode);
//        nodes.get(nodes.size()-1).setDisplay(startNode.getDisplay());
//        nodes.get(nodes.size()-1).setLevel(startNode.getLevel()+1);
//        startNode.getChildren().add(nodes.get(nodes.size()-1));
//        nodes.get(nodes.size()-1).getChildren().add(startNode);
//        repaint();
//    }
    
    public void addNode(DragNode startNode, String name, int x, int y, String direction) {
        nodes.add(new DragNode(name, x, y));
        DragNode endNode = nodes.get(nodes.size()-1);
        nodes.get(nodes.size()-1).setDisplay(startNode.getDisplay());
        nodes.get(nodes.size()-1).setLevel(startNode.getLevel()+1);
        
        if(direction.equals("toNew"))
        {
        	addEdge(startNode, endNode, true);
        	
        }
        
        else if (direction.equals("toOld"))
        {
        	addEdge(endNode, startNode, true);
        	
        }
        
        repaint();
    }

    public void addEdge(DragNode startNode, DragNode endNode, boolean isDirected) {
    	if(isDirected)
    	{
        lines.add(new LineComp("", startNode.getX()+25, startNode.getY()+50, endNode.getX()+25, endNode.getY()));
        lines.get(lines.size()-1).setDirected(isDirected);
        lines.get(lines.size()-1).setEndNode(endNode);
        lines.get(lines.size()-1).setStartNode(startNode);
        startNode.getChildren().add(endNode);
        endNode.getParent().add(startNode);
        
    	}
    	
    	else
    	{
    		lines.add(new LineComp("", startNode.getX()+25, startNode.getY()+50, endNode.getX()+25, endNode.getY()));
            lines.get(lines.size()-1).setDirected(isDirected);
            lines.get(lines.size()-1).setEndNode(endNode);
            lines.get(lines.size()-1).setStartNode(startNode);
            startNode.getChildren().add(endNode);
            endNode.getChildren().add(startNode);
    	}
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (DragNode node : nodes) {
        	if(node.getX() >= 1485)
        	{
        		node.setX(1485);
        	}
        	else if(node.getX() <= 0)
        	{
        		node.setX(0);
        	}
        	
        	if(node.getY() >= 720)
        	{
        		node.setY(720);
        	}
        	else if(node.getY() <= 0)
        	{
        		node.setY(0);
        	}
            int x = node.getX();
            int y = node.getY();
            g2.setColor(node.getColor());
            g2.fillOval(x, y, 50, 50);
            g2.setColor(Color.BLACK);
            if(node.getBold())
            {
            	g2.setStroke(new BasicStroke(3));
            }
            g2.drawOval(x, y, 50, 50);
            
            if(node.getDisplay().equals("values"))
            g2.drawString(node.getValue(), x + 20, y + 30);
            	
            else
            g2.drawString(node.getName(), x + 20, y + 30);
            	
        }

        for (LineComp line : lines) {
            g2.setColor(line.getColor());
            int startX = 0;
            int startY = 0;
            int endX = 0;
            int endY = 0;

            switch(line.getPreset()) {
        	
        	case 1:
        	    startX = line.getStartNode().getX() + 7;
        	    startY = line.getStartNode().getY() + 7;
        		endX = line.getEndNode().getX() + 43;
        		endY = line.getEndNode().getY() + 43;
        		break;
        		
        	case 2:
        		startX = line.getStartNode().getX();
        	    startY = line.getStartNode().getY() + 25;
        		endX = line.getEndNode().getX() + 50;
        		endY = line.getEndNode().getY() + 25;
        		break;
        		
        	case 3:
        		startX = line.getStartNode().getX() + 7;
        	    startY = line.getStartNode().getY() + 43;
        		endX = line.getEndNode().getX() + 43;
        		endY = line.getEndNode().getY() + 7;
        		break;
        		
        	case 4:
        		startX = line.getStartNode().getX() + 25;
        		startY = line.getStartNode().getY() + 50;
        		endX = line.getEndNode().getX() + 25;
        		endY = line.getEndNode().getY();
        		break;
        		
        	case 5:
        		startX = line.getStartNode().getX() + 43;
        	    startY = line.getStartNode().getY() + 43;
        		endX = line.getEndNode().getX() + 7;
        		endY = line.getEndNode().getY() + 7;
        		break;
        		
        	case 6:
        		startX = line.getStartNode().getX() + 50;
        	    startY = line.getStartNode().getY() + 25;
        		endX = line.getEndNode().getX();
        		endY = line.getEndNode().getY() + 25;
        		break;
        		
        	case 7:
        		startX = line.getStartNode().getX() + 43;
        	    startY = line.getStartNode().getY() + 7;
        		endX = line.getEndNode().getX() + 7;
        		endY = line.getEndNode().getY() + 43;
        		break;
        		
        	case 8:
        		startX = line.getStartNode().getX() + 25;
        	    startY = line.getStartNode().getY();
        		endX = line.getEndNode().getX() + 25;
        		endY = line.getEndNode().getY() + 50;
        		break;
            
        	}
            // Draw a line between nodes
            g2.setStroke(new BasicStroke(line.getThick()));
            g2.drawLine(startX, startY, endX, endY);
            

            // Draw an arrow at the end of the line if it is directed
            if (line.isDirected()) {
                drawArrow(g2, startX, startY, endX, endY);
            }
        }
    }

    private void drawArrow(Graphics2D g2, int startX, int startY, int endX, int endY) {
        double arrowAngle = Math.toRadians(20); // Angle of the arrow wings
        int arrowLength = 20; // Length of the arrow wings

        double angle = Math.atan2(endY - startY, endX - startX);
        double x1 = endX - arrowLength * Math.cos(angle - arrowAngle);
        double y1 = endY - arrowLength * Math.sin(angle - arrowAngle);
        double x2 = endX - arrowLength * Math.cos(angle + arrowAngle);
        double y2 = endY - arrowLength * Math.sin(angle + arrowAngle);

        g2.drawLine(endX, endY, (int) x1, (int) y1);
        g2.drawLine(endX, endY, (int) x2, (int) y2);
    }

    private void selectNode(int x, int y) {
        for (DragNode node : nodes) {
            int nodeX = node.getX();
            int nodeY = node.getY();
            if (x >= nodeX && x <= nodeX + 50 && y >= nodeY && y <= nodeY + 50) {
                selectedNode = node;
                dragOffset = new Point(x - nodeX, y - nodeY);
                break;
            } else {
                selectedNode = null;
            }
        }
    }
    private void selectLine(int x, int y) {

        // Check if a line is selected
        for (LineComp line : lines) {
            if (line.isInsideLine(x, y)) {
                selectedLine = line;
                break;
            } 
            else {
                selectedLine = null;
            }
        }
    }
    
    public void align()
    {
    	for(LineComp line : lines) {
    	int p1 = line.Dist(line.getStartNode().getX() + 7, line.getStartNode().getY() + 7, line.getEndNode().getX() + 43, line.getEndNode().getY() + 43);
		  int p2 = line.Dist(line.getStartNode().getX(), line.getStartNode().getY() + 25, line.getEndNode().getX() + 50, line.getEndNode().getY() + 25);
		 int p3 = line.Dist(line.getStartNode().getX() + 7, line.getStartNode().getY() + 43, line.getEndNode().getX() + 43, line.getEndNode().getY() + 7);
		  int p4 = line.Dist(line.getStartNode().getX() + 25, line.getStartNode().getY() + 50, line.getEndNode().getX() + 25, line.getEndNode().getY());
		  int p5 = line.Dist(line.getStartNode().getX() + 43, line.getStartNode().getY() + 43, line.getEndNode().getX() + 7, line.getEndNode().getY() + 7);
		  int p6 = line.Dist(line.getStartNode().getX() + 50, line.getStartNode().getY() + 25, line.getEndNode().getX() + 0, line.getEndNode().getY() + 25);
		 int p7 = line.Dist(line.getStartNode().getX() + 43, line.getStartNode().getY() + 7, line.getEndNode().getX() + 7, line.getEndNode().getY() + 43);
		  int p8 = line.Dist(line.getStartNode().getX() + 25, line.getStartNode().getY(), line.getEndNode().getX() + 25, line.getEndNode().getY() + 50);
		  int dist = line.Dist(line.getStartNode().getX(), line.getStartNode().getY(), line.getEndNode().getX(), line.getEndNode().getY());
		  
		  int pf = Math.min(Math.min(Math.min(Math.min(dist, p1), Math.min(p2, p3)),Math.min(Math.min(p4, p5), Math.min(p6, p7))), p8);
		 
		  if(pf == p1)
			  line.setPreset(1);
		  else if( pf == p2)
			  line.setPreset(2);
		  else if( pf == p3)
			  line.setPreset(3);
		  else if( pf == p4)
 			  line.setPreset(4);
 		  else if( pf == p5)
			  line.setPreset(5);
 		  else if( pf == p6)
  			  line.setPreset(6);			  
		  else if( pf == p7)
			  line.setPreset(7);				  
		  else if(pf == p8)
			  line.setPreset(8);
			  
	  }
    }
    //getter method for the list of nodes in the workspace
    public java.util.List<DragNode> getNodeList()
    {
    	return nodes;
    }
    
  //getter method for the list of lines in the workspace
    public java.util.List<LineComp> getLineList()
    {
    	return lines;
    }
    
    
    public void addLog()
    {
    	addNode("", 100, 100);
    	nodes.get(nodes.size()-1).setType(DragNode.NOT);
    	addNode(nodes.get(nodes.size()-1), "", 40, 100, "toOld");
    	nodes.get(nodes.size()-1).setType(DragNode.IN);
    	addNode(nodes.get(nodes.size()-2), "", 160, 100, "toNew");
    	nodes.get(nodes.size()-1).setType(DragNode.OUT);
    }
    
    public void addLog(int type)
    {
    	addNode("", 100, 100);
    	nodes.get(nodes.size()-1).setType(type);
    	addNode(nodes.get(nodes.size()-1), "", 40, 150, "toOld");
    	nodes.get(nodes.size()-1).setType(DragNode.IN);
    	addNode(nodes.get(nodes.size()-2), "", 40, 50, "toOld");
    	nodes.get(nodes.size()-1).setType(DragNode.IN);
    	addNode(nodes.get(nodes.size()-3), "", 160, 100, "toNew");
    	nodes.get(nodes.size()-1).setType(DragNode.OUT);
    }
    
    public void addLogIn(DragNode input)
    {
    
    	addNode("", input.getX(), input.getY() + 60);
    	nodes.get(nodes.size()-1).setType(DragNode.NOT);
    	addEdge(input, nodes.get(nodes.size()-1), true);
    	addNode(nodes.get(nodes.size()-1), "", nodes.get(nodes.size()-1).getX(), nodes.get(nodes.size()-1).getY() + 60, "toNew");
    	nodes.get(nodes.size()-1).setType(DragNode.OUT);
    	
    
    }
    	
    
    public void addLogIn(DragNode input, int type)
    {
    	
    	addNode("", input.getX() + 60, input.getY() + 50);
    	nodes.get(nodes.size()-1).setType(type);
    	addEdge(input, nodes.get(nodes.size()-1), true);
    	addNode(nodes.get(nodes.size()-1), "", nodes.get(nodes.size()-1).getX() - 60, nodes.get(nodes.size()-1).getY() + 50, "toOld");
    	nodes.get(nodes.size()-1).setType(DragNode.IN);
    	addNode(nodes.get(nodes.size()-2), "", nodes.get(nodes.size()-2).getX() + 60, nodes.get(nodes.size()-2).getY(), "toNew");
    	nodes.get(nodes.size()-1).setType(DragNode.OUT);
    	
    }
    
    public void addLogOut(DragNode out)
    {
    	out.setType(DragNode.OUT);
    	out.setDisplay("names");
    	addNode("", out.getX(), out.getY() + 60);
    	nodes.get(nodes.size()-1).setType(DragNode.NOT);
    	addEdge(nodes.get(nodes.size()-1), out, true);
    	addNode(nodes.get(nodes.size()-1), "", nodes.get(nodes.size()-1).getX(), nodes.get(nodes.size()-1).getY() + 60, "toOld");
    	nodes.get(nodes.size()-1).setType(DragNode.IN);
    	
    
    }
    	
    
   public void addLogOut(DragNode out, int type)
    {
    	out.setType(DragNode.OUT);
    	out.setDisplay("names");
    	addNode("", out.getX(), out.getY() + 50);
    	nodes.get(nodes.size()-1).setType(type);
    	addEdge(nodes.get(nodes.size()-1), out, true);
    	addNode(nodes.get(nodes.size()-1), "", nodes.get(nodes.size()-1).getX() - 60, nodes.get(nodes.size()-1).getY() + 50, "toOld");
    	nodes.get(nodes.size()-1).setType(DragNode.IN);
    	addNode(nodes.get(nodes.size()-2), "", nodes.get(nodes.size()-2).getX() + 60, nodes.get(nodes.size()-2).getY() - 50, "toOld");
    	nodes.get(nodes.size()-1).setType(DragNode.IN);
    	
    	
    }
   
	    public List<String> generateBinaryCombinations(int numDigits) {
	        List<String> combinations = new ArrayList<>();
	        generateBinaryCombinationsHelper(numDigits, "", combinations);
	        return combinations;
	    }

	    private void generateBinaryCombinationsHelper(int numDigits, String current, List<String> combinations) {
	        if (current.length() == numDigits) {
	            combinations.add(current);
	            return;
	        }

	        generateBinaryCombinationsHelper(numDigits, current + "0", combinations);
	        generateBinaryCombinationsHelper(numDigits, current + "1", combinations);
	    }
    
  
    
   
   
   
   
}
