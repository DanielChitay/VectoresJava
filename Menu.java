import java.util.*;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Ejercicios");
            System.out.println("1. Ejercicio 1: Cajas Binarias");
            System.out.println("2. Ejercicio 2: Agrupar a personas");
            System.out.println("3. Ejercicio 3: Array a matriz");
            System.out.println("4. Ejercicio 4: Ordenar por frecuencias");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ejecutarEjercicio1(scanner);
                    break;
                case 2:
                    ejecutarEjercicio2(scanner);
                    break;
                case 3:
                    ejecutarEjercicio3(scanner);
                    break;
                case 4:
                    ejecutarEjercicio4(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void ejecutarEjercicio1(Scanner scanner) {
        System.out.print("Ingrese la cadena binaria que representa las cajas: ");
        String boxes = scanner.next();
        
        int[] result = MinOpe.minOperations(boxes);
        System.out.println("El número mínimo de operaciones para cada caja es: " + Arrays.toString(result));
    }

    private static void ejecutarEjercicio2(Scanner scanner) {
        System.out.print("Ingrese el número de personas: ");
        int n = scanner.nextInt();
        int[] groupSizes = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el tamaño del grupo para la persona " + i + ": ");
            groupSizes[i] = scanner.nextInt();
        }

        int[][] grupos = AgruPer.agruparPersonas(groupSizes);

        System.out.println("Los grupos son:");
        for (int[] grupo : grupos) {
            System.out.println(Arrays.toString(grupo));
        }
    }

    private static void ejecutarEjercicio3(Scanner scanner) {
        System.out.print("Ingrese el tamaño del vector: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el valor " + (i + 1) + ": ");
            nums[i] = scanner.nextInt();
        }

        List<List<Integer>> matriz = ArrMat.arrayAMatriz(nums);

        System.out.println("Matriz resultante:");
        for (List<Integer> fila : matriz) {
            System.out.println(fila);
        }
    }

    private static void ejecutarEjercicio4(Scanner scanner) {
        System.out.print("Ingrese el tamaño del vector: ");
        int n = scanner.nextInt();
        int[] frecuencias = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese la frecuencia " + i + ": ");
            frecuencias[i] = scanner.nextInt();
        }

        String orden = OrdFrec.Ordenar(frecuencias);
        
        System.out.println("Matriz ordenada por frecuencias: " + orden);
    }
}

// Implementaciones básicas para que funcione el código

class MinOpe {
    public static int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++) {
                if (boxes.charAt(j) == '1') {
                    result[i] += Math.abs(i - j);
                }
            }
        }
        return result;
    }
}

class AgruPer {
    public static int[][] agruparPersonas(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            map.putIfAbsent(size, new ArrayList<>());
            map.get(size).add(i);
            if (map.get(size).size() == size) {
                result.add(map.get(size).stream().mapToInt(x -> x).toArray());
                map.get(size).clear();
            }
        }

        return result.toArray(new int[0][]);
    }
}

class ArrMat {
    public static List<List<Integer>> arrayAMatriz(int[] nums) {
        List<List<Integer>> matriz = new ArrayList<>();
        List<Integer> fila = new ArrayList<>();

        for (int num : nums) {
            fila.add(num);
            if (fila.size() == 3) {
                matriz.add(new ArrayList<>(fila));
                fila.clear();
            }
        }
        if (!fila.isEmpty()) {
            matriz.add(fila);
        }

        return matriz;
    }
}

class OrdFrec {
    public static String Ordenar(int[] frecuencias) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : frecuencias) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : frecuencias) {
            list.add(num);
        }

        list.sort((a, b) -> {
            int freqComp = freqMap.get(a) - freqMap.get(b);
            return freqComp == 0 ? Integer.compare(a, b) : freqComp;
        });

        return list.toString();
    }
}
