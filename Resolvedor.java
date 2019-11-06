import java.util.StringTokenizer;
import java.io.*;

public class Resolvedor
{
	protected Matriz sistema;
	protected int qtdEquacoes;

	/**
	Construtor da classe Resolvedor.
	Passa-se um objeto da classe Matriz, contendo o sistema de equac�es
	Em seguida seta os atributos sistema e qtdEquacoes
	@param sistema inst�ncia da classe Matriz
	@throws Exception caso � objeto passado seja nulo
	*/
	public Resolvedor(Matriz sistema)
	{
		this.sistema = sistema;
		this.qtdEquacoes = sistema.getLinhas();
	}

	/**
	Verifica se o sistema tem solu��o.
	Percorre todas as as equa��es do sistema, e as dividem pelas outras equa��es
	Caso as divis�es tenham todo o mesmo valor, retorna-se false
	Sen�o, retorna-se true
	@return true se as divis�es tiverem pelo menos 1 valor diferente, false se tiverem todos valores iguais
	@throws Exception caso ocorra algum problema ao resgatar os valores
	*/
    public boolean isSolucionavel() throws Exception // divis�es
    {
		for(int linha = 0; linha < this.qtdEquacoes-1; linha++)
		{
			for(int outraLinha = linha+1; outraLinha < this.qtdEquacoes; outraLinha++)
			{
				double[] jaFoi = new double[this.qtdEquacoes];
				for(int coluna = 0; coluna < this.qtdEquacoes; coluna++)
				{
					try
					{
						double valor = this.sistema.getValor(linha, coluna) / this.sistema.getValor(outraLinha,coluna);
						jaFoi[coluna] = valor;
					}
					catch(Exception ex)
					{
						throw new Exception("Problema ao verificar se � solucion�vel");
					}
				}

				double primeiroValor = jaFoi[0];
				int qtsVezes = 1;
				for(int i = 1; i < jaFoi.length; i++) // verifica se as divis�es tiveram
				{									  // o mesmo resultado
					if(primeiroValor == jaFoi[i])
						qtsVezes++;
				}
				if(qtsVezes == jaFoi.length)
					return false;
			}
		}
		return true;
	}


	/**

	*/
	public void tirarZeroDiag() throws Exception
	{
		int vezes = 0;
		try
		{
			while(temZeroDiag() && vezes != this.qtdEquacoes)
			{
				double[] primeiraLinha = this.sistema.getLinha(0);

				for(int linha = 0; linha < this.qtdEquacoes-1; linha++)
				{
					double[] deBaixo = this.sistema.getLinha(linha+1);
					for(int coluna = 0; coluna < this.qtdEquacoes + 1; coluna++)
					{
						this.sistema.setValor(linha, coluna, deBaixo[coluna]);
					}
				}
				int ultimaLinha = this.qtdEquacoes - 1;
				for(int coluna = 0; coluna < this.qtdEquacoes+1; coluna++)
				this.sistema.setValor(ultimaLinha, coluna, primeiraLinha[coluna]);
				vezes++;
				if (vezes == this.qtdEquacoes)
					throw new Exception("Sistema inv�lido");
			}

		}
		catch(Exception erro)
		{
			throw new Exception("Troca inv�lida");
		}
	}

	/**
	Verifica se h� zeros na diagonal principal da matriz.
	@return true caso exista 0 na diagonal principal, false sen�o existir
	*/
	public boolean temZeroDiag()
	{
		try
		{
			for(int linha = 0; linha < this.qtdEquacoes; linha++)
				if(this.sistema.getValor(linha, linha) == 0)
					return true;

		}
		catch(Exception erro)
		{ }
		return false;

	}

	/**
	Seta os valores da coluna como 0, menos os pertencentes a diagonal principal.
	Primeiro, se o valor na diagonal principal for diferente de 1, transforma-se ele em 1, dividindo a linha pel valor a ser transformado
	Ap�s ter certeza de que o valor da diagonal principal � 1, setamos 0 nas demais linhas
	Fazemos isso pegando o valor da linha, e somamos com a linha que implementamos 1 (diagonal principal) multiplicada pelo inverso do valor que ser� transformado
	Ao fazer isso com todas as linhas e com todas as colunas, consequentemente o m�todo encontrar� o resultado
	*/
	public void setZerosColuna(int col) throws Exception
	{
		double x = this.sistema.getValor(col,col);

		// verifica se o valor da diagonal principal � 1
		if(x != 1.0)
		{
			int linha = col;
			for(int coluna = 0; coluna < this.qtdEquacoes + 1; coluna++)
				this.sistema.setValor(linha,coluna, this.sistema.getValor(linha,coluna) / x);
		}

		// deixa toda a coluna, com excecao do valor pertecente a diagonal, com 0
		for(int linha = 0; linha < this.qtdEquacoes; linha++)
		{
			double valor = this.sistema.getValor(linha,col);
			if(valor != 0 && linha != col)
			{
				for(int coluna = 0; coluna < qtdEquacoes+1; coluna++)
				{
				   double val = this.sistema.getValor(linha,coluna) + (this.sistema.getValor(col,coluna) * -valor);
				   this.sistema.setValor(linha,coluna, val);
				}
			}
		}
	}

	/**
	Chama o m�todo que deixa as colunas com o valor 0.
	� feito um for para percorrer todas equa��es do sistema.
	Para cada equa��o, chama-se o m�todo que seta os valores da coluna 'i' com 0.
	Consequentemente, a equa��o ser� resolvida.
	*/
	public void resolver() throws Exception
	{
			for(int i = 0; i < this.qtdEquacoes; i++)
				this.setZerosColuna(i);
	}

	/**
	String com os resultados do sistema.
	@return a string com os resultados.
	*/
	public String resultado() throws Exception
	{
		String ret = "";
		try
		{
			for(int i = 0; i < this.qtdEquacoes; i++)
			{
				ret += i+1 + "a incognita: " + this.sistema.getValor(i,this.qtdEquacoes) + "\n";
			}
		}
		catch(Exception e)
		{} // sei que n�o vai dar erro
		return ret;
	}

	/**
	Calcula e devolve o c�digo hash da inst�ncia.
	@return o c�digo hash.
	*/
	public int hashCode()
	{
		int ret = 356;
		ret = ret * 7 + this.sistema.hashCode();
		ret = ret * 7 + new Integer(this.qtdEquacoes).hashCode();

		if(ret < 0)
			ret = -ret;
		return ret;
	}

	/**
	Transforma e retorna a inst�ncia da classe em formato de String
	@return string com os valores da inst�ncia
	*/
	public String toString()
	{
		String ret = sistema.toString() + "\n";
		ret += "Quantidade de Equacoes: " + this.qtdEquacoes;
		return ret;
	}

	/**
	Verifica se a inst�ncia � igual a outra.
	@param obj objeto a ser comparado com a inst�ncia
	@return true se os atributos forem iguais, false se n�o forem
	*/
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;

		Resolvedor res = (Resolvedor) obj;
		if(!(this.sistema.equals(res.sistema)))
			return false;
		if(this.qtdEquacoes != res.qtdEquacoes)
			return false;
		return true;
	}
}