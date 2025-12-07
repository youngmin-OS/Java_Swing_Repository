import javax.swing.JFrame; // 최상위 컨테이너
import javax.swing.JPanel; //컨테이너
import javax.swing.JLabel;
import javax.swing.JButton;

class MyHelloSwingPanel extends JPanel{
    MyHelloSwingPanel(){
        JLabel label = new JLabel("Swing is Fun!!");
        JButton but1 = new JButton("Push!");
        JButton but2 = new JButton("Me too!");
        add(label);
        add(but1);
        add(but2);
    }
}

public class HelloSwing extends JFrame {
    public static void main(String [] arg) {
        new HelloSwing();
        System.out.println("hahaha!!!");
    }
    HelloSwing(){
        setTitle("Hello Swing");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new MyHelloSwingPanel());

        setVisible(true);
    }
}
