import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
	private ArrayList<JTextField> nameList = new ArrayList<JTextField>();
	private ArrayList<JTextField> nameList2 = new ArrayList<JTextField>();
	private ArrayList<ArrayList<JTextField>> numList = new ArrayList<ArrayList<JTextField>>();
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
    public JMenuItem r = new JMenuItem("Rename");
    public JMenuItem aN = new JMenuItem("Add Node");
    public JMenuItem dC = new JMenuItem("Delete Connection");
    public JMenuItem aCD = new JMenuItem("Add Directed Edge");
    public JMenuItem aC = new JMenuItem("Add Non-directed Edge");
    public JMenuItem dN = new JMenuItem("Delete Node");
    public JMenuItem v = new JMenuItem("Set Node Value");
    public JMenuItem sW = new JMenuItem("Set Edge Weight");
    public JMenuItem cL = new JMenuItem("Set Edge Color");
    public JMenuItem sT = new JMenuItem("Set Edge Thickness");
    public JMenuItem fC = new JMenuItem("Count the cycles from this node");
   // public JButton path = new JButton("generate matrix for path length");
    public String[][] orig;
    public String[] nam;
    public GraphPanel() {
        // List of all nodes and lines currently on the workspace
        nodes = new ArrayList<DragNode>();
        lines = new ArrayList<LineComp>();

        // Options in the pop-up menu for Nodes
        menu.add(r);
        menu.add(c);
        menu.add(aN);

        menu.add(aCD);
        menu.add(dN);
        menu.add(v);
        menu.add(fC);
        //Edge menu
        menuLine.add(sW);
        menuLine.add(cL);
        menuLine.add(sT);
        menuLine.add(dC);

        // Renaming a node
        r.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.setVisible(false);
                String name = selectedNode.getName();
                name = JOptionPane.showInputDialog("Rename the node:", name);
                selectedNode.setName(name);

                if (name != null)
                    repaint();
            }
        });
        
        //adding a Directed Edge from an existing node to another existing node
        aCD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                if(nodes.size() > 1)
                {
                  final DragNode startNode = selectedNode; 
                  JOptionPane.showMessageDialog(null, "Select Node to draw Edge towards", "Draw Directed Edge", JOptionPane.PLAIN_MESSAGE);

                  addMouseListener(new MouseAdapter() {
                     public void mouseClicked(MouseEvent e) {
                        selectNode(e.getX(), e.getY());

                        if(selectedNode != null) {
                        	
                        
                        
                        if(!(selectedNode.getChildren().contains(startNode) || startNode.getChildren().contains(selectedNode)))
                        		{
                        			if(selectedNode.equals(startNode))	
                        			{
                        				selectedNode.setBold(true);
                        				repaint();
                        			}
                        			
                        			else
                        			addEdge(startNode, selectedNode, true);
                        			
                        			startNode.getChildren().add(selectedNode);
                        			selectedNode.getParent().add(startNode);
                        			
                        		}
                        		
                        		else
                        		{
                        			JOptionPane.showConfirmDialog(null, "edge already drawn here", "Draw Directed Edge", JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                        		}
                        	
                        } 
                        
                        removeMouseListener(this);
                    }
                });

                }
                
                else {
                	JOptionPane.showConfirmDialog(null, "no other node to draw an edge to", "Draw Directed Edge", JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                }
                    repaint();
            }
        });


        // Re-coloring a node
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                // Option colors for drop-down menu
            	System.out.println("c" + selectedNode.dumpChildren());
                System.out.println("p" + selectedNode.dumpParent());
                String[] options = {"White", "Cyan", "Green", "Yellow", "Magenta", "Orange", "Gray"};
   
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

        //Adding a node connected to the currently selected node
        aN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	menu.setVisible(false);
            	 String[] options = {"Non-Directed Edge", "Directed towards new node", "Directed away from new node"};
                String name = JOptionPane.showInputDialog("Name for the new node:");
                String directed = "";
                if(name != null)
                directed = (String) JOptionPane.showInputDialog(null, "Choose Edge Specifics", "Menu",
                        JOptionPane.OK_OPTION, null, options, options[0]);
                if (name != null && directed != null)
                {
                	switch(directed)
                	{
                	 case "Non-Directed Edge":
                		 addNode(selectedNode, name, 100, 100);
                		 break;
                	
                	 case "Directed towards new node":
                		 addNode(selectedNode, name, 100, 100, "toNew");
                		 break;
                		 
                	 case "Directed away from new node":
                		 addNode(selectedNode, name, 100, 100, "toOld");
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
                    for(int i = 0; i < nodes.size(); i++)
                    {
                    	if(nodes.get(i).getParent().contains(selectedNode))
                    	{
                    		nodes.get(i).getParent().remove(selectedNode);
                    	}
                    	if(nodes.get(i).getChildren().contains(selectedNode))
        				{
                    		nodes.get(i).getChildren().remove(selectedNode);
        				}
                    }
                    nodes.remove(selectedNode);

                    repaint();
                }
            }
        });

        //setting value of node
        v.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);

                String value = selectedNode.getValue();
                value = JOptionPane.showInputDialog("Set Node Value:", value);
                selectedNode.setValue(value);

                if (value != null)
                    repaint();
            }
        });
        
        //setting weight of an edge
        sW.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuLine.setVisible(false);

                int value = selectedLine.getWeight();
                String valS = JOptionPane.showInputDialog("Set Line Value:", value);
                if(!(valS == null))
                {
                try{
                selectedLine.setWeight(Integer.parseInt(valS));
                }
                
                catch(Exception e1)
                {
                	JOptionPane.showMessageDialog(null, "did not provide valid Integer value for edge weight", "non-Integer input", JOptionPane.ERROR_MESSAGE);
                }
               }
                    repaint();
            }
        });
        
        cL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuLine.setVisible(false);
                // Option colors for drop-down menu
                String[] options = {"Black", "Cyan", "Green", "Yellow", "Magenta", "Orange", "Gray"};
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


        dC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuLine.setVisible(false);
                
                if(selectedLine.isDirected())
                	{
                		selectedLine.getStartNode().getChildren().remove(selectedLine.getEndNode());
                		selectedLine.getEndNode().getParent().remove(selectedLine.getStartNode());
                	}
                	else
                	{
                		selectedLine.getStartNode().getChildren().remove(selectedLine.getEndNode());
                		selectedLine.getEndNode().getParent().remove(selectedLine.getStartNode());
                		selectedLine.getStartNode().getParent().remove(selectedLine.getEndNode());
                		selectedLine.getEndNode().getChildren().remove(selectedLine.getStartNode());
                	}
                	
                    lines.remove(selectedLine);

                    repaint();
                }
               
            
        });
        
        sT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	menuLine.setVisible(false);
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
        
        // Renaming a node
        fC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.setVisible(false);
                boolean ret = true;
                int c = 0;
                String count = "";
                while(ret) {
                count = JOptionPane.showInputDialog("find cycles of length:", "3");
                
                try {
                	c = Integer.parseInt(count);
                	ret = false;
                }
                catch(Exception e1)
                {
                	if(count == null)
                	{
                		ret = false;
                	}
                	else
                	JOptionPane.showMessageDialog(null, "did not provide valid Integer value for cycle length", "non-Integer input", JOptionPane.ERROR_MESSAGE);
                }
             
                }
                
                if(count != null)
                JOptionPane.showMessageDialog(null, "there are " + selectedNode.countCycles(c) + " cycles of length " + c + " starting from " + selectedNode.getName(), "number of cycles", JOptionPane.PLAIN_MESSAGE);

               
            }
        });
        
     /* path.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuLine.setVisible(false);
                menu.setVisible(false);
                boolean ret = true;
                String valS = "";
                while(ret) {
                    int c = 0;
                	valS = JOptionPane.showInputDialog("path length for new matrix:", "2");
                    try {
                    	c = Integer.parseInt(valS);
                    	ret = false;
                    }
                    catch(Exception e1)
                    {
                    	if(valS == null)
                    	{
                    		ret = false;
                    	}
                    	else
                    	JOptionPane.showMessageDialog(null, "did not provide valid Integer value for cycle length", "non-Integer input", JOptionPane.ERROR_MESSAGE);
                    }
                 
                    }
                
                if(valS != null)
                {
                	 String[][] matrix = new String[orig.length][orig[0].length];
                	 String[][] newMat = multMatrix(matrix, orig, Integer.parseInt(valS));
                	 JTable table = new JTable(newMat, nam);
                     JScrollPane scrollPane = new JScrollPane(table);
                     table.setFillsViewportHeight(false);

                     // Creating the JFrame and adding the JScrollPane to it
                     JFrame tableFrame = new JFrame("Adjacency Matrix");
                     tableFrame.setLayout(new BorderLayout());
                     tableFrame.setPreferredSize(new Dimension(500, 500));
                     tableFrame.setMaximumSize(new Dimension(500,500));
                     tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                     tableFrame.add(scrollPane, BorderLayout.CENTER); // Adding the JScrollPane to the JFrame
                     tableFrame.add(path, BorderLayout.NORTH);
                     tableFrame.pack(); // Adjusts the JFrame size to fit its components
                     tableFrame.setVisible(true); // Make the JFrame visible
                }
                
                
            }
        });
        */
        
       
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
                        menu.show(null, selectedNode.getX(), selectedNode.getY());
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
    
    public void addNode(DragNode startNode, String name, int x, int y) {
        nodes.add(new DragNode(name, x, y));
        lines.add(new LineComp("", startNode.getX() + 25, startNode.getY() + 50, nodes.get(nodes.size()-1).getX() + 25, nodes.get(nodes.size()-1).getY()));
        lines.get(lines.size()-1).setEndNode(nodes.get(nodes.size()-1));
        lines.get(lines.size()-1).setStartNode(startNode);
        nodes.get(nodes.size()-1).setDisplay(startNode.getDisplay());
        nodes.get(nodes.size()-1).setLevel(startNode.getLevel()+1);
        startNode.getChildren().add(nodes.get(nodes.size()-1));
        startNode.getParent().add(nodes.get(nodes.size()-1));
        nodes.get(nodes.size()-1).getChildren().add(startNode);
        nodes.get(nodes.size()-1).getParent().add(startNode);
        
        repaint();
    }
    
    public void addNode(DragNode startNode, String name, int x, int y, String direction) {
        nodes.add(new DragNode(name, x, y));
        DragNode endNode = nodes.get(nodes.size()-1);
        nodes.get(nodes.size()-1).setDisplay(startNode.getDisplay());
        nodes.get(nodes.size()-1).setLevel(startNode.getLevel()+1);
        
        if(direction.equals("toNew"))
        {
        	addEdge(startNode, endNode, true);
        	startNode.getChildren().add(endNode);
        	endNode.getParent().add(startNode);
        }
        
        else if (direction.equals("toOld"))
        {
        	addEdge(endNode, startNode, true);
        	endNode.getChildren().add(startNode);
        	startNode.getParent().add(endNode);
        }
        
        repaint();
    }

    public void addEdge(DragNode startNode, DragNode endNode, boolean isDirected) {
    	
    	if(!(startNode.equals(endNode))) {
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
            endNode.getParent().add(startNode);
            startNode.getParent().add(endNode);
            endNode.getChildren().add(startNode);
            
    	}
    	}
    	
    	else
    	{
    		startNode.setBold(true);
    		startNode.getChildren().add(endNode);
    		startNode.getParent().add(endNode);
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
            g2.setStroke(new BasicStroke(1));
            if(node.getBold())
            {
            	g2.setStroke(new BasicStroke(3));
            }
            g2.drawOval(x, y, 50, 50);
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
            //display the weight of the Edge
            int midX = (startX + endX) / 2;
            int midY = (startY + endY) / 2;

            // Get the FontMetrics for the current graphics context
            FontMetrics fontMetrics = g2.getFontMetrics();

            // Calculate the width and height of the text
            int textWidth = fontMetrics.stringWidth("Weight: " + line.getWeight());
            int textHeight = fontMetrics.getHeight();

            // Calculate the bounding box coordinates with padding
            int boxX = midX - textWidth / 2 - 2; // Add padding of 2 pixels
            int boxY = midY - textHeight / 2 - 2; // Add padding of 2 pixels
            int boxWidth = textWidth + 4; // Add padding of 4 pixels
            int boxHeight = textHeight + 4; // Add padding of 4 pixels

            // Draw a filled white rectangle
            g2.setColor(Color.WHITE);
            g2.fillRect(boxX, boxY, boxWidth, boxHeight);

            // Draw the number on top of the white rectangle
            g2.setColor(Color.BLACK);
            g2.drawString("Weight: " + line.getWeight(), midX - textWidth / 2, midY + textHeight / 2);

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
    
    
    
    public void generateMatrix()
    {
    	final List<DragNode> nodes2 = nodes;
    	String[][] Matrix = new String[nodes.size()][nodes.size()+1];
    	String[] names = new String[nodes.size()+1];
    	names[0] = "";

    	for(DragNode node: nodes)
    	{
    		names[nodes.indexOf(node) + 1] = node.getName() + ":";
    		Matrix[nodes.indexOf(node)][0] = node.getName() + ":";
    		for(int i = 0; i < nodes2.size(); i++)
    		{
    			if(node.getChildren().contains(nodes2.get(i)))
    			{
    				Matrix[nodes.indexOf(node)][i + 1] = "1";
    			}
    			
    			else
    			{
    				Matrix[nodes.indexOf(node)][i + 1] = "0";
    			}
    		}
    	}
    	orig = Matrix;
    	nam = names;
    	 JTable table = new JTable(Matrix, names);
         JScrollPane scrollPane = new JScrollPane(table);
         table.setFillsViewportHeight(false);

         // Creating the JFrame and adding the JScrollPane to it
         JFrame tableFrame = new JFrame("Adjacency Matrix");
         tableFrame.setLayout(new BorderLayout());
         tableFrame.setPreferredSize(new Dimension(500, 500));
         tableFrame.setMaximumSize(new Dimension(500,500));
         tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         tableFrame.add(scrollPane, BorderLayout.CENTER); // Adding the JScrollPane to the JFrame
        // tableFrame.add(path, BorderLayout.NORTH);
         tableFrame.pack(); // Adjusts the JFrame size to fit its components
         tableFrame.setVisible(true); // Make the JFrame visible
    }
    
    
   

        public void inputGrid(int gridSize) {
            // Create the main frame
        	for(int i = 0; i < gridSize; i++)
        	{
        		numList.add(new ArrayList<JTextField>());
        	}
            final JFrame frame = new JFrame("Grid Panel");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            

            // Create the grid panel
            JPanel gridPanel = new JPanel(new GridLayout(gridSize+1, gridSize+1));

            // Add text boxes to the grid panel
            for (int i = 0; i < gridSize + 1; i++) {
                for (int j = 0; j < gridSize + 1; j++) {
                    JTextField textField = new JTextField(5);
                    gridPanel.add(textField);
                    if(i == 0 && j == 0)
                    {
                    	textField.setBackground(Color.yellow);
                    	textField.setText("Names:");
                    	textField.setEditable(false);
                    	 textField.addMouseListener(new MouseAdapter() {            
                        	   @Override
                        	   public void mouseClicked(MouseEvent e) {
                        	      for(int i = 0; i < nameList.size(); i++)
                        	      {
                        	    	  nameList2.get(i).setText(nameList.get(i).getText());
                        	      }
                        	   }
                        	});
                    }
                    else if(i == 0)
                    {
                    	textField.setBackground(Color.yellow);
                    	nameList.add(textField);
                    	 textField.addMouseListener(new MouseAdapter() {            
                        	   @Override
                        	   public void mouseClicked(MouseEvent e) {
                        	      for(int i = 0; i < nameList.size(); i++)
                        	      {
                        	    	  nameList2.get(i).setText(nameList.get(i).getText());
                        	      }
                        	   }
                        	});
                    }
                    else if(j == 0)
                    {
                    	textField.setEditable(false);
						nameList2.add(textField);
						 textField.addMouseListener(new MouseAdapter() {            
	                      	   @Override
	                      	   public void mouseClicked(MouseEvent e) {
	                      	      for(int i = 0; i < nameList.size(); i++)
	                      	      {
	                      	    	  nameList2.get(i).setText(nameList.get(i).getText());
	                      	      }
	                      	   }
	                      	});
                    }
                    else
                    {
                    	 textField.addMouseListener(new MouseAdapter() {            
                      	   @Override
                      	   public void mouseClicked(MouseEvent e) {
                      	      for(int i = 0; i < nameList.size(); i++)
                      	      {
                      	    	  nameList2.get(i).setText(nameList.get(i).getText());
                      	      }
                      	   }
                      	});
                    	numList.get(i-1).add(textField);
                    }
                }
            }

            // Create the buttons
            JButton submitButton = new JButton("submit");
            JButton fillWithZeroButton = new JButton("fill empty cells with zero");

            // Add the buttons to a separate panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(submitButton);
            buttonPanel.add(fillWithZeroButton);

            // Add action listeners to the buttons (you can implement their functionality)
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	boolean error = false;
                	for(int i = 0; i < numList.size();i++)
                	{
                		for(int j = 0; j < numList.get(i).size(); j++)
                		{
                			try {
                				Integer.parseInt(numList.get(i).get(j).getText());
                			}
                			catch(Exception e1)
                			{
                				numList.get(i).get(j).setText("");
                				error = true;
          
                			}
                		}
                	}
                	if(error)
                	{
                		JOptionPane.showMessageDialog(null, "Incorrect input, there were empty or non-integer cells present", "error", JOptionPane.PLAIN_MESSAGE);
                	}
                	else {
                	  generateGraph();
                      frame.dispose();
                	}
                }
            });

            fillWithZeroButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	for(int i = 0; i < numList.size(); i++) {
                		for(int j = 0; j < numList.get(i).size(); j++)
                		if(numList.get(i).get(j).getText().equals(""))
            			{
                			numList.get(i).get(j).setText("0");
            			}
                	}
                    
                }
            });

           
            // Add the grid panel and button panel to the main frame
            frame.add(gridPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Display the frame
            frame.pack();
            frame.setVisible(true);
        }

      

    
  /*
    public String[][] multMatrix(String[][] matrix, String[][] orig, int pow)
    {
        int rows = matrix.length;
        int cols = matrix[0].length ;
        String[][] result = new String[rows][cols];
        System.out.println(cols);
        for(int l = 0; l < pow-1; l++) {
        result = new String[rows][cols];
        for(int h = 0; h<rows; h++) {
            Arrays.fill(result[h], 0, cols, "0");
        }
        System.out.println(result.toString());
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                for (int k = 1; k < cols; k++) {
                    result[i][j] = "" + Integer.parseInt(result[i][j]) +  Integer.parseInt(matrix[i][k]) * Integer.parseInt(orig[k-1][j]);
                }
            }
        }
        matrix = result;
        }
		return result;
    
    }
    */
        
    public void generateGraph()
    {
    	ArrayList<DragNode> holder = new ArrayList<DragNode>();
    	 for(int i = 0; i < numList.size();i++)
  	   	 {
  		   addNode(nameList.get(i).getText(), i*5, i*5);
  		   holder.add(nodes.get(nodes.size()-1));
  	     }
    	 
	   for(int i = 0; i < numList.size();i++)
	   {
		
		   for(int j = 0; j < numList.get(i).size(); j++)
		   {
			   if(!(numList.get(i).get(j).getText().equals("0"))){
				   
				   for(int k = 0; k < Integer.parseInt(numList.get(i).get(j).getText()); k++)
				   {
					   addEdge(holder.get(i), holder.get(j), true);  
				   }
				   
			   }
		   }
	   } 
	   numList.clear();
	   nameList.clear();
	   nameList2.clear();
	   repaint();
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
    
    
    
}
