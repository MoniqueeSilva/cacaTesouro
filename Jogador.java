import java.util.HashSet;
import java.util.Set;

public class Jogador {
    private int vertical;
    private int horizontal;
    private int pontos;
    private Set<String> visitados; //Registrar as posições visitadas

    public Jogador() {
        this.vertical = 0;
        this.horizontal = 0;
        this.pontos = 0;
        this.visitados = new HashSet<>();
        registrarVisita(0, 0);
    }

    //Get: acessar
    public int getVertical() {
        return vertical;
    }
    public int getHorizontal() {
        return horizontal;
    }
    public int getPontos() {
        return pontos;
    }

    public boolean jaVisitado(int v, int h) { //Já passou
        return visitados.contains(v + "," + h);
    }
    public void registrarVisita(int v, int h) { //Visitada
        visitados.add(v + "," + h);
    }

    public void addPontos(int p) {
        pontos += p;
    }

    public boolean mover(char direcao) {
    int novoV = vertical;
    int novoH = horizontal;

    switch (direcao) {
        case 'W': novoV--; //Cima
            break;
        case 'S': novoV++; //Baixo
            break;
        case 'A': novoH--; //Esquerda
            break;
        case 'D': novoH++; //Direita
            break;
        default:
            System.out.println("Direção inválida!");
            return false;
    }

    if (novoV < 0 || novoV >= 6 || novoH < 0 || novoH >= 6) {
        System.out.println("Movimento fora do tabuleiro!");
        return false;
    }

    //Atualiza posição
    vertical = novoV;
    horizontal = novoH;
    return true;
    }

}
