package b_s1_d5;


import java.util.Scanner;

// interfaccia
interface Riproducibile {
    void play();
}

// classe abs generale
abstract class ElementoMultimediale {
    protected String titolo;

    public ElementoMultimediale(String titolo) {
        this.titolo = titolo;
    }
}

// Classe abs elems con vol
abstract class ElementoConVolume extends ElementoMultimediale implements Riproducibile {
    protected int durata;
    protected int volume;

    public ElementoConVolume(String titolo, int durata, int volume) {
        super(titolo);
        this.durata = durata;
        this.volume = volume;
    }

    public void alzaVolume() {
        volume++;
    }

    public void abbassaVolume() {
        if (volume > 0) volume--;
    }
}

// x audio
class RegistrazioneAudio extends ElementoConVolume {

    public RegistrazioneAudio(String titolo, int durata, int volume) {
        super(titolo, durata, volume);
    }


    public void play() {
        for (int i = 0; i < durata; i++) {
            System.out.println(titolo + " " + "!".repeat(volume));
        }
    }
}

// x video
class Video extends ElementoConVolume {
    private int luminosita;

    public Video(String titolo, int durata, int volume, int luminosita) {
        super(titolo, durata, volume);
        this.luminosita = luminosita;
    }

    public void aumentaLuminosita() {
        luminosita++;
    }

    public void diminuisciLuminosita() {
        if (luminosita > 0) luminosita--;
    }


    public void play() {
        for (int i = 0; i < durata; i++) {
            System.out.println(titolo + " " + "!".repeat(volume) + "*".repeat(luminosita));
        }
    }
}

// x img
class Immagine extends ElementoMultimediale {
    private int luminosita;

    public Immagine(String titolo, int luminosita) {
        super(titolo);
        this.luminosita = luminosita;
    }

    public void aumentaLuminosita() {
        luminosita++;
    }

    public void diminuisciLuminosita() {
        if (luminosita > 0) luminosita--;
    }

    public void show() {
        System.out.println(titolo + " " + "*".repeat(luminosita));
    }
}


public class LettoreMultimediale {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElementoMultimediale[] elementi = new ElementoMultimediale[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Inserisci tipo elemento (1=Audio, 2=Video, 3=Immagine):");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Titolo: ");
            String titolo = scanner.nextLine();

            switch (tipo) {
                case 1 -> {
                    System.out.print("Durata: ");
                    int durata = scanner.nextInt();
                    System.out.print("Volume: ");
                    int volume = scanner.nextInt();
                    elementi[i] = new RegistrazioneAudio(titolo, durata, volume);
                }
                case 2 -> {
                    System.out.print("Durata: ");
                    int durata = scanner.nextInt();
                    System.out.print("Volume: ");
                    int volume = scanner.nextInt();
                    System.out.print("Luminosità: ");
                    int luminosita = scanner.nextInt();
                    elementi[i] = new Video(titolo, durata, volume, luminosita);
                }
                case 3 -> {
                    System.out.print("Luminosità: ");
                    int luminosita = scanner.nextInt();
                    elementi[i] = new Immagine(titolo, luminosita);
                }
                default -> {
                    System.out.println("Tipo non valido. Riprova.");
                    i--;
                }
            }
        }

        int scelta;
        do {
            System.out.println("Scegli un elemento da eseguire (1-5, 0 per uscire):");
            scelta = scanner.nextInt();
            if (scelta >= 1 && scelta <= 5) {
                ElementoMultimediale elem = elementi[scelta - 1];
                if (elem instanceof Immagine img) {
                    img.show();
                } else if (elem instanceof Riproducibile r) {
                    r.play();
                }
            }
        } while (scelta != 0);

        scanner.close();
    }
}
