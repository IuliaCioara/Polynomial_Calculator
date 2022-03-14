public class Monomial {
    private double coef;
    private double power;

    Monomial(double coef ,double power) {
        setPower(power);
        setCoef(coef);
    }

    public void setPower(double power2) {

        this.power = power2;

    }


    public double getPower() {
        return this.power;
    }

    public Monomial div(Monomial aux) {
        Monomial imp = new Monomial(this.coef / aux.getCoef(), power - aux.getPower());
        return imp;
    }

    public void setCoef(double coef){
        this.coef = coef;
    }

    public double getCoef(){
        return this.coef;
    }

}