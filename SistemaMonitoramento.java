import java.util.Scanner;

/**
 * Classe principal do sistema de monitoramento da estação espacial.
 * Contém o menu interativo e integra todos os componentes do sistema.
 *
 * Conceitos de POO demonstrados neste projeto:
 *  - Classe Abstrata  : ComponenteEspacial, SistemaPropulsao
 *  - Interface        : Sensor (implementada por 3 sensores)
 *  - Encapsulamento   : DadosMissao (atributos privados, getters/setters, senha)
 *  - Herança          : PropulsaoQuimica e PropulsaoEletrica herdam de SistemaPropulsao
 *                       SensorTemperatura, SensorPressao, SensorRadiacao herdam de ComponenteEspacial
 */
public class SistemaMonitoramento {

    // ---- Instâncias dos componentes da estação ----
    private static SensorTemperatura sensorTemp;
    private static SensorPressao     sensorPressao;
    private static SensorRadiacao    sensorRadiacao;

    private static PropulsaoQuimica  propQuimica;
    private static PropulsaoEletrica propEletrica;

    private static DadosMissao dadosMissao;

    private static Scanner scanner = new Scanner(System.in);

    // =============================================
    //                  MAIN
    // =============================================
    public static void main(String[] args) {
        inicializarSistema();
        loopPrincipal();
    }

    /** Inicializa todos os componentes da estação */
    private static void inicializarSistema() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║   ESTAÇÃO ESPACIAL NOVA AURORA - INICIALIZANDO  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // Criando sensores (implementam interface Sensor e herdam ComponenteEspacial)
        sensorTemp    = new SensorTemperatura("ST-01", "Sensor Temp. Principal");
        sensorPressao = new SensorPressao("SP-01", "Sensor Pressão Casco");
        sensorRadiacao= new SensorRadiacao("SR-01", "Sensor Radiação Ext.");

        // Configurando limites de alerta
        sensorTemp.setLimiteAlerta(65.0);
        sensorPressao.setLimiteAlerta(85.0);
        sensorRadiacao.setLimiteAlerta(1.8);

        // Criando sistemas de propulsão (herdam de SistemaPropulsao)
        propQuimica  = new PropulsaoQuimica("PQ-01", "Motor Químico Principal");
        propEletrica = new PropulsaoEletrica("PE-01", "Motor Iônico Secundário");

        // Criando dados da missão (encapsulamento)
        dadosMissao = new DadosMissao("Missão Nova Aurora", "AURORA2026", 6);
        dadosMissao.setCoordenadas("AURORA2026", -23.5505, -46.6333, 408.0);

