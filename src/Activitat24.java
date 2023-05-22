import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Activitat24 {

    public static List<Integer> fusionarLlistes(List<Integer> llista1, List<Integer> llista2) {
        List<Integer> fusio = new ArrayList<>();
        fusio.addAll(llista1);
        fusio.addAll(llista2);
        Collections.sort(fusio);
        return fusio;
    }

    //Encara que el mètode que acabe de fer és més eficient, he decidit fer dos mètodes més per a practicar:

    public static List<Integer> fusionarLlistesSenseIterador(List<Integer> llista1, List<Integer> llista2) {
        List<Integer> fusio = new ArrayList<>();

        int i = 0; // Índex per recórrer llista1
        int j = 0; // Índex per recórrer llista2

        while (i < llista1.size() && j < llista2.size()) {
            if (llista1.get(i) <= llista2.get(j)) {
                fusio.add(llista1.get(i));
                i++;
            } else {
                fusio.add(llista2.get(j));
                j++;
            }
        }

        // Afegir els elements restants de llista1
        fusio.addAll(llista1.subList(i, llista1.size()));

        // Afegir els elements restants de llista2
        fusio.addAll(llista2.subList(j, llista2.size()));

        return fusio;
    }

    public static List<Integer> fusionarLlistesAmbIterador(List<Integer> lista1, List<Integer> lista2) {
        List<Integer> fusion = new ArrayList<>();
        Iterator<Integer> iter1 = lista1.iterator();
        Iterator<Integer> iter2 = lista2.iterator();
        Integer aux1 = null;
        Integer aux2 = null;

        // Obtindre el primer element de cada llista
        if (iter1.hasNext()) {
            aux1 = iter1.next();
        }
        if (iter2.hasNext()) {
            aux2 = iter2.next();
        }

        while (aux1 != null && aux2 != null) {
            if (aux1 <= aux2) {
                fusion.add(aux1);
                if (iter1.hasNext()) {
                    aux1 = iter1.next();
                } else {
                    aux1 = null;
                }
            } else {
                fusion.add(aux2);
                if (iter2.hasNext()) {
                    aux2 = iter2.next();
                } else {
                    aux2 = null;
                }
            }
        }

        // Afegir els elements restants de llista1
        while (aux1 != null) {
            fusion.add(aux1);
            if (iter1.hasNext()) {
                aux1 = iter1.next();
            } else {
                aux1 = null;
            }
        }

        // Afegir els elements restants de llista2
        while (aux2 != null) {
            fusion.add(aux2);
            if (iter2.hasNext()) {
                aux2 = iter2.next();
            } else {
                aux2 = null;
            }
        }

        return fusion;
    }


    public static void main(String[] args) {
        List<Integer> llista1 = List.of(1, 3, 5, 7, 9);
        List<Integer> llista2 = List.of(2, 4, 6, 8, 10);

        List<Integer> resultat1 = fusionarLlistes(llista1, llista2);
        System.out.println(resultat1);  // Imprimeix: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // Les llistes originals no han sigut modificades
        System.out.println(llista1);  // Imprimeix: [1, 3, 5, 7, 9]
        System.out.println(llista2);  // Imprimeix: [2, 4, 6, 8, 10]

        List<Integer> resultado2 = fusionarLlistesSenseIterador(llista1, llista2);
        System.out.println(resultado2);  // Imprimeix: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // Les llistes originals no han sigut modificades
        System.out.println(llista1);  // Imprimeix: [1, 3, 5, 7, 9]
        System.out.println(llista2);  // Imprimeix: [2, 4, 6, 8, 10]

        List<Integer> resultado3 = fusionarLlistesAmbIterador(llista1, llista2);
        System.out.println(resultado3);  // Imprimeix: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // Les llistes originals no han sigut modificades
        System.out.println(llista1);  // Imprimeix: [1, 3, 5, 7, 9]
        System.out.println(llista2);  // Imprimeix: [2, 4, 6, 8, 10]

    }
}
