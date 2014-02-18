package uag.bcc.ia.stanford_parser.padroes;

/**
 * 
 * @author Paulinely Morgan
 * @author Raphael Cordeiro
 */
public class DistributingWords {

	private String mainArray[][];
	private int sizeWord;

	// Para decidir o tamanho do array de palavras
	public int countWords(String str) {
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			// desconsidera as virgulas e pontos do tagging
			if (str.charAt(i) == ' ' && str.charAt(i + 1) != '/'
					&& str.charAt(i + 1) != '.') {
				count++;
			}
		}

		// retorna exatamente a qnt de palavras classificadas no tagging
		return count + 1;
	}// coutSpace()

	// Procurando espacos (retorna a posicao do espaco)
	public int searchSpaceLine(int pos, String str) {
		int space = -1;

		//
		for (int i = pos; i < str.length(); i++) {
			if (str.charAt(i) == ' ' && str.charAt(i + 1) != '/'
					&& str.charAt(i + 1) != '.') {
				space = i;
				break;
			}
		}

		return space + 1;
	}// searchSpaceLine()

	// Procurando espacos (retorna a posicao do espaco)
	public int searchSpace(int pos, String str) {
		int space = -1;

		for (int i = pos; i < str.length(); i++) {
			if (str.charAt(i) == ' ' && str.charAt(i - 1) != '/'
					&& str.charAt(i - 1) != '.') {
				space = i;
				break;
			}
		}

		return space + 1;
	}// searchSpace()

	// Procurando barras (retorna a UMA posicao anterior a barra)
	public int searchLines(int pos, String str) {
		int line = -1;

		// procura pela primeira ocorrencia de uma "/"
		for (int i = pos; i < str.length(); i++) {
			if (str.charAt(i) == '/' && str.charAt(i + 1) != ' '
					&& str.charAt(i + 1) != '.') {
				line = i;
				break;
			}
		}// for

		return line;
	}// searchLine()

	// Distribuindo as palavras num array...
	public void distributingWordsArray(String str) {
		int words = countWords(str);

		// array tem a mesma qnt de palavras da frase
		String array[][] = new String[2][words];

		// distribuindo...
		for (int i = 0; i < 2; i++) {
			int pos = 0;

			for (int j = 0; j < words; j++) {
				// palavras
				if (i == 0) {
					array[i][j] = str.substring(pos, searchLines(pos, str));
					pos = searchSpaceLine(pos, str);

					// tags
				} else {
					array[i][j] = str.substring(searchLines(pos, str) + 1,
							(searchSpace(pos, str) - 1));
					pos = searchSpace(pos, str);
				}
			}// for j (coluna)
		}// for i (linha)

		// passando resultado pro array principal
		this.mainArray = array;

		// passando a quantidade de palavras
		this.sizeWord = words;
	}// distributingWordsArray()

	// Gets
	public String[][] getMainArray() {
		return mainArray;
	}

	public int getSizeWord() {
		return sizeWord;
	}

	// set
	public void setMainArray(String[][] mainArray) {
		this.mainArray = mainArray;
	}

}// DistributingWords