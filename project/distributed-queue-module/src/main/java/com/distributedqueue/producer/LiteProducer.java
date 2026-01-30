package com.distributedqueue.producer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class LiteProducer<K, V> implements Producer<K, V> {

    @Override
    public Future<RecordMetadata> send(ProducerRecord<K, V> record) {
        return send(record, null);
    }

    @Override
    public Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback) {
        CompletableFuture<RecordMetadata> future = new CompletableFuture<>();

        // Simulating async processing
        CompletableFuture.runAsync(() -> {
            try {
                // Mock metadata
                RecordMetadata metadata = new RecordMetadata(record.topic(), 0, System.currentTimeMillis());

                if (callback != null) {
                    callback.onCompletion(metadata, null);
                }
                future.complete(metadata);
            } catch (Exception e) {
                if (callback != null) {
                    callback.onCompletion(null, e);
                }
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    @Override
    public void close() {
        // Cleanup resources if any
    }
}
