import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestorClub {
    private Map<String, Soci> socis;
    private String fitxerDades;

    public GestorClub(String fitxerDades) {
        this.socis = new HashMap<>();
        this.fitxerDades = fitxerDades;
        carregarDades();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcio;

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Alta soci");
            System.out.println("2. Baixa soci");
            System.out.println("3. Modificació soci");
            System.out.println("4. Llistar socis per àlies");
            System.out.println("5. Llistar socis per antiguitat");
            System.out.println("6. Llistar els socis amb alta anterior a un any determinat");
            System.out.println("0. Sortir");
            System.out.print("Introduïu una opció: ");
            opcio = scanner.nextInt();
            scanner.nextLine(); // Consumir el salt de línia

            switch (opcio) {
                case 1:
                    altaSoci();
                    break;
                case 2:
                    baixaSoci();
                    break;
                case 3:
                    modificacioSoci();
                    break;
                case 4:
                    llistarSocisPerAlies();
                    break;
                case 5:
                    llistarSocisPerAntiguitat();
                    break;
                case 6:
                    llistarSocisPerAltaAnterior();
                    break;
                case 0:
                    guardarDades();
                    System.out.println("Fins a la pròxima!");
                    break;
                default:
                    System.out.println("Opció invàlida. Intenteu-ho de nou.");
                    break;
            }
        } while (opcio != 0);
    }

    private void altaSoci() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduïu l'àlies del soci: ");
        String alias = scanner.nextLine();

        if (socis.containsKey(alias)) {
            System.out.println("L'àlies ja està en ús. Intenteu-ho de nou.");
            return;
        }

        System.out.print("Introduïu el nom del soci: ");
        String nom = scanner.nextLine();
        System.out.print("Introduïu la data d'ingrés del soci (format: dd/mm/aaaa): ");
        String dataIngresStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataIngres = LocalDate.parse(dataIngresStr, formatter);
            Soci soci = new Soci(alias, nom, dataIngres);
            socis.put(alias, soci);
            System.out.println("Soci afegit amb èxit.");
        } catch (Exception e) {
            System.out.println("Error en afegir el soci: " + e.getMessage());
        }
    }

    private void baixaSoci() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduïu l'àlies del soci a donar de baixa: ");
        String alias = scanner.nextLine();

        if (socis.containsKey(alias)) {
            socis.remove(alias);
            System.out.println("Soci eliminat amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap soci amb l'àlies introduït.");
        }
    }

    private void modificacioSoci() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduïu l'àlies del soci a modificar: ");
        String alias = scanner.nextLine();

        if (socis.containsKey(alias)) {
            Soci soci = socis.get(alias);

            System.out.print("Introduïu el nou nom del soci: ");
            String nouNom = scanner.nextLine();
            System.out.print("Introduïu la nova data d'ingrés del soci (format: dd/MM/yyyy): ");
            String novaDataIngresStr = scanner.nextLine();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate novaDataIngres = LocalDate.parse(novaDataIngresStr, formatter);
                soci.setNom(nouNom);
                soci.setDataIngres(novaDataIngres);
                System.out.println("Soci modificat amb èxit.");
            } catch (Exception e) {
                System.out.println("Error en modificar el soci: " + e.getMessage());
            }
        } else {
            System.out.println("No s'ha trobat cap soci amb l'àlies introduït.");
        }
    }

    private void llistarSocisPerAlies() {
        System.out.println("\n--- Llistat de socis per àlies ---");
        List<String> aliesOrdenats = new ArrayList<>(socis.keySet());
        Collections.sort(aliesOrdenats);

        for (String alias : aliesOrdenats) {
            Soci soci = socis.get(alias);
            System.out.println("Àlies: " + soci.getAlias() + ", Nom: " + soci.getNom() +
                    ", Data d'ingrés: " + soci.getDataIngres());
        }
    }

    private void llistarSocisPerAntiguitat() {
        System.out.println("\n--- Llistat de socis per antiguitat ---");
        List<Soci> socisOrdenats = new ArrayList<>(socis.values());
        socisOrdenats.sort(Comparator.naturalOrder());

        for (Soci soci : socisOrdenats) {
            System.out.println("Àlies: " + soci.getAlias() + ", Nom: " + soci.getNom() +
                    ", Data d'ingrés: " + soci.getDataIngres());
        }
    }

    private void llistarSocisPerAltaAnterior() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduïu l'any límit per llistar els socis (format: aaaa): ");
        int anyLimit = scanner.nextInt();
        scanner.nextLine(); // Consumir el salt de línia

        System.out.println("\n--- Llistat de socis amb alta anterior a " + anyLimit + " ---");

        for (Soci soci : socis.values()) {
            int anyIngres = soci.getDataIngres().getYear();

            if (anyIngres < anyLimit) {
                System.out.println("Àlies: " + soci.getAlias() + ", Nom: " + soci.getNom() +
                        ", Data d'ingrés: " + soci.getDataIngres());
            }
        }
    }

    private void carregarDades() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fitxerDades))) {
            socis = (Map<String, Soci>) in.readObject();
            System.out.println("Dades carregades correctament.");
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer de dades.");
        } catch (Exception e) {
            System.out.println("Error en carregar les dades: " + e.getMessage());
        }
    }

    private void guardarDades() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fitxerDades))) {
            out.writeObject(socis);
            System.out.println("Dades guardades correctament.");
        } catch (Exception e) {
            System.out.println("Error en guardar les dades: " + e.getMessage());
        }
    }
}
