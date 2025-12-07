import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingLineWithMouse extends JFrame {
    private MyPanel panel = new MyPanel();

    DrawingLineWithMouse(){
        setTitle("선그리기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);


        setSize(1000,1000);
        setVisible(true);
    }

    class RatioPoint{
        double xr;
        double yr;

        RatioPoint(double xr, double yr){
            this.xr = xr;
            this.yr = yr;
        }
    }

    class MyPanel extends JPanel implements MouseListener, MouseMotionListener{
        ArrayList<RatioPoint> points = new ArrayList<RatioPoint>();
        MyPanel(){
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            int w = getWidth();
            int h = getHeight();

            if(points.size() < 2){
                return;
            }

            for(int i = 0; i < points.size() - 1; i++){
                RatioPoint p1 = points.get(i);
                RatioPoint p2 = points.get(i + 1);

                int x1 = (int)(p1.xr * w);
                int y1 = (int)(p1.yr * h);
                int x2 = (int)(p2.xr * w);
                int y2 = (int)(p2.yr * h);

                g2.drawLine(x1,y1,x2,y2);
            }
        }

        private void addRatioPoint(MouseEvent e){
            int w = getWidth();
            int h = getHeight();
            if(w == 0 || h == 0) {
                return;
            }

            double xr = e.getX() / (double)w;
            double yr = e.getY() / (double)h;

            points.add(new RatioPoint(xr,yr));
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            addRatioPoint(e);
            repaint();
        }
        @Override
        public void mouseMoved(MouseEvent e) {}
    }

    public static void main(String[] args) {
        new DrawingLineWithMouse();
    }
}