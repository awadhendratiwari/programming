package com.distributedqueue.producer;

import org.junit.jupiter.api.Test;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class ProducerTest {

    @Test
    public void testSendMessage() throws ExecutionException, InterruptedException {
        Producer<String, String> producer = new LiteProducer<>();
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key", "value");

        Future<RecordMetadata> future = producer.send(record);
        RecordMetadata metadata = future.get();

        assertNotNull(metadata);
        assertEquals("my-topic", metadata.topic());
        producer.close();
    }

    @Test
    public void testSendMessageWithCallback() throws ExecutionException, InterruptedException {
        Producer<String, String> producer = new LiteProducer<>();
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key", "value");

        Future<RecordMetadata> future = producer.send(record, (metadata, exception) -> {
            assertNotNull(metadata);
            assertNull(exception);
            assertEquals("my-topic", metadata.topic());
        });

        future.get();
        producer.close();
    }

    @Test
    public void testMultiThreadedProduction() throws InterruptedException, ExecutionException {
        int threadCount = 10;
        int messagesPerThread = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Producer<String, String> producer = new LiteProducer<>();
        List<Future<RecordMetadata>> futures = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < messagesPerThread; j++) {
                    String topic = "topic-" + random.nextInt(3); // Randomly choose between 3 topics
                    ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key-" + j, "value-" + j);
                    synchronized (futures) {
                        futures.add(producer.send(record));
                    }
                }
            });
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(10, TimeUnit.SECONDS));

        assertEquals(threadCount * messagesPerThread, futures.size());

        for (Future<RecordMetadata> future : futures) {
            RecordMetadata metadata = future.get();
            assertNotNull(metadata);
            assertTrue(metadata.topic().startsWith("topic-"));
        }

        producer.close();
    }
}
