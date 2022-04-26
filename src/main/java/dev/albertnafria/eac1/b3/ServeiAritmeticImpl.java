package dev.albertnafria.eac1.b3;

/**
 *
 * @author 
 */
public class ServeiAritmeticImpl implements ServeiAritmetic{

    @Override
    public double suma(double valor1, double valor2) {
       return valor1+valor2;
    }

    @Override
    public double resta(double valor1, double valor2) {
        return valor1-valor2;
    }

    @Override
    public double div(double valor1, double valor2) {
        if(valor2!=0) return valor1/valor2;
        return Double.MAX_VALUE;
    }

    @Override
    public double mult(double valor1, double valor2) {
        return valor1*valor2;
    }
    
}
