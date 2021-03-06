package views.pages;

import command.entity.Operator;
import handler.Handler;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class ScreenPanel extends Operator {
    private boolean kz;

    private JLabel jlbImg;

    private String key;
    private ScreenFrame screenFrame;

    private InputStream tranInputStream;
    private Robot robot;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Image image;

    private volatile boolean imageSize = true;

    public ScreenPanel(boolean kz,String key, ScreenFrame screenFrame) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        this.kz = kz;
        this.screenFrame = screenFrame;
        this.setSize(screenFrame.getSize());
        this.jlbImg = new JLabel();
        this.key = key.intern();
        this.add(jlbImg);
        super.connect();
        setConnected(true);
        submitScrrentIn();

        if(!this.kz) {
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isControlDown()) {
                        changeSize();
                    }
                }

                MouseMotionListener mouseMotionListener = null;

                @Override
                public void mousePressed(final MouseEvent e1) {
                    mouseMotionListener = new MouseMotionAdapter() {
                        int oldX = e1.getX();
                        int oldY = e1.getY();
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            int rightMove = e.getX() - oldX;
                            int bottomMove = e.getY() - oldY;
                            moveImage(rightMove,bottomMove);
                            oldX = e.getX();
                            oldY = e.getY();
                        }
                    };
                    ScreenPanel.this.addMouseMotionListener(mouseMotionListener);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ScreenPanel.this.removeMouseMotionListener(mouseMotionListener);
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }else{
            imageSize = false;
            this.setFocusable(true);
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    //submitCommand(Handler.keyPress+Handler.separator+keyCode);
                    sendMessage(Handler.keyPress+Handler.separator+keyCode);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    //submitCommand(Handler.keyRelease+Handler.separator+keyCode);
                    sendMessage(Handler.keyRelease+Handler.separator+keyCode);
                }
            });
            this.addMouseListener(new MouseAdapter() {
                MouseMotionListener mouseMotionListener = null;
                @Override
                public void mousePressed(final MouseEvent e) {
                    int button = e.getButton();
                    //submitCommand(Handler.mousePress+Handler.separator+button);
                    sendMessage(Handler.mousePress+Handler.separator+button);
                    mouseMotionListener = new MouseMotionAdapter() {
                        int oldX = e.getX();
                        int oldY = e.getY();
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            if(e.isControlDown() && e.isShiftDown() && e.isAltDown()){
                                int rightMove = e.getX() - oldX;
                                int bottomMove = e.getY() - oldY;
                                moveImage(rightMove,bottomMove);
                                oldX = e.getX();
                                oldY = e.getY();
                            }else if(ScreenPanel.this.image != null) {
                                int x = e.getX();
                                int y = e.getY();
                                x = x - ScreenPanel.this.x;
                                y = y - ScreenPanel.this.y;
                                sendMousePoint(x,y);
                            }
                        }
                    };
                    ScreenPanel.this.addMouseMotionListener(mouseMotionListener);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ScreenPanel.this.removeMouseMotionListener(mouseMotionListener);
                    int button = e.getButton();
                    //submitCommand(Handler.mouseRelease+Handler.separator+button);
                    sendMessage(Handler.mouseRelease+Handler.separator+button);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isControlDown() && e.isShiftDown() && e.isAltDown()) {
                        changeSize();
                    }
                }
            });

            this.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if(ScreenPanel.this.image != null) {
                        int x = e.getX();
                        int y = e.getY();
                        x = x - ScreenPanel.this.x;
                        y = y - ScreenPanel.this.y;
                        sendMousePoint(x,y);
                    }
                }
            });

            this.addMouseWheelListener(new MouseAdapter() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int amount = e.getWheelRotation() > 0 ? e.getScrollAmount() : -e.getScrollAmount();
                    sendMessage(Handler.mouseWheelMove + Handler.separator + amount);
                }
            });
        }
    }

    public void changeSize(){
        this.imageSize = !this.imageSize;
        this.x = 0;
        this.y = 0;
        this.repaint();
    }

    public void moveImage(int rightMove,int bottomMove){
        this.x += rightMove;
        this.y += bottomMove;
        this.repaint();
    }

    public void sendMousePoint(int x,int y){
        BigDecimal decimal1 = new BigDecimal(x);
        BigDecimal decimal2 = new BigDecimal(this.drawWidth);
        BigDecimal decimal3 = new BigDecimal(y);
        BigDecimal decimal4 = new BigDecimal(this.drawHeight);
        double leftBfb = decimal1.divide(decimal2, 100, RoundingMode.HALF_DOWN).doubleValue();
        double bottomBfb = decimal3.divide(decimal4, 100,RoundingMode.HALF_DOWN).doubleValue();
        //submitCommand(Handler.mouseMove + Handler.separator + leftBfb + "," + bottomBfb);
        robot.delay(50);
        sendMessage(Handler.mouseMove + Handler.separator + leftBfb + "," + bottomBfb);
    }

    public void submitScrrentIn() {
        submitCommand(Handler.OPERATE + Handler.separator + this.key + "SC:");
        String uuid = UUID.randomUUID().toString();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        screenIn(this, Handler.SCREENIN + Handler.separator + uuid + ">" + uuid);
    }

    public void setImage(InputStream inputStream,PrintWriter downSocketWrite) {
        this.screenFrame.setTitle("已连接：" + this.key + "   正在获取屏幕...");
        this.tranInputStream = inputStream;
        try {
            byte[] b = new byte[1024];
            int len = 0;
            byte[] l = new byte[4];
            while (true) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //数据原始长度
                //System.out.println("将读取dataLength");
                int d = inputStream.read(l);
                int dataLength = d < 4 ? 0 : BitUtils.bytesToInt(l, 0);
                //System.out.println("已读取dataLength："+dataLength);
                if (dataLength <= 0) {
                    System.out.println("读取dataLength失败");
                    break;
                }
                //已经读取的长度
                int readLength = 0;
                while (dataLength > readLength && (len = inputStream.read(b)) != -1) {
                    byteArrayOutputStream.write(b, 0, len);
                    readLength += len;
                    //System.out.println("readLength:"+readLength);
                }
                //ThreadManager.getExecutorService().submit(new SetImageThread(this,jlbImg).setByteArrayOutputStream(byteArrayOutputStream));
                image = ImageIO.read(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                this.repaint();
                Thread.sleep(10);
                downSocketWrite.println("f");
                downSocketWrite.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.screenFrame.setTitle("未连接");
            stop();
            System.out.println("setImage方法退出");
        }
    }

    private volatile int x = 0;
    private volatile int y = 0;
    private volatile int drawWidth = 0;
    private volatile int drawHeight = 0;

    @Override
    public void paint(Graphics g) {
        if(image != null) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            int imageWidth = image.getWidth(this);
            int imageHeight = image.getHeight(this);
            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();
            if(this.x > 0){
                this.x = 0;
            }
            if(this.y > 0){
                this.y = 0;
            }
            if(imageSize){
                drawWidth = imageWidth;
                drawHeight = imageHeight;
            }else{
                drawWidth = panelWidth;
                drawHeight = panelHeight;
            }
            if(this.x < panelWidth - drawWidth){
                this.x = panelWidth - drawWidth;
            }
            if(this.y < panelHeight - drawHeight){
                this.y = panelHeight - drawHeight;
            }
            g.drawImage(image, x, y,drawWidth , drawHeight, null);
        }
    }

    public void stop() {
        try {
            if (tranInputStream != null) {
                this.tranInputStream.close();
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitT() {
        stop();
    }

    public JLabel getJlbImg() {
        return jlbImg;
    }

    public void setJlbImg(JLabel jlbImg) {
        this.jlbImg = jlbImg;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ScreenFrame getScreenFrame() {
        return screenFrame;
    }

    public void setScreenFrame(ScreenFrame screenFrame) {
        this.screenFrame = screenFrame;
    }

    public static Dimension getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(Dimension screenSize) {
        ScreenPanel.screenSize = screenSize;
    }

    public String getKey() {
        return key;
    }
}
