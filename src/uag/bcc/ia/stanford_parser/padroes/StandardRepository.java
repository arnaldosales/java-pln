package uag.bcc.ia.stanford_parser.padroes;

import java.util.ArrayList;



public class StandardRepository {
	
	private Standards array[] = new Standards[100];
	private static int size = 0;
	
	//Adicionar padrao ao repositorio
	public void addStandardRep(String str){
		if (size < 100){
			Standards obj = new Standards(str);
			obj.addStandard();
			this.array[size] = obj;
			this.size++;
		} else {
			System.out.println("Limite de padroes excedido!");
		  }
	}//addStandardRep()
	
	
	
	
	//Retorna o tagggig do repositorio
	public void printTaggings(){
		System.out.println();
		System.out.println("Taggings cadastradas:");
		System.out.println();
		for(int i=0; i<this.size; i++){
			System.out.println("palavras: "+ array[i].getSizeWord());			
			System.out.print("| "+ (i+1) +" | ");
			System.out.println(array[i].getTagging()+" | ");
		}
		System.out.println("----------------------------------------------------------");
	}//printTaggings()
	
	
	
	public String[][] getStandards(){
		String r[][] = null;
		for(int i=0; i<this.size; i++){
			for(int j=0; j<array[i].getSizeWord(); j++){	
				r[i][j] = array[i].getStandardArray()[1][j];			
			}			
		}
		return r;
	}
	
	
	
	//Retorna os padroes salvos.
	public void printStandards(){
	
		for(int i=0; i<this.size; i++){
			System.out.print("| "+ (i+1) +" | ");
			for(int j=0; j<array[i].getSizeWord(); j++){	
				System.out.print(array[i].getStandardArray()[1][j]+" ");			
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------");
	}//printStandards()
	
	

	//gets
	public static int getSize() {
		return size;
	}
	public Standards[] getArray() {
		return array;
	}
	
	
	
}//StandardRepository