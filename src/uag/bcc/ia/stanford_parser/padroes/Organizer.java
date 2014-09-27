package uag.bcc.ia.stanford_parser.padroes;
import java.util.ArrayList;
import java.util.StringTokenizer;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;


public class Organizer {
	
	private static String localArquivoPadroes = "res/padroes.txt";
	private static String localArquivoFrase;
	private static String localArquivoModel = "res/englishPCFG.ser.gz";
	
	private static LexicalizedParser lp = LexicalizedParser.loadModel(localArquivoModel);
	private static	StanfordParser frase = new StanfordParser();
	private static StanfordParser padrao = new StanfordParser();
	private static  ArrayList<String[]> arrayListPadroesFinal;
	private static int qtdPadroes;
	private static String novosPadroes;
	private Standards compara = new Standards();
	
	//Repositorio de Padroes
	static StandardRepository bd = new StandardRepository();
	
	
	
//Framework:	
	
	public Organizer(String urlFrase) {
		localArquivoFrase = urlFrase;
		organizerSentence();
	}
	
	
	//Retorna matriz de Padroes. 
	public String[][] getStandards(){
		return bd.getStandards();					
	}
		
	//Imprime matriz de Padroes (console)
	public void printStandards(){
		bd.printStandards();	
	}
	
	// Retorna o Tagging da frase de entrada.
	public String getTagging(){
		return frase.demoAPI(lp, localArquivoFrase);
	}
	
	// Retorna frase final.
	public String getFinalFrase(){
		return compara.getFraseFinal();
	}
	
	
	
	
	
	
//Framework - fim.
		
	
	private void organizerSentence(){
		StanfordParser frase = new StanfordParser();
		
		
				
		// Chama funcao para importar os padores - Salva padroes na String novosPadroes
		importarPadroes();
		
		// Variavel arrayListPadroes serve de auxilio apenas
	    // Organizar Padroes no Array  e verificar quantidade de padroes no arquivo.
		String arrayStandards[] = new String[qtdPadroes];
		arrayStandards = organizarPadroes(novosPadroes);
		
		printStandards();		
		
				
		//Adicionando Padroes ao repositorio
		for(int i=0; i<arrayStandards.length; i++){
			bd.addStandardRep(arrayStandards[i]);
		}
		
		
		//Imprimindo o tagging da frase desorganizada
		System.out.println();
		System.out.println("-------------------TAGGING da frase baguncada-----------------");
		System.out.println("      ["+frase.demoAPI(lp, localArquivoFrase)+"]");
		System.out.println("--------------------------------------------------------------");
		
		//Compara frase do txt, com as frases do repositorio de padroes
		compara.comparePhraseWRepository(frase.demoAPI(lp, localArquivoFrase), bd);
	
	} // organizerSentence()
	
		
	
	//Importa arquivo de PADROES e retorna o TAGGING em String
		private void importarPadroes(){
		  String novoPadrao; 
		  novoPadrao = padrao.demoAPI(lp, localArquivoPadroes);
		  //System.out.println("Novo Padrao: " + novoPadrao);
		  novosPadroes = novoPadrao;  
		}
		
		    
		// Separa as TAGs de cada padrao e coloca em Arrays
		// Retorna ArrayList de Arrays contendo cada Array com as TAGs dos padroes.
		private String[] organizarPadroes(String padroes) {

						//Verifica a quantidade de Padroes que tem no arquivo.
						StringTokenizer token = new StringTokenizer(padroes, ".");
						qtdPadroes = token.countTokens() / 2;
						System.out.println("Quantidade de Padroes: " + qtdPadroes);
						
						// Separa frases dos padroes
						String[] arrayPadroes = new String[qtdPadroes];
						int j = 0;
						int inicio = 0;
						boolean c = true;
							for (int i = 0; i < padroes.length(); i++){
								if(padroes.charAt(i) == '.'){
									i++;
									if(padroes.charAt(i) == '/'){
										i++;
										if(padroes.charAt(i) == '.'){
											i++;
											arrayPadroes[j] = padroes.substring(inicio, i);
											i++;
											inicio = i;
											j++;
										}
									}
								}
							}
						
					
						//System.out.println("Array de Padroes: " + Arrays.deepToString(arrayPadroes));

			return arrayPadroes;
		} // organizarPadores();
	
}
