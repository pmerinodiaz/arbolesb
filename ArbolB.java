import java.applet.*;
import java.awt.*;
import java.util.Random;
 
public class ArbolB extends Applet
{
  private int maxnodos = 40;
  private int maxnivel = 20; 
	
	private int ancho = 30;
  private int alto = 20;
  private int ptox = ancho + 4;
  private int ptoy = alto  + 40;
	
	private NodoGUI arbolB[];
  
	private Arbol23 arbol23 = new Arbol23(100);
  
	private int nodoarray[];
  private int nodopos[] = new int[maxnivel];
  private int indice = 0;
  private int llave = -1; 
  private int hi_key = -1; 
  private Random numrandom = new Random();
	
	private Font fuente;
  private Button random, limpiar, insertar, borrar, buscar;
  private TextField numero;       
  private TextArea comentario;
	
	public void init()
  {
    int i;
		
		resize(ptox*27, ptoy*7);
    fuente   = new Font("Helvetica", Font.PLAIN, 10);
    random   = new Button(" Random ");
    insertar = new Button(" Insertar ");
    borrar   = new Button(" Borrar ");
    limpiar  = new Button(" Limpiar ");
    buscar   = new Button(" Buscar ");
    numero   = new TextField("",15);
    comentario = new TextArea("",7,35);
    comentario.setEditable(false);
		add(comentario);
		add(random);
    add(limpiar);
		add(numero);
		add(insertar);
    add(borrar);
    add(buscar);
    
		nodoarray = new int[maxnodos];
		arbolB    = new NodoGUI[maxnodos];
    arbolB[0]  = new NodoGUI(13*ptox, 3*ptoy, ancho, alto, false);
    arbolB[1]  = new NodoGUI(4*ptox,  4*ptoy, ancho, alto, false);
    arbolB[2]  = new NodoGUI(13*ptox, 4*ptoy, ancho, alto, false);
    arbolB[3]  = new NodoGUI(22*ptox, 4*ptoy, ancho, alto, false);
    arbolB[4]  = new NodoGUI(1*ptox,  5*ptoy, ancho, alto, false);
    arbolB[5]  = new NodoGUI(4*ptox,  5*ptoy, ancho, alto, false);
    arbolB[6]  = new NodoGUI(7*ptox,  5*ptoy, ancho, alto, false);
    arbolB[7]  = new NodoGUI(10*ptox, 5*ptoy, ancho, alto, false);
    arbolB[8]  = new NodoGUI(13*ptox, 5*ptoy, ancho, alto, false);
    arbolB[9]  = new NodoGUI(16*ptox, 5*ptoy, ancho, alto, false);
    arbolB[10] = new NodoGUI(19*ptox, 5*ptoy, ancho, alto, false);
    arbolB[11] = new NodoGUI(22*ptox, 5*ptoy, ancho, alto, false);
    arbolB[12] = new NodoGUI(25*ptox, 5*ptoy, ancho, alto, false);
    for (i=0; i<27; i++) 
		arbolB[i+13]=new NodoGUI(i*ptox,  6*ptoy, ancho, alto, false);
		 
		comentario.appendText("Arbol B de orden 1\n");
    comentario.appendText("Universidad de La Serena.\n");
    comentario.appendText("Ing. en computaci�n.\n");
    comentario.appendText("-----------------------------\n");
    reInit();
   }
   
