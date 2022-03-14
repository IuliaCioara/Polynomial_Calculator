import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main implements ActionListener {
    private String work = ""; //The String in which we will place the final value
    private static Polynomial polinomP = new Polynomial();
    private static Polynomial polinomQ = new Polynomial();
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;//Orientation
    private boolean readP = true, readQ = true;//Used to read the polynomials
    JButton add,sub,mul,div;
    JButton instr;
    JButton reset;
    JTextField editarePolinomP,editarePolinomQ,resultTextField ;
    JLabel poliP,poliQ,result;
    JRadioButton unary;//,binary;
    GridBagConstraints c = new GridBagConstraints();
    /**
     * The method which adds the component to the container.
     */
    public  void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout()); //Layout form


        unary = new JRadioButton("Operatii unare");
        c.gridx++;
        unary.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                add.setEnabled(false);
                sub.setEnabled(false);
                mul.setEnabled(false);
                div.setEnabled(false);
                editarePolinomQ.setEnabled(false);
                editarePolinomQ.setText(null);
                resultTextField.setText(null);
            }

        });
        pane.add(unary,c);
        ButtonGroup grup = new ButtonGroup();
        grup.add(unary);
        poliP = new JLabel("Polinomul P :",JLabel.LEFT);
        c.gridx = 0;
        c.gridy = 1;
        pane.add(poliP, c);

        editarePolinomP = new JTextField(10);
        editarePolinomP.setActionCommand("Ceva text");
        c.gridx ++;
        pane.add(editarePolinomP, c);
        poliQ = new JLabel("Polinomul Q :",JLabel.LEFT);
        c.gridx = 0;
        c.gridy = 2;
        pane.add(poliQ, c);

        editarePolinomQ = new JTextField(10);
        c.gridx++;
        pane.add(editarePolinomQ, c);

        pane.add(Box.createRigidArea(new Dimension(50,0))); //space

        add = new JButton("+");
        c.gridx = 3;
        c.gridy = 1;
        pane.add(add, c);
        add.addActionListener(this);

        sub = new JButton("-");
        c.gridx++;
        sub.addActionListener(this);
        pane.add(sub, c);

        mul = new JButton("*");
        c.gridx++;
        mul.addActionListener(this);
        pane.add(mul, c);

        div = new JButton("/");
        div.addActionListener(this);
        c.gridx++;
        pane.add(div, c);

        result = new JLabel("Rezultat :",JLabel.LEFT);
        c.gridx = 0;
        c.gridy = 3;
        pane.add(result,c);
        result.setFont(result.getFont().deriveFont(18.0f));

        resultTextField = new JTextField(20);
        c.gridx++;
        c.ipadx = 15;
        c.ipady = 10;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        resultTextField.setEditable(false);
        resultTextField.setBackground(new Color(180,190,210));
        pane.add(resultTextField,c);
        c.gridx = 0;
        c.gridy = 0;

        pane.add(Box.createRigidArea(new Dimension(50,0)));

        reset = new JButton("C");
        reset.addActionListener(this);
        reset.setFont(reset.getFont().deriveFont(9.0f));
        c.gridy = 3;
        c.gridx = 6;
        c.ipadx = 1;
        c.ipady = 1;
        pane.add(reset,c);

    }


    public void actionPerformed(ActionEvent ev) {
        String textP,textQ;//used to read the polynomials
        int cMonom = 0,iMonom = 0;
        int valx;//used to read the value of the desired point
        boolean hasCoef = false; //used to determine if P or Q starts with a coefficient
        boolean negative = false;//
        String inter = ""; // reading process for P
        String interQ = "";// reading process for Q

        Operations o = new Operations();

        final String mesajFormat = "Format invalid";
        final String mesajGrad = "Gradul polinomului P este mai mic decat gradul polinomului Q";
        if(readP) {
            textP = editarePolinomP.getText();//get the text from the text field
            if(textP.startsWith("-")) {hasCoef = true;
                negative = true;}
            if(textP.startsWith("x")) {hasCoef = true;
                negative = false;}
            if(hasCoef == true) {

                if(negative == true) {cMonom = -1;textP = textP.substring(1);}
                if(negative == false) cMonom = 1;

            }
            StringTokenizer st1 = new StringTokenizer(textP,"x^");

            while (st1.hasMoreElements()){
                inter = (String) st1.nextElement();

                //Forward dividing into integers
                StringTokenizer st2 = new StringTokenizer(inter,"+");
                StringTokenizer st3 = new StringTokenizer(inter,"-");

                if(inter.indexOf("-") != -1) //if it has a - operator then we use
                    while(st3.hasMoreElements()){//this part of code to break the string
                        try{
                            if(cMonom == 0) {
                                String copy = (String) st3.nextElement();
                                cMonom = Integer.parseInt(copy);
                                cMonom = - cMonom;
                                continue;
                            }

                            else
                            if (iMonom == 0) {
                                String copy = (String) st3.nextElement();
                                iMonom = Integer.parseInt(copy);
                                continue;
                            }
                        }
                        //NumberFormatException could be thrown
                        catch(Exception e) {popUp(mesajFormat);}
                        //We add the monom to the polynom
                        Monomial monom = new Monomial(cMonom,iMonom);
                        polinomP.addMonom(monom);
                        iMonom = 0;
                        cMonom = 0;
                    }
                else  //it doesn't containt '-'
                    while(st2.hasMoreElements()){

                        if(cMonom == 0) {	String value = (String) st2.nextElement();
                            try{cMonom = Integer.parseInt(value);
                            }
                            catch(Exception e) {popUp(mesajFormat);}
                            continue;
                        }

                        else
                        if(iMonom == 0) {	String value = (String) st2.nextElement();
                            try{iMonom = Integer.parseInt(value);
                            }
                            catch(Exception e) {popUp(mesajFormat);}

                            continue;
                        }
                        //Instantiate and add the monom to P
                        Monomial monom = new Monomial(cMonom,iMonom);
                        polinomP.addMonom(monom);
                        //reseting the values
                        iMonom = 0;
                        cMonom = 0;
                    }

            }
            if(cMonom != 0) {

                Monomial monom = new Monomial(cMonom,iMonom);
                polinomP.addMonom(monom);

            }
            readP = false;//We read P only once until reset button is pressed
        }


        //Getting the polynom Q
        if(readQ) {
            textQ = editarePolinomQ.getText();
            //reset the values
            iMonom = 0;
            cMonom = 0;
            hasCoef = false;
            negative = false;

            if(textQ.startsWith("-")) {hasCoef = true;
                negative = true;}
            if(textQ.startsWith("x")) {hasCoef = true;
                negative = false;}

            if(hasCoef == true) {

                if(negative == true) {cMonom = -1;
                    textQ = textQ.substring(1);}
                if(negative == false) cMonom = 1;

            }

            StringTokenizer stQ1 = new StringTokenizer(textQ,"x^");

            while (stQ1.hasMoreElements()){

                interQ = (String) stQ1.nextElement();

                StringTokenizer stQ2 = new StringTokenizer(interQ,"+");
                StringTokenizer stQ3 = new StringTokenizer(interQ,"-");

                if(interQ.indexOf("-") != -1) //if it has a - operator then we use
                    while(stQ3.hasMoreElements()){//this part of code to break the string

                        try{
                            if(cMonom == 0) {String copy = (String) stQ3.nextElement();
                                cMonom = Integer.parseInt(copy);
                                cMonom = -cMonom;
                                continue;
                            }

                            else
                            if (iMonom == 0) {String copy = (String) stQ3.nextElement();
                                iMonom = Integer.parseInt(copy);

                                continue;
                            }
                        }
                        catch(Exception e) {popUp(mesajFormat);}

                        //Instantiate and add the monom to the polynomial
                        Monomial monom = new Monomial(cMonom,iMonom);
                        polinomQ.addMonom(monom);
                        //reset the values
                        iMonom = 0;
                        cMonom = 0;
                    }
                else
                    while(stQ2.hasMoreElements()){//It has '+' as a token

                        if(cMonom == 0) {	String value = (String) stQ2.nextElement();
                            try{cMonom = Integer.parseInt(value);}
                            catch(Exception e) {popUp(mesajFormat);}
                            continue;
                        }

                        else
                        if(iMonom == 0) {	String value = (String) stQ2.nextElement();
                            try{iMonom = Integer.parseInt(value); }
                            catch(Exception e) {popUp(mesajFormat);}
                            continue;
                        }
                        //Instantiate and add the monom to the polynomial
                        Monomial monom = new Monomial(cMonom,iMonom);
                        System.out.println("Creat monomul :" + cMonom + " , " + iMonom);
                        polinomQ.addMonom(monom);
                        iMonom = 0;
                        cMonom = 0;
                    }
                //A test display to see if the reading is done properly
                System.out.println("Polinomul P dupa citire " + polinomP.toString());
                System.out.println("Polinomul Q dupa citire " + polinomQ.toString());

            }

            if(cMonom != 0) {
                Monomial monom = new Monomial(cMonom,iMonom);
                polinomQ.addMonom(monom);
            }

            readQ = false;//We read only once until the pressing of reset button
        }

        if(ev.getSource() == reset){
            //Reset all the fields
            readP = true;
            readQ = true;
            editarePolinomQ.setText("");
            editarePolinomP.setText("");
            work ="";
            polinomP.reset();
            polinomQ.reset();
        }

        if (ev.getSource() == sub) {
            //Substract
            work = o.sub(polinomP, polinomQ).toString();
        }

        if (ev.getSource() == add) {
            //Add
            work = o.add(polinomP, polinomQ).toString();
        }

        if(ev.getSource() == mul){
            //Multiply
            work = o.mul(polinomP, polinomQ).toString();
        }
        resultTextField.setText(work);
    }

    private void popUp(String mesaj) {

        JOptionPane.showMessageDialog(null,
                mesaj,
                "Eroare",
                JOptionPane.ERROR_MESSAGE);
    }

    private void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Calcul de polinom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Main obj = new Main();//type Main object
        obj.createAndShowGUI();//call to create method
    }
}