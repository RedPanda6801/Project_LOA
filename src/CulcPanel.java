import javax.swing.*;
import java.awt.*;

class CulcPanel extends JPanel {
    private String[] stemp_arr = {};
    private Color bgColor = new Color(60,60,60);
    private Color listColor = new Color(120,120,120);
    CulcPanel(){
        this.setLayout(new BorderLayout());
        this.add(mainCulcPanel(), BorderLayout.WEST);
        this.add(resultCulcPanel(), BorderLayout.CENTER);
    }

    private JPanel resultCulcPanel() {
        JPanel pane = new JPanel();
        pane.setBackground(bgColor);
        return pane;
    }

    public JPanel mainCulcPanel(){
        JPanel pane = new JPanel();
        JPanel[] pane_arr = culcPanel();
        pane.setLayout(new GridLayout(2,3));
        for(int i = 0; i < 6; i++){
            JPanel subPane = pane_arr[i];
            pane.add(subPane);
        }
        return pane;
    }
    public JPanel[] culcPanel(){
        JPanel[] pane = new JPanel[6];
        for(int i = 0; i < 6; i++){
            pane[i] = new JPanel();
            pane[i].setBackground(listColor);
        }
        pane[0].add(new JLabel("목걸이"));
        pane[1].add(new JLabel("귀걸이1"));
        pane[2].add(new JLabel("귀걸이2"));
        pane[3].add(new JLabel("반지1"));
        pane[4].add(new JLabel("반지2"));
        pane[5].add(new JLabel("돌"));
        return pane;
    }
}
