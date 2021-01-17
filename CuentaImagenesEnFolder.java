import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

//Busca archivos jpg o png en todos los folders a partir de un folder inicial.
//Crea resultado.txt donde quedan las rutas y el número de archivos que tiene cada folder

class CuentaImagenesEnFolder {

    public static void listFilesForFolder(final File folder) {
        try {
            int cuenta = 0;
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                } else {
                    String nombreArchivo = fileEntry.getName().toLowerCase();
                    cuenta+=(nombreArchivo.matches(".*\\.png.*|.*\\.jpg.*"))?1:0; //si contiene .png o .jpg suma 1 a cuenta 
                }
            }
            if (cuenta != 0) { 
                String resultado = folder.getAbsolutePath()+'\t'+cuenta+'\n';
                Files.write(Paths.get("resultado.txt"), resultado.getBytes(),StandardOpenOption.APPEND);
                System.out.println(folder.getName() + " " + resultado);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public static void main(String[] args) {
        //recibe como parametro el nombre del folder inicial donde busca
        final File folder = new File(args[0]);
        listFilesForFolder(folder); 
    }
} 