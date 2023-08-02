import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    //boolean to represent visitation in recursive functions
    private boolean visited = false;
    
    
    //constants for Boolean operation  :
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
        this.type = DragNode.IN;
        
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
   
    public boolean isVisited()
    {
    	return visited;
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
    		type = DragNode.IN;
    		break;
    	case(2):
    		type = DragNode.OUT;
    		this.setName("OUTPUT");
    		break;
    	case(3):
    		type = DragNode.NOT;
    		this.setName("NOT");
    		break;
    	case(4):
    		type = DragNode.AND;
    	    this.setName("AND");
    		break;
    	case(5):
    		type = DragNode.OR;
    	 	this.setName("OR");
    		break;
    	case(6):
    		type = DragNode.XOR;
    		this.setName("XOR");
    		break;
    	case(7):
    		type = DragNode.NAND;
    		this.setName("NAND");
    		break;
    	case(8):
    		type = DragNode.NOR;
    		this.setName("NOR");
    		break;
    	case(9):
    		type = DragNode.XNOR;
    		this.setName("XNOR");
    		break;
    	}
    }

    public void setVisit(boolean v)
    {
    	visited = v;
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
    		this.getParent().get(0).setDisplay("values");
		this.getParent().get(1).setDisplay("values");
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
    		this.getParent().get(0).setDisplay("values");
    		this.getParent().get(1).setDisplay("values");
    		return convert(!(convert(this.getParent().get(0).evaluate()) || convert(this.getParent().get(1).evaluate())));
    	}
    	else if(this.getType() == DragNode.XNOR)
    	{
    		return convert(!(convert(this.getParent().get(0).evaluate()) ^ convert(this.getParent().get(1).evaluate())));
    	}
    	
    	return 0;
    
 
    }
    
    public int convert(boolean bool)
    {
    	if(bool)
    		return 1;
    	else
    		return 0;
    }
    
    public static boolean convert(int i)
    {
    	if(i == 1)
    		return true;
    	else
    		return false;
    }
    
    public static boolean validMerge(DragNode in, DragNode out)
    {
    	if(out.getType() == DragNode.IN)
    	{
    		if(out.equals(in))
    		{
    			return false;
    		}
    		else
    		{
    			return true;
    		}
    	}
    	
    	else if(out.getType() == DragNode.OUT || out.getType() == DragNode.NOT)
    	{
    		return validMerge(in, out.getParent().get(0));
    	}
    	
    	else
    	{
    		return validMerge(in, out.getParent().get(0)) && validMerge(in, out.getParent().get(1));
    	} 
    }
    
    public ArrayList<DragNode> getIn(ArrayList<DragNode>ins)
    {
    	if(this.getType() == DragNode.OUT || this.getType() == DragNode.NOT)
    	{
    		this.getParent().get(0).getIn(ins);
    	}
    	
    	else if(this.getType() != DragNode.IN)
    	{
    		this.getParent().get(0).getIn(ins);
    		this.getParent().get(1).getIn(ins);
    	}
    	
    	else
    	{
    		if(!ins.contains(this))
    		ins.add(this);
    	}
    	
    	return ins;
    }
    
    private int CountCycles(int length, int currentLength) {
        // Base case: if the current length is equal to the desired length, we found a cycle
        if (currentLength == length) {
            return 1;
        }

        int cycleCount = 0;
        setVisit(true);

        // Recursively explore children nodes that have not been visited yet
        for (DragNode child : children) {
            if (!child.isVisited()) {
                cycleCount += child.CountCycles(length, currentLength + 1);
            }
        }

        // Reset the visit status of the current node before backtracking
        setVisit(false);
        return cycleCount;
    }

    // Method to compute the number of cycles of a given length in the graph starting from this node
    public int countCycles(int length) {
        int cycleCount = 0;

        // Perform DFS starting from this node
        for (DragNode node : children) {
            cycleCount += node.CountCycles(length, 1);
        }

        return cycleCount;
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
    
    public static ArrayList<String> inOrder(DragNode root, ArrayList<String> passed)
    {
		return passed;
    }
    
    public static ArrayList<String> postOrder(DragNode root, ArrayList<String> passed)
    {
		return passed;
    }
    
    public static ArrayList<String> lvlOrder(ArrayList<DragNode> nodes, ArrayList<String> passed)
    {
    	nodes.sort(
    			  Comparator.comparing(DragNode::getLevel).thenComparing(DragNode::getX)
    			);
    	for(DragNode node: nodes)
    	{
    		passed.add(node.getName());
    	}
		return passed;
    }
    
    public static ArrayList<String> preOrder(DragNode root, ArrayList<String> passed)
    {
    	
    	if(root.isLeaf() && !root.isVisited())
    	{
    		passed.add(root.getName());
    		root.setVisit(true);
    		return passed;
    	}
    	
    	else if(root.isRoot() && !root.isVisited())
    	{
    		
    		if(root.getChildren().size() == 0)
    		{
    			root.setVisit(true);
    			passed.add(root.getName());
    			return preOrder(root, passed);
    		}
    		else if(root.getChildren().size() == 1)
    		{
    			root.setVisit(true);
    			ArrayList<String> temp = preOrder(root.getChildren().get(0), passed);
    			temp.add(0, root.getName());
    			return temp;
    		}
    		else if(root.getChildren().size() == 2)
    		{
    			if(root.getChildren().get(0).getX() < root.getChildren().get(1).getX())
    			{
    				ArrayList<String> temp = new ArrayList<String>();
    				temp = preOrder(root.getChildren().get(0),passed);
    				ArrayList<String> temp2 = new ArrayList<String>();
    				temp.addAll(preOrder(root.getChildren().get(1), temp2));
    				temp.add(0, root.getName());
    				return temp;
    			}
    			
    			else
    			{
    				ArrayList<String> temp = new ArrayList<String>();
    				temp = preOrder(root.getChildren().get(1),passed);
    				ArrayList<String> temp2 = new ArrayList<String>();
    				temp.addAll(preOrder(root.getChildren().get(0), temp2));
    				temp.add(0, root.getName());
    				return temp;
    			}
    		}
    	}
    	
    	else if(root.isRoot() && root.isVisited())
    	{
    		return passed;
    	}
    	
    	else
    	{
    		if(root.getChildren().size() == 1)
    		{
    			root.setVisit(true);
    			ArrayList<String> temp = preOrder(root.getChildren().get(0), passed);
    			temp.add(0, root.getName());
    			return temp;
    		}
    		else if(root.getChildren().size() == 2)
    		{
    			if(root.getChildren().get(0).getX() < root.getChildren().get(1).getX())
    			{
    				ArrayList<String> temp = new ArrayList<String>();
    				temp = preOrder(root.getChildren().get(0),passed);
    				ArrayList<String> temp2 = new ArrayList<String>();
    				temp.addAll(preOrder(root.getChildren().get(1), temp2));
    				temp.add(0, root.getName());
    				return temp;
    			}
    			else
    			{
    				ArrayList<String> temp = new ArrayList<String>();
    				temp = preOrder(root.getChildren().get(1),passed);
    				ArrayList<String> temp2 = new ArrayList<String>();
    				temp.addAll(preOrder(root.getChildren().get(0), temp2));
    				temp.add(0, root.getName());
    				return temp;
    			}
    		}
    	}
    	
    	
    	
    	
    	return passed;
    }
    
}