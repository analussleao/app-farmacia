import java.util.ArrayList;
import java.util.Scanner; //leitor de teclado
import medicamento.GerenciadorDados;
import medicamento.Medicamento; //ve como é a estrutura do objeto medicamento

public class Principal {
    public static void main(String[] args) {
       Scanner leitor = new Scanner(System.in); //mecanismo que monitora o teclado
       ArrayList<Medicamento> baseDeDados = GerenciadorDados.carregarMedicamentos(); //lista na RAM que armazena objetos medicamento
       
       int opcao = 0; //variável para guardar a escolha do usuário
       while (opcao != 3) { // enquanto for diferente de 3 repita o que esta aqui dentro para que nao feche já que a opção 3 é sair
           System.out.println("Menu:");
           System.out.println("1. Cadastrar medicamento");
           System.out.println("2. Consultar medicamento");
           System.out.println("3. Sair");
           System.out.print("Escolha uma opção: ");
           if (!leitor.hasNextInt()) {
               System.out.println("Erro: Você deve digitar um número inteiro (1, 2 ou 3)!");
               leitor.nextLine(); // Descarta a letra digitada para destravar o teclado
               opcao = 0;         // Reseta a opção para o loop continuar
               continue;          // Força o Java a voltar para o topo do "while", pulando o switch
           }
           opcao = leitor.nextInt(); //o leitor pausa e espera o usuário digitar e o numero vai para variavell opcao
           leitor.nextLine(); // Consumir a quebra de linha para limpar após o enter

           switch (opcao) {
               case 1:
                   System.out.print("Digite o nome do medicamento: ");
                   String nome = leitor.nextLine(); //le o texto que foi digitado
                   System.out.print("Digite a classe terapêutica: ");
                   String classeTerapeutica = leitor.nextLine();
                   System.out.print("Digite o mecanismo de ação: ");
                   String mecanismodeAcao = leitor.nextLine();
                   System.out.print("Digite a indicação: ");
                   String indicacao = leitor.nextLine();
                   System.out.print("Digite a contra-indicação: ");
                   String contraIndicacao = leitor.nextLine();
                   System.out.print("Digite os efeitos colaterais: ");
                   String efeitosColaterais = leitor.nextLine();
                   System.out.print("Digite as interações medicamentosas: ");
                   String interacaoMedicamentosa = leitor.nextLine();
                    // cria um novo medicamento
                   Medicamento novoMedicamento = new Medicamento(nome, classeTerapeutica, mecanismodeAcao, indicacao, contraIndicacao, efeitosColaterais, interacaoMedicamentosa);
                   baseDeDados.add(novoMedicamento);
                   GerenciadorDados.salvarMedicamentos(baseDeDados); // insere o novo objeto no final da lista
                   System.out.println("Medicamento cadastrado com sucesso!");
                   break; //fim do case 1, sai do switch e volta para o while

               case 2:
                   System.out.print("Digite o nome do medicamento para consulta: ");
                   String nomeConsulta = leitor.nextLine();
                   boolean encontrado = false; // variavel do tipo flag que começa falsa/desligada e se o medicmaneto for encontrado vira true
                   for (Medicamento medicamento : baseDeDados) { //le a lista e joga cada medicamento tempoariamente na variave local
                       if (medicamento.getNome().equalsIgnoreCase(nomeConsulta)) {//busca o nome e ignora masiculas e minusculas
                           System.out.println("Nome: " + medicamento.getNome());
                           System.out.println("Classe Terapêutica: " + medicamento.getClasseTerapeutica());
                           System.out.println("Mecanismo de Ação: " + medicamento.getMecanismodeAcao());
                           System.out.println("Indicação: " + medicamento.getIndicacao());
                           System.out.println("Contra-indicação: " + medicamento.getContraIndicacao());
                           System.out.println("Efeitos Colaterais: " + medicamento.getEfeitosColaterais());
                           System.out.println("Interações Medicamentosas: " + medicamento.getInteracaoMedicamentosa());
                           encontrado = true;
                           break; //se o if for trueimprime a ficha e o break interrompe o laço
                       }
                   }
                   if (!encontrado) {// se nao encontrado com a variavel terminando em false
                       System.out.println("Medicamento não encontrado.");
                   } 
                     break;
                case 3:
                    System.out.println("Saindo do programa...");
                    break;     //se digitar 3 sai do programa  
                default: //executa se o usuário digitar opcao invalida em numero
                    System.out.println("Opção inválida. Tente novamente."); 
                    break;
              }
         }
         leitor.close(); // desliga o monitoramento do teclado
    }
}