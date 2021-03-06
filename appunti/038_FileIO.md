# File Input/Output

### Creare text file:
```java
PrintWriter writer = new PrintWriter("nomefile.txt", "UTF-8");
writer.println("Prima riga");
writer.println("Seconda riga");
writer.close();
```

### Creare binary file:

```java
byte data[] = ...
FileOutputStream out = new FileOutputStream("nomefile");
out.write(data);
out.close();
```

---

### Java 7+: Files class:

### Creare text file:

```java
List<String> lines = Arrays.asList("Prima riga", "Seconda riga");
Path file = Paths.get("nomefile.txt");
Files.write(file, lines, StandardCharsets.UTF_8);
//Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
```

### Creare binary file:

```java
byte data[] = ...
Path file = Paths.get("nomefile");
Files.write(file, data);
//Files.write(file, data, StandardOpenOption.APPEND);
```

---

## in dettaglio

## Package java.io
Per eseguire l'elaborazione dei file in Java, è necessario importare il package java.io, che contiene le definizioni delle seguenti classi di flussi:

classe|descrizione
---|---
classe InputStream	| per input di byte da un file una per l'output
classe OutputStream	| per output di byte verso un file
classe Reader		| per input di caratteri da un file
classe Writer		| per output di caratteri verso un file

---
## Il flusso - Stream

Per prelevare informazioni da una fonte esterna (un file, una rete, ecc.), un programma deve aprire uno stream su essa e leggerne le informazioni in maniera sequenziale.

Allo stesso modo un programma può spedire a una destinazione esterna aprendo uno stream su essa e scrivendo le informazioni sequenzialmente.

---

## Character Stream

Gli stream che leggono o scrivono caratteri nei file sono detti character stream. I file che sono creati usando stream a caratteri sono detti file di testo.

**Reader** e **Writer** sono le due superclassi astratte per i character stream.
Queste due classi hanno la caratteristica di obbligare le sottoclassi a leggere e scrivere dividendo i dati in "pezzi" di 16 bit ognuno, quindi compatibili con il tipo char di Java.

---

## Byte Stream

Gli stream che leggono o scrivono byte (dati binari) nei file sono detti byte stream. I file che sono creati usando degli stream binari sono detti file binari.

**InputStream** e **OutputStream** sono le due superclassi astratte per i byte stream.

Queste due classi hanno la caratteristica di obbligare le sottoclassi a leggere e scrivere dividendo i dati in "pezzi" di 8 bit ognuno. Praticamente esistono gerarchie di classi parallele alle classi viste per i character stream.


I programmi dovrebbero utilizzare i byte stream per le informazioni di tipo non testuale, come, ad esempio, immagini o suoni.

---

## Le classi astratte di Reader e InputStream

Reader e InputStream definiscono gli stessi metodi, ma per differenti tipi di dati.
In particolare, Reader dichiara i metodi per leggere flussi di caratteri da una sorgente, mentre InputStream dichiara i metodi per leggere byte ed array di byte.


Il metodo read() della classe **Reader** si occupa di leggere flussi di caratteri da una sorgente specifica e restituisce:
* un int (da 0 a 65535) che rappresenta il carattere letto
* il valore -1 se il file è terminato

Il metodo read() della classe **InputStream** si occupa di leggere un byte alla volta da una sorgente specifica e restituisce:
un int (da 0 a 255) che rappresenta il byte letto
il valore -1 se il file è terminato

---

## Le classi astratte di Writer e OutputStream

  Il metodo write() della classe Writer si  occupa di scrivere flussi di caratteri in una destinazione specifica. Il carattere è passato come argomento di tipo int.
  Il metodo write() della classe InputStream si occupa di scrivere un byte alla volta. Il byte è passato come argomento di tipo int.

---

## IOException

l'I/O da file

* Richiede la gestione di eccezioni controllate
* La radice della gerarchia di queste eccezioni è IOExcetion

```java
import java.io.*;
...

public metodo() throws IOException {
File <inF> = new File ("<nameIn>");
FileReader <inR> = new FileReader (<inF>);
File <outF> = new File ("<nameOut>");
FileWriter <outW> = new FileWriter (<outF>);
int c;
while ((c = <inR>.read()) != -1)
<outW>.write (c);
<inR>.close ();
<outW>.close ();
}
```

---


* Chiusura degli stream
  * gli stream devono essere chiusi, oggi si può fare con il blocco try-with-resources
* Lettura da tastiera
  * Reader+BufferedReader
  * Scanner


---

#

## Object Stream: Serializzazione di oggetti

Tutte le classi che implementano l'interfaccia Serializable possono essere salvate su persistenza attraverso un ObjectOutputStream e caricate con un ObjectInputStream. Queste ultime sono classi che implementano le interfacce ObjectOutput e ObjectInput e consentono agli oggetti di essere letti o scritti in uno stream (possibilmente un file).

Scriviamo una classe Java  con un solo campo id ed i corrispondenti metodi getter e setter.

```java

    public class Base implements Serializable {
        private int id;
     
        public int getId() {
            return id;
        }
     
        public void setId(int id) {
            this.id = id;
        }
    }


```

