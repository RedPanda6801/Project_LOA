import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    Main(){
        super("LostArk Scaduler (Beta)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();

        JTabbedPane pane = createTabPane();
        c.add(pane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setBounds(400,100,750,500);
        setVisible(true);
    }
    public JTabbedPane createTabPane(){
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("캐릭터 관리", new ManagePanel());
        pane.addTab("주간/일일 컨텐츠 스케줄러", new ContentPanel());
        pane.addTab("각인 계산기", new CulcPanel());
        pane.addTab("떠상 및 모험섬 시간표", new TimetablePanel());
        return pane;
    }

    public static void main(String[] args) {
        new Main();
    }
}
