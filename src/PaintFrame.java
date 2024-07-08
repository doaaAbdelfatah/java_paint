import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PaintFrame  extends JFrame {

    private JButton buttonLine , buttonRect , buttonOval , buttonColor;

    public PaintFrame(){
        setBounds(400,150,700,600);
        setTitle("Painting...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        JToolBar toolBar = new JToolBar("Paint ToolBar");
        toolBar.setPreferredSize(new Dimension(0,150));
        this.getContentPane().add(toolBar , BorderLayout.NORTH);



        JPanel panelClipboard = new JPanel();
        panelClipboard.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black ) , "Clipboard" , TitledBorder.CENTER , TitledBorder.BOTTOM));
        toolBar.add(panelClipboard);

        toolBar.addSeparator();
        JPanel panelShapes = new JPanel();
        panelShapes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black ) , "Shapes" , TitledBorder.CENTER , TitledBorder.BOTTOM));
        toolBar.add(panelShapes);

        buttonLine = new JButton("line");
        buttonRect = new JButton("Rect");
        buttonOval = new JButton("Oval");

        panelShapes.add(buttonLine);
        panelShapes.add(buttonRect);
        panelShapes.add(buttonOval);

        toolBar.addSeparator();

        JPanel panelColors = new JPanel();
        panelColors.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black ) , "Colors" , TitledBorder.CENTER , TitledBorder.BOTTOM));
        toolBar.add(panelColors);

        buttonColor = new JButton("Choose Color");
        panelColors.add(buttonColor);
        buttonColor.addActionListener(e->{
            Color selectedColor = JColorChooser.showDialog(null , "Choose Color" , Color.BLUE);
            System.out.println(selectedColor);
            panelColors.setBackground(selectedColor);
        });




    }
    public static void main(String[] args) {
        new PaintFrame().setVisible(true);
    }

}
