import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

class CharObject {
    private String name;
    private int level;
    private String job;
    CharObject(String name, int lv, String job){
        this.name = name;
        this.level = lv;
        this.job = job;
    }
    public String getName(){return name;}
    public int getLevel(){return level;}
    public String getJob(){return job;}
}

class ManagePanel extends JPanel { // 캐릭터 창을 띄우는 Panel
    private Color bgColor = new Color(60,60,60);
    private Color listColor = new Color(120,120,120);
    private ArrayList<CharObject> objArr;
    private Vector<String> strArr;
    private JList<String> strList;
    private JLabel name = new JLabel("이름 : ");
    private JLabel level = new JLabel("아이템레벨 : ");
    private JLabel classJob = new JLabel("클래스 : ");
    private JLabel contentProgress = new JLabel("컨텐츠 진행도 : ");
    private JLabel nameValue;
    private JLabel levelValue;
    private JLabel classValue;
    private JLabel contentValue; // 각 캐릭터에 대한 data를 필드로 선언 (추후에 수정될 것)
    private JTextField tfName = new JTextField(10);
    private JTextField tfLevel = new JTextField(10);
    private JTextField tfJob = new JTextField(10);
    private AddDialog dialog;
    private JPanel state;
    private JPanel statePanel;
    private JPanel content;
    ManagePanel(){
        this.nameValue = new JLabel("");
        this.levelValue = new JLabel("");
        this.classValue = new JLabel("");
        this.contentValue = new JLabel("");
        this.strArr = new Vector(); // 리스트의 문자열을 보관하는 백터 컨테이너
        this.strList = new JList(strArr); // 벡터 컨테이너로 만든 리스트
        strList.setBackground(listColor);
        this.objArr = new ArrayList(); // 각 캐릭터를 객체로 저장하는 리스트
        this.statePanel = new JPanel();
        this.statePanel.setLayout(new BorderLayout(20,20));
        // 초기화 파트

        this.strList.addListSelectionListener(new JListHandler());
        //리스너 추가 파트
        this.setBackground(bgColor);
        this.setLayout(new BorderLayout(20,0));
        this.add(createScrollbar(),BorderLayout.WEST);
        this.add(createMainStatePanel(),BorderLayout.CENTER);
    }

    public JPanel createScrollbar(){ // 캐릭터 리스트에 대한 패널
        JPanel scrollPane = new JPanel();
        JPanel scrollButton = new JPanel();
        JButton addBtn = new JButton("+");
        JButton delBtn = new JButton("-");
        scrollPane.setLayout(new BorderLayout(0,50));
        scrollButton.setLayout(new BorderLayout());
        scrollPane.setBackground(bgColor);
        addBtn.addActionListener(new DeleteHandler());
        delBtn.addActionListener(new DeleteHandler()); // 버튼에 리스너 추가

        scrollButton.add(addBtn, BorderLayout.WEST);
        scrollButton.add(delBtn, BorderLayout.EAST);
        scrollPane.add(strList,BorderLayout.CENTER);
        scrollPane.add(scrollButton, BorderLayout.SOUTH); // 패널에 컴포넌트 추가

        return scrollPane;
    }

    public JPanel createMainStatePanel(){ // 상태창을 보여주는 패널 생성 메소드
        JPanel stateMainPanel = new JPanel();
        stateMainPanel.setLayout(new BorderLayout(20,0));
        stateMainPanel.setBackground(bgColor);
        statePanel.add(createStatePanel(), BorderLayout.NORTH);
        statePanel.add(createContentPanel(), BorderLayout.CENTER);

        stateMainPanel.add(createImagePanel(), BorderLayout.WEST);
        stateMainPanel.add(statePanel,BorderLayout.CENTER); // 메인 레이아웃에 컴포넌트 추가

        return stateMainPanel;
    }
    public JLabel createImagePanel(){
        ImageIcon classImage = new ImageIcon("src/image/BattleMasterEdit.jpg");
        Image image = classImage.getImage();
        Image newimg = image.getScaledInstance(350, 450,  java.awt.Image.SCALE_SMOOTH);
        classImage = new ImageIcon(newimg);
        JLabel imageLabel = new JLabel(classImage); // 이미지 세팅

        return imageLabel;
    }
    public JPanel createStatePanel(){ // 상태창만 보여주는 패널 생성 메소드
        JPanel pane = new JPanel();
        JPanel content = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.setBackground(bgColor);
        pane.add(resetState(), BorderLayout.NORTH);
        pane.add(content, BorderLayout.CENTER);
        return pane;
    }
    public JPanel resetState(){
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(4,2,10,10));
        pane.setBackground(listColor);
        pane.add(name);
        pane.add(nameValue);
        pane.add(level);
        pane.add(levelValue);
        pane.add(classJob);
        pane.add(classValue);
        pane.add(contentProgress);
        pane.add(contentValue);

