import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Node {
    private String name;
    private int x;
    private int y;

    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
}

class GraphPanel extends JPanel {
    private java.util.List<Node> nodes;
    private Node selectedNode;
    private Point dragOffset;

    public GraphPanel() {
    
    
   
    
    	
        nodes = new java.util.ArrayList<>();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                selectNode(e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                selectedNode = null;
            }
            
            public void mouseClicked(MouseEvent e)
            {
            	if(e.getButton() == MouseEvent.BUTTON3)
            	{
            		selectNode(e.getX(), e.getY());
            		
            		String name = selectedNode.getName();
            		
            		if(selectedNode != null)
            		{
            			
            			name = JOptionPane.showInputDialog("rename the node:");
            			if(name != null) {
            				selectedNode.setName(name);
            			}
            			
            			else
            			{
            				name = selectedNode.getName();
            			}
            			
            			repaint();
            			
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
        nodes.add(new Node(name, x, y));
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Node node : nodes) {
            int x = node.getX();
            int y = node.getY();
            g2.setColor(Color.WHITE);
            g2.fillOval(x, y, 50, 50);
            g2.setColor(Color.BLACK);
            g2.drawOval(x, y, 50, 50);
            g2.drawString(node.getName(), x + 20, y + 30);
        }

        g2.setColor(Color.BLACK);
        for (Node node : nodes) {
            int startX = node.getX() + 25;
            int startY = node.getY() + 50;

            for (Node connectedNode : nodes) {
                if (connectedNode != node) {
                    int endX = connectedNode.getX() + 25;
                    int endY = connectedNode.getY();
                    g2.drawLine(startX, startY, endX, endY);
                }
            }
        }
    }

    private void selectNode(int x, int y) {
        for (Node node : nodes) {
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

        GraphPanel graphPanel = new GraphPanel();
        
      
 
        
        JMenuBar options = new JMenuBar();
        JMenuItem addN = new JMenuItem();
        JMenuItem delN = new JMenuItem();
        JMenuItem help = new JMenuItem();
        JMenuItem connect = new JMenuItem();
        JMenuItem delConnect = new JMenuItem();
        
    	
    	options.add(help);
    	options.add(addN);
    	options.add(delN);
    	options.add(connect);
    	options.add(delConnect);
    	frame.setJMenuBar(options);
    	options.setVisible(true);

        frame.add(graphPanel);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
     
        frame.setVisible(true);
        
        

        graphPanel.addNode("Node 1", 100, 100);
        graphPanel.addNode("Node 2", 300, 200);
        graphPanel.addNode("Node 3", 500, 300);
    }
}
