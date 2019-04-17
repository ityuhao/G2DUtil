import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 绘图util
 * @Auther: yuhao
 * @Date: 2018/12/28 11:15
 * @Description:
 */
public class G2DUtil {

    /**
     * 居中计算
     * @param g2d
     * @param font 字体
     * @param width 宽度
     * @param str 内容
     * @return x开始位置
     */
    public static int center(Graphics2D g2d ,Font font,int width,String str ){
        int stringWidth = measuringLength(g2d, font, str);
        int widthX = (width - stringWidth) / 2;
        return widthX;
    }

    /**
     * 测量文字宽度
     * @param g2d
     * @param font
     * @param str
     * @return
     */
    public static int measuringLength(Graphics2D g2d ,Font font,String str){
        FontMetrics fontMetrics = g2d.getFontMetrics(font);
        // 字符串宽度
        int stringWidth = fontMetrics.stringWidth(str);
        return stringWidth;
    }



    /**
     * 打开抗锯齿
     * @param g2d
     */
    public static void antiAliasing(Graphics2D g2d){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 实现多行文本输出
     * 参考https://gitee.com/long1218/codes/k6fqcs3v497moz1l8jt0w45
     * @param g2d
     * @param text 文本
     * @param lineHeight 行高
     * @param maxWidth 行宽
     * @param maxLine 最大行数
     * @param left 左边距
     * @param top 上边距
     */
    public static void drawString(Graphics2D g2d, String text, float lineHeight, float maxWidth, int maxLine, float left,
                                   float top) {
        if (text == null || text.length() == 0) return;

        FontMetrics fm = g2d.getFontMetrics();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sb.append(c);
            int stringWidth = fm.stringWidth(sb.toString());
            if (c == '\n' || stringWidth > maxWidth) {
                if(c == '\n') {
                    i += 1;
                }
                if (maxLine > 1) {
                    g2d.drawString(text.substring(0, i), left, top);
                    drawString(g2d, text.substring(i), lineHeight, maxWidth, maxLine - 1, left, top + lineHeight);
                } else {
                    g2d.drawString(text.substring(0, i - 2) + "…", left, top);
                }
                return;
            }
        }
        g2d.drawString(text, left, top);
    }

    public static void drawImage(Graphics2D g2d,InputStream inputStream, int x, int y) throws IOException {
        g2d.drawImage(ImageIO.read(inputStream),x,y,null);
    }

    public static void drawImage(Graphics2D g2d,BufferedImage bufferedImage, int x, int y) throws IOException{
        g2d.drawImage(bufferedImage,x,y,null);
    }

    public static void drawImage(Graphics2D g2d,String imgUrl, int x, int y, int width, int height) throws IOException {
        BufferedImage read = ImageIO.read(new URL(imgUrl));
        Image scaledInstance = read.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        g2d.drawImage(scaledInstance,x,y,width,height,null);
    }

    public static void setFont(Graphics2D g2d,Font font) throws IOException{
        g2d.setFont(font);
    }
}
