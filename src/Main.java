import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //endereço da imagem de entrada
        File file = new File("C:\\Users\\Eduardo Braz\\Downloads\\img3.jpg");
        File file2 = new File("C:\\Users\\Eduardo Braz\\Downloads\\img1.jpg");

        //inicializa uma instância de Bufferediamge
        BufferedImage img = null, img2 = null;
//        Rgb rgb = new Rgb(255, 255,255);
//        Hsb hsb = rgb.toHsb();
//        System.out.println("rgb to hsb:::::");
//        System.out.println((hsb.getHue()));
//        System.out.println(hsb.getSaturation());
//        System.out.println(hsb.getBrightnes());
//        Rgb rgb2 = hsb.toRgb();
//        System.out.println("rgb to hsb:::::");
//        System.out.println(rgb2.getRed());
//        System.out.println(rgb2.getBlue());
//        System.out.println(rgb2.getGreen());

        //lê a imagem de entrada e executa o tópico 1 da atividade
        try{
            img = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace(System.out);
        }
        try{
            img2 = ImageIO.read(file2);
        }catch(IOException e){
            e.printStackTrace(System.out);
        }

        if(img != null && img2 != null){
            display(img);
            //display(saturation(img, img2));
            //display(convertRGB(img));
            display(media2(img, 10, 10));
        }
    }
    public static void display (BufferedImage img){
        System.out.println("... Displaying Image ...");
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        frame.setSize(img.getWidth(), img.getHeight());
        label.setIcon(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static BufferedImage convertRGB(BufferedImage img){
        System.out.println("... Converting Image ...");
        //inicializa imagemRetorno que armazenara a iamgem de saída
        BufferedImage imagemRetorno = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        //inicializa as variáveis para "pegar" os valores de cada pixel
        int rgb, r, g, b;

        //inicializa instancias das classes rgb e hsb para que o método de conversão de cores seja chamado
        Rgb rgbColor = new Rgb(0,0,0);
        Hsb hsbColor;

        //inicializa a varredura pixel a pixel
        for (int linha=0; linha<img.getHeight(); linha++){
            for (int pixel=0; pixel<img.getWidth(); pixel++){

                //recebe as 3 bandas do pixel na variavel rgb
                rgb = (img.getRGB(pixel, linha));
                //armazena em cr, g e b os valoesr correspondentes a cada banda de cor
                r = ((rgb>>16) & 0xFF);
                g = ((rgb>>8) & 0xFF);
                b = (rgb & 0xFF);

                //seta em rgbColor os valores de R, g e b obtidos do pixel
                rgbColor.setRed(r);
                rgbColor.setGreen(g);
                rgbColor.setBlue(b);

                //atribui a hsbColor o valor de rgbColor após a chamada o método de conversão de rgb para hsb
                hsbColor = rgbColor.toHsb();

                //atribui a rgbColor o valor de hsbColor após a chamada do método de conversão de hsb para rgb
                rgbColor = hsbColor.toRgb();

                //atribuo a rgb os valores de r, g e b contindos em rgbColor após as conversões
                rgb=  ((int)rgbColor.getRed() << 16) | ((int)rgbColor.getGreen() << 8) | (int)rgbColor.getBlue();

                //insere o pixel convertido (rgb-hsb-rgb) na imagem de saída
                imagemRetorno.setRGB(pixel, linha, rgb);
            }
        }
        //ao fim do loop uma imagem de saída idêntica a de entrada é retornada
        return imagemRetorno;
    }
    public static BufferedImage saturation(BufferedImage img, BufferedImage img2){
        System.out.println("... Converting Image ...");
        //inicializa imagemRetorno que armazenara a iamgem de saída
        BufferedImage imagemRetorno = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        //inicializa as variáveis para "pegar" os valores de cada pixel
        int rgb, r, g, b, rgb2, r2, g2, b2;

        //inicializa instancias das classes rgb e hsb para que o método de conversão de cores seja chamado
        Rgb rgbColor = new Rgb(0,0,0), rgbColor2 = new Rgb(0,0,0);
        Hsb hsbColor, hsbColor2;

        //inicializa a varredura pixel a pixel
        for (int linha=0; linha<img.getHeight(); linha++){
            for (int pixel=0; pixel<img.getWidth(); pixel++){

                //recebe as 3 bandas do pixel na variavel rgb
                rgb = (img.getRGB(pixel, linha));

                r = ((rgb>>16) & 0xFF);
                g = ((rgb>>8) & 0xFF);
                b = (rgb & 0xFF);

                rgbColor.setRed(r);
                rgbColor.setGreen(g);
                rgbColor.setBlue(b);

                rgb2 = (img2.getRGB(pixel,linha));
                //armazena em cr, g e b os valoesr correspondentes a cada banda de cor

                r2 = ((rgb2>>16) & 0xFF);
                g2 = ((rgb2>>8) & 0xFF);
                b2 = (rgb2 & 0xFF);

                rgbColor2.setRed(r2);
                rgbColor2.setGreen(g2);
                rgbColor2.setBlue(b2);

                //atribui a hsbColor o valor de rgbColor após a chamada o método de conversão de rgb para hsb
                hsbColor = rgbColor.toHsb();
                hsbColor2 = rgbColor2.toHsb();
                hsbColor.setSaturation(hsbColor2.getSaturation());
                //hsbColor.setBrightnes(hsbColor2.getBrightnes());
                //hsbColor.setHue(hsbColor2.getHue());
                //System.out.println("pixel[" + pixel + "," + linha + "] h= " + (int)hsbColor.getHue() + " ,s= " + hsbColor.getSaturation() + " ,b= " + hsbColor.getBrightnes());
                //atribui a rgbColor o valor de hsbColor após a chamada do método de conversão de hsb para rgb
                rgbColor = hsbColor.toRgb();
                //atribuo a rgb os valores de r, g e b contindos em rgbColor após as conversões
                rgb = ((int)rgbColor.getRed() << 16) | ((int)rgbColor.getGreen() << 8) | (int)rgbColor.getBlue();

                //insere o pixel convertido (rgb-hsb-rgb) na imagem de saída
                imagemRetorno.setRGB(pixel, linha, rgb);
            }
        }
        //ao fim do loop uma imagem de saída idêntica a de entrada é retornada
        return imagemRetorno;
    }
    public static BufferedImage media(BufferedImage img, int medida1, int medida2){
        System.out.println("... bluring image ...");
        //inicializa imagemRetorno que armazenara a iamgem de saída
        BufferedImage imagemRetorno = new BufferedImage(img.getWidth()-(medida1/2), img.getHeight()-(medida2/2), BufferedImage.TYPE_INT_RGB);

        //inicializa as variáveis para "pegar" os valores de cada pixel
        int pivot, r, g, b;
        int rAux, gAux, bAux, pivotAux;
        int[][] mascara = new int[medida1][medida2];
        int[][] valoresMascara = new int[medida1][medida2];
        int mascaraVermelha=0, mascaraVerde=0, mascaraAzul=0;
        Rgb rgbColor = new Rgb(0,0,0);
        int contadorLinha = 0, contadorColuna=0;
        int y =0, x=0;

        //inicializa instancias das classes rgb e hsb para que o método de conversão de cores seja chamado
        //inicializa a varredura pixel a pixel
        for (int linha=medida1/2; linha<img.getHeight()-(medida1/2); linha++){
            for (int pixel=medida2/2; pixel<img.getWidth()-(medida2/2); pixel++){
                for (int i = linha-(medida1/2); i < medida1+contadorLinha; i++){
                    for (int j = pixel-(medida2/2); j < medida2+contadorColuna; j++){
                        mascara[x][y]=1;
                        pivotAux = (img.getRGB(j,i));
                        rAux = ((pivotAux>>16) & 0xFF)*(mascara[x][y]);
                        gAux = ((pivotAux>>8) & 0xFF)*(mascara[x][y]);
                        bAux = (pivotAux & 0xFF)*(mascara[x][y]);
                        //pivotAux=  (rAux << 16) | (gAux << 8) | bAux;
                        valoresMascara[x][y]=pivotAux;
                        mascaraVermelha+=rAux;
                        mascaraVerde+=gAux;
                        mascaraAzul+=bAux;
                        x++;
                    }
                    y++;
                    x=0;
                }

                //recebe as 3 bandas do pixel na variavel rgb

                pivot = (img.getRGB(pixel, linha));
                //armazena em cr, g e b os valoesr correspondentes a cada banda de cor
                r = ((pivot>>16) & 0xFF);
                g = ((pivot>>8) & 0xFF);
                b = (pivot & 0xFF);

                //seta em rgbColor os valores de R, g e b obtidos do pixel
                rgbColor.setRed(mascaraVermelha);
                rgbColor.setGreen(mascaraVerde);
                rgbColor.setBlue(mascaraAzul);

                //atribuo a rgb os valores de r, g e b contindos em rgbColor após as conversões
                pivot =  ((int)rgbColor.getRed() << 16) | ((int)rgbColor.getGreen() << 8) | (int)rgbColor.getBlue();
                mascaraVermelha=0;
                mascaraVerde=0;
                mascaraAzul=0;

                //insere o pixel convertido (rgb-hsb-rgb) na imagem de saída
                imagemRetorno.setRGB(pixel, linha, pivot);

                contadorColuna++;
                x=0;
                y=0;
            }
            contadorLinha++;
        }
        //ao fim do loop uma imagem de saída idêntica a de entrada é retornada
        return imagemRetorno;
    }
    public static BufferedImage media2(BufferedImage img, int medida1, int medida2){
        System.out.println("... bluring image ...");
        //inicializa imagemRetorno que armazenara a iamgem de saída
        BufferedImage imagemRetorno = new BufferedImage(img.getWidth()-(medida1/2), img.getHeight()-(medida2/2), BufferedImage.TYPE_INT_RGB);

        //inicializa as variáveis para "pegar" os valores de cada pixel
        int pivot;
        int rAux, gAux, bAux, pivotAux;
        int[][] mascara = {{1,1,1},{1,1,1},{1,1,1}};
        int mascaraVermelha=0, mascaraVerde=0, mascaraAzul=0;
        Rgb rgbColor = new Rgb(0,0,0);
        int contadorLinha = 0, contadorColuna=0;
        int y =0, x=0;

        //inicializa instancias das classes rgb e hsb para que o método de conversão de cores seja chamado
        //inicializa a varredura pixel a pixel
        for (int linha=medida1/2; linha<img.getHeight()-(medida1/2); linha++){
            for (int pixel=medida2/2; pixel<img.getWidth()-(medida2/2); pixel++){
                for(int linhasMascara = linha - (medida1/2); linhasMascara < linha + (medida1/2); linhasMascara++ ){
                    for(int pixelMascara = pixel - (medida2/2); pixelMascara < pixel + (medida2/2); pixelMascara++){
                        pivotAux = img.getRGB(pixelMascara, linhasMascara);
                        rAux = ((pivotAux >> 16) & 0xFF);
                        gAux = ((pivotAux >> 8) & 0xFF);
                        bAux = ((pivotAux) & 0xFF);

                        mascaraVermelha += rAux;
                        mascaraVerde += gAux;
                        mascaraAzul += bAux;
                    }
                }

                //recebe as 3 bandas do pixel na variavel rgb

                pivot = (img.getRGB(pixel, linha));

                //seta em rgbColor os valores de R, g e b obtidos do pixel
                rgbColor.setRed(mascaraVermelha/100);
                rgbColor.setGreen(mascaraVerde/100);
                rgbColor.setBlue(mascaraAzul/100);

                //atribuo a rgb os valores de r, g e b contindos em rgbColor após as conversões
                pivot =  ((int)rgbColor.getRed() << 16) | ((int)rgbColor.getGreen() << 8) | (int)rgbColor.getBlue();
                mascaraVermelha=0;
                mascaraVerde=0;
                mascaraAzul=0;

                //insere o pixel convertido (rgb-hsb-rgb) na imagem de saída
                imagemRetorno.setRGB(pixel, linha, pivot);

                contadorColuna++;
                x=0;
                y=0;
            }
            contadorLinha++;
        }
        //ao fim do loop uma imagem de saída idêntica a de entrada é retornada
        return imagemRetorno;
    }
}