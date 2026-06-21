package medicamento;

public class Medicamento { // Classe para representar um medicamento
    private int id;
    private String nome;
    private String classeTerapeutica;
    private String mecanismodeAcao;
    private String indicacao;
    private String contraIndicacao;
    private String efeitosColaterais;
    private String interacaoMedicamentosa;
    private String apresentacao;

    public Medicamento(int id, String nome, String classeTerapeutica, String mecanismodeAcao, String indicacao,
            String contraIndicacao, String efeitosColaterais, String interacaoMedicamentosa, String apresentacao) {
        // Construtor para inicializar os atributos do medicamento
        this.id = id;
        this.nome = nome;
        this.classeTerapeutica = classeTerapeutica;
        this.mecanismodeAcao = mecanismodeAcao;
        this.indicacao = indicacao;
        this.contraIndicacao = contraIndicacao;
        this.efeitosColaterais = efeitosColaterais;
        this.interacaoMedicamentosa = interacaoMedicamentosa;
        this.apresentacao = apresentacao;

    }

    // Getters e Setters para acessar e modificar os atributos do medicamento
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasseTerapeutica() {
        return classeTerapeutica;
    }

    public void setClasseTerapeutica(String classeTerapeutica) {
        this.classeTerapeutica = classeTerapeutica;
    }

    public String getMecanismodeAcao() {
        return mecanismodeAcao;
    }

    public void setMecanismodeAcao(String mecanismodeAcao) {
        this.mecanismodeAcao = mecanismodeAcao;
    }

    public String getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

    public String getContraIndicacao() {
        return contraIndicacao;
    }

    public void setContraIndicacao(String contraIndicacao) {
        this.contraIndicacao = contraIndicacao;
    }

    public String getEfeitosColaterais() {
        return efeitosColaterais;
    }

    public void setEfeitosColaterais(String efeitosColaterais) {
        this.efeitosColaterais = efeitosColaterais;
    }

    public String getInteracaoMedicamentosa() {
        return interacaoMedicamentosa;
    }

    public void setInteracaoMedicamentosa(String interacaoMedicamentosa) {
        this.interacaoMedicamentosa = interacaoMedicamentosa;
    }

    public String getApresentacao(){
        return apresentacao;
    }

    public void setApresentacao(String apresentacao){
        this.apresentacao = apresentacao;
    }

}
