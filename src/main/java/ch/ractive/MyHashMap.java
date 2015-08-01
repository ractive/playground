package ch.ractive;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class MyHashMap<K, V> {
	private final List<Entry<K, V>>[] buckets;
	private final int bucketSize;
	
	public MyHashMap(int bucketSize) {
		this.bucketSize = bucketSize;
		this.buckets = new List[bucketSize];
	}
	
	@Getter @Setter
	@RequiredArgsConstructor
	public static class Entry<K, V> {
		private final K key;
		private final V value;
	}
	
	public boolean put(K k, V v) {
		int bucket = k.hashCode() % bucketSize;
		if (buckets[bucket] == null) {
			List<Entry<K, V>> l = new LinkedList<Entry<K, V>>();
			l.add(new Entry<>(k, v));
			buckets[bucket] = l;
			return false;
		} else {
			List<Entry<K, V>> l = buckets[bucket];
			for(int i = 0; i < l.size(); ++i) {
				if (l.get(i).getKey().equals(k)) {
					l.set(i, new Entry<>(k, v));
					return true;
				}
			}
			l.add(new Entry<>(k, v));
			return false;
		}
	}
	
	public V get(K k) {
		int bucket = k.hashCode() % bucketSize;
		List<Entry<K, V>> l = buckets[bucket];
		if (l == null) {
			return null;
		}
		if (l.size() == 1) {
			return l.get(0).getValue();
		}
		for(Entry<K, V> entry : l) {
			if (entry.getKey().equals(k)) {
				return entry.getValue();
			}
		}
		return null;
	}
}
