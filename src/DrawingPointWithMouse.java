import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingPointWithMouse extends JFrame {
    private MyPanel panel = new MyPanel();
    private int pointCount = 0;
    private int pressedButton = 0;

    DrawingPointWithMouse(){
        setTitle("점그리기, 개수도 세기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(panel);

        MyMouseListener listener = new MyMouseListener();
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        setSize(300,300);
        setVisible(true);
    }

    class MyMouseListener implements MouseListener, MouseMotionListener{
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            pressedButton = e.getButton();
            if (pressedButton == MouseEvent.BUTTON3) {   // 오른쪽 버튼 누른 순간
                panel.dragStart = e.getPoint();          // ★ 시작점 기억
                panel.erase = null;                      // 이전 사각형 초기화 (선택)
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (pressedButton == MouseEvent.BUTTON3 && panel.erase != null) {
                Rectangle r = panel.erase;

                // 람다 버전 (네가 배웠다고 했으니까 이대로 써도 됨)
                panel.points.removeIf(p -> r.contains(p));
                pointCount = panel.points.size();

                panel.erase = null;
                panel.dragStart = null;
                panel.repaint();
            }

            pressedButton = 0;   // ★ 다음 드래그를 위해 초기화
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            if (pressedButton == MouseEvent.BUTTON1) {
                // 왼쪽 드래그 → 점 찍기
                panel.points.add(e.getPoint());
                pointCount++;
                panel.repaint();
            }
            else if (pressedButton == MouseEvent.BUTTON3) {
                // 오른쪽 드래그 → 영역 박스 만들기

                if (panel.dragStart == null) {          // 혹시라도 null이면
                    panel.dragStart = e.getPoint();     // 현재 지점으로 초기화
                }

                Point cur = e.getPoint();
                int x = Math.min(panel.dragStart.x, cur.x);
                int y = Math.min(panel.dragStart.y, cur.y);
                int w = Math.abs(panel.dragStart.x - cur.x);
                int h = Math.abs(panel.dragStart.y - cur.y);

                panel.erase = new Rectangle(x, y, w, h);
                panel.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
    class MyPanel extends JPanel{
        ArrayList<Point> points = new ArrayList<Point>();
        Rectangle erase = null;
        Point dragStart = null;

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            for (Point p : points) {
                g.fillOval(p.x - 2, p.y - 2, 2, 2);
            }

            if(erase != null){
                ((Graphics2D) g).draw(erase);
            }

            g.drawString("점 개수 : " + pointCount, 10, 20);
        }
    }

    public static void main(String[] args) {
        new DrawingPointWithMouse();
    }
}
