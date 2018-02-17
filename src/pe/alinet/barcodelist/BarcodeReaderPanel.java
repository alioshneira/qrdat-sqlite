/*
 * Copyright (C) 2018 Aliosh Neira <aliosh2006 at gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pe.alinet.barcodelist;

/**
 *
 * @author Aliosh Neira <aliosh2006 at gmail.com>
 */

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class BarcodeReaderPanel extends WebcamPanel implements  Runnable, ThreadFactory{
    
    Webcam webcam;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public BarcodeReaderPanel(Webcam webcam) {
        super(webcam);
        this.webcam = webcam;
        executor.execute(this);

    }

    @Override
    public void run() {
        do {
            try {
                    Thread.sleep(100);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            
            BufferedImage image = null;
            
            
            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                        continue;
                }
                
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = null;
                String text = null;
                try {
                    result = new MultiFormatReader().decode(bitmap);
                    text = result.getText();

                } catch (NotFoundException e) {
                        // fall thru, it means there is no QR code in image
                    //Logger.getLogger(QRScannerFrame.class.getName()).log(Level.FINEST, null, e);
                }

                if (text != null){

                    //this.lbOut.setText(qrscanner.getText());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }
        while(true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "barcode-reader-panel");
        t.setDaemon(true);
        return t;

    }
    
}
