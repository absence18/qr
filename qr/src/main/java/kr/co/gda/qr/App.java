package kr.co.gda.qr;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class App extends JFrame{
	static Logger log = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws WriterException, IOException { // 원래는 main을 던지면 안됨..! (진입단 메소드)..
        log.info( "Hello World!" );
        
        // 1. QR에 어떤 콘텐츠(문자열 영문자 4000자 정도)를 추가 할 것인가? -> 다른 API를 통해서 획득
        // QRService qrService = new QRService()
        // String name = qrService.getUserName()
        QRService qrService = new QRService();
        String userName = qrService.getUserName();
        String systemInfo = qrService.getSystemInfo();
        String networkInfo = qrService.getNetworkInfo();
        
        StringBuffer contents = new StringBuffer();
        contents.append(userName);
        log.info(userName);
        contents.append(","+systemInfo);
        log.info(systemInfo);
        contents.append(","+networkInfo);
        log.info(networkInfo);
        // contents.append(",");
        
        // 2. QR 생성
        QRCodeWriter qrWriter = new QRCodeWriter();
        
        // QR 이미지 관련 (모양, 데이터) //
        // BitMatrix matrix = qrWriter.encode(콘텐츠, QR종류, 폭, 높이);
        BitMatrix matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 300, 300);
        
        // QR 설정 (색상) //
        // MatrixToImageConfig config = new MatrixToImageConfig(qr색상16진수(0x~), 배경색상);
        // MatrixToImageConfig config = new MatrixToImageConfig(0xFFFFFFFF, 0xFF000000); // 색상 Ox:16진수, FF:투명도, FF:R, FF:B
        
        // 위 두개의 설정 매개변수를 이용하여 이미지 생성 //
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
       
        // 3. QR 저장
        String imageFileName = "myqr.png";
        ImageIO.write(qrImage, "png", new File(imageFileName)); // 메모리 안의 이미지, 확장자, 파일생성
        
        // 4. QR 출력 -> web이면 jsp view -> pc App이면 swing frame -> android App이면 activity
        App app = new App();
        app.setTitle("QR");
        app.setLayout(new FlowLayout());
        
        ImageIcon icon = new ImageIcon(imageFileName);
        JLabel imageLabel = new JLabel(icon);
        app.add(imageLabel);
        
        app.setSize(400, 400);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
