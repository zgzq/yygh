import my.cache.CacheManager;

public class Test {
	public static void main(String[] args) {
		CacheManager.set("session", "test", "111");
		
		 
		
		Object obj = new Object();
		synchronized (obj) {
			try {
				obj.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		
		
	}
}
