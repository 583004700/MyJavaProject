package logmonitor.mail;

import java.io.Serializable;

/**
 * 功能描述：
 * <p/>
 * <p/>
 * ----------------------------
 * 姓名：毛祥溢
 * 邮箱：sendmail2mao@gmail.com
 * 网站：www.maoxiangyi.cn
 */
public class MailCenterConstant implements Serializable {
    private static final long serialVersionUID = 472077651977893328L;
    public final static String PROTOCOL = "smtp";
    // 设置发件人使用的SMTP服务器、用户名、密码
    public final static String SMTP_SERVER = "smtp.163.com";
    public final static String FROM_ADDRESS = "itcast123itcast@163.com";
    public final static String USER = "itcast123itcast@163.com";
    public final static String PWD = "itcast123itcast";

}
