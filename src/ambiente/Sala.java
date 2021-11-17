package ambiente;

import java.util.Random;

public class Sala {

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";

    private int tamanho;
    private String[][] medidas;

    public Sala(int tamanho) {
        this.tamanho = tamanho;
        setMedidas(new String[tamanho][tamanho]);
    }

    public int getTamanho() {
        return tamanho;
    }

    private void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String[][] getMedidas() {
        return medidas;
    }

    private void setMedidas(String[][] medidas) {
        this.medidas = medidas;
    }

    /**
     * Método sem retorno que executa o preenchimento da sala
     * Valores contidos na sala:
     * 💩 -> Sujo, 🚩 -> Limpo
     * Retorna valor entre 0 e 1 onde
     * 0 -> 💩, 1 -> 🚩
     */
    public void preencherSala(){
        inserirAgente();
        for (int i = 0; i < this.getTamanho(); i++){
            for (int j = 0; j < this.getTamanho(); j++){
                int valorObjetoSala = valorAleatorio(2);

                if (this.medidas[i][j] == null){
                    if (valorObjetoSala == 1){
                        this.medidas[i][j] = (TEXT_GREEN+" 🚩 "+TEXT_RESET);
                    }else{
                        this.medidas[i][j] = (TEXT_RED+" 💩 "+TEXT_RESET);
                    }
                }
            }
        }

    }

    /**
     * Método que recupera duas posições aleatórias e
     * insere o agente na posição
     * Valor do agente na sala:
     * 👾 -> Agente
     * */
    private void inserirAgente() {

        int posicaoIAgente = valorAleatorio(this.getTamanho());
        int posicaoJAgente = valorAleatorio(this.getTamanho());

        this.medidas[posicaoIAgente][posicaoJAgente] = (" 👾 ");
    }

    /**
     * Método sem retorno que mostrar como estão os
     * comodos da sala
     */
    public void mostrarSala(){
        for (int i = 0; i < this.getTamanho(); i++){
            for (int j = 0; j < this.getTamanho(); j++){
                System.out.print("|"+ this.medidas[i][j] + "|");
            }
            System.out.println();
        }
    }


    /**
     * Método que retorna um valor inteiro que será equivalente
     * a um emoji ou posicao
     * @param faixaValor - faixa de valor para executar o método Random
     * @return ValorEmoji ou PosicaoEmoji
     */
    private int valorAleatorio(int faixaValor){
        Random gerador = new Random();

        return gerador.nextInt(faixaValor);
    }
}
