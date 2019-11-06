import java.util.StringTokenizer;
import java.io.*;
import java.util.List;

public class Programa
{
    public static void main (String[] args)
    {
		int cont = 0;
		try
		{
			System.out.println("Digite o nome do seu arquivo!");
			String arq = Teclado.getUmString();

			Leitor leitor = new Leitor(arq);

			while(!leitor.fimDoArquivo())
			{
				Matriz mat = new Matriz(leitor.getSistema());
				Matriz sistema = new Matriz(mat); // clona-se a matriz para n�o estragar os valores da equa��o

				Resolvedor resolvedor = new Resolvedor(sistema);
				if(!resolvedor.isSolucionavel())
				{
					System.out.println(cont+1 + "a Equa��o: sem solu��o");
					cont++;
					continue;
				}
				if (resolvedor.temZeroDiag())
				{
					try
					{
						resolvedor.tirarZeroDiag();
					}
					catch(Exception erro)
					{
						System.out.println(cont+1 + "a Equa��o: sem solu��o");
						cont++;
						continue;
					}
				}

				resolvedor.resolver();
				System.out.println(cont+1 + "a Equa��o: \n " + resolvedor.resultado() + "\n");

				cont++;
			}
			leitor.fecharArquivo();
		}
		catch(Exception ex)
		{
			System.out.println("Erro: " + ex.getMessage());
		}
    }
}