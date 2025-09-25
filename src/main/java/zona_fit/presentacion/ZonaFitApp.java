package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        IClienteDAO clienteDAO = new ClienteDAO();
        System.out.println("*** Menu Interactivo ZonaFit ***");

        while (!salir) {
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpcion (opcion, consola, clienteDAO);
            }catch (Exception e) {
                System.out.println("Ocurrio un error: "+e.getMessage());
            }finally {
                System.out.println(); // Imprime un salto de linea con cada iteración
            }
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                1. Lista de clientes
                2. Buscar cliente por ID
                3. Agregar nuevo cliente
                4. Actualizar cliente
                5. Eliminar cliente
                6. Salir
                Elige una opcion:\s""");
        // Leemos y retornamos la opcion seleccionada
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpcion(int opcion, Scanner consola, IClienteDAO clienteDAO) {
        var salir = false;
        switch (opcion) {
            case 1 -> mostrarClientes(clienteDAO);
            case 2 -> buscarClienteID(consola, clienteDAO);
            case 3 -> agregarCliente(consola, clienteDAO);
            case 4 -> actualizarCliente(consola, clienteDAO);
            case 5 -> eliminarCliente(consola, clienteDAO);
            case 6 -> {
                System.out.println("Regresa pronto !!!");
                salir = true;
            }
            default -> System.out.println("Opcion invalida: "+ opcion);
        }
        return salir;
    }

    private static void mostrarClientes(IClienteDAO clienteDAO) {
        var cliente = clienteDAO.listarClientes();
        cliente.forEach(System.out::println);
    }

    private static void buscarClienteID(Scanner consola, IClienteDAO clienteDAO) {
        System.out.print("Ingrese el ID del cliente: ");
        var id = Integer.parseInt(consola.nextLine());

        var idCliente = new Cliente(id);
        var encontrado = clienteDAO.buscarClientePorID(idCliente);
        if (encontrado)
            System.out.println("El cliente con el ID: "+idCliente.getNombre()
                    + " "+ idCliente.getApellido()+" - membresia "+ idCliente.getMembresia());
        else
            System.out.println("El cliente no existe");
    }

    private static void agregarCliente(Scanner consola, IClienteDAO clienteDAO) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = consola.nextLine();
        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = consola.nextLine();
        System.out.print("Ingrese el membresia del cliente: ");
        var membresia = Integer.parseInt(consola.nextLine());

        var cliente = new Cliente(nombre, apellido, membresia);
        var agregado = clienteDAO.agregarCliente(cliente);

        if (agregado)
            System.out.println("El cliente ha sido agregado exitosamente");
        else
            System.out.println("El cliente no se pudo agregar");
    }

    public static void actualizarCliente (Scanner consola, IClienteDAO clienteDAO) {
        System.out.print("Ingrese el ID del cliente");
        var id = Integer.parseInt(consola.nextLine());
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = consola.nextLine();
        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = consola.nextLine();
        System.out.print("Ingrese el membresia del cliente: ");
        var membresia = Integer.parseInt(consola.nextLine());

        var cliente = new Cliente(id, nombre, apellido, membresia);
        var actualizar = clienteDAO.modificarCliente(cliente);

        if (actualizar)
            System.out.println("El cliente ha sido actualizado exitosamente");
        else
            System.out.println("El cliente no se pudo actualizar");
    }

    public static void eliminarCliente(Scanner consola, IClienteDAO clienteDAO) {
        System.out.print("Ingrese el ID del cliente que desea eliminar:");
        var id = Integer.parseInt(consola.nextLine());

        var cliente = new Cliente(id);
        var eliminado = clienteDAO.eliminarCliente(cliente);

        if (eliminado)
            System.out.println("El cliente ha sido eliminado exitosamente");
        else
            System.out.println("El cliente no se pudo eliminar");
    }
}
