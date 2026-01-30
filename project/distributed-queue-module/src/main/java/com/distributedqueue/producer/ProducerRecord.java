package com.distributedqueue.producer;

public class ProducerRecord<K, V> {
    private final String topic;
    private final K key;
    private final V value;

    public ProducerRecord(String topic, K key, V value) {
        this.topic = topic;
        this.key = key;
        this.value = value;
    }

    public String topic() {
        return topic;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }
}
