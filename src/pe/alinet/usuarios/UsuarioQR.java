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
package pe.alinet.usuarios;

/**
 *
 * @author Aliosh Neira <aliosh2006 at gmail.com>
 */

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class UsuarioQR {
    
    public static void main(String[] args){
        try {
            QRCode qrcode = Encoder.encode("GABY TE AMO <3 ALIOSH", com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);
            ByteMatrix matriz = qrcode.getMatrix();
            byte[][] array = matriz.getArray();

            int height = matriz.getHeight();
            int width = matriz.getWidth();
            
            for (int i =0; i < width; i++){
                for (int j =0; j < height; j++){
                    Byte b = matriz.get(i, j);
                    System.out.println(i+","+j+":"+b.toString());
                }
            }
            
            BufferedImage image = new BufferedImage(width*5+100, height*5+100, BufferedImage.TYPE_INT_RGB);

            int h = 5;
            int p = 10;
            //iterate through the matrix and draw the pixels to the image
            
            Graphics2D g = image.createGraphics();
            
            

            g.setColor(Color.white);
            g.fillRect(0, 0, width*5+100, height*5+100);
            
            
            for (int y = 0; y < height; y++) { 
                for (int x = 0; x < width; x++) { 
                    Byte b = matriz.get(x, y); 

                    for (int m=0; m<h; m++){
                        for (int n=0; n<h; n++){
                            image.setRGB(p+h*x+m, p+h*y+n, (b.intValue()==0 ? 0xFFFFFF : 0));
                        }                        
                    }
                }
            }
            
            g.setColor(Color.BLUE);
            g.drawString("ALIOSH NEIRA RAMIREZ",10 , 160);
            
            FileOutputStream outputStream;
            outputStream = new FileOutputStream("C:\\Users\\Admin\\prueba.png");
            ImageIO.write(image, "png", outputStream);
            outputStream.close();

        } catch (WriterException ex) {
            Logger.getLogger(UsuarioQR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuarioQR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioQR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
