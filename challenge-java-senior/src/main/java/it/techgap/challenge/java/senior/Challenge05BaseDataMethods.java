package it.techgap.challenge.java.senior;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import it.techgap.challenge.java.senior.beans.OneSecondWait;
import it.techgap.challenge.java.senior.beans.TimeBasedKVCache;
import it.techgap.challenge.java.senior.beans.ValueCounter;

public class Challenge05BaseDataMethods {

	static ExecutorService executor;
			
	public static void executeWait(OneSecondWait waitp, int times){
		executor = Executors.newFixedThreadPool(times);

		Runnable r = () -> {
			try {
				waitp.waitOneSecond();	
			} catch (InterruptedException ie) {
				
			}
		};
		for (int i = 0; i < times; i++) {
			executor.execute(r);;
		}

		try {
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println(e.getMessage());
		}
		finally {
		    if (!executor.isTerminated()) {
		        System.err.println("terminating...");
		    }
		    executor.shutdownNow();
		}
	}

	public static class myValueCounter<V> implements ValueCounter<Object> {

		private HashMap<String, AtomicInteger> h = new HashMap<String, AtomicInteger>();
		@Override
		public void addValue(Object value) {
			h.putIfAbsent((String)value, new AtomicInteger());
			h.get(value).incrementAndGet();
		}

		@Override
		public int howMany(Object value) {
			h.putIfAbsent((String)value, new AtomicInteger());
			return h.get(value).get();
		}	
	}
	
	public static <V> ValueCounter<V> newValueCounter(){
		return new myValueCounter();
	}
	
	public static class myTimeBasedCache<K,V> implements TimeBasedKVCache {
		private ConcurrentHashMap<Object, Integer> timeMap = new ConcurrentHashMap<Object, Integer>();
		private ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<Object>();
	    private long duration;
	    private TimeUnit tu;
	    private int maxSize;
	    
	    public myTimeBasedCache() {
	    	new CleanerThread().start();
	    }
	    
		@Override
		public void setMaximumElements(int elements) {
			maxSize = elements;
		}

		@Override
		public void setElementsTimeToLive(long duration, TimeUnit timeunit) {
			this.duration = duration;
			tu = timeunit;
		}

		@Override
		public Object getValue(Object key) {
			if (timeMap.containsKey(key)) {
				return timeMap.get(key);
			}
			return null;
		}

		@Override
		public void addValue(Object key, Object value) {
			if (queue.size() < maxSize) {
				queue.add(key);
				timeMap.put(key, (Integer)value);
			}
			else if (timeMap.containsKey(key)) {
				timeMap.put(key, (Integer)value);
			}
			else {
				Object k = queue.remove();
        		timeMap.remove(k);
				queue.add(key);
				timeMap.put(key, (Integer)value);
			}
		}
		
		class CleanerThread extends Thread {
	        @Override
	        public void run() {
	            while (true) {
	            	timeMap = new ConcurrentHashMap<Object, Integer>();
	            	queue = new ConcurrentLinkedQueue<Object>();
	                try {
	                    tu.sleep(duration);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }	
	}
	
	public static <K,V> TimeBasedKVCache<K,V> newTimeBasedKVCache(){
		return new myTimeBasedCache();
	}

}
