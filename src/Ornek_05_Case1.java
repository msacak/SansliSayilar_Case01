import java.util.*;
import java.util.stream.Collectors;

public class Ornek_05_Case1 {
    static Scanner sc = new Scanner(System.in);
    static Map<Integer,Integer> randomNums = new HashMap<>();

    public static void main(String[] args) {

        generateRandomNums(randomNums); // Rastgele sayilar uretip map'e aktarma.
        List<Integer> numsList = getNumbersList();// Mapteki degerleri adet tekrari kadar listeye aktarma.
        List<Integer> luckyNumbersList =  getLuckyNumbers(numsList); // 10 adet şanslı sayı listesi.
        randomNumberOperations(luckyNumbersList); //Sansli sayi menusu


    }
    private static List<Integer> getNumbersList(){
        List<Integer> numsList = new ArrayList<>();
        for(Map.Entry<Integer,Integer> item : randomNums.entrySet()){
            for(int i =0;i<item.getValue();i++){
                numsList.add(item.getKey()); //2-Liste olusturma, mapteki sayilar tekrar sayisi kadar listeye eklenecek.
            }
        }
        return numsList;
    }
    public static void listAllRandomNums(Map<Integer,Integer> map){ //Tum random sayilarini ve adetlerini gosterir.
        map.forEach((k,v)-> System.out.println(k+" = "+v+" adet"));
    }

    public static void generateRandomNums(Map<Integer,Integer> map){
        Random random = new Random();
        for(int i =0;i<10_000;i++){
            int num = random.nextInt(0,101);
            map.put(num, map.getOrDefault(num, 0)+1);

        }
    }
    public static List<Integer> getLuckyNumbers(List<Integer> list){
        Random random = new Random();
        List<Integer> newList = new ArrayList<>();
        while(newList.size()<10){
            int randomNumIndex = random.nextInt(0,list.size());
            if(!newList.contains(list.get(randomNumIndex))){ //Aynı sayı eklememesi icin.
                newList.add(list.get(randomNumIndex));
            }

        }
        return newList;
    }

    public static void randomNumberOperations(List<Integer> luckyNumberList){
        while(true){
            System.out.println("\n--- SANSLI SAYI ISLEMLERI ---");
            System.out.println("1- Sansli sayilari goruntule");
            System.out.println("2- Sansli numaralardan 50'den buyuk olanlari goruntule.");
            System.out.println("3- 50'den buyuk sayilari yeni bir listeye yaz.");
            System.out.println("4- Tum sansli numaraları topla");
            System.out.println("5- 50'den buyuk olan sansli numarali topla");
            System.out.println("6- Uretilen tum rastgele sayilari ve listedeki tekrar adetlerini goruntule");
            System.out.println("7- Uretilen tum rastgele sayilari ve listedeki tekrar adetlerini detayli goruntule");
            System.out.println("0- Cikis yap");
            System.out.print("Seciminiz: ");
            int secim = sc.nextInt();
            sc.nextLine();
            switch(secim){
                case 1:
                    System.out.println("-- SANSLI SAYILAR --");
                    System.out.println(luckyNumberList);
                    break;
                case 2: listAllLuckyNumbersGreaterThan50(luckyNumberList);
                break;
                case 3: List<Integer> luckyNumbersListGreaterThan50 = makeNewListFromLuckyNumbersGreaterThan50(luckyNumberList);
                break;
                case 4: sumAllLuckNumbers(luckyNumberList);
                break;
                case 5: sumLuckyNumbersGreaterThan50(luckyNumberList);
                break;
                case 6:
                    System.out.println(randomNums);
                    break;
                case 7: listAllRandomNums(randomNums);
                break;
                case 0:
                    System.out.println("Cikis yapiyorsunuz, gule gule.");
                    return;
                default:
                    System.out.println("Lütfen belirtilen aralikta secim yapin.");
                    break;
            }
        }


    }

    private static void sumLuckyNumbersGreaterThan50(List<Integer> luckyNumberList) {
        Optional<Integer> reduce = luckyNumberList.stream().filter(p -> p > 50).reduce((sum, x) -> sum + x);
        reduce.ifPresentOrElse(p-> System.out.println("50'den buyuk sansli sayin toplami = "+p), ()-> System.out.println("No lucky numbers greater than 50 found on list"));
    }

    private static void sumAllLuckNumbers(List<Integer> luckyNumberList) {
        Optional<Integer> reduce = luckyNumberList.stream().reduce((sum, i) -> sum + i);
        reduce.ifPresentOrElse(p-> System.out.println("Sansli sayilarin toplami = "+p),()-> System.out.println("No lucky numbers found on list. "));
    }

    private static List<Integer> makeNewListFromLuckyNumbersGreaterThan50(List<Integer> luckyNumberList) { //50'den buyuk sansli sayilardan yeni bir liste olusturur.
        return luckyNumberList.stream().filter(num -> num > 50).collect(Collectors.toList());
    }

    private static void listAllLuckyNumbersGreaterThan50(List<Integer> luckyNumberList) {
        System.out.println("50'den büyük sansli sayilar:");
        luckyNumberList.stream().filter(num->num>50).forEach(System.out::println);
    }


}
