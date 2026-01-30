package com.distributedqueue.producer;

import java.io.Closeable;
import java.util.concurrent.Future;

public interface Producer<K, V> extends Closeable {
    Future<RecordMetadata> send(ProducerRecord<K, V> record);

    Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback);

    void close();
}
