/**
 * Propulsão Química - herda de SistemaPropulsao.
 * Usa combustível químico (ex: hidrogênio líquido + oxigênio).
 * Acelera de forma linear, com aquecimento gradual.
 */
public class PropulsaoQuimica extends SistemaPropulsao {

    // Atributos específicos da propulsão química
    private double nivelCombustivel;     // 0 a 100 (%)
    private double temperaturaCombustao; // °C
    private String tipoCombustivel;

    private static final double TEMP_CRITICA = 3500.0; // temperatura máxima segura

    public PropulsaoQuimica(String id, String nome) {
        // Chama o construtor da classe mãe com super()
        super(id, nome, 3_500_000.0); // empuxo máximo: 3.5 milhões de Newtons
        this.nivelCombustivel = 100.0;
        this.temperaturaCombustao = 0.0;
        this.tipoCombustivel = "Hidrogênio Líquido + Oxigênio";
    }

    /**
     * Acelera o motor químico.
     * Comportamento: aquece proporcionalmente à potência.
     * Consome combustível a cada aceleração.
     */
    @Override
    public void acelerar(double porcentagem) {
        // Validação de valores (não aceitar fora de 0-100)
        if (porcentagem < 0 || porcentagem > 100) {
            System.out.println("[ERRO] Potência inválida! Use valores entre 0 e 100.");
            return;
        }
        if (!motorLigado) {
            System.out.println("[ERRO] Ligue o motor antes de acelerar!");
            return;
        }
        if (nivelCombustivel <= 0) {
            System.out.println("[CRÍTICO] Sem combustível! Não é possível acelerar.");
            return;
        }

        // Chama cálculo da classe mãe via super
        this.potenciaAtual = porcentagem;
        super.calcularEmpuxo();

        // Comportamento específico da propulsão química
        temperaturaCombustao = (porcentagem / 100.0) * TEMP_CRITICA;
        nivelCombustivel = Math.max(0, nivelCombustivel - (porcentagem * 0.05));

        System.out.printf("[%s] Acelerando a %.0f%% de potência.%n", nome, porcentagem);
        System.out.printf("  Empuxo gerado    : %.0f N%n", empuxoAtual);
        System.out.printf("  Temperatura      : %.0f°C%n", temperaturaCombustao);
        System.out.printf("  Combustível rest.: %.1f%%%n", nivelCombustivel);

        if (temperaturaCombustao > TEMP_CRITICA * 0.9) {
            System.out.println("  ⚠️  [ATENÇÃO] Temperatura de combustão elevada!");
        }
        if (nivelCombustivel < 20) {
            System.out.println("  ⚠️  [ALERTA] Combustível abaixo de 20%!");
        }
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus(); // chama método da classe mãe
        System.out.println("  -- Dados Químicos --");
        System.out.println("  Combustível  : " + tipoCombustivel);
        System.out.printf ("  Nível Comb.  : %.1f%%%n", nivelCombustivel);
        System.out.printf ("  Temp. Combust: %.0f°C%n", temperaturaCombustao);
    }

    // Getters específicos
    public double getNivelCombustivel() { return nivelCombustivel; }
    public double getTemperaturaCombustao() { return temperaturaCombustao; }
    public String getTipoCombustivel() { return tipoCombustivel; }
}
