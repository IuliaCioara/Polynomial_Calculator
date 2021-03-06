import java.util.ArrayList;
import java.util.List;
public class Polynomial {
    //The list with the monoms forming the polynomial
    private List<Monomial> argumente = new ArrayList<Monomial>();
    private int nrMonoame = 0;//The number of the monoms

    /**
     * Method used to add monoms to polynomial.
     */
    public void addMonom(Monomial monom) {
        argumente.add(monom);
        nrMonoame++;
    }

    /**
     * Method used to represent the polynomials as a String
     */
    @Override
    public String toString(){

        String reprezentarePolinom = "";
        String auxP = "";
        String auxC = "";
        boolean areMonomPozitiv = false;//Used to check and display if the polynomial has the last coefficient 1

        for (Monomial m : argumente){

            int coef = (int) m.getCoef();
            if(m.getCoef() == 0) continue;
            if((coef != 1) && (coef != -1)) auxC = coef + "";
            else if(coef == 1) {auxC = "";
                areMonomPozitiv = true;}
            else {auxC = "-";

            }
            if((m.getPower() != 0) && (m.getPower() != 1)) auxP = "x^" + (int)m.getPower();
            else if(m.getPower() == 1) auxP = "x";
            else auxP = "";
            if(coef == 1 && m.getPower() == 0) auxC = "1";
            if(coef == -1 && m.getPower() == 0) auxC = "-1";

            reprezentarePolinom = reprezentarePolinom+ auxC + auxP + "+";
            reprezentarePolinom = reprezentarePolinom.replace("+-", "-");
        }
        if(reprezentarePolinom.compareTo("") == 0)
        {if(areMonomPozitiv) reprezentarePolinom = "1";
        else reprezentarePolinom = "0";
        }
        else if(reprezentarePolinom.length() > 0)
            reprezentarePolinom = reprezentarePolinom.substring(0,reprezentarePolinom.length()-1);
        reprezentarePolinom = reprezentarePolinom.replace("+-", "-");
        if(reprezentarePolinom.compareTo("-") == 0) reprezentarePolinom = "-1";
        if(argumente.isEmpty()) {reprezentarePolinom = "0";
        }
        return reprezentarePolinom;
    }

    /**
     * Method used to represent the polynomials as a string.
     */
    public String toStringDouble() {
        String reprezentarePolinom = "";
        String auxP = "";
        String auxC = "";
        for (Monomial m : argumente){

            if(m.getCoef() == 0) {System.out.println("a ajuns in continue");continue;}
            if((m.getCoef() != 1) && (m.getCoef() != -1)) auxC = String.format("%.2g%n", m.getCoef()) + "";
            else if(m.getCoef() == 1) auxC = "";
            else auxC = "-";
            if((m.getPower() != 0) && (m.getPower() != 1)) auxP = "x^" + (int)m.getPower();
            else if(m.getPower() == 1) auxP = "x";
            else auxP = "";
            reprezentarePolinom = reprezentarePolinom + auxC + auxP + "+";
        }

        if(argumente.isEmpty())
            reprezentarePolinom = "0";
        if(reprezentarePolinom.compareTo("") == 0) reprezentarePolinom = "0";
        else if(reprezentarePolinom.length() > 0)
            reprezentarePolinom = reprezentarePolinom.substring(0,reprezentarePolinom.length()-1);

        reprezentarePolinom = reprezentarePolinom.replace("+-", "-");

        return reprezentarePolinom;	}
    /**
     * Returns the number of monoms
     */
    public int nrMonoms() {
        return nrMonoame;
    }

    /**
     * Returns the monom found at index i.
     */
    public Monomial getMonom(int i){

        return argumente.get(i);

    }

    /**
     * Method used to reset the polynomial
     */
    public void reset() {
        argumente.clear();
        nrMonoame = 0;
    }

    /**
     * Method used to eliminate one monom found at index i
     */
    public void elimMonom(int i) {
        argumente.remove(i);

    }

    /**
     * Method which shows if one polynomial is empty or not.
     */
    public boolean isEmpty() {
        if(argumente.isEmpty()) return true;
        else return false;

    }
}