Creiamo un'altra classe che estende la classe base (chiamata Figlia) aggiungendo un campo nome.

```java
    public class Figlia extends Base implements Serializable {
        private String nome;
     
        public void setNome(String nome) {
            this.nome = nome;
        }
        public String getNome() {
            return nome;
        }
    }


```

Scriviamo un blocco di codice di prova per serializzare e deserializzare un oggetto (usando le classi descritte all'inizio di questo paragrafo).

```java
    public static void main (String[] args) {
    Figlia f = new Figlia();
    f.setId(123);
    f.setNome("ABC");

    System.out.println(f.getId() + " " + f.getNome());

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bos);
    out.writeObject(f);
    out.close();

    ObjectInputStream in = new ObjectInputStream(
        new ByteArrayInputStream(bos.toByteArray()));
    f = (Figlia) in.readObject();
    in.close();

    System.out.println(f.getId() + " " + f.getNome());


```


---

## snippets


### I costrutti

`File <inF> = new File ("<nameIn>");`
`FileReader <inR> = new FileReader (inF);`
`File <outF> = new File ("<nameOut>");`
`FileWriter <outW> = new FileWriter (outF);`

possono essere scritti come segue

`FileReader <inR> = new FileReader("<nameIn>");`
`FileWriter <outW> = new FileWriter("<nameOut>");`

---
### Metodi generali

  * Chiusura del file
`public void close () throws IOException`
  * Scrittura dei caratteri nel "buffer"
`public void flush () throw IOEXception`

---
### Modalità di lettura

  * Il metodo read restituisce (-1) al raggiungimento di EOF
  * Lettura di caratteri singoli
`public int read () throws IOException`
  * Lettura di un numero di caratteri pari alla lunghezza di un vettori di caratteri
`public int read (char [] charArray) throws IOException`

---
### Modalità di scrittura

  * Scrittura di caratteri singoli
public void write (int c) throws
IOException
  * Scrittura di un vettore di caratteri
public void write (char [] charArray)
throws IOException
  * Scrittura parziale di un vettore di caratteri
public void write (char [] charArray, int
da, int a) throws IOException
  * Scrittura di una stringa
public void write (String s) throws
IOException
  * Scrittura parziale di una stringa
public void write (String s, int da, int a)
throws IOException

---

## I/O orientato alle righe

### La lettura di righe intere è permessa
dalla classe BufferedReader

---
### I metodi di tale classe

* Sono analoghi a quelli della classe Writer (metodo write)
* L'I/O bufferizzato è più efficiente
  * Lettura di una riga intera
  * (conclusa dai caratteri `\n = linefeed`, `\r = carriage return`) 

```java
public String readLine () throws IOException
//Ritorna null al raggiungimento di EOF
import java.io.*;
...
public ...throws IOException {
FileReader <inR> = new FileReader ("<nameIn>");
BufferedReader <inB> = new BufferedReader (<inR>);
FileWriter <outF> = new FileWriter ("<nameOut>");
BufferedWriter <outB> = new BufferedReader (<outF>);
String str;
...
while ((str = <inB>.readLine ()) != null)
<outB>.write (str);
...
<inB>.close ();
<outB>.close ();
}
```
---

### I costrutti

* `FileReader <inR> = new FileReader("<nameIn>");`
* `BufferedReader <inB> = new BufferedReader(<inR>);`

* `FileWriter <outF> = new FileWriter("<nameOut>");`
* `BufferedWriter <outW> = new BufferedWriter(outF);`

possono essere scritti come

* `BufferedReader <inB> = new BufferedReader(new FileReader ("<nameIn>"));`
* `BufferedWriter <outW> = new BufferedWriter(new FileWriter ("<nameOut>"));`

## La classe file

### La classe file permette controlli su
* File
* Directory

---
### Definisce/rappresenta nomi/path
  * Crea un oggetto di tipo file
public File (String pathName)
e.g.,
`File <x> = new File ("<nameIn>");`
`File <y> = new File ("c:\dir1\dir2", "<nameOut>");`

---
### Check file/direttory
  * Verifica che l'oggetto esista
`public boolean exists()`
e.g., if (<x>.exists ()) ...
  * Verifica se l'oggetto è un file
`public boolean isFile()`
e.g., if (<x>.isFile ()) ...
  * Verifica se l'oggetto è una directory
`public boolean isDirectory()`
  * Restituisce il nome del file o directory
`public String getName()`
  * Restituisce la stringa che descrive il path
completo del file
`public String getAbsolutePath ()`
  * Restituisce dimensione in byte
`public long length ()`
  * Controlla se il file può essere scritto
`public boolean canWrite()`
  * Controlla se il file può essere letto
`public boolean canRead ()`

---
### Contenuto di una directory
  * Restituisce l'array di stringhe contenenti I nomi
dei file/directory nel direttorio corrente
`String [] list ()`
e.g.,
`String fileName[] = <x>.list ();`
`for (int i=0; i<fileName.length; i++)`
`System.out.println (filename[i]);`

