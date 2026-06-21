import java.util.ArrayList;
import java.util.Scanner; //leitor de teclado
import medicamento.GerenciadorDados;
import medicamento.Medicamento; //ve como é a estrutura do objeto medicamento

public class Principal {
    public static void main(String[] args) {
       Scanner leitor = new Scanner(System.in); //mecanismo que monitora o teclado
       ArrayList<Medicamento> baseDeDados = GerenciadorDados.carregarMedicamentos(); //lista na RAM que armazena objetos medicamento
       
       int opcao = 0; //variável para guardar a escolha do usuário
       while (opcao != 4) { // enquanto for diferente de 4 repita o que esta aqui dentro para que nao feche já que a opção 3 é sair
           System.out.println("Menu:");
           System.out.println("1. Cadastrar medicamento");
           System.out.println("2. Consultar medicamento");
           System.out.println("3. Alterar medicamento");
           System.out.println("4. Sair");
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
                   System.out.println("\n--- Cadastrar Novo Medicamento ---");
                   int proximoId = 1;
                   if (!baseDeDados.isEmpty()){
                       proximoId = baseDeDados.get(baseDeDados.size() -1).getId() + 1;
                   }
                   System.out.println("Id gerado automaticamente para este registro: " + proximoId);
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
                   System.out.print("Digite a apresentação do medicamento (ex: Comprimido, Xarope): ");
                   String apresentacao = leitor.nextLine();

                    // cria um novo medicamento
                   Medicamento novoMedicamento = new Medicamento(proximoId, nome, classeTerapeutica, mecanismodeAcao, indicacao, contraIndicacao, efeitosColaterais, interacaoMedicamentosa, apresentacao);
                   baseDeDados.add(novoMedicamento);
                   GerenciadorDados.salvarMedicamentos(baseDeDados); // insere o novo objeto no final da lista
                   System.out.println("Medicamento cadastrado com sucesso!");
                   break; //fim do case 1, sai do switch e volta para o while

               case 2:
    System.out.print("Digite o nome do medicamento para consulta: ");
    String nomeConsulta = leitor.nextLine().trim();
    
    // 1. Cria uma lista apenas com as correspondências do nome digitado
    ArrayList<Medicamento> correspondenciasConsulta = new ArrayList<>();
    for (Medicamento m : baseDeDados) {
        if (m.getNome().equalsIgnoreCase(nomeConsulta)) {
            correspondenciasConsulta.add(m);
        }
    }

    // 2. Analisa o resultado do filtro
    if (correspondenciasConsulta.isEmpty()) {
        System.out.println("Medicamento não encontrado.");
        break;
    } 
    
    Medicamento medParaExibir = null;

    if (correspondenciasConsulta.size() == 1) {
        // Se só existe um com esse nome, já seleciona ele direto para exibir
        medParaExibir = correspondenciasConsulta.get(0);
    } else {
        // SE HOUVER MAIS DE UM: Mostra apenas a lista compacta de opções (sem a ficha técnica gigante)
        System.out.println("\nForam encontrados " + correspondenciasConsulta.size() + " registros para '" + nomeConsulta + "'. Escolha qual deseja visualizar:");
        for (Medicamento m : correspondenciasConsulta) {
            System.out.println("-> ID: " + m.getId() + " | Nome: " + m.getNome() + " | Apresentação: " + m.getApresentacao());
        }
        
        System.out.print("\nDigite o ID do medicamento que deseja abrir a ficha completa: ");
        if (!leitor.hasNextInt()) {
            System.out.println("Erro: Você deve digitar um ID numérico!");
            leitor.nextLine();
            break;
        }
        int idEscolhidoConsulta = leitor.nextInt();
        leitor.nextLine(); // Limpa o buffer

        // Busca o objeto exato correspondente ao ID dentro do subgrupo
        for (Medicamento m : correspondenciasConsulta) {
            if (m.getId() == idEscolhidoConsulta) {
                medParaExibir = m;
                break;
            }
        }
    }

    // 3. Exibe a Ficha Técnica de forma isolada apenas se o objeto foi validado
    if (medParaExibir != null) {
        System.out.println("\n===================================================");
        System.out.println("[ID: " + medParaExibir.getId() + "] --- FICHA TÉCNICA DETALHADA ---");
        System.out.println("Nome: " + medParaExibir.getNome());
        System.out.println("Apresentação: " + medParaExibir.getApresentacao());
        System.out.println("Classe Terapêutica: " + medParaExibir.getClasseTerapeutica());
        System.out.println("Mecanismo de Ação: " + medParaExibir.getMecanismodeAcao());
        System.out.println("Indicação: " + medParaExibir.getIndicacao());
        System.out.println("Contra-indicação: " + medParaExibir.getContraIndicacao());
        System.out.println("Efeitos Colaterais: " + medParaExibir.getEfeitosColaterais());
        System.out.println("Interações Medicamentosas: " + medParaExibir.getInteracaoMedicamentosa());
        System.out.println("===================================================");
    } else {
        System.out.println("ID inválido. Consulta cancelada.");
    }
    break;
                  

                case 3:
                    System.out.print("Digite o ID ou o Nome do medicamento que deseja alterar: ");
                    String buscaAlterar = leitor.nextLine().trim();
                    Medicamento medEncontrado = null;

                    // se o usuário digitou o ID
                    if (buscaAlterar.matches("\\d+")) {
                        int idBuscado = Integer.parseInt(buscaAlterar);
                        for (Medicamento m : baseDeDados) {
                            if (m.getId() == idBuscado) {
                                medEncontrado = m;
                                break;
                            }
                        }
                    } else {
                        // se digitou nome, cria uma lista de correspondencias
                        ArrayList<Medicamento> correspondencias = new ArrayList<>();
                        for (Medicamento m : baseDeDados) {
                            if (m.getNome().equalsIgnoreCase(buscaAlterar)){
                                correspondencias.add(m);
                            }
                        }
                        
                        //analisa o resultado
                        if (correspondencias.isEmpty()) {
                            System.out.println("Nenhum medicamento encontrado com o nome: " + buscaAlterar);
                            break;
                        } else if (correspondencias.size() == 1){
                            medEncontrado = correspondencias.get(0);
                        }else{
                            //se houver mais de um apresenta a lista
                            System.out.println("\n[Aviso] Foram encontrados " + correspondencias.size() + " medicamentos com o nome '" + buscaAlterar + "':");
                            for (Medicamento m : correspondencias) {
                                System.out.println("-> ID: " + m.getId() + " | Nome: " + m.getNome() + " | Apresentação: " + m.getApresentacao());
                            }
                        //exige o ID para seguir
                        System.out.println("\nDigite o ID do medicamento que deseja alterar: ");
                        if (!leitor.hasNextInt()) {
                            System.out.println("Erro: Voc~e deve digitar um ID numérico!");
                            leitor.nextLine();
                            break;
                        }
                        int idEscolhido = leitor.nextInt();
                        leitor.nextLine(); //Limpa o buffer do teclado

                        for(Medicamento m : correspondencias){
                            if(m.getId() == idEscolhido){
                                medEncontrado = m;
                                break;
                            }
                        }
                    }
                        }
                    if (medEncontrado == null) {
                        System.out.println("Seleção inválida ou ID não encontrado. Operação cancelada.");
                        break;
                    }

                    // 3. Abre o Submenu de Alterações com persistência em tempo real no arquivo JSON
    int pcaoSubmenu = 0;
    while (pcaoSubmenu != 9) {
        System.out.println("\n--- Alterando Registro (ID: " + medEncontrado.getId() + ") ---");
        System.out.println("1. Nome (Atual: " + medEncontrado.getNome() + ")");
        System.out.println("2. Classe Terapêutica (Atual: " + medEncontrado.getClasseTerapeutica() + ")");
        System.out.println("3. Mecanismo de Ação (Atual: " + medEncontrado.getMecanismodeAcao() + ")");
        System.out.println("4. Indicação (Atual: " + medEncontrado.getIndicacao() + ")");
        System.out.println("5. Contra-indicação (Atual: " + medEncontrado.getContraIndicacao() + ")");
        System.out.println("6. Efeitos Colaterais (Atual: " + medEncontrado.getEfeitosColaterais() + ")");
        System.out.println("7. Interações Medicamentosas (Atual: " + medEncontrado.getInteracaoMedicamentosa() + ")");
        System.out.println("8. Apresentação (Atual: " + medEncontrado.getApresentacao() + ")");
        System.out.println("9. Voltar ao Menu Principal");
        System.out.print("Escolha o campo que deseja modificar: ");

        if(!leitor.hasNextInt()){
            System.out.println("Erro: Digite um número inteiro válido!");
            leitor.nextLine();
            continue;
        }
        pcaoSubmenu = leitor.nextInt();
        leitor.nextLine(); // Limpa o buffer do teclado

        switch (pcaoSubmenu) {
            case 1:
                System.out.println("Nome Atual: " + medEncontrado.getNome());
                System.out.print("Digite o novo Nome (ou aperte ENTER para manter o atual): ");
                String novoNome = leitor.nextLine().trim();
                if (!novoNome.isEmpty()) {
                    medEncontrado.setNome(novoNome);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Nome atualizado com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 2:
                System.out.println("Classe Terapêutica Atual: " + medEncontrado.getClasseTerapeutica());
                System.out.print("Digite a nova Classe Terapêutica (ou aperte ENTER para manter o atual): ");
                String novaClasse = leitor.nextLine().trim();
                if (!novaClasse.isEmpty()) {
                    medEncontrado.setClasseTerapeutica(novaClasse);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Classe Terapêutica atualizada com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 3:
                System.out.println("Mecanismo de Ação Atual: " + medEncontrado.getMecanismodeAcao());
                System.out.print("Digite o novo Mecanismo de Ação (ou aperte ENTER para manter o atual): ");
                String novoMec = leitor.nextLine().trim();
                if (!novoMec.isEmpty()) {
                    medEncontrado.setMecanismodeAcao(novoMec);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Mecanismo de Ação atualizado com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 4:
                System.out.println("Indicação Atual: " + medEncontrado.getIndicacao());
                System.out.print("Digite a nova Indicação (ou aperte ENTER para manter o atual): ");
                String novaInd = leitor.nextLine().trim();
                if (!novaInd.isEmpty()) {
                    medEncontrado.setIndicacao(novaInd);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Indicação atualizada com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 5:
                System.out.println("Contra-indicação Atual: " + medEncontrado.getContraIndicacao());
                System.out.print("Digite a nova Contra-indicação (ou aperte ENTER para manter o atual): ");
                String novaContra = leitor.nextLine().trim();
                if (!novaContra.isEmpty()) {
                    medEncontrado.setContraIndicacao(novaContra);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Contra-indicação atualizada com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 6:
                System.out.println("Efeitos Colaterais Atuais: " + medEncontrado.getEfeitosColaterais());
                System.out.print("Digite os novos Efeitos Colaterais (ou aperte ENTER para manter o atual): ");
                String novosEfeitos = leitor.nextLine().trim();
                if (!novosEfeitos.isEmpty()) {
                    medEncontrado.setEfeitosColaterais(novosEfeitos);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Efeitos Colaterais atualizados com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 7:
                System.out.println("Interações Atuais: " + medEncontrado.getInteracaoMedicamentosa());
                System.out.print("Digite as novas Interações Medicamentosas (ou aperte ENTER para manter o atual): ");
                String novasInteracoes = leitor.nextLine().trim();
                if (!novasInteracoes.isEmpty()) {
                    medEncontrado.setInteracaoMedicamentosa(novasInteracoes);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Interações Medicamentosas atualizadas com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 8:
                System.out.println("Apresentação Atual: " + medEncontrado.getApresentacao());
                System.out.print("Digite a nova Apresentação (ou aperte ENTER para manter o atual): ");
                String novaApres = leitor.nextLine().trim();
                if (!novaApres.isEmpty()) {
                    medEncontrado.setApresentacao(novaApres);
                    GerenciadorDados.salvarMedicamentos(baseDeDados);
                    System.out.println("Apresentação atualizada com sucesso!");
                } else {
                    System.out.println("Informação mantida.");
                }
                break;
            case 9:
                System.out.println("Retornando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida no submenu. Tente novamente.");
        }
    }
    break;

case 4:
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