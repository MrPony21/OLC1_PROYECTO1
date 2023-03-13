package analisis;

/**
 *
 * @author Pilo Tuy
 */
public class Generador {
    public static void main(String[] args) {
        generarCompilador();
    }
    
    private static void generarCompilador(){
        try {
            
            String ruta = "src/analisis/";    
            //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = { ruta + "lex.jflex", "-d", ruta };
            jflex.Main.generate(opcFlex);
            String opcCUP[] = { "-destdir", ruta, "-parser", "parser", ruta + "sint.cup" };
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}