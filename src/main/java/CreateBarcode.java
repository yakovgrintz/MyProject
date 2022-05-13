import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.encoder.QRCode;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateBarcode {
    private QRCode _barcode;
    private String _urlToSave;
    public static final String URL_FOLDER="C:\\projects\\";//path of folder of QRcode
    public static final String JPG=".jpg";//
    public static final String CHAREST = "UTF-8";
    public CreateBarcode(String idOFstudents)
    {
        String path=URL_FOLDER+idOFstudents+JPG;
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(idOFstudents.getBytes(CHAREST), CHAREST),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
        _barcode = new QRCode();

// Set barcode data text to encode
        _barcode.setData(idOFstudents);

// Set barcode data text to encode
        _barcode.setX(2);
        _urlToSave=urlFolder+idOFstudents+JPG;
        try {
            _barcode.drawBarcode(_urlToSave);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public String getURL(){
        return _urlToSave;
    }
    public static String readQR(String path)
            throws FileNotFoundException, IOException,
            NotFoundException
    {
        BinaryBitmap binaryBitmap
                = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(
                                new FileInputStream(path)))));

        Result result
                = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }
}
