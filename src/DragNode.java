import java.awt.Color;
import java.util.ArrayList;

public class DragNode {
	//name of node
    private String name;
    //optionally assigned value of node
    private String value;
    //coordinates of node
    private int x;
    private int y;
    //color of node
    private Color col;
    //type of value to be displayed
    private String display;
    //level of depth in the tree
    private int level;
    //children is the arraylist of nodes that represents the current object's children nodes
    private ArrayList<DragNode> children;

    public DragNode(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.col = Color.white;
        this.value = "0";
        this.display = "names";
        this.level = 0;
        children = new ArrayList<DragNode>();
    }
    
    // getters:::
    public String getName() {  
        return name;
    }
    
    public String getValue()
    {
    	return value;
    }
    
    public Color getColor() {
        return col;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String getDisplay() {
    	return display;
    }
    
    public int getLevel()
    {
    	return level;
    }
    
    public ArrayList<DragNode> getChildren()
    {
    	return children;
    }
    //:::getters
    
    //setters:::
    public void setX(int x) {
        this.x = x;
    }
    
    public void setCol(Color color) {
        this.col = color;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
    
    public void setValue(String val)
    {
    	value = val;
    }
    
    public void setDisplay(String disp)
    {
    	display = disp;
    }
    public void setLevel(int lev)
    {
    	level = lev;
    }
    //:::setters^^^
    
    //recursive method to update the levels when a node is deleted
    public void unLevel ()
    {
    	this.setLevel(this.getLevel()-1);
    	if(this.getChildren().size() > 0)
    	{
    		for(DragNode child: this.getChildren())
    		{
    			child.unLevel();
    		}
    	}
    }
    
}