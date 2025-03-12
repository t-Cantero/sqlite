public class Main {
    public static void main(String[] args) {
        mostrarOpciones();
    }


    public static void mostrarOpciones(){
        int n;
        do{
            System.out.println("Sobre que tabla deseas actuar:");
            System.out.println("1.Libros");
            System.out.println("2.Salir");
            n = Utilidades.leerEntero("Elige una opción (introduzca el número):");
        }while(n<1 || n>2);//Provisional
        if(n == 2){
            System.out.println("Salida realizada con éxito");
        }else{
            switch (n){
                case 1://Mostrar opciones sobre la tabla Libros
                    opcionesLibros();
                    break;
            }
        }
    }

    public static void opcionesLibros(){
        int n;

        do{
            System.out.println("¿Qué desea hacer?:");
            System.out.println("1. Listar Libros");
            System.out.println("2. Agregar Libro");
            System.out.println("3. Eliminar Libro");
            System.out.println("4. Modificar Disponibilidad");
            System.out.println("5. Volver a la página inicial");
            n = Utilidades.leerEntero("Elige una opción  (Introduzca el número):");
        }while(n<1 || n>5);
        if(n==5){
            mostrarOpciones();
        }else{
            switch(n){//Prueba Inicial
                case 1://Listar Libro
                    obtenerInfoLibro.listarLibros();
                    break;
                case 2://Agregar Libro
                    addLibro.agregarLibro();
                    break;
                case 3://Eliminar Libro
                    deleteLibro.eliminarLibro();
                    break;
                case 4://Modificar disponibilidad
                    updateDisponible.modificarDisponibilidad();
                    obtenerInfoLibro.listarLibros();
                    break;

            }
        }
    }
}
