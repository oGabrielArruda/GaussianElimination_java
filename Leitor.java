import java.util.StringTokenizer;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Leitor
{
	protected BufferedReader arquivo;

	/**
	Construtor da classe leitor.
	V� se a string passada como par�metro � v�lida, sen�o for, lan�a excess�o
	Caso esteja v�lido, instancia um novo BufferedReader
	@throws Exception caso o nome do arquivo seja inv�lido
	*/
	public Leitor(String arq) throws Exception
	{
		if (arq == null || arq == "")
			throw new Exception("Diret�rio inv�lido");
		this.arquivo = new BufferedReader (new FileReader (arq));
	}

	/**
	V� se o arquivo terminou.
	@return true se a leitura chegou a final do arquivo, false sen�o chegou
	*/
	public boolean fimDoArquivo() throws Exception
	{
		return !this.arquivo.ready();
	}

	/**
	L� os valores do arquivo e os coloca em uma matriz.
	Primeiro, pega-se o primeiro valor que � a quantidade de equa��es do sistema
	Depois, inst�ncia-se uma nova matriz com a quantdade de linhas igual a quantidade de equa��es
	E a quantidade de colunas igual a quantidade de equa��es mais um
	Enfim, as linhas seguintes s�o lidas e s�o inseridas nas respectivas linhas e colunas na matriz
	@return a matriz com as equa��es
	*/
	 public double[][] getSistema() throws Exception // retorna uma matriz
     {
			int qtdEquacoes = Integer.parseInt(arquivo.readLine());

			double[][] ret = new double[qtdEquacoes][qtdEquacoes+1];

			for(int linha = 0; linha < qtdEquacoes; linha++)
			{
				String l = arquivo.readLine();
				if(l.equals(""))
					l = arquivo.readLine();

				StringTokenizer quebrador = new StringTokenizer(l);
				int coluna = 0;
				while(quebrador.hasMoreTokens())
				{
					ret[linha][coluna] = Double.parseDouble(quebrador.nextToken());
					coluna++;
				}
			}
			return ret;
     }

	/**
	Fecha o arquivo que est� sendo lido.
	@throws Exception caso o arquivo esteja inv�lido
	*/
     public void fecharArquivo() throws Exception
     {
		try
		{
			this.arquivo.close();
		}
		catch(Exception ex)
		{
			throw new Exception("Erro ao fechar o arquivo");
		}
	 }

	/**
	V� se a inst�ncia � igual a outra.
	@param obj inst�ncia a ser comparada
	*/
     public boolean equals(Object obj)
     {
		 if(this == obj)
		 	return true;
		 if(obj == null)
		 	return false;
		 if(this.getClass() != obj.getClass())
		 	return false;

		 Leitor leitor = (Leitor)obj;

		 if(!leitor.arquivo.equals(this.arquivo))
		 	return false;
		 return true;
	 }

	/**
	Calcula o c�digo hash da inst�ncia.
	@return valor do c�digo
	*/
	 public int hashCode()
	 {
		int ret = 345;
		ret = ret*7 + this.arquivo.hashCode();
		return ret;
	 }

	/**
	Retorna a inst�ncia em formato de string.
	*/
	 public String toString()
	 {
		return this.arquivo.lines().collect(Collectors.joining());
	 }
}