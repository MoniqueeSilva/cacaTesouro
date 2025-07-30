//Interface
//É apartir daqui que ocorre o polimorfismo

public abstract class ElementoTabuleiro {
    public abstract int interagir();
    public abstract String simbolo();
    public boolean podeReceberElemento() {
        return false;
    }
}

//Obriga todas as classes filhas a implementar esse método e é usado para definir quantos pontos o jogador ganha ou perde
