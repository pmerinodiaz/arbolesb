public class Pila 
{
  private int dato[];
  private int tope;

  public Pila(int tamanio) 
	{
    dato = new int[tamanio];
    tope = -1;
  }

  public void poner(int llave) 
	{
    dato[++tope] = llave;
  }
	
	public int sacar() 
	{
    return dato[tope--];
  }
	
	public void limpiar() 
	{
    for (int i=0; i<dato.length; i++)
         dato[i] = 0;
    tope = -1;
  }
}