public class HashTable<K, V> { // Generics для любых видов key, value
    // мой HashTable хранит массив Node-ов LinkedList-а (Chaining метод), чтобы избежать коллизий.
    private final int size = 256; // поскольку каждый LinkedList может бесконечно расширяться, не вижу смысла расширять сам массив, и поставил final
    private Node<K, V>[] hashtable = new Node[size];

    private int hashFunction(K key) { 
        if (key instanceof String) { // на случай если ключ окажется строкой
            int hash = 0;
            for (int i = 0; i < ((String)key).length(); ++ i)
                hash += ((String)key).charAt(i);
            return hash % size;
        }
        else // на случай если ключ окажется целым числом
            return (Integer)key % size;
    }

    public void put(K key, V value) {
        int hash = hashFunction(key);
        if (hashtable[hash] == null) {
            hashtable[hash] = new Node<K, V>(key, value);
            return;
        }
        Node<K, V> node = hashtable[hash];
        while(node.next != null && !key.equals(node.key))
            node = node.next;
        if (key.equals(node.key))
            node.value = value;
        else
            node.next = new Node<K, V>(key, value);
    }
    
    public void remove(K key) {
        int hash = hashFunction(key);
        if (hashtable[hash] == null)
            return;
        Node<K, V> node = hashtable[hash];
        if (key.equals(node.key)) {
            hashtable[hash] = node.next;
            return;
        }
        while(node.next != null && !key.equals(node.next.key))
            node = node.next;
        if (node.next == null)
            return;
        if (node.next.next != null)
            node.next = node.next.next;
        else
            node.next = null;
    }

    public V get(K key) {
        int hash = hashFunction(key);
        if (hashtable[hash] == null)
            return null;
        Node<K, V> node = hashtable[hash];
        while(node.next != null && key.equals(node.key))
            node = node.next;
        return key.equals(node.key) ? node.value : null;
    }

    public boolean containsKey(K key) {
        int hash = hashFunction(key);
        if (hashtable[hash] == null)
            return false;
        Node<K, V> node = hashtable[hash];
        if (key.equals(node.key))
            return true;
        while(node.next != null && key.equals(node.key)) {
            System.out.print(node.key + " ");
            node = node.next;
        }
        return key.equals(node.key);
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < size; ++ i) {
            Node<K, V> node = hashtable[i];
            if (hashtable[i] == null)
                continue;
            if (value.equals(node.value))
                return true;
            while(node.next != null && !value.equals(node.value))
                node = node.next;
            if (node.value == value)
                return true;
        }
        return false;
    }
}
