public class Vazio extends ElementoTabuleiro {
    public int interagir(){ 
        return 0; 
    }
    public String simbolo(){
        return "💜"; 
    }
    public boolean podeReceberElemento() {
        return true;
    }
}
