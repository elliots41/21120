package cs21120.wordprediction;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * Provides a list-based implementation of the Map abstract data type.
 * @see Lecture 12
 * 
 * @author      chz8
 */

public class ListMap<K, V> implements Map<K, V> {

	/**
	 * The list of Map entries.
	 */
	private List<Map.Entry<K, V>> entries;

	/**
	 * Inner class for the entries of the Map.
	 */
	private static class ListEntry<K, V> implements Entry<K, V> {

		/**
		 * The key of the Map entry.
		 */
		private K key;
		
		/**
		 * The value of the Map entry.
		 */
		private V value;

		/**
		 * Constructor with key and value as parameters.
		 *
		 * @param	key		The key of new entry.  
		 * @param	value	The value of the new entry.
		 */
		public ListEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		/**
		 * Returns the key of the entry.
		 *
		 * @return The key of the entry.
		 */

		@Override
		public K getKey() {
			return key;
		}

		/**
		 * Returns the value of the entry.
		 *
		 * @return The value of the entry.
		 */
		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Changes the value of the entry and returns its old value.
		 *
		 * @param  value	The new value of the entry.
		 * @return The old value of the entry.
		 */
		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}
	}

	/**
	 * Standard constructor for the list-based Map.
	 * 
	 * Creates a new linked list for the entries.
	 */
	public ListMap() {
		entries = new LinkedList<Entry<K, V>>();
	}

	/**
	 * Returns the number of entries in the Map.
	 *
	 * @return The number of entries in the Map.
	 */
	@Override
	public int size() {
		return entries.size();
	}

	/**
	 * Returns a boolean indicating whether the Map is empty.
	 *
	 * @return False if the Map contains at least one item. Otherwise true.
	 */
	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	/**
	 * Checks if the Map contains an entry with a given key.
	 *
	 * @param	key	The key to be looked up in the Map.
	 * @return  True if the key exists in the Map; False otherwise. 
	 */
	@Override
	public boolean containsKey(Object key) {
		for (Entry<K, V> entry : entries) {
			if (entry.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the Map contains an entry with a given value.
	 *
	 * @param	value	The value to be looked up in the Map.
	 * @return  True if the value exists in the Map; False otherwise. 
	 */
	@Override
	public boolean containsValue(Object value) {
		for (Entry<K, V> entry : entries) {
			if (entry.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the Map contains an entry with a given key.
	 *
	 * @param	key	The key to be looked up in the Map.
	 * @return  The value associated with the given key if it exists in the Map.
	 * 			Otherwise null. 
	 */
	@Override
	public V get(Object key) {
		for (Entry<K, V> entry : entries) {
			if (entry.getKey().equals(key)) {
				entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Inserts a new entry into the Map.
	 *
	 * @param	key		The key of the new entry.
	 * @param	value	The value of the new entry.
	 * @return	Null if the key is not already in the Map; otherwise the old value. 
	 */
	@Override
	public V put(K key, V value) {
		for (Entry<K, V> entry : entries) {
			if (entry.getKey().equals(key)) {
				// update existing entry and return old value
						return entry.setValue(value);
			}
		}
		//key not found
		Entry<K, V> entry = new ListEntry<K, V>(key, value);
		entries.add(entry);
		return null;
	}

	/**
	 * Removes the entry with the given key from the Map and returns its value if it exists.
	 *
	 * @param	key	The key of the entry to be removed.
	 * @return	The value of the entry if one with the given key exists; otherwise null.
	 */
	@Override
	public V remove(Object key) {
		ListIterator<Entry<K, V>> listIterator = entries.listIterator();
		while (listIterator.hasNext()) {
			Entry<K, V> entry = listIterator.next();
			if (entry.getKey().equals(key)) {
				listIterator.remove();
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Inserts entries from another Map.
	 *
	 * @param	m	Another map that contains the entries to be added.
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Entry<? extends K, ? extends V> e : m.entrySet())
			put(e.getKey(), e.getValue());
	}

	/**
	 * Removes all entries from the Map.
	 */
	@Override
	public void clear() {
		entries.clear();
	}
	
	/**
	 * Returns an iterable set of all keys in the Map.
	 *
	 * @return	A set containing all the keys in the Map. 
	 */
	@Override
	public Set<K> keySet() {
		Set<K> keySet = new HashSet<K>();
		for (Entry<K, V> entry : entries) {
			keySet.add(entry.getKey());
		}
		return keySet;
	}

	/**
	 * Returns an iterable collection of all values in the Map (with repetitions).
	 *
	 * @return	A collection containing all the value in the Map. 
	 */
	@Override
	public Collection<V> values() {
		Collection<V> values = new LinkedList<V>();
		for (Entry<K, V> entry : entries) {
			values.add(entry.getValue());
		}
		return values;
	}

	/**
	 * Returns an iterable set of all entries in the Map.
	 * 
	 * @return	A set containing all the entries in the Map. 
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> entrySet = new HashSet<Entry<K, V>>();
		for (Entry<K, V> entry : entries) {
			entrySet.add(entry);
		}
		return entrySet;
	}

}
