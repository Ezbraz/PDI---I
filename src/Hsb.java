public class Hsb {
    private double hue;
    private double saturation;
    private double brightnes;

    public Hsb(double hue, double saturation, double brightnes) {
        this.hue = (hue>360.0)? 360.0 : ((hue<0.0)?0.0:hue);
        this.saturation = (saturation>1.0)? 1.0 : ((saturation<0.0)?0.0:saturation);
        this.brightnes = (brightnes>1.0)? 1.0 : ((brightnes<0.0)?0.0:brightnes);
    }

    public double getHue() {
        return hue;
    }

    public void setHue(double hue) {
        this.hue = hue;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    public double getBrightnes() {
        return brightnes;
    }

    public void setBrightnes(double brightnes) {
        this.brightnes = brightnes;
    }

    public Rgb toRgb(){
        double r = 0.0, g = 0.0, b = 0.0;
        if(this.getSaturation() == 0){
            //se a saturação for igual a zero significa que r,g e b possuem valores iguais e a cor é um tom de cinza
            //então atribuimos o valor do brilho para todas as bandas
            r = g = b = this.getBrightnes();
        } else {
            // se a saturação não for 0 então a cor possui uma matiz que pertence a um de 6 setores (cada 60º no cone de cores)
            double sectorPos = this.getHue()/60.0;
            int sectorNumber = (int)(Math.floor(sectorPos));
            double fractionalSector = sectorPos - sectorNumber;

            //calcula-se os valores dos 3 eixos do cone de cores
            double p = this.getBrightnes() * (1.0 - this.getSaturation());
            double q = this.getBrightnes() * (1.0 - (this.getSaturation() * fractionalSector));
            double t = this.getBrightnes() * (1.0 - (this.getSaturation() * (1 - fractionalSector)));

            //
            switch (sectorNumber){
                case 0:
                    r = this.getBrightnes();
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = this.getBrightnes();
                    b = p;
                    break;
                case 2:
                    r = t;
                    g = this.getBrightnes();
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = this.getBrightnes();
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = this.getBrightnes();
                    break;
                case 5:
                    r = this.getBrightnes();
                    g = p;
                    b = q;
                    break;
            }
        }
        Rgb rgb = new Rgb(Math.round((r*255.0)),Math.round((g*255.0)),Math.round((b*255.0)));
        return rgb;
    }

    public Rgb adicioanrMatiz(double hueFactor){
        double r = 0.0, g = 0.0, b = 0.0;
        if(this.getSaturation() == 0){
            //se a saturação for igual a zero significa que r,g e b possuem valores iguais e a cor é um tom de cinza
            //então atribuimos o valor do brilho para todas as bandas
            r = g = b = this.getBrightnes();
        } else {
            // se a saturação não for 0 então a cor possui uma matiz que pertence a um de 6 setores (cada 60º no cone de cores)
            double sectorPos = (this.getHue() + hueFactor)/60.0;
            if((this.getHue() + hueFactor)>360){
                double excedente = (this.getHue() + hueFactor)%360;
                sectorPos = (excedente/60.0);
            } else if((this.getHue() + hueFactor)<0){
                double excedente = (this.getHue() + hueFactor)%360;
                sectorPos = ((excedente * -1)/60.0);
            }

            int sectorNumber = (int)(Math.floor(sectorPos));
            double fractionalSector = sectorPos - sectorNumber;

            //calcula-se os valores dos 3 eixos do cone de cores
            double p = this.getBrightnes() * (1.0 - this.getSaturation());
            double q = this.getBrightnes() * (1.0 - (this.getSaturation() * fractionalSector));
            double t = this.getBrightnes() * (1.0 - (this.getSaturation() * (1 - fractionalSector)));

            //
            switch (sectorNumber){
                case 0:
                    r = this.getBrightnes();
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = this.getBrightnes();
                    b = p;
                    break;
                case 2:
                    r = t;
                    g = this.getBrightnes();
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = this.getBrightnes();
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = this.getBrightnes();
                    break;
                case 5:
                    r = this.getBrightnes();
                    g = p;
                    b = q;
                    break;
            }
        }
        Rgb rgb = new Rgb(Math.round((r*255.0)),Math.round((g*255.0)),Math.round((b*255.0)));
        return rgb;
    }

    public Rgb multiplicarBrilho(double brilho){
        double r = 0.0, g = 0.0, b = 0.0;
        if(this.getSaturation() == 0){
            //se a saturação for igual a zero significa que r,g e b possuem valores iguais e a cor é um tom de cinza
            //então atribuimos o valor do brilho para todas as bandas
            r = g = b = this.getBrightnes()*brilho;
        } else {
            // se a saturação não for 0 então a cor possui uma matiz que pertence a um de 6 setores (cada 60º no cone de cores)
            double sectorPos = this.getHue()/60.0;
            int sectorNumber = (int)(Math.floor(sectorPos));
            double fractionalSector = sectorPos - sectorNumber;

            //calcula-se os valores dos 3 eixos do cone de cores
            double p = (this.getBrightnes()*brilho) * (1.0 - this.getSaturation());
            double q = (this.getBrightnes()*brilho) * (1.0 - (this.getSaturation() * fractionalSector));
            double t = (this.getBrightnes()*brilho) * (1.0 - (this.getSaturation() * (1 - fractionalSector)));

            //
            switch (sectorNumber){
                case 0:
                    r = this.getBrightnes()*brilho;
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = this.getBrightnes()*brilho;
                    b = p;
                    break;
                case 2:
                    r = t;
                    g = this.getBrightnes()*brilho;
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = this.getBrightnes()*brilho;
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = this.getBrightnes()*brilho;
                    break;
                case 5:
                    r = this.getBrightnes()*brilho;
                    g = p;
                    b = q;
                    break;
            }
        }
        Rgb rgb = new Rgb(Math.round((r*255.0)),Math.round((g*255.0)),Math.round((b*255.0)));
        return rgb;
    }

    public Rgb multiplicarSaturacao(double sat){
        double r = 0.0, g = 0.0, b = 0.0;
        if(this.getSaturation() == 0){
            //se a saturação for igual a zero significa que r,g e b possuem valores iguais e a cor é um tom de cinza
            //então atribuimos o valor do brilho para todas as bandas
            r = g = b = this.getBrightnes();
        } else {
            // se a saturação não for 0 então a cor possui uma matiz que pertence a um de 6 setores (cada 60º no cone de cores)
            double sectorPos = this.getHue()/60.0;
            int sectorNumber = (int)(Math.floor(sectorPos));
            double fractionalSector = sectorPos - sectorNumber;

            //calcula-se os valores dos 3 eixos do cone de cores
            double p = this.getBrightnes() * (1.0 - (this.getSaturation() * sat));
            double q = this.getBrightnes() * (1.0 - ((this.getSaturation() * sat) * fractionalSector));
            double t = this.getBrightnes() * (1.0 - ((this.getSaturation() * sat) * (1 - fractionalSector)));

            //
            switch (sectorNumber){
                case 0:
                    r = this.getBrightnes();
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = this.getBrightnes();
                    b = p;
                    break;
                case 2:
                    r = t;
                    g = this.getBrightnes();
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = this.getBrightnes();
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = this.getBrightnes();
                    break;
                case 5:
                    r = this.getBrightnes();
                    g = p;
                    b = q;
                    break;
            }
        }
        Rgb rgb = new Rgb(Math.round((r*255.0)),Math.round((g*255.0)),Math.round((b*255.0)));
        return rgb;
    }
}
