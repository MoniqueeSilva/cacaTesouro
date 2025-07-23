// A classe controla os movimentos do jogador no jogo

public class Jogador {
    public int vertical = 0;
    public int horizontal = 0;
    public int pontos = 0;
    public boolean[][] visitado = new boolean[6][6]; // Passou ou não, valor padão: false

    public Jogador() {
        visitado[0][0] = true; // Primeira casa que o jogador está
    }

    public boolean mover(char direcao) {
        int novaVertical = vertical;         // Nova posição temporária
        int novaHorizontal = horizontal;     // Nova posição temporária

        if (direcao == 'W') novaVertical--;  // cima
        if (direcao == 'S') novaVertical++;  // baixo
        if (direcao == 'A') novaHorizontal--; // esquerda
        if (direcao == 'D') novaHorizontal++; // direita 

        if (novaVertical < 0 || novaVertical >= 6 || novaHorizontal < 0 || novaHorizontal >= 6) {
            return false; // Movimento inválido se a posição sair do tabuleiro
        }

        if (visitado[novaVertical][novaHorizontal]) {
            return false;// inválido se já foi visitada
        }

        vertical = novaVertical; // Atualiza posição 
        horizontal = novaHorizontal; // Atualiza posição
        visitado[vertical][horizontal] = true; // Visitada
        return true; // Movimento válido
    }

    public boolean foiVisitado(int v, int h) {
        return visitado[v][h]; // Casa que já foi visitada
    }
}
