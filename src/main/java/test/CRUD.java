package test;

import com.mycompany.crud.jpa.console.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CRUD {
    
    public static void main(String[] args){
        
        int item = 0;
        Scanner scanner = new Scanner(System.in);
        Persona persona;
        
        EntityManager entity = JPA.getEntityManagerFactory().createEntityManager();
        while (item != 5){
            System.out.println("Bienvenido al CRUD de JPA a nivel de Consola");
            System.out.println("1. Crear Persona");
            System.out.println("2. Buscar Persona");
            System.out.println("3. Ver listado de Persona");
            System.out.println("4. Actualizar Persona");
            System.out.println("5. Eliminar Persona");
            System.out.println("6. Salir");
            System.out.println("Seleccione la funcion que desea realizar");
            
            item = scanner.nextInt();
            switch (item){
                case 1:
                        System.out.println("Digite el nombre del usuario");
                        persona = new Persona();
                        scanner.nextLine();
                        persona.setNombre(scanner.nextLine());
                        System.out.println("Digite el apellido del usuario");
                        persona.setApellido(scanner.nextLine());
                        System.out.println("Digite el email del usuario");
                        persona.setEmail(scanner.nextLine());
                        System.out.println("Digite el numero telefonico del usuario");
                        persona.setTelefono(scanner.nextLine());
                        System.out.println(persona);
                        entity.getTransaction().begin();
                        entity.persist(persona);
                        entity.getTransaction().commit();
                        System.out.println("Producto Registrado");
                        System.out.println("");
                        break;
                        
                case 2: 
                        System.out.println("Digite el id de la persona que desea buscar:");
                        persona = new Persona();
                        persona = entity.find(Persona.class, scanner.nextInt());
                        if (persona != null){
                            System.out.println(persona);
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("Persona no encontrada...    Listad de Personas");
                            List<Persona> listaPersona = new ArrayList<>();
                            Query query = entity.createQuery("SELECT t FROM Persona t");
                            listaPersona = query.getResultList();
                            
                            for (Persona p : listaPersona){
                                System.out.println(p);
                            }
                            System.out.println();
                        }
                        break;
                        
                case 3:
                        System.out.println("Listado de personas");
                        persona = new Persona();
                        List<Persona> listaPersona = new ArrayList<>();
                        Query query = entity.createQuery("SELECT t FROM Persona t");
                        listaPersona = query.getResultList();
                        
                        for (Persona p : listaPersona) {
                            System.out.println(p);
                        }
                        System.out.println();
                        
                        break;
                        
                case 4:
                        System.out.println("Digite el id del empleado que actuzalizara");
                        persona = new Persona();
                        
                        persona = entity.find(Persona.class, scanner.nextInt());
                        if (persona != null){
                            System.out.println(persona);
                            System.out.println("Digite el nombre de la persona");
                            scanner.nextLine();
                            persona.setNombre(scanner.nextLine());
                            System.out.println("Digite el apellido de la persona");
                            persona.setApellido(scanner.nextLine());
                            System.out.println("Digite el email de la persona");
                            persona.setEmail(scanner.nextLine());
                            System.out.println("Digite el numero telefonico de la persona");
                            persona.setTelefono(scanner.nextLine());
                            entity.getTransaction().begin();
                            entity.merge(persona);
                            entity.getTransaction().commit();
                            System.out.println("Persona actualizada");
                            System.out.println();
                        } else {
                            System.out.println("Persona no encontrada");
                            System.out.println();
                        }
                        break;
                        
                case 5:
                        System.out.println("Digite el id de la persona a eliminar");
                        persona = new Persona();
                        
                        persona = entity.find(Persona.class, scanner.nextInt());
                        if (persona != null){
                            System.out.println(persona);
                            entity.getTransaction().begin();
                            entity.remove(persona);
                            entity.getTransaction().commit();
                            System.out.println("Persona eliminada");
                        } else {
                            System.out.println("Persona no encontrada");
                        }
                        break;
                        
                case 6:
                        entity.close();
                        JPA.shutdown();
                        
                        break;
                        
                default:
                        
                        System.out.println("Opcion no valida");
                        break;
            }
        }
    }
}
