package com.distributedqueue.producer;

public interface Callback {
    void onCompletion(RecordMetadata metadata, Exception exception);
}
