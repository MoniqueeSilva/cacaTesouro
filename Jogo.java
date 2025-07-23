import java.util.Random; // Gerar aleatóriamente
import java.util.Scanner;

public class Jogo { //obs: ElementoTabuleiro é uma superclasse
    ElementoTabuleiro[][] tabuleiro = new ElementoTabuleiro[6][6]; // Representa o tabuleiro
    Jogador jogador = new Jogador(); // Instância do jogador, que inicia em (0,0)
    int movimentos = 0;
    int tesourosAchados = 0;

    // Método que configura o jogo antes de começar
    public void iniciar() {
        Random random = new Random(); // Números(posições) aleatórios

        // Preenchendo tabuleiro com Vazio
        for (int v = 0; v < 6; v++) {
            for (int h = 0; h < 6; h++) {
                tabuleiro[v][h] = new Vazio();
            }
        }

        // Colocar o tesouro na posição
        int tesouros = 0;
        while (tesouros < 3) {
            int v = random.nextInt(6);// Linha aleatória
            int h = random.nextInt(6);// Coluna aleatória

            if (!(v == 0 && h == 0) && tabuleiro[v][h].interagir() == 0) { // = vazio
                tabuleiro[v][h] = new Tesouro();
                tesouros++;
            }
        }

        // Colocar as armadilhas na posição
        int armadilhas = 0;
        while (armadilhas < 3) {
            int v = random.nextInt(6); // Linha aleatória
            int h = random.nextInt(6); // Coluna aleatória

            if (!(v == 0 && h == 0) && tabuleiro[v][h].interagir() == 0) {
                tabuleiro[v][h] = new Armadilha();
                armadilhas++;
            }
        }

        jogar(); // Começa o jogo
    }

    // Método que controla o jogo até finalizar
    public void jogar() {
        Scanner entrada = new Scanner(System.in);
        while (movimentos < 10 && tesourosAchados < 3) {
            mostrarTabuleiro();
            System.out.print("Digite W (cima), A (esquerda), S (baixo), D (direita): ");
            char direcao = entrada.next().toUpperCase().charAt(0);
            boolean moveu = jogador.mover(direcao);
            if (!moveu) { //negação(não moveu)
                System.out.println("Movimento inválido. Tente novamente.");
                continue;
            }

            // Posição atual
            int v = jogador.vertical;
            int h = jogador.horizontal;

            ElementoTabuleiro elemento = tabuleiro[v][h]; // Pega o elemento atual *
            int pontos = elemento.interagir(); // Calcula pontos
            jogador.pontos += pontos; // Atualiza pontuação

            if (elemento instanceof Tesouro) {
                tesourosAchados++;                                 
            }
            movimentos++;
        }

        mostrarTabuleiro(); 
        System.out.println("Fim do jogo! Pontos: " + jogador.pontos);

        entrada.close();
    }
    

    public void mostrarTabuleiro() {
        System.out.println("\nTabuleiro:");
        for (int v = 0; v < 6; v++) {
            for (int h = 0; h < 6; h++) {
                if (jogador.foiVisitado(v, h)) { // Se já foi visitada *
                    System.out.print(tabuleiro[v][h].simbolo() + " "); 
                } else {
                    System.out.print("⬜ ");
                }
            }
            System.out.println();
        }
        System.out.println("Pontos: " + jogador.pontos); // Pontos atuais
    }
}