        System.out.println("\n[OK] Todos os sistemas inicializados com sucesso!");
        System.out.println("     Tripulantes a bordo: 6");
        System.out.println("     Sensores online: 3");
        System.out.println("     Propulsores prontos: 2");
        pausar();
    }

    // =============================================
    //             LOOP PRINCIPAL (MENU)
    // =============================================
    private static void loopPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuSensores();
                case 2 -> menuPropulsao();
                case 3 -> menuDadosMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("\n🚀 Encerrando sistema. Boa viagem!\n");
                default -> System.out.println("[AVISO] Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║       SISTEMA DE MONITORAMENTO ESPACIAL          ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Verificar Sensores                           ║");
        System.out.println("║  2. Controlar Propulsão                          ║");
        System.out.println("║  3. Gerenciar Dados da Missão                    ║");
        System.out.println("║  4. Simular Alertas                              ║");
        System.out.println("║  5. Exibir Status Completo                       ║");
        System.out.println("║  0. Sair                                         ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    // =============================================
    //             MENU: SENSORES
    // =============================================
    private static void menuSensores() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE SENSORES ---");
            System.out.println("1. Ler todos os sensores");
            System.out.println("2. Verificar funcionamento dos sensores");
            System.out.println("3. Diagnóstico detalhado");
            System.out.println("4. Verificar alertas dos sensores");
            System.out.println("0. Voltar");

            opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1 -> lerTodosSensores();
                case 2 -> verificarFuncionamentoSensores();
                case 3 -> diagnosticoSensores();
                case 4 -> verificarAlertasSensores();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("[AVISO] Opção inválida.");
            }
        }
    }

    private static void lerTodosSensores() {
        System.out.println("\n=== LEITURA DE SENSORES ===");
        sensorTemp.lerValor();
        sensorPressao.lerValor();
        sensorRadiacao.lerValor();
    }

    private static void verificarFuncionamentoSensores() {
        System.out.println("\n=== VERIFICAÇÃO DE FUNCIONAMENTO ===");
        sensorTemp.verificarFuncionamento();
        sensorPressao.verificarFuncionamento();
        sensorRadiacao.verificarFuncionamento();
    }

    private static void diagnosticoSensores() {
        System.out.println("\n=== DIAGNÓSTICO COMPLETO DOS SENSORES ===");
        sensorTemp.realizarDiagnostico();
        sensorPressao.realizarDiagnostico();
        sensorRadiacao.realizarDiagnostico();
    }

    private static void verificarAlertasSensores() {
        System.out.println("\n=== VERIFICAÇÃO DE ALERTAS ===");
        lerTodosSensores();
        System.out.println();

        verificarAlertaSensor("Temperatura", sensorTemp.estaEmAlerta(), sensorTemp.getValorAtual(), sensorTemp.getLimiteAlerta());
        verificarAlertaSensor("Pressão",     sensorPressao.estaEmAlerta(), sensorPressao.getValorAtual(), sensorPressao.getLimiteAlerta());
        verificarAlertaSensor("Radiação",    sensorRadiacao.estaEmAlerta(), sensorRadiacao.getValorAtual(), sensorRadiacao.getLimiteAlerta());
    }

    private static void verificarAlertaSensor(String tipo, boolean emAlerta, double valor, double limite) {
        if (emAlerta) {
            emitirAlerta(tipo, valor, limite);
        } else {
            System.out.printf("[OK] Sensor %s: %.2f — dentro do limite (%.2f)%n", tipo, valor, limite);
        }
    }

    // =============================================
    //             MENU: PROPULSÃO
    // =============================================
    private static void menuPropulsao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE PROPULSÃO ---");
            System.out.println("1. Ligar motor químico");
            System.out.println("2. Desligar motor químico");
            System.out.println("3. Acelerar motor químico");
            System.out.println("4. Ligar motor iônico");
            System.out.println("5. Desligar motor iônico");
            System.out.println("6. Acelerar motor iônico");
            System.out.println("7. Status dos propulsores");
            System.out.println("0. Voltar");

            opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1 -> propQuimica.ligarMotor();
                case 2 -> propQuimica.desligarMotor();
                case 3 -> {
                    double pot = lerDouble("Potência (0-100%): ");
                    propQuimica.acelerar(pot);
                }
                case 4 -> propEletrica.ligarMotor();
                case 5 -> propEletrica.desligarMotor();
                case 6 -> {
                    double pot = lerDouble("Potência (0-100%): ");
                    propEletrica.acelerar(pot);
                }
                case 7 -> {
                    propQuimica.exibirStatus();
                    System.out.println();
                    propEletrica.exibirStatus();
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("[AVISO] Opção inválida.");
            }
        }
    }

    // =============================================
    //             MENU: DADOS DA MISSÃO
    // =============================================
    private static void menuDadosMissao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE DADOS DA MISSÃO ---");
            System.out.println("1. Exibir resumo da missão");
            System.out.println("2. Ver coordenadas (requer senha)");
            System.out.println("3. Atualizar nível de combustível");
            System.out.println("4. Atualizar trajetória");
            System.out.println("5. Verificar nível de combustível");
            System.out.println("0. Voltar");

            opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1 -> dadosMissao.exibirResumo();
                case 2 -> {
                    System.out.print("Digite a senha de acesso: ");
                    String senha = scanner.nextLine();
                    dadosMissao.exibirCoordenadas(senha);
                }
                case 3 -> {
                    double nivel = lerDouble("Novo nível de combustível (0-100%): ");
                    dadosMissao.setNivelCombustivel(nivel);
                }
                case 4 -> {
                    System.out.print("Nova trajetória: ");
                    String traj = scanner.nextLine();
                    dadosMissao.setTrajetoria(traj);
                }
                case 5 -> dadosMissao.verificarCombustivel();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("[AVISO] Opção inválida.");
            }
        }
    }

    // =============================================
    //             SIMULAR ALERTAS
    // =============================================
    private static void simularAlertas() {
        System.out.println("\n=== SIMULAÇÃO DE ALERTAS DO SISTEMA ===");
        System.out.println("Realizando verificação automática de todos os sistemas...\n");

        // Lê sensores e verifica alertas
        verificarAlertasSensores();

        // Verifica combustível
        dadosMissao.verificarCombustivel();

        // Simulação de situação crítica
        System.out.println("\n--- Simulando cenário de emergência ---");
        dadosMissao.setNivelCombustivel(4.0); // combustível crítico
        dadosMissao.setNivelCombustivel(100.0); // restabelece

        System.out.println("\n[OK] Simulação de alertas concluída.");
    }

    // =============================================
    //             STATUS COMPLETO
    // =============================================
    private static void exibirStatusCompleto() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║              STATUS COMPLETO DA ESTAÇÃO          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        dadosMissao.exibirResumo();

        System.out.println("\n--- SENSORES ---");
        sensorTemp.lerValor();
        sensorPressao.lerValor();
        sensorRadiacao.lerValor();
        sensorTemp.realizarDiagnostico();
        sensorPressao.realizarDiagnostico();
        sensorRadiacao.realizarDiagnostico();

        System.out.println("\n--- PROPULSÃO ---");
        propQuimica.exibirStatus();
        System.out.println();
        propEletrica.exibirStatus();

        System.out.println("\n[OK] Status completo exibido.");
    }

    // =============================================
    //             SISTEMA DE ALERTAS
    // =============================================
    /**
     * Emite alerta com nível automático baseado na gravidade.
     * Diferencia: ATENÇÃO, ALERTA, CRÍTICO.
     */
    private static void emitirAlerta(String sensor, double valor, double limite) {
        double desvio = Math.abs((valor - limite) / limite) * 100;
        String nivel;
        String icone;

        if (desvio <= 15) {
            nivel = "ATENÇÃO";
            icone = "⚠️ ";
        } else if (desvio <= 40) {
            nivel = "ALERTA";
            icone = "🔶";
        } else {
            nivel = "CRÍTICO";
            icone = "🚨";
        }

        System.out.println("╔══════════════════════════════════════╗");
        System.out.printf ("║ %s [%s] Sensor: %-18s║%n", icone, nivel, sensor);
        System.out.printf ("║   Valor atual : %-22.2f║%n", valor);
        System.out.printf ("║   Limite      : %-22.2f║%n", limite);
        System.out.println("╚══════════════════════════════════════╝");
    }

    // =============================================
    //             UTILITÁRIOS
    // =============================================
    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -99; // valor inválido propositalmente
        }
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Valor inválido. Usando 0.");
            return 0;
        }
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