        return pane;
    }
    public JScrollPane createContentPanel(){
        String[] title = {"컨텐츠", "남은 횟수"};
        String[][] data = new String[9][2];
        data[0][0] = "에포나 의뢰";
        data[1][0] = "카오스 던전";
        data[2][0] = "가디언 토벌";
        data[3][0] = "어비스 던전";
        data[4][0] = "아르고스";
        data[5][0] = "발탄 노말/하드";
        data[6][0] = "비아 노말/하드";
        data[7][0] = "쿠크 노말/하드";
        data[8][0] = "아브렐 노말/하드";
        for(int i = 0; i < 9; i++){ data[i][1] = "-"; }

        JTable table = new JTable(data, title);
        table.setBackground(listColor);
        JScrollPane tablePanel = new JScrollPane(table);

        return tablePanel;
    }
    class AddDialog extends JDialog{
        private JButton check = new JButton("확인");
        private JButton cancel = new JButton("취소");
        public AddDialog(){
            setLayout(new FlowLayout());
            check.addActionListener(new OkListener());
            cancel.addActionListener(new OkListener());

            add(new JLabel("캐릭터 이름 :"));
            add(tfName);
            add(new JLabel("아이템 레벨 :"));
            add(tfLevel);
            add(new JLabel("클래스         :"));
            add(tfJob);
            add(check);
            add(cancel);

            setBounds(550,300,0,0);
            setSize(200,150);
        }
    }
    public class OkListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String btn_str = btn.getText();
            String str_name = tfName.getText();
            String str_level = tfLevel.getText();
            String str_job = tfJob.getText();
            tfName.setText("");
            tfLevel.setText("");
            tfJob.setText("");

            if(btn_str.equals("확인")){
                if (!availTest(str_name, str_level, str_job)){ return; } // 유효성 검사
                else{
                    int int_level = Integer.parseInt(str_level);
                    objArr.add(new CharObject(str_name, int_level, str_job));
                    strArr.add(str_name);
                    strList.setListData(strArr);
                }
            }
            dialog.dispose();
        }
    }
    public class JListHandler implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList)e.getSource();
            String selectStr = (String) list.getSelectedValue();
            for(int i = 0; i < objArr.size(); i++){
                CharObject tmpObj = objArr.get(i);
                if(selectStr.equals(tmpObj.getName())){
                    nameValue.setText(tmpObj.getName());
                    levelValue.setText(Integer.toString(tmpObj.getLevel()));
                    classValue.setText(tmpObj.getJob());
                    contentValue.setText("Sdfsdf");
                    statePanel.remove(state);
                    state = resetState();
                    statePanel.add(state, BorderLayout.NORTH);
                }
            }
        }
    }
    public class DeleteHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String btn_str = btn.getText();
            if(btn_str.equals("+")){
                dialog = new AddDialog();
                dialog.setVisible(true);
            }
            else{
                int result = JOptionPane.showConfirmDialog(null, "이 캐릭터를 지웁니다.");
                if(result == JOptionPane.CLOSED_OPTION){
                    System.out.println("나가짐");
                }
                else if(result == JOptionPane.YES_OPTION){
                    System.out.println("예");
                }
                else{
                    System.out.println("아니오");
                }
            }
        }
    }
    public boolean availTest(String name, String lv, String job){
        if(name.equals("")){
            JOptionPane.showMessageDialog(null, "이름을 입력하시오", "경고", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(lv.equals("")){
            JOptionPane.showMessageDialog(null, "아이템 레벨을 입력하시오", "경고", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(job.equals("")){
            JOptionPane.showMessageDialog(null, "클래스를 입력하시오", "경고", JOptionPane.WARNING_MESSAGE);;
            return false;
        }
        else return true;
    }

}