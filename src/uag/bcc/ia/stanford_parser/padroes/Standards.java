package uag.bcc.ia.stanford_parser.padroes;

/**
 * 
 * @author Paulinely Morgan
 * @author Raphael Cordeiro
 */
public class Standards {

	private String tagging;
	private String standardArray[][];
	private int sizeWord;

	// Construtores
	public Standards(String str) {
		this.tagging = str;
	}

	public Standards() {

	}

	// Adicionando o tagging ao array
	public void addStandard() {
		DistributingWords obj = new DistributingWords();
		obj.distributingWordsArray(this.tagging);
		this.standardArray = obj.getMainArray();
		this.sizeWord = obj.getSizeWord();
	}// addStandard()

	// Reorganiza a frase, de acordo com o padrão encontrado (frase c/ o mesmo
	// n. de palavras do padrão)
	public String[][] remodelPhraseSameSize(String rightArray[][],
			String wrongArray[][], int size) {
		String temp[][] = new String[2][size];

		// fixa o tag do array certo e varre o array errado, a procura do tag
		// fixado.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (rightArray[1][i].equals(wrongArray[1][j])) {
					// Quando encontra o tag, pega a palavra e a tag, e joga no
					// array provisório
					// na mesma posição do array certo.
					temp[0][i] = wrongArray[0][j];
					temp[1][i] = wrongArray[1][j];

					// Limpa o tag da posição já encontrada, para evitar duplas
					// ocorrências
					wrongArray[1][j] = "";
					break;
				}
			}
		}

		return temp;
	}// remodelPhraseSameSize()

	// Compara uma frase normal com as do repositório de padrões
	public void comparePhraseWRepository(String phrase, StandardRepository obj) {
		DistributingWords obj2 = new DistributingWords();
		int standardDoor = 0, tagDoor = 0;

		// Distribuindo a frase normal num array, para pode fazer a comparação
		// das tags
		obj2.distributingWordsArray(phrase);

		// printArrayPhrase(obj2.getMainArray(), obj2.getSizeWord());
		// System.out.println(obj2.getMainArray()[1][1]);
		// Verifica se a quantidade de palavras da frase, é igual a de algum dos
		// padrões
		for (int i = 0; i < obj.getSize(); i++) {
			int count = 0;

			// Se a quantidade de palavras da frase for igual ao de algum padrão
			// do repositório
			if (obj2.getSizeWord() == obj.getArray()[i].getSizeWord()) {
				standardDoor = 1;
				System.out.println();
				System.out.println("Quantidade de palavras igual ao Padrão "
						+ (i + 1) + ". (" + obj2.getSizeWord() + " palavras)");
				System.out.println();
				System.out.println("Verificando a ordenação...");

				// Olha tag por tag e incrementa 'count'
				for (int j = 0; j < obj2.getSizeWord(); j++) {

					// Caso a tag da palavra já esteja na mesma posição da tag
					// do padrão encontrado, ele incrementa
					if (obj2.getMainArray()[1][j].equals(obj.getArray()[i]
							.getStandardArray()[1][j])) {
						count++;
					}
				}

				// Se todas as tags estiverem na mesma posição das tags do
				// padrão encontrado, a palavra já está ordenada
				if (count == obj2.getSizeWord()) {
					tagDoor = 1;
					System.out.println();
					System.out.println("A frase já está ordenada!");
					break;
				} else {
					int count2 = 0;

					// 'Obj2' será limpada logo abaixo, precisa-se preserver os
					// dados da frase desejada
					String objTemp[][] = new String[2][obj2.getSizeWord()];
					for (int m = 0; m < obj2.getSizeWord(); m++) {
						objTemp[0][m] = obj2.getMainArray()[0][m];
						objTemp[1][m] = obj2.getMainArray()[1][m];
					}

					// Caso a frase tenha o mesmo n. de palavras do padrão
					// encontrado no repositorio,
					// mas as tags estando desordenadas. Daí ele vai verificar
					// se todas as
					// tags da frase encontrada no repositório tbm estão na
					// frase desejada.
					for (int y = 0; y < obj2.getSizeWord(); y++) {
						for (int x = 0; x < obj2.getSizeWord(); x++) {
							if (obj.getArray()[i].getStandardArray()[1][y]
									.equals(objTemp[1][x])) {
								count2++;
								objTemp[1][x] = "";
								break;
							}
						}
					}

					// Caso a frase realmente tenha as mesmas tags do padrão
					// encontrado no repósitório
					if (count2 == obj2.getSizeWord()) {
						tagDoor = 1;
						standardDoor = 1;
						System.out
								.println("A frase está desordenada! Organizando a frase...");
						System.out.println();

						obj2.setMainArray(remodelPhraseSameSize(
								obj.getArray()[i].getStandardArray(),
								obj2.getMainArray(), obj2.getSizeWord()));

						// Imprimindo a frase resultante (organizada)
						printArrayPhrase(obj2.getMainArray(),
								obj2.getSizeWord());

						// A frase tem a mesma quantidade de padrões, mas as
						// tags são diferentes
					} else {
						standardDoor = 0;
						System.out
								.println("Não há semelhança entre a frase desejada e o padrão encontrado.");
						System.out
								.println("---------------------------------------------");
					}
				}// else se a frase está desordenada

			}// if qnt de palavras == padrão do repositório
		}// for

		// Casa não haja um padrão com a mesma quantidade de palavras
		if (standardDoor == 0) {
			System.out.println("Procurando o melhor caso...");
			System.out.println();

			// --------------Heurística-----------------------
			// Procurando se algum padrão tem, pelo menos 2/3 das tags da frase
			for (int u = 0; u < obj.getSize(); u++) {
				int count3 = 0;

				String objTemp2[] = new String[obj2.getSizeWord()];
				for (int m = 0; m < obj2.getSizeWord(); m++) {
					objTemp2[m] = obj2.getMainArray()[1][m];
				}

				String objTemp3[] = new String[obj.getArray()[u].getSizeWord()];
				for (int m = 0; m < obj.getArray()[u].getSizeWord(); m++) {
					objTemp3[m] = obj.getArray()[u].getStandardArray()[1][m];
					// System.out.println(objTemp3[m]);
				}

				// Caso olhando tag por tag, pra ver qual o mais próximo da
				// frase
				for (int y = 0; y < obj.getArray()[u].getSizeWord(); y++) {
					for (int x = 0; x < obj2.getSizeWord(); x++) {
						if (objTemp3[y].equals(objTemp2[x])) {
							count3++;
							objTemp2[x] = "";
							objTemp3[y] = "";
							break;
						}
					}
				}

				// Percentual de proximidade
				float percent = (float) 0.67;
				float parcial = obj2.getSizeWord() * percent;

				// Caso a frase tenha, pelo menos 2/3 das tags do padrão
				if (count3 >= parcial) {
					tagDoor = 1;
					System.out
							.println("O Padrão "
									+ (u + 1)
									+ ", foi escolhido. Ele tem 66,67% da classifacação da frase...");
					System.out.println("Organizando a frase...");
					System.out.println();

					obj2.setMainArray(remodelPhraseSameSize(
							obj.getArray()[u].getStandardArray(),
							obj2.getMainArray(), obj2.getSizeWord()));

					// Imprimindo a frase resultante (organizada)
					printArrayPhrase(obj2.getMainArray(), obj2.getSizeWord());
					break;
					// A frase tem a mesma quantidade de padrões, mas as tags
					// são diferentes
				} else {
					// System.out.println("Não há semelhança entre a frase desejada e o padrão encontrado.");
					// System.out.println("---------------------------------------------");
				}

			}// for heurística

			// Casa não haja um padrão com a mesma quantidade de palavras e tags
		} else if (tagDoor == 0) {
			System.out
					.println("Nenhum padrão tem a mesma classificação da frase.");
		}
	}// comparePhrases()

	// Imprimir uma frase de um array[][]
	public void printArrayPhrase(String array[][], int size) {
		System.out.print("FRASE FINAL: ");
		for (int i = 0; i < size; i++) {
			if (i == (size - 1)) {
				System.out.print(array[0][i] + ".");
			} else {
				System.out.print(array[0][i] + " ");
			}
		}
		System.out.println();
	}

	// gets
	public String getTagging() {
		return this.tagging;
	}

	public String[][] getStandardArray() {
		return this.standardArray;
	}

	public int getSizeWord() {
		return this.sizeWord;
	}

}// Standards
