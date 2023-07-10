import java.awt.Color;
import java.util.ArrayList;

public class DragNode {
    private String name;
    private String value;
    private int x;
    private int y;
    private Color col;
    private String display;
    private int level;
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