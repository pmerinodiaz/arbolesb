public class Arbol23 
{
	public class Nodo 
	{
  	public int datoizq, datoder;
  	public int hijoizq, hijomed, hijoder;
	}
	
	public Nodo nodo[];
  public int t, indicepila;
		
	private Pila pila;
	private int y, a;
	private int NULL = -1;
	private int tamaniopila;
	  
  public Arbol23(int tamanio) 
	{
    nodo = new Nodo[tamanio + 1];
    tamaniopila = tamanio;
    t = NULL;
    indicepila = 1;
    
		for (int i=1; i<=tamaniopila; i++) 
		{
      nodo[i] = new Nodo();
      nodo[i].datoizq = nodo[i].datoder = 0;
      nodo[i].hijoizq = NULL;
      nodo[i].hijomed = NULL;
      nodo[i].hijoder = NULL;
    }
  }
  
	public boolean insertar(int llave) 
	{
    pila = new Pila(tamaniopila/2);
    int p; 
		boolean nohecho;
    if (t == NULL) 
		{
      nuevaRaiz(t, llave, NULL);
      return true;
    }
    else 
		{
      p = buscarNodo(llave);
      if (p == NULL) 
	      return false;
      else
      {
			  a = NULL; 
				nohecho = true; 
				y = llave;
			}	
      while (nohecho) 
			{
        if (nodo[p].datoder == 0) 
				{
          PutIn(p, y, a);
          nohecho = false;
        }
        else 
				{
          dividir(p, y, a);
          if (p == t) 
					{
            nuevaRaiz(t, y, a);
            nohecho = false;
          }
          else 
					  p = pila.sacar();
        }
      }
      pila.limpiar();
    }
    return true;
  }
	
  public boolean buscar(int llave) 
	{
    int p;
    boolean nohecho;
    
		if (t == NULL) 
		  return false;
    else 
		{
      p = t; 
			nohecho = true;
      while ((nohecho) && (p != NULL)) 
			{
        if ((nodo[p].datoizq == llave) || (nodo[p].datoder == llave))
             nohecho = false;
        else if (nodo[p].datoizq > llave)
                  p = nodo[p].hijoizq;
             else if (nodo[p].hijoizq < llave) 
							    {
                    if (nodo[p].datoder == 0)
                      p = nodo[p].hijomed;
                    else 
										{
                      if (nodo[p].datoder < llave)
                           p = nodo[p].hijoder;
                      else p = nodo[p].hijomed;
                    }
                  }
                  else;
      }
      if (nohecho == true) 
			     return false;
      else return true;
    }
  }
 
  private void dividir(int p, int llave, int b) 
	{
    int x;
    
		x = indicepila; 
		indicepila++;
    if (nodo[p].datoder < llave) 
		{
      nodo[x].datoizq = llave;
      nodo[x].hijomed = b;
      nodo[x].hijoizq = nodo[p].hijoder;
      y = nodo[p].datoder;
    }
    else 
		{
      nodo[x].datoizq = nodo[p].datoder;
      nodo[x].hijomed = nodo[p].hijoder;
      if (nodo[p].datoizq < llave) 
			{
        nodo[x].hijoizq = b;
        y = llave;
      }
      else 
			{
        nodo[x].hijoizq = nodo[p].hijomed;
        y = nodo[p].datoizq;
        nodo[p].datoizq = llave;
        nodo[p].hijomed = b;
      }
    }
    nodo[p].datoder = 0;
    nodo[p].hijoder = NULL;
    a = x;
  }

  private void PutIn(int p, int llave, int k) 
	{
    if (nodo[p].datoizq < llave)
		{
      nodo[p].datoder = llave;
      nodo[p].hijoder = k;
    }
    else 
		{
      nodo[p].datoder = nodo[p].datoizq;
      nodo[p].hijoder = nodo[p].hijomed;
      nodo[p].datoizq = llave;
      nodo[p].hijomed = k;
    }
  }

  private int buscarNodo(int llave) 
	{
    int p;
    p = t;
    while (p != NULL) 
		{
      if ((nodo[p].datoizq == llave) || (nodo[p].datoder == llave)) 
			{
        pila.poner(p);
        return NULL;
      }
      if (nodo[p].datoizq > llave) 
			{
        pila.poner(p);
        p = nodo[p].hijoizq;
      }
      else if ((nodo[p].datoizq < llave) && (nodo[p].datoder == 0)) 
			     {
             pila.poner(p);
             p = nodo[p].hijomed;
           }
           else if ((nodo[p].datoizq < llave) && (nodo[p].datoder > llave)) 
						    {
                  pila.poner(p);
                  p = nodo[p].hijomed;
                }
                else 
								{
                  pila.poner(p);
                  p = nodo[p].hijoder;
                }
    }
    return (pila.sacar());
  }

  private void nuevaRaiz(int x, int k, int z) 
	{
    int p;
  
		p = indicepila;
		indicepila++;
    nodo[p].datoizq = k;
    nodo[p].hijoizq = x;
    nodo[p].hijomed = z;
    t = p;
  }
 
  public boolean borrar(int llave) 
	{
    int p, q, r;
    boolean nohecho = true;
 
	  pila = new Pila(tamaniopila/2);
    if ((p = buscarNodo(llave)) != NULL) 
		  return false;
    else
		{
      p = pila.sacar();
      if (nodo[p].hijoizq != NULL) 
			{
        p = buscarNodoIzq(p, llave);
        if (nodo[p].datoder == 0)
             llave = nodo[p].datoizq;
        else llave = nodo[p].datoder;
      }
      if (nodo[p].datoizq == llave)
      {
			  if (nodo[p].datoder != 0) 
				{
          nodo[p].datoizq = nodo[p].datoder;
          nodo[p].datoder = 0;
          nohecho = false;
        }
        else
          nodo[p].datoizq = 0;
			}
      else 
			{
        nodo[p].datoder = 0;
        nohecho = false;
      }
      if (nodo[t].datoizq == 0)
         nohecho = false;
      while (nohecho == true) 
			{
        r = pila.sacar();
        if (nodo[r].hijomed == p)
             q = nodo[r].hijoizq;
        else q = nodo[r].hijomed;
        if (nodo[q].datoder != 0) 
				{
          rotar(p, q, r);
          nohecho = false;
        }
        else 
				{
          combinar(p, q, r);
          if (nodo[r].datoizq != 0)
            nohecho = false;
          else 
					{
            if (r == t) 
						{
              if (nodo[r].hijoizq == p)
                   t = p;
              else t = q;
              nohecho = false;
            }
            else
              p = r;
          }
        }
      }
      pila.limpiar();
    }
    return true;
  }

  private int buscarNodoIzq(int ptr, int llave) 
	{
    int p, q;
    boolean nodoizq;
 
    p = ptr;
    if (nodo[ptr].datoizq == llave) 
		{
      pila.poner(p);
      p = nodo[p].hijoizq;
      nodoizq = true;
    }
    else 
		{
      pila.poner(p);
      p = nodo[p].hijomed;
      nodoizq = false;
    }
    q = p;
    while (p != NULL) 
		{
      if (nodo[p].datoder == 0) 
			{
        q = p;
        pila.poner(p);
        p = nodo[p].hijomed;
      }
      else 
			{
        q = p;
        pila.poner(p);
        p = nodo[p].hijoder;
      }
    }
    if (nodo[q].datoder != 0) 
		{
      if (nodoizq)
           nodo[ptr].datoizq = nodo[q].datoder;
      else nodo[ptr].datoder = nodo[q].datoder;
    }
    else
      if (nodoizq)
           nodo[ptr].datoizq = nodo[q].datoizq;
      else nodo[ptr].datoder = nodo[q].datoizq;
    return (pila.sacar());
	}
 
  private void rotar(int p, int q, int r) 
	{
    if (p == nodo[r].hijoizq) 
		{
      nodo[p].datoizq = nodo[r].datoizq;
      nodo[p].hijomed = nodo[q].hijoizq;
      nodo[r].datoizq = nodo[q].datoizq;
      nodo[q].datoizq = nodo[q].datoder;
      nodo[q].hijoizq = nodo[q].hijomed;
      nodo[q].hijomed = nodo[q].hijoder;
    }
    else 
		  if (p == nodo[r].hijomed) 
			{
        nodo[p].datoizq = nodo[r].datoizq;
        nodo[p].hijomed = nodo[p].hijoizq;
        nodo[p].hijoizq = nodo[q].hijoder;
        nodo[r].datoizq = nodo[q].datoder;
      }
      else 
			{
        nodo[p].datoizq = nodo[r].datoder;
        nodo[p].hijomed = nodo[p].hijoizq;
        nodo[p].hijoizq = nodo[q].hijoder;
        nodo[r].hijoder = nodo[q].datoder;
      }
    nodo[q].datoder = 0;
    nodo[q].hijoder = NULL;
  }

  private void combinar(int p, int q, int r) 
	{
    if (p == nodo[r].hijoizq) 
		{
      nodo[p].datoizq = nodo[r].datoizq;
      nodo[p].datoder = nodo[q].datoizq;
      nodo[p].hijomed = nodo[q].hijoizq;
      nodo[p].hijoder = nodo[q].hijomed;
      colocar(nodo[r].hijomed);
      nodo[r].datoizq = nodo[r].datoder;
      nodo[r].hijomed = nodo[r].hijoder;
    }
    else 
		  if (p == nodo[r].hijomed) 
			{
        nodo[q].datoder = nodo[r].datoizq;
        nodo[q].hijoder = nodo[p].hijoizq;
        nodo[r].datoizq = nodo[r].datoder;
        colocar(nodo[r].hijomed);
        nodo[r].hijomed = nodo[r].hijoder;
      }
      else 
			{
        nodo[q].datoder = nodo[r].datoder;
        nodo[q].hijoder = nodo[p].hijoizq;
        colocar(nodo[r].hijoder);
      }
    nodo[r].datoder = 0;
    nodo[r].hijoder = NULL;
  }

  private void colocar(int ptr) 
	{
    nodo[ptr].datoizq = 0;
    nodo[ptr].datoder = 0;
    nodo[ptr].hijoizq = NULL;
		nodo[ptr].hijomed = NULL;
		nodo[ptr].hijoder = NULL;
  }
}
 


