import java.awt.Color;
import java.util.ArrayList;

public class DragNode {
    private String name;
    private int x;
    private int y;
    private Color col;

    public DragNode(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.col = Color.white;
    }

    public String getName() {  
        return name;
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
}