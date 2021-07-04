package com.agnasarp.kafka;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducerService producer;

    @Autowired
    public KafkaController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<KafkaResponse> sendMessage(@RequestBody KafkaMessage kafkaMessage) {
        producer.sendMessage(kafkaMessage.getMessage());
        KafkaResponse kafkaResponse = new KafkaResponse();
        kafkaResponse.setStatus("OK!");
        return new ResponseEntity<KafkaResponse>(kafkaResponse, HttpStatus.OK);
    }
}
