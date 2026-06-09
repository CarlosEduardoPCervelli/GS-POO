/**
 * Interface que define o "contrato" para todos os sensores da estação espacial.
 * Qualquer classe que implemente Sensor DEVE ter estes métodos.
 */
public interface Sensor {

    /**
     * Realiza a leitura do valor atual do sensor.
     * @return valor lido (simulado aleatoriamente)
     */
    double lerValor();

    /**
     * Verifica se o sensor está funcionando corretamente.
     * @return true se estiver operacional
     */
    boolean verificarFuncionamento();

    /**
     * Retorna o tipo do sensor (ex: "Temperatura", "Pressão").
     * @return String com o tipo
     */
    String retornarTipo();

    /**
     * Define o limite máximo de alerta para este sensor.
     * @param limite valor limite
     */
    void setLimiteAlerta(double limite);

    /**
     * Verifica se o valor atual passou do limite de alerta.
     * @return true se estiver em alerta
     */
    boolean estaEmAlerta();
}
