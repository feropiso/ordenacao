package problema2381;

import java.util.Scanner;

public class ListaDeChamada {

	private String[] heap; 
	private int tamanho;   
	private int capacidade; 


	public ListaDeChamada(int capacidade) {
		this.capacidade = capacidade;
		this.tamanho = 0;
		this.heap = new String[capacidade + 1]; 
	}


	public void insere(String x) {

		if (tamanho == capacidade) {
			System.out.println("Heap cheia, não é possível inserir!");
			return;
		}

		tamanho++; 
		int k = tamanho; 

		while (k > 1 && comparaStrings(x, k/2)) {
			heap[k] = heap[k / 2]; 
			k = k / 2; 
		}

		heap[k] = x; 
	}

	private boolean comparaStrings(String s, int k) {
				
		String comparada = heap[k]; 
	    
	    int comparicao = s.compareToIgnoreCase(comparada);
	    
	    if (comparicao > 0) {
	        return true; 
	    } else {
	        return false; 
	    }

	}

	public String remove() {
		
		String topo = heap[1];      // O maior elemento (na raiz)
	    String ultimo = heap[tamanho]; // O último elemento da heap
	    tamanho--;                  // Reduz o tamanho da heap
	    int k = 1;

	    while (2 * k <= tamanho) {   // Enquanto houver filhos
	        int filho = 2 * k;       // Filho esquerdo

	        // Se o filho direito existe e é maior que o esquerdo
	        if (filho < tamanho && comparaStrings(heap[filho + 1], filho)) {
	            filho++;
	        }

	        // Se o último é maior ou igual ao maior filho, termine
	        if (comparaStrings(ultimo, filho)) {
	            break;
	        }

	        heap[k] = heap[filho]; // Substitua o pai pelo filho
	        k = filho;             // Desça para o próximo nível
	    }

	    heap[k] = ultimo;          // Coloque o último no lugar correto

	    return topo;
	}
	
	public void exibeAlunoVencedor(int k) {
		
		int quantidade_alunos = tamanho;
		
		String[] alunosOrdenados = new String[quantidade_alunos];
		
		for(int i = quantidade_alunos - 1; i >= 0; i--) 			
			alunosOrdenados[i] =  remove();
		
				
		System.out.println(alunosOrdenados[k - 1]);	
		
	}
		
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int K = scanner.nextInt();
		scanner.nextLine();  
		
		ListaDeChamada listaDeChamada = new ListaDeChamada(N); 
		
		for (int i = 0; i < N; i++) {
			listaDeChamada.insere(scanner.nextLine());
		}
		
		listaDeChamada.exibeAlunoVencedor(K);			
		
		scanner.close();		

	}
}

