import java.awt.Color;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LineComp extends JPanel {
    private int stx;
    private int sty;
    private int endx;
    private int endy;
    private int weight;
    private String name;
    private Color col;
    private DragNode stNode;
    private DragNode endNode;
    private boolean isDirected;
    private int thickness;
    //represents orientation of line with respect to Start and End Node:
    //preset = 1: NW
    //preset = 2: W
    //preset = 3: SW
    //preset = 4: S
    //preset = 5: SE
    //preset = 6: E
    //preset = 7: NE
    //preset = 8: N
    private int preset; 

    public LineComp(String name, int stx, int sty, int endx, int endy) {
        this.name = name;
        this.endx = endx;
        this.endy = endy;
        this.stx = stx;
        this.sty = sty;
        this.col = Color.black;
        this.isDirected = false;
        this.weight = 0;
        this.thickness = 1;
        this.preset = 1;
    }
   

    public String getName() {  
        return name;
    }
    
    public Color getColor() {
        return col;
    }
    
    public void setCol(Color color) {
        this.col = color;
    }

    public void setName(String name)
    {
    	this.name = name;
    }
    
    public int getStX()
    {
    	return stx;
    }
    
    public int getStY()
    {
    	return sty;
    }
    
    public int getEndX()
    {
    	return endx;
    }
    
    public int getEndY()
    {
    	return endy;
    }
    
    public DragNode getEndNode()
    {
    	return endNode;
    }
    
    public DragNode getStartNode()
    {
    	return stNode;
    }
    
    public boolean isDirected()
    {
    	return isDirected;
    }
    
    public int getWeight()
    {
    	return weight;
    }
    
    public int getThick()
    {
    	return thickness;
    }
    
    public int getPreset()
    {
    	return preset;
    }
    
    public void setEndNode(DragNode node)
    {
    	endNode = node;		
    }
    
    public void setStartNode(DragNode node)
    {
    	stNode = node;		
    }
    
    public void setStX(int newn)
    {
    	stx = newn;
    }
    
    public void setStY(int newn)
    {
    	sty = newn;
    }
    
    public void setEndX(int newn)
    {
    	endx = newn;
    }
    
    public void setEndY(int newn)
    {
    	endy = newn;
    }
    
    public void setDirected(boolean bool) 
    {
    	isDirected = bool;
    }
    
    public void setWeight(int weight)
    {
    	this.weight = weight;
    }
    
    public void setThick(int t)
    {
    	thickness = t;
    }
    
    public void setPreset(int p)
    {
    	preset = p;
    	
    	if(p>8)
    		preset = 1;
    	else if(p < 1)
    		preset = 8;
    }
    		

    //for checking if a click was made close to a LineComp object
    public boolean isInsideLine(int x, int y) {
        int startX = this.getStartNode().getX() + 25;
        int startY = this.getStartNode().getY() + 25;
        int endX = this.getEndNode().getX() + 25;
        int endY = this.getEndNode().getY() + 25;

        Line2D lineSegment = new Line2D.Double(startX, startY, endX, endY);
        double distance = lineSegment.ptSegDist(x, y);

        return distance <= 20; // Adjust the range as needed
    }

        public int calculateDistance() {
            int deltaX = endx - stx;
            int deltaY = endy - sty;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            return (int)distance;
        }
        
        public int Dist(int x1, int y1, int x2, int y2) {
            int deltaX = x2 - x1;
            int deltaY = y2 - y1;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            return (int)distance;
        }
    
}
