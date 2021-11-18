package agente;

import ambiente.Sala;
import util.PosicaoXY;

public class AspiradorPo {
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";

    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_BACKGROUND_CYAN = "\u001b[46m";


    private Sala sala;
    private MovimentosAspiradorPo movimento;
    private PosicaoXY posicaoXY;
    private int pilhaMovimentos;

    /**
     * O aspirador pode se virar para esquerda ou direita
     * Andar para frente, verifica a sujeira e aspira
     */

    public AspiradorPo(Sala sala){
        this.sala = sala;
        this.sala.setAgente(this);
        this.posicaoXY = new PosicaoXY(sala);
        this.movimento = MovimentosAspiradorPo.DIREITA;
    }



    public PosicaoXY getPosicaoXY() {
        return posicaoXY;
    }

    public void movimentar(){
        if(!(this.pilhaMovimentos >= sala.getTamanho())){
            PosicaoXY proximoMovimento = realizaMovimento();
            String valor = this.sala.getValorPosicaoSala(proximoMovimento);

            if (valor.equals(TEXT_GREEN+" 🚩 "+TEXT_RESET) || valor.equals(TEXT_BACKGROUND_CYAN+TEXT_BLACK+" 🤖 "+TEXT_RESET)){
                proximoMovimento();
                aumentarPilha();
                movimentar();
            }else{
                this.sala.limpar();
                this.posicaoXY = proximoMovimento;
            }
        }
    }

    public boolean isLimpezaConcluida() {
        return this.pilhaMovimentos < this.sala.getTamanho();
        //TODO o problema pode ser o -1 que falta em this.sala.getTamanho()
    }

    private void aumentarPilha() {
        this.pilhaMovimentos++;
    }

    private void proximoMovimento() {
        switch (this.movimento) {
            case FRENTE:
                this.movimento = MovimentosAspiradorPo.ESQUERDA;
                break;
            case ESQUERDA:
                this.movimento = MovimentosAspiradorPo.DIREITA;
                break;
            case DIREITA:
                this.movimento = MovimentosAspiradorPo.FRENTE;
                break;
        }
    }

    public PosicaoXY realizaMovimento(){
        int retornoPosX = this.posicaoXY.getPosX();
        int retornoPosY = this.posicaoXY.getPosY();

        switch (movimento){
            case FRENTE:
                if (retornoPosX < this.sala.getTamanho() - 1){
                    retornoPosX += 1;
                }
                break;
            case ESQUERDA:
                if (retornoPosY > 0){
                    retornoPosY -= 1;
                }
                break;
            case DIREITA:
                if (retornoPosY < this.sala.getTamanho() - 1){
                    retornoPosY += 1;
                }
                break;
        }
        return new PosicaoXY(retornoPosX, retornoPosY);
    }

    public void zerarPilha() {
        this.pilhaMovimentos = 0;
    }
}
