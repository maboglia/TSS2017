import java.util.ArrayList;
import gente.Persona;

public class ArrayListToArray {

	public static void main(String[] args) {
		
		ArrayList lista = new ArrayList();
		lista.add(new Persona ("Gabriele", "Pippo", 20));
		lista.add(new Persona ("Marco", "Tozzi", 21));
		lista.add(new Persona ("Claudio", "Pluto", 23));
		
		
		Persona[] array = new Persona[lista.size()];
		
		for (int i = 0; i < lista.size(); i++) {
			array[i] = (Persona) lista.get(i);
		}

		for (Persona persona : array) {
			System.out.println(persona);
		}
		
	}

}
