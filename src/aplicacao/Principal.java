/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacao;

import edu.stanford.nlp.ling.TaggedWord;
import java.util.List;
import uag.bcc.ia.stanford_parser.SPHelper;
import uag.bcc.ia.texto.TextoUtil;

/**
 *
 * @author ramonsantos
 */
public class Principal {
    
    public static void main(String[] args){
        
        String frase = "Ramon is a dog, he was constipated last week";
        
      //  List<TaggedWord> listTag = SPHelper.getInstanceParser().getListTaggerWord(frase);
        
      //  System.out.println(listTag.toString());
         System.out.println(TextoUtil.getInstanceTextoUtil().retirarPontuacao(frase));
    }
}
