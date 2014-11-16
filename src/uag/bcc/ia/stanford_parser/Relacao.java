/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uag.bcc.ia.stanford_parser;

/**
 *
 * @author ramonsantos
 */
public class Relacao {
 
    private String elementoInicial;
    private String elementoCentral;
    private String elementoFinal;
   

    public Relacao(String elementoInicial, String elementoCentral, String elementoFinal) {
        
        this.elementoInicial = elementoInicial;
        this.elementoCentral = elementoCentral;
        this.elementoFinal = elementoFinal;
        
    }

    public String getElementoInicial() {
        return elementoInicial;
    }

    public void setElementoInicial(String elementoInicial) {
        this.elementoInicial = elementoInicial;
    }

    public String getElementoFinal() {
        return elementoFinal;
    }

    public void setElementoFinal(String elementoFinal) {
        this.elementoFinal = elementoFinal;
    }

    public String getElementoCentral() {
        return elementoCentral;
    }

    public void setElementoCentral(String elementoCentral) {
        this.elementoCentral = elementoCentral;
    }
    
    @Override
    public String toString(){
        return this.elementoInicial + " -> " + this.elementoCentral + " -> " + this.elementoFinal;
    }
}
