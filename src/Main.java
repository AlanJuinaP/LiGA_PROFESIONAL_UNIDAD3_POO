import java.util.*;

public class Main {
    public static void main(String[] args) {
        Liga_de_futbol liga = new Liga_de_futbol();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=====LIGA PRO====");
            System.out.println("1. Ingresar equipo");
            System.out.println("2. Quitar equipo");
            System.out.println("3. Iniciar Torneo");
            System.out.println("4. Ver estadisitcas de equipos");
            System.out.println("5. Mostrar registro de resultados");
            System.out.println("6. Salir");

            System.out.println("Selecciones una opcion: ");
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombredelEquipo = scanner.nextLine();
                    liga.Agregar_Equipos(new Equipo(nombredelEquipo));
                    break;
                
                
                case 2:
                System.out.print("Ingrese el nombre del equipo que desea quitar: ");
                String equipo_a_quitar = scanner.nextLine();
                liga.Quitar_Equipo(equipo_a_quitar);
                break;

                case 3:
                System.out.print("Ingrese la etapa (octavos, cuartos, semifinales, final): ");
                String etapa = scanner.nextLine();
                try{
                    liga.realizar_Sorteo(etapa);
                }catch(Cantidad_Equipos_Invalido_Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
                break;

                case 4:
                liga.ver_Estadistics();
                break;

                case 5:
                liga.most_Registro_Partidos();
                break;

                case 6:
                salir = true;
                System.out.println("Saliendo del programa..... Gracias por usarlo :)");
                break;

                default:
                System.out.println("Opcion no valida.....");
                    //break;
            }
        }
    }
}
