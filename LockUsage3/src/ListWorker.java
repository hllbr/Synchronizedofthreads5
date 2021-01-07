import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ListWorker {
    
    Random random = new Random();
    ArrayList<Integer> array1 = new ArrayList<Integer>();
    ArrayList<Integer> array2 = new ArrayList<Integer>();
    /*
    bize kurmak istediğimz yapıda 2 anahtar lazım bu anahtarları biz her hangi bir veri tipinden oluşturabiliriz.
    
    ister kendi oluşturduğumuz ogrenci veri tipinden olsun ister object veri tipinden olsun ...
    
    biz hernahgi veri tipinden  objemizi lock olarak anahtar olarak kullanabiliriz....
    */
    
    private Object lock1 = new Object();//birinci anahtarımız...
    
    private Object lock2 = new Object();//ikinci anahtarımız...
    
    //lock1'i bizim list1 değerekle metoduna özgü yazmamız gerek.synchronized bir kod bloku gerekliliğimiz var.
    public void degerEkleListe1(){
        synchronized (lock1){//bu yapıyla birlikte metodu synchronized yapmadık yapının içerisindeki işlemleri synchronized yaptık.
            //anahtar metoda özgü oldu.bu yapı sayesinde
        try {
            
            Thread.sleep(1);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
            array1.add(random.nextInt(100));
    }
    }

    public void degerEkleListe2(){
        
        synchronized(lock2){
     
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
            array2.add(random.nextInt(100));
                   
        }
    }
    public void degerAta(){
        for(int i = 0 ;i<1000;i++){
        degerEkleListe1();
        degerEkleListe2();
        }
       
     
    }
   
    public void calıstır(){
       
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
              degerAta();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               degerAta();
            }
        });
        long baslangıc = System.currentTimeMillis();
        
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Array1 size = "+array1.size()+" array2 size = "+array2.size());
        long bitis = System.currentTimeMillis();
        
        System.out.println("İşlemler Süresince Geçen Süre : "+(bitis-baslangıc)+" ms.");
      
        
    }
    
}

