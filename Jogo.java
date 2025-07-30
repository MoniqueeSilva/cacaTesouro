import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private ElementoTabuleiro[][] tabuleiro;
    private Jogador jogador;
    private int movimentos;
    private int tesourosAchados;

    public Jogo() {
        tabuleiro = new ElementoTabuleiro[6][6];
        jogador = new Jogador();
        movimentos = 0;
        tesourosAchados = 0;
        iniciar(); //Preenche o tabuleiro com vazio, tesouro e armadilha
        jogar(); //Inicia o jogo
    }

    //Configurar antes do jogo começar
    private void iniciar() {
        Random random = new Random();

        // Preenche o tabuleiro com vazios
        for (int v = 0; v < 6; v++) {
            for (int h = 0; h < 6; h++) {
                tabuleiro[v][h] = new Vazio();
            }
        }

        // Adiciona 3 tesouros
        int tesouros = 0;
        while (tesouros < 3) {
            int v = random.nextInt(6);
            int h = random.nextInt(6);

            if (tabuleiro[v][h].podeReceberElemento() && (v != 0 || h != 0)) { //Evitar o stanceof
                tabuleiro[v][h] = new Tesouro(); // Substitui o Vazio
                tesouros++;
            }
        }

        // Adiciona 3 armadilhas
        int armadilhas = 0;
        while (armadilhas < 3) {
            int v = random.nextInt(6);
            int h = random.nextInt(6);

            if (tabuleiro[v][h].podeReceberElemento() && (v != 0 || h != 0)) {
                tabuleiro[v][h] = new Armadilha();
                armadilhas++;
            }
        }
    }

    //Método com a lógica do jogo
    private void jogar() {
        Scanner entrada = new Scanner(System.in);

        while (movimentos < 10 && tesourosAchados < 3) {
            mostrarTabuleiro();
            System.out.print("Digite W (cima), A (esquerda), S (baixo), D (direita): ");
            char direcao = entrada.next().toUpperCase().charAt(0);
            boolean moveu = jogador.mover(direcao);

            if (!moveu) {
                System.out.println("Movimento inválido. Tente novamente.");
                continue;
            }

            int v = jogador.getVertical();
            int h = jogador.getHorizontal();

            if (!jogador.jaVisitado(v, h)) {
                int pontos = tabuleiro[v][h].interagir(); //executa o método correspondente
                jogador.addPontos(pontos);
                if (pontos > 0) {
                    tesourosAchados++;
                }
                jogador.registrarVisita(v, h);
            }

            movimentos++;
        }

        //Estado final do jogo
        mostrarTabuleiro();
        System.out.println("Fim do jogo! Pontuação final: " + jogador.getPontos());
        entrada.close();
    }

    private void mostrarTabuleiro() {
        System.out.println("\nTabuleiro:");
        for (int v = 0; v < 6; v++) {
            for (int h = 0; h < 6; h++) {
                if (v == jogador.getVertical() && h == jogador.getHorizontal()) {
                    System.out.print("💜 ");
                } else if (jogador.jaVisitado(v, h)) {
                    System.out.print(tabuleiro[v][h].simbolo() + " "); //
                } else {
                    System.out.print("⬜ ");
                }
            }
            System.out.println();
        }
        System.out.println("Pontos: " + jogador.getPontos());
        System.out.println("Movimentos restantes: " + (10 - movimentos));
    }
}
