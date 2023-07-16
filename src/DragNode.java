import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
    //children is the arrayList of nodes that represents the current object's children nodes
    private ArrayList<DragNode> children;
    private ArrayList<DragNode> pointsTo;
    //orientation of the children
    private ArrayList<String> orient;
    //used to indicate a loop in graph
    private boolean isBold  = false;
    //type of boolean expression
    private int type;
    
    //constants for Boolean operation statements:
    static final int IN = 1;
    static final int OUT = 2;
    static final int NOT = 3;
    static final int AND = 4;
    static final int OR = 5;
    static final int XOR = 6;
    static final int NAND = 7;
    static final int NOR = 8;
    static final int XNOR = 9;
    	
    
    

    public DragNode(String name, int x, int y) {
    	
        this.name = name;
        this.x = x;
        this.y = y;
        this.col = Color.white;
        this.value = "0";
        this.display = "names";
        this.level = 0;
        children = new ArrayList<DragNode>();
        orient = new ArrayList<String>();
        pointsTo = new ArrayList<DragNode>();
        this.isBold = false;
        this.type = this.IN;
    }
    
    // getters:::
    
    public String getName() {  
        return name;
    }
    
    public String getValue(){
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
    
    public boolean getBold()
    {
    	return isBold;
    }
    
    public ArrayList<String> getOrient()
    {
    	return orient;
    }
    
    public int getType()
    {
    	return type;
    }
    
    public ArrayList<DragNode> getParent()
    {
    	return pointsTo;
    }
    
    public String dumpParent()
    {
		return pointsTo.toString();
    	
    }
    
    public String dumpChildren()
    {
    	return children.toString();
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
    
    public void setBold(boolean set)
    {
    	isBold = set;
    }

    public void setType(int x)
    {
    	switch(x)
    	{
    	case(1):
    		type = this.IN;
    		break;
    	case(2):
    		type = this.OUT;
    		this.setName("OUTPUT");
    		break;
    	case(3):
    		type = this.NOT;
    		this.setName("NOT");
    		break;
    	case(4):
    		type = this.AND;
    	    this.setName("AND");
    		break;
    	case(5):
    		type = this.OR;
    	 	this.setName("OR");
    		break;
    	case(6):
    		type = this.XOR;
    		this.setName("XOR");
    		break;
    	case(7):
    		type = this.NAND;
    		this.setName("NAND");
    		break;
    	case(8):
    		type = this.NOR;
    		this.setName("NOR");
    		break;
    	case(9):
    		type = this.XNOR;
    		this.setName("XNOR");
    		break;
    	}
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
    
    public boolean isLeaf()
    {
    	if(this.getChildren().size() == 0)
    		return true;
    	
    	return false;
    }
    
    public boolean isRoot()
    {
    	if(this.getLevel() == 0)
    		return true;
    	
    	return false;
    }
    
    public int evaluate()
    {
    	try {
    	if(this.getType() == DragNode.OUT)
    	{
    		this.setDisplay("values");
    		this.setValue("" + this.getParent().get(0).evaluate());
    		return Integer.parseInt(this.getValue());
    	}
    	else if(this.getType() == DragNode.IN)
    	{
    		this.setDisplay("values");
    		return Integer.parseInt(this.getValue());
    	}
    	else if(this.getType() == DragNode.NOT)
    	{
    		return convert(!convert(this.getParent().get(0).evaluate()));
    	}
    	else if(this.getType() == DragNode.AND)
    	{
    		this.getParent().get(0).setDisplay("values");
    		this.getParent().get(1).setDisplay("values");
    		return convert(convert(this.getParent().get(0).evaluate()) && convert(this.getParent().get(1).evaluate()));
    	}
    	else if(this.getType() == DragNode.OR)
    	{
    		return convert(convert(this.getParent().get(0).evaluate()) || convert(this.getParent().get(1).evaluate()));
    	}
    	else if(this.getType() == DragNode.XOR)
    	{
    		return convert(convert(this.getParent().get(0).evaluate()) ^ convert(this.getParent().get(1).evaluate()));
    	}
    	else if(this.getType() == DragNode.NAND)
    	{
    		this.getParent().get(0).setDisplay("values");
    		this.getParent().get(1).setDisplay("values");
    		return convert(!(convert(this.getParent().get(0).evaluate()) && convert(this.getParent().get(1).evaluate())));
    	}
    	else if(this.getType() == DragNode.NOR)
    	{
    		return convert(!(convert(this.getParent().get(0).evaluate()) || convert(this.getParent().get(1).evaluate())));
    	}
    	else if(this.getType() == DragNode.XNOR)
    	{
    		return convert(!(convert(this.getParent().get(0).evaluate()) ^ convert(this.getParent().get(1).evaluate())));
    	}
    	
    	return 0;
    }
    catch(StackOverflowError e)
    {
    	return 2;
    }
    }
    
    public int convert(boolean bool)
    {
    	if(bool)
    		return 1;
    	else
    		return 0;
    }
    
    public boolean convert(int i)
    {
    	if(i == 1)
    		return true;
    	else
    		return false;
    }
    public void organize(int xdiff, int ydiff)
    {
    	if(this.getLevel() == 0)
    	{
    		this.setX(730);
    		this.setY(0);
    	
    		if(this.getChildren().size() == 1)
    		{
    			this.getChildren().get(0).setX(this.getX() - xdiff);
    			this.getChildren().get(0).setY(this.getY() + ydiff);
    			this.getChildren().get(0).organize(xdiff, ydiff);
    		}
    		
    		else if(this.getChildren().size() == 2)
    		{
    			this.getChildren().get(0).setX(this.getX() - xdiff);
    			this.getChildren().get(0).setY(this.getY() + ydiff);
    			this.getChildren().get(1).setX(this.getX() + xdiff);
    			this.getChildren().get(1).setY(this.getY() + ydiff);
    			this.getChildren().get(0).organize(xdiff, ydiff);
    			this.getChildren().get(1).organize(xdiff, ydiff);
    			
    		}
    	}
    	
    	else
    	{
    		if(this.getChildren().size() == 1)
    		{
    			this.getChildren().get(0).setX(this.getX() - xdiff);
    			this.getChildren().get(0).setY(this.getY() + ydiff);
    			this.getChildren().get(0).organize(xdiff, ydiff);
    		}
    		
    		else if(this.getChildren().size() == 2)
    		{
    			
    			this.getChildren().get(0).setX(this.getX() - xdiff);
    			this.getChildren().get(0).setY(this.getY() + ydiff);
    			this.getChildren().get(1).setX(this.getX() + xdiff);
    			this.getChildren().get(1).setY(this.getY() + ydiff);
    			this.getChildren().get(0).organize(xdiff, ydiff);
    			this.getChildren().get(1).organize(xdiff, ydiff);
    		}
    	}
    	
    }
    
    
}