	 public void paint(Graphics grafico)
   {
     int nodo;
     String valor1;
     String valor2;

     grafico.setColor(this.getBackground());
     grafico.fillRect(0, ptoy*3, ptox*27, ptoy*7);
     grafico.setColor(Color.black);

     for (int j=0; nodoarray[j]>-1; j++)
     {
       grafico.setColor(Color.black);
       nodo = nodoarray[j];
       
			 if (nodo == 0)
         grafico.drawRect(arbolB[0].x, arbolB[0].y, arbolB[0].ancho, arbolB[0].alto);
       else
       {
         grafico.drawRect(arbolB[nodo].x, arbolB[nodo].y, arbolB[nodo].ancho, arbolB[nodo].alto);
         grafico.drawLine(arbolB[(nodo+2)/3-1].x + ancho/2, arbolB[(nodo+2)/3-1].y + alto, arbolB[nodo].x + ancho/2, arbolB[nodo].y);
       }
       
			 grafico.setFont(fuente);
       grafico.setColor(Color.blue);
       valor1 = String.valueOf(arbolB[nodo].valorizq);
       if (arbolB[nodo].valorizq <= 9) valor1 = " "+valor1;
       if (arbolB[nodo].valorder == 0) valor2 = "-";
       else 
				   valor2 = String.valueOf(arbolB[nodo].valorder);
       if (arbolB[nodo].valorder <= 9) valor2 = " "+valor2;
       if (arbolB[nodo].valorizq == hi_key)
       {
         grafico.drawString("    ,"+valor2, arbolB[nodo].x+2, arbolB[nodo].y+alto*2/3);
         grafico.setColor(Color.red);
         grafico.drawString(valor1, arbolB[nodo].x+2, arbolB[nodo].y+alto*2/3);
         hi_key = -1;
       }
       else 
				 if (arbolB[nodo].valorder == hi_key)
         {
           grafico.setColor(Color.red);
           grafico.drawString("     "+valor2, arbolB[nodo].x+2, arbolB[nodo].y+alto*2/3);
           grafico.setColor(Color.blue);
           grafico.drawString(valor1+",", arbolB[nodo].x+2, arbolB[nodo].y+alto*2/3);
           hi_key = -1;
         }
         else
           grafico.drawString(valor1+","+valor2, arbolB[nodo].x+2, arbolB[nodo].y+alto*2/3);
     }
   }
	
   public boolean action(Event evento, Object arg)
   {
     if (evento.target == limpiar)
     {
       Graphics g = this.getGraphics();
       g.setColor(this.getBackground());
       g.fillRect(0, 3*ptoy, ptox*27, ptoy*7);
       comentario.setText("");
       comentario.appendText("Arbol B de orden 1\n");
       comentario.appendText("Universidad de La Serena.\n");
       comentario.appendText("Ing. en computaci�n.\n");
       comentario.appendText("-----------------------------\n");
			 comentario.appendText("Arbol limpiado\n");
       reInit();
       return true;
     }
     else if (evento.target == random)
          {
            comentario.appendText("Creaci�n Aleatoria del Arbol\n");
            randomInsertar();
            return true;
          }
          else if (evento.target == insertar)
               {
                 if (!numero.getText().equals(""))
								 {
                   llave = Integer.parseInt(numero.getText());
                   if ((llave <= 0) || (llave > 99))
                   {
										 comentario.appendText("Fuera del Rango permitido!!\n");
										 comentario.appendText("[1-99] solamente\n");	
									 }		
                   else
									 {
                     comentario.appendText("Inserci�n del n�mero "+numero.getText()+"\n");
                     insertar();
                   }
                   numero.setText(""); 
                 }
                 else 
                   comentario.appendText("Ingrese el n�mero a Insertar!!\n");
                 return true;
               }
               else if (evento.target == borrar)
                    {
                      if (!numero.getText().equals(""))
											{
                        llave = Integer.parseInt(numero.getText());
                        comentario.appendText("Borrado del n�mero "+numero.getText()+"\n");
                        borrar();
                        numero.setText("");
                      }
                      else
                        comentario.appendText("Ingrese el n�mero a Borrar!!\n");
                      return true;
                    }
                    else if (evento.target == buscar)
                         {
                           if (!numero.getText().equals(""))
													 {
                             llave = Integer.parseInt(numero.getText());
                             comentario.appendText("B�squeda del n�mero "+numero.getText()+"\n");
                             if (arbol23.buscar(llave))
                             {
                               comentario.appendText("El n�mero "+numero.getText()+" fu� Encontrado\n");
                               hi_key = llave;
                               repaint();
                             }
                             else
                               comentario.appendText("El n�mero "+numero.getText()+" no fu� Encontrado\n");
                             numero.setText("");
                           }
                           else 
                             comentario.appendText("Ingrese el n�mero a Buscar!!\n");
                           return true;
                         }
                         else return super.action(evento, arg);
   }

