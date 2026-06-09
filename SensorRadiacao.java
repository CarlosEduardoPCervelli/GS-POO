import java.util.Random;

/**
 * Sensor de Radiação - implementa a interface Sensor.
 * Mede o nível de radiação em milissieverts (mSv).
 * Nível seguro: até 1 mSv/hora. Acima de 2 mSv = CRÍTICO!
 */
public class SensorRadiacao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAlerta;
    private boolean operacional;
    private Random random;

    // Radiação simulada entre 0.1 e 5.0 mSv
    private static final double RAD_MIN = 0.1;
    private static final double RAD_MAX = 5.0;

    public SensorRadiacao(String id, String nome) {
        super(id, nome, 18.0);
        this.limiteAlerta = 2.0; // alerta acima de 2.0 mSv
        this.operacional = true;
        this.random = new Random();
        this.valorAtual = 0.5;
    }

    @Override
    public double lerValor() {
        if (!operacional) {
            System.out.println("[ERRO] Sensor de radiação " + getNome() + " com falha!");
            return -999;
        }
        valorAtual = RAD_MIN + (RAD_MAX - RAD_MIN) * random.nextDouble();
        System.out.printf("[%s] Radiação lida: %.3f mSv%n", getNome(), valorAtual);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        operacional = random.nextDouble() > 0.05;
        System.out.println("[" + getNome() + "] Funcionamento: " + (operacional ? "NORMAL" : "FALHA DETECTADA"));
        return operacional;
    }

    @Override
    public String retornarTipo() {
        return "Radiação";
    }

    @Override
    public void setLimiteAlerta(double limite) {
        this.limiteAlerta = limite;
        System.out.printf("[%s] Limite de alerta definido para: %.2f mSv%n", getNome(), limite);
    }

    @Override
    public boolean estaEmAlerta() {
        return valorAtual > limiteAlerta;
    }

    /** Classifica o nível de radiação em 3 categorias */
    public String classificarNivel() {
        if (valorAtual <= 1.0) return "NORMAL";
        if (valorAtual <= 2.0) return "ATENÇÃO";
        if (valorAtual <= 3.5) return "ALERTA";
        return "CRÍTICO";
    }

    @Override
    public void realizarDiagnostico() {
        System.out.println("=== DIAGNÓSTICO: " + getNome() + " ===");
        System.out.printf("  Tipo         : %s%n", retornarTipo());
        System.out.printf("  Valor Atual  : %.3f mSv%n", valorAtual);
        System.out.printf("  Limite Alerta: %.2f mSv%n", limiteAlerta);
        System.out.println("  Nível        : " + classificarNivel());
        System.out.println("  Operacional  : " + (operacional ? "SIM" : "NÃO"));
        if (estaEmAlerta()) {
            System.out.println("  *** ALERTA: Radiação acima do limite seguro! ***");
        }
    }

    public double getValorAtual() { return valorAtual; }
    public double getLimiteAlerta() { return limiteAlerta; }
}
