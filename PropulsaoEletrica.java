/**
 * Propulsão Elétrica (Iônica) - herda de SistemaPropulsao.
 * Usa íons acelerados por campo elétrico. Empuxo menor,
 * porém extremamente eficiente e econômico para longas viagens.
 */
public class PropulsaoEletrica extends SistemaPropulsao {

    // Atributos específicos da propulsão elétrica
    private double nivelEnergia;         // 0 a 100 (%)
    private double eficiencia;           // Isp (impulso específico) em segundos
    private double tensaoOperacao;       // tensão do sistema em Volts
    private boolean paineisSolaresAtivos;

    private static final double TENSAO_MAXIMA = 10_000.0; // 10 kV

    public PropulsaoEletrica(String id, String nome) {
        // Chama o construtor da classe mãe com super()
        super(id, nome, 200.0); // empuxo máximo: 200 Newtons (muito menor que a química)
        this.nivelEnergia = 100.0;
        this.eficiencia = 3000.0; // Isp de ~3000s (muito superior ao foguete químico ~450s)
        this.tensaoOperacao = 0.0;
        this.paineisSolaresAtivos = true;
    }

    /**
     * Acelera o motor iônico.
     * Comportamento: resposta mais lenta, mas consome muito menos energia.
     * Eficiência aumenta com o tempo de operação.
     */
    @Override
    public void acelerar(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 100) {
            System.out.println("[ERRO] Potência inválida! Use valores entre 0 e 100.");
            return;
        }
        if (!motorLigado) {
            System.out.println("[ERRO] Ligue o motor antes de acelerar!");
            return;
        }
        if (nivelEnergia <= 0) {
            System.out.println("[CRÍTICO] Sem energia! Verifique os painéis solares.");
            return;
        }
        if (!paineisSolaresAtivos && nivelEnergia < 30) {
            System.out.println("[ALERTA] Painéis solares inativos e energia baixa!");
        }

        // Chama cálculo da classe mãe
        this.potenciaAtual = porcentagem;
        super.calcularEmpuxo();

        // Comportamento específico da propulsão elétrica
        // Consome muito menos energia que a química (0.01 por %)
        tensaoOperacao = (porcentagem / 100.0) * TENSAO_MAXIMA;
        nivelEnergia = Math.max(0, nivelEnergia - (porcentagem * 0.01));

        // Se painéis solares ativos, recarrega parte da energia
        if (paineisSolaresAtivos) {
            nivelEnergia = Math.min(100, nivelEnergia + 0.5);
        }

        System.out.printf("[%s] Motor iônico a %.0f%% de potência.%n", nome, porcentagem);
        System.out.printf("  Empuxo gerado  : %.1f N%n", empuxoAtual);
        System.out.printf("  Tensão         : %.0f V%n", tensaoOperacao);
        System.out.printf("  Energia rest.  : %.1f%%%n", nivelEnergia);
        System.out.printf("  Eficiência(Isp): %.0f s%n", eficiencia);
        System.out.println("  Painéis solares: " + (paineisSolaresAtivos ? "ATIVOS" : "INATIVOS"));
        System.out.println("  [INFO] Propulsão iônica: baixo empuxo, altíssima eficiência.");
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus(); // chama método da classe mãe
        System.out.println("  -- Dados Elétricos --");
        System.out.printf ("  Energia rest.  : %.1f%%%n", nivelEnergia);
        System.out.printf ("  Tensão Operação: %.0f V%n", tensaoOperacao);
        System.out.printf ("  Eficiência(Isp): %.0f s%n", eficiencia);
        System.out.println("  Painéis Solares: " + (paineisSolaresAtivos ? "ATIVOS" : "INATIVOS"));
    }

    public void ativarPaineisSolares() {
        paineisSolaresAtivos = true;
        System.out.println("[" + nome + "] Painéis solares ATIVADOS.");
    }

    public void desativarPaineisSolares() {
        paineisSolaresAtivos = false;
        System.out.println("[" + nome + "] Painéis solares DESATIVADOS.");
    }

    // Getters específicos
    public double getNivelEnergia() { return nivelEnergia; }
    public double getEficiencia() { return eficiencia; }
    public boolean isPaineisSolaresAtivos() { return paineisSolaresAtivos; }
}
