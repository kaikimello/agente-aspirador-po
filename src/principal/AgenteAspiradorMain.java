package principal;

import agente.AspiradorPo;
import ambiente.Sala;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AgenteAspiradorMain {

    public static void main(String[] args) throws InterruptedException {

        int tamanhoMundo = InformarValor();
        System.out.println("O tamanho do mundo será: X [" + tamanhoMundo + "]: Y [" + tamanhoMundo + "]\n");
        Sala sala = new Sala(tamanhoMundo);
        sala.mostrarSala();

        AspiradorPo aspiradorPo = new AspiradorPo(sala);

        while (aspiradorPo.isLimpezaConcluida()){
            aspiradorPo.zerarPilha();
            aspiradorPo.movimentar();
            sala.mostrarSala();
            Thread.sleep(1500);
        }
    }

    /**
     * Método que solicita o valor do tamanho do mundo e retorna o mesmo,
     * caso não seja informado valor inteiro o programa solicita até
     * o usuário informar um valor inteiro.
     *
     * @return tamanho do mundo/sala
     */
    private static int InformarValor(){
        Scanner input = new Scanner(System.in);
        int valor = 0;
        try {
            do {
                System.out.println("Informe o tamanho do mundo");
                valor = input.nextInt();
            }while (valor <= 0);
        } catch (InputMismatchException e){
            System.out.println("Informe um valor inteiro");
            InformarValor();
        }
        return valor;
    }
}
