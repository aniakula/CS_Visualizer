import java.awt.Color;

public class LineComp {
    private int stx;
    private int sty;
    private int endx;
    private int endy;
    private String name;
    private Color col;
    private DragNode stNode;
    private DragNode endNode;

    public LineComp(String name, int stx, int sty, int endx, int endy) {
        this.name = name;
        this.endx = endx;
        this.endy = endy;
        this.stx = stx;
        this.sty = sty;
        this.col = Color.black;
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
}
