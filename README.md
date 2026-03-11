# 💊 DoseAlerta - Gestão Inteligente de Medicação

O DoseAlerta é um aplicativo Android nativo projetado para solucionar um problema crítico de saúde pública: a baixa adesão a tratamentos medicamentosos. Focado em idosos, pacientes crônicos e cuidadores, o app transforma o smartphone em um assistente de saúde rigoroso e intuitivo.


## 🎯 Objetivo do Projeto
Muitos pacientes enfrentam dificuldades em gerenciar múltiplos medicamentos, o que compromete a eficácia do tratamento. O DoseAlerta elimina o esquecimento e a insegurança através de:

* **Lembretes Automáticos:** Notificações personalizadas no horário exato.

* **Gestão de Cuidadores:** Monitoramento de múltiplos pacientes em uma única interface.

* **Histórico de Adesão:** Registro detalhado para consulta médica e controle pessoal.

## 📱 Fluxo do Aplicativo 

1. **Abertura & Login:** Splash screen com identidade visual e autenticação segura.

2. **Menu Principal:** Hub central para navegação entre agendamentos, histórico e configurações.

3. **Agendamento:** Cadastro inteligente onde o usuário define o intervalo (ex: 8h em 8h) e a duração do tratamento.

4. **Monitoramento:** Visualização diária das doses próximas e histórico do que já foi administrado.

## 🧩 Tecnologias Utilizadas

* **Java:** Responsável por toda a lógica de agendamento e regras de negócio.
* **XML:** Utilizado para criar uma interface moderna, limpa e acessível.
* **SQLite:** Banco de dados local para armazenar os horários e o histórico.
* **Android Studio:** Plataforma utilizada para a criação do aplicativo.


## 🗄️ Estrutura de Dados
O projeto utiliza o SQLite para persistência local. Abaixo, detalho a estrutura das tabelas conforme a modelagem do sistema. O sistema utiliza Foreign Keys (Chaves Estrangeiras) para ligar o histórico aos usuários e remédios, garantindo que os dados estejam sempre organizados e seguros.

| Tabela | Campos Principais | Função |
| :--- | :--- | :--- |
| **Usuarios** | `id`, `nome`, `email`, `cpf`, `senha` | Armazena dados de acesso e perfil do usuário. |
| **Agendamentos** | `nome`, `data`, `horario_inicial`, `intervalo_horas`, `duracao_dias` | Define a regra do tratamento (Ex: de 8 em 8 horas). |
| **Agendados** | `nome`, `data`, `horario` | Instâncias específicas de cada dose gerada pelo sistema. |
| **historico_remedio** | `usuario_id`, `remedio_id`, `data_geracao` | Registra a confirmação da ingestão para controle. |

## 📂 Como Executar o Projeto
### 1. Clone este repositório: 
git clone https://github.com/sfleticia/DoseAlerta

2. Abra a pasta do projeto no **Android Studio**.
3. Certifique-se de ter o SDK do Android instalado (API 24 ou superior recomendada).
4. Execute o app em um emulador ou dispositivo físico via USB.
