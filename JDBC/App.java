public class App {
    public static void main(String[] args)  {
        model m = new model();
        views c = new views();
        new controller(m,c);

      
    }
}
