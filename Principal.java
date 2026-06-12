import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import medicamento.Medicamento;

public class Principal {
    public static void main(String[] args) {
       
        List<Medicamento> medicamentos = new ArrayList<>();

        Medicamento medicamento1 = new Medicamento("Paracetamol", "Farmacêutica XYZ", "Analgésico e Antipirético", "Inibe a síntese de prostaglandinas no sistema nervoso central", "Alívio da dor e redução da febre", "Hipersensibilidade ao paracetamol, insuficiência hepática grave", "Reações alérgicas, náuseas, vômitos", "Pode aumentar o efeito de anticoagulantes");
        medicamento1.setNome("Paracetamol");
        medicamento1.setFabricante("Farmacêutica XYZ");
        medicamento1.setClasseTerapeutica("Analgésico e Antipirético");
        medicamento1.setMecanismodeAcao("Inibe a síntese de prostaglandinas no sistema nervoso central");
        medicamento1.setIndicacao("Alívio da dor e redução da febre");
        medicamento1.setContraIndicacao("Hipersensibilidade ao paracetamol, insuficiência hepática grave");
        medicamento1.setEfeitosColaterais("Reações alérgicas, náuseas, vômitos");
        medicamento1.setInteracaoMedicamentosa("Pode aumentar o efeito de anticoagulantes");

        medicamentos.add(medicamento1);
    }
}