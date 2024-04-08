import java.util.Arrays;

public class Rgb {
    private double red;
    private double green;
    private double blue;

    public Rgb(double red, double green, double blue) {
        this.red = (red>255.0)? 255.0 : ((red<0.0)?0.0:red);
        this.green = (green>255.0)? 255.0 : ((green<0.0)?0.0:green);
        this.blue = (blue>255.0)? 255.0 : ((blue<0.0)?0.0:blue);
    }

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }

    public String getMax(){
        double r=this.getRed(), g=this.getGreen(), b=this.getBlue();
        double[] cores ={r, g, b};
        String max="";
        Arrays.sort(cores);
        if(cores[2] == r){
            max = "red";
        } else if (cores[2] == g){
            max = "green";
        } else if (cores[2] == b){
            max = "blue";
        }
        return max;
    }
    public String getMin(){
        double r=this.getRed(), g=this.getGreen(), b=this.getBlue();
        double[] cores ={r, g, b};
        String min="";
        Arrays.sort(cores);
        if(cores[0] == r){
            min = "red";
        } else if (cores[0] == g){
            min = "green";
        } else if (cores[0] == b){
            min = "blue";
        }
        return min;
    }
    public Hsb toHsb(){
        String corMax = this.getMax();
        String corMin = this.getMin();
        double r=(this.getRed()/255.0), g=(this.getGreen()/255.0), b=(this.getBlue()/255.0);
        double hue=0.0;
        double s=0.0;
        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        //double max=(corMax == "red")? r : ((corMax=="green")? g : b);
        //double min=(corMin == "red")? r : ((corMin=="green")? g : b);
        if( r==1 && g==1 && b==1 ){
            hue=0;
        } else if(max == r && g>=b){
            hue = 60 * ((g-b)/(max-min));
        } else if ( max == r && g<b){
            hue = 60 * ((g-b)/(max-min)) + 360;
        } else if (max == g){
            hue = 60 * ((b-r)/(max-min)) + 120;
        } else if (max == b){
            hue = 60 * ((r-g)/(max-min)) + 240;
        }
        s = (max==0)?0.0 : (1.0 - (min/max));
        Hsb hsb = new Hsb(hue, s, max);
        return hsb;
    }
}
