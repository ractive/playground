package ch.ractive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class MyHashMap<K, V> {
	private final List<List<Entry<K, V>>> buckets;
	private final int bucketSize;
	
	public MyHashMap(int bucketSize) {
		this.bucketSize = bucketSize;
		this.buckets = new ArrayList<>(bucketSize);
		while (buckets.size() < bucketSize) {
			buckets.add(new LinkedList<Entry<K, V>>());
		}
	}
	
	@Getter @Setter
	@RequiredArgsConstructor
	public static class Entry<K, V> {
		private final K key;
		private final V value;
	}
	public boolean put(K k, V v) {
		List<Entry<K, V>> bucket = buckets.get(k.hashCode() % bucketSize);
		if (bucket.isEmpty()) {
			bucket.add(new Entry<>(k, v));
			return false;
		} else {
			ListIterator<Entry<K, V>> listIterator = bucket.listIterator();
			while (listIterator.hasNext()) {
				Entry<K, V> e = listIterator.next();
				if (e.getKey().equals(k)) {
					listIterator.set(new Entry<>(k, v));
					return true;
				}
			}

			bucket.add(new Entry<>(k, v));
			return false;
		}
	}
	
	public V get(K k) {
		int bucket = k.hashCode() % bucketSize;
		List<Entry<K, V>> l = buckets.get(bucket);
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
