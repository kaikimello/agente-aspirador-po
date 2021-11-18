package util;

import ambiente.Sala;

public class PosicaoXY {
    private int posX;
    private int posY;

    public PosicaoXY(Sala sala) {
        this.posX = sala.getPosicaoInicialAgenteX();
        this.posY = sala.getPosicaoInicialAgenteY();
    }

    public PosicaoXY(int retornoPosX, int retornoPosY) {
        this.posX = retornoPosX;
        this.posY = retornoPosY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
