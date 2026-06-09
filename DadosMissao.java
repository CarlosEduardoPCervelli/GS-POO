/**
 * Classe que armazena os dados sensíveis da missão espacial.
 * Demonstra ENCAPSULAMENTO: todos os atributos são privados,
 * acesso controlado por getters/setters com validação.
 * Dados restritos protegidos por senha.
 */
public class DadosMissao {

    // ---- Atributos privados (encapsulamento) ----
    private String nomeMissao;
    private String codigoAcesso;          // senha para dados restritos
    private double coordenadaX;           // dado restrito
    private double coordenadaY;           // dado restrito
    private double coordenadaZ;           // dado restrito
    private double nivelCombustivel;      // 0 a 100 (%)
    private String trajetoria;
    private int numeroDeTripulantes;
    private String statusMissao;

    // Constante: limite de alerta de combustível
    private static final double LIMITE_COMBUSTIVEL_ALERTA = 20.0;

    // Construtor
    public DadosMissao(String nomeMissao, String codigoAcesso, int tripulantes) {
        this.nomeMissao = nomeMissao;
        this.codigoAcesso = codigoAcesso;
        this.numeroDeTripulantes = tripulantes;
        this.nivelCombustivel = 100.0;
        this.trajetoria = "Órbita terrestre baixa";
        this.statusMissao = "EM ANDAMENTO";
        this.coordenadaX = 0.0;
        this.coordenadaY = 0.0;
        this.coordenadaZ = 0.0;
    }

    // ---- Getters e Setters com validação ----

    public String getNomeMissao() {
        return nomeMissao;
    }

    public void setNomeMissao(String nomeMissao) {
        if (nomeMissao == null || nomeMissao.trim().isEmpty()) {
            System.out.println("[ERRO] Nome da missão não pode ser vazio!");
            return;
        }
        this.nomeMissao = nomeMissao;
        System.out.println("[OK] Nome da missão atualizado: " + nomeMissao);
    }

    public double getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(double nivel) {
        if (nivel < 0) {
            System.out.println("[ERRO] Nível de combustível não pode ser negativo!");
            return;
        }
        if (nivel > 100) {
            System.out.println("[ERRO] Nível de combustível não pode exceder 100%!");
            return;
        }
        this.nivelCombustivel = nivel;
        System.out.printf("[OK] Nível de combustível atualizado: %.1f%%%n", nivel);

        // Alerta automático se combustível < 20%
        verificarCombustivel();
    }

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria == null || trajetoria.trim().isEmpty()) {
            System.out.println("[ERRO] Trajetória não pode ser vazia!");
            return;
        }
        this.trajetoria = trajetoria;
        System.out.println("[OK] Trajetória atualizada: " + trajetoria);
    }

    public int getNumeroDeTripulantes() {
        return numeroDeTripulantes;
    }

    public void setNumeroDeTripulantes(int numero) {
        if (numero < 0) {
            System.out.println("[ERRO] Número de tripulantes não pode ser negativo!");
            return;
        }
        if (numero > 20) {
            System.out.println("[ERRO] Capacidade máxima da estação: 20 tripulantes!");
            return;
        }
        this.numeroDeTripulantes = numero;
        System.out.println("[OK] Número de tripulantes: " + numero);
    }

    public String getStatusMissao() {
        return statusMissao;
    }

    public void setStatusMissao(String status) {
        this.statusMissao = status;
        System.out.println("[OK] Status da missão: " + status);
    }

    // ---- Acesso a dados restritos (protegidos por senha) ----

    /**
     * Retorna as coordenadas apenas com a senha correta.
     * @param senha código de acesso informado
     */
    public void exibirCoordenadas(String senha) {
        if (!senha.equals(codigoAcesso)) {
            System.out.println("[ACESSO NEGADO] Código de acesso incorreto!");
            return;
        }
        System.out.println("[ACESSO LIBERADO] Coordenadas da missão:");
        System.out.printf("  X: %.4f%n", coordenadaX);
        System.out.printf("  Y: %.4f%n", coordenadaY);
        System.out.printf("  Z: %.4f%n", coordenadaZ);
    }

    /**
     * Atualiza as coordenadas apenas com a senha correta.
     */
    public void setCoordenadas(String senha, double x, double y, double z) {
        if (!senha.equals(codigoAcesso)) {
            System.out.println("[ACESSO NEGADO] Não foi possível atualizar as coordenadas.");
            return;
        }
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.coordenadaZ = z;
        System.out.println("[OK] Coordenadas atualizadas com sucesso.");
    }

    /**
     * Verifica automaticamente o nível de combustível e emite alerta se necessário.
     */
    public void verificarCombustivel() {
        if (nivelCombustivel <= 5.0) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║  🚨 [CRÍTICO] COMBUSTÍVEL CRÍTICO!  ║");
            System.out.printf ("║  Nível: %.1f%% — RETORNO IMEDIATO!   ║%n", nivelCombustivel);
            System.out.println("╚══════════════════════════════════════╝");
        } else if (nivelCombustivel <= LIMITE_COMBUSTIVEL_ALERTA) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║  ⚠️  [ALERTA] COMBUSTÍVEL BAIXO!    ║");
            System.out.printf ("║  Nível: %.1f%% — Planeje o retorno!  ║%n", nivelCombustivel);
            System.out.println("╚══════════════════════════════════════╝");
        }
    }

    /** Exibe o resumo geral (sem dados restritos) */
    public void exibirResumo() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║           DADOS DA MISSÃO                ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ Missão      : " + nomeMissao);
        System.out.printf ("║ Combustível : %.1f%%%n", nivelCombustivel);
        System.out.println("║ Trajetória  : " + trajetoria);
        System.out.println("║ Tripulantes : " + numeroDeTripulantes);
        System.out.println("║ Status      : " + statusMissao);
        System.out.println("║ Coordenadas : [RESTRITAS - requer senha]");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}
