package views.pages;

import handler.Handler;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CMDFrame extends JFrame {
    private JTabbedPane jTabbedPane = new JTabbedPane();

    private JMenuBar jMenuBar = new JMenuBar();

    private static Map<String,FileListFrame> fileListFrameMap = new ConcurrentHashMap<String, FileListFrame>();
    private static Map<String,ScreenFrame> screenFrameMap = new ConcurrentHashMap<String, ScreenFrame>();

    public CMDFrame(){

        JMenu fileMenu = new JMenu("   文件   ");
        fileMenu.setSize(100,30);
        JMenuItem openMenuItem = new JMenuItem(" 选择连接 ");
        final JMenuItem fileCsMenuItem = new JMenuItem(" 文件传输 ");
        final JMenuItem screenMenuItem = new JMenuItem(" 屏幕监控 ");

        JMenuItem screenTr = new JMenuItem("截图上传");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jTabbedPane.getTabCount() == 0){
                    addPanel("未连接",new CMDPanel(CMDFrame.this));
                }
                getCurrentSelectTab().submitCommand(Handler.LIST+Handler.separator);
            }
        });
        fileCsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentSelectTab() == null || "".equals(getCurrentSelectTab().getCurrentConnectKey())) {
                    JOptionPane.showMessageDialog(CMDFrame.this, "请先选中连接!");
                }else{
                    final String key = getCurrentSelectTab().getCurrentConnectKey();
                    if(fileListFrameMap.containsKey(key)) {
                        FileListFrame fileListFrame = fileListFrameMap.get(key);
                        fileListFrame.show();
                    }else{
                        final FileListFrame fileListFrame = new FileListFrame(key);
                        fileListFrameMap.put(key,fileListFrame);
                        fileListFrame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                int value = JOptionPane.showConfirmDialog(null, "确定要关闭吗？");
                                if (value == JOptionPane.OK_OPTION) {
                                    fileListFrame.stopRemoteFileT();
                                    fileListFrame.dispose();
                                    CMDFrame.fileListFrameMap.remove(key);
                                }
                            }
                        });
                    }
                }
            }
        });

        screenMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentSelectTab() == null || "".equals(getCurrentSelectTab().getCurrentConnectKey())) {
                    JOptionPane.showMessageDialog(CMDFrame.this, "请先选中连接!");
                }else{
                    final String key = getCurrentSelectTab().getCurrentConnectKey();
                    if(screenFrameMap.containsKey(key)) {
                        ScreenFrame fileListFrame = screenFrameMap.get(key);
                        fileListFrame.show();
                    }else{
                        final ScreenFrame screenFrame = new ScreenFrame(key,CMDFrame.this);
                        screenFrameMap.put(key,screenFrame);
                        screenFrame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                int value = JOptionPane.showConfirmDialog(null, "确定要关闭吗？");
                                if (value == JOptionPane.OK_OPTION) {
                                    screenFrame.stopRemoteFileT();
                                    screenFrame.dispose();
                                    CMDFrame.screenFrameMap.remove(key);
                                }
                            }
                        });
                    }
                }
            }
        });

        screenTr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentSelectTab() == null || "".equals(getCurrentSelectTab().getCurrentConnectKey())) {
                    JOptionPane.showMessageDialog(CMDFrame.this, "请先选中连接!");
                }else{
                    String key = getCurrentSelectTab().getCurrentConnectKey();
                    getCurrentSelectTab().submitCommand("java:command.entity.JavaMethod.screenPrintUp(null,null)");
                }
            }
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(fileCsMenuItem);
        fileMenu.add(screenTr);
        fileMenu.add(screenMenuItem);
        jMenuBar.add(fileMenu);

        Container container = this.getContentPane();
        container.add(jTabbedPane);
        container.add(jMenuBar, BorderLayout.NORTH);

        setSize(1200, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 添加面板
     */
    public void addPanel(String name,CMDPanel cmdPanel){
        jTabbedPane.addTab(name,cmdPanel);
        this.jTabbedPane.setSelectedComponent(cmdPanel);
    }

    public CMDPanel getCurrentSelectTab(){
        return (CMDPanel) jTabbedPane.getSelectedComponent();
    }

    /**
     * get set
     */
    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }
}
