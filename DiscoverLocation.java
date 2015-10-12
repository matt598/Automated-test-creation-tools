import java.awt.MouseInfo;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

//will make a new version that updates when the mouse is clicked.
public class DiscoverLocation
{
    public static void main(String[] args) throws InterruptedException{
        while(true){
            Thread.sleep(100);
            System.out.println("("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")");
        }
    }
}
