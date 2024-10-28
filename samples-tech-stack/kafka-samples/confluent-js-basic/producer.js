const Kafka = require('@confluentinc/kafka-javascript');

async function producerStart() {
    client = new Kafka.KafkaClient();
    producer = new Kafka.Producer(this.client)

/*     const kafka = new Kafka.Client({
        kafkaJS: {
            ssl: true,
            sasl: {
                mechanism: 'plain',
                username: 'payments@rameshv.me',
                password: 'Rvur2024*',
            },
        }
    }); */

    const producer = kafka.producer();

    await producer.connect();

    console.log("Connected successfully");

    const res = []
    for (let i = 0; i < 50; i++) {
        res.push(producer.send({
            topic: 'test-topic',
            messages: [
                { value: 'v222', partition: 0 },
                { value: 'v11', partition: 0, key: 'x' },
            ]
        }));
    }
    await Promise.all(res);

    await producer.disconnect();

    console.log("Disconnected successfully");
}

producerStart();