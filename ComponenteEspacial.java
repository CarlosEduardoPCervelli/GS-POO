/**
 * Classe abstrata que representa um componente genérico da estação espacial.
 * Serve como "molde" para todos os componentes do sistema.
 */
public abstract class ComponenteEspacial {

    // Atributos comuns a todos os componentes
    protected String id;
    protected String nome;
    protected boolean status; // true = ligado, false = desligado
    protected double temperatura; // em graus Celsius

    // Construtor
    public ComponenteEspacial(String id, String nome, double temperatura) {
        this.id = id;
        this.nome = nome;
        this.temperatura = temperatura;
        this.status = false; // começa desligado por padrão
    }

    // ---- Métodos concretos ----

    /** Liga o componente */
    public void ligar() {
        this.status = true;
        System.out.println("[" + nome + "] Componente LIGADO.");
    }

    /** Desliga o componente */
    public void desligar() {
        this.status = false;
        System.out.println("[" + nome + "] Componente DESLIGADO.");
    }

    /** Exibe informações básicas do componente */
    public void exibirInfo() {
        System.out.println("----------------------------------");
        System.out.println("ID       : " + id);
        System.out.println("Nome     : " + nome);
        System.out.println("Status   : " + (status ? "LIGADO" : "DESLIGADO"));
        System.out.printf ("Temp.    : %.1f°C%n", temperatura);
    }

    // ---- Método abstrato (subclasses DEVEM implementar) ----

    /**
     * Cada componente deve saber exibir seu diagnóstico específico.
     */
    public abstract void realizarDiagnostico();

    // ---- Getters e Setters básicos ----

    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isLigado() { return status; }
    public double getTemperatura() { return temperatura; }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
}
