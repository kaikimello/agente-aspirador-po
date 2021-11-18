package ambiente;

import agente.AspiradorPo;
import util.PosicaoXY;

import java.util.Random;

public class Sala {

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";

    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_BACKGROUND_CYAN = "\u001b[46m";

    private int tamanho;
    private String[][] sala;
    private int posicaoInicialAgenteX;
    private int posicaoInicialAgenteY;
    private AspiradorPo aspiradorPo;

    public Sala(int tamanho) {
        this.tamanho = tamanho;
        setSala(new String[tamanho][tamanho]);
        this.preencherSala();
        this.aspiradorPo = new AspiradorPo(this);
    }

    public int getTamanho() {
        return tamanho;
    }

    private void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String[][] getSala() {
        return sala;
    }

    private void setSala(String[][] sala) {
        this.sala = sala;
    }

    public int getPosicaoInicialAgenteX() {
        return posicaoInicialAgenteX;
    }

    private void setPosicaoInicialAgenteX(int posicaoInicialAgenteX) {
        this.posicaoInicialAgenteX = posicaoInicialAgenteX;
    }

    public int getPosicaoInicialAgenteY() {
        return posicaoInicialAgenteY;
    }

    private void setPosicaoInicialAgenteY(int posicaoInicialAgenteY) {
        this.posicaoInicialAgenteY = posicaoInicialAgenteY;
    }

    /**
     * Método sem retorno que executa o preenchimento da sala
     * Valores contidos na sala:
     * 💩 -> Sujo, 🚩 -> Limpo
     * Retorna valor entre 0 e 1 onde
     * 0 -> 💩, 1 -> 🚩
     */
    private void preencherSala(){
        inserirAgente();
        for (int i = 0; i < this.getTamanho(); i++){
            for (int j = 0; j < this.getTamanho(); j++){
                int valorObjetoSala = valorAleatorio(2);

                if (this.sala[i][j] == null){
                    if (valorObjetoSala == 1){
                        this.sala[i][j] = (TEXT_GREEN+" 🚩 "+TEXT_RESET);
                    }else{
                        this.sala[i][j] = (TEXT_RED+" 💩 "+TEXT_RESET);
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

        this.posicaoInicialAgenteX = valorAleatorio(this.getTamanho());
        this.posicaoInicialAgenteY = valorAleatorio(this.getTamanho());

        this.sala[posicaoInicialAgenteX][posicaoInicialAgenteY] = (TEXT_BACKGROUND_CYAN+TEXT_BLACK+" 🤖 "+TEXT_RESET);
    }

    /**
     * Método sem retorno que mostrar como estão os
     * comodos da sala
     */
    public void mostrarSala(){
        atualizarPosicaoAgente();
        for (int i = 0; i < this.getTamanho(); i++){
            for (int j = 0; j < this.getTamanho(); j++){
                System.out.print("|"+ this.sala[i][j] + "|");
            }
            System.out.println();
        }
    }

    private void atualizarPosicaoAgente() {
        PosicaoXY posicaoAspirador = this.aspiradorPo.getPosicaoXY();

        this.sala[posicaoAspirador.getPosX()][posicaoAspirador.getPosY()] = (TEXT_BACKGROUND_CYAN+TEXT_BLACK+" 🤖 "+TEXT_RESET);
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

    public String getValorPosicaoSala(PosicaoXY posicao) {
        return this.sala[posicao.getPosX()][posicao.getPosY()];
    }

    public void setAgente(AspiradorPo aspiradorPo) {
        this.aspiradorPo = aspiradorPo;
    }

    public void limpar() {
        PosicaoXY posicaoXY = this.aspiradorPo.getPosicaoXY();
        this.sala[posicaoXY.getPosX()][posicaoXY.getPosY()] = (TEXT_GREEN+" 🚩 "+TEXT_RESET);
    }
}
