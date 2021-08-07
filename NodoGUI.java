public class NodoGUI
{
   public int x;
   public int y;
   public int ancho;
   public int alto;
   public int valorizq;
   public int valorder;
   public boolean enuso;

   public NodoGUI(int x, int y, int ancho, int alto)
   {
      this.x = x;
      this.y = y;
      this.ancho = ancho;
      this.alto = alto;
      this.valorizq = 0;
      this.valorder = 0;
      this.enuso = true;
   }
 
   public NodoGUI(int x, int y, int ancho, int alto, boolean enuso)
   {
      this.x = x;
      this.y = y;
      this.ancho = ancho;
      this.alto = alto;
      this.valorizq = 0;
      this.valorder = 0;
      this.enuso = enuso;
   }

   public NodoGUI(int x, int y, int ancho, int alto, int valorizq, int valorder)
   {
      this.x = x;
      this.y = y;
      this.ancho = ancho;
      this.alto = alto;
      this.valorizq = valorizq;
      this.valorder = valorder;
      this.enuso = true;
   }
}