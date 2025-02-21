<h1>Distributed Persistence Based Queue</h1>
<h2>Introduction</h2>
<p>This project aims to create a distributed persistence based queue in Java, which is similar to Apache Kafka but with less functionality. The queue should support multiple producers and consumers, and should be persistent to ensure data durability.</p>

<h2>Functional Requirements</h2>
<ul>
 <li>Producers should be able to publish messages to the queue</li>
 <li>Consumers should be able to consume messages from the queue</li>
 <li>Messages should be persisted to disk to ensure data durability</li>
 <li>Consumers should be able to consume messages in the order they were published</li>
 <li>Consumers should be able to seek to a specific position in the queue</li>
 <li>The queue should support multiple producers and consumers</li>
 <li>The queue should support dead letter queue implementation for failed delivery</li>
 <li>The queue should support back pressure mechanism for producers</li>
</ul>

<h2>Non-Functional Requirements</h2>
<ul>
 <li>The queue should be scalable and able to handle high message rates</li>
 <li>The queue should have low latency for message production and consumption</li>
 <li>The queue should be fault-tolerant and able to handle node failures</li>
 <li>The queue should be easy to use and have a simple API</li>
 <li>The queue should be configurable and extensible</li>
</ul>

<h2>Project Structure</h2>
<ul>
 <li><code>com.dpbq</code> package should contain all the classes and interfaces for the queue implementation</li>
 <li><code>com.dpbq.config</code> package should contain all the configuration classes and interfaces</li>
 <li><code>com.dpbq.consumer</code> package should contain all the consumer classes and interfaces</li>
 <li><code>com.dpbq.producer</code> package should contain all the producer classes and interfaces</li>
 <li><code>com.dpbq.util</code> package should contain all the utility classes and interfaces</li>
</ul>

<h2>Build and Dependencies</h2>
<ul>
 <li>Java 17 is required to build and run the project</li>
 <li>Only the standard Java library is used, no external dependencies are required</li>
</ul>



