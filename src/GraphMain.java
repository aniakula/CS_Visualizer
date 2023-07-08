import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



class GraphPanel extends JPanel {
    private java.util.List<DragNode> nodes;
    private DragNode selectedNode;
    private Point dragOffset;
    //pop-up menu for editing Nodes
    public JPopupMenu menu = new JPopupMenu("Menu");
	public JMenuItem c = new JMenuItem("Color");
	public JMenuItem r = new JMenuItem("Rename");
	public JMenuItem aC = new JMenuItem("Add Connection");
	public JMenuItem aN = new JMenuItem("Add Child Node");
	public JMenuItem dC = new JMenuItem("Delete Connection");
	public JMenuItem dN = new JMenuItem("Delete This Node");

    public GraphPanel() {
    
    	//list of all nodes currently on the Workspace
        nodes = new java.util.ArrayList<>();
       
        //options in the pop-up
        menu.add(r);
        menu.add(c);
        menu.add(aC);
        menu.add(aN);
        menu.add(dC);
        menu.add(dN);
        
        //renaming a node
        r.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    
        		  menu.setVisible(false);
	
        		  String name = selectedNode.getName();
        		  name = JOptionPane.showInputDialog("rename the node:", selectedNode.getName());
        		  selectedNode.setName(name);
        		  
        		  if(name != null)
        		  repaint(); 
	
        	  } 
        	} );
        
        
        
        //re-coloring a nodes
        c.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      	    
      		  menu.setVisible(false);
      		  //option colors for drop down menu
      		  String[] options = {"White", "Blue", "Green", "Yellow", "Magenta", "Gray"};
      		  DragNode node = selectedNode;
      		  String selection = (String) JOptionPane.showInputDialog(null, "Choose color", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
      		  //change color based on choice
      		  switch(selection)
      		  {
      		   case "White" : 
      			   
      			   selectedNode.setCol(Color.white);
      			   break;
      			   
      		   case "Blue" :
      			 selectedNode.setCol(Color.blue);
    			   break;
      			 
      		   case "Green" :
      			 selectedNode.setCol(Color.green);
    			   break;
      			
      		   case "Yellow" :
      			 selectedNode.setCol(Color.yellow);
    			   break;
      			
      		   case "Magenta" :
      			 selectedNode.setCol(Color.magenta);
    			   break;
      			
      		   case "Gray" :
      			 selectedNode.setCol(Color.gray);
    			   break;
      		  
      		  }
      		  
      		  repaint(); 
	
      	  } 
      	} );
        
        aN.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      	    
      		  String name = "";
      		  name = JOptionPane.showInputDialog("name for new node:");
      		  
      		  if(name != null)
      		  addNode(name, 100, 100);
      		  repaint(); 
	
      	  } 
      	} );
        
        //Deleting Node:
        dN.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      	    
      		  menu.setVisible(false);
      		  int choice = JOptionPane.showConfirmDialog(null, "Are you Sure you want to delete " + selectedNode.getName() + " ?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
      		  
      		  if(choice == 0)
      		  {
      		  nodes.remove(selectedNode);
      		  repaint(); 
      		  }
      		  
      		  
	
      	  } 
      	} );
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                selectNode(e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                selectedNode = null;
            }
            
            public void mouseClicked(MouseEvent e)
            {
            	selectNode(e.getX(), e.getY());
            	if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2)
            	{
            		menu.setVisible(false);
            	}
            	
            	if(selectedNode != null) {
            
            	if(e.getButton() == MouseEvent.BUTTON3)
            	{
            		
                            menu.show(null, selectedNode.getX(), selectedNode.getY());
            		
            			}
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
                    repaint();
                }
            }
        });
    }

    public void addNode(String name, int x, int y) {
        nodes.add(new DragNode(name, x, y));
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (DragNode node : nodes) {
            int x = node.getX();
            int y = node.getY();
            g2.setColor(node.getColor());
            g2.fillOval(x, y, 50, 50);
            g2.setColor(Color.BLACK);
            g2.drawOval(x, y, 50, 50);
            g2.drawString(node.getName(), x + 20, y + 30);
        }

        g2.setColor(Color.BLACK);
        DragNode node = nodes.get(0);

            int startX = node.getX() + 25;
            int startY = node.getY() + 50;

            for (DragNode connectedNode : nodes) {
                if (connectedNode != node) {
                    int endX = connectedNode.getX() + 25;
                    int endY = connectedNode.getY();
                    g2.drawLine(startX, startY, endX, endY);
            }
        }
    }

    private void selectNode(int x, int y) {
        for (DragNode node : nodes) {
            int nodeX = node.getX();
            int nodeY = node.getY();
            if (x >= nodeX && x <= nodeX + 50 && y >= nodeY && y <= nodeY + 50) {
                selectedNode = node;
                dragOffset = new Point(x - nodeX, y - nodeY);
                break;
            }
            
            else
            {
            	selectedNode = null;
            }
        }
    }
}

public class GraphMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final GraphPanel graphPanel = new GraphPanel();
        JMenuBar bar = new JMenuBar();
        JMenu options = new JMenu("Actions");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem addN = new JMenuItem("Add new Node");
        JMenuItem connect = new JMenuItem("Add a connection");
        JMenuItem delConnect = new JMenuItem("Remove existing connection");
    	options.add(help);
    	options.add(addN);
    	options.add(connect);
    	options.add(delConnect);
    	
    	
    	bar.add(options);
    	frame.setJMenuBar(bar);
    	options.setVisible(true);
    	
        frame.add(graphPanel);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
     
        frame.setVisible(true); 

        graphPanel.addNode("Node 1", 100, 100);
        graphPanel.addNode("Node 2", 300, 200);
        graphPanel.addNode("Node 3", 500, 300);
    }
}
