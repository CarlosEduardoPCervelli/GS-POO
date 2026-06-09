import java.util.Random;

/**
 * Sensor de Temperatura - implementa a interface Sensor.
 * Mede a temperatura em graus Celsius dentro da estação.
 */
public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAlerta;
    private boolean operacional;
    private Random random;

    // Limites normais de temperatura na estação: -50°C a 80°C
    private static final double TEMP_MIN = -50.0;
    private static final double TEMP_MAX = 80.0;

    public SensorTemperatura(String id, String nome) {
        super(id, nome, 20.0);
        this.limiteAlerta = 70.0; // padrão: alerta acima de 70°C
        this.operacional = true;
        this.random = new Random();
        this.valorAtual = 20.0;
    }

    @Override
    public double lerValor() {
        if (!operacional) {
            System.out.println("[ERRO] Sensor de temperatura " + getNome() + " com falha!");
            return -999;
        }
        // Simula leitura com variação aleatória
        valorAtual = TEMP_MIN + (TEMP_MAX - TEMP_MIN) * random.nextDouble();
        System.out.printf("[%s] Temperatura lida: %.2f°C%n", getNome(), valorAtual);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        // Simula 95% de chance de estar operacional
        operacional = random.nextDouble() > 0.05;
        System.out.println("[" + getNome() + "] Funcionamento: " + (operacional ? "NORMAL" : "FALHA DETECTADA"));
        return operacional;
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    @Override
    public void setLimiteAlerta(double limite) {
        this.limiteAlerta = limite;
        System.out.printf("[%s] Limite de alerta definido para: %.1f°C%n", getNome(), limite);
    }

    @Override
    public boolean estaEmAlerta() {
        return valorAtual > limiteAlerta;
    }

    @Override
    public void realizarDiagnostico() {
        System.out.println("=== DIAGNÓSTICO: " + getNome() + " ===");
        System.out.printf("  Tipo         : %s%n", retornarTipo());
        System.out.printf("  Valor Atual  : %.2f°C%n", valorAtual);
        System.out.printf("  Limite Alerta: %.2f°C%n", limiteAlerta);
        System.out.println("  Operacional  : " + (operacional ? "SIM" : "NÃO"));
        if (estaEmAlerta()) {
            System.out.println("  *** ALERTA: Temperatura acima do limite! ***");
        }
    }

    public double getValorAtual() { return valorAtual; }
    public double getLimiteAlerta() { return limiteAlerta; }
}
