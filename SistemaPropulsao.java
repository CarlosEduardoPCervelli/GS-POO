/**
 * Classe abstrata que representa o sistema de propulsão da estação espacial.
 * Define comportamentos comuns a todos os tipos de propulsão.
 * Subclasses devem implementar o método acelerar() de forma específica.
 */
public abstract class SistemaPropulsao {

    // Atributos comuns a todos os sistemas de propulsão
    protected String id;
    protected String nome;
    protected boolean motorLigado;
    protected double potenciaAtual;   // 0 a 100 (%)
    protected double empuxoMaximo;    // em Newtons (N)
    protected double empuxoAtual;     // calculado conforme potência

    // Construtor
    public SistemaPropulsao(String id, String nome, double empuxoMaximo) {
        this.id = id;
        this.nome = nome;
        this.empuxoMaximo = empuxoMaximo;
        this.motorLigado = false;
        this.potenciaAtual = 0.0;
        this.empuxoAtual = 0.0;
    }

    // ---- Métodos concretos comuns ----

    /** Liga o motor de propulsão */
    public void ligarMotor() {
        this.motorLigado = true;
        System.out.println("[" + nome + "] Motor LIGADO.");
    }

    /** Desliga o motor de propulsão */
    public void desligarMotor() {
        this.motorLigado = false;
        this.potenciaAtual = 0.0;
        this.empuxoAtual = 0.0;
        System.out.println("[" + nome + "] Motor DESLIGADO. Potência zerada.");
    }

    /** Calcula o empuxo com base na potência atual */
    public double calcularEmpuxo() {
        empuxoAtual = (potenciaAtual / 100.0) * empuxoMaximo;
        return empuxoAtual;
    }

    /** Exibe o status do sistema de propulsão */
    public void exibirStatus() {
        System.out.println("=== STATUS: " + nome + " ===");
        System.out.println("  ID          : " + id);
        System.out.println("  Motor       : " + (motorLigado ? "LIGADO" : "DESLIGADO"));
        System.out.printf ("  Potência    : %.1f%%%n", potenciaAtual);
        System.out.printf ("  Empuxo Max  : %.0f N%n", empuxoMaximo);
        System.out.printf ("  Empuxo Atual: %.0f N%n", empuxoAtual);
    }

    // ---- Método abstrato (cada tipo de propulsão implementa de forma diferente) ----

    /**
     * Acelera o sistema com a porcentagem de potência fornecida.
     * Cada tipo de propulsão tem comportamento diferente.
     * @param porcentagem valor de 0 a 100
     */
    public abstract void acelerar(double porcentagem);

    // ---- Getters ----

    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isMotorLigado() { return motorLigado; }
    public double getPotenciaAtual() { return potenciaAtual; }
    public double getEmpuxoMaximo() { return empuxoMaximo; }
    public double getEmpuxoAtual() { return empuxoAtual; }
}