   public void randomInsertar()
   {
     int numero;
 
     reInit();
     numero = Math.abs(numrandom.nextInt())%99+1;
     arbol23.insertar(numero);

     for (int i=1; i<35 ; i++)
     {
       while (arbol23.buscar(numero = Math.abs(numrandom.nextInt())%99+1));
       arbol23.insertar(numero);
     }

     for (int j=0; j<maxnivel; j++)
       nodopos[j]=0;
     indice = 0;
     infoNodo(0, arbol23.t);
     repaint();
   }
 
	 public void infoNodo(int nivel, int ptr)
   {
     int numnodo=-1; 
     if (ptr == -1)
		 {
       nodopos[nivel]++;
       nodopos[nivel + 1]+=3;
       return;
     }
     nodopos[nivel]++;
     switch (nivel)
		 {
       case 0: { numnodo=0;    break; }
       case 1: { numnodo = 1 + nodopos[nivel] - 1; break; }
       case 2: { numnodo = 4 + nodopos[nivel] - 1; break; }
			 case 3: { numnodo =13 + nodopos[nivel] - 1; break; }
     }
		 if (numnodo >-1)
		 {
       nodoarray[indice] = numnodo;
       arbolB[numnodo].valorizq = arbol23.nodo[ptr].datoizq;
       arbolB[numnodo].valorder = arbol23.nodo[ptr].datoder;
       indice++;
     }
     else
		 {
       if ((arbol23.nodo[ptr].datoizq == llave) || (arbol23.nodo[ptr].datoder == llave))
       {
         comentario.appendText("No se puede Insertar!!\n");
         comentario.appendText("Sobrepas� el l�mite permitido\n");
         comentario.appendText("Haga click en el bot�n Limpiar\n");
       }
       arbol23.nodo[ptr].datoizq = 0;
       arbol23.nodo[ptr].datoder = 0;
       arbol23.nodo[ptr].hijoizq= -1;
       arbol23.nodo[ptr].hijomed= -1;
       arbol23.nodo[ptr].hijoder= -1;
     }
     infoNodo(nivel+1, arbol23.nodo[ptr].hijoizq);
     infoNodo(nivel+1, arbol23.nodo[ptr].hijomed);
     infoNodo(nivel+1, arbol23.nodo[ptr].hijoder);
   }

   public void reInit()
   {
     for (int i=0; i<maxnodos; i++)
     {
       arbolB[i].valorizq = 0;
       arbolB[i].valorder = 0;
       nodoarray[i] = -1;
       reInitArbol(0, arbol23.t);
       arbol23.t = -1;
       arbol23.indicepila = 1;
     }
   }

   public void reInitArbol(int nivel, int ptr)
   {
     if (ptr == -1) return;
     arbol23.nodo[ptr].datoizq = 0;
     arbol23.nodo[ptr].datoder = 0;
     reInitArbol(nivel+1, arbol23.nodo[ptr].hijoizq);
     arbol23.nodo[ptr].hijoizq= -1;
     reInitArbol(nivel+1, arbol23.nodo[ptr].hijomed);
     arbol23.nodo[ptr].hijomed= -1;
     reInitArbol(nivel+1, arbol23.nodo[ptr].hijoder);
     arbol23.nodo[ptr].hijoder= -1;
   }

   public void insertar()
   {
     if (arbol23.buscar(llave))
     	 comentario.appendText("El n�mero "+llave+" ya est� en el Arbol!!\n");
     else
     { 
       hi_key = llave;
       arbol23.insertar(llave);
       for (int j=0; j<maxnivel; j++) nodopos[j]   =  0;
       for (int i=0; i<maxnodos; i++) nodoarray[i] = -1;
       indice = 0;
       infoNodo(0, arbol23.t);
       repaint();
       llave = -1; 
     }
   }

   public void borrar()
   {
     if (arbol23.buscar(llave))
     {
       arbol23.borrar(llave);
       for (int j=0; j<maxnivel; j++) nodopos[j]   =  0;
       for (int i=0; i<maxnodos; i++) nodoarray[i] = -1;
       indice = 0;
       if (arbol23.nodo[arbol23.t].datoizq == 0)
            reInit();
       else infoNodo(0, arbol23.t);
       repaint();
     }
     else
       comentario.appendText("El n�mero "+llave+" no fu� Encontrado!!\n");
     llave = -1;
   }
}