🚀 Plataforma de Monitoramento de Sistemas Espaciais
Projeto desenvolvido para a Global Solution 2026 da disciplina de Programação Orientada a Objetos (POO).
---
📋 Descrição
Sistema de monitoramento de uma estação espacial desenvolvido em Java, aplicando os principais conceitos de POO: Classe Abstrata, Interface, Encapsulamento e Herança.
O sistema permite monitorar sensores, controlar sistemas de propulsão e gerenciar dados sigilosos da missão, com alertas automáticos em tempo real.
---
🗂️ Estrutura do Projeto
```
projeto-espacial/
├── ComponenteEspacial.java   → Classe abstrata base
├── Sensor.java               → Interface dos sensores
├── DadosMissao.java          → Encapsulamento dos dados
├── SistemaPropulsao.java     → Classe abstrata de propulsão
├── PropulsaoQuimica.java     → Herda de SistemaPropulsao
├── PropulsaoEletrica.java    → Herda de SistemaPropulsao
├── SensorTemperatura.java    → Implementa Sensor
├── SensorPressao.java        → Implementa Sensor
├── SensorRadiacao.java       → Implementa Sensor
└── SistemaMonitoramento.java → Classe principal com menu
```
---
🧠 Conceitos de POO Aplicados
1. Classe Abstrata
`ComponenteEspacial` — define atributos e métodos comuns a todos os componentes (id, nome, status, temperatura). Contém o método abstrato `realizarDiagnostico()`.
`SistemaPropulsao` — base para os sistemas de propulsão, com o método abstrato `acelerar()`.
2. Interface
`Sensor` — contrato com os métodos `lerValor()`, `verificarFuncionamento()`, `retornarTipo()`, `setLimiteAlerta()` e `estaEmAlerta()`.
Implementada por: `SensorTemperatura`, `SensorPressao` e `SensorRadiacao`.
3. Encapsulamento
`DadosMissao` — todos os atributos são `private`. Getters e setters com validação. Coordenadas protegidas por senha. Alerta automático quando combustível < 20%.
4. Herança
`PropulsaoQuimica` e `PropulsaoEletrica` herdam de `SistemaPropulsao`, usam `super()` e sobrescrevem `acelerar()` com comportamentos distintos.
---
⚙️ Funcionalidades
✅ Leitura simulada de sensores (temperatura, pressão, radiação)
✅ Verificação de funcionamento dos sensores
✅ Limites de alerta configuráveis por sensor
✅ Controle de propulsão química e elétrica (ligar, desligar, acelerar)
✅ Dados da missão protegidos por senha
✅ Alerta automático de combustível baixo
✅ Sistema de alertas com 3 níveis: ATENÇÃO, ALERTA e CRÍTICO
✅ Menu interativo no terminal
---
▶️ Como Executar
Pré-requisitos
Java JDK 17 ou superior instalado
Download JDK
Compilar e rodar
```bash
javac *.java
java SistemaMonitoramento
```
Ou pelo VS Code
Abra o arquivo `SistemaMonitoramento.java` e clique no botão ▷ Run no canto superior direito.
---
🔐 Senha de Acesso
Para visualizar as coordenadas restritas da missão, utilize a senha:
```
AURORA2026
```
---
👨‍💻 Tecnologias
Java 21
VS Code + Extension Pack for Java
