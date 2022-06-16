package TPO_desarrollo_parte1;

//Alumno : Osorio Mariano - FAI 2982

public class Obra {
    
    //Instancias de la clase
    private String autor;
    private String titulo;
    private final int codigo;
    private int votos;    
    private double largo;
    private double ancho;
    private double precio;
    private boolean descalificada;
    
   
    // ------- METODOS ------
           
    //Constructores
    public Obra (){
        autor = null;
        titulo = null;
        codigo = 0;
        largo = 0;
        ancho = 0;
        votos = 0;
        precio = 0;
        descalificada = false;
    }
    
    public Obra(int cod)    {
        autor = "";
        titulo = null;
        codigo = cod;
        largo = 0;
        ancho = 0;
        votos = 0;
        precio = 0;
        descalificada = false;
    }
    
    public Obra (String autor, String titulo, int cod, double largo, double ancho){
        this.autor = autor;
        this.titulo = titulo;
        this.codigo = cod;
        this.largo = largo;
        this.ancho = ancho;
        this.votos = 0;
        this.precio = 999.99;
        this.descalificada = false;
    }
    
    //Observadores
    
    public String getAutor(){
        return autor;
    }
    
    public String getTitulo(){
        return titulo;
    }   
        
    public int getCodigo(){
        return codigo;
    }
    
    public int getVotos(){
        return votos;
    } 
    
    public double getLargo(){
        return largo;
    }
    
    public double getAncho(){
        return ancho;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public boolean getDescalificada(){
        return descalificada;
    }
    
    public String aCadena(){
        return " Obra: " + titulo +
               "\n Código: " + codigo + 
               "\n Autor: " + autor +
               "\n Medidas: Largo = " + largo +" -- Ancho = " + ancho +
               "\n Votos: " + votos + 
               "\n Precio: " + precio +
               "\n Descalificada: " + descalificada;                
    }
    
    public boolean equals (Obra cuadro){
        return this.codigo == cuadro.getCodigo();
    }
    
    //Modificadores
    
    public void setAutor(String nombre){
        autor = nombre;
    }
    
    public void setTitulo(String rotulo){
        titulo = rotulo;
    }
    
    public void setVotos (int puntos){
        if(puntos >= 0){
            votos = puntos;
        }
    }
    
    public void setLargo (double lar){
        if(lar > 0){
            largo = lar;
        }
    }
    
    public void setAncho (double ancho){
        if(ancho > 0){
            this.ancho = ancho;
        }
    }
    
    public void setPrecio (double valor){
        if(valor >= 0){
            precio = valor;
        }
    }
    
    public void setDescalificada (boolean estado){
        descalificada = estado;
    }
    
    //propias del tipo
    
    public void votar(){
        votos++;
        actualizarPrecio();
    }
    
    public void descalificar(){
        descalificada = true;        
        actualizarPrecio();
    }
    
    private void actualizarPrecio(){
        if(votos % 10 == 0){
            precio += precio * 0.1; 
        }
    }
    
}
