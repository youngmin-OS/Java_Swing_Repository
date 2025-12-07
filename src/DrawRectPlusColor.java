import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class MyRect{
    int x,y,w,h;
    Color color;

    MyRect(int x, int y, int w, int h, Color color){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }
}

public class DrawRectPlusColor extends JFrame {
    private MyPanel panel = new MyPanel();
    DrawRectPlusColor(){
        setTitle("사각형 그리기 및 색 바꾸기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        panel.setLayout(new FlowLayout());
        createMenu();
        setSize(500,500);
        setVisible(true);
    }

    class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
        ArrayList<MyRect> rect = new ArrayList<MyRect>();
        MyRect cur = null;
        Point dragStart = null;
        Color curColor = Color.RED;

        MyPanel(){
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            for (MyRect r : rect) {
                g2.setColor(r.color);
                g2.fillRect(r.x, r.y, r.w, r.h);
                g2.setColor(Color.BLACK);
                g2.drawRect(r.x, r.y, r.w, r.h);
            }

            if(cur != null){
                g2.setColor(cur.color);
                g2.fillRect(cur.x, cur.y, cur.w, cur.h);
                g2.setColor(Color.BLACK);
                g2.drawRect(cur.x, cur.y, cur.w, cur.h);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            dragStart = e.getPoint();
            cur = null;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(cur != null && cur.w > 0 && cur.h > 0){
                rect.add(cur);
            }
            cur = null;
            dragStart = null;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            if(dragStart == null){
                return;
            }

            Point curp = e.getPoint();
            int x = Math.min(dragStart.x, curp.x);
            int y = Math.min(dragStart.y, curp.y);
            int w = Math.abs(dragStart.x - curp.x);
            int h = Math.abs(dragStart.y - curp.y);

            cur = new MyRect(x,y,w,h,curColor);
            repaint();
        }
        @Override
        public void mouseMoved(MouseEvent e) {}
    }

    private void createMenu(){
        JMenuBar mb = new JMenuBar();
        JMenu colorMenu = new JMenu("Color");

        JRadioButtonMenuItem redItem = new JRadioButtonMenuItem("Red");
        JRadioButtonMenuItem greenItem = new JRadioButtonMenuItem("Green");
        JRadioButtonMenuItem blueItem = new JRadioButtonMenuItem("Blue");

        ButtonGroup group = new ButtonGroup();
        group.add(redItem);
        group.add(greenItem);
        group.add(blueItem);

        redItem.setSelected(true);

        colorMenu.add(redItem);
        colorMenu.add(greenItem);
        colorMenu.add(blueItem);

        mb.add(colorMenu);
        setJMenuBar(mb);

        redItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.curColor = Color.RED;
            }
        });

        greenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.curColor = Color.GREEN;
            }
        });

        blueItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.curColor = Color.BLUE;
            }
        });
    }

    public static void main(String[] args) {
        new DrawRectPlusColor();
    }
}
