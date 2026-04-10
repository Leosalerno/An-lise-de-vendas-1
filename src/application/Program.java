package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Sale> list = new ArrayList<>();
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				Integer month =Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total =Double.parseDouble(fields[4]);
				list.add(new Sale(month, year, seller, items, total));
				line = br.readLine();
			}
			
			System.out.println();
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");
			List<Sale> result = list.stream()
		    .filter(s -> s.getYear() == 2016)
		    .sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice()))
		    .limit(5)
		    .collect(Collectors.toList());
			
			result.forEach(System.out::println);
			
			
			Double sum = list.stream()
			.filter(s -> s.getSeller().equals("Logan"))
			.filter(s -> s.getMonth() == 1 || s.getMonth() == 7)
			.map(s -> s.getTotal())
			.reduce(0.0, (x,y) -> x + y);
			
			System.out.println();
			System.out.printf("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f%n", sum);
				
				
			
			
			
			
			
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		

		sc.close();
	}

}
 