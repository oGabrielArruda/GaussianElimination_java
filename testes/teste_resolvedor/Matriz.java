public class Matriz implements Cloneable
{
	protected double elem[][];
	protected int linhas;
	protected int colunas;

	/**
	Construtor da classe matriz.
	Seta os atributos da classe.
	@param matriz uma matriz de n�meros reais
	@throws Exception caso o par�metro seja nulo
	*/
	public Matriz(double[][] matriz) throws Exception
	{
		if(matriz == null)
			throw new Exception("Matriz nula");

		this.elem = matriz;
		this.linhas = matriz.length;
		this.colunas = matriz[0].length;
	}

	/**
	Seta o valor de uma posi��o especifica na matriz.
	@param linha linha desejada para inclus�o
	@param coluna coluna desejada para inclus�o
	@param valor valor real desejado para inclus�o
	@throws Exception caso a linha ou a coluna sejam negativas ou maiores que a quantidade de linhas e colunas
	*/
	public void setValor(int linha, int coluna, double valor) throws Exception
	{
		if(linha < 0 || coluna < 0 || linha > this.linhas - 1 || coluna > this.colunas - 1)
			throw new Exception("Espa�o inv�lido!");

		this.elem[linha][coluna] = valor;
	}

	/**
	Pega o valor em determinada posi��o da matriz.
	@param linha linha desejada
	@param coluna coluna desejada
	@return o valor do elemento na posi��o indicada
	@throws Exception caso a linha ou a coluna sejam negativas ou maiores que a quantidade de linhas e colunas
	*/
	public double getValor(int linha, int coluna) throws Exception
	{
		if(linha < 0 || coluna < 0 || linha > this.linhas - 1 || coluna > this.colunas - 1)
			throw new Exception("Espa�o inv�lido!");
		return this.elem[linha][coluna];
	}

	/**
	Pega os valores de uma determinada linha.
	Percorre os valores da linha desejada e os adicionam em um vetor.
	Feito isso, retorna-se o vetor com os valores da linha
	@param linha linha desejada
	@return um vetor de n�meros reais com os valores da linha desejada
	@throws Exception caso a linha seja inv�lida
	*/
	public double[] getLinha(int linha) throws Exception
	{
		if (linha < 0 || linha > this.linhas - 1)
			throw new Exception("Param�tro inv�lido!");
		double[] aux = new double[this.colunas];
		for (int coluna = 0; coluna < this.colunas; coluna++)
		{
			aux[coluna] = this.elem[linha][coluna];
		}
		return aux;
	}

	/**
	Pega a quantidade de linhas da matriz
	@return quantidade de linhas da matriz
	*/
	public int getLinhas()
	{
		return this.elem.length;
	}

	/**
	Pega a matriz da inst�ncia.
	@return a matriz clonada
	*/
	public double[][] getMatriz()
	{
		return this.elem.clone();
	}

	/**
	Transforma e retorna a inst�ncia em formato de String
	@return string com os valores da inst�ncia
	*/
	public String toString()
	{
		String ret = "";
		for(int linha = 0; linha < this.linhas; linha++)
		{
			for(int coluna = 0; coluna < this.colunas; coluna++)
			{
				ret+= this.elem[linha][coluna] + " ";
			}
			ret+="\n";
		}

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
		Matriz mat = (Matriz)obj;

		if(this.colunas != mat.colunas || this.linhas != mat.linhas)
			return false;
		for(int linha = 0; linhas < this.linhas; linha++)
			for(int coluna = 0; coluna < this.colunas; coluna++)
				if(this.elem[linha][coluna] != mat.elem[linha][coluna])
					return false;
		return true;
	}

	/**
	Calcula e devolve o c�digo hash da inst�ncia.
	@return o c�digo hash.
	*/
	public int hashCode()
	{
		int ret = 357;
		ret = ret*7 + new Integer(this.linhas).hashCode();
		ret = ret*7 + new Integer(this.colunas).hashCode();

		for(int linha = 0; linha < this.linhas; linha++)
			for(int coluna = 0; coluna < this.colunas; coluna++)
				ret = ret*7 + new Double(this.elem[linha][coluna]).hashCode();
		return ret;
	}

	/**
	Construtor de c�pia da classe.
	Seta os atributos da inst�ncia com os do passado como par�metro
	@param inst�ncia a ser copiada
	*/
	public Matriz(Matriz modelo) throws Exception
	{
		if (modelo == null)
			throw new Exception("Parametro inv�lido");

		this.elem = new double[modelo.linhas][modelo.colunas];
		this.linhas = modelo.linhas;
		this.colunas = modelo.colunas;;

		for (int i = 0; i < this.linhas; i++)
		{
			for(int j = 0; j < this.colunas; j++)
			{
				this.elem[i][j] = modelo.elem[i][j];
			}
		}
	}

	/**
	Clona a inst�ncia.
	@return a inst�ncia clonada
	*/
	public Object clone()
	{
		Matriz ret = null;
		try
		{
			ret = new Matriz(this);
		}
		catch(Exception erro)
		{ }
		return ret;
	}
}