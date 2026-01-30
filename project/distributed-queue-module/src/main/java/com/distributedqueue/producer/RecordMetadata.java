package com.distributedqueue.producer;

public record RecordMetadata(String topic, int partition, long offset) {
}
