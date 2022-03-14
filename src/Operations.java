public class Operations {

    int i = 0,j = 0;

    /**
     * Method used to evaluate the sum of two polynomials
     */
    public Polynomial add(Polynomial P,Polynomial Q) {

        Monomial m;
        //The returned polynomial
        Polynomial PplusQ = new Polynomial();

        int nrP = P.nrMonoms();
        int nrQ = Q.nrMonoms();

        try{
            while(i < nrP && j < nrQ){
                //Coefficients and powers of P and Q
                double coefP = P.getMonom(i).getCoef();
                double coefQ = Q.getMonom(j).getCoef();
                double powerP = P.getMonom(i).getPower();
                double powerQ = Q.getMonom(j).getPower();

                if(powerP == powerQ){
                    m = new Monomial(coefP + coefQ,powerP);
                    i++;j++;}

                else if(powerP > powerQ){
                    m = new Monomial(coefP,powerP);
                    i++;
                }
                else {
                    m = new Monomial(coefQ,powerQ);
                    j++;
                }
                PplusQ.addMonom(m);
            }
            while(i < nrP) {
                double coefP = P.getMonom(i).getCoef();
                double powerP = P.getMonom(i).getPower();
                m = new Monomial(coefP,powerP);
                i++;
                PplusQ.addMonom(m);
            }
            while(j < nrQ) {
                double coefQ = Q.getMonom(j).getCoef();
                double powerQ = Q.getMonom(j).getPower();
                m = new Monomial(coefQ,powerQ);
                j++;
                PplusQ.addMonom(m);
            }
        }
        catch(Exception E) {};
        return PplusQ;
    }

    /*public Polynomial sub(Polynomial other)
    {
        for(Monomial MonomialY: other.getMonoame()) {
            Monomial MonomialX = find(MonomialY.getDeg());
            if(MonomialX != null) {
                MonomialX.sub(MonomialY);
            }
            else {
                insertMonomial(inv(MonomialY));
            }
        }
        return this;
    }*/

    /**
     * The method used to evaluate the difference between P and Q
     */
    public Polynomial sub(Polynomial P,Polynomial Q) {

        i = 0;
        j = 0;
        Monomial m;
        Polynomial PminusQ = new Polynomial();
        int nrP = P.nrMonoms();
        int nrQ = Q.nrMonoms();
        try{
            while(i < nrP && j < nrQ){

                double coefP = P.getMonom(i).getCoef();
                double coefQ = Q.getMonom(j).getCoef();
                double powerP = P.getMonom(i).getPower();
                double powerQ = Q.getMonom(j).getPower();

                if(powerP == powerQ){
                    m = new Monomial(coefP - coefQ,powerP);
                    i++;j++;
                }
                else if(powerP > powerQ){
                    m = new Monomial(coefP,powerP);
                    i++;
                }
                else {
                    m = new Monomial(-coefQ,powerQ);
                    j++;
                }

                PminusQ.addMonom(m);
            }

            while(i < nrP) {
                double coefP = P.getMonom(i).getCoef();
                double powerP = P.getMonom(i).getPower();
                m = new Monomial(coefP,powerP);
                i++;
                PminusQ.addMonom(m);
            }
            while(j < nrQ) {
                double coefQ = Q.getMonom(j).getCoef();
                double powerQ = Q.getMonom(j).getPower();
                m = new Monomial(-coefQ,powerQ);
                j++;
                PminusQ.addMonom(m);
            }
        }
        catch(Exception E) {};
        return PminusQ;
    }


    public Polynomial mul(Polynomial P,Polynomial Q) {
        int i = 0, j = 0;
        Monomial m;
        Polynomial PmulQ = new Polynomial();

        try{
            int nrP = P.nrMonoms();
            int nrQ = Q.nrMonoms();

            while(i < nrP){
                double coefP = P.getMonom(i).getCoef();
                double powerP = P.getMonom(i).getPower();
                i++;
                j = 0;

                while(j < nrQ){
                    double coefQ = Q.getMonom(j).getCoef();
                    double powerQ = Q.getMonom(j).getPower();
                    m = new Monomial(coefP * coefQ, powerP + powerQ);
                    PmulQ.addMonom(m);
                    j++;
                }
            }

        }catch(Exception E){}
        return PmulQ;
    }

    /*public int maxDeg()
    {
        int max=0;
        for(Monomial m: this.getMonomial())
        {
            if(max<m.getPower())
                max=m.getPower();
        }
        return max;
    }*/
}