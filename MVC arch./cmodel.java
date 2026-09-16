package extra;
public class cmodel {
    

    public double add(double a, double b, double c){
        return a+b+c;
    }
    
    public double sub(double a, double b, double c){
        return a+b-c;
    }
    public double mul(double a, double b, double c){
        return a*b*c;
    }

    public double div(double a, double b, double c){
        if (c==0){
            return 0;
        }
        return (a+b)/c;
    }
    



}
