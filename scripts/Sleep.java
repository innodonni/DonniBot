public class Sleep {
        public static void main(String[]args) {
                try {
                        Thread.sleep((long)(new Double(args[0]) * 1000));
                } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                }
        }
}