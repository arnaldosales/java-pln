package aplicacao;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import uag.bcc.ia.owl.OWLHelper;
import uag.bcc.ia.stanford_parser.SPHelper;

/**
 *
 * @author ramonsantos
 */
public class Principal {

	private static Logger log = Logger.getLogger(Principal.class);

	public static void main(String[] args) {

		PropertyConfigurator.configure("conf/log4j.properties");
		log.info("Teste Log");
		
		SPHelper.getInstanceParser().escreverHierarquia("Shark are a Fish");
		OWLHelper.getOWLHelper().gerarArquivo("exit");
		
	}
	
}
