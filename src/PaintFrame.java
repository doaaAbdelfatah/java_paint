import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class PaintFrame  extends JFrame {
    private JButton buttonLine ,buttonPen , buttonEraser  ,buttonText , buttonRect ,buttonSqr , buttonOval ,buttonCircle, buttonColor , buttonBackGroundColor , buttonForGroundColor;
    private  JButton [] colorsArray ;
    private  PaintPanel paintPanel;
    private  ArrayList<MyShape> shapes ;
    private  String selectedType ="Line";
    private  boolean fillShape = true;

    private int x1 ,x2 , y1,y2 , shapeStrokWidth;

    ImageIcon penIcon;

    private String selectedColorButton = "for";
    public PaintFrame(){
        penIcon=new ImageIcon("C:\\Users\\dode3\\IdeaProjects\\MyPaintProgram\\src\\pen.png");
        shapes = new ArrayList<>();

        setBounds(400,150,700,600);
        setTitle("Painting...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        JToolBar toolBar = new JToolBar("Paint ToolBar");
        toolBar.setPreferredSize(new Dimension(0,150));
        this.getContentPane().add(toolBar , BorderLayout.NORTH);

        Dimension dimension  = new Dimension(70,50);
        Border border = BorderFactory.createLineBorder(Color.GRAY);

        JPanel panelClipboard = new JPanel();
        panelClipboard.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black ) , "Clipboard" , TitledBorder.CENTER , TitledBorder.BOTTOM));
        toolBar.add(panelClipboard);

        toolBar.addSeparator();
        JPanel panelShapes = new JPanel();
        panelShapes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black ) , "Shapes" , TitledBorder.CENTER , TitledBorder.BOTTOM));
        toolBar.add(panelShapes);

        buttonPen = new JButton(penIcon);
        buttonPen.setActionCommand("Pen");
        buttonEraser = new JButton("Eraser");
        buttonLine = new JButton("Linexx"); buttonLine.setActionCommand("Line");
        buttonRect = new JButton("Rect");
        buttonSqr = new JButton("Square");
        buttonOval = new JButton("Oval");
        buttonCircle = new JButton("Circle");
        buttonText = new JButton("Text");

        BtnAction btnAction = new BtnAction();

        panelShapes.add(buttonPen);
        panelShapes.add(buttonEraser);
        panelShapes.add(buttonLine);
        panelShapes.add(buttonRect);
        panelShapes.add(buttonSqr);
        panelShapes.add(buttonOval);
        panelShapes.add(buttonCircle);
        panelShapes.add(buttonText);

        for (Component component : panelShapes.getComponents()){
            if ( component instanceof JButton){
                JButton button = (JButton) component;
                button.setPreferredSize(dimension);
                button.setBorder(border);
                button.setContentAreaFilled(false);
                button.addActionListener(btnAction);
            }
        }

        String [] thikness = {"1" ,"2" ,"3" ,"4" , "5" , "6" ,"7" ,"8" , "9" ,"10" , "20" , "50"};
        JComboBox<String> comboBox = new JComboBox<>(thikness);
        comboBox.setPreferredSize(new Dimension(100, 50));
        panelShapes.add(comboBox);
        comboBox.addItemListener(e -> {
            String selectedItem =e.getItem().toString();
            shapeStrokWidth = Integer.parseInt(selectedItem);
        });

        JCheckBox fillCheckBox = new JCheckBox("Fill");
        fillCheckBox.setSelected(fillShape);
        panelShapes.add(fillCheckBox);
        fillCheckBox.addActionListener(e->{
            fillShape= fillCheckBox.isSelected();
        });
        toolBar.addSeparator();

        JPanel panelColors = new JPanel();
        panelColors.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black ) , "Colors" , TitledBorder.CENTER , TitledBorder.BOTTOM));
        toolBar.add(panelColors);

        buttonForGroundColor = new JButton();
        buttonBackGroundColor= new JButton();
        buttonForGroundColor.setBackground(Color.BLACK);
        buttonBackGroundColor.setBackground(Color.WHITE);
        buttonForGroundColor.setPreferredSize(new Dimension(40,70));
        buttonBackGroundColor.setPreferredSize(new Dimension(40,70));
        panelColors.add(buttonForGroundColor);
        panelColors.add(buttonBackGroundColor);

        buttonForGroundColor.addActionListener(e->{selectedColorButton = "for";});
        buttonBackGroundColor.addActionListener(e->{selectedColorButton = "back";});

        JPanel panelColorGrid = new JPanel(new GridLayout(2,6,2,2));
        panelColorGrid.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        colorsArray = new JButton[12];
        Color[] selectdColors   = {Color.white , Color.BLACK , Color.BLUE ,Color.RED , Color.PINK
                ,Color.CYAN ,Color.yellow ,Color.orange ,Color.GREEN ,Color.DARK_GRAY ,Color.GRAY ,Color.magenta };
        Random random= new Random();
        for (int i = 0; i <colorsArray.length ; i++) {
            colorsArray[i] = new JButton();
//            int r =random.nextInt(255);
//            int g =random.nextInt(255);
//            int b =random.nextInt(255);
            colorsArray[i].setPreferredSize(new Dimension(30,30));
            colorsArray[i].setBorder(border);
//            colorsArray[i].setBackground(new Color(r,g,b));
            colorsArray[i].setBackground(selectdColors[i]);
            panelColorGrid.add( colorsArray[i]);

            colorsArray[i].addActionListener(e->{
                JButton b = (JButton) e.getSource();
                if (selectedColorButton.equals("for")){
                    buttonForGroundColor.setBackground(b.getBackground());
                }else{
                    buttonBackGroundColor.setBackground(b.getBackground());
                }
            });
        }
        panelColors.add(panelColorGrid);

        buttonColor = new JButton("Color");
        buttonColor.setContentAreaFilled(false);
        buttonColor.setPreferredSize(dimension);
        panelColors.add(buttonColor);
        buttonColor.addActionListener(e->{
            Color selectedColor = JColorChooser.showDialog(null , "Choose Color" , Color.BLUE);
            if (selectedColorButton.equals("for")){
                buttonForGroundColor.setBackground(selectedColor);
            }else{
                buttonBackGroundColor.setBackground(selectedColor);
            }
        });
        paintPanel=new PaintPanel();
        paintPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        paintPanel.addMouseListener(paintPanel);
        paintPanel.addMouseMotionListener(paintPanel);
        paintPanel.setBackground(Color.white);
        this.getContentPane().add( paintPanel,BorderLayout.CENTER);


    }
    public static void main(String[] args) {
        new PaintFrame().setVisible(true);
    }


    class PaintPanel extends JPanel implements MouseListener , MouseMotionListener {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            System.out.println("inside paint componet");
            Graphics2D graphics2D = (Graphics2D) g;

            for (MyShape shape :shapes){
                int w = Math.abs( shape.x2 - shape.x1);
                int h = Math.abs(shape.y2 - shape.y1);
                graphics2D.setStroke(new BasicStroke(shape.strokWidth));

                switch (shape.type){
                    case "Text":
                        if(shape.text != null){
                            System.out.println(shape.text);
                            graphics2D.setColor(shape.forGroundColor);
                            graphics2D.setFont(new Font("Tahoma" ,Font.BOLD , shape.strokWidth));
                            graphics2D.drawString(shape.text , shape.x1 ,shape.y1);

                        }

                        break;
                    case "Square":
                        h =w;
                    case "Rect" :
                        Rectangle2D rectangle2D = new Rectangle2D.Float(shape.x1,shape.y1,w,h);
                        graphics2D.setColor(shape.backGroundColor);
                        if(shape.fill) graphics2D.fill(rectangle2D);
                        graphics2D.setColor(shape.forGroundColor);
                        graphics2D.draw(rectangle2D);
                        break;
                    case  "Circle":
                        h =w;
                    case "Oval" :
                        Ellipse2D ellipse2D = new Ellipse2D.Float(shape.x1,shape.y1,w,h);
                        graphics2D.setColor(shape.backGroundColor);
                        if(shape.fill) graphics2D.fill(ellipse2D);
                        graphics2D.setColor(shape.forGroundColor);
                        graphics2D.draw(ellipse2D);
                        break;

                    case "Line" :
                        Line2D line2D = new Line2D.Float(shape.x1,shape.y1,shape.x2,shape.y2);
                        graphics2D.setColor(shape.forGroundColor);
                        graphics2D.draw(line2D);
                        break;
                    case "Pen" :case "Eraser" :
                        Ellipse2D rectangle2D2 = new Ellipse2D.Float(shape.x1,shape.y1,shape.strokWidth,shape.strokWidth);
//                        Rectangle2D rectangle2D2 = new Rectangle2D.Float(shape.x1,shape.y1,shape.strokWidth,shape.strokWidth);
                        graphics2D.setColor(shape.forGroundColor);
                        graphics2D.fill(rectangle2D2);

                        break;

//                        Line2D line2D2 = new Line2D.Float(shape.x1,shape.y1,shape.x1 + shape.strokWidth,shape.y1 + shape.strokWidth);
//                        graphics2D.setColor(shape.forGroundColor);
//                        graphics2D.draw(line2D2);
//                        break;
            }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
                if (selectedType.equals("Text")){
                    String msg =JOptionPane.showInputDialog(null , "Enter your text");
                    MyShape shape = new MyShape();
                    shape.type =selectedType;
                    shape.text = msg;
                    shape.forGroundColor = buttonForGroundColor.getBackground();
                    shape.strokWidth = shapeStrokWidth ;
                    shape.x1 =e.getX();
                    shape.y1 =e.getY();
                    shapes.add(shape);
                    paintPanel.repaint();
                }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();


        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();

            MyShape shape = new MyShape();
            shape.type =selectedType;
            shape.backGroundColor = buttonBackGroundColor.getBackground();
            shape.forGroundColor = buttonForGroundColor.getBackground();
            shape.strokWidth = shapeStrokWidth ;
            shape.x1 =x1;
            shape.y1 =y1;
            if (x1 <x2 && y1 > y2  ){
                shape.x2 = x2;
                shape.y2 = shape.y1;
                shape.y1 = y2;
            }else if (x1 > x2 && y1 > y2  ){
                shape.x2 =shape.x1;
                shape.x1 =x2;
                shape.y2 = shape.y1;
                shape.y1 = y2;
            }else if (x1 > x2 && y1 < y2  ){
                shape.x2 =shape.x1;
                shape.x1 =x2;
                shape.y2 = y2;
            }else{
                shape.x2 =x2;
                shape.y2 =y2;
            }

            shape.fill = fillShape;
            shapes.add(shape);
            paintPanel.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (selectedType.equals("Pen") || selectedType.equals("Eraser")){

                MyShape shape = new MyShape();
                shape.type =selectedType;
                if(selectedType.equals("Eraser")){
                    shape.forGroundColor = Color.white;
                }else{
                    shape.forGroundColor = buttonForGroundColor.getBackground();
                }

                shape.strokWidth = shapeStrokWidth ;
                shape.x1 =e.getX();
                shape.y1 =e.getY();
                shape.fill = fillShape;
                shapes.add(shape);
                paintPanel.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }


    class MyShape {

        String text ;
        Color backGroundColor ;
        Color forGroundColor ;

        String type;

        int strokWidth;

        int x1 , x2 , y1 ,y2;

        boolean fill;
    }

    class  BtnAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedType = e.getActionCommand();

            switch (selectedType){
                case "Text":
                    paintPanel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
                    break;
                case "Pen":
                    paintPanel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(penIcon.getImage() , new Point(0,0) ,"pen"));
                    break;
                default:
                    paintPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

            }

            System.out.println(e.getActionCommand());
        }
    }

}
