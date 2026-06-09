import java.util.Random;

/**
 * Sensor de Pressão - implementa a interface Sensor.
 * Mede a pressão interna da estação em kPa (quilopascal).
 * Pressão normal: ~101 kPa. Abaixo de 80 kPa = perigo!
 */
public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAlerta;
    private boolean operacional;
    private Random random;

    // Pressão simulada entre 60 e 120 kPa
    private static final double PRESSAO_MIN = 60.0;
    private static final double PRESSAO_MAX = 120.0;

    public SensorPressao(String id, String nome) {
        super(id, nome, 22.0);
        this.limiteAlerta = 80.0; // alerta abaixo de 80 kPa
        this.operacional = true;
        this.random = new Random();
        this.valorAtual = 101.0;
    }

    @Override
    public double lerValor() {
        if (!operacional) {
            System.out.println("[ERRO] Sensor de pressão " + getNome() + " com falha!");
            return -999;
        }
        valorAtual = PRESSAO_MIN + (PRESSAO_MAX - PRESSAO_MIN) * random.nextDouble();
        System.out.printf("[%s] Pressão lida: %.2f kPa%n", getNome(), valorAtual);
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
        return "Pressão";
    }

    @Override
    public void setLimiteAlerta(double limite) {
        this.limiteAlerta = limite;
        System.out.printf("[%s] Limite de alerta definido para: %.1f kPa%n", getNome(), limite);
    }

    @Override
    public boolean estaEmAlerta() {
        // Para pressão, o alerta é quando cai ABAIXO do limite (diferente da temperatura)
        return valorAtual < limiteAlerta;
    }

    @Override
    public void realizarDiagnostico() {
        System.out.println("=== DIAGNÓSTICO: " + getNome() + " ===");
        System.out.printf("  Tipo         : %s%n", retornarTipo());
        System.out.printf("  Valor Atual  : %.2f kPa%n", valorAtual);
        System.out.printf("  Limite Alerta: %.2f kPa%n", limiteAlerta);
        System.out.println("  Operacional  : " + (operacional ? "SIM" : "NÃO"));
        if (estaEmAlerta()) {
            System.out.println("  *** ALERTA: Pressão abaixo do limite seguro! ***");
        }
    }

    public double getValorAtual() { return valorAtual; }
    public double getLimiteAlerta() { return limiteAlerta; }
}
