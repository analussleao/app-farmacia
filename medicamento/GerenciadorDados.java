package medicamento;

import com.google.gson.Gson; //metodos fundamentais da biblioteca
import com.google.gson.GsonBuilder; //configura e customiza, arquivo é salvo uebrado por linhas junto com .setPrettyPriting
import com.google.gson.reflect.TypeToken; //converte o arquivo para o tipo array medicamento
import java.io.FileReader;//leitor do arquivo
import java.io.FileWriter; //escreve no arquvo
import java.io.IOException; //trata erros
import java.lang.reflect.Type; // é usado junto com TypeToken guarda o tipo de dado
import java.util.ArrayList;

public class GerenciadorDados {
    public static final String CAMINHO_ARQUIVO = "medicamento.json"; //localização e nome do arquivo fisico (cria ou procura)
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();//inicia o motor do google que traduz os dados
    //new chama o construtor builder para customizar o arquivo
    //.setPretty: configuração para não salvar tudo em 1 unica linha
    //.create finaliza e cria o objeto gson

    public static void salvarMedicamentos(ArrayList<Medicamento> lista){ //coração de gravacao de dados pega a lista da RAM  e salva no HD
        //variável writer por onde os dados passam e instancia o escritor apontando para o arquivo medicamentos.json
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) { //USAR O TRY QDO MEXE NO SO OU THROWS PARA AVISAR QUE É PERIGOSO
           //o metodo Json recebe 2 coisas: lista de objetos java e o canal fisico writer
           //ele varre a lista e transforma cada objeto Medicamento em bloco de texto formatado
            gson.toJson(lista, writer);
            System.out.println("\n[Sistema] Dados persistidos com sucesso em " + CAMINHO_ARQUIVO);
        } catch (IOException e) {//intercepta erros e o erro fica dentro da variavel e
        System.out.println("\n[Erro] Falha ao gravar os dados no arquivo: " + e.getMessage());
        }    
    }

    //void significa que vai executar uma tarefa mas devolve informação ou dado para classe principal 

    //carregar medicamento tem que retornar por isso não tem void, usa return
    //a classe principal chama carregar medicamentos o metodo vai no HD e transforma o texto em um arrylst

    public static ArrayList<Medicamento> carregarMedicamentos() {
        //tenta abrir umcanal de leitura (FileReader) chamado reader apontando para o arquivo medicamento.json
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            //em tempo de execução apaga as infos de tipos genericos typetoken cria um carimbo que captura e preserva a info ue precisa de uma rraylist do tipo medicamento
            Type tipoLista =  new TypeToken<ArrayList<Medicamento>>(){}.getType();
            //motor do gson age com o metodo fromJson pega o texto atraves do reader interpreta o Jsonusando as regras tipolista e reconstroi na variavel lista carregada
            ArrayList<Medicamento> listaCarregada = gson.fromJson(reader, tipoLista);

            //previde o NullPointerException, que ocorre quando a lista vem nula, o if intercepta e devolve um arraylits novo
            if (listaCarregada == null) {
                return new ArrayList<>();
             }
             //quando continua os dados
            System.out.println("[Sistema] Banco de Dados local carregado com sucesso.");
            return listaCarregada;
                //ocorre quando é  aprimeira vez que roda o app e inicia uma lista limpa
        } catch (IOException e) {
        System.out.println("[Sistema] Nenhum banco de dados local encontrado. Iniciando nova lista ");
        return new ArrayList<>();
        }
    }
}
