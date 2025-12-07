import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EventDrivenSwing extends JFrame {
    JLabel txt;
    EventDrivenSwing(){
        setTitle("Event Driven Practice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        txt = new JLabel("Swing is Fun");
        add(txt, BorderLayout.SOUTH);

        EventDrivenPanel panel = new EventDrivenPanel();
        panel.frame = this;
        add(panel);

        setSize(300,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new EventDrivenSwing();
    }
}

class EventDrivenPanel extends JPanel implements ActionListener{
    JLabel txt;
    EventDrivenSwing frame;

    EventDrivenPanel(){
        setBackground(Color.orange);
        txt = new JLabel("Hahahah");
        add(txt);

        JButton but = new JButton("Push");
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Push me more");
            }
        };
        but.addActionListener(this);
        add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        txt.setText(txt.getText() + "!");
        frame.txt.setText(txt.getText() + "!");
    }
